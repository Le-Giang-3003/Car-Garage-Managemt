package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dto.Car;
import util.DBUtils;

/**
 *
 * @author legiang300304
 */
public class CarDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet table = null;

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     *
     * @author Admin
     */
    private String normalizeString(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    public boolean checkCar(String serialNumber) {
        String sql = "SELECT TOP 1 1 FROM Cars WHERE LOWER(serialNumber) = LOWER(?)";
        try {

            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, normalizeString(serialNumber.trim()));
            table = pst.executeQuery();
            return table.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //CRUD
    //View(Read)
    public ArrayList<Car> getAllCar() {
        ArrayList<Car> list = new ArrayList<>();
        String sql = "SELECT carID, serialNumber, model, colour, year, price FROM Cars Where status = 'AVAILABLE'";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            table = pst.executeQuery();
            while (table.next()) {
                String carID = table.getString("carID");
                String serialNumber = table.getString("serialNumber");
                String model = table.getString("model");
                String colour = table.getString("colour");
                int year = table.getInt("year");
                double price = table.getDouble("price");
                list.add(new Car(carID, serialNumber, model, colour, year, price));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Create
    public boolean createCar(String serialNumber, String model, String colour, int year, double price) {
        if (checkCar(serialNumber)) {
            return false;
        } else {
            String sql = "INSERT INTO Cars (carID, serialNumber, model, colour, year, price)"
                    + "VALUES ((SELECT COALESCE(MAX(carID), 0) + 1 FROM Cars), ?, ?, ?, ?, ?)";
            try {
                cn = DBUtils.getConnection();
                pst = cn.prepareStatement(sql);
                pst.setString(1, serialNumber.toUpperCase());
                pst.setString(2, model);
                pst.setString(3, colour);
                pst.setInt(4, year);
                pst.setDouble(5, price);
                return pst.executeUpdate() > 0;
            } catch (Exception e) {
                System.err.println("Lỗi khi thêm Car: " + e.getMessage());
            }
        }

        return false;
    }

    public Car findCarByID(String carID) {
        Car car = null;
        String sql = "SELECT * FROM Cars WHERE status = 'AVAILABLE' AND carID = ?";

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, carID);
            table = pst.executeQuery();
            if (table.next()) {
                car = new Car(
                        table.getString("carID"),
                        table.getString("serialNumber"),
                        table.getString("model"),
                        table.getString("colour"),
                        table.getInt("year"),
                        table.getDouble("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public ArrayList<Car> searchCar(String serialNumber, String model, String year) {
        ArrayList<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM Cars WHERE status='AVAILABLE' AND 1=1 ";

        if (serialNumber != null && !serialNumber.isEmpty()) {
            sql += " AND serialNumber LIKE ?";
        }
        if (model != null && !model.isEmpty()) {
            sql += " AND model LIKE ?";
        }
        if (year != null && !year.isEmpty()) {
            sql += " AND year = ?";
        }

        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            int paramIndex = 1;
            if (serialNumber != null && !serialNumber.isEmpty()) {
                pst.setString(paramIndex++, "%" + serialNumber + "%");
            }
            if (model != null && !model.isEmpty()) {
                pst.setString(paramIndex++, "%" + model + "%");
            }
            if (year != null && !year.isEmpty()) {
                pst.setInt(paramIndex++, Integer.parseInt(year));
            }

            table = pst.executeQuery();
            while (table.next()) {
                list.add(new Car(table.getString("carID"), table.getString("serialNumber"),
                        table.getString("model"), table.getString("colour"), table.getInt("year"), table.getDouble("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Update
    public boolean updateCar(Car car) {
        String sql = "UPDATE Cars SET serialNumber = ?, model = ?, colour = ?, year = ?, price = ? WHERE status = 'AVAILABLE' AND carID = ?";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, car.getSerialNumber());
            pst.setString(2, car.getModel());
            pst.setString(3, car.getColour());
            pst.setInt(4, car.getYear());
            pst.setDouble(5, car.getPrice());
            pst.setString(6, car.getCarID());
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCar(String carID) {
        String sql = "UPDATE Cars SET status = 'DELETED' WHERE carID = ?";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, carID);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

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
        return false;
    }

    public ArrayList<String[]> getAllCarsByCustid(String custID) {
        ArrayList<String[]> list = new ArrayList<>();
        String sql = "select si.carID, c.model from SalesInvoice si join Cars c on si.carID=c.carID where c.status = 'SOLD' AND si.custID = ?";
        try {
            cn = DBUtils.getConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, custID.trim());
            table = pst.executeQuery();
            while (table.next()) {
                String carID = table.getString("carID");
                String model = table.getString("model");
                list.add(new String[]{carID, model});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
