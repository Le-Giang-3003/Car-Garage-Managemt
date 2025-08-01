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
import java.util.ArrayList;
import java.util.List;
import dto.Customer;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class CustomerDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    /**
     * Kiểm tra thông tin đăng nhập của khách hàng.
     *
     * @param custName Tên khách hàng.
     * @param phone Số điện thoại của khách hàng.
     * @return Đối tượng Customer nếu tìm thấy
     */
    public Customer checkLogin(String custName, String phone) {
        Customer c = null;
        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String sql = "SELECT custID, custName, phone, sex, cusAddress, status "
                        + "FROM Customer "
                        + "WHERE custName = ? AND phone = ? AND status = 'Active'";

                pst = cn.prepareStatement(sql);
                pst.setString(1, custName.trim());  // So khớp chính xác
                pst.setString(2, phone.trim());
                table = pst.executeQuery();

                if (table.next()) { // Chỉ lấy một kết quả
                    c = new Customer(table.getString("custID"),
                            table.getString("custName"),
                            table.getString("phone"),
                            table.getString("sex"),
                            table.getString("cusAddress"),
                            table.getString("status"));
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
        return c;
    }

    /**
     * Thêm một khách hàng mới vào database.
     *
     * @param customer Đối tượng Customer chứa thông tin khách hàng cần thêm.
     * @return true nếu thêm thành công, ngược lại trả về false.
     */
    public boolean insertCustomer(Customer customer) {
        boolean isInserted = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO Customer (custID, custName, phone, sex, cusAddress, status)"
                        + "VALUES (?, ?, ?, ?, ?, 'Active') ";

                pst = cn.prepareStatement(sql);

                pst.setString(1, customer.getCustID());
                pst.setString(2, customer.getCustName());
                pst.setString(3, customer.getPhone());
                pst.setString(4, customer.getSex());
                pst.setString(5, customer.getCusAddress());

                int rowInserted = pst.executeUpdate();
                if (rowInserted > 0) {
                    isInserted = true;
                }
            } else {
                System.out.println("Failed to connect to database!");
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isInserted;
    }

    /**
     * Lấy ra custID tiếp theo bằng cách tìm ID lớn nhất và tăng lên 1.
     *
     * @return ID tiếp theo.
     * @throws SQLException, ClassNotFoundException
     */
    public String getNextId() throws SQLException, ClassNotFoundException {
        String nextId = "11000"; // ID mặc định 
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT MAX(custID) "
                        + "FROM Customer";

                pst = cn.prepareStatement(sql);
                table = pst.executeQuery();

                if (table.next()) {
                    String maxId = table.getString(1); // Lấy ID lớn nhất hiện tại
                    if (maxId != null) {
                        int numericId = Integer.parseInt(maxId); // Chuyển về số nguyên
                        nextId = String.valueOf(numericId + 1); // Tăng lên 1 và chuyển lại thành String
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
        return nextId;
    }

    /**
     * Xuất toàn bộ danh sách khách hàng.
     *
     * @return Danh sách các Customer.
     */
    public List<Customer> showAllCustomer() {
        List<Customer> customerList = new ArrayList<>();

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Customer"; // Sửa lại để lấy giá trị boolean đúng
                pst = cn.prepareStatement(sql);
                table = pst.executeQuery();

                while (table.next()) {
                    customerList.add(new Customer(
                            table.getString("custID"),
                            table.getString("custName"),
                            table.getString("phone"),
                            table.getString("sex"),
                            table.getString("cusAddress"),
                            table.getString("status")));
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
        return customerList;
    }

    /**
     * Tìm kiếm khách hàng theo tên.
     *
     * @param custName Tên (hoặc một phần tên) của khách hàng cần tìm.
     * @return Danh sách các Customer phù hợp.
     */
    public List<Customer> searchCustomerByName(String custName) {
        List<Customer> customerList = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * "
                        + "FROM Customer "
                        + "WHERE custName "
                        + "LIKE ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + custName + "%");
                table = pst.executeQuery();

                while (table.next()) {
                    customerList.add(new Customer(table.getString("custID"),
                            table.getString("custName"),
                            table.getString("phone"),
                            table.getString("sex"),
                            table.getString("cusAddress"),
                            table.getString("status")));
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
        return customerList;
    }

    /**
     * Truy xuất thông tin của một khách hàng dựa trên custID.
     *
     * @param custID ID của khách hàng cần tìm.
     * @return Customer nếu tìm thấy, ngược lại trả về null.
     */
    public Customer getCustomerById(String custID) {
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT * "
                        + "FROM Customer "
                        + "WHERE custID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, custID);
                table = pst.executeQuery();
                while (table.next()) {
                    Customer c = new Customer(table.getString("custID"),
                            table.getString("custName"),
                            table.getString("phone"),
                            table.getString("sex"),
                            table.getString("cusAddress"),
                            table.getString("status"));
                    return c;
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
        return null;
    }

    /**
     * Cập nhật thông tin của một khách hàng.
     *
     * @param c Customer chứa thông tin mới của khách hàng.
     */
    public void updateCustomer(Customer c) {
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Customer "
                        + "SET custName = ?, phone = ?, sex = ?, cusAddress = ?, status = ? "
                        + "WHERE custID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, c.getCustName());
                pst.setString(2, c.getPhone());
                pst.setString(3, c.getSex());
                pst.setString(4, c.getCusAddress());
                pst.setString(5, c.getStatus());
                pst.setString(6, c.getCustID());
                pst.executeUpdate();
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
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Xóa một khách hàng khỏi database.
     *
     * @param custID ID của khách hàng cần xóa.
     */
    public void deleteCustomer(String custID) {
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE Customer SET status = 'Inactive' where custID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, custID);
                pst.executeUpdate();
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
    }

    /**
     * Kiểm tra xem số điện thoại đã tồn tại trong cơ sở dữ liệu chưa.
     *
     * @param phone Số điện thoại cần kiểm tra.
     * @return true nếu số điện thoại đã tồn tại, ngược lại trả về false.
     */
    public boolean checkDuplicate(String phone) {
        boolean isDuplicate = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) "
                        + "FROM Customer "
                        + "WHERE phone = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, phone);
                table = pst.executeQuery();

                if (table.next() && table.getInt(1) > 0) {
                    isDuplicate = true;
                }
            } else {
                System.out.println("Failed to connect to database!");
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

    public boolean checkPhone(String phone, String custID) {
        boolean exists = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM Customer WHERE phone = ? AND custID != ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, phone);
                pst.setString(2, custID);
                table = pst.executeQuery();
                if (table.next() && table.getInt(1) > 0) {
                    exists = true;
                }
            } else {
                System.out.println("Failed to connect to database!");
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
        return exists;
    }

}
