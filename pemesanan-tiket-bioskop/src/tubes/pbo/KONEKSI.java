/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes.pbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sntn_Prnwr
 */
public class KONEKSI {
    Connection conn =null;
    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_tiket","root","");
           JOptionPane.showMessageDialog(null,"koneksi berhasil");
           return conn;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
    public static ObservableList<Pelanggan>getDataPelanggan(){
   Connection Conn = ConnectDb();
   ObservableList<Pelanggan>List = FXCollections.observableArrayList();
       try{
            PreparedStatement ps = (PreparedStatement) Conn.prepareStatement("select * from bioskop");
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               List.add(new Pelanggan(rs.getString("no_kursi"),rs.getString("jenis_tiket"),rs.getString("harga"),rs.getString("jumlah_pesanan"),rs.getString("total_harga")));
           }
       }catch (Exception e){
           
       }
        return List;
    }
}
