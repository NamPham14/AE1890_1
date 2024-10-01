/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerDemographics;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
/**
 *
 * @author Admin
 */
public class DAOCustomerDemographics extends DBConnection{
    
    public int insertCusDemoGrap(CustomerDemographics other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n"
                + "           ([CustomerTypeID]\n"
                + "           ,[CustomerDesc])\n"
                + "     VALUES\n"
                + "           ('" + other.getCustomerTypeID() + "','" + other.getCustomerDesc() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCusDemoGrap(CustomerDemographics other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n"
                + "           ([CustomerTypeID]\n"
                + "           ,[CustomerDesc])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCustomerTypeID());
            prestate.setString(2, other.getCustomerDesc());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }
    public int deleteCusDemoGrap(String customerTypeID){
        int n = 0;
        
        return n;
    }
    public int updateCusDemoGrap(CustomerDemographics other) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerDemographics]\n"
                + "   SET [CustomerDesc] = ?\n"
                + " WHERE CustomerTypeID = ?";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getCustomerDesc());
            prestate.setString(2, other.getCustomerTypeID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOCustomerDemographics dcg = new DAOCustomerDemographics();
        int x = dcg.updateCusDemoGrap(new CustomerDemographics("abc", "khoqua"));
        if (x > 0) {
            System.out.println("Succsess");
        }
    }
}
