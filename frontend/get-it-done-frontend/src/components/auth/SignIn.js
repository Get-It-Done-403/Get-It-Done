import React, {useState} from "react";
import { auth, provider } from "../../firebase";
import { useSignInWithEmailAndPassword, useSignInWithGoogle } from "react-firebase-hooks/auth";
import {Link} from "react-router-dom";

const SignIn = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [signInWithEmailAndPassword] = useSignInWithEmailAndPassword(auth);
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
                // setEmail(userCredentials.user.email)
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
        <div className={"signInBackground"}>
            <img className={"SignInLogo"} src={require("./GetItDone.png")} alt="Logo"/>
            <div className={"module"}>
                <div className={"bg-[#F1F8FF] flex flex-1 flex-col pl-10 pr-10 pt-10 pb-5 rounded-md"}>
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
                        <button  className={"bg-[#90B1EE] mt-10 rounded-[100px] text-[20px] p-3"} type={"submit"}> SIGN IN </button>
                    </form>
                    {/*<Link to={"/"} className={"mb-2 text-[#14509F]"}> Forgot Password? </Link>*/}
                    <div className={"flex ml-2 justify-center"}>
                        <button  className={"bg-[#90B1EE] rounded-[100px] w-[200px] text-[20px] p-1 mb-2 mr-2"} onClick={handleGoogleSignIn}> SIGN IN WTH GOOGLE </button>
                        <button  className={"bg-[#90B1EE] rounded-[100px]  w-[200px] text-[20px] p-1 mb-2"} onClick={() => window.location = '/forgotpassword'}> Forgot Password </button>
                    </div>
                    <div className={"flex ml-2 justify-center"}>
                        Don't have an account?
                        <Link to={"/signup"} className={"ml-4 text-[#14509F] font-medium"}> Sign Up</Link>
                    </div>

                </div>
            </div>




            <style jsx> {`
                
                .header {
                    background-color: rgba(225, 225, 225, 16);
                    display: flex;
                    color: white;
                    font-family: "Light";
                }
                .signInBackground{
                    background-color: rgba(100, 125, 172, 100);
                    font-family: "Light", "Serif";
                    min-height: 100vh;
                    display: flex;
                    flex-direction: column;
                    flex: 1;
                }
                .SignInLogo {
                    padding: 5px;
                    display: flex;
                    flex: .1;
                    margin-top: 50px;
                    width: 500px;
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

export default SignIn;
