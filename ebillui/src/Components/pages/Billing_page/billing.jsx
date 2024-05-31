import axios from 'axios';
import React, { useEffect, useState } from 'react';
import './billing.css';

function BillingPage() {
    const [formData, setFormData] = useState({
        customer_name: '',
        contact_number: '',
        email: '',
        payment_mode: 'cash',
        product_id: '',
        product_name: '',
        quantity: '',
        price: ''
    });

    const [products, setProducts] = useState([]);
    const [billingItems, setBillingItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);
    const [showSuccessPopup, setShowSuccessPopup] = useState(false); // State for success popup

    useEffect(() => {
        fetchProductNames();
    }, []);

    const fetchProductNames = async () => {
        try {
            const response = await axios.get('http://localhost:4143/api/customers/getProduct');
            const data = response.data;
            setProducts(data);
        } catch (error) {
            console.error('Error fetching product names:', error);
        }
    };

   const handleChange = async (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
        if (name === 'product_name') {
            try {
                const response = await axios.get(`http://localhost:4143/api/customers/getProductDetails?productName=${value}`);
                const { id, price } = response.data;
                setFormData({
                    ...formData,
                    product_id: id,
                    price: price,
                    selectedProductName: value, // Update selectedProductName on product name change
                });
            } catch (error) {
                console.error('Error fetching product details:', error);
            }
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const total = parseFloat(formData.quantity) * parseFloat(formData.price);
        const newItem = { ...formData, total: total.toFixed(2) };

        setBillingItems([...billingItems, newItem]);
        setTotalPrice(totalPrice + total);

        clearProductDetails();
    };

    const removeItem = (index) => {
        const itemToRemove = billingItems[index];
        const updatedBillingItems = billingItems.filter((item, i) => i !== index);
        setBillingItems(updatedBillingItems);
        setTotalPrice(totalPrice - parseFloat(itemToRemove.total));
    };

    const clearProductDetails = () => {
        setFormData({
            ...formData,
            product_id: '',
            quantity: '',
            price: ''
        });
    };

    const getProductMap = () => {
        const productMap = {};
        billingItems.forEach(item => {
            const quantity = parseInt(item.quantity); // Parse quantity as integer
            if (productMap[item.selectedProductName]) {
                // If product already exists in map, update its quantity
                productMap[item.selectedProductName] += quantity;
            } else {
                // If product doesn't exist, add it to the map
                productMap[item.selectedProductName] = quantity;
            }
        });
        return productMap;
    };

    const printBill = async () => {
        try {
            const response = await axios.post('http://localhost:4143/api/customers/addBillAndSendProductList', {
                formData: {
                    customer_name: formData.customer_name,
                    contact_number: formData.contact_number,
                    email: formData.email,
                    payment_mode: formData.payment_mode,
                    // Add other fields from formData as needed
                },
                productMap: getProductMap(),
                totalPrice: totalPrice
            }, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.data === 'Bill and product list processed successfully') {
                console.log("Bill printed successfully!");
                setShowSuccessPopup(true); // Show success popup
            } else {
                throw new Error('Failed to print bill');
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred. Please try again later.");
        }
    };

    // Function to close the popup and reload the page
    const closePopup = () => {
        setShowSuccessPopup(false);
        window.location.reload(); // Reload the page when the popup is closed
    };

    return (
        <div className="container">
            <h2>Billing Information</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <div className="input-group">
                        <label htmlFor="customer_name">Customer Name:</label>
                        <input type="text" id="customer_name" name="customer_name" value={formData.customer_name} onChange={handleChange} required />
                    </div>
                    <div className="input-group">
                        <label htmlFor="contact_number">Contact Number:</label>
                        <input type="text" id="contact_number" name="contact_number" value={formData.contact_number} onChange={handleChange} required />
                    </div>
                    <div className="input-group">
                        <label htmlFor="email">Email:</label>
                        <input type="text" id="email" name="email" value={formData.email} onChange={handleChange} required />
                    </div>
                    <div className="input-group">
                        <label htmlFor="payment_mode">Payment Mode:</label>
                        <select id="payment_mode" name="payment_mode" value={formData.payment_mode} onChange={handleChange}>
                            <option value="cash">Cash</option>
                            <option value="credit_card">Credit Card</option>
                            <option value="debit_card">Debit Card</option>
                            <option value="UPI">UPI</option>
                        </select>
                    </div>
                    <div className="input-group">
                        <label htmlFor="product_name">Product Name:</label>
                        <select id="product_name" name="product_name" value={formData.selectedProductName} onChange={handleChange} required>
                            <option value="">Select a product</option>
                            {products && products.map((product, index) => (
                                <option key={index} value={product}>{product}</option>
                            ))}
                        </select>
                    </div>
                    <div className="input-group">
                        <label htmlFor="quantity">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" min="1" value={formData.quantity} onChange={handleChange} required />
                    </div>
                    <div className="input-group">
                        <label htmlFor="product_id">Product Id:</label>
                        <input type="number" id="product_id" name="product_id" min="1" value={formData.product_id} onChange={handleChange} />
                    </div>
                    <div className="input-group">
                        <label htmlFor="price">Price:</label>
                        <input type="number" id="price" name="price" min="0.01" step="0.01" value={formData.price} onChange={handleChange} required />
                    </div>
                </div>
                <div className="input-group input-group-submit">
                    <input type="submit" value="Add Item" />
                </div>
            </form>
            <h2>Billing Table</h2>
            <table>
                <thead>
                    <tr>
                        <th>Sno</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th className="actions">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {billingItems.map((item, index) => (
                        <tr key={index}>
                            <td>{index + 1}</td> {/* Serial number */}
                            <td>{item.product_id}</td>
                            <td>{item.selectedProductName}</td>
                            <td>{item.quantity}</td>
                            <td>&#8377; {item.price}</td>
                            <td>&#8377; {item.total}</td>
                            <td className="actions">
                               <button className="cancel-button" onClick={() => removeItem(index)}>Cancel</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
                <tfoot>
                    <tr className="total">
                        <td colSpan="5">Total</td>
                        <td>&#8377; {totalPrice.toFixed(2)}</td>
                        <td></td>
                    </tr>
                </tfoot>
            </table>
            <div className="print-bill-box" style={{ display: 'flex', justifyContent: 'flex-end' }}>
                <button className="print_bill_button" onClick={printBill}>Print Bill</button>
            </div>
            {/* Success popup */}
            {showSuccessPopup && (
                <div className="success-popup">
                    <p>Bill printed successfully!</p>
                    <button onClick={closePopup}>Close</button>
                </div>
            )}
        </div>
    );
}

export default BillingPage;
