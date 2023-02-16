import CalendarPage from "./pages/CalendarPage";
import Header from "./components/Header";
import AddTask from "./components/AddTask";
// import Login from "./pages/Login";
// import Register from "./pages/Register";
// import Reset from "./pages/Reset";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePageFlow from "./pages/homePage/HomePageFlow";
// import Signup from "./pages/Signup";
// import { Container } from 'react-bootstrap'
// import {AuthProvider} from "./pages/Contexts/AuthContext";
import SignIn from "./components/auth/SignIn";
import SignUp from "./components/auth/SignUp";
import AuthDetails from "./components/auth/AuthDetails";
import React, {useEffect, useState} from "react";
import {useAuthState} from "react-firebase-hooks/auth";
import {auth} from "./firebase";
import ProfilePage from "./pages/ProfilePage";
import ForgotPassword from "./components/auth/ForgotPassword";
import SettingsPage from "./pages/SettingsPage";

function App() {
    const [authUser, setAuthUser] = useState(null);
    const [user, loading, error] = useAuthState(auth);
    const [username, setUsername] = useState("");

    useEffect(() => {
        if (user) {
            setAuthUser(user);

        } else {
            setAuthUser(null);
        }

    }, [user]);




    const userSignOut = () => {
        auth.signOut().then(() => {
            window.location = '/'
            console.log("sign out successful");
        }).catch(error => console.log(error));
    }


    return (
                authUser ?
                    <Router>
                        <Header userEmail={authUser.email}/>
                        <Routes>
                            <Route path="/" element={<HomePageFlow userEmail={authUser.email} username={username}/>}/>
                            <Route path="/calendar" element={<CalendarPage/>}/>
                            {/*<Route path="/settings" element={<AddTask/>}/>*/}
                            <Route path="/profile" element={<ProfilePage userEmail={authUser.email} signOut={userSignOut}/>}/>
                            {/*<Route path="/signin" element={<SignIn/>}/>*/}
                            {/*<Route path="/signup" element={<SignUp/>}/>*/}
                            <Route path="/settings" element={<SettingsPage/>}/>

                        </Routes>
                </Router> :
                    <Router>
                        <Routes>
                            <Route path={"/"} element={<SignIn/>}/>
                            <Route path={"/signup"} element={<SignUp/>}/>
                            <Route path={"/forgotpassword"} element={<ForgotPassword/>}/>
                        </Routes>
                    </Router>
    );
}

export default App;
