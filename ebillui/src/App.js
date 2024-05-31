import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './Components/Navbarr/navbar';
import AddProduct from './Components/pages/AddProduct/addProduct';
import Billing from './Components/pages/Billing_page/billing';
import ProductDetails from './Components/pages/P_Details/ProDetails';
import ViewBills from './Components/pages/ViewBills/Viewbills';
import Footer from './Components/footer/Footer';
import AboutUs from './Components/AboutUs/AboutUs';
import Home from './home/Home';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/addproduct" element={<AddProduct />} />
        <Route path="/billing" element={<Billing />} />
        <Route path="/viewbill" element={<ViewBills />} />
        <Route path="/productDetails" element={<ProductDetails />} />
        <Route path="/aboutUs" element={<AboutUs />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
