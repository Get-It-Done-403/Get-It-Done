import CalendarPage from "./pages/CalendarPage";
import Header from "./components/Header";
import AddTask from "./components/AddTask";
// import Login from "./pages/Login";
// import Register from "./pages/Register";
// import Reset from "./pages/Reset";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePageFlow from "./pages/homePage/HomePageFlow";

function App() {
  return (
      <Router>
          <div>
            <Header/>
            <Routes>
                <Route path="/" element={<HomePageFlow/>}/>
                <Route path="/calendar" element={<CalendarPage/>}/>
                <Route path="/settings" element={<AddTask/>}/>
            </Routes>
          </div>
      </Router>
  );
}

export default App;
