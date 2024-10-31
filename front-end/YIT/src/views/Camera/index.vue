<template>
<div class="container">
    <div class="box">
        <span class="label">入口</span>
        <el-upload 
            class="uploader"
            action="http://127.0.0.1:5000/upload" 
            :show-file-list="false"
            :on-success="handleSuccess_in"
            :auto-upload="true"
            >
            <img v-if="imgURL_in" :src="imgURL_in" class="img" />
            <el-icon v-else class="uploader-icon"><Plus /></el-icon>
        </el-upload>
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
                :src="imgURL_in"
            />
            </el-descriptions-item>
            <el-descriptions-item label="用户ID">易佳</el-descriptions-item>
            <el-descriptions-item label="车牌号">
            <el-tag size="small">{{ inCarid }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="车位ID">1</el-descriptions-item>
            <el-descriptions-item label="开始时间">
            2024-10-30 21:51:34
            </el-descriptions-item>
        </el-descriptions>
    </div>
    <div class="box">
        <span class="label">出口</span>
        <el-upload 
            class="uploader"
            action="http://127.0.0.1:5000/upload" 
            :show-file-list="false"
            :on-success="handleSuccess_out"
            :auto-upload="true"
            >
            <img v-if="imgURL_out" :src="imgURL_out" class="img" />
            <el-icon v-else class="uploader-icon"><Plus /></el-icon>
        </el-upload>
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
                :src="imgURL_out"
            />
            </el-descriptions-item>
            <el-descriptions-item label="用户ID">易佳</el-descriptions-item>
            <el-descriptions-item label="车牌号">
            <el-tag size="small">{{ outCarid }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="开始时间">2024-10-30 21:51:34</el-descriptions-item>
            <el-descriptions-item label="结束时间">2024-10-30 23:53:02</el-descriptions-item>
            <el-descriptions-item label="停车时间">2小时2分钟</el-descriptions-item>
            <el-descriptions-item label="应付金额">20元</el-descriptions-item>
        </el-descriptions>
    </div>
</div>
</template>
<script lang="ts" setup>
    import { ref } from 'vue'
    import type { UploadProps } from 'element-plus'
    import { Plus } from '@element-plus/icons-vue'

    const imgURL_in = ref('')
    const imgURL_out = ref('')
    const inMsg = ref('')
    const outMsg = ref('')
    const inCarid = ref('')
    const outCarid = ref('')

    const handleSuccess_in: UploadProps['onSuccess'] = (
        response,
        uploadFile
        ) => {
        imgURL_in.value = URL.createObjectURL(uploadFile.raw!)
        inMsg.value = response
        inCarid.value = response.carid
    }

    const handleSuccess_out: UploadProps['onSuccess'] = (
        response,
        uploadFile
        ) => {
        imgURL_out.value = URL.createObjectURL(uploadFile.raw!)
        outMsg.value = response
        outCarid.value = response.carid
    }

</script>
<style scoped>
    .container {
        display: flex;
        width: calc(100vw - 181px);
        height: calc(100vh - 51px);
    }

    .box {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 50%;
        margin: 20px;
        border: 1px solid #dcdfe6;
    }

    .label {
        font-size: 30px;
        font-weight: 800;
        margin: 20px;
    }

    .uploader .img {
        width: 300px;
        height: 300px;
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
        width: 300px;
        height: 300px;
        text-align: center;
    }

    .el-descriptions {
        padding: 50px;
    }
</style>