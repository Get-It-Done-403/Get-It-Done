import React, { useEffect, useState } from "react";
import { auth } from "../../firebase";
import { useAuthState } from "react-firebase-hooks/auth";

const AuthDetails = () => {
    const [authUser, setAuthUser] = useState(null);
    const [user, loading, error] = useAuthState(auth);

    useEffect(() => {
        if (user) {
            setAuthUser(user);
        } else {
            setAuthUser(null);
        }
    }, [user]);

    const userSignOut = () => {
        auth.signOut().then(() => {
            console.log("sign out successful");
        }).catch(error => console.log(error));
    }

    return (
        <div>{authUser ? <div> Signed in as {authUser.email} <button onClick={userSignOut}>Sign Out</button></div> : <div> signed out </div>}</div>
    );
};

export default AuthDetails;
