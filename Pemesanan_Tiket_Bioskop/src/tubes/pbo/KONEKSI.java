
package tubes.pbo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class KONEKSI {
Connection conn =null;
   public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_tiket/");
          JOptionPane.showMessageDialog(null,"koneksi berhasil");
           return conn;
        }catch (Exception e){
           JOptionPane.showMessageDialog(null,e);
         return null;
       }
   }

    
    public static ObservableList<Pelanggan>getDataPelanggan(){
   Connection conn = ConnectDb();
   ObservableList<Pelanggan>List = FXCollections.observableArrayList();
       try{
           PreparedStatement ps = conn.clientPrepareStatement("select from bioskop");
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               List.add(new Pelanggan(rs.getString("no_kursi"),rs.getString("jenis_tiket"),rs.getInt("harga"),rs.getInt("jumlah_pesanan"),rs.getInt("total_harga")));
           }
       }catch (Exception e){
           
       }
        return null;
    }
}