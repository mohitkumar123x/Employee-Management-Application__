const API_BASE_URL =
  process.env.REACT_APP_API_BASE_URL ||
  (window.location.hostname.includes('onrender.com')
    ? 'https://employee-management-backend.onrender.com'
    : 'http://localhost:8080');

export default API_BASE_URL;
