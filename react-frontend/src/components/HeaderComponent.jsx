import React, { Component } from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            employees: []

        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                        <div className="navbar-brand">Employee Application</div>
                    </nav>
                </header>
                
            </div>
        );
    }
}

export default HeaderComponent;