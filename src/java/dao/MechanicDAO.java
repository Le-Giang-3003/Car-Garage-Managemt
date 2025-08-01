/*
 * To change this license header, choose License Headetable in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dto.Mechanic;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class MechanicDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    public Mechanic checkLogin(String name) {

        Mechanic m = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select mechanicID, mechanicName "
                        + "from Mechanic "
                        + "where mechanicName = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, name);

                table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String mechanicID = table.getString("mechanicID");
                        String mechanicName = table.getString("mechanicName");

                        m = new Mechanic(mechanicID, mechanicName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return m;
    }

    public String getMechanicNameByID(String mechanicID) {
        String mechanicName = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT mechanicName FROM Mechanic WHERE mechanicID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, mechanicID);
            table = pst.executeQuery();

            if (table.next()) {
                mechanicName = table.getString("mechanicName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (table != null) {
                    table.close();
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
        return mechanicName;
    }
}
