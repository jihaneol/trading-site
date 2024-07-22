import React, { useState } from "react";
import http from "../api/http";
import * as FileSaver from "file-saver";
// import * as XLSX from "xlsx";
const FileUpload = () => {
  const [file, setFile] = useState(null);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };
  const excelFileType =
    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
  const excelFileExtension = ".xlsx";
  const excelFileName = "작성자";

  const handleUpload = () => {
    const formData = new FormData();
    formData.append("file", file);
    http
      .post("/api/file", formData)
      .then(({ res }) => {
        console.log(res);
        // const excelButter = XLSX.write(res);
        // console.log(excelButter);
        // const excelFile = new Blob([excelButter]);
        // FileSaver.saveAs(excelFile, excelFileName + excelFileExtension);
      })
      .catch((error) => {
        console.error("Error fetching batteries data", error);
      });
  };
  return (
    <div>
      <input type="file" onChange={handleFileChange} style={{ fontSize: "50px" }} />
      <button onClick={handleUpload} style={{ fontSize: "50px" }}>
        Upload
      </button>
    </div>
  );
};

export default FileUpload;
