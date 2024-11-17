<template>
    <div class="map">
      <ParkingMap :parkingSlots="parkingSlots" :path="path" />
    </div>
</template>
  
<script setup>
  import { onMounted, ref } from "vue";
  import { useRoute } from 'vue-router';
  import ParkingMap from "./components/userParkingMap.vue";
  import { getPath, getOccupied } from "@/utils/api";

  const route = useRoute()
  const parkingSlots = ref([
        { slotNumber: 63, occupied: 0 },
        { slotNumber: 65, occupied: 0 },
        { slotNumber: 67, occupied: 0 },
        { slotNumber: 69, occupied: 0 },
        { slotNumber: 71, occupied: 0 },
        { slotNumber: 73, occupied: 0 },
        { slotNumber: 77, occupied: 0 },
        { slotNumber: 79, occupied: 0 },
        { slotNumber: 81, occupied: 0 },
        { slotNumber: 83, occupied: 0 },
        { slotNumber: 85, occupied: 0 },
        { slotNumber: 87, occupied: 0 },
        { slotNumber: 183, occupied: 0 },
        { slotNumber: 185, occupied: 0 },
        { slotNumber: 187, occupied: 0 },
        { slotNumber: 189, occupied: 0 },
        { slotNumber: 191, occupied: 0 },
        { slotNumber: 193, occupied: 0 },
        { slotNumber: 197, occupied: 0 },
        { slotNumber: 199, occupied: 0 },
        { slotNumber: 201, occupied: 0 },
        { slotNumber: 203, occupied: 0 },
        { slotNumber: 205, occupied: 0 },
        { slotNumber: 207, occupied: 0 },
        { slotNumber: 333, occupied: 0 },
        { slotNumber: 335, occupied: 0 },
        { slotNumber: 337, occupied: 0 },
        { slotNumber: 339, occupied: 0 },
        { slotNumber: 341, occupied: 0 },
        { slotNumber: 343, occupied: 0 },
        { slotNumber: 347, occupied: 0 },
        { slotNumber: 349, occupied: 0 },
        { slotNumber: 351, occupied: 0 },
        { slotNumber: 353, occupied: 0 },
        { slotNumber: 355, occupied: 0 },
        { slotNumber: 357, occupied: 0 },
        { slotNumber: 453, occupied: 0 },
        { slotNumber: 455, occupied: 0 },
        { slotNumber: 457, occupied: 0 },
        { slotNumber: 459, occupied: 0 },
        { slotNumber: 461, occupied: 0 },
        { slotNumber: 463, occupied: 0 },
        { slotNumber: 467, occupied: 0 },
        { slotNumber: 469, occupied: 0 },
        { slotNumber: 471, occupied: 0 },
        { slotNumber: 473, occupied: 0 },
        { slotNumber: 475, occupied: 0 },
        { slotNumber: 477, occupied: 0 },
        { slotNumber: 603, occupied: 0 },
        { slotNumber: 605, occupied: 0 },
        { slotNumber: 607, occupied: 0 },
        { slotNumber: 609, occupied: 0 },
        { slotNumber: 611, occupied: 0 },
        { slotNumber: 613, occupied: 0 },
        { slotNumber: 617, occupied: 0 },
        { slotNumber: 619, occupied: 0 },
        { slotNumber: 621, occupied: 0 },
        { slotNumber: 623, occupied: 0 },
        { slotNumber: 625, occupied: 0 },
        { slotNumber: 627, occupied: 0 },
        { slotNumber: 723, occupied: 0 },
        { slotNumber: 725, occupied: 0 },
        { slotNumber: 727, occupied: 0 },
        { slotNumber: 729, occupied: 0 },
        { slotNumber: 731, occupied: 0 },
        { slotNumber: 733, occupied: 0 },
        { slotNumber: 737, occupied: 0 },
        { slotNumber: 739, occupied: 0 },
        { slotNumber: 741, occupied: 0 },
        { slotNumber: 743, occupied: 0 },
        { slotNumber: 745, occupied: 0 },
        { slotNumber: 747, occupied: 0 },
        // 继续添加更多车位数据，尽量覆盖不同区域
          ])
  const path = ref([])
  const occupied = ref([])
  
  // console.log(path.value)

  const getPath1 = async (pageid) => {
        try {
            const response = await getPath(pageid)
            console.log('getPath')
            path.value = response.road
        } catch(error) {
            console.log('getPath() failed')
        }
    }

  const getOccupied1 = async () => {
        try {
            const response = await getOccupied()
            occupied.value = response
            console.log(occupied.value)

            // 修改parkingSlots，更正occupied信息
            if (occupied.value.data.length === parkingSlots.value.length) {
              parkingSlots.value = parkingSlots.value.map((slot, index) => ({
                ...slot,
                occupied: occupied.value.data[index], // 使用 occupied 数组中的值更新状态
              }));
            } else {
              console.error("Occupied data length mismatch!");
            }
            console.log('parkingSlots:', parkingSlots.value)
            
        } catch(error) {
            console.log('getOccupied() failed')
        }
    }
  
  onMounted(() => {
    const pageid = route.params.pageid
    getPath1(pageid)
    getOccupied1()
  })
  
</script>
  
<style scoped>
  .map {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: calc(100vh - 102px);
    width: 100vw;
    overflow: auto;
  }
</style>
  