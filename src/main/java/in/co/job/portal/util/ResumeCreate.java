package in.co.job.portal.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.co.job.portal.bean.MakeResumeBean;

public class ResumeCreate {

	public static void ResumeCreate(MakeResumeBean bean) throws FileNotFoundException, DocumentException {
		
		try {

			OutputStream file = new FileOutputStream(
					new File("F:\\JobPortal\\JobPortal\\src\\main\\webapp\\file\\"+bean.getName()+".pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........
			//------------------------------------------------------------------------------------------
			Font font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLUE);
			Chunk chunk = new Chunk("Name : "+bean.getName(), font);
			document.add(new Paragraph(chunk));

			Font emailfont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLUE);
			Chunk emailchunk = new Chunk("Email : "+bean.getEmail(), emailfont);
			document.add(new Paragraph(emailchunk));

			Font mobilefont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLUE);
			Chunk mobilechunk = new Chunk("Mobile : "+bean.getMobile(), mobilefont);
			document.add(new Paragraph(mobilechunk));
			document.add(Chunk.NEWLINE); // Something like in HTML 🙂

			//------------------------------------------------------------------------------------------
			Font objfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk objchunk = new Chunk("Objective", objfont);
			objchunk.setUnderline(+1f, -2f);
			document.add(objchunk);

			document.add(Chunk.NEWLINE);
			Font objdfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk objdchunk = new Chunk(
					bean.getObjective(),
					objdfont);
			document.add(objdchunk);

			//------------------------------------------------------------------------------------------
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font edfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk edchunk = new Chunk("Education And Qualification", edfont);
			edchunk.setUnderline(+1f, -2f);
			document.add(edchunk);

			
			//------------------------------------------------------------------------------------------
			PdfPTable table = new PdfPTable(3);

			/*
			 * PdfPCell cell = new PdfPCell(new Paragraph("Java4s.com"));
			 * 
			 * cell.setColspan(3); cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * cell.setPadding(10.0f); cell.setBackgroundColor(new BaseColor(140, 221, 8));
			 * 
			 * table.addCell(cell);
			 */

			table.addCell(bean.getgCourceName());
			table.addCell(bean.getgInsName());
			table.addCell(bean.getgPercentage()+"%");
		
			table.addCell(bean.gethCourceName());
			table.addCell(bean.gethInsName());
			table.addCell(bean.gethPercentage()+"%");
			
			table.addCell(bean.getsCourceName());
			table.addCell(bean.getsInsName());
			table.addCell(bean.getsPercentage()+"%");
			//table.setSpacingBefore(30.0f); // Space Before table starts, like margin-top in CSS
			table.setSpacingAfter(30.0f);
			document.add(table);
			//------------------------------------------------------------------------------------------
			Font skillfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk skillchunk = new Chunk("Programming Skill", skillfont);
			skillchunk.setUnderline(+1f, -2f);
			document.add(skillchunk);
			
			document.add(Chunk.NEWLINE);
			Font pskillfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk pskillchunk = new Chunk(bean.getSkill(),pskillfont);
			document.add(pskillchunk);
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font hobfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk hobchunk = new Chunk("Hobbies and Intrest", hobfont);
			hobchunk.setUnderline(+1f, -2f);
			document.add(hobchunk);
			
			document.add(Chunk.NEWLINE);
			Font hbfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk hbchunk = new Chunk(bean.getHobbies(),hbfont);
			document.add(hbchunk);
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font pfont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk pchunk = new Chunk("Personal Detail", pfont);
			pchunk.setUnderline(+1f, -2f);
			document.add(pchunk);
			
			document.add(Chunk.NEWLINE);
			Font pdfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk pdchunk = new Chunk(bean.getpDetail(),pdfont);
			document.add(pdchunk);
			
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Font defont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk dechunk = new Chunk("Declaration", defont);
			dechunk.setUnderline(+1f, -2f);
			document.add(dechunk);
			
			document.add(Chunk.NEWLINE);
			Font decfont = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
			Chunk decchunk = new Chunk(bean.getDeclaration(),decfont);
			document.add(decchunk);
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
			Font datefont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
			Chunk datechunk = new Chunk("Date : "+DataUtility.getDateString(bean.getDate()).toString(), datefont);
			document.add(new Paragraph(datechunk));
			
	
			Font plfont = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
			Chunk plchunk = new Chunk("Place : "+bean.getPlace(), plfont);
			document.add(new Paragraph(plchunk));
			
			document.newPage(); // Opened new page
			document.close();
			file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
