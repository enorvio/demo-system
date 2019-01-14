package newXmlReader;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;

import relationalCategory.*;

public class UserHandler extends DefaultHandler {

    boolean bInvoice = false;
    boolean bInvoicesxml = false;
    boolean bOrderId = false;
    boolean bPersonId = false;
    boolean bOrderDate = false;
    boolean bTotalPrice = false;
    boolean bOrderline = false;
    boolean bproductId = false;
    boolean basin = false;
    boolean btitle = false;
    boolean bprice = false;
    boolean bbrand = false;
    int currentOrderlinesId = 0;
    int j = 0;
    String[][] rows1 = new String[25][6];
    String[][] rows2 = new String[25][7];
    String[] attributes1 = {"OrderId", "PersonId", "OrderDate", "TotalPrice", "OrderlinesId"};
    relationalCategory.Table invoices = new relationalCategory.Table("invoices", attributes1);
    String[] attributes2 = {"OrderlinesId", "productId", "asin", "title", "price", "brand"};
    relationalCategory.Table orderlines = new relationalCategory.Table("orderlines", attributes2);
    private DefaultMutableTreeNode currentNode = null;
    private DefaultMutableTreeNode previousNode = null;
    private DefaultMutableTreeNode rootNode = null;

    public UserHandler(DefaultMutableTreeNode root) {
        this.rootNode = root;
    }

    public Table getInvoices() {
        return invoices;
    }

    public Table getOrderlines() {
        return orderlines;
    }

    public void startDocument() {
        currentNode = rootNode;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        previousNode = currentNode;
        currentNode = new DefaultMutableTreeNode(qName);
        previousNode.add(currentNode);
        if (qName.equals("OrderId")) {
            currentOrderlinesId++;
            bOrderId = true;
        } else if (qName.equals("PersonId")) {
            bPersonId = true;
        } else if (qName.equals("Orderline")) {
            bOrderline = true;
            rows1[currentOrderlinesId][4] = "" + currentOrderlinesId;
            rows2[j][0] = "" + currentOrderlinesId;
        } else if (qName.equals("OrderDate")) {
            bOrderDate = true;
        } else if (qName.equals("TotalPrice")) {
            bTotalPrice = true;
        } else if (qName.equals("productId")) {
            bproductId = true;
        } else if (qName.equals("asin")) {
            basin = true;
        } else if (qName.equals("title")) {
            btitle = true;
        } else if (qName.equals("price")) {
            bprice = true;
        } else if (qName.equals("brand")) {
            bbrand = true;
        }
    }

    public void endDocument() throws SAXException {
        for (int j = 0; j < 25; j++) {
            if (rows1[j][0] != null) {
                invoices.addRow(new Row(attributes1, rows1[j]));
            }
            if (rows2[j][0] != null) {
                orderlines.addRow(new Row(attributes2, rows2[j]));
            }
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String contents = (new String(ch, start, length));
        if (!contents.equals("")) {
            currentNode.add(new DefaultMutableTreeNode(contents));
        }

        if (bOrderId) {
            rows1[currentOrderlinesId][0] = contents;
            bOrderId = false;
        } else if (bPersonId) {
            rows1[currentOrderlinesId][1] = contents;
            bPersonId = false;
        } else if (bOrderDate) {
            rows1[currentOrderlinesId][2] = contents;
            bOrderDate = false;
        } else if (bTotalPrice) {
            rows1[currentOrderlinesId][3] = contents;
            bTotalPrice = false;
        } else if (bproductId) {
            rows2[j][1] = contents;
            bproductId = false;
        } else if (basin) {
            rows2[j][2] = contents;
            basin = false;
        } else if (btitle) {
            rows2[j][3] = contents;
            btitle = false;
        } else if (bprice) {
            rows2[j][4] = contents;
            bprice = false;
        } else if (bbrand) {
            rows2[j][5] = contents;
            j++;
            bbrand = false;
        }

    }

    public void endElement(String uri, String qName, String lName) {
        if (currentNode.getUserObject().equals(lName)) {
            currentNode = (DefaultMutableTreeNode) currentNode.getParent();
        }
    }

    public DefaultMutableTreeNode getTree() {
        return rootNode;
    }

}
