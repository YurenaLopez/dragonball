package org.vaadin.example;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PDFManager {
    private String savePath;

    public PDFManager() {
        try {
            // Cargar la configuración desde application.properties
            Properties prop = new Properties();
            InputStream input = PDFManager.class.getClassLoader().getResourceAsStream("application.properties");
            if (input != null) {
                prop.load(input);
                savePath = prop.getProperty("pdf.savePath");
            } else {
                throw new RuntimeException("No se pudo encontrar el archivo de configuración application.properties");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al cargar la configuración desde application.properties", ex);
        }
    }

    public void GenerarPDF(String name, Characters character) {
        try {
            // Construir la ruta completa al archivo PDF
            String fullPath = savePath + name + ".pdf";

            // Crear el archivo PDF
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter.getInstance(doc, new FileOutputStream(fullPath));
            doc.open();

            // Agregar título al documento
            Paragraph title = new Paragraph("Información del Personaje: " + character.getName());
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);

            // Agregar información del personaje
            doc.add(new Paragraph("ID: " + character.getId()));
            doc.add(new Paragraph("Nombre: " + character.getName()));
            doc.add(new Paragraph("Ki: " + character.getKi()));
            doc.add(new Paragraph("Ki Máximo: " + character.getMaxKi()));
            doc.add(new Paragraph("Raza: " + character.getRace()));
            doc.add(new Paragraph("Género: " + character.getGender()));

            // Cerrar el documento
            doc.close();

            System.out.println("PDF generado exitosamente en: " + fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
