import {Link} from "react-router-dom";
import {useState} from "react";


function NavBar({currentPage}) {
    const homeButtonCSS = currentPage === "home" ?
        "flex flex-col justify-center text-center display-flex w-[150px] " +
        "bg-opacity-50 h-[70px] bg-[#31FFFF]"
        : "mt-4 mb-5"
    const calendarButtonCSS = currentPage === "calendar" ?
        "flex flex-col justify-center text-center display-flex w-[150px] " +
        "bg-opacity-50 h-[70px] bg-[#31FFFF]"
        : "mt-4 mb-5"
    const settingsButtonCSS = currentPage === "settings" ?
        "flex flex-col justify-center text-center display-flex w-[150px] " +
        "bg-opacity-50 h-[70px] bg-[#31FFFF]"
        : "mt-4 mb-5"

    return (
        <div className={"navBar"}>
            <Link to={"/"}>
                <div className={homeButtonCSS}> Home </div>
            </Link>
            <Link to={"/calendar"}>
                <div className={calendarButtonCSS}> Calendar </div>
            </Link>
            <Link to={"/settings"}>
                <div className={settingsButtonCSS}> Settings </div>
            </Link>

            <style jsx> {`
                .navBar {
                    display: flex;
                    /*padding: 5px;*/
                    flex: .05;
                    background-color: #647DAC;
                    flex-direction: column;
                    font-size: 24px;
                    font-family: Text;
                    /*justify-content: center;*/
                    align-items: center;
                    min-width: 150px;
                    max-width: 150px;
                }`}
            </style>
        </div>

    );
}

export default NavBar;
