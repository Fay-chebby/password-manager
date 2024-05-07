import React, { useState } from "react";
import passwordImage from "../../Assets/pics/password.jpg";
import "./Password.css";
import DataTable from "react-data-table-component";

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
            name: "Password",
            selector: (row) => row.password,
            sortable: true,
        },
    ];

    const [records, setRecords] = useState([]);

    const handleFilter = (event) => {

        const newData = records.filter((row) => {
            return row.constructor.toLowerCase().includes(event.target.value.toLowerCase());
        });
        setRecords(newData);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData(event.target);
        const newRecord = {
            date: formData.get("date"),
            note: formData.get("note"),
            email: formData.get("email"),
            password: formData.get("password"),
        };

        setRecords([...records, newRecord]);

        event.target.reset();
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
                            <input type="account" name="account" placeholder="Enter your account" required/>
                            <br/>
                            <label htmlFor="userName">
                                <h3>UseName:</h3>
                            </label>
                            <input type="userName" name="UserName" placeholder="userName" required/>
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
                <DataTable columns={columns} data={records} selectableRows fixedHeader pagination />
            </div>
        </div>
    );
};
