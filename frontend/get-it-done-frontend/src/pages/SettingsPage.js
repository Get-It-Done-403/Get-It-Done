import "../css/mainCSS.css";
import React, {useEffect} from "react";
import NavBar from "../components/NavBar";

function SettingsPage(props) {

    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"settings"}/>
            </div>
        </div>
    );
}

export default SettingsPage;
