import React from 'react';
import './popup.css';

function Popup({ show, handleClose, message, confirmAction }) {
  return (
    <>
      {show && (
        <div className="popup-overlay">
          <div className="popup">
            <h3>{message}</h3>
            <div className="button-container">

            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default Popup;
