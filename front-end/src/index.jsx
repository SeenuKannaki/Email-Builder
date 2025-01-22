import "./index.css";
import React from 'react';
import ReactDOM from 'react-dom/client'; // Make sure this is imported
import App from './App'; // Import App component

const root = ReactDOM.createRoot(document.getElementById('root')); // Use createRoot instead of render
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);




