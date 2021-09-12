import React, { Component } from 'react'
import Header from './Layout/Header';
import Projectitem from './Project/Projectitem';

class Dashboard extends Component {
    render() {
        return (
            <div>
            <h1 className = "alert alert-warning">Welcome to Dashboard !!</h1>
            <Projectitem/>
            <Projectitem/>
            <Projectitem/>
            </div>
        )
    }
}

export default Dashboard;