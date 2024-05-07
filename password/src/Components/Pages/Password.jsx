import React, { useState, useEffect } from "react";

import "./Password.css";
import DataTable from "react-data-table-component";
import axios from "axios";

export const Password = () => {
    const columns = [
        {
            name: "Date",
            selector: (row) => row.date,
            sortable: true,
        },
        {
            name: "Note",
            selector: (row) => row.note,
            sortable: true,
        },
        {
            name: "Email",
            selector: (row) => row.email,
            sortable: true,
        },
        {
            name: "Account",
            selector: (row) => row.account,
            sortable: true,
        },
        {
            name: "UserName",
            selector: (row) => row.userName,
            sortable: true,
        },
        {
            name: "Password",
            selector: (row) => row.password,
            sortable: true,
        },
    ];

    const [records, setRecords] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        fetchRecords();
    }, []);

    const fetchRecords = () => {
        // Fetch records from the backend
        axios.get('/api/passwords')
            .then(response => {
                setRecords(response.data);
            })
            .catch(error => {
                console.error('Error fetching records:', error);
            });
    };

    const handleFilter = (event) => {
        setSearchTerm(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData(event.target);
        const newRecord = {
            date: formData.get("date"),
            note: formData.get("note"),
            email: formData.get("email"),
            account: formData.get("account"),
            userName: formData.get("userName"),
            password: formData.get("password"),
        };

        // Send new record to the backend
        axios.post('/api/passwords', newRecord)
            .then(response => {
                setRecords([...records, response.data]);
                event.target.reset();
            })
            .catch(error => {
                console.error('Error adding record:', error);
            });
    };

    return (
        <div>
            <div className="container">
                <div className="sign__container">
                    <fieldset>
                        <form onSubmit={handleSubmit}>
                            <h1>Add Password</h1>
                            <label htmlFor="date">
                                <h3>Date:</h3>
                            </label>
                            <input type="datetime-local" name="date" placeholder="Date" required/>
                            <br/>
                            <label htmlFor="note">
                                <h3>Note:</h3>
                            </label>
                            <input type="text" name="note" placeholder="Note" required/>
                            <br/>
                            <label htmlFor="email">
                                <h3>Email:</h3>
                            </label>
                            <input type="email" name="email" placeholder="Enter Email" required/>
                            <br/>
                            <label htmlFor="account">
                                <h3>Account:</h3>
                            </label>
                            <input type="text" name="account" placeholder="Enter your account" required/>
                            <br/>
                            <label htmlFor="userName">
                                <h3>UserName:</h3>
                            </label>
                            <input type="text" name="userName" placeholder="userName" required/>
                            <br/>
                            <label htmlFor="password">
                                <h3>Password:</h3>
                            </label>

                            <input type="password" name="password" placeholder="Enter Password" required/>
                            <br/>
                            <br/>
                            <button type="submit">Add</button>
                        </form>
                    </fieldset>
                </div>
            </div>
            <div className="container mt-5">
                <div className="text-end">
                    <input type="text" onChange={handleFilter} placeholder="Search"/>
                </div>
                <DataTable columns={columns} data={records.filter(record =>
                    record.note.toLowerCase().includes(searchTerm.toLowerCase()) ||
                    record.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
                    record.account.toLowerCase().includes(searchTerm.toLowerCase()) ||
                    record.userName.toLowerCase().includes(searchTerm.toLowerCase())
                )} selectableRows fixedHeader pagination />
            </div>
        </div>
    );
};
