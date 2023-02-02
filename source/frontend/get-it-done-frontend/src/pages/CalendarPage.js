import "../css/mainCSS.css";
import {Link} from "react-router-dom";
import NavBar from "../components/NavBar";


function CalendarPage() {
    const monthNames = [{monthName: "January", days: 31}, {monthName: "February", days: 28}, {monthName: "March", days: 31}, {monthName: "April", days: 30}, {monthName: "May", days: 30}, {monthName: "June", days: 30}, {monthName: "July", days: 31}, {monthName: "August", days: 31}, {monthName: "September", days: 30}, {monthName: "October", days: 31}, {monthName: "November", days: 30}, {monthName: "December", days: 31}];
    const testArray = Array(31).fill(0);
    const currentMonthName = monthNames[new Date().getMonth()].monthName;
    const days = monthNames[new Date().getMonth()].days;
    const currentYear = new Date().getFullYear();


    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"calendar"}/>

                <div className={"calendarContainer"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535] mb-[30px]"}> {currentMonthName} {currentYear} </h1>
                    <div className={"allDaysBox"}>
                        <div className={"dayNameBox"}>Sunday</div>
                        <div className={"dayNameBox"}>Monday</div>
                        <div className={"dayNameBox"}>Tuesday</div>
                        <div className={"dayNameBox"}>Wednesday</div>
                        <div className={"dayNameBox"}>Thursday</div>
                        <div className={"dayNameBox"}>Friday</div>
                        <div className={"saturdayBox"}>Saturday</div>
                    </div>

                    <div className={"grid-cols-7 grid-rows-5 grid border-r-2 border-black"}>
                        {testArray && testArray.map(item => (
                            <div className={"singleDayBox"}>
                                test
                            </div>
                        ))}
                    </div>
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
                }`}

            </style>
        </div>
    );
}

export default CalendarPage;
