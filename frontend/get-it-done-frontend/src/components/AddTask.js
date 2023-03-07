import "../css/mainCSS.css";
import NavBar from "./NavBar";
import { v4 as uuid } from "uuid";

function AddTask(props) {
    const uid = uuid();
    const date = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000) //local Date
    const timeZone = date.toTimeString().split("-")[1].substring(0,4)
    const withColons = "-" + timeZone.substring(0,2) + ":" + timeZone.substring(2,4)
    // let test = date.toISOString().substring(0,16).concat(":00").concat(withColons)
    // test.concat(withColons)


    // Creates new task in database
    const handleSubmit = async (event) => {
        event.preventDefault()
        const task = {
            "hoursToComplete": event.target.hoursToComplete.value,
            "isCompleted": false,
            "tid": uid,
            "title": event.target.taskName.value,
            "dueDate": (event.target.dueDate.value).concat(":00").concat(withColons),
            "description": event.target.description.value
        }

        await fetch("http://localhost:8080/createTask?uid=" + props.userID, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(task),

        })
            .then(response => response.text())
            .then((response) => {
                alert(response)
                // alert("hi")
            })
            // .then(response => {
            //     return response.json();
            // })
            // .then(props.setTrigger("default"))
            .then(() => {props.setTrigger("default")})
            // .then(window.location.reload(false))
            .catch((error) => console.error(error));
        // const test = await json.response();
        // window.location.reload()
        // alert(bool.text() + "  OUTSIDE fejsdfkjl")
    };


    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"None"}/>
                <form className={"calendarContainer"} onSubmit={handleSubmit}>
                    <input type="text" className={"text-[36px] ml-[12px] border-b-2 border-[#353535] mb-[10px]"} required id={"taskName"} placeholder={"Enter Name of Task "}/>
                    <input type="datetime-local" className={"ml-[12px]"} id={"dueDate"} min={date.toISOString().substring(0,16)} defaultValue={date.toISOString().substring(0,16)} required/>
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
                    <input className={"border-2 border-black rounded-[3px] w-[450px] mt-[12px] ml-[12px] p-2"} type={"number"} id={"hoursToComplete"} required placeholder={"Enter Time Commitment in Hours"} min={"0"} max={"500"}/>
                    <input className={"border-2 border-black rounded-[3px] w-[450px] mt-[12px] ml-[12px] p-2"} type={"text"} id={"description"} placeholder={"Enter Description"}/>
                    <div className={"flex-1 flex"}>
                    <button className={"ml-[12px] w-20 bg-[#D9D9D9] self-end pt-2 pb-2 pl-3 pr-3"} type={"submit"}> Save </button>
                    <button className={"ml-[12px] w-20 bg-[#D9D9D9] self-end pt-2 pb-2 pl-3 pr-3"} onClick={(() => {props.setTrigger("default")})}> Cancel  </button>
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
