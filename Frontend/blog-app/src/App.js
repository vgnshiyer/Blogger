import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import About from './pages/About';
import { UserDashboard } from './pages/user-routes/UserDashboard';
import { Profile } from './pages/user-routes/Profile'
import { PrivateRoute } from './components/PrivateRoute';

function App() {
  return (
    <BrowserRouter>
    <ToastContainer position='top-center' />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/user" element={<PrivateRoute />}>
        <Route path="dashboard" element={<UserDashboard />} />
        <Route path="profile" element={<Profile />} />
      </Route>
    </Routes>
    </BrowserRouter>
  );
}

export default App;
