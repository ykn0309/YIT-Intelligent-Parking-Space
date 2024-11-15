<template>
  <div class="parking-lot">
    <div
      v-for="(cell, index) in grid"
      :key="index"
      :class="cellClass(cell)"
    ></div>
  </div>
</template>



  
  <script>
  export default {
    props: {
      parkingSlots: {
        type: Array,
        required: true,
      },
    },
    data() {
      return {
        rows: 29, // 行数
        cols: 30, // 列数
        slotSize: { width: 2, height: 3 },
        aisleWidth: 4,
      };
    },
    computed: {
      grid() {
      const grid = Array(this.rows * this.cols).fill("empty");

      this.parkingSlots.forEach(({ slotNumber, occupied }) => {
        const x = (slotNumber - 1) % this.cols;
        const y = Math.floor((slotNumber - 1) / this.cols);

        for (let i = 0; i < this.slotSize.height; i++) {
          for (let j = 0; j < this.slotSize.width; j++) {
            const index = (y + i) * this.cols + (x + j);
            if (index < grid.length) {
              // 设置边框类
              let borderClass = "";
              if (i === 0) borderClass += " border-top";
              if (i === this.slotSize.height - 1) borderClass += " border-bottom";
              if (j === 0) borderClass += " border-left";
              if (j === this.slotSize.width - 1) borderClass += " border-right";

              // 添加状态和边框类
              grid[index] = occupied
                ? `occupied ${borderClass}`
                : `available ${borderClass}`;
            }
          }
        }
      });

      return grid;
    },
    },
    methods: {
      cellClass(cell) {
      return cell;
    },
  },
  };
  </script>
  
  <style scoped>
  .parking-lot {
    display: grid;
    grid-template-columns: repeat(30, 3vw); /* 使用 3vw 适应屏幕 */
    grid-template-rows: repeat(29, 3vw); /* 使用 3vw 适应屏幕 */
    
    width: 100vw;
    height: 100vh;
    background-color: white;
  }
  .empty {
  background-color: lightgray;
}
  .occupied {
    background-color: red;
    width: 100%;
    height: 100%;
    
  }
  .available {
    background-color: green;
    width: 100%;
    height: 100%;
    
  }

 /* 车位边缘黑色边框 */
.border-top {
  border-top: 2px solid black;
}
.border-right {
  border-right: 2px solid black;
}
.border-bottom {
  border-bottom: 2px solid black;
}
.border-left {
  border-left: 2px solid black;
}
  </style>
  