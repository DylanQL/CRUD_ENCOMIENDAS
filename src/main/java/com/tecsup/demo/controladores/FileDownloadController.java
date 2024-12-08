package com.tecsup.demo.controladores;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.tecsup.demo.modelo.daos.ReclamoRepository;
import com.tecsup.demo.modelo.daos.MotivoRepository;
import com.tecsup.demo.modelo.entidades.Reclamo;
import com.tecsup.demo.modelo.entidades.Motivo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class FileDownloadController {

    @Autowired
    private ReclamoRepository reclamoRepository;

    @Autowired
    private MotivoRepository motivoRepository;

    // Métodos para descargar PDF y XLS de Reclamos y Motivos

    @GetMapping("/download/reclamos/pdf")
    public ResponseEntity<ByteArrayResource> downloadReclamosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Reclamos").setFontSize(18));

        List<Reclamo> reclamos = reclamoRepository.findAll();

        for (Reclamo reclamo : reclamos) {
            document.add(new Paragraph("ID: " + reclamo.getId()));
            document.add(new Paragraph("Descripción: " + reclamo.getDescripcion()));
            document.add(new Paragraph("Fecha: " + reclamo.getFecha().toString()));
            document.add(new Paragraph("Estado: " + reclamo.getEstado()));
            document.add(new Paragraph("Motivo: " + reclamo.getMotivo().getDescripcion()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_reclamos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/reclamos/xls")
    public ResponseEntity<ByteArrayResource> downloadReclamosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reclamos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");
        headerRow.createCell(2).setCellValue("Fecha");
        headerRow.createCell(3).setCellValue("Estado");
        headerRow.createCell(4).setCellValue("Motivo");

        List<Reclamo> reclamos = reclamoRepository.findAll();

        int rowCount = 1;
        for (Reclamo reclamo : reclamos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(reclamo.getId());
            row.createCell(1).setCellValue(reclamo.getDescripcion());
            row.createCell(2).setCellValue(reclamo.getFecha().toString());
            row.createCell(3).setCellValue(reclamo.getEstado());
            row.createCell(4).setCellValue(reclamo.getMotivo().getDescripcion());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_reclamos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Similar methods for Motivos...

    @GetMapping("/download/motivos/pdf")
    public ResponseEntity<ByteArrayResource> downloadMotivosPdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Lista de Motivos").setFontSize(18));

        List<Motivo> motivos = motivoRepository.findAll();

        for (Motivo motivo : motivos) {
            document.add(new Paragraph("ID: " + motivo.getId()));
            document.add(new Paragraph("Descripción: " + motivo.getDescripcion()));
            document.add(new Paragraph("-------------------------------"));
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_motivos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/motivos/xls")
    public ResponseEntity<ByteArrayResource> downloadMotivosXls() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Motivos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Descripción");

        List<Motivo> motivos = motivoRepository.findAll();

        int rowCount = 1;
        for (Motivo motivo : motivos) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(motivo.getId());
            row.createCell(1).setCellValue(motivo.getDescripcion());
        }

        workbook.write(out);
        workbook.close();

        byte[] xlsBytes = out.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(xlsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lista_motivos.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(xlsBytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
