<template>
    <el-table :data="tableData" class="table">
      <el-table-column prop="parkId" label="车位ID" width="180" />
      <el-table-column prop="userId" label="用户ID" width="180" />
      <el-table-column prop="carId" label="车牌号" width="180"/>
      <el-table-column prop="startTime" label="开始时间" />
    </el-table>
</template>

<script setup>
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
</script>

<style scoped>
    .table {
        /* float: right; */
        width: calc(100% - 181px);
    }
</style>