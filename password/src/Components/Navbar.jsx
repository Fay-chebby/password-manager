import React from "react";
import {NavLink} from "react-router-dom";
import './Navbar.css'

export const Navbar=()=>{
    return(
       <nav className='nav'>
           <NavLink  to="/" className='nav-log'>Home</NavLink >
           <ul className='nav-menu'>
               <li>
                   <NavLink to="/SignUp">SignUp</NavLink>
               </li>
               <li>
                   <NavLink to="/Login">Login</NavLink>
               </li>
               <li className='nav-password'><NavLink to="/Password">Passwords </NavLink></li>

           </ul>
       </nav>
    )
}