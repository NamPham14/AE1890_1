/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.EmployeeTerritories;
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
public class DAOEmployeeTerritories extends DBConnection{
    public int insertEmployeeTerr(EmployeeTerritories other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID]\n"
                + "           ,[TerritoryID])\n"
                + "     VALUES\n"
                + "           (" + other.getEmployeeID() + ",'" + other.getTerritoryID() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int addEmployeeTerr(EmployeeTerritories other) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID]\n"
                + "           ,[TerritoryID])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setInt(1, other.getEmployeeID());
            prestate.setString(2, other.getTerritoryID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteEmployeeTerr(int employeeID, String territoryID) {
        int n = 0;
        String sql = "Delete from EmployeeTerritories where EmployeeID =" + employeeID + "and TerritoryID =" + territoryID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateEmployeeTerr(EmployeeTerritories other) {
        int n = 0;
        String sql = "UPDATE [dbo].[EmployeeTerritories]\n"
                + "   SET [TerritoryID] = ?\n"
                + " WHERE [EmployeeID] = ?";
        try {
            PreparedStatement prestate = conn.prepareStatement(sql);
            prestate.setString(1, other.getTerritoryID());
            prestate.setInt(2, other.getEmployeeID());
            n = prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public Vector<EmployeeTerritories> getEmployeeTerritorieses(String sql) {
        Vector<EmployeeTerritories> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String TerritoryID = rs.getString("TerritoryID");

                EmployeeTerritories newEmployeeTerritories = new EmployeeTerritories(EmployeeID, TerritoryID);
                vector.add(newEmployeeTerritories);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOEmployeeTerritories det = new DAOEmployeeTerritories();
        Vector<EmployeeTerritories> vector = det.getEmployeeTerritorieses("Select * from EmployeeTerritories");
        for (EmployeeTerritories employeeTerritories : vector) {
            System.out.println(employeeTerritories.toString());
        }
    }
}
