import axios from 'axios';
import React, { useEffect, useState } from 'react';
import './Viewbills.css'; // Import your custom CSS file

const ViewBills = () => {
  const [bills, setBills] = useState([]);

  useEffect(() => {
    fetchBills();
  }, []);

  const fetchBills = async () => {
    try {
      const response = await axios.get('http://localhost:4143/api/customers/getAllBills');
      setBills(response.data);
    } catch (error) {
      console.error('Error fetching bills:', error);
    }
  };

  const formatTotalAmount = (totalAmount) => {
    if (totalAmount === null || totalAmount === undefined) {
      return 0; // or any other appropriate placeholder
    }
    return totalAmount.toFixed(2);
  };
  

  const openPdf = async (pdfPath) => {
    try {
      const value = pdfPath;
      // Send a request to the API with the pdfPath
      const response = await axios.get(`http://localhost:4143/api/customers/openPdf?pdfPath=${value}`);
      console.log(response.data);
    } catch (error) {
      console.error('Error opening PDF:', error);
    }
  };

  return (
    <div className="container">
      <h1 className="mt-4 mb-4">View Bills</h1>
      <div className="table-responsive"> {/* Wrap the table inside a div with class "table-responsive" */}
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>ID</th>
              <th>Customer Name</th>
              <th>Contact Number</th>
              <th>Email</th>
              <th>Payment Mode</th>
              <th>Total Amount</th>
              <th>Purchase Time</th>
              <th>No of Products</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {bills.map((bill, index) => (
              <tr key={index}>
                <td>{bill.id}</td>
                <td>{bill.customerName}</td>
                <td>+91 {bill.contactNumber}</td>
                <td>{bill.email}</td>
                <td>{bill.paymentMode}</td>
                <td>&#8377; {formatTotalAmount(bill.totalAmount)}</td>
                <td>{bill.purchaseTime}</td>
                {/* <td>{new Date(bill.purchaseTime).toLocaleString()}</td> */}
                <td>{bill.noOfProduct}</td>
                <td>
                  <button className="btn btn-primary" onClick={() => { openPdf(bill.pdfPath); }}>Open PDF</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ViewBills;
