import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class ListEmployeeComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            nameInput: '',
            idInput: 1,
            employees: []

        }
        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
        this.changeIdHandler = this.changeIdHandler.bind(this);
        this.getEmployee = this.getEmployee.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.getEmployeeName = this.getEmployeeName.bind(this);
        this.showAll = this.showAll.bind(this);


       
    }

    deleteEmployee(id){
        EmployeeService.deleteEmployee(id).then(res =>{
            this.setState({employees: this.state.employees.filter(employee => employee.id !== id)});
        });
    }

    editEmployee(id){
        this.props.history.push('/update-employee/' + id);
    }

    componentDidMount(){

        EmployeeService.getEmployees().then((res) => {
            this.setState({employees : res.data});

        });
    }

    addEmployee(){
        this.props.history.push('/add-employee');
    }

    getEmployee(){
        this.props.history.push('/search-employee/' + this.state.idInput);

    }

    changeIdHandler = (event) =>{
        this.setState({idInput: event.target.value});
    }

    changeNameHandler = (event) =>{
        this.setState({nameInput : event.target.value});
    }

    getEmployeeName(){
        EmployeeService.getEmployeeName(this.state.nameInput).then((res) => {
            this.setState({employees : res.data});

        });
    }

    showAll(){
        EmployeeService.getEmployees().then((res) => {
            this.setState({employees : res.data});

        });

    }

   

    render() {
        return (
            
            <div>
                <br></br>
                <h1 className="text-center">Employees List</h1>
                <div className="card-body">
                    
                    <button className="btn btn-primary" onClick={this.addEmployee}>Add Employee</button>
                    <input type ="Number" placeholder="Search by Id" name="id"  onChange={this.changeIdHandler} style={{marginLeft: "15px"}}></input>
                    <button className="btn btn-dark" onClick={this.getEmployee} style={{marginLeft: "5px"}}>Search</button>
                    <input placeholder="Search by Name" autocomplete="off" name="name"  onChange={this.changeNameHandler} style={{marginLeft: "15px"}}></input>
                    <button className="btn btn-dark" onClick={this.getEmployeeName} style={{marginLeft: "5px"}}>Search</button>
                    <button className="btn btn-info" onClick={this.showAll} style={{marginLeft: "5px"}}>Show all</button>
                </div>
                
            
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Employee id</th>
                                <th>Employee name</th>
                                <th>Employee birthdate</th>
                                <th>Employee address</th>
                                <th>Employee status</th>
                                <th>Employee position</th>
                                <th>Employee created</th>
                                <th>Employee updated</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.employees.map(
                                    employee =>
                                    <tr key = {employee.id}>
                                        <td>{employee.id}</td>
                                        <td>{employee.name}</td>
                                        <td>{employee.birthdate}</td>
                                        <td>{employee.address}</td>
                                        <td>{employee.status.description}</td>
                                        <td>{employee.positions.description}</td>
                                        <td>{employee.created}</td>
                                        <td>{employee.updated}</td>
                                        <td>
                                            
                                                <button onClick = {() => this.editEmployee(employee.id)} className="btn btn-warning">Update</button>
                                                <button onClick = {() => this.deleteEmployee(employee.id)} className="btn btn-danger" style={{marginLeft: "85px"}}>Delete</button>
                                           
                                        </td>

                                    </tr>
                                )
                            }
                        </tbody>

                    </table>

                </div>
                
            </div>
        );
    }
}

export default ListEmployeeComponent;