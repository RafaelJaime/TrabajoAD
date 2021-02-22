package com.medicos.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.medicos.model.Medicine;

public class PDF {
	private List<Medicine> list;
	public PDF(List<Medicine> medicines) {
		this.list = medicines;
	}
	private void write(PdfPTable tabla) {
		PdfPCell cell = new PdfPCell();
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		
		cell.setPhrase(new Phrase("Medicine", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Description", font));
		tabla.addCell(cell);
		cell.setPhrase(new Phrase("Price", font));
		tabla.addCell(cell);
	}
	private void tableData(PdfPTable tabla) {
		float total = 0;
		for (Medicine medicine : list) {
			tabla.addCell(medicine.getName());
			tabla.addCell(medicine.getDescription());
			tabla.addCell(String.valueOf(medicine.getPrice()));
			total+= medicine.getPrice();
		}
		System.out.println(total);
		tabla.addCell("");tabla.addCell("");
		tabla.addCell("Total: " + String.valueOf(total));
	}
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(25);
		
		Paragraph p = new Paragraph("SegPrivado medicaments shop", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable tabla = new PdfPTable(3);
		tabla.setWidthPercentage(100f);
		tabla.setWidths(new float[] {1.5f, 3.5f, 1.5f});
		tabla.setSpacingBefore(10);
		
		write(tabla);
		tableData(tabla);
		document.add(tabla);
		document.close();
	}
}
