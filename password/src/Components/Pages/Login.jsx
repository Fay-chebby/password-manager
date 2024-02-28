import './Login.css'
export const Login = () => {
    return(

        <div className='sign'>
            <fieldset>
                <form action="" target="_blank">
                <h1>Login </h1>
                <label htmlFor="name"><h3 >Username:</h3></label>
                <input type="text" placeholder="Enter your name"/><br/>
                <label htmlFor="password">
                    <h3 >Password:</h3>
                </label>
                <input type="text" placeholder="Enter your password"/><br/><br/>
                <button type="submit">Login</button>
                </form>
            </fieldset>
        </div>
    )
}
