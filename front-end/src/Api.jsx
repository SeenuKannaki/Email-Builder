import axios from "axios";

const API_URL = "http://localhost:8080";

// Fetch layout from API
export const fetchData = async () => {
    try {
        const response = await axios.get(`${API_URL}/api/templates`);
        return response.data;
    } catch (error) {
        console.error("Error fetching layout:", error);
    }
};

// Upload image and return the URL
export const uploadData = async (file) => {
    try {
        const formData = new FormData();
        formData.append("files", file);
        const response = await axios.post(`${API_URL}/api/uploadImage`, formData);
        return response.data.url; // Assuming response contains a URL for the image
    } catch (error) {
        console.error("Error uploading image:", error);
    }
};

// Upload the email config to the backend
export const uploadEmailConfig = async (config) => {
    try {
        const response = await axios.post(`${API_URL}/api/uploadEmailConfig`, config);
        console.log(response.data); // Handle success (e.g., display success message)
    } catch (error) {
        console.error("Error uploading email config:", error);
    }
};

// Download the template (ensure API returns correct content type)
export const downloadTemplate = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/api/templates/${id}`, {
            responseType: "blob", // Ensures we get a downloadable file
        });

        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", `email-template-${id}.html`);
        document.body.appendChild(link);
        link.click();
    } catch (error) {
        console.error("Error downloading template:", error);
    }
};
