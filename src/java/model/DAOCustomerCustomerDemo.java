/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
/**
 *
 * @author Admin
 */
public class DAOCustomerCustomerDemo extends DBConnection {

    public int inserCustomerDemo(CustomerCustomerDemo pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES('"+pro.getCustomerID()+"','"+pro.getCustomerTypeID()+"')";
               
        System.out.println(sql);
        
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        return n;
        
        
    }
     public int addCustomerDemo(CustomerCustomerDemo pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES "
                + "           (?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, pro.getCustomerID());
            prestate.setString(2, pro.getCustomerTypeID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
      public int updateCustomerDemo(CustomerCustomerDemo pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerCustomerDemo]\n"
                + "   SET \n"
                + "      [CustomerTypeID] = ?\n"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, pro.getCustomerTypeID());
            prestate.setString(2, pro.getCustomerID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    
    public static void main(String[] args) {
      DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();
//        int n =dao.inserCustomerDemo(new CustomerCustomerDemo("ALFKI", "ABC"));
//        if(n>0){
//            System.out.println("inserted");
//        }
        
//  int x = dao.addCustomerDemo(new CustomerCustomerDemo("ALFKI", "abc"));
//       if (x > 0) {
//           System.out.println("Add success");
//        }
       int x = dao.updateCustomerDemo(new CustomerCustomerDemo("ANATR", "abc"));
        if (x > 0) {
            System.out.println("update success");
        }
        
    }

}
