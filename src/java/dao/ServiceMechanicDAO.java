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
import dto.ServiceMechanic;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class ServiceMechanicDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    public List<ServiceMechanic> showAllServiceMechanic() {
        List<ServiceMechanic> serviceTicketlist = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT serviceTicketID, serviceID, mechanicID, hours, comment, rate FROM ServiceMechanic";
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();

            while (table.next()) {
                serviceTicketlist.add(new ServiceMechanic(table.getString("serviceTicketID"),
                        table.getString("serviceID"), table.getString("mechanicID"),
                        table.getInt("hours"), table.getString("comment"), table.getBigDecimal("rate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceTicketlist;
    }

    public void updateServiceMechanic(ServiceMechanic sm) {
        try {
            cn = DBUtils.getConnection();
            String sql = "UPDATE ServiceMechanic SET serviceID = ?, mechanicID = ?, hours = ?, comment = ?, rate = ? "
                    + "WHERE serviceTicketID = ? AND serviceID = ? AND mechanicID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, sm.getServiceID());
            pst.setString(2, sm.getMechanicID());
            pst.setInt(3, sm.getHours());
            pst.setString(4, sm.getComment());
            pst.setBigDecimal(5, sm.getRate());
            pst.setString(6, sm.getServiceTicketID());
            pst.setString(7, sm.getServiceID());
            pst.setString(8, sm.getMechanicID());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ServiceMechanic getServiceMechanicByID(String id) {
        try {
            cn = DBUtils.getConnection();
            String sql = "select * from ServiceMechanic where serviceTicketID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            table = pst.executeQuery();

            while (table.next()) {
                return new ServiceMechanic(table.getString("serviceTicketID"), table.getString("serviceID"),
                        table.getString("mechanicID"), table.getInt("hours"),
                        table.getString("comment"), table.getBigDecimal("rate"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public List<ServiceMechanic> viewServiceDetailByTicketID(String serviceTicketID) {
        List<ServiceMechanic> list = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();

            String sql = "SELECT sm.serviceTicketID, sm.serviceID, sm.mechanicID, sm.hours, sm.rate "
                    + "FROM ServiceMechanic sm "
                    + "WHERE sm.serviceTicketID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, serviceTicketID);
            table = pst.executeQuery();

            while (table.next()) {
                list.add(new ServiceMechanic(
                        table.getString("serviceTicketID"),
                        table.getString("serviceID"), // Truyền serviceID thay vì serviceName
                        table.getString("mechanicID"), // Truyền mechanicID thay vì mechanicName
                        table.getInt("hours"),
                        null, //table.getString("comment"),
                        table.getBigDecimal("rate")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
