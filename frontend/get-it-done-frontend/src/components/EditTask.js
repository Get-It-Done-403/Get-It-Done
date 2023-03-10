import "../css/mainCSS.css";

function EditTask(props) {

    const todayDate = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000) //local Date
    const dueDate = new Date(props.currentTask.dueDate.substring(0,16));
    const curr = new Date(dueDate.getTime() - new Date().getTimezoneOffset() * 60000) //local Date
    let hours = curr.getUTCHours();
    let minutes = curr.getUTCMinutes() < 10 ? '0'+ curr.getUTCMinutes()  : curr.getUTCMinutes() ;
    hours = (hours < 10) ? '0' + hours.toString() : hours.toString();
    minutes = minutes < 10 ? '0'+ minutes : minutes;
    const  strTime = hours + ':' + minutes;
    const date = curr.toISOString().substring(0,11).concat(strTime);

    const getTimeZone = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000) //local Date
    const timeZone = getTimeZone.toTimeString().split("-")[1].substring(0,4)
    const withColons = "-" + timeZone.substring(0,2) + ":" + timeZone.substring(2,4)

    const deleteTask = (event) => {
        event.preventDefault()
        const userID = props.userID;
        const taskID = props.currentTask.tid
        fetch('http://localhost:8080/deleteTask?uid=' + userID + "&tid=" + taskID, {
            method: 'DELETE'
        })
        .then(() => {props.setTrigger("default")})
        .catch((error) => console.error(error));
    }

    const editTask = (event) => {

        event.preventDefault();
        let sendTask = props.currentTask;
        sendTask.title = event.target.title.value
        sendTask.isCompleted = document.querySelector('input[name="isCompleted"]:checked').value
        sendTask.hoursToComplete = event.target.hoursToComplete.value
        sendTask.dueDate = (event.target.dueDate.value).concat(":00").concat(withColons)
        sendTask.description = event.target.description.value

        fetch('http://localhost:8080/updateTask?uid=' + props.userID,  {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(sendTask),

        })
        .then(response => response.text())
        .then((response) => {
            alert(response)
        })
        .then(() => {props.setTrigger("default")})
        .catch((error) => console.error(error));
        // window.location.reload(false);
    }

    return (
        <div className={"popup-first"}>
            <div className={"popup-inner"}>
                <form className={"calendarContainer"} onSubmit={editTask}>
                    <input type="text" className={"text-[36px] border-b mb-[10px] p-2"} required id={"title"} defaultValue={props.currentTask.title}/>
                    <div className={""}>
                        <input type="datetime-local" className={"ml-[12px]"} id={"dueDate"} min={todayDate.toISOString().substring(0,16)} defaultValue={date}/>
                        <div className={"radio-toolbar"}>
                            <div className={"ml-[12px] mt-[12px]"}>
                                <input type="radio" id={"true"} name={"isCompleted"} defaultValue="true" required defaultChecked={props.currentTask.isCompleted}/>
                                <label htmlFor={"true"}>Completed</label>
                                <input type="radio" id={"false"} name={"isCompleted"} defaultValue="false" required defaultChecked={props.currentTask.isCompleted === false}/>
                                <label htmlFor={"false"}>Not Completed</label>
                                <button className={"ml-[400px] self-end justify-self-end w-20 bg-[#D9D9D9] self-right pt-2 pb-2 pl-3 pr-3 mr-2"} onClick={deleteTask}> Delete  </button>
                            </div>
                        </div>
                    </div>


                    {/*<div className={"radio-toolbar"}>*/}
                    {/*    <div className={"ml-[12px] mt-[5px]"}>*/}
                    {/*        <input type="radio" id={"None"} name={"radioButton"} defaultValue="true" defaultChecked={true}/>*/}
                    {/*        <label htmlFor={"None"}>None</label>*/}
                    {/*        <input type="radio" id={"Daily"} name={"radioButton"} defaultValue="false"/>*/}
                    {/*        <label htmlFor={"Daily"}>Daily</label>*/}
                    {/*        <input type="radio" id={"Weekly"} name={"radioButton"} defaultValue="true" />*/}
                    {/*        <label htmlFor={"Weekly"}>Weekly</label>*/}
                    {/*        <input type="radio" id={"Monthly"} name={"radioButton"} defaultValue="false"/>*/}
                    {/*        <label htmlFor={"Monthly"}>Monthly</label>*/}
                    {/*    </div>*/}
                    {/*</div>*/}

                    <input className={"border rounded-[3px] w-[450px] mt-[12px] ml-[12px] p-2"} type={"number"} id={"hoursToComplete"} required defaultValue={props.currentTask.hoursToComplete} min={"1"} max={"500"}/>
                    <textarea className={"border rounded-[3px]  mt-[12px] h-[100px] ml-[12px] p-2"} type={"text"} id={"description"} placeholder={"Enter Description"} defaultValue={props.currentTask.description}/>
                    <div className={"flex-1 flex mt-[12px]"}>
                        <button className={"ml-[12px] w-20 bg-[#D9D9D9] self-end pt-2 pb-2 pl-3 pr-3"} type={"submit"}> Save </button>
                        <button className={"ml-[12px] w-20 bg-[#D9D9D9] self-end pt-2 pb-2 pl-3 pr-3"} onClick={() => props.setTrigger("default")}> Cancel  </button>
                    </div>
                </form>

            </div>

            <style jsx> {`
                .calendarContainer {
                    flex: .95;
                    font-family: Text;
                    color: black;
                    // background-color: black;
                    flex-direction: column;
                    display: flex;
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

export default EditTask;
