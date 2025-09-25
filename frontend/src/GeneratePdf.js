import jsPDF from "jspdf";

export function generateLogSheetPDF(jsonData) {
    const doc = new jsPDF();
    doc.setFontSize(10);
    doc.text("Log Sheet Report", 10, 10);

    const pageHeight = doc.internal.pageSize.height;

    let y = 20;

    for(const shooterId in jsonData) {
        const logSheet = jsonData[shooterId];

        doc.text(`shooter Id : ${shooterId}`,10, y);
        y += 10;

        for(const series of logSheet.series){

            for(const record of series.shotRecords){

                // check if the page has space
                if(y > pageHeight - 20){
                    doc.addPage();
                    y=20;
                }

                const line = `${record.shotNumber}    ${record.scoreValue}  ${record.timestamp}  ${record.coordinates.x}/   ${record.coordinates.y} `;
                doc.text(line,10, y);
                y += 5;
                doc.text(`     (${record.overtime})`,10, y);
                y += 5;
            }
            doc.text(`Subtotal : ${series.subTotal}`,10, y);
            y += 10;
        }

        doc.text(`Total : ${logSheet.total}`,10, y);
        y += 10;

    }

    doc.save("logsheet.pdf");
}
