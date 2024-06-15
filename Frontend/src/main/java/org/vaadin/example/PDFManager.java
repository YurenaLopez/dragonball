package org.vaadin.example;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PDFManager {
    public void GenerarPDF(String name, Characters character) {
        try {
            // Obtener la ruta absoluta del directorio donde se encuentra la clase PDFManager
            String classLocation = PDFManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File classLocationFile = new File(classLocation);
            String projectRoot = classLocationFile.getParentFile().getParentFile().getParentFile().getParent(); // Subir cuatro niveles

            // Construir la ruta completa al archivo PDF en la raíz del proyecto
            String savePath = projectRoot + File.separator + "dragonball" + File.separator + name + ".pdf";

            // Crear el archivo PDF
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter.getInstance(doc, new FileOutputStream(savePath));
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

            System.out.println("PDF generado exitosamente en: " + savePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
