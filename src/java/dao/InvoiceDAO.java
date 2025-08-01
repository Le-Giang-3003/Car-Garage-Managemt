/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dto.Invoice;
import dto.InvoiceDetail;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class InvoiceDAO {

    private Connection cn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public String getNextInvoiceID() {
        String nextId = "1";
        String sql = "SELECT MAX(invoiceID) FROM SalesInvoice";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                int maxId = rs.getInt(1);
                nextId = String.valueOf(maxId + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return nextId;
    }

    public boolean createInvoice(Invoice invoice) {
        boolean success = false;
        String insertInvoiceSQL = "INSERT INTO SalesInvoice (invoiceID, invoiceDate, salesID, carID, custID) VALUES (?, ?, ?, ?, ?)";
        String updateCarStatusSQL = "UPDATE Cars SET status = 'SOLD' WHERE carID = ?";

        try {
            cn = DBUtils.getConnection();
            cn.setAutoCommit(false);

            pst = cn.prepareStatement(insertInvoiceSQL);
            pst.setString(1, invoice.getInvoiceID());
            pst.setString(2, invoice.getInvoiceDate());
            pst.setString(3, invoice.getSalesID());
            pst.setString(4, invoice.getCarID());
            pst.setString(5, invoice.getCustID());
            int invoiceInserted = pst.executeUpdate();

            pst = cn.prepareStatement(updateCarStatusSQL);
            pst.setString(1, invoice.getCarID());
            int carUpdated = pst.executeUpdate();

            if (invoiceInserted > 0 && carUpdated > 0) {
                cn.commit();
                success = true;
            } else {
                cn.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cn != null) {
                    cn.rollback(); // Hoàn tác nếu có lỗi
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            closeResources();
        }
        return success;
    }

    public List<Invoice> getRecentInvoicesBySalesIDAndDate(String salesID, String invoiceDate) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM SalesInvoice WHERE salesID = ? AND invoiceDate = ? ORDER BY invoiceID DESC";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, salesID);
            pst.setString(2, invoiceDate);
            rs = pst.executeQuery();

            while (rs.next()) {
                invoices.add(new Invoice(
                        rs.getString("invoiceID"),
                        rs.getString("invoiceDate"),
                        rs.getString("salesID"),
                        rs.getString("carID"),
                        rs.getString("custID")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return invoices;
    }

    public List<Invoice> getInvoicesByCustomerID(String custID) {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT invoiceID, invoiceDate, salesID, carID FROM SalesInvoice WHERE custID = ?";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, custID);
            rs = pst.executeQuery();

            while (rs.next()) {
                invoices.add(new Invoice(
                        rs.getString("invoiceID"),
                        rs.getString("invoiceDate"),
                        rs.getString("salesID"),
                        rs.getString("carID"),
                        custID
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return invoices;
    }

    public InvoiceDetail getInvoiceDetail(String invoiceID) {
        InvoiceDetail invoiceDetail = null;
        String sql = "SELECT i.invoiceID, i.invoiceDate, "
                + "s.salesID, s.salesName, "
                + "c.carID, c.serialNumber, c.model, c.colour, c.year "
                + "FROM SalesInvoice i "
                + "JOIN SalesPerson s ON i.salesID = s.salesID "
                + "JOIN Cars c ON i.carID = c.carID "
                + "WHERE i.invoiceID = ?";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, invoiceID);
            rs = pst.executeQuery();

            if (rs.next()) {
                invoiceDetail = new InvoiceDetail(
                        rs.getString("invoiceID"),
                        rs.getString("invoiceDate"),
                        rs.getString("salesID"),
                        rs.getString("salesName"),
                        rs.getString("carID"),
                        rs.getString("serialNumber"),
                        rs.getString("model"),
                        rs.getString("colour"),
                        rs.getString("year")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return invoiceDetail;
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
