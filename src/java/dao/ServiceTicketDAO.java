/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ServiceTicket;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class ServiceTicketDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    public List<ServiceTicket> showAllServiceTicket() {
        List<ServiceTicket> serviceTicketList = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM ServiceTicket";
                pst = cn.prepareStatement(sql);
                table = pst.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        String ticketid = table.getString("serviceTicketID");
                        Date received = table.getDate("dateReceived");
                        Date returned = table.getDate("dateReturned");
                        String custid = table.getString("custID");
                        String carid = table.getString("carID");

                        ServiceTicket st = new ServiceTicket(ticketid, received, returned, custid, carid);
                        serviceTicketList.add(st);
                    }
                }
            } else {
                System.out.println("Failed to connect to database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (table != null) {
                    table.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceTicketList;
    }

    /**
     * Searches for service tickets based on the given criteria
     *
     * @param custID the customer ID to filter by
     * @param carID the car ID to filter by
     * @param dateReceived the date the ticket was received
     * @return a list of ServiceTicket matching the search criteria
     */
    public List<ServiceTicket> searchServiceTickets(String custID, String carID, java.sql.Date dateReceived) {
        List<ServiceTicket> serviceTicketList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ServiceTicket WHERE 1=1");

        if (custID != null && !custID.trim().isEmpty()) {
            sql.append(" AND custID = ?");
        }
        if (carID != null && !carID.trim().isEmpty()) {
            sql.append(" AND carID = ?");
        }
        if (dateReceived != null) {
            sql.append(" AND dateReceived = ?");
        }

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql.toString());
            int index = 1;
            if (custID != null && !custID.trim().isEmpty()) {
                pst.setString(index++, custID);
            }
            if (carID != null && !carID.trim().isEmpty()) {
                pst.setString(index++, carID);
            }
            if (dateReceived != null) {
                pst.setDate(index++, dateReceived);
            }

            table = pst.executeQuery();
            while (table.next()) {
                String ticketid = table.getString("serviceTicketID");
                java.sql.Date received = table.getDate("dateReceived");
                java.sql.Date returned = table.getDate("dateReturned");
                String customerID = table.getString("custID");
                String carIDResult = table.getString("carID");

                ServiceTicket st = new ServiceTicket(ticketid, received, returned, customerID, carIDResult);
                serviceTicketList.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceTicketList;
    }

    public List<ServiceTicket> getServiceTicketByCustID(String custID) {
        List<ServiceTicket> serviceTicketList = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT serviceTicketID, dateReceived, dateReturned, carID "
                        + "FROM ServiceTicket WHERE custID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, custID);
                table = pst.executeQuery();

                while (table.next()) {
                    String serviceticketid = table.getString("serviceTicketID");
                    Date datereceived = table.getDate("dateReceived");
                    Date datereturned = table.getDate("dateReturned");
                    String carid = table.getString("carID");

                    ServiceTicket st = new ServiceTicket(serviceticketid, datereceived, datereturned, null, carid);
                    serviceTicketList.add(st);
                }
            } else {
                System.out.println("Failed to connect to database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (table != null) {
                    table.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceTicketList;
    }

    public ArrayList<ServiceTicket> getAll() {
        ArrayList<ServiceTicket> list = new ArrayList<>();
        String sql = "SELECT * FROM ServiceTicket";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                String serviceTicketID = table.getString("serviceTicketID");
                Date dateReceived = table.getDate("dateReceived");
                Date dateReturned = table.getDate("dateReturned");
                String custID = table.getString("custID");
                String carID = table.getString("carID");
                list.add(new ServiceTicket(serviceTicketID, dateReceived, dateReturned, custID, carID));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
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

    public boolean createServiceTicket(Date dateReceived, Date dateReturned, String custID, String carID) {
        String sql;
        sql = "INSERT INTO ServiceTicket (serviceTicketID ,dateReceived, dateReturned, custID, carID)"
                + "VALUES ((SELECT COALESCE(MAX(serviceTicketID), 0)+1 "
                + "FROM ServiceTicket), ?, ?, ?, ?)";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setDate(1, dateReceived);

            pst.setDate(1, dateReceived);
            pst.setDate(2, dateReturned);
            pst.setString(3, custID);
            pst.setString(4, carID);

            return pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
        return false;
    }

}
