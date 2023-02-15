import React, { useState } from "react";
import { auth } from "../../firebase";
import { useCreateUserWithEmailAndPassword } from "react-firebase-hooks/auth";
import "../../css/mainCSS.css"
import {Link} from "react-router-dom";
import {Redirect} from "react-router-dom";

const SignUp = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [createUserWithEmailAndPassword, user, error] = useCreateUserWithEmailAndPassword(auth);

    const handleSignUp = (e) => {
        e.preventDefault();
        createUserWithEmailAndPassword(email, password)
            .then((userCredentials) => {
                console.log(userCredentials);
                window.location = '/'
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className={"pageBackground"}>
            <div className={"logoHeader"}> Get It Done </div>
            <div className={"module"}>
                <div className={"bg-[#CCCCCC] flex flex-1 flex-col pl-10 pr-10 pt-10 pb-5 rounded-md"}>
                    <div className={"text-[60px] text-center"}> Sign Up </div>
                        <form onSubmit={handleSignUp} className={"flex flex-1 flex-col mt-6"}>
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
                            <button  className={"bg-[#63789F] mt-10 rounded-[100px] text-[20px] p-3"} type={"submit"}> SIGN UP </button>
                        </form>
                    <div className={"flex"}> Already have an account? <Link to={"signin"} className={"ml-4 text-[#14509F] font-medium"}> Sign In</Link> </div>
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

export default SignUp;
