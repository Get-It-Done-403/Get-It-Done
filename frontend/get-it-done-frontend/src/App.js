import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignIn from "./components/auth/SignIn";
import SignUp from "./components/auth/SignUp";
import React, {useEffect, useState} from "react";
import {useAuthState} from "react-firebase-hooks/auth";
import {auth} from "./firebase";
import ForgotPassword from "./components/auth/ForgotPassword";
import HomePage from "./pages/HomePage";

function App() {
    const [authUser, setAuthUser] = useState(null);
    const [user, loading] = useAuthState(auth);

    useEffect(() => {
        if (user) {
            setAuthUser(user);
        } else {
            setAuthUser(null)
        }

    }, [user]);

    const userSignOut = () => {
        auth.signOut().then(() => {
            window.location = '/'
            console.log("sign out successful");
        }).catch(error => console.log(error));
    }

    if (loading) {
        return (
            <div className="loading-spinner">
                <style jsx> {`
                .loading-spinner {
                  border: 6px solid rgba(0, 0, 0, 0.1);
                  border-left-color: #3498db;
                  border-radius: 50%;
                  width: 150px;
                  height: 150px;
                  animation: spin 1s linear infinite;
                  margin: auto;
                  position: absolute;
                  top: 0;
                  left: 0;
                  bottom: 0;
                  right: 0;
                }
                
                @keyframes spin {
                  to { transform: rotate(360deg); }
                }
                                }`}
                </style></div>
                // <div className="loading-spinner"> hi </div>
        )
    } else {
        return (
            authUser ?
                <HomePage signOut={userSignOut} userID={authUser.email}/>
                // <Router>
                //     <Routes>
                //         <Route path="/" element={<HomePageFlow signOut={userSignOut} userEmail={authUser.email}/>}/>
                //         {/*<Route path="/calendar" element={<CalendarPage userID={authUser.email}/>}/>*/}
                //         <Route path="/profile"
                //                element={<ProfilePage userEmail={authUser.email} signOut={userSignOut}/>}/>
                //         {/*<Route path="/settings" element={<SettingsPage/>}/>*/}
                //     </Routes>
                // </Router>
            :
                <Router>
                    <Routes>
                        <Route path={"/"} element={<SignIn/>}/>
                        <Route path={"/signup"} element={<SignUp/>}/>
                        <Route path={"/forgotpassword"} element={<ForgotPassword/>}/>
                    </Routes>
                </Router>
        );
    }
}

export default App;
