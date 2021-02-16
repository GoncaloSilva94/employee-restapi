import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListEmployeeComponent from './components/ListEmployeeComponent';
import HeaderComponent from './components/HeaderComponent';
import CreateEmployeeComponent from './components/CreateEmployeeComponent';
import UpdateEmployeeComponent from './components/UpdateEmployeeComponent';
import SearchEmployeeComponent from './components/SearchEmployeeComponent';


function App() {
  return (
   <div>
     <HeaderComponent/>
      <Router>
        <div className="container">
          
    
          <div className="container">
            <Switch>
                <Route path="/" exact component = {ListEmployeeComponent}></Route>
                <Route path="/employee" component = {ListEmployeeComponent}></Route>
                <Route path="/add-employee" component = {CreateEmployeeComponent}></Route>
                <Route path="/update-employee/:id" component = {UpdateEmployeeComponent}></Route>
                <Route path="/search-employee/:id" component = {SearchEmployeeComponent}></Route>
                <Route path="/search-employee-name/:name" component = {ListEmployeeComponent}></Route>
            </Switch>
       
          </div>
        </div>
      </Router>
    </div> 
  );
}

export default App;
