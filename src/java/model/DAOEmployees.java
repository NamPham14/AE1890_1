package model;

import entity.Employees;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class DAOEmployees extends DBConnection {

    public int insertEmployees(Employees employee) {
        int n =0;
        String sql = "INSERT INTO [dbo].[Employees] "
                + "([LastName], [FirstName], [Title], [TitleOfCourtesy], [BirthDate], [HireDate], "
                + "[Address], [City], [Region], [PostalCode], [Country], [HomePhone], "
                + "[Extension], [Notes], [ReportsTo], [PhotoPath]) "
                + "VALUES ('" + employee.getLastName() + "','" + employee.getFirstName() + "','" + employee.getTitle() + "','" + employee.getTitleOfCourtesy() + "',"
                + "" + employee.getBirthDate() + "," + employee.getHireDate() + ",'" + employee.getAddress() + "','" + employee.getCity() + "','" + employee.getRegion() + "',"
                + "'" + employee.getPostalCode() + "','" + employee.getCountry() + "','" + employee.getHomePhone() + "','" + employee.getExtension() + "','" + employee.getNotes() + "',"
                + "" + employee.getReportsTo() + ",'" + employee.getPhotoPath() + "')";

            
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
        
    }

    public int addEmployees(Employees employee) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees] "
                + "([LastName], [FirstName], [Title], [TitleOfCourtesy], [BirthDate], [HireDate], "
                + "[Address], [City], [Region], [PostalCode], [Country], [HomePhone], "
                + "[Extension], [Notes], [ReportsTo], [PhotoPath]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, employee.getLastName());
            pre.setString(2, employee.getFirstName());
            pre.setString(3, employee.getTitle());
            pre.setString(4, employee.getTitleOfCourtesy());
            pre.setString(5, employee.getBirthDate());
            pre.setString(6, employee.getHireDate());
            pre.setString(7, employee.getAddress());
            pre.setString(8, employee.getCity());
            pre.setString(9, employee.getRegion());
            pre.setString(10, employee.getPostalCode());
            pre.setString(11, employee.getCountry());
            pre.setString(12, employee.getHomePhone());
            pre.setString(13, employee.getExtension());
            pre.setString(14, employee.getNotes());
            pre.setInt(15, employee.getReportsTo());
            pre.setString(16, employee.getPhotoPath());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }
    public void changEmployeesStatus(int empID, int newValue){
        String sql ="UPDATE Employees set EmployeesStatus= "+ newValue + " Where EmployeeID= "+empID;
        
        try {
            Statement state = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int deleteEmployees(int empID) {
        int n =0;
       String sqlCheck ="SELECT * from EmployeeTerritories WHERE EmployeeID =" +empID;
       ResultSet rs = getData(sqlCheck);
       
       
        try {
            if (rs.next()) {
                changEmployeesStatus(empID, 1);
                return 0;
                
            }
            String sql="DELETE FROM Employees WHERE EmployeeID=" +empID;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
      return n;
      
    }

    public int updateEmployees(Employees employee) {
        String sql = "UPDATE [dbo].[Employees] SET "
                + "[LastName] = ?, [FirstName] = ?, [Title] = ?, [TitleOfCourtesy] = ?, "
                + "[BirthDate] = ?, [HireDate] = ?, [Address] = ?, [City] = ?, "
                + "[Region] = ?, [PostalCode] = ?, [Country] = ?, [HomePhone] = ?, "
                + "[Extension] = ?, [Photo] = ?, [Notes] = ?, [ReportsTo] = ?, "
                + "[PhotoPath] = ? WHERE [EmployeeID] = ?";

        try (PreparedStatement prestate = conn.prepareStatement(sql)) {
            prestate.setString(1, employee.getLastName());
            prestate.setString(2, employee.getFirstName());
            prestate.setString(3, employee.getTitle());
            prestate.setString(4, employee.getTitleOfCourtesy());
            prestate.setString(5, employee.getBirthDate());
            prestate.setString(6, employee.getHireDate());
            prestate.setString(7, employee.getAddress());
            prestate.setString(8, employee.getCity());
            prestate.setString(9, employee.getRegion());
            prestate.setString(10, employee.getPostalCode());
            prestate.setString(11, employee.getCountry());
            prestate.setString(12, employee.getHomePhone());
            prestate.setString(13, employee.getExtension());
            prestate.setString(14, employee.getPhoto());
            prestate.setString(15, employee.getNotes());
            prestate.setInt(16, employee.getReportsTo());
            prestate.setString(17, employee.getPhotoPath());
            prestate.setInt(18, employee.getEmployeeID());
            return prestate.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public Vector<Employees> getEmployee(String sql) {
        Vector<Employees> vector = new Vector<>();
        try (Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs = state.executeQuery(sql)) {
            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String title = rs.getString("Title");
                String titleOfCourtesy = rs.getString("TitleOfCourtesy");
                String birthDate = rs.getString("BirthDate");
                String hireDate = rs.getString("HireDate");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String region = rs.getString("Region");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                String homePhone = rs.getString("HomePhone");
                String extension = rs.getString("Extension");
                String photo = rs.getString("Photo");
                String notes = rs.getString("Notes");
                int reportsTo = rs.getInt("ReportsTo");
                String photoPath = rs.getString("PhotoPath");

                Employees newEmployee = new Employees(employeeID, reportsTo, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region,
                        postalCode, country, homePhone, extension, photo, notes, photoPath);
                vector.add(newEmployee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOEmployees dao = new DAOEmployees();
//        Vector<Employees> vector = daoEmp.getEmployee("SELECT * FROM Employees");
//        for (Employees employee : vector) {
//            System.out.println(employee);
//        }
//
        int n = dao.insertEmployees(new Employees(1, "NAM", "Pham", null, null, 
                null, null, null, null, null, null, null, null, null, null, null));
        if (n < 0) {
            System.out.println("added");
        }

//  int n = dao.insertEmployees(new Employees(12, "NAM", "Pham", "ABC", "XYZ", 
//                "2004-04-01", "2025-04-01", "HN", "FPT", "NorthSidde", "100", "VN", "0868202662", "DONE", "nDONE", "ABCD"));
//     if(n<0){
//         System.out.println("added");
//     }
    }
}
