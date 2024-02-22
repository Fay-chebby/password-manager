import React from "react";
import {Link} from "react-router-dom";
import './Navbar.css'

export const Navbar=()=>{
    return(
       <nav className='nav'>
           <Link to="/" className='nav-log'>Home</Link>
           <ul className='nav-menu'>
               <li>
                   <Link to="/SignUp">sign up</Link>
               </li>
               <li className='nav-password'>  <Link to="/passwords">Passwords </Link> </li>

           </ul>
       </nav>
    )
}