import React from "react";
import user_icon from "../../Assets/Assets/person.png";
import password_icon from "../../Assets/Assets/password.png";
import './Password.css'
export const Password = () => {
    return (
        <div className="password">
            <div className='Show-password'>
                <div className='inputs'>
                <div className="input">
                    <img src={user_icon} alt=""/>
                    <input type="text" placeholder="Name"/>
                </div>
                <div className="input">
                    <img src={password_icon} alt=""/>
                    <input type="password" placeholder="Password"/>
                </div>
                </div>
                    <div className="details-container">
                <div><button className='details'>Show Details</button></div>
                    </div>


            </div>
        </div>
    );
};
