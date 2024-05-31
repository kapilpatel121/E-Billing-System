import React from 'react'
import { faFacebook, faInstagram, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { faChartLine, faMoneyBillWave, faShoppingCart, faUsers } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './footer.css';
function Footer() {
    return (
        <div>
            <footer className="footer">
                <div className="footer-content">
                    <p>&copy; 2024 E-billing System. All rights reserved.</p>
                    <div className="social-icons">
                        <a href="#"><FontAwesomeIcon icon={faFacebook} className="green-icon" /></a>
                        <a href="#"><FontAwesomeIcon icon={faTwitter} className="green-icon" /></a>
                        <a href="#"><FontAwesomeIcon icon={faInstagram} className="green-icon" /></a>
                    </div>
                </div>
            </footer>
        </div>
    )
}

export default Footer