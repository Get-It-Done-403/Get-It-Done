import "../css/mainCSS.css";
import React, {useEffect} from "react";
import NavBar from "../components/NavBar";
import {useState} from "react";

function ProfilePage(props) {
    const [username, setUsername] = useState("")
    const [newUsername, setNewUsername] = useState("");

    const handleSubmit = (newUsername) => {
        fetch("http://localhost:8080/createUserName?uid=" + props.userEmail + "&userName=" + newUsername, {
            method: "POST",
        })
            .then((response) => response.json())
            .then((data) => console.log(data))
            .catch((error) => console.error(error));
        window.location.reload(false)
    };

    useEffect(() => {
        fetch(`http://localhost:8080/getUserName?uid=${props.userEmail}`)
            .then(response => response.text())
            .then(data => {
                setUsername(data.split("=")[1].split("}")[0])
            })
            .catch(error => console.error(error));

        // alert(username)

    }, [props.userEmail]);

    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"profile"}/>
                <div className={"tasksPage"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535] grid grid-cols-2"}>
                        Welcome, {username}
                        <button className={"bg-blue-100 h-full w-1/4 rounded-md justify-self-end w-[175px]"} onClick={() => props.signOut()}> SIGN OUT </button>
                    </h1>
                    <div className={""}>
                        <input type={"text"} placeholder={"Set New Username"} className={"border-2 mt-10 w-1/2 p-2"} value={newUsername}
                               onChange={(e) => setNewUsername(e.target.value)} />
                        <button onClick={() => handleSubmit(newUsername)} className={"bg-gray-100 p-2 ml-2"}> Set Username </button>
                    </div>


                </div>
            </div>
        </div>
    );
}

export default ProfilePage;
