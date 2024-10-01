/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.OrderDetails;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class DAOOrderDetails extends DBConnection{
    public int insertOrderDetail(OrderDetails other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES\n"
                + "           (" + other.getOrderID() + " ," + other.getProductID() + " ," + other.getUnitPrice() + " ," + other.getQuantity() + " ," + other.getDiscount() + ")";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrderDetail(OrderDetails other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setInt(1, other.getOrderID());
            prestate.setInt(2, other.getProductID());
            prestate.setDouble(3, other.getUnitPrice());
            prestate.setInt(4, other.getQuantity());
            prestate.setDouble(5, other.getDiscount());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteOrderDetail(int orderID, int productID) {
        int n = 0;
        String sql = "Delete from Order where OrderID = " + orderID + "and ProductID = " + productID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n = 0;
    }

    public int updateOrderDetail(OrderDetails other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Order Details]\n"
                + "   SET      [UnitPrice] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Discount] = ?\n"
                + " WHERE  OrderID = ? AND  ProductID = ? ";

        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setDouble(1, other.getUnitPrice());
            prestate.setInt(2, other.getQuantity());
            prestate.setDouble(3, other.getDiscount());
            prestate.setInt(4, other.getOrderID());
            prestate.setInt(5, other.getProductID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<OrderDetails> getOrderDetails(String sql) {
        Vector<OrderDetails> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                OrderDetails newOrderDetail = new OrderDetails(OrderID, ProductID, UnitPrice, Quantity, Discount);
                vector.add(newOrderDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOOrderDetails dod = new DAOOrderDetails();
        Vector<OrderDetails> vector = dod.getOrderDetails("SELECT [OrderID]\n" +
"      ,[ProductID]\n" +
"      ,[UnitPrice]\n" +
"      ,[Quantity]\n" +
"      ,[Discount]\n" +
"  FROM [dbo].[Order Details]");
        for (OrderDetails orderDetails : vector) {
            System.out.println(orderDetails.toString());
        }
    }
}
