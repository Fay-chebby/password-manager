import axios from "axios";

const USERS_REST_API_URL = 'http://localhost:8080/api/v0/';

class UserService {
    getUsers() {
        return axios.get(USERS_REST_API_URL);
    }

    signup(userData) {
        return axios.post(`${USERS_REST_API_URL}signup`, userData);
    }

    login(username, password) {
        return axios.post(`${USERS_REST_API_URL}login`, { username, password });
    }


}

export default new UserService();
