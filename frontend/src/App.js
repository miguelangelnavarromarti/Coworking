import './App.css';
import React from 'react';
import { BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

import Home from "./Components/Home";
import Table from "./Components/Table";
import Selectable from './Components/Selectable';

class App extends React.Component {
  render() {
    return (
      <Router>
        <div className="App">
          <nav>
            <ul className='header'>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/table">Table</Link>
              </li>
              <li>
                <Link to="/selectable">Selectable</Link>
              </li>
            </ul>
          </nav>
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/table" element={<Table/>} />
            <Route path="/selectable" element={<Selectable/>} />
          </Routes>
        </div>
        
      </Router>
      
    );
  }
}


export default App;
