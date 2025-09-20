import React, { useState } from "react";

function App() {
  const [report, setReport] = useState(null);

  // CSV upload + lineNumber bhejna
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
      setReport(data);
    } catch (err) {
      console.error("Error:", err);
    }
  };


  // const handleGenerate = async (e) => {
  //   e.preventDefault();
  //   const form = document.getElementById("uploadForm");
  //   const formData = new FormData(form);
  //
  //   try {
  //     const response = await fetch("http://localhost:8080/api/logs/upload", {
  //       method: "POST",
  //       body: formData,
  //     });
  //
  //     const data = await response.json();
  //     setReport(data);
  //   } catch (err) {
  //     console.error("Error:", err);
  //   }
  // };

  // PDF download
  const handleDownload = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/logs/pdf");
      const blob = await response.blob();
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = "report.pdf";
      a.click();
    } catch (err) {
      console.error("Error:", err);
    }
  };

  return (
      <div style={{ padding: "20px", fontFamily: "Arial" }}>
        <h1>Log Sheet Generator</h1>

        {/* Upload Form */}
        <form id="uploadForm" onSubmit={handleGenerate}>
          <input type="file" name="file" accept=".csv" required /> <br /><br />
          <input type="number" name="shooterId" placeholder="Enter line number" required /> <br /><br />
          <button type="submit">Generate Report</button>
        </form>

        {/* Report Output */}
        {report && (
            <div style={{ marginTop: "20px" }}>
              <h2>Generated Report (JSON)</h2>
              <pre style={{ background: "#f4f4f4", padding: "10px" }}>
            {JSON.stringify(report, null, 2)}
          </pre>
              <button onClick={handleDownload}>Download PDF</button>
            </div>
        )}
      </div>
  );
}

export default App;
