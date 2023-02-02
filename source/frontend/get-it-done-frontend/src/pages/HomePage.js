import "../css/mainCSS.css";
import {Link} from "react-router-dom";
import NavBar from "../components/NavBar";


function HomePage() {
    return (
        <div className={"pageBackground"}>
            <div className={"pageContainer"}>
                <NavBar currentPage={"home"}/>

                <div className={"tasksPage"}>
                    <h1 className={"text-[36px] ml-[12px] border-b-2 border-[#353535]"}> Today's Tasks </h1>
                    <div className={"tasksContainer"}>
                        <div className={"p-3 rounded-[7px] mr-8 bg-[#FF3434] bg-opacity-95 text-white text-[28px] flex flex-[.5] list-item flex-col"}>
                            Remaining Tasks
                                {/* Turn this to component */}
                            <li className={"bg-[#BABABA] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}> Create UI for 403 </li>
                            <li className={"bg-[#BABABA] p-3 bg-opacity-40 rounded-[7px] text-[16px] mt-3 mb-3"}> Get Groceries </li>
                            <div className={"bg-[#F2F2F2] text-center rounded-[7px] p-3 text-[16px] text-[#251B1B]"}> Create new Task </div>

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
