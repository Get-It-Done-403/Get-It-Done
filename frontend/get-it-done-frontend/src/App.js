import CalendarPage from "./pages/CalendarPage";
import Header from "./components/Header";
import AddTask from "./components/AddTask";
// import Login from "./pages/Login";
// import Register from "./pages/Register";
// import Reset from "./pages/Reset";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePageFlow from "./pages/homePage/HomePageFlow";
import Signup from "./pages/Signup";
import { Container } from 'react-bootstrap'
import {AuthProvider} from "./pages/Contexts/AuthContext";


function App() {
    return (
        <AuthProvider>
            <Container className="d-flex align-items-center justify-content-center"
                       style={{ minHeight: "100vh" }}
            >
                <Router>

                        <Header/>
                        <Routes>
                            <Route path="/" element={<HomePageFlow/>}/>
                            <Route path="/calendar" element={<CalendarPage/>}/>
                            <Route path="/settings" element={<AddTask/>}/>
                            <Route path="/signup" element={<Signup/>}/>
                        </Routes>

                </Router>
            </Container>
        </AuthProvider>
    );
}

export default App;