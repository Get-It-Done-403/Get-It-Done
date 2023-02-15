import "../../css/mainCSS.css";
import React, {useEffect} from "react";
import NavBar from "../../components/NavBar";
import {useState} from "react";

function HomePage(props) {
    // const dueDate = new Date(props.currentTask.dueDate);
    // const curr = new Date(dueDate.getTime() - new Date().getTimezoneOffset() * 60000) //local Date
    // let hours = curr.getUTCHours();
    // var minutes = curr.getUTCMinutes() < 10 ? '0'+ curr.getUTCMinutes()  : curr.getUTCMinutes() ;
    // hours = (hours < 10) ? '0' + hours.toString() : hours.toString();
    // minutes = minutes < 10 ? '0'+minutes : minutes;
    // var strTime = hours + ':' + minutes;
    // const date = curr.toISOString()
    //     .substring(0,11).concat(strTime);

    const todayDate = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000) //local Date
    const todayDateString = todayDate.toISOString().substring(0,10);


    const [tasks, setTasks] = useState([]);

    const [searchTask, setSearchTask] = useState("")

    function editTask(currentTask) {
        props.setCurrentTask(currentTask)
        props.setTrigger("editTask")
    }

    useEffect(() => {
        fetch("http://localhost:8080/getTaskList?uid=" + props.userID)
            .then(res=>res.json())
            .then((result)=>{
                setTasks(result);

                // setEstimatedTime(1000)
            }


    )}, [])


    useEffect(() => {

    },[])

    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"home"}/>
                 <div className={"tasksPage"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535]"}> Today's Tasks</h1>
                     <input type={"text"} className={" mt-[12px] text-center border-b-[1px] border-black"} placeholder={"Search for Tasks"} onChange={(e) => setSearchTask(e.target.value)}/>
                     <div className={"tasksContainer"}>
                        <div className={"p-3 rounded-[7px] mr-8 bg-[#FF3434] bg-opacity-95 text-white text-[28px] flex flex-[.5] list-item flex-col"}>
                            Remaining Tasks
                            {tasks && tasks.map(singleTask => (
                                !singleTask.isCompleted && singleTask.dueDate.substring(0,10) === todayDateString ?
                                    searchTask === "" ?

                                        <div key={singleTask.tid}>
                                            <button className={"w-full text-left"} onClick={() => editTask(singleTask)}>
                                                <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                                                    {singleTask.title}
                                                </li>
                                            </button>
                                        </div>
                                        : singleTask.title.includes(searchTask) ?

                                            <div key={singleTask.tid}>
                                                <button className={"w-full text-left"} onClick={() => editTask(singleTask)}>
                                                    <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                                                        {singleTask.title}
                                                    </li>
                                                </button>
                                            </div>: null
                                    :null
                            ))}
                            <button className={"w-full bg-[#F2F2F2] mt-3 text-center rounded-[7px] p-3 text-[16px] text-[#251B1B]"} onClick={() => {props.setTrigger("addTask")}}> Create new Task </button>

                        </div>
                        <div className={"p-3 bg-[#15CD32] rounded-[7px] bg-opacity-95 text-white text-[28px] flex flex-[.5] display-flex flex-col"}>
                            Completed Tasks

                            {tasks && tasks.map(singleTask => (
                                singleTask.isCompleted && singleTask.dueDate.substring(0,10) === todayDateString ?
                                    searchTask === "" ?

                                        <div key={singleTask.tid}>
                                            <button className={"w-full text-left"} onClick={() => editTask(singleTask)}>
                                                <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                                                    {singleTask.title}
                                                </li>
                                            </button>
                                        </div>
                                        : singleTask.title.includes(searchTask) ?

                                            <div key={singleTask.tid}>
                                                <button className={"w-full text-left"} onClick={() => editTask(singleTask)}>
                                                    <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                                                        {singleTask.title}
                                                    </li>
                                                </button>
                                            </div>: null
                                    : null
                            ))}
                        </div>
                    </div>
                </div>
                <div className={"upcomingTasks"}>
                    <div className={"text-[20px] w-9/12 mt-[54px] border-b-2 border-[#353535] min-w-[150px] text-center"}> Upcoming Tasks </div>
                    <div className={"text-[12px]"}>
                        {/*Estimated Time: {estimatedTime} Hours*/}
                        {tasks && tasks.map(singleTask => (
                            (!singleTask.isCompleted && singleTask.dueDate.substring(0,10) !== todayDateString ?
                                searchTask === "" ?
                                    <div className={"mt-2"}>
                                        {/*<button className={"w-full text-left"} onClick={() => editTask(singleTask)}>*/}
                                        <button className={'w-full text-left'}  onClick={() => editTask(singleTask)}>
                                            <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[12px] mt-3 mb-3"}>
                                                {singleTask.title}
                                            </li>
                                        </button>
                                        {/*</button>*/}
                                    </div>
                                    : singleTask.title.includes(searchTask) ? <div className={"mt-2"}>
                                        {/*<button className={"w-full text-left"} onClick={() => editTask(singleTask)}>*/}
                                        <button className={'w-full text-left'}  onClick={() => editTask(singleTask)}>
                                            <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[12px] mt-3 mb-3"}>
                                                {singleTask.title}
                                            </li>
                                        </button>
                                    </div> : null
                                : null)
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomePage;
