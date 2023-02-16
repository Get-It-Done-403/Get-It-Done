import "../css/mainCSS.css";
import React from "react";

function DisplayTask(props) {

    return (
        <div>
            {props.tasks && props.tasks.map(singleTask => (
                (singleTask.isCompleted).toString() === props.completedBool && (singleTask.dueDate.substring(0,10) === props.todayDate.toISOString().substring(0,10)).toString() === props.todayBool
                && (new Date(props.todayDate.toISOString().substring(0,10)) > new Date(singleTask.dueDate.substring(0,10))).toString() === props.overdueBool ?
                props.searchTask === "" ?

                <div key={singleTask.tid}>
                    <button className={"w-full text-left"} onClick={() => props.editTask(singleTask)}>
                        <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                            {singleTask.title}
                        </li>
                    </button>
                </div>
                : singleTask.title.toLowerCase().includes(props.searchTask.toLowerCase()) ?

                <div key={singleTask.tid}>
                    <button className={"w-full text-left"} onClick={() => props.editTask(singleTask)}>
                        <li className={"bg-[#FFFFFF] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}>
                            {singleTask.title}
                        </li>
                    </button>
                </div>: null
                :null
                ))}
        </div>
    )
}

export default DisplayTask;
