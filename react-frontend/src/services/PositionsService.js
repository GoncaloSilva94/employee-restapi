import axios from 'axios';

const POSITIONS_API_BASE_URL = "http://localhost:8080/positions";

class EmployeeService {

    getPositions(){
        return axios.get(POSITIONS_API_BASE_URL);
    }

}

export default new EmployeeService()