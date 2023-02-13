import "../css/mainCSS.css";
import React from "react";
import NavBar from "../components/NavBar";
import {useState} from "react";
import AddTask from "./AddTask";

function HomePage() {

    const [trigger, setTrigger] = useState(false);
    // let completedTasks = [{"name" : "one"}]
    const [remainingTasks, setRemainingTasks] = useState([]);
    function addTask() {
        // setRemainingTasks({"name": "test"});
        setTrigger(true)

        // setRemainingTasks(remainingTasks => [...remainingTasks, {"name" : "test"}]);
    }

    return trigger ? (<AddTask setTrigger={setTrigger} remainingTasks={remainingTasks} setRemainingTasks={setRemainingTasks}/>) : (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"home"}/>
                <div className={"tasksPage"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535]"}> Today's Tasks </h1>
                    <div className={"tasksContainer"}>
                        <div className={"p-3 rounded-[7px] mr-8 bg-[#FF3434] bg-opacity-95 text-white text-[28px] flex flex-[.5] list-item flex-col"}>
                            Remaining Tasks
                            {remainingTasks && remainingTasks.map(task => (
                                <div>
                                    <li className={"bg-[#BABABA] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                                        {task.name}
                                    </li>
                                </div>

                            ))}
                            <button className={"w-full bg-[#F2F2F2] mt-3 text-center rounded-[7px] p-3 text-[16px] text-[#251B1B]"} onClick={addTask}> Create new Task </button>

                        </div>
                        <div className={"p-3 bg-[#15CD32] rounded-[7px] bg-opacity-95 text-white text-[28px] flex flex-[.5] display-flex flex-col"}>
                            Completed Tasks
                            <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}> Capstone Interview </li>
                            {/*<div> Capstone Interview </div>*/}
                        </div>
                    </div>
                </div>
                <div className={"upcomingTasks"}>
                    <div className={"text-[20px] w-9/12 mt-[54px] border-b-2 border-[#353535] min-w-[150px] text-center"}> Upcoming Tasks </div>
                    <div className={"text-[12px]"}> Estimated Time: 4 Hours
                        <lu>
                            <li>Create UI for 403</li>
                            <li>Get Groceries</li>
                        </lu>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomePage;
