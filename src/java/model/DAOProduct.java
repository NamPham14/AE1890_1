/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAOProduct extends DBConnection {
    // DBConnection dbconn = new DBConnection();

   public int insertProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES('" + pro.getProductName() + "'," + pro.getSupplierID()
                + "," + pro.getCategoryID() + ",'" + pro.getQuantityPerUnit()
                + "'," + pro.getUnitPrice() + "," + pro.getUnitsInStock()
                + "," + pro.getUnitsOnOrder() + "," + pro.getReorderLevel()
                + "," + (pro.isDiscontinued() == true ? 1 : 0) + ")";
        System.out.println(sql);
        try {
            //import java.sql.Statement;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            //  ? --> field , index start 1 : first : ProductName; second:  SupplierID ...
            PreparedStatement pre = conn.prepareStatement(sql);
//            set value/parameter for ?
//           pre.setDataType(index?,para); DataType is datatype of field. 
//                   Ex: ProductName String    SupplierID: int     
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public void changeDiscontinued(int pid , int newValue){
        String sql= "update Products set Discontinued = "+ newValue+ " where ProductID=" +pid;
        try {
            Statement state = conn.createStatement();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int deleteProduct(int pid) {
        int n = 0;
        
        String sqlCheck ="SELECT * FROM [Order Details] WHERE ProductID= " + pid;
       
        ResultSet rs = getData(sqlCheck);
     
        
        try {
              if(rs.next()){
                  changeDiscontinued(pid, 1);
                  return 0;
            
        }
              
           String sql ="DELETE FROM Products WHERE ProductID=" + pid;

            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
        
    }
    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ? ,[SupplierID] = ?,[CategoryID] = ?,[QuantityPerUnit] = ?,[UnitPrice] = ?\n"
                + "      ,[UnitsInStock] = ?,[UnitsOnOrder] = ?,[ReorderLevel] = ?,[Discontinued] = ?\n"
                + " WHERE ProductID=?";
        try {
            //  ? --> field , index start 1 : first : ProductName; second:  SupplierID ...
            PreparedStatement pre = conn.prepareStatement(sql);
//            set value/parameter for ?
//           pre.setDataType(index?,para); DataType is datatype of field. 
//                   Ex: ProductName String    SupplierID: int     
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            pre.setInt(10, pro.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Product> getProduct(String sql) {
        Vector<Product> vector = new Vector<Product>();
        try {
            //  note: in case login must be used PreparedStatement
            //default :ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ProductID= rs.getInt(1);
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt("SupplierID");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = (rs.getInt("Discontinued") == 1 ? true : false);
                Product pro = new Product(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                vector.add(pro);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
        
    }
    
    public static void main(String[] args) {
       DAOProduct dao = new DAOProduct();
//        int n = dao.insertProduct(new Product("PName", 1, 1, "Quan1", 100.1, 10, 11, 1, true));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
//        int n = dao.addProduct(new Product("PName", 1, 1, "Quan1", 100.1, 10, 11, 1, true));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
//       int n = dao.updateProduct(new Product(14,"new Product", 1, 1, "Quan1", 100.1, 10, 11, 1, true));
//        if (n > 0) {
//            System.out.println("update");
//        }
//        int n = dao.deleteProduct(70);
//        if (n > 0) {
//            System.out.println("success");
//        }
  Vector<Product> vector = dao.getProduct("Select * from Products");
        for (Product product : vector) {
            System.out.println(product);
            
        }
        
    }
}
