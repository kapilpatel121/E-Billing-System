import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Popup from '../Common/popup';
import './ProDetails.css';

function ProductDetails() {
    const [products, setProducts] = useState([]);
    const [showSuccessPopup, setShowSuccessPopup] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
    const [showEditPopup, setShowEditPopup] = useState(false);
    const [productToEdit, setProductToEdit] = useState({
      id: '',
      name: '',
      company_name: '',
      price: '',
      mfd: new Date().toISOString().split('T')[0],
      exp: new Date().toISOString().split('T')[0],
      units: '',
      discount: '',
      gst: ''
    });

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = () => {
        axios.get('http://localhost:4143/rwi/ebill/api/getAllProducts')
            .then(response => {
                const updatedProducts = response.data.map(product => ({
                    ...product,
                    mfd: new Date(product.mfd).toISOString().split('T')[0],
                    exp: new Date(product.exp).toISOString().split('T')[0]
                }));
                setProducts(updatedProducts);
            })
            .catch(error => {
                console.error('Error fetching product data:', error);
            });
    };

    const deleteProduct = (productId) => {
        axios.delete(`http://localhost:4143/rwi/ebill/api/deleteProduct/${productId}`)
            .then(response => {
                if (response.status === 200) {
                    // setShowSuccessPopup(true);
                    // setSuccessMessage('Product deleted successfully!');
                    fetchProducts();
                } else {
                    console.error('Failed to delete product.');
                }
            })
            .catch(error => {
                console.error('Error deleting product:', error);
            });
        setShowDeleteConfirmation(false);
    };

    const editProduct = (productId) => {
        const product = products.find(product => product.id === productId);
        setProductToEdit(product);
        setShowEditPopup(true);
    };

    const handleEditSubmit = (e) => {
        e.preventDefault();
        axios.post(`http://localhost:4143/rwi/ebill/api/updateProduct/${productToEdit.id}`, productToEdit)
            .then(response => {
                if (response.status === 200) {
                    fetchProducts();
                    setShowSuccessPopup(true);
                    setSuccessMessage('Product updated successfully!');
                    setShowEditPopup(false);
                } else {
                    console.error('Failed to update product.');
                }
            })
            .catch(error => {
                console.error('Error updating product:', error);
            });
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProductToEdit({
            ...productToEdit,
            [name]: value
        });
    };

    const handleDeleteConfirmation = (productId) => {
        setShowDeleteConfirmation(true);
        setProductToEdit({ ...productToEdit, id: productId });
    };

    const handleDeleteConfirm = () => {
        deleteProduct(productToEdit.id);
    };
     // Function to close the popup and reload the page
     const closePopup = () => {
        setShowSuccessPopup(false);
        window.location.reload(); // Reload the page when the popup is closed
    };


    const handleDeleteCancel = () => {
        setShowDeleteConfirmation(false);
    };

    return (
        <div className="container mt-5">
            <Popup 
                show={showSuccessPopup} 
                handleClose={() => setShowSuccessPopup(false)} 
                message={successMessage} 
            />
            <Popup 
                show={showDeleteConfirmation} 
                handleClose={() => setShowDeleteConfirmation(false)} 
                message={
                    <div>
                        <p>Are you sure you want to delete this product?</p>
                        <button onClick={handleDeleteConfirm}>Yes</button>
                        <button onClick={handleDeleteCancel}>No</button>
                    </div>
                }
            />
            <Popup 
                show={showEditPopup} 
                handleClose={() => setShowEditPopup(false)} 
                message={
                    <div className="edit-popup">
                        <h3 className="text-center">Update Product</h3>
                        <form onSubmit={handleEditSubmit}>
                            <div className="form-container">
                                <div className="input-group">
                                    <label htmlFor="name">Name:</label>
                                    <input type="text" id="name" name="name" value={productToEdit.name} onChange={handleInputChange} />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="company_name">Company Name:</label>
                                    <input type="text" id="company_name" name="company_name" value={productToEdit.company_name} onChange={handleInputChange} />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="price">Price:</label>
                                    <input type="number" id="price" name="price" value={productToEdit.price} onChange={handleInputChange} />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="mfd">MFD:</label>
                                    <input type="date" id="mfd" name="mfd" value={productToEdit.mfd} onChange={handleInputChange} />
                                </div>
                            </div>
                            <div className="form-container">
                                <div className="input-group">
                                    <label htmlFor="exp">EXP:</label>
                                    <input type="date" id="exp" name="exp" value={productToEdit.exp} onChange={handleInputChange} />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="units">Units:</label>
                                    <input type="number" id="units" name="units" value={productToEdit.units} onChange={handleInputChange} />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="discount">Discount:</label>
                                    <input type="number" id="discount" name="discount" value={productToEdit.discount} onChange={handleInputChange} />
                                </div>
                                <div className="input-group">
                                    <label htmlFor="gst">GST:</label>
                                    <input type="number" id="gst" name="gst" value={productToEdit.gst} onChange={handleInputChange} />
                                </div>
                            </div>
                            <button type="submit">Update</button>
                        </form>
                    </div>
                }
            />
            <h2 className="mb-4">Product Details</h2>
            <div className="table-responsive">
                <table className="table table-striped table-hover">
                    <thead className="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Company</th>
                            <th>Price</th>
                            <th>MFD</th>
                            <th>EXP</th>
                            <th>Units</th>
                            <th>Discount</th>
                            <th>GST</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {products.map(product => (
                            <tr key={product.id}>
                                <td>{product.id}</td>
                                <td>{product.name}</td>
                                <td>{product.company_name}</td>
                                <td>{product.price}</td>
                                <td>{new Date(product.mfd).toLocaleDateString()}</td>
                                <td>{new Date(product.exp).toLocaleDateString()}</td>
                                <td>{product.units}</td>
                                <td>{product.discount}</td>
                                <td>{product.gst}</td>
                                <td>
                                    <button className="update-button" onClick={() => editProduct(product.id)}>Update</button>
                                    <button className="delete-button" onClick={() => handleDeleteConfirmation(product.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default ProductDetails;
