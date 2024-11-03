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
        rows: 50, // 行数
        cols: 50, // 列数
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
  
          //设置车位
          for (let i = 0; i < this.slotSize.height; i++) {
            for (let j = 0; j < this.slotSize.width; j++) {
              const index = (y + i) * this.cols + (x + j);
              if (index < grid.length) {
                grid[index] = occupied ? "occupied" : "available";
              }
            }
          }
        });
  
        // 设置过道
        // for (let r = 0; r < this.rows; r += this.slotSize.height + this.aisleWidth) {
        //   for (let i = 0; i < this.aisleWidth; i++) {
        //     for (let c = 0; c < this.cols; c++) {
        //       const index = (r + i) * this.cols + c;
        //       if (index < grid.length) grid[index] = "aisle";
        //     }
        //   }
        // }
  
        return grid;
      },
    },
    methods: {
      cellClass(cell) {
        switch (cell) {
          case "occupied":
            return "occupied";
          case "available":
            return "available";
          case "aisle":
            return "aisle";
          default:
            return "empty";
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .parking-lot {
    display: grid;
    grid-template-columns: repeat(50, 2vw); /* 使用 2vw 适应屏幕 */
    grid-template-rows: repeat(50, 2vw); /* 使用 2vw 适应屏幕 */
    
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
    border: 1px solid black; /* 加上黑色边框 */
  }
  .available {
    background-color: green;
    width: 100%;
    height: 100%;
    border: 1px solid black; /* 加上黑色边框 */
  }
  .aisle {
    background-color: white;
    width: 100%;
    height: 100%;
  }
  </style>
  