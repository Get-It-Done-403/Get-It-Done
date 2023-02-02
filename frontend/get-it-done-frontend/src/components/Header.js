import {Link} from "react-router-dom";


function Header() {
    return (
        <div className={"header"}>
            <div className={"logoHeader"}> Get It Done </div>
            <div className={"ml-auto mr-12 self-center text-[#181818] text-[28px]"}>  Profile </div>
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
                    flex: .05;
                    /*flex-shrink: 0;*/
                    min-width: 150px;
                    max-width: 150px;
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
