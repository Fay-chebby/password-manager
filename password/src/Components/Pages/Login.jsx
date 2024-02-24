import './Login.css'
export const Login = () => {
    return(
        <div className='sign'>
            <fieldset>
                <h1>Login </h1>
                <label htmlFor="name"><h3 >Username:</h3></label>
                <input type="text" value="Enter your name"/><br/>
                <label htmlFor="password">
                    <h3 >Password:</h3>
                </label>
                <input type="text" value="Enter your password"/><br/><br/>
                <button type="submit">Login</button>

            </fieldset>
        </div>
    )
}
