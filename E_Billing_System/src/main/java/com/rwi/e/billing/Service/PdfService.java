package com.rwi.e.billing.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rwi.e.billing.Entity.Product_Entity;
import com.rwi.e.billing.dto.Customer;
import com.rwi.e.billing.repository.Product_Repository;

@Service
public class PdfService {
	LocalDateTime dt = null;

	@Autowired
	private Product_Repository ProductService;

	// Get the current date
	LocalDate currentDate = LocalDate.now();

	// Get the current time
	LocalTime currentTime = LocalTime.now();

	// Define formats for date and time
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	// Format the current date and time using the defined formats
	String formattedDate = currentDate.format(dateFormatter);
	String formattedTime = currentTime.format(timeFormatter);

	public String createPdf(Customer customer, Map<String, Integer> productMap)
			throws IOException, DocumentException, SQLException {
     int id=customer.getId();
		//path of the local computer 
		String DEST = "E:/Ebilling/Bill" + id + ".pdf";
       String imagePath="E:/Ebilling/QRCodes/"+id+"QR.png";
		//create file in local computer 
		File file = new File(DEST);
		file.getParentFile().mkdirs();

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(DEST));
		document.open();
		PdfPTable table = new PdfPTable(8);

		// Set total width of the table
		table.setTotalWidth(500); // Adjust as needed

		// Set column widths
		float[] columnWidths = { 1f, 2f, 4f, 2.5f, 2f, 1.5f, 2f, 2.5f }; // Adjust as needed
		table.setWidths(columnWidths);

		// Add heading
		Paragraph heading = new Paragraph("VKP Billing Service");
		heading.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(heading);

		// Add paragraph
		Date dt = new Date();
		Paragraph paragraph = new Paragraph("Date:- " + formattedDate + "\n Time:-" + formattedTime);
		paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(paragraph);

		// Create a paragraph containing billing details
		Paragraph billingDetails = new Paragraph();
		billingDetails.setAlignment(Paragraph.ALIGN_LEFT);

		// Append customer information to the paragraph
		billingDetails.add("Customer Name: " + customer.getCustomerName() + "\n");
		billingDetails.add("Contact Number: " + customer.getContactNumber() + "\n");
		billingDetails.add("Email: " + customer.getEmail() + "\n");
		billingDetails.add("Payment Mode: " + customer.getPaymentMode() + "\n");
		// billingDetails.add("Total Amount: " + customer.getTotalAmount());

		// Add the paragraph to the document
		document.add(billingDetails);
		// Add space
		document.add(new Paragraph(" "));
		Optional<Product_Entity> opt = null;
		int i = 0, qty;

		Double price, grandTotal = 0.0, total = 0.0;
		table.addCell("Sno");
		table.addCell("Product id");
		table.addCell("Name");
		table.addCell("Company");
		table.addCell("GST");
		table.addCell("Quantity");
		table.addCell("price");
		table.addCell("total");
		for (Map.Entry<String, Integer> ProductInfo : productMap.entrySet()) {

			opt = ProductService.findProductByName(ProductInfo.getKey());
			if (opt.isPresent()) {

				Product_Entity product = opt.get();
				price = product.getPrice();
				qty = ProductInfo.getValue();
				table.addCell("" + ++i);
				table.addCell("" + product.getId());
				table.addCell(ProductInfo.getKey());
				table.addCell(product.getCompany_name());
				table.addCell("" + product.getGst());
				table.addCell("" + qty);
				table.addCell(String.format("%.2f", price));
				total = qty * product.getPrice();
				table.addCell(String.format("%.2f", total));
				//count total amount
				grandTotal += total;
			}

		}
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell("Grand Total");
		table.addCell(String.format("%.2f", grandTotal));

		
		// Add paragrap
		Paragraph paragraph2 = new Paragraph(
				"\"Thank you for using VKP BILLING Service for your recent shopping spree! We hope you found everything "
						+ "you were looking for and more. Your continued support means the world to us. We'd love to welcome you back soon for another"
						+ " delightful shopping experience. Stay tuned for new arrivals and exciting offers tailored just for you. See you again soon!\n\n");
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph2);
		System.out.println("Billing  pdf Generated successfully");
		System.out.println("The link is: " + DEST);
		document.add(table);

		// Add an image
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Image.ALIGN_CENTER);
		document.add(img);
		
		document.close();
		openPdfFile(DEST);

		return DEST;
	}

	public void openPdfFile(String filePath) {
		try {
			File pdfFile = new File(filePath);
			if (pdfFile.exists()) {
				String osName = System.getProperty("os.name").toLowerCase();
				Runtime rt = Runtime.getRuntime();
				if (osName.contains("win")) {
					rt.exec("cmd /c start chrome " + filePath);
				} else if (osName.contains("mac")) {
					rt.exec("open -a \"Google Chrome\" " + filePath);
				} else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
					rt.exec("google-chrome " + filePath);
				} else {
					System.out.println("Unsupported operating system.");
				}
			} else {
				System.out.println("PDF file not found.");
			}
		} catch (IOException e) {
			System.out.println("Error opening PDF file: " + e.getMessage());
		}
	}

}