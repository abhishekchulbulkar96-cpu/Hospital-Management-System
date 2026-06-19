package com.hospital.service;

import java.awt.Color;

import org.springframework.stereotype.Service;

import com.hospital.entity.Bill;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service 
public class PdfService{
public void generateAppointmentSlip(
        HttpServletResponse response,
        String patientName,
        String doctorName,
        String date)
        throws Exception {

    Document document =
            new Document(PageSize.A4);

    PdfWriter.getInstance(
            document,
            response.getOutputStream());

    document.open();

    // Header

    PdfPTable header =
            new PdfPTable(1);

    header.setWidthPercentage(100);

    Font hospitalFont =
            new Font(
                    Font.HELVETICA,
                    24,
                    Font.BOLD,
                    Color.WHITE);

    PdfPCell hospitalCell =
            new PdfPCell(
                    new Phrase(
                            "LIFECARE HOSPITAL",
                            hospitalFont));

    hospitalCell.setHorizontalAlignment(
            Element.ALIGN_CENTER);

    hospitalCell.setBackgroundColor(
            new Color(37,99,235));

    hospitalCell.setPadding(15);

    hospitalCell.setBorder(0);

    header.addCell(hospitalCell);

    document.add(header);

    document.add(new Paragraph(" "));

    // Title

    Font titleFont =
            new Font(
                    Font.HELVETICA,
                    18,
                    Font.BOLD);

    Paragraph title =
            new Paragraph(
                    "Appointment Confirmation",
                    titleFont);

    title.setAlignment(
            Element.ALIGN_CENTER);

    document.add(title);

    document.add(new Paragraph(" "));

    // Details Table

    PdfPTable table =
            new PdfPTable(2);

    table.setWidthPercentage(100);

    table.addCell("Patient Name");
    table.addCell(patientName);

    table.addCell("Doctor Name");
    table.addCell(doctorName);

    table.addCell("Appointment Date");
    table.addCell(date);

    table.addCell("Token Number");
    table.addCell("A-101");

    table.addCell("Room Number");
    table.addCell("101");

    document.add(table);

    document.add(new Paragraph(" "));

    // Instructions

    Font instructionFont =
            new Font(
                    Font.HELVETICA,
                    12,
                    Font.BOLD);

    Paragraph instructionTitle =
            new Paragraph(
                    "Instructions",
                    instructionFont);

    document.add(instructionTitle);

    document.add(
            new Paragraph(
                    "• Please arrive 15 minutes early"));

    document.add(
            new Paragraph(
                    "• Carry a valid ID proof"));

    document.add(
            new Paragraph(
                    "• Bring previous reports"));

    document.add(new Paragraph(" "));

    // Footer

    Paragraph footer =
            new Paragraph(
                    "Thank You For Choosing LifeCare Hospital");

    footer.setAlignment(
            Element.ALIGN_CENTER);

    document.add(footer);

    document.close();
}
public void generateBillPdf(
        HttpServletResponse response,
        Bill bill)
        throws Exception {

    response.setContentType("application/pdf");

    response.setHeader(
            "Content-Disposition",
            "inline; filename=Bill_" +
            bill.getBillId() + ".pdf");

    Document document =
            new Document(PageSize.A4);

    PdfWriter.getInstance(
            document,
            response.getOutputStream());

    document.open();

    // ==========================
    // HOSPITAL HEADER
    // ==========================

    Font hospitalFont =
            new Font(
                    Font.HELVETICA,
                    24,
                    Font.BOLD,
                    Color.WHITE);

    PdfPTable header =
            new PdfPTable(1);

    header.setWidthPercentage(100);

    PdfPCell hospitalCell =
            new PdfPCell(
                    new Phrase(
                            "LIFECARE HOSPITAL",
                            hospitalFont));

    hospitalCell.setHorizontalAlignment(
            Element.ALIGN_CENTER);

    hospitalCell.setBackgroundColor(
            new Color(37,99,235));

    hospitalCell.setPadding(18);

    hospitalCell.setBorder(0);

    header.addCell(hospitalCell);

    document.add(header);

    Paragraph subTitle =
            new Paragraph(
                    "Healthcare & Diagnostic Center");

    subTitle.setAlignment(
            Element.ALIGN_CENTER);

    document.add(subTitle);

    document.add(new Paragraph(" "));

    // ==========================
    // INVOICE TITLE
    // ==========================

    Font titleFont =
            new Font(
                    Font.HELVETICA,
                    18,
                    Font.BOLD);

    Paragraph title =
            new Paragraph(
                    "BILL INVOICE",
                    titleFont);

    title.setAlignment(
            Element.ALIGN_CENTER);

    document.add(title);

    document.add(new Paragraph(" "));

    // ==========================
    // BILL DETAILS
    // ==========================

    PdfPTable details =
            new PdfPTable(2);

    details.setWidthPercentage(100);

    details.addCell("Invoice No");
    details.addCell(
            "INV-" + bill.getBillId());

    details.addCell("Bill Date");
    details.addCell(
            String.valueOf(
                    bill.getBillDate()));

    details.addCell("Patient Name");
    details.addCell(
            bill.getPatient().getName());

    details.addCell("Patient ID");
    details.addCell(
            String.valueOf(
                    bill.getPatient()
                        .getPatientId()));

    document.add(details);

    document.add(new Paragraph(" "));

    // ==========================
    // AMOUNT TABLE
    // ==========================

    PdfPTable amountTable =
            new PdfPTable(2);

    amountTable.setWidthPercentage(100);

    PdfPCell head1 =
            new PdfPCell(
                    new Phrase("Description"));

    PdfPCell head2 =
            new PdfPCell(
                    new Phrase("Amount (₹)"));

    head1.setBackgroundColor(
            new Color(220,220,220));

    head2.setBackgroundColor(
            new Color(220,220,220));

    amountTable.addCell(head1);
    amountTable.addCell(head2);

    amountTable.addCell(
            "Consultation Fee");

    amountTable.addCell(
            String.valueOf(
                    bill.getConsultationFee()));

    amountTable.addCell(
            "Medicine Fee");

    amountTable.addCell(
            String.valueOf(
                    bill.getMedicineFee()));

    PdfPCell totalText =
            new PdfPCell(
                    new Phrase(
                            "TOTAL AMOUNT"));

    PdfPCell totalAmount =
            new PdfPCell(
                    new Phrase(
                            "₹ " +
                            bill.getTotalAmount()));

    totalText.setBackgroundColor(
            new Color(200,230,255));

    totalAmount.setBackgroundColor(
            new Color(200,230,255));

    amountTable.addCell(totalText);
    amountTable.addCell(totalAmount);

    document.add(amountTable);

    document.add(new Paragraph(" "));

    // ==========================
    // PAYMENT STATUS
    // ==========================

    Font statusFont =
            new Font(
                    Font.HELVETICA,
                    14,
                    Font.BOLD,
                    Color.GREEN);

    Paragraph status =
            new Paragraph(
                    "PAYMENT STATUS : PAID",
                    statusFont);

    status.setAlignment(
            Element.ALIGN_CENTER);

    document.add(status);

    document.add(new Paragraph(" "));
    document.add(new Paragraph(" "));
    document.add(new Paragraph(" "));

    // ==========================
    // SIGNATURE
    // ==========================

    Paragraph sign =
            new Paragraph(
                    "Authorized Signature");

    sign.setAlignment(
            Element.ALIGN_RIGHT);

    document.add(sign);

    Paragraph line =
            new Paragraph(
                    "_____________________");

    line.setAlignment(
            Element.ALIGN_RIGHT);

    document.add(line);

    document.add(new Paragraph(" "));
    document.add(new Paragraph(" "));

    // ==========================
    // FOOTER
    // ==========================

    Font footerFont =
            new Font(
                    Font.HELVETICA,
                    12,
                    Font.ITALIC);

    Paragraph footer =
            new Paragraph(
                    "Thank You For Choosing LifeCare Hospital",
                    footerFont);

    footer.setAlignment(
            Element.ALIGN_CENTER);

    document.add(footer);

    document.close();
}
}