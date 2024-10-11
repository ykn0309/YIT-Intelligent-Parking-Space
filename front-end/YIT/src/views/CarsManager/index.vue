<template>
    <div class="command">
        <div class="add">
            <span>车位ID</span>
            <el-input v-model="parkIdInput" style="width: 240px" placeholder="请输入" />
            <span>用户ID</span>
            <el-input v-model="userIdInput" style="width: 240px" placeholder="请输入" />
            <span>车牌号</span>
            <el-input v-model="carIdInput" style="width: 240px" placeholder="请输入" />
            <span>开始时间</span>
            <el-input v-model="startTimeInput" style="width: 240px" placeholder="请输入" />
            <el-button type="success" @click="addCar1">添加</el-button>
        </div>
    </div>
    <el-table :data="filterTableData" class="table">
        <el-table-column prop="parkId" label="车位ID" width="180" />
        <el-table-column prop="userId" label="用户ID" width="180" />
        <el-table-column prop="carId" label="车牌号" width="180" />
        <el-table-column prop="startTime" label="开始时间" width="180" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="搜索" style="width: 50%;" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                    编辑
                </el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
                    删除
                </el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog v-model="dialogVisible" title="编辑车辆信息">
        <div class="edit">
            <span>车位ID</span>
            <el-input v-model="parkIdEdit" placeholder="请输入车位ID" />
            <span>用户ID</span>
            <el-input v-model="userIdEdit" placeholder="请输入用户ID" />
            <span>车牌号</span>
            <el-input v-model="carIdEdit" placeholder="请输入车牌号" />
            <span>开始时间</span>
            <el-input v-model="startTimeEdit" placeholder="请输入开始时间" />
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="handleCancelEdit">取 消</el-button>
            <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
    </el-dialog>
</template>

<script setup>
    import { computed, onMounted, ref } from 'vue'
    import { addCar, deleteCar, getAllCars } from '@/utils/api';
    const parkIdInput = ref('')
    const userIdInput = ref('')
    const carIdInput = ref('')
    const startTimeInput = ref('')
    const parkIdEdit = ref('')
    const userIdEdit = ref('')
    const carIdEdit = ref('')
    const startTimeEdit = ref('')
    const search = ref('')
    const currentEditIndex = ref(null);
    const dialogVisible = ref(false);
    const tableData = ref([
        // {
        //     parkId: 1,
        //     userId: 123,
        //     carId: '鄂A12345',
        //     startTime: '2024-10-01 14:00:56'
        // },
        // {
        //     parkId: 2,
        //     userId: 456,
        //     carId: '鄂A67890',
        //     startTime: '2024-10-02 15:23:12'
        // },
        // {
        //     parkId: 3,
        //     userId: 789,
        //     carId: '鄂A56874',
        //     startTime: '2024-10-02 09:30:02'
        // },
        // {
        //     parkId: 4,
        //     userId: 259,
        //     carId: '鄂A89521',
        //     startTime: '2024-10-01 17:51:35'
        // },
    ])

    const fetchAllCars = async () => {
        console.log('fetchAllCars')
        try {
            const response = await getAllCars()
            tableData.value = response
        } catch (error) {
            console.log('fetchAllCars() failed')
        }
    }

    onMounted(() => {
        fetchAllCars()
    })

    const addCar1 = async () => {
        console.log('addCar1')
        const newCar = {  
            parkId: parseInt(parkIdInput.value, 10),
            userId: parseInt(userIdInput.value, 10),
            carId: carIdInput.value,  
            startTime: startTimeInput.value,
            occupied: true
        }
        try {
            const response = await addCar(newCar)
            tableData.value.push(newCar)
            clearInputs()
        } catch (error) {
            console.log('addCar1() failed')
        }
    }

    const filterTableData = computed(() =>
        tableData.value.filter(
            (data) => 
                !search.value ||
                data.parkId.toString().includes(search.value) ||
                data.userId.toString().includes(search.value) ||
                data.carId.includes(search.value) ||
                data.startTime.includes(search.value)
        )
        
    )

    const handleEdit = (index, row) => {
        dialogVisible.value = true;
        currentEditIndex.value = index;
        parkIdEdit.value = row.parkId;
        userIdEdit.value = row.userId;
        carIdEdit.value = row.carId;
        startTimeEdit.value = row.startTime;
    }

    const handleDelete = async (index, row) => {
        try {
            const response = await deleteCar(row.parkId)
            tableData.value.splice(index, 1)
            console.log('delete')
        } catch(error) {
            console.log('handleDelete() failed')
        }
    }

    const saveEdit = () => {
        const updatedCar = {  
            parkId: parseInt(parkIdEdit.value, 10),
            userId: parseInt(userIdEdit.value, 10),
            carId: carIdEdit.value,  
            startTime: startTimeEdit.value  
        }

        if (currentEditIndex.value !== null) {
            tableData.value[currentEditIndex.value] = updatedCar;
            currentEditIndex.value = null;
        }

        dialogVisible.value = false;
        clearEditInputs();
    }

    const handleCancelEdit = () => {
        dialogVisible.value = false
        clearEditInputs()
    }

    const clearInputs = () => {
        parkIdInput.value = '';  
        userIdInput.value = '';  
        carIdInput.value = '';  
        startTimeInput.value = '';
    }

    const clearEditInputs = () => {
        parkIdEdit.value = '';  
        userIdEdit.value = '';  
        carIdEdit.value = '';  
        startTimeEdit.value = '';
    }
</script>

<style scoped>
    .table {
        display: flex;
        width: calc(100% - 181px);
    }

    .command {
        display: flex;
        padding: 40px 0;
        border-bottom: 1px solid #dcdfe6;
    }

    .command span {
        margin: 0px 10px 0px 20px;
    }

    .command .el-button {
        margin: 0 20px 0 20px;
    }

    .edit span {
        display: flex;
        margin: 10px 0;
    }

    .dialog-footer {
        display: flex;
        margin: 15px 0 0 0;
    }
</style>