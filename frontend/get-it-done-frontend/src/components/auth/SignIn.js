import React, {useEffect, useState} from "react";
import { auth, provider } from "../../firebase";
import { useSignInWithEmailAndPassword, useSignInWithGoogle } from "react-firebase-hooks/auth";
import {Link} from "react-router-dom";

const SignIn = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [signInWithEmailAndPassword, user, error] = useSignInWithEmailAndPassword(auth);
    const [signInWithGoogle] = useSignInWithGoogle(auth, provider);

    const handleSignIn = (e) => {
        e.preventDefault();
        signInWithEmailAndPassword(email, password)
            .then((userCredentials) => {
                console.log(userCredentials);
                window.location = '/'
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const handleGoogleSignIn = (e) => {
        signInWithGoogle()
            .then((userCredentials) => {
                console.log(userCredentials)
                setEmail(userCredentials.user.email)
                // localStorage.setItem("email", userCredentials.user.email)
                window.location = '/'
            })
            .catch((error) => {
                console.log(error)
            })
    }
    //
    // useEffect(() => {
    //     setEmail(localStorage.getItem('email'))
    // })

    return (
        <div className={"pageBackground"}>
            <div className={"logoHeader"}> Get It Done </div>
            <div className={"module"}>
                <div className={"bg-[#CCCCCC] flex flex-1 flex-col pl-10 pr-10 pt-10 pb-5 rounded-md"}>
                    <div className={"text-[60px] text-center"}> Sign In </div>
                    <form onSubmit={handleSignIn} className={"flex flex-1 flex-col mt-6"}>
                        <input
                            className={"p-2 border-2 border-black text-black"}
                            type={"email"}
                            placeholder={"Email"}
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <input
                            className={"p-2 border-2 border-black mt-4 text-black"}
                            type={"password"}
                            placeholder={"Password"}
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <button  className={"bg-[#63789F] mt-10 rounded-[100px] text-[20px] p-3"} type={"submit"}> SIGN IN </button>
                    </form>
                    {/*<Link to={"/"} className={"mb-2 text-[#14509F]"}> Forgot Password? </Link>*/}
                    <button  className={"bg-[#63789F] rounded-[100px] w-[250px] text-[20px] p-3 mb-2"} onClick={handleGoogleSignIn}> SIGN IN WTH GOOGLE </button>

                    <div className={"flex ml-2"}> Don't have an account? <Link to={"/"} className={"ml-4 text-[#14509F] font-medium"}> Sign Up</Link> </div>

                </div>
            </div>




            <style jsx> {`
                .header {
                    background-color: rgba(225, 225, 225, 16);
                    display: flex;
                    color: white;
                    font-family: "Light";
                }
                .logoHeader {
                    padding: 5px;
                    display: flex;
                    flex: .1;
                    margin-top: 50px;
                    min-width: 300px;
                    max-width: 300px;
                    background-color: rgb(99, 120, 159);
                    justify-content: center;
                    align-items: center;
                    align-self: center;
                    font-size: 24px;
                    border-radius: 6px;
                }`}
            </style>
        </div>
    );
};

export default SignIn;
