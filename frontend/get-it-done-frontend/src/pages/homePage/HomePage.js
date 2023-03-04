import "../../css/mainCSS.css";
import React, {useEffect, useMemo} from "react";
import NavBar from "../../components/NavBar";
import {useState} from "react";
import DisplayTask from "../../components/DisplayTask";

function HomePage(props) {
    const monthNames = useMemo(() => [  {monthName: "January", days: 31},  {monthName: "February", days: 28},  {monthName: "March", days: 31},  {monthName: "April", days: 30},  {monthName: "May", days: 30},  {monthName: "June", days: 30},  {monthName: "July", days: 31},  {monthName: "August", days: 31},  {monthName: "September", days: 30},  {monthName: "October", days: 31},  {monthName: "November", days: 30},  {monthName: "December", days: 31}], []);
    const [currentMonthName, setCurrentMonthName] = useState(monthNames[new Date().getMonth()].monthName);
    const [currentYear, setCurrentYear] = useState(new Date().getFullYear())

    const [todayDate, setTodayDate] = useState(new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000))
    const [actualDate, setActualDate] = useState(todayDate.getUTCDate())
    const [tasks, setTasks] = useState([]);

    const [searchTask, setSearchTask] = useState("")

    function backDate() {
        setTodayDate(new Date(todayDate.getTime() - (60000 * 60 * 24)))
    }

    function nextDate() {
        setTodayDate(new Date(todayDate.getTime() + (60000 * 60 * 24)))
    }
    function editTask(currentTask) {
        props.setCurrentTask(currentTask)
        props.setTrigger("editTask")
    }

    useEffect(() => {
        let date = todayDate.getUTCDate()
        setCurrentMonthName(monthNames[todayDate.getMonth()].monthName)
        setCurrentYear(todayDate.getFullYear())
        setActualDate(date)
    }, [todayDate, monthNames])

    useEffect(() => {
        fetch("http://localhost:8080/getTaskList?uid=" + props.userID)
            .then(res=>res.json())
            .then((result)=>{
                setTasks(result);
            }


    )}, [props.trigger, props.userID])

    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"home"}/>
                 <div className={"tasksPage"}>
                     <div className={"flex flex-row align-center justify-center justify-self-center"}>
                         <button className={"border-2 rounded-[10000px] p-3"} onClick={backDate}> Back </button>
                         <h1 className={"text-[36px] flex-1 ml-[12px] border-b-2 border-[#353535] text-center"}> {currentMonthName} {actualDate} {currentYear}</h1>
                         <button className={"border-2 rounded-[10000px] p-3"} onClick={nextDate}> Next </button>
                     </div>
                     <div className={"tasksContainer"}>
                         <div className={"p-3 rounded-[7px] mr-8 bg-[#FF3434] bg-opacity-95 text-white text-[28px] flex flex-[.5] list-item flex-col text-center"}>
                             Remaining Tasks
                             <DisplayTask searchTask={searchTask} tasks={tasks} todayDate={todayDate} completedBool={'false'} editTask={editTask} todayBool={'true'} overdueBool={'false'}/>
                             <button data-testid="createTask" className={"w-full bg-[#F2F2F2] mt-3 text-center rounded-[7px] p-3 text-[16px] text-[#251B1B]"} onClick={() => {props.setTrigger("addTask")}}> Create new Task </button>
                         </div>
                         <div className={"p-3 bg-[#15CD32] rounded-[7px] bg-opacity-95 text-white text-[28px] flex flex-[.5] display-flex flex-col text-center"}>
                            Completed Tasks
                             <DisplayTask searchTask={searchTask} tasks={tasks} todayDate={todayDate} completedBool={'true'} editTask={editTask} todayBool={'true'} overdueBool={'false'}/>
                        </div>
                    </div>
                </div>
                <div className={"upcomingTasks"}>
                    <input type={"text"} className={"mt-2 text-center border-b-[1px] w-full border-black bg-[#EFEFEF]"} placeholder={"Search for Tasks"} onChange={(e) => setSearchTask(e.target.value)}/>
                    <div className={"flex-1 flex-col flex w-full p-5"}>
                        <div className={"text-[20px] mt-[20px] text-center border-b-2 border-[#353535] "}> Upcoming Tasks </div>
                        <div className={"text-[12px]"}>
                            <DisplayTask searchTask={searchTask} tasks={tasks} todayDate={todayDate} completedBool={'false'} editTask={editTask} todayBool={'false'} overdueBool={'false'}/>
                        </div>
                    </div>

                    <div className={"mb-[54px] flex-1 flex-col flex w-full p-5"}>
                        <div className={"text-[20px] text-center border-b-2 border-[#353535] "}> Overdue Tasks </div>
                        <DisplayTask searchTask={searchTask} tasks={tasks} todayDate={todayDate} completedBool={'false'} editTask={editTask} todayBool={'false'} overdueBool={'true'}/>
                    </div>

                </div>
            </div>
        </div>
    );
}

export default HomePage;
