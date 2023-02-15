import "../css/mainCSS.css";
import React, {useEffect} from "react";
import NavBar from "../components/NavBar";
import {useState} from "react";

function ProfilePage(props) {
    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"profile"}/>
                <div className={"tasksPage"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535]"}> Welcome, {props.userID} </h1>
                </div>
            </div>
        </div>
    );
}

export default ProfilePage;
