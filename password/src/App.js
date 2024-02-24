
import './App.css';
import {Navbar} from "./Components/Navbar";
import {Route, Routes} from "react-router-dom";
import {Login} from "./Components/Pages/Login";
import {Password} from "./Components/Pages/Password";
import {Home} from "./Components/Pages/Home";
import {SignUp} from "./Components/Pages/SignUp";

function App() {
  return (
    <div >
<Navbar/>
        <Routes>
            <Route path="/" element={<Home/>}/>

            <Route path="/SignUp" element={<SignUp/>}/>
            <Route path="/Login" element={<Login/>}/>
            <Route path="/password" element={<Password/>}/>
        </Routes>
    </div>
  );
}

export default App;
