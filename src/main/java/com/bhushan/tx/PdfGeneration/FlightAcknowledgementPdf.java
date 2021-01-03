package com.bhushan.tx.PdfGeneration;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.bhushan.tx.dto.FlightBookingAcknowledgement;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class FlightAcknowledgementPdf {

	public static String createPdf(FlightBookingAcknowledgement flightBookingAcknowledgement) {
		Document document = null;
		PdfWriter writer = null;
		String dest = "E://TicketInfo.pdf";
		try {
			document = new Document(PageSize.A3);
			writer = PdfWriter.getInstance(document, new FileOutputStream(dest, false));
			Font mainHead = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, new BaseColor(15, 63, 107));
			Font data = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, new BaseColor(15, 63, 107));
			Font subhead = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.WHITE);
			Font smalldata = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.DARK_GRAY);
			document.open();
			PdfPTable table2 = new PdfPTable(4);
			table2.setWidthPercentage(100f);
			table2.setWidths(new float[] { 3.4f, 0.9f, 0.6f, 1 });
			table2.addCell(getCell("TicketInfo™+ REPORT", PdfPCell.ALIGN_RIGHT, mainHead));
			table2.addCell(getCellSpan(" ", PdfPCell.ALIGN_MIDDLE, mainHead, 5));
			table2.addCell(getCell1("PNR_NUMBER", PdfPCell.ALIGN_LEFT, data));
			table2.addCell(getCell1(": " + flightBookingAcknowledgement.getPnrNo(), PdfPCell.ALIGN_LEFT, data));
			table2.addCell(getCellSpan("For " + flightBookingAcknowledgement.getPassengerInfo().getName(),
					PdfPCell.ALIGN_RIGHT, data, 4));
			table2.addCell(getCell1("Arrival Time", PdfPCell.ALIGN_LEFT, data));
			table2.addCell(getCell1(":" + flightBookingAcknowledgement.getPassengerInfo().getArrivaltime(),
					PdfPCell.ALIGN_LEFT, data));
			table2.addCell(getCell1("PickUpTime", PdfPCell.ALIGN_LEFT, data));
			table2.addCell(getCell1(": " + flightBookingAcknowledgement.getPassengerInfo().getPickuptime(),
					PdfPCell.ALIGN_LEFT, data));
			table2.setSpacingAfter(15f);
			document.add(table2);
			PdfPTable table3 = new PdfPTable(6);
			table3.setWidthPercentage(100f);
			table3.setWidths(new float[] { 1, 2, 1, 2, 1, 2 });
			table3.addCell(getCellColSpan("	   Inquiry Input Information", PdfPCell.ALIGN_LEFT, subhead, 6))
					.setBackgroundColor(new BaseColor(15, 63, 107));
			;
			table3.addCell(getCell("Name", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getName(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("Email ID(s)", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getEmail(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("Fare", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getFare(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("Source", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getSource(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("Destination", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getDestination(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("TravelDate", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getTraveldate(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("ArrivalTime", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getArrivaltime(),
					PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell("PickUpTime", PdfPCell.ALIGN_LEFT, data));
			table3.addCell(getCell(": " + flightBookingAcknowledgement.getPassengerInfo().getPickuptime(),
					PdfPCell.ALIGN_LEFT, data));
			table3.setSpacingAfter(6f);
			document.add(table3);
			table3.setSpacingAfter(6f);
			document.add(table3);
			PdfPTable table4 = new PdfPTable(3);
			table4.setWidthPercentage(100f);
			table4.setWidths(new float[] { 1.3f, 1.3f, 2 });
			table4.addCell(getCellColSpan("	   CRIF HM Score (s):", PdfPCell.ALIGN_LEFT, subhead, 6))
					.setBackgroundColor(new BaseColor(15, 63, 107));
			table4.addCell(getCell("   PNR_NUMBER", PdfPCell.ALIGN_LEFT, data));
			table4.addCell(getCell("   FARE_STATUS", PdfPCell.ALIGN_LEFT, data));
			table4.addCell(getCell("   DESCRIPTION", PdfPCell.ALIGN_LEFT, data));
			table4.addCell(getCell("   " + flightBookingAcknowledgement.getPnrNo(), PdfPCell.ALIGN_LEFT, data))
					.setBackgroundColor(new BaseColor(167, 203, 227));
			table4.addCell(getCell("   " + flightBookingAcknowledgement.getStatus(), PdfPCell.ALIGN_LEFT, data))
					.setBackgroundColor(new BaseColor(167, 203, 227));
			table4.addCell(getCell("  Flight Ticket has been Booked ", PdfPCell.ALIGN_LEFT, data))
					.setBackgroundColor(new BaseColor(167, 203, 227));
			table4.setSpacingAfter(0.5f);
			document.add(table4);
			Paragraph p1 = new Paragraph(
					"Tip: A-D: Very Low Risk ; E-G: Low Risk ; H-I: Medium Risk ; J-K: High Risk ; L-M: Very High Risk",
					smalldata);
			p1.setAlignment(Element.ALIGN_RIGHT);
			p1.setSpacingAfter(30f);
			document.add(p1);
			PdfPTable table5 = new PdfPTable(1);
			table5.setWidthPercentage(100f);
			table5.setWidths(new float[] { 1 });
			table5.addCell(getCellColSpan("	   Personal Information - Variations", PdfPCell.ALIGN_LEFT, subhead, 1))
					.setBackgroundColor(new BaseColor(15, 63, 107));
			table5.setSpacingAfter(0.5f);
			document.add(table5);
			// side by side tables
			PdfContentByte content = writer.getDirectContent();

			// end
			PdfPTable table6 = new PdfPTable(1);
			table6.setWidthPercentage(100f);
			table6.setWidths(new float[] { 1 });
			table6.addCell(getCellColSpan("	   Account Summary", PdfPCell.ALIGN_LEFT, subhead, 1))
					.setBackgroundColor(new BaseColor(15, 63, 107));
			table6.setSpacingAfter(0.5f);
			document.add(table6);

		} catch (Exception e) {

		}
		document.close();
		return "Pdf has been Generated";
	}

	private static PdfPCell getCell(String string, int alignment, Font canFont) {
		PdfPCell cell = new PdfPCell(new Phrase(string, canFont));
		cell.setPadding(6);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static PdfPCell getCell1(String string, int alignment, Font canFont) {
		PdfPCell cell = new PdfPCell(new Phrase(string, canFont));
		cell.setPadding(3);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static PdfPCell getCell2(String string, int alignment, Font canFont) {
		PdfPCell cell = new PdfPCell(new Phrase(string, canFont));
		cell.setPadding(3);
		cell.setHorizontalAlignment(alignment);
//	        cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static PdfPCell getCellSpan(String string, int alignment, Font canFont, int span) {
		PdfPCell cell = new PdfPCell(new Phrase(string, canFont));
		cell.setPadding(3);
		cell.setRowspan(span);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static PdfPCell getCellColSpan(String string, int alignment, Font canFont, int span) {
		PdfPCell cell = new PdfPCell(new Phrase(string, canFont));
		cell.setPadding(6);
		cell.setColspan(span);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

}
