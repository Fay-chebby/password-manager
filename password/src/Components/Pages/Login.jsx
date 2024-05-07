import React, { Component } from 'react';
import './Login.css';
import UserService from '../../Services/UserService';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            error: ''
        };
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
            error: ''
        });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        const { username, password } = this.state;
        // Send login to backend
        UserService.login(username, password)
            .then((response) => {
                // Handle successful login
                console.log(response.data);

            })
            .catch((error) => {
                // Handle login failure
                console.error('Login failed:', error);
                this.setState({ error: 'Invalid username or password' });
            });
    };

    render() {
        const { username, password, error } = this.state;
        return (
            <div className='sign'>
                <fieldset>
                    <form onSubmit={this.handleSubmit}>
                        <h1>Login</h1>
                        <label htmlFor='username'>
                            <h3>Username:</h3>
                        </label>
                        <input
                            type='text'
                            name='username'
                            value={username}
                            onChange={this.handleChange}
                            placeholder='Enter your name'
                        />
                        <br />
                        <label htmlFor='password'>
                            <h3>Password:</h3>
                        </label>
                        <input
                            type='password'
                            name='password'
                            value={password}
                            onChange={this.handleChange}
                            placeholder='Enter your password'
                        />
                        <br />
                        {error && <p className='error'>{error}</p>}
                        <br />
                        <button type='submit'>Login</button>
                    </form>
                </fieldset>
            </div>
        );
    }
}

export default Login;
