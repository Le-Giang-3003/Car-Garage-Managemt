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
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class ReportDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    public List<Object[]> getCarsSoldByYear() {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT YEAR(invoiceDate) AS Year, COUNT(si.carID) AS CarsSold "
                + "FROM SalesInvoice si "
                + "JOIN Cars c ON si.carID = c.carID "
                + "WHERE c.status = 'SOLD' "
                + "GROUP BY YEAR(invoiceDate) ORDER BY Year DESC";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                result.add(new Object[]{table.getInt("Year"), table.getInt("CarsSold")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Object[]> getRevenueByYear() {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT YEAR(si.invoiceDate) AS Year, SUM(c.price) AS Revenue "
                + "FROM SalesInvoice si "
                + "JOIN Cars c ON si.carID = c.carID "
                + "WHERE c.status = 'SOLD' "
                + "GROUP BY YEAR(si.invoiceDate) "
                + "ORDER BY Year DESC";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                result.add(new Object[]{table.getInt("Year"), table.getDouble("Revenue")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Object[]> getBestSellingCars() {
        List<Object[]> result = new ArrayList<>();
        String sql = "WITH SalesCount AS ("
                + "SELECT LOWER(c.model) AS model, COUNT(si.carID) AS Sold "
                + "FROM Cars c "
                + "JOIN SalesInvoice si ON c.carID = si.carID "
                + "WHERE c.status = 'SOLD' "
                + "GROUP BY LOWER(c.model)"
                + ") "
                + "SELECT model, Sold "
                + "FROM SalesCount "
                + "WHERE Sold = (SELECT MAX(Sold) FROM SalesCount) "
                + "ORDER BY Sold DESC";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                result.add(new Object[]{table.getString("model"), table.getInt("Sold")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Object[]> getBestUsedParts() {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT TOP 1 partName, SUM(numberUsed) AS Used FROM Parts "
                + "JOIN PartsUsed ON Parts.partID = PartsUsed.partID "
                + "GROUP BY partName ORDER BY Used DESC";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                result.add(new Object[]{table.getString("partName"), table.getInt("Used")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Object[]> getTopMechanics() {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT TOP 3 m.mechanicName, COUNT(sm.serviceTicketID) AS RepairCount "
                + "FROM ServiceMechanic sm "
                + "JOIN Mechanic m ON sm.mechanicID = m.mechanicID "
                + "GROUP BY m.mechanicName ORDER BY RepairCount DESC";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                result.add(new Object[]{table.getString("mechanicName"), table.getInt("RepairCount")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
