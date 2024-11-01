<template>
    <div class="map">
      <ParkingMap :parkingSlots="parkingSlots" />
    </div>
  </template>
  
  <script>
  import ParkingMap from "./components/ParkingMap.vue";
  
  export default {
    components: {
      ParkingMap,
    },
    data() {
      return {
        // 示例数据：车位编号和占用状态
        parkingSlots: [
      { slotNumber: 1, occupied: 1 },
      { slotNumber: 3, occupied: 0 },
      { slotNumber: 5, occupied: 1 },
      { slotNumber: 7, occupied: 0 },
      { slotNumber: 9, occupied: 1 },
      { slotNumber: 11, occupied: 1 },
      { slotNumber: 13, occupied: 0 },
      { slotNumber: 15, occupied: 1 },
      { slotNumber: 17, occupied: 0 },
      { slotNumber: 19, occupied: 1 },
      { slotNumber: 21, occupied: 1 },
      { slotNumber: 23, occupied: 0 },
      { slotNumber: 25, occupied: 1 },
      { slotNumber: 27, occupied: 0 },
      { slotNumber: 29, occupied: 1 },
      { slotNumber: 31, occupied: 0 },
      { slotNumber: 37, occupied: 0 },
      { slotNumber: 39, occupied: 0 },
      { slotNumber: 41, occupied: 0 },
      { slotNumber: 43, occupied: 0 },
      { slotNumber: 45, occupied: 0 },
      { slotNumber: 47, occupied: 0 },
      { slotNumber: 49, occupied: 0 },
      { slotNumber: 351, occupied: 1 },
      { slotNumber: 353, occupied: 1 },
      { slotNumber: 355, occupied: 1 },
      { slotNumber: 357, occupied: 0 },
      { slotNumber: 359, occupied: 1 },
      { slotNumber: 361, occupied: 0 },
      { slotNumber: 363, occupied: 1 },
      { slotNumber: 369, occupied: 0 },
      { slotNumber: 371, occupied: 1 },
      { slotNumber: 373, occupied: 1 },
      { slotNumber: 375, occupied: 1 },
      { slotNumber: 377, occupied: 0 },
      { slotNumber: 379, occupied: 1 },
      { slotNumber: 381, occupied: 0 },
      { slotNumber: 383, occupied: 0 },
      { slotNumber: 385, occupied: 0 },
      { slotNumber: 387, occupied: 0 },
      { slotNumber: 389, occupied: 0 },
      { slotNumber: 391, occupied: 0 },
      { slotNumber: 393, occupied: 1 },
      { slotNumber: 395, occupied: 1 },
      { slotNumber: 397, occupied: 0 },
      { slotNumber: 399, occupied: 1 },
      { slotNumber: 551, occupied: 1 },
      { slotNumber: 553, occupied: 0 },
      { slotNumber: 555, occupied: 1 },
      { slotNumber: 557, occupied: 1 },
      { slotNumber: 559, occupied: 1 },
      { slotNumber: 561, occupied: 1 },
      { slotNumber: 563, occupied: 1 },
      { slotNumber: 569, occupied: 0 },
      { slotNumber: 571, occupied: 1 },
      { slotNumber: 573, occupied: 1 },
      { slotNumber: 575, occupied: 1 },
      { slotNumber: 577, occupied: 0 },
      { slotNumber: 579, occupied: 1 },
      { slotNumber: 581, occupied: 0 },
      { slotNumber: 583, occupied: 0 },
      { slotNumber: 585, occupied: 1 },
      { slotNumber: 587, occupied: 0 },
      { slotNumber: 589, occupied: 1 },
      { slotNumber: 591, occupied: 0 },
      { slotNumber: 593, occupied: 1 },
      { slotNumber: 595, occupied: 1 },
      { slotNumber: 597, occupied: 0 },
      { slotNumber: 599, occupied: 0 },
      { slotNumber: 901, occupied: 1 },
      { slotNumber: 903, occupied: 1 },
      { slotNumber: 905, occupied: 1 },
      { slotNumber: 907, occupied: 1 },
      { slotNumber: 909, occupied: 1 },
      { slotNumber: 911, occupied: 1 },
      { slotNumber: 913, occupied: 1 },
      { slotNumber: 915, occupied: 1 },
      { slotNumber: 917, occupied: 1 },
      { slotNumber: 919, occupied: 1 },
      { slotNumber: 921, occupied: 1 },
      { slotNumber: 923, occupied: 1 },
      { slotNumber: 925, occupied: 1 },
      { slotNumber: 931, occupied: 1 },
      { slotNumber: 933, occupied: 1 },
      { slotNumber: 935, occupied: 1 },
      { slotNumber: 937, occupied: 1 },
      { slotNumber: 939, occupied: 1 },
      { slotNumber: 941, occupied: 1 },
      { slotNumber: 943, occupied: 1 },
      { slotNumber: 945, occupied: 1 },
      { slotNumber: 947, occupied: 1 },
      { slotNumber: 949, occupied: 1 },
      { slotNumber: 2103, occupied: 0 },
      { slotNumber: 2109, occupied: 1 },
      { slotNumber: 2115, occupied: 0 },
      { slotNumber: 2121, occupied: 1 },
      // 继续添加更多车位数据，尽量覆盖不同区域
    ],
      };
    },
  };
  </script>
  
  <style>
.map {
        display: flex;
        flex-direction: column;
        justify-content: center;
        height: calc(100vh - 0px);
        width: calc(100vw - 181px);
    }
  </style>
  