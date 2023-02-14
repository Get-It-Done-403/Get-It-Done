import HomePage from "./HomePage";
import {useState} from "react";
import AddTask from "../../components/AddTask";
import EditTask from "../../components/EditTask";
import {useEffect} from "react";

function HomePageFlow()  {
    const [trigger, setTrigger] = useState("default")
    const [currentTask, setCurrentTask] = useState({})
    const userID = "aidan"


    return (
        {
            default: (
            <>
                <HomePage setTrigger={setTrigger} setCurrentTask={setCurrentTask} userID={userID}/>
            </>
            ),
            addTask: (
                <>
                    <AddTask setTrigger={setTrigger} userID={userID}/>
                </>
            ),
            editTask: (
                <>
                    <EditTask setTrigger={setTrigger} currentTask={currentTask} userID={userID}/>
                </>
            )
        }[trigger] || <> BROKEN </>
    );
}

export default HomePageFlow
