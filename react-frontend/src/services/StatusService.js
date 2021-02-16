import axios from 'axios';

const STATUS_API_BASE_URL = "http://localhost:8080/status";

class EmployeeService {

    getStatus(){
        return axios.get(STATUS_API_BASE_URL);
    }

}

export default new EmployeeService()