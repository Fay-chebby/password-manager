import React, { useState } from "react";
import './Home.css'
import laptopPic from "../../Assets/pics/pc.jpg"
import phonePic from "../../Assets/pics/phone.jpg"
import passPic from "../../Assets/pics/pass.jpg"

export const Home = () => {
    const [selected, setSelected] = useState(null);

    const toggle = (i) => {
        if (selected === i) {
            return setSelected(null);
        }
        setSelected(i);
    };

    const data = [
        {
            question: 'Why Password Manager',
            answer: 'Password managers generate strong, unique passwords for each of your accounts and store them in an encrypted vault.'
        },
        {
            question: 'When to use Password Manager',
            answer: 'Difficulty in remembering passwords, if you struggle to remember multiple complex passwords for different accounts, a password manager can help by securely storing and autofilling your login credentials.'
        },
        {
            question: 'Advantage of Password Manager',
            answer: 'To improve security and convenience and create a cross-Platform Accessibility'
        }
    ];

    return (
        <div className="container">
            <div className="pass">
            <div className="password">
                <div className="word">
                <div className="words">
                    <div className="man">
                    <span>Password</span><br/>
                    <span>Manager </span><br/>
                    <span>For all accounts</span><br/>
                    </div>
                    <p className="par">Most things revolve on online activities, </p>
                    <p className="par">Password manager puts your life at your tips,simple and secure</p>
                    <button className="btn">Sign up</button>
                </div>
                </div>
                <div className="pic">
                    <div className="phone-image">
                        <img className="phone-images"      src={phonePic} alt="Password" width="600px" height="600px"/>
                    </div>



                </div>
            </div>
                <div className="pics">
                    <div>
                        <div className="password-image">
                            <img src={laptopPic} alt="Password" width="600px" height="600px"/>
                        </div>
                        <p className="para2">💯 safety</p>
                    </div>
                    <div>
                        <div className="password-images">
                            <img src={passPic} alt="Password" width="600px" height="600px"/>
                        </div>
                        <p className="para2">Trust</p>
                    </div>

                </div>
            </div>
            <div className="wrapper">
                <div className="accordian">
                    {data.map((item, i) => (
                        <div className="item" key={i}>
                            <div className="title" onClick={() => toggle(i)}>
                                <h2>{item.question}</h2>
                                <span>{selected === i ? '<' : '>'}</span>
                            </div>
                            <div className={selected === i ? 'content show' : 'content'}>{item.answer}</div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};
