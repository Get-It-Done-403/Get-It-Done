import React, {useState} from "react";
import {Link} from "react-router-dom";
import {useSendPasswordResetEmail} from "react-firebase-hooks/auth";
import {auth} from "../../firebase";

const ForgotPassword = () => {
    const [email, setEmail] = useState("");
    const [sendPasswordResetEmail] = useSendPasswordResetEmail(auth);

    const forgotPassword = (e) => {
        e.preventDefault()
        sendPasswordResetEmail(email)
            .then(() => {
                alert("Password Reset Email Sent!");
                window.location = '/'
            })
            . catch((error) => {
                console.log(error)
            })
    }



    return (
        <div className={"signInBackground"}>
            <img className={"SignInLogo"} src={require("./GetItDone.png")} alt="Logo"/>
            <div className={"module"}>
                <div className={"bg-[#F1F8FF] flex flex-1 flex-col pl-10 pr-10 pt-10 pb-5 rounded-md"}>
                    <div className={"text-[50px] text-center"}> Forgot Password </div>
                    <form onSubmit={forgotPassword} className={"flex flex-1 flex-col mt-6"}>
                        <input
                            className={"p-2 border-2 border-black text-black"}
                            type={"email"}
                            placeholder={"Email"}
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <button  className={"bg-[#90B1EE] mt-10 rounded-[100px] text-[20px] p-3"} type={"submit"}> REQUEST RESET </button>
                    </form>
                    {/*<Link to={"/"} className={"mb-2 text-[#14509F]"}> Forgot Password? </Link>*/}
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
    )
}
export default ForgotPassword
