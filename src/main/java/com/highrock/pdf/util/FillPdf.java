package com.highrock.pdf.util;

import com.highrock.pdf.bean.Invoice;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;


import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static com.itextpdf.kernel.pdf.PdfName.AcroForm;
import static com.itextpdf.kernel.pdf.PdfName.BaseFont;

/**
 * Created by user on 2017/12/4.
 */
public class FillPdf {
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    private String templatePdfPath;
    private String ttcPath;
    private String targetPdfpath;
    private Invoice invoice;


    public FillPdf() {
        super();
    }

    public FillPdf(String templatePdfPath, String ttcPath,
                           String targetPdfpath, Invoice invoice) {
        this.templatePdfPath= templatePdfPath;
        this.ttcPath= ttcPath;
        this.targetPdfpath= targetPdfpath;
        this.invoice= invoice;
    }

    public void templetInvoice(String SRC,String DEST,Invoice invoice) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        form.setGenerateAppearance(true);
        form.getField("soldName").setValue(String.valueOf(invoice.getSoldName()));
        form.getField("soldAddress").setValue(String.valueOf(invoice.getSoldAddress()));
        form.getField("soldEmail").setValue(String.valueOf(invoice.getSoldEmail()));
        form.getField("invoiceDate").setValue(String.valueOf(invoice.getInvoiceDate()));
        form.getField("invoiceNumber").setValue(String.valueOf(invoice.getInvoiceNumber()));
        form.getField("orderPrice").setValue(String.valueOf(invoice.getOrderPrice()));
        form.getField("tax").setValue(String.valueOf(invoice.getTax()));
        form.getField("total").setValue(String.valueOf(invoice.getTotal()));
        form.getField("orderNumber").setValue(String.valueOf(invoice.getOrderNumber()));
        form.getField("styleName").setValue(String.valueOf(invoice.getStyleName()));
        form.getField("description").setValue(String.valueOf(invoice.getDescription()));
        form.getField("amount").setValue(String.valueOf(invoice.getAmount()));
        form.flattenFields();

        //Rectangle r = new Rectangle(PageSize.A4.getRight(72), PageSize.A4.getTop(72));
        //table.setWidthPercentage(widths, r);
        //table.setw
        pdfDoc.close();
    }

    /**
     * @return the templatePdfPath
     */
    public String getTemplatePdfPath() {
        return templatePdfPath;
    }

    /**
     * @param templatePdfPath the templatePdfPathto set
     */
    public void setTemplatePdfPath(String templatePdfPath) {
        this.templatePdfPath= templatePdfPath;
    }

    /**
     * @return the ttcPath
     */
    public String getTtcPath() {
        return ttcPath;
    }

    /**
     * @param ttcPath the ttcPath to set
     */
    public void setTtcPath(String ttcPath) {
        this.ttcPath= ttcPath;
    }

    /**
     * @return the targetPdfpath
     */
    public String getTargetPdfpath() {
        return targetPdfpath;
    }

    /**
     * @param targetPdfpath the targetPdfpath toset
     */
    public void setTargetPdfpath(String targetPdfpath) {
        this.targetPdfpath= targetPdfpath;
    }


}
