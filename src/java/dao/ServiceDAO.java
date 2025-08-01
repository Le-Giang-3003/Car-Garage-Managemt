/*
 * To change this license header, choose License Headetable in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dto.Service;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class ServiceDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    public boolean insertServices(Service service) {

        boolean isInserted = false;
        try {
            cn = DBUtils.getConnection();
            String sql = "INSERT INTO Service (serviceID, serviceName, hourlyRate, status) VALUES (?, ?, ?, 'Active')";
            pst = cn.prepareStatement(sql);

            pst.setString(1, service.getServiceID());
            pst.setString(2, service.getServiceName());
            pst.setBigDecimal(3, service.getHourlyRate());

            int rowInserted = pst.executeUpdate();

            if (rowInserted > 0) {
                isInserted = true;
            }

        } catch (Exception e) {
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
        return isInserted;
    }

    public String getNextId() {
        String nextId = "1";
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT MAX(serviceID) FROM Service";

            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();

            if (table.next()) {
                String maxId = table.getString(1);
                if (maxId != null) {
                    int numericId = Integer.parseInt(maxId);
                    nextId = String.valueOf(numericId + 1);
                }
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
        return nextId;
    }

    public List<Service> showAllService() {
        List<Service> serviceList = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Service";
                pst = cn.prepareStatement(sql);
                table = pst.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        serviceList.add(new Service(table.getString("serviceId"),
                                table.getString("serviceName"),
                                table.getBigDecimal("hourlyRate"),
                                table.getString("status")));
                    }
                }

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
        return serviceList;
    }

    public void updateService(Service s) {
        try {
            cn = DBUtils.getConnection();
            String sql = "UPDATE Service SET serviceID = ?, serviceName = ?, hourlyRate = ?, status = ? WHERE serviceID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, s.getServiceID());
            pst.setString(2, s.getServiceName());
            pst.setBigDecimal(3, s.getHourlyRate());
            pst.setString(4, s.getStatus());
            pst.setString(5, s.getServiceID());
            pst.executeUpdate();
        } catch (Exception e) {
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
    }

    public void deleteService(String id) {
        try {
            cn = DBUtils.getConnection();
            String sql = "UPDATE Service SET status = 'Inactive' WHERE serviceID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
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
    }

    public Service getServiceById(String id) {
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT * FROM Service WHERE serviceID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            table = pst.executeQuery();

            while (table.next()) {
                return new Service(table.getString("serviceID"),
                        table.getString("serviceName"),
                        table.getBigDecimal("hourlyRate"), 
                        table.getString("status"));

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
        return null;
    }

    public boolean checkDuplicate(String serviceName) {
        boolean isDuplicate = false;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM Service WHERE TRIM(serviceName) = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, serviceName.trim());
            table = pst.executeQuery();

            if (table.next() && table.getInt(1) > 0) {
                isDuplicate = true;
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
        return isDuplicate;
    }

    public String getServiceNameByID(String serviceID) {
        String serviceName = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT serviceName FROM Service WHERE serviceID = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, serviceID);
            table = pst.executeQuery();

            if (table.next()) {
                serviceName = table.getString("serviceName");
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
        return serviceName;
    }
}
