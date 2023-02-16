import React, { useState } from "react";
import {auth, provider} from "../../firebase";
import {useCreateUserWithEmailAndPassword, useSignInWithGoogle} from "react-firebase-hooks/auth";
import "../../css/mainCSS.css"
import {Link} from "react-router-dom";
import {Redirect} from "react-router-dom";

const SignUp = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [createUserWithEmailAndPassword, user, error] = useCreateUserWithEmailAndPassword(auth);
    const [signInWithGoogle] = useSignInWithGoogle(auth, provider);

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

    const handleGoogleSignIn = (e) => {
        signInWithGoogle()
            .then((userCredentials) => {
                console.log(userCredentials)
                // setEmail(userCredentials.user.email)
                // localStorage.setItem("email", userCredentials.user.email)
                window.location = '/'
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return (
        <div className={"signUpBackground"}>
            <img className={"SignUpLogo"} src={require("./GetItDone.png")} alt="Logo"/>
            <div className={"module"}>
                <div className={"bg-[#F1F8FF] flex flex-1 flex-col pl-10 pr-10 pt-10 pb-5 rounded-md"}>
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
                            <button  className={"bg-[#90B1EE] mt-10 rounded-[100px] text-[20px] p-3"} type={"submit"}> SIGN UP </button>
                        </form>
                    <button  className={"bg-[#90B1EE] rounded-[80px] w-[250px] text-[20px] p-3 mb-2"} onClick={handleGoogleSignIn}> SIGN UP WTH GOOGLE </button>
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
                .signUpBackground{
                    background-color: rgba(100, 125, 172, 100);
                    font-family: "Light", "Serif";
                    min-height: 100vh;
                    display: flex;
                    flex-direction: column;
                    flex: 1;
                }
                .SignUpLogo {
                    padding: 5px;
                    display: flex;
                    flex: .1;
                    margin-top: 50px;
                    min-width: 300px;
                    max-width: 500px;
                    max-height: 450px;
                    background-color: rgb(99, 120, 159);
                    justify-content: center;
                    align-items: center;
                    align-self: center;
                    font-size: 24px;
                    border-top-left-radius: 6px;
                    border-top-right-radius: 6px;
                }`}
            </style>
        </div>
    );
};

export default SignUp;
