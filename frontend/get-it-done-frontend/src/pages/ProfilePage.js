import "../css/mainCSS.css";
import React, {useEffect} from "react";
import NavBar from "../components/NavBar";
import {Link} from "react-router-dom";
import {useState} from "react";

function ProfilePage(props) {
    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"profile"}/>
                <div className={"tasksPage"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535] grid grid-cols-2"}>
                        Welcome, {props.userEmail}
                        <button className={"bg-blue-100 h-full w-1/4 rounded-md justify-self-end"} onClick={() => props.signOut()}> SIGN OUT </button>
                    </h1>
                </div>
            </div>
        </div>
    );
}

export default ProfilePage;
