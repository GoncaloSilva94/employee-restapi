import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class SearchEmployeeComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            id: null,
            name: '',
            birthdate: '',
            address: '',
            status: '',
            position: '',
            created: '',
            updated: ''
            

        }
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
        this.return = this.return.bind(this);
    }

    componentDidMount(){
        EmployeeService.getEmployeeId(this.props.match.params.id).then((res) =>{
            let employee = res.data;
            this.setState({id: employee.id, name: employee.name,
                birthdate: employee.birthdate,
                address: employee.address, status: employee.status.description,
                position: employee.positions.description, created: employee.created,
                updated: employee.updated

            });
        });
    }

    editEmployee(id){
        this.props.history.push('/update-employee/' + id);
    }

    deleteEmployee(id){
        EmployeeService.deleteEmployee(id).then(res =>{
            this.props.history.push('/employee');
        });
    }

    return(){
        this.props.history.push('/employee');
    }

    render() {
        //Employee not found in database
        if(this.state.id == null){
            return (
                <div>
                    <h1 className="text-center">Employee not found, please enter a valid ID</h1>
                    <button onClick = {() => this.return()} className="btn btn-info">Home</button>
                    
                </div>

            );
        }
        
        //Employee found in database, return employee data
        return (
            
            <div>
                <br></br>
                 <h1 className="text-center">Employee {this.state.name} ID {this.state.id}</h1>
                 <button onClick = {() => this.return()} className="btn btn-info">Home</button>
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
                           
                                    <tr key = {this.state.id}>
                                        <td>{this.state.id}</td>
                                        <td>{this.state.name}</td>
                                        <td>{this.state.birthdate}</td>
                                        <td>{this.state.address}</td>
                                        <td>{this.state.status}</td>
                                        <td>{this.state.position}</td>
                                        <td>{this.state.created}</td>
                                        <td>{this.state.updated}</td>
                                        <td>
                                            
                                                <button onClick = {() => this.editEmployee(this.state.id)} className="btn btn-warning">Update</button>
                                                <button onClick = {() => this.deleteEmployee(this.state.id)} className="btn btn-danger" style={{marginLeft: "85px"}} >Delete</button>
                                           
                                        </td>

                                    </tr>
                              
                        </tbody>

                    </table>

                </div>
            </div>
        );
    }
}

export default SearchEmployeeComponent;