package com.highrock.pdf.action;

import com.alibaba.fastjson.JSON;
import com.highrock.pdf.bean.Invoice;
import com.highrock.pdf.util.FillPdf;
import com.highrock.pdf.util.IpAddressHelper;
import com.highrock.pdf.util.JSONHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by user on 2017/12/4.
 */
@RestController
@RequestMapping(value="/pdf")
public class PdfCreateController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${pdf.temp.location.hexainvoice}")
    private String tempLocationInvoice;
    @Value("${pdf.location.hexainvoice}")
    private String locationInvoice;
    @RequestMapping(value = "/invoice/", method = RequestMethod.POST)
    public String invoicePDF(@RequestBody String invoiceInfo) {

        logger.info("Get invoiceInfo:" + invoiceInfo + " ...");
        try {
            Invoice invoice = JSON.parseObject(invoiceInfo, Invoice.class);
            FillPdf pdfTT = new FillPdf();
            String path = locationInvoice + invoice.getInvoiceNumber();
            if (!new File(path).exists()) {
                new File(path).mkdirs();
            }
            String targetFileName = path + "/" + "Hexainvoice.pdf";
            File file = new File(targetFileName);
            file.createNewFile();
            pdfTT.templetInvoice(tempLocationInvoice, targetFileName,invoice);
            String pdfAddress = IpAddressHelper.getHostAddress()+"/"+invoice.getInvoiceNumber()+ "/" + "Hexainvoice.pdf";
            return pdfAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

}
