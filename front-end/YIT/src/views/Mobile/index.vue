<template>
    <div class="header">
        <span class="project-name">欢迎使用易停移动端</span>
    </div>
    <RouterView />
</template>

<style scoped>
    .header {
        display: flex;
        width: 100vw;
        height: 50px;
        border-bottom: 1px solid #dcdfe6;
    }
    
    .project-name {
        display: flex;
        align-items: center;
        text-align: center;
        margin-left: 30px;
        font-size: 25px;
        font-weight: 800;
    }
</style>