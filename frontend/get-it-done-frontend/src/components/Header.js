import {useEffect} from "react";
import {useState} from "react";
import React from "react";

function Header(props) {
    const [username, setUsername] = useState(props.userEmail)

    const handleSubmit = (newUsername) => {
        fetch("http://localhost:8080/createUserName?uid=" + props.userEmail + "&userName=" + newUsername, {
            method: "POST",
        })
            .then((response) => response.json())
            .then((data) => console.log(data))
            .catch((error) => console.error(error));
        // window.location.reload(false)
    };

    useEffect(() => {
        fetch(`http://localhost:8080/getUserName?uid=${props.userEmail}`)
            .then(response => response.text())
            .then(data => {
                setUsername(data.split("=")[1].split("}")[0])
            })
            .catch(error => console.error(error));

    }, [props.userEmail]);
    return (
        <div className={"header"}>
            <img className={"SignInLogo"} src={require("./auth/GetItDone.png")} height={70} width={310} alt="Logo"/>

            <div className={"text-center text-[36px] flex justify-center ml-[250px]"}>
                Welcome,
                <form onSubmit={() => handleSubmit(username)}>
                    <input type={"text"} placeholder={" Change Username"} className={"ml-2 bg-[#63789F] text-white placeholder-white"} value={username}
                       onChange={(e) => setUsername(e.target.value)} />
                    {/*<button onClick={() => handleSubmit(username)} className={"bg-[#5bc0de] rounded-[10000px] w-[40px] p-2 ml-2"}> Set </button>*/}
                </form>
            </div>
            <button className={"bg-[#5bc0de] h-full text-[28px]  justify-self-end w-[175px]"} onClick={() => props.signOut()}> Sign Out </button>
            {/*<div className={"text-center text-[#181818] text-[28px]"}>*/}
            {/*    <Link to={"/profile"}>*/}
            {/*        {username}*/}
            {/*    </Link>*/}
            {/*</div>*/}

            <style jsx> {`
                .header {
                    background-color: rgb(99, 120, 159);
                    display: grid;
                    grid-template-columns: repeat(3, 1fr);
                    color: white;
                    height: 70px;
                    font-family: "Light";
                    align-items: center;
                    padding: 5px;
                }
                .logoHeader {
                    padding: 5px;
                    display: flex;
                    flex: .12;
                    /*flex-shrink: 0;*/
                    // min-width: 150px;
                    // max-width: 150px;
                    background-color: rgb(99, 120, 159);
                    justify-content: center;
                    align-items: center;
                    font-size: 24px;
                }`}
            </style>
        </div>


    );
}

export default Header;
