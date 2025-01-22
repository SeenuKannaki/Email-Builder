import { useState, useEffect } from "react";
import { downloadTemplate, fetchData, uploadEmailConfig, uploadData } from "../Api";
import { useDropzone } from "react-dropzone";

const EmailEditor = () => {
    const [layout, setLayout] = useState("grid");
    const [emailData, setEmailData] = useState({
        title: "",
        content: "",
        footer: "",
        imageUrl: "",
    });

    // Fetch the layout on component mount
    useEffect(() => {
        const getLayout = async () => {
            const fetchedLayout = await fetchData();
            setLayout(fetchedLayout); // Fixed the setLayout function name to use lowercase 's'
        };
        getLayout();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEmailData((prevData) => ({ ...prevData, [name]: value }));
    };

    const onDrop = (acceptedFiles) => {
        const file = acceptedFiles[0];
        uploadData(file).then((url) => {
            setEmailData((prevData) => ({ ...prevData, imageUrl: url }));
        });
    };

    const { getRootProps, getInputProps } = useDropzone({ onDrop });

    const handleSave = () => {
        uploadEmailConfig(emailData); // Calling API to save email config
    };

    const handleDownload = () => {
        downloadTemplate(emailData.title); // We should pass title as an ID or unique identifier
    };

    return (
        <div className="container">
            <h1>Email Editor</h1>
            <div>
                <h2>Title</h2>
                <input
                    type="text"
                    name="title"
                    value={emailData.title}
                    onChange={handleChange}
                    placeholder="Enter Title"
                />
            </div>
            <div>
                <h2>Content</h2>
                <textarea
                    name="content"
                    value={emailData.content}
                    onChange={handleChange}
                    placeholder="Enter Content"
                />
            </div>
            <div>
                <h2>Footer</h2>
                <input
                    type="text"
                    name="footer"
                    value={emailData.footer}
                    onChange={handleChange}
                    placeholder="Enter Footer"
                />
            </div>
            <div>
                <h2>Image</h2>
                <div {...getRootProps()}>
                    <input {...getInputProps()} />
                    <p>Drag and drop an image here, or click to select one.</p>
                </div>
                {emailData.imageUrl && <img src={emailData.imageUrl} alt="uploaded" />}
            </div>
                <button onClick={handleSave}>Save Template</button>
                <button onClick={handleDownload}>Download Template</button>
        </div>

    );
};

export default EmailEditor;
