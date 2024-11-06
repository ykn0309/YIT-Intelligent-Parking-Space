<template>
    <div class="header">
        <span class="project-name">易停Mobile</span>
    </div>
    <RouterView />
</template>

<style scoped>
   .header {
    display: flex;
    align-items: center;
    justify-content: flex-start; /* 左对齐内容 */
    width: 100vw;
    height: 50px;
    border-bottom: 1px solid #dcdfe6;
    background-color: #f4f4f4;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    padding-left: 20px; /* 调整与左边的距离 */
}

.project-name {
    font-family: 'Dancing Script', cursive; /* 使用飘逸的字体 */
    font-size: 32px; /* 增大字体以更突出 */
    font-weight: 600; /* 中等粗细，突出笔触 */
    color: #2c3e50; /* 深色调提升对比度 */
    letter-spacing: 1.5px; /* 更宽的字间距，增加优雅感 */
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.15); /* 柔和的文字阴影，增添立体感 */
    margin: 0; /* 确保没有额外边距 */
    padding: 5px 0; /* 上下增加一些内边距 */
}


</style>
