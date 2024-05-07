import React, { Component } from 'react';
import './SignUp.css';
import UserService from '../../Services/UserService';

class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            password: '',
            confirmPassword: '',
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
        const { name, email, password, confirmPassword } = this.state;
        // Ensure password and confirm password match
        if (password !== confirmPassword) {
            this.setState({ error: 'Passwords do not match' });
            return;
        }
        // Send registration data to the backend
        UserService.signup(name, email, password)
            .then((response) => {
                // Handle successful registration
                console.log(response.data); // You might want to do something with the response
                // Redirect or do something else upon successful registration
            })
            .catch((error) => {
                // Handle registration failure
                console.error('Registration failed:', error);
                this.setState({ error: 'Registration failed. Please try again.' });
            });
    };

    render() {
        const { name, email, password, confirmPassword, error } = this.state;
        return (
            <div className='sign__container'>
                <fieldset>
                    <form onSubmit={this.handleSubmit}>
                        <h1>Password Registration</h1>
                        <label htmlFor='name'>
                            <h3>Name:</h3>
                        </label>
                        <input
                            type='text'
                            name='name'
                            value={name}
                            onChange={this.handleChange}
                            placeholder='Enter your name'
                            required
                        />
                        <br /><br />
                        <label htmlFor='email'>
                            <h3>Email:</h3>
                        </label>
                        <input
                            type='email'
                            name='email'
                            value={email}
                            onChange={this.handleChange}
                            placeholder='Enter your Email'
                            required
                        />
                        <br /><br />
                        <label htmlFor='password'>
                            <h3>Password:</h3>
                        </label>
                        <input
                            type='password'
                            name='password'
                            value={password}
                            onChange={this.handleChange}
                            placeholder='Enter your password'
                            required
                        />
                        <br /><br />
                        <label htmlFor='confirmPassword'>
                            <h3>Confirm Password:</h3>
                        </label>
                        <input
                            type='password'
                            name='confirmPassword'
                            value={confirmPassword}
                            onChange={this.handleChange}
                            placeholder='Confirm password'
                            required
                        />
                        <br /><br />
                        {error && <p className='error'>{error}</p>}
                        <br />
                        <button type='submit'>Register</button>
                    </form>
                </fieldset>
            </div>
        );
    }
}

export default SignUp;
