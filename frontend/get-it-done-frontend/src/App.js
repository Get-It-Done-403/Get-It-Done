import HomePage from "./pages/HomePage";
import CalendarPage from "./pages/CalendarPage";
import Header from "./components/Header";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
function App() {
  return (
      <Router>
          <div>
            <Header/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/calendar" element={<CalendarPage/>}/>
            </Routes>
          </div>
      </Router>
  );
}

export default App;
