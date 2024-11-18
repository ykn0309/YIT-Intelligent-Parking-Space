<template>
<div class="container">
    <div class="box">
        <div class="uploader-box">
            <span class="label">入口</span>
            <el-upload 
                class="uploader"
                action="http://127.0.0.1:5000/api/entrance" 
                :show-file-list="false"
                :on-success="handleSuccess_in"
                :auto-upload="true"
                >
                <img v-if="inImgURL" :src="inImgURL" class="img" />
                <el-icon v-else class="uploader-icon"><Plus /></el-icon>
            </el-upload>
        </div>
        <div class="result-box">
            <el-descriptions
                title="识别结果"
                direction="vertical"
                border
                style="margin-top: 20px"
            >
                <el-descriptions-item
                :rowspan="2"
                :width="140"
                label="Photo"
                align="center"
                >
                <el-image
                    style="width: 100px; height: 100px"
                    :src="inImgURL"
                />
                </el-descriptions-item>
                <el-descriptions-item label="用户ID">{{ inUserId }}</el-descriptions-item>
                <el-descriptions-item label="车牌号">
                <el-tag size="small">{{ inCarid }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="车位ID">{{ inParkId }}</el-descriptions-item>
                <el-descriptions-item label="开始时间">{{ inStartTime }}</el-descriptions-item>
            </el-descriptions>
            <el-button type="primary" @click="addCar1" :disabled="inButtonDisable" size="large" style="font-size: 20px;">确认</el-button>
            <div>{{ 'http://localhost:5173/mobile/usermap/' + inPageId }}</div>
            <canvas ref="canvas" v-if="inConfirmed"></canvas>
        </div>
    </div>
    <div class="box">
        <div class="uploader-box">
            <span class="label">出口</span>
            <el-upload 
                class="uploader"
                action="http://127.0.0.1:5000/api/exit" 
                :show-file-list="false"
                :on-success="handleSuccess_out"
                :auto-upload="true"
                >
                <img v-if="outImgURL" :src="outImgURL" class="img" />
                <el-icon v-else class="uploader-icon"><Plus /></el-icon>
            </el-upload>
        </div>
        <div class="result-box">
            <el-descriptions
                title="识别结果"
                direction="vertical"
                border
                style="margin-top: 20px"
            >
                <el-descriptions-item
                :rowspan="2"
                :width="140"
                label="Photo"
                align="center"
                >
                <el-image
                    style="width: 100px; height: 100px"
                    :src="outImgURL"
                />
                </el-descriptions-item>
                <el-descriptions-item label="用户ID">{{ outUserId }}</el-descriptions-item>
                <el-descriptions-item label="车牌号">
                <el-tag size="small">{{ outCarid }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="开始时间">{{ outStartTime }}</el-descriptions-item>
                <el-descriptions-item label="结束时间">{{ outEndTime }}</el-descriptions-item>
                <el-descriptions-item label="停车时间">{{ parkingTime }}</el-descriptions-item>
                <el-descriptions-item label="应付金额">{{ cost }}</el-descriptions-item>
            </el-descriptions>
            <div class="out-conf-qr">
                <el-button type="primary" @click="deleleCar1" :disabled="outButtonDisable" size="large" style="font-size: 20px;">确认</el-button>
                <div class="out-qr">
                    <div class="outmsg" v-if="outMsg">{{ outMsg }}</div>
                    <img :src="qrCodeData"  v-if="outConfirmed" />
                </div>
            </div>
        </div>
    </div>
</div>
</template>
<script lang="ts" setup>
    import { computed, onMounted, ref } from 'vue'
    import type { UploadProps } from 'element-plus'
    import { Plus } from '@element-plus/icons-vue'
    import { addCar, deleteCar } from '@/utils/api';
    import QRCode from 'qrcode';

    const inImgURL = ref('')
    const outImgURL = ref('')
    const inMsg = ref('')
    const outMsg = ref('')
    const inCarid = ref('')
    const outCarid = ref('')
    const inStartTime = ref('')
    const outStartTime = ref('')
    const outEndTime = ref('')
    const inUserId = ref('')
    const outUserId = ref('')
    const inParkId = ref('')
    const outParkId = ref('')
    const parkingTime = ref('')
    const cost = ref()
    const inPageId = ref()
    const inButtonDisable = ref(false)
    const outButtonDisable = ref(false)
    const inConfirmed = ref(false)
    const outConfirmed = ref(false)
    const QRurl = computed(() => {
        // 确保 `inPageId` 存在才生成 URL，避免 undefined 导致错误
        return inPageId.value ? `http://localhost:5173/mobile/usermap/${inPageId.value}` : '';
    });
    const qrCodeData = ref(null)

    const handleSuccess_in: UploadProps['onSuccess'] = (
        response,
        uploadFile
        ) => {
        inButtonDisable.value = false
        inImgURL.value = URL.createObjectURL(uploadFile.raw!)
        if (response.message != 'success') {
            inButtonDisable.value = true
            return
        }
        inCarid.value = response.carid
        inStartTime.value = response.startTime
        inUserId.value = response.userid
        inParkId.value = response.parkId
        inPageId.value = response.pageId
        inConfirmed.value = false
    }

    const handleSuccess_out: UploadProps['onSuccess'] = async (
        response,
        uploadFile
        ) => {
        outConfirmed.value = false
        outButtonDisable.value = false
        outMsg.value = ''
        outImgURL.value = URL.createObjectURL(uploadFile.raw!)
        outCarid.value = response.carid
        outStartTime.value = response.startTime
        outEndTime.value = response.endTime
        outUserId.value = response.userid
        outParkId.value = response.parkId
        parkingTime.value = `${response.parkingTime}分钟`
        cost.value = response.cost.toFixed(2)
        if (response.message == 'nomoney') {
            outConfirmed.value = true
            await generateQRCode1()
            outButtonDisable.value = true
            outMsg.value = '余额不足，请扫码充值'
            return
        }
    }

    const addCar1 = async () => {
        const newCar = {  
            parkId: parseInt(inParkId.value, 10),
            userId: parseInt(inUserId.value, 10),
            carId: inCarid.value,  
            startTime: inStartTime.value,
            occupied: true
        }
        inConfirmed.value = true
        try {
            const response = await addCar(newCar)
            generateQRCode(); // 生成二维码
        } catch (error) {
            console.log('addCar1() failed')
        }
    }

    const deleleCar1 = async () => {
        try {
            const response = await deleteCar(outParkId.value, outEndTime.value, cost.value)
            console.log(response)
            if (response.status== 'success') {
                outMsg.value = '付款成功'
            }
            console.log('delete')
        } catch(error) {
            console.log('handleDelete() failed')
        }
    }

    // 画布引用
    const canvas = ref<HTMLCanvasElement | null>(null);


    // 生成二维码
    const generateQRCode = () => {
    // if (canvas.value) {
    
        QRCode.toCanvas(canvas.value, QRurl.value, {
        width: 80, // 二维码宽度
        margin: 2,  // 二维码边距
        }).catch((err) => {
        console.error('生成二维码失败:', err);
        });
    // }
    };

    const generateQRCode1 = async () => {
        try{
            const qrCodeUrl = await QRCode.toDataURL('http://localhost:5173/userlogin', {
            width: 80, // 二维码宽度
            margin: 2,  // 二维码边距
            })
            qrCodeData.value = qrCodeUrl
        } catch (error) {
            console.error('生成二维码失败:', error)
        }
    }

</script>
<style scoped>
    .container {
        display: flex;
        width: calc(100vw - 181px);
        height: calc(100vh - 51px);
    }

    .box {
        width: 50%;
        margin: 20px;
        border: 1px solid #dcdfe6;
    }

    .uploader-box {
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 40%;
    }

    .result-box {
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 60%;
    }

    .label {
        font-size: 30px;
        font-weight: 800;
        margin: 20px;
    }

    .uploader .img {
        width: 200px;
        height: 200px;
        display: block;
    }

    .uploader {
        border: 1px dashed black;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    .uploader:hover {
        border-color: var(--el-color-primary);
    }

    .el-icon.uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 200px;
        height: 200px;
        text-align: center;
    }

    .el-descriptions {
        padding: 20px;
    }

    .out-conf-qr {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .out-qr {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin-left: 10px;
    }
</style>