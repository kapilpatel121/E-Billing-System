import axios from 'axios';
import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import Popup from '../Common/popup';
import './addProduct.css';
const AddProduct = () => {
    const [formData, setFormData] = useState({
        name: '',
        company_name: '',
        price: '',
        purchasePrice: '',
        mfd: new Date(),
        exp: new Date(),
        units: '',
        discount: '',
        gst: ''
    });

    const [showMfdCalendar, setShowMfdCalendar] = useState(false);
    const [showExpCalendar, setShowExpCalendar] = useState(false);
    const [showPopup, setShowPopup] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleMfdChange = (date) => {
        setFormData(prevState => ({
            ...prevState,
            mfd: date
        }));
        setShowMfdCalendar(false);
    };

    const handleExpChange = (date) => {
        setFormData(prevState => ({
            ...prevState,
            exp: date
        }));
        setShowExpCalendar(false);
    };

    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    const handleClose = () => {
        setShowPopup(false);
        // Reload the page
        window.location.reload();
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:4143/rwi/ebill/api/addProduct', formData);
            console.log('Product added:', response.data);
            setShowPopup(true);
            setFormData({
                name: '',
                company_name: '',
                price: '',
                purchasePrice: '',
                mfd: new Date(),
                exp: new Date(),
                units: '',
                discount: '',
                gst: ''
            });
        } catch (error) {
            console.error('Error adding product:', error);
        }
    };

    return (


        <div className="container">

            <h2>Add Product</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">Product Name:</label>
                    <input type="text" id="name" name="name" value={formData.name} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label htmlFor="company_name">Company name:</label>
                    <input type="text" id="company_name" name="company_name" value={formData.company_name} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="price">Sell Price:</label>
                    <input type="number" id="price" name="price" min="0.01" step="0.01" value={formData.price} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label htmlFor="purchasePrice">Purchas Price:</label>
                    <input type="number" id="purchasePrice" name="purchasePrice" min="0.01" step="0.01" value={formData.purchasePrice} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label htmlFor="mfd">Manufacturing Date:</label>
                    <div className="calendar-wrapper">
                        <input type="text" id="mfd" name="mfd" value={formData.mfd.toLocaleDateString()} onFocus={() => setShowMfdCalendar(true)} readOnly />
                        {showMfdCalendar && <Calendar value={formData.mfd} onChange={handleMfdChange} />}
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="exp">Expiry Date:</label>
                    <div className="calendar-wrapper">
                        <input type="text" id="exp" name="exp" value={formData.exp.toLocaleDateString()} onFocus={() => setShowExpCalendar(true)} readOnly />
                        {showExpCalendar && <Calendar value={formData.exp} onChange={handleExpChange} />}
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="units">Units:</label>
                    <input type="number" id="units" name="units" min="1" value={formData.units} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label htmlFor="discount">Discount:</label>
                    <input type="number" id="discount" name="discount" min="0" value={formData.discount} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label htmlFor="gst">GST:</label>
                    <input type="number" id="gst" name="gst" min="0" step="0.01" value={formData.gst} onChange={handleChange} required />
                </div>
                <input type="submit" value="Add Product" className="add-product-btn" />
            </form>
            <Popup show={showPopup} handleClose={togglePopup} message={
                <div>
                    <p>Product Added Successfully</p>
                    <button onClick={handleClose}>Close</button>
                </div>
            } />
            {/* {showPopup && (
                <div className="popup-overlay">
                    <div className="popup">
                        <h3>Product Added Successfully!</h3>
                        <button className="close-btn" onClick={() => setShowPopup(false)}>Close</button>
                    </div>
                </div>
            )} */}
        </div>
    );
}

export default AddProduct;
