import React from "react";
import {Link, NavLink} from "react-router-dom";
import './Navbar.css'

export const Navbar=()=>{
    return(
       <nav className='nav'>
           <NavLink  to="/" className='nav-log'>Home</NavLink >
           <ul className='nav-menu'>
               <li>
                   <NavLink to="/SignUp">sign up</NavLink>
               </li>
               <li className='nav-password'>  <NavLink to="/Password">Passwords </NavLink> </li>

           </ul>
       </nav>
    )
}