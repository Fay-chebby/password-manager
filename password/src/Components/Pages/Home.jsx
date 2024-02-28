import React, { useState } from "react";
import './Home.css'
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
        <div>
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
