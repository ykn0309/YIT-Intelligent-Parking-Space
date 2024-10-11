import axios from 'axios'

const BASE_URL = 'http://localhost:8080';

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
export async function deleteCar(parkId) {
    try {
        const response = await axios.get(`${BASE_URL}/api/deleteCar`, {
            params: {
                parkId: parkId 
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
