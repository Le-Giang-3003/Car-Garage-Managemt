/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.SalePerson;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class SalePersonDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    /**
     * Kiểm tra thông tin đăng nhập của sale person
     *
     * @param salesName tên của sale person
     * @return sale person nếu tìm thấy
     * @throws ClassNotFoundException
     */
    public SalePerson checkLogin(String salesName) {
        SalePerson sp = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * "
                        + "FROM SalesPerson "
                        + "WHERE salesName LIKE ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + salesName + "%");
                table = pst.executeQuery();
                while (table.next()) {
                    sp = new SalePerson(table.getString("salesID"),
                            table.getString("salesName"),
                            table.getString("birthday"),
                            table.getString("sex"),
                            table.getString("salesAddress"));

                }
            } else {
                System.out.println("Failed to connect to database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (cn != null) {
                    cn.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (table != null) {
                    table.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sp;
    }
}
