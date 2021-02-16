import React, { Component } from 'react';

import EmployeeService from '../services/EmployeeService';
import StatusService from '../services/StatusService';
import PositionsService from '../services/PositionsService';

class UpdateEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
            birthdate: '',
            address: '',
            statusValue: 1,
            positionValue: 1,
            statusList: [],
            positionsList: []
            

        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeBirthdateHandler = this.changeBirthdateHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.changeStatusHandler = this.changeStatusHandler.bind(this);
        this.changePositionsHandler = this.changePositionsHandler.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);

    }
    componentDidMount(){
        StatusService.getStatus().then((res) => {
            this.setState({statusList : res.data});

        });

        PositionsService.getPositions().then((res) => {
            this.setState({positionsList : res.data});

        });

        EmployeeService.getEmployeeId(this.state.id).then((res) =>{
            let employee = res.data;
            this.setState({name: employee.name,
                birthdate: employee.birthdate,
                address: employee.address
            });
        });

    }

    updateEmployee = (e) => {
        e.preventDefault();
        let employee = {name: this.state.name, birthdate: this.state.birthdate, address: this.state.address,
         status: this.state.statusValue, position: this.state.positionValue};
         console.log('employee => ' + JSON.stringify(employee));

         EmployeeService.updateEmployee(this.state.id, employee).then(res =>{
             this.props.history.push('/employee');
         });

    }

    changeNameHandler = (event ) => {
        this.setState({name: event.target.value});
    }

    changeBirthdateHandler = (event ) => {
        this.setState({birthdate: event.target.value});
    }

    changeAddressHandler = (event ) => {
        this.setState({address: event.target.value});
    }

    changeStatusHandler = (event) => {
        this.setState({statusValue: event.target.value});
    }

    changePositionsHandler = (event ) => {
        this.setState({positionValue: event.target.value});
    }

    cancel(){
        this.props.history.push('/employee');
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-center">Update Employee</h3>
                        <div className="card-body">
                            <form>
                                <div className="from-group">
                                    <label>Name</label>
                                    <input placeholder="Name" autocomplete="off" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}></input>
                                </div>
                                <div className="from-group">
                                    <label>Birthdate</label>
                                    <input type="date" placeholder="Birthdate" name="birthdate" className="form-control"
                                    value={this.state.birthdate} onChange={this.changeBirthdateHandler}></input>
                                </div>
                                <div className="from-group">
                                    <label>Address</label>
                                    <input placeholder="Address" autocomplete="off" name="address" className="form-control"
                                    value={this.state.address} onChange={this.changeAddressHandler}></input>
                                </div>
                                <br></br>
                                <div className="from-group">
                                    <label>Status</label>
                                    <select onChange={this.changeStatusHandler}>
                                        {this.state.statusList.map(status =>(
                                            <option key={status.id} value={status.id} >
                                                {status.description}

                                            </option>
                                        ))}
                                    </select>
                                </div>
                                <br></br>
                                <div className="from-group">
                                    <label>Positions</label>
                                    <select onChange={this.changePositionsHandler}>
                                        {this.state.positionsList.map(positions =>(
                                            <option key={positions.id} value={positions.id} >
                                                {positions.description}

                                            </option>
                                        ))}
                                    </select>
                                </div>
                                <button className="btn btn-success" onClick={this.updateEmployee}>Save</button>
                                <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>

                            </form>


                        </div>

                    </div>

                </div>
                
            </div>
        );
    }
}


export default UpdateEmployeeComponent;