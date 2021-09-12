import React, { Component } from 'react'
import Header from './Layout/Header';
import Projectitem from './Project/Projectitem';

class Dashboard extends Component {
    render() {
        return (
            <div>
            <Header/>
            <h1>Welcome to Dashboard !!</h1>
            <Projectitem/>
            <Projectitem/>
            <Projectitem/>
            </div>
        )
    }
}

export default Dashboard;