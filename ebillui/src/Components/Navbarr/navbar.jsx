import React, { useState, useEffect } from 'react';
import './navbar.css';
import { Link } from 'react-router-dom';
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { faChartLine, faMoneyBillWave, faShoppingCart, faUsers } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function EBillingSystem() {
    const [totalCustomers, setTotalCustomers] = useState(0);
    const [totalAmount, setTotalAmount] = useState(0);

    useEffect(() => {
        fetchData();
    }, []);
    const formatTotalAmount = (totalAmount) => {
        if (totalAmount === null || totalAmount === undefined) {
          return 0; // or any other appropriate placeholder
        }
        return totalAmount.toFixed(2);
      };
   const fetchData = async () => {
        try {
            const response = await fetch('http://localhost:4143/api/customers/getNavbarInfo');
            const data = await response.json();
            setTotalCustomers(data.todaysCustomerCount);
            setTotalAmount(data.todaysSell);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div className="wrapper">
            <header className="header">
                <h1 className="heading"> VKP E-Billing Service Indore(@Railworld India) </h1>
                <nav className="navbar">
                    <div className="store-info">
                       <ul className="store-stats">
                            <li>Todays Customer Count: <span id="total-customers">{totalCustomers}</span></li>
                            <li>Todays sell: &#8377; <span className="amount" id="total-amount"> {formatTotalAmount(totalAmount)}</span></li>
                        </ul>
                        
                    </div>
                    <div className="store-name">The Corner Market</div>
                    <div className="navigation-links">
                        <button className="my-button"><Link to="/">Home</Link></button>
                        <button className="my-button"><Link to="/billing">Billing</Link></button>
                        <button className="my-button"><Link to="/addproduct">Add Product</Link></button>
                        <button className="my-button"><Link to="/productDetails">Product Details</Link></button>
                        <button className="my-button"><Link to="/viewbill">View Bills</Link></button>
                        <button className="my-button"><Link to="/aboutUs">About us</Link></button>
                    </div>
                </nav>
            </header>
        </div>
    );
}

export default EBillingSystem;
