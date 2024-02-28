import './SignUp.css'
export const SignUp = () => {
    return(
        <div class="sign__container">
            <fieldset>
                <form action="" target="_blank">
                    <h1>Password Registration </h1>
                    <label htmlFor="name"><h3>Name:</h3></label>
                    <input type="text" placeholder="Enter your name" required/><br/><br/>
                    <label htmlFor="email"><h3 >Email:</h3></label>
                    <input type="text" placeholder="Enter your Email" required/><br/><br/>


                    <label htmlFor="password"
                    ><h3 >Input your password:</h3></label
                    >
                    <input type="text" placeholder="Enter your password"/><br/><br/>
                    <label htmlFor="password">
                        <h3 >Confirm password:</h3></label
                    >
                    <input type="text" placeholder="Confirm password"/><br/><br/>


                    <button type="submit">Register</button>

                </form>
            </fieldset>
        </div>
    )
}