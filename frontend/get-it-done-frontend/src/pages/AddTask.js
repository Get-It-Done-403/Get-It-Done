import "../css/mainCSS.css";
import NavBar from "../components/NavBar";


function AddTask() {
    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"calendar"}/>

                <div className={"calendarContainer"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535] mb-[10px]"}> Enter Name of Task </h1>
                    <div className={"ml-[12px]"}>
                        January 13th, 2023
                    </div>

                    <form>
                        <div className={"radio-toolbar"}>
                            <div className={"ml-[12px] mt-[5px]"}>
                                <input type="radio" id={"None"} name={"radioButton"} defaultValue="true" />
                                <label htmlFor={"None"}>X</label>
                                <input type="radio" id={"Daily"} name={"radioButton"} defaultValue="false" defaultChecked/>
                                <label htmlFor={"Daily"}>Daily</label>
                                <input type="radio" id={"Weekly"} name={"radioButton"} defaultValue="true" />
                                <label htmlFor={"Weekly"}>Weekly</label>
                                <input type="radio" id={"Monthly"} name={"radioButton"} defaultValue="false" defaultChecked/>
                                <label htmlFor={"Monthly"}>Monthly</label>
                            </div>
                        </div>

                    </form>
                </div>
            </div>

            <style jsx> {`
                .calendarContainer {
                    flex: .95;
                    font-family: Text;
                    color: black;
                    padding: 30px;
                }
                .allDaysBox {
                    display: grid; 
                    grid-template-columns: repeat(7, minmax(0, 1fr)); 
                }
                .dayNameBox {
                    text-align: center;
                    padding-left: 6px;
                    padding-top: 2px;
                    padding-right: 6px;
                    padding-bottom: 2px;
                    border-color: black;
                    border-left: 2px solid;
                    border-top: 2px solid;
                    border-bottom: 2px solid;
                    
                }
                .saturdayBox {
                    text-align: center;
                    padding-left: 6px;
                    padding-top: 2px;
                    padding-right: 6px;
                    padding-bottom: 2px;
                    border-color: black;
                    border-left: 2px solid;
                    border-top: 2px solid;
                    border-bottom: 2px solid;
                    border-right: 2px solid;
                }
                .singleDayBox {
                    padding: 20px;
                    border-color: black;
                    border-left: 2px solid;
                    border-bottom: 2px solid;
                }
                .radio-toolbar input[type="radio"] {
                    opacity: 0;
                    position: fixed;
                    width: 0;
                }

                .radio-toolbar label {
                    display: inline-block;
                    background-color: #D9D9D9;
                    padding: 10px 20px;
                    font-size: 16px;
                    margin-right: 20px;
                }
                
                .radio-toolbar input[type="radio"]:checked + label {
                    background-color: #A9C9C9;
                }
                
                `}

            </style>
        </div>
    );
}

export default AddTask;
