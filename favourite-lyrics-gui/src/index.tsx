import React from 'react';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import * as ReactDOMClient from "react-dom/client";


let elementById = document.getElementById('root');
if (elementById) {

    const root = ReactDOMClient.createRoot(elementById);

    root.render(<React.StrictMode>
        <App/>
    </React.StrictMode>);

    reportWebVitals();
}