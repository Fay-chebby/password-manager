
import './App.css';
import {Navbar} from "./Components/Navbar";
import {Route, Routes} from "react-router-dom";
import {SignUp} from "./Components/Pages/SignUp";
import {Password} from "./Components/Pages/Password";
import {Home} from "./Components/Pages/Home";

function App() {
  return (
    <div >
<Navbar/>
        <Routes>
            <Route path="/" element={<Home/>}/>

            <Route path="/signup" element={<SignUp/>}/>
            <Route path="/password" element={<Password/>}/>
        </Routes>
    </div>
  );
}

export default App;
