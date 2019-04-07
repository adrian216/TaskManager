package com.someonesmarter.todo.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Set;
import java.util.stream.Stream;

import com.someonesmarter.todo.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream taskPDFReport(Set<Task> tasks) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add text to PDF file
            Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);
            Paragraph para = new Paragraph( "Tasks", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(3);
            // Add PDF Table Header
            Stream.of( "Title", "Description", "Deadline")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (Task task : tasks) {
                PdfPCell titleCell = new PdfPCell(new Phrase(task.getTitle()));
                titleCell.setPaddingLeft(4);
                titleCell.setPaddingBottom(4);
                titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(titleCell);

                PdfPCell descriptionCell = new PdfPCell(new Phrase(task.getDescription()));
                descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                descriptionCell.setPaddingLeft(4);
                descriptionCell.setPaddingBottom(4);
                table.addCell(descriptionCell);

                PdfPCell deadlineCell = new PdfPCell(new Phrase(String.valueOf(task.getDeadline().toString())));
                deadlineCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                deadlineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                deadlineCell.setPaddingRight(4);
                deadlineCell.setPaddingBottom(4);
                table.addCell(deadlineCell);
            }
            document.add(table);

            document.close();
        }catch(DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
