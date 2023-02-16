import HomePage from "./HomePage";
import {useState} from "react";
import AddTask from "../../components/AddTask";
import EditTask from "../../components/EditTask";
import {useEffect} from "react";

function HomePageFlow(props)  {



    const [trigger, setTrigger] = useState("default")
    const [currentTask, setCurrentTask] = useState({})
    const userID = props.userEmail

    // useEffect(() => {
    //     fetch("http://localhost:8080/getUserName?uid=" + userID)
    //         .then(res=>res.json())
    //         .then((result)=>{
    //                 setUsername(result);
    //             }
    //         )
    // }, [userID]);


    return (
        {
            default: (
            <>
                <HomePage setTrigger={setTrigger} setCurrentTask={setCurrentTask} userID={userID} trigger={trigger}/>
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
