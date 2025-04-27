package utils;

import entity.Ve;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import static utils.GenHTML.generateHtmlTicket;
import static utils.GenHTML.generateHtmlTickets;

public class TicketPrinter {
    private final JDialog dialog;
    private final JEditorPane previewArea;

    public TicketPrinter(Ve veInput) {
        // Initialize the JFrame for the ticket printer
        dialog = new JDialog();
        dialog.setTitle("Ticket Preview");

        // Generate HTML content for the ticket and set it in the preview area
        String htmlContent = generateHtmlTicket(veInput);

        // Initialize the preview area (JEditorPane) and set it to display HTML content
        previewArea = new JEditorPane();
        previewArea.setEditable(false);
        previewArea.setContentType("text/html");
        previewArea.setText(htmlContent); // Set HTML content into the previewArea

        // Set up the JFrame properties
        dialog.setModal(true);
        dialog.setSize(700, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Add the preview area to the dialog
        dialog.add(new JScrollPane(previewArea), BorderLayout.CENTER);

        // Control panel with print button
        JPanel controlPanel = new JPanel();
        JButton printButton = new JButton("Print");
        controlPanel.add(printButton);

        // Add control panel to the dialog
        dialog.add(controlPanel, BorderLayout.SOUTH);

        // Button action to print the ticket and send it by email
        printButton.addActionListener(e -> {
            printTicket();//goi cua so in pre view
        });

        // Make the dialog visible
        dialog.setVisible(true);
    }


    // Method to print the ticket
    public void printTicket() {
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
                JOptionPane.showMessageDialog(null, "Printing failed: " + ex.getMessage());
            }
        }
    }













}