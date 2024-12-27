package utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import entity.HoaDon;
import entity.Ve;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;

public class BillPrinter {
    private final JDialog dialog;
    private final JEditorPane previewArea;

    public BillPrinter(HoaDon hoaDonInput) {
        // Initialize the Jdialog for the bill printer
        dialog = new JDialog();
        dialog.setTitle("Hóa đơn thanh toán");

        // Generate HTML content for the bill and set it in the preview area
        String htmlContent = GenHTML.generateHtmlBill(hoaDonInput);

        // Initialize the preview area (JEditorPane) and set it to display HTML content
        previewArea = new JEditorPane();
        previewArea.setEditable(false);
        previewArea.setContentType("text/html");
        previewArea.setText(htmlContent); // Set HTML content into the previewArea

        // Set up the Jdialog properties
        dialog.setModal(true);
        dialog.setSize(700, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Add the preview area to the dialog
        dialog.add(new JScrollPane(previewArea), BorderLayout.CENTER);

        // Control panel with print button
        JPanel controlPanel = new JPanel();
        JButton printButton = new JButton("In hóa đơn");
        controlPanel.add(printButton);

        // Add control panel to the dialog
        dialog.add(controlPanel, BorderLayout.SOUTH);

        // Button action to print the bill and send it by email
        printButton.addActionListener(e -> {
            printBill();
        });

        // Make the dialog visible
        dialog.setVisible(true);
    }


    // Method to print the bill
    public void printBill() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;

            // Lấy vùng in khả dụng
            double imageableWidth = pageFormat.getImageableWidth();
            double imageableHeight = pageFormat.getImageableHeight();

            // Thiết lập scale để nội dung vừa khít vùng in
            double scaleX = imageableWidth / previewArea.getWidth();
            double scaleY = imageableHeight / previewArea.getHeight();
            double scale = Math.min(scaleX, scaleY);

            // Dịch tọa độ để vẽ từ vùng in khả dụng
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d.scale(scale, scale);

            // In nội dung
            previewArea.printAll(g2d);

            return PAGE_EXISTS;
        });

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "In thất bại: " + ex.getMessage());
            }
        }
    }










}