import React, { useState, useEffect } from 'react';
import './Home.css';
import purchaseImage from '../Components/images/purchase.png';
import customerImage from '../Components/images/customer.png';
import sellsImage from '../Components/images/sells.png';
import profitImage from '../Components/images/profit.png';

function Dashboard() {
    const [data, setData] = useState({
        totalPurchases: '0',
        totalCustomers: '0',
        totalSales: '0',
        totalProfit: '0'
    });
    

    useEffect(() => {
        // Fetch data from API when component mounts
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            // Make API call to fetch data
            const response = await fetch('http://localhost:4143/api/customers/getDashboardInfo');
            const dashboardData = await response.json();

            // Update state with fetched data
            setData({
                totalPurchases: `${dashboardData.totalPurchases}`,
                totalCustomers: dashboardData.totalCustomers.toString(),
                totalSales: `${dashboardData.totalSales.toFixed(2)}`,
                totalProfit: `${dashboardData.totalProfit}`
            });
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div className="dashboard">
            <div className="box-container">
                <div className="box">
                    <img src={purchaseImage} alt="Total Purchases" />
                    <h2>Total Purchases</h2>
                    <p>&#8377; {data.totalPurchases}</p>
                </div>
                <div className="box">
                    <img src={customerImage} alt="Total Number of Customers" />
                    <h2>Total Number of Customers</h2>
                    <p>{data.totalCustomers}</p>
                </div>
            </div>
            <div className="box-container">
                <div className="box">
                    <img src={sellsImage} alt="Total Sales" />
                    <h2>Total Sales</h2>
                    <p>&#8377; {data.totalSales}</p>
                </div>
                <div className="box">
                    <img src={profitImage} alt="Total Profit" />
                    <h2>Total Profit</h2>
                    <p>&#8377; {data.totalProfit}</p>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
