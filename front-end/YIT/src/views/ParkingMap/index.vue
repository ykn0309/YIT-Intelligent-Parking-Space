<template>
    <div class="map">
        <div class="row">
            <div 
                v-for="(space, index) in row1"
                :key="index"
                class="space"
                :class="{occupied: space.occupied, free: !space.occupied}"
            >
                {{ space.parkId }}
            </div>
        </div>
        <div class="row">
            <div 
                v-for="(space, index) in row2"
                :key="index"
                class="space"
                :class="{occupied: space.occupied, free: !space.occupied}"
            >
                {{ space.parkId }}
            </div>
        </div>
        <div class="row">
            <div 
                v-for="(space, index) in row3"
                :key="index"
                class="space"
                :class="{occupied: space.occupied, free: !space.occupied}"
            >
                {{ space.parkId }}
            </div>
        </div>
    </div>
</template>

<script setup>
    import { onMounted, ref } from 'vue';
    import { getMap } from '@/utils/api'
    const row1 = ref([
    // {
    //         parkId: 1,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 2,
    //         occupied: false,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 3,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 4,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 5,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     }
    ])
    const row2 = ref([
    // {
    //         parkId: 6,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 7,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 8,
    //         occupied: false,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 9,
    //         occupied: false,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 10,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     }
    ])
    const row3 = ref([
    // {
    //         parkId: 11,
    //         occupied: false,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 12,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 13,
    //         occupied: true,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 14,
    //         occupied: false,
    //         userId: 1,
    //         carId: 'a'
    //     },
    //     {
    //         parkId: 15,
    //         occupied: false,
    //         userId: 1,
    //         carId: 'a'
    //     }
    ])

    const fetchMap = async () => {
        console.log('fetchMap')
        try {
            const response = await getMap()
            row1.value = response.slice(0, 5)
            row2.value = response.slice(5, 10)
            row3.value = response.slice(10, 15)
        } catch (error) {
            console.log('fetchMap() failed')
        }
    }

    onMounted(() => {
        fetchMap()
    })
</script>

<style scoped>
    .map {
        display: flex;
        flex-direction: column;
        justify-content: center;
        height: calc(100vh - 51px);
        width: calc(100vw - 181px);
    }

    .row {
        display: flex;
        /* height: 150px; */
        justify-content: center;
        align-items: center;
    }

    .row .space {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 20px;
        height: 200px;
        width: 120px;
        color: white;
        font-weight: 800;
        font-size: 40px;
    }

    .occupied {
        background-color: rgb(192, 15, 15);
    }

    .free {
        background-color: rgb(9, 112, 9);
    }

</style>