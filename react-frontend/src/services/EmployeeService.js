import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/employee";
const EMPLOYEE_API_GET_NAME = "http://localhost:8080/employee/search";

class EmployeeService {

    getEmployees(){
        return axios.get(EMPLOYEE_API_BASE_URL);
    }

    getEmployeeName(name){
        return axios.get(EMPLOYEE_API_GET_NAME + '/' + name)

    }

    addEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL, employee);
    }

    getEmployeeId(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL + '/' + employeeId);
    }

    updateEmployee(employeeId, employee){
        return axios.put(EMPLOYEE_API_BASE_URL + '/' + employeeId, employee);
    }

    deleteEmployee(employeeId){
        return axios.delete(EMPLOYEE_API_BASE_URL + '/' + employeeId);
    }

}

export default new EmployeeService()