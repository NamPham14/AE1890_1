/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import entity.Shipper;
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
public class DAOShipper extends DBConnection {

    public int insertShippers(Shipper other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "           ('" + other.getCompanyName() + "' ,'" + other.getPhone() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int addShippers(Shipper other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCompanyName());
            prestate.setString(2, other.getPhone());

            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public void changeStatus(int shipperID, int newValue ){
        
        String sql="update [dbo].[Shippers] set SHipperStatus ="+ newValue +"Where ShipperID="+shipperID;
        
        try {
            Statement state = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public int deleteShippers(int shipperID) {
        int n = 0;
        String sqlCheck ="Select * FROM [Orders] Where ShipVia="+shipperID;
        ResultSet rs = getData(sqlCheck);
        
        try {
            if (rs.next()) {
                changeStatus(shipperID, 1);
                return 0;
                
            }
            String sql = "Delete from Shippers where ShipperID = " + shipperID;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateShippers(Shipper other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Shippers]\n"
                + "   SET [CompanyName] = ?,[Phone] = ?\n"
                + " WHERE [ShipperID] = ?";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCompanyName());
            prestate.setString(2, other.getPhone());
            prestate.setInt(3, other.getShipperID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Shipper> getShippers(String sql) {
        Vector<Shipper> vector = new Vector<>();
        Statement state;
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ShipperID = rs.getInt("ShipperID");
                String CompanyName = rs.getString("CompanyName");
                String Phone = rs.getString("Phone");
                Shipper newshipper = new Shipper(ShipperID, CompanyName, Phone);
                vector.add(newshipper);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
 
    public static void main(String[] args) {
        DAOShipper dao = new DAOShipper();
//       Vector<Shipper> vector = dao.getShippers("Select * from Shippers");
//        for (Shipper shippers : vector) {
//            System.out.println(shippers.toString());
//            
//        } 
 int x = dao.addShippers(new Shipper("FPT", "123232131"));
 if(x>0){
     System.out.println("added");
 }

    }
}
