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
import dto.Part;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class PartDAO {

    Connection cn = null;
    PreparedStatement pst = null;

    private String normalizeString(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    public boolean checkExistence(String name) {
        String sql = "SELECT TOP 1 1 FROM Parts WHERE deleted = 'FALSE' AND LOWER(partName) = LOWER(?)";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, normalizeString(name.trim()));
            try (ResultSet table = pst.executeQuery()) {
                return table.next();
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra partName: " + e.getMessage());
        }
        return false;
    }

    public boolean insertPart(String partName, double purPrice, double retPrice) {
        String sql = "INSERT INTO Parts (partID, partName, purchasePrice, retailPrice) "
                + "VALUES ((SELECT COALESCE(MAX(partID), 0) + 1 FROM Parts), ?, ?, ?)";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, partName);
            pst.setDouble(2, purPrice);
            pst.setDouble(3, retPrice);

            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm Part: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Part> viewAllPart() {
        ArrayList<Part> list = new ArrayList<>();
        String sql = "SELECT partID, partName, purchasePrice, retailPrice, deleted FROM Parts";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            while (table.next()) {
                String partID = table.getString("partID");
                String partName = table.getString("partName");
                double purchasePrice = table.getDouble("purchasePrice");
                double retailPrice = table.getDouble("retailPrice");
                boolean deleted = table.getBoolean("deleted");
                Part p = new Part(partID, partName, purchasePrice, retailPrice,deleted);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    nhận 1 keyword và 1 boolean 
    -> Name => false || ID => true
    dùng cho tìm kiếm đối tượng tùy theo tham số
     */
    public ArrayList<Part> findPart(String keyword, boolean searchByID) {
        ArrayList<Part> list = new ArrayList<>();

        String sql = searchByID
                ? "SELECT partID, partName, purchasePrice, retailPrice, deleted FROM Parts WHERE partID = ?"
                : "SELECT partID, partName, purchasePrice, retailPrice, deleted FROM Parts WHERE LOWER(partName) LIKE ?";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            if (searchByID) {
                pst.setString(1, keyword);
            } else {
                pst.setString(1, "%" + keyword + "%");
            }

            try (ResultSet table = pst.executeQuery()) {
                while (table.next()) {
                    list.add(new Part(
                            table.getString("partID"),
                            table.getString("partName"),
                            table.getDouble("purchasePrice"),
                            table.getDouble("retailPrice"),
                            table.getBoolean("deleted")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updatePart(Part part) {
        String sql = "UPDATE Parts SET partName = ?, purchasePrice = ?, retailPrice = ?, deleted = ? WHERE partID = ?";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, part.getPartName());
            pst.setDouble(2, part.getPurPrice());
            pst.setDouble(3, part.getRetPrice());
            pst.setString(4, part.isDeleted() ? "TRUE" : "FALSE");
            pst.setString(5, part.getPartID());
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePart(String partID) {
        String sql = "UPDATE Parts SET deleted = 'TRUE' WHERE partID = ?";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, partID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
