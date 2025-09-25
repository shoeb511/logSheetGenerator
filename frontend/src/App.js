import React, { useState } from "react";
import { generateLogSheetPDF } from "./GeneratePdf"; // <-- your pdf generator file

function App() {
  const [report, setReport] = useState(null);

  // CSV upload + shooterId
  const handleGenerate = async (e) => {
    e.preventDefault();
    const fileInput = e.target.elements.file.files[0];
    const shooterId = e.target.elements.shooterId.value;

    const formData = new FormData();
    formData.append("file", fileInput);
    formData.append("shooterId", shooterId);

    try {
      const response = await fetch("http://localhost:8080/api/logs/upload", {
        method: "POST",
        body: formData,
      });

      const data = await response.json();
      setReport(data); // save JSON response
    } catch (err) {
      console.error("Error:", err);
    }
  };

  // PDF generation (frontend)
  const handleDownloadPDF = () => {
    if (report) {
      generateLogSheetPDF(report); // call your GeneratePdf.js function
    } else {
      alert("Please generate report first!");
    }
  };

  return (
      <div style={{ padding: "20px", fontFamily: "Arial" }}>
        <h1>Log Sheet Generator</h1>

        {/* Upload Form */}
        <form id="uploadForm" onSubmit={handleGenerate}>
          <input type="file" name="file" accept=".csv" required /> <br /><br />
          <input
              type="number"
              name="shooterId"
              placeholder="Enter shooter ID"
              required
          />{" "}
          <br /><br />
          <button type="submit">Generate Report</button>
        </form>

        {/* Report Output */}
        {report && (
            <div style={{ marginTop: "20px" }}>
              <h2>Generated Report (JSON)</h2>
              <pre style={{ background: "#f4f4f4", padding: "10px" }}>
            {JSON.stringify(report, null, 2)}
          </pre>
              <button onClick={handleDownloadPDF}>Download PDF</button>
            </div>
        )}
      </div>
  );
}

export default App;
