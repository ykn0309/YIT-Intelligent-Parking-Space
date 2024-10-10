<template>
    <div class="command">
        <div class="add">
            <span>车位ID</span>
            <el-input v-model="parkIdInput" style="width: 240px" placeholder="Please input" />
            <span>用户ID</span>
            <el-input v-model="userIdInput" style="width: 240px" placeholder="Please input" />
            <span>车牌号</span>
            <el-input v-model="carIdInput" style="width: 240px" placeholder="Please input" />
            <span>开始时间</span>
            <el-input v-model="startTimeInput" style="width: 240px" placeholder="Please input" />
            <el-button type="success" @click="addCar">添加</el-button>
        </div>
    </div>
    <el-table :data="filterTableData" class="table">
        <el-table-column prop="parkId" label="车位ID" width="180" />
        <el-table-column prop="userId" label="用户ID" width="180" />
        <el-table-column prop="carId" label="车牌号" width="180" />
        <el-table-column prop="startTime" label="开始时间" width="180" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" style="width: 50%;" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                    Edit
                </el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
                    Delete
                </el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script setup>
    import { computed, ref } from 'vue'
    const parkIdInput = ref('')
    const userIdInput = ref('')
    const carIdInput = ref('')
    const startTimeInput = ref('')
    const search = ref('')
    const tableData = [
        {
            parkId: 1,
            userId: 123,
            carId: '鄂A12345',
            startTime: '2024-10-01 14:00:56'
        },
        {
            parkId: 2,
            userId: 456,
            carId: '鄂A67890',
            startTime: '2024-10-02 15:23:12'
        },
        {
            parkId: 3,
            userId: 789,
            carId: '鄂A56874',
            startTime: '2024-10-02 09:30:02'
        },
        {
            parkId: 4,
            userId: 259,
            carId: '鄂A89521',
            startTime: '2024-10-01 17:51:35'
        },
    ]

    const addCar = () => {
        console.log('addCar()')
        const newCar = {  
            parkId: parseInt(parkIdInput.value, 10),
            userId: parseInt(userIdInput.value, 10),
            carId: carIdInput.value,  
            startTime: startTimeInput.value  
        }
        tableData.push(newCar)
        parkIdInput.value = ''  
        userIdInput.value = ''  
        carIdInput.value = ''  
        startTimeInput.value = ''
        console.log(tableData)
    }

    const filterTableData = computed(() =>
        tableData.filter(
            (data) => 
                !search.value ||
                data.parkId.toString().includes(search.value) ||
                data.userId.toString().includes(search.value) ||
                data.carId.includes(search.value) ||
                data.startTime.includes(search.value)
        )
        
    )
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
</style>