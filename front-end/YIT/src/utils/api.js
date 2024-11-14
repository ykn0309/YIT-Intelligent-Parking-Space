import axios from 'axios'

const BASE_URL = 'http://localhost:8080';
const PYTHON_SERVER_URL = 'http://localhost:5000'

//getAllCars
export async function getAllCars() {
    try {
        const response = await axios.get(`${BASE_URL}/api/getAllCars`);
        return response.data.data;
    } catch (error) {
        throw new Error('getAllCars() failed');
    }
}

//addCar
export async function addCar(formData) {
    try {
        const response = await axios.post(`${BASE_URL}/api/addCar`, formData);
        return response.data;
    } catch (error) {
        throw new Error('addCar() failed.');
    }
}

//deleteCar
export async function deleteCar(parkId, endTime) {
    try {
        const response = await axios.get(`${BASE_URL}/api/deleteCar`, {
            params: {
                parkId: parkId,
                endTime: endTime
            }
        });
        return response.data;
    } catch (error) {
        throw new Error('deleteCar() failed.');
    }
}

//getMap
export async function getMap() {
    try {
        const response = await axios.get( `${BASE_URL}/api/getCarsMap`);
        return response.data.data;
    } catch (error) {
        throw new Error('getMap() failed.');
    }
}

//getAllMap
export async function getAllMap() {
    try {
        const response = await axios.get( `${BASE_URL}/api/getAllMap`);
        return response.data.data;
    } catch (error) {
        throw new Error('getAllMap() failed.');
    }
}

//getPath
export async function getPath(pageid) {
    try {
        const response = await axios.get( `${PYTHON_SERVER_URL}/api/getPath`,{params:{pageid: pageid}});
        console.log(response.data)
        return response.data;
    } catch (error) {
        throw new Error('getPath() failed.');
    }
}
