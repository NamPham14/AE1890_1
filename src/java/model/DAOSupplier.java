/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Suppliers;
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
public class DAOSupplier extends DBConnection {

    public int insertSupplier(Suppliers other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES\n"
                + "           ('" + other.getCompanyName() + "' ,'" + other.getContactName() + "','" + other.getContactTitle() + "',"
                + " '" + other.getAddress() + "' ,'" + other.getCity() + "'   ,'" + other.getRegion() + "' ,"
                + "'" + other.getPostalCode() + "' ,'" + other.getCountry() + "' ,'" + other.getPostalCode() + "' ,'" + other.getFax() + "' ,'" + other.getHomePage() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addSupplier(Suppliers other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,? )";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCompanyName());
            prestate.setString(2, other.getContactName());
            prestate.setString(3, other.getContactTitle());
            prestate.setString(4, other.getAddress());
            prestate.setString(5, other.getCity());
            prestate.setString(6, other.getRegion());
            prestate.setString(7, other.getPostalCode());
            prestate.setString(8, other.getCountry());

            prestate.setString(10, other.getFax());
            prestate.setString(11, other.getHomePage());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteSupplier(int SupplierID) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Suppliers] WHERE SupplierID =" + SupplierID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSupplier(Suppliers other) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Fax] = ?\n"
                + "      ,[HomePage] = ?\n"
                + " WHERE [SupplierID] = ? ";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCompanyName());
            prestate.setString(2, other.getContactName());
            prestate.setString(3, other.getContactTitle());
            prestate.setString(4, other.getAddress());
            prestate.setString(5, other.getCity());
            prestate.setString(6, other.getRegion());
            prestate.setString(7, other.getPostalCode());
            prestate.setString(8, other.getCountry());
            
            prestate.setString(10, other.getFax());
            prestate.setString(11, other.getHomePage());
            prestate.setInt(12, other.getSupplierID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Suppliers> getSuppliers(String sql) {
        Vector<Suppliers> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);

            
            while (rs.next()) {
                int SupplierID = rs.getInt("SupplierID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String Region = rs.getString("Region");
                String PostalCode = rs.getString("PostalCode");
                String Country = rs.getString("Country");
                String Phone = rs.getString("Phone");
                String Fax = rs.getString("Fax");
                String HomePage = rs.getString("HomePage");

                Suppliers newsupplier = new Suppliers(SupplierID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Fax, HomePage);
                
           vector.add(newsupplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOSupplier dsl = new DAOSupplier();
        Vector<Suppliers> vector = dsl.getSuppliers("SELECT * FROM Suppliers");

         
        for (Suppliers supplier : vector) {
            System.out.println(supplier ) ;
        }
    }

}
