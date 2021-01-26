
package tubes.pbo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import static tubes.pbo.KONEKSI.getDataPelanggan;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableView<Pelanggan> tabel_tiket;
    @FXML
    private TableColumn<Pelanggan, String> col_no_kursi;
    @FXML
    private TableColumn<Pelanggan, String> col_jenis_tiket;
    @FXML
    private TableColumn<Pelanggan, String> col_harga;
    @FXML
    private TableColumn<Pelanggan, String> col_jumlah;
    @FXML
    private TableColumn<Pelanggan, String> col_total;
    @FXML
    private TextField txt_kursi;
    @FXML
    private Button button_tambah;
    @FXML
    private TextField txt_jenis_tiket;
    @FXML
    private TextField txt_harga;
    @FXML
    private TextField txt_jumlah_pesanan;
    @FXML
    private TextField txt_total_harga;
    @FXML
    private Button button_hapus;
    @FXML
    private Button button_edit;
    @FXML
    private Button button_cancel;
    
    ObservableList<Pelanggan>listM;
int index = -1;
Connection Conn = null;
ResultSet rs = null;
PreparedStatement ps = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showdata();  
    }    

    @FXML
    private void tambah_data(ActionEvent event) {
         Conn = KONEKSI.ConnectDb();
  String sql = "insert into bioskop (no_kursi,jenis_tiket,harga,jumlah_pesanan,total_harga)values(?,?,?,?,?)";
  try {
      ps = Conn.prepareCall(sql);
      ps.setString(1, txt_kursi.getText());
      ps.setString(2, txt_jenis_tiket.getText());
      ps.setString(3, txt_harga.getText());
      ps.setString(4, (txt_jumlah_pesanan.getText()));
      ps.setString(5, (txt_total_harga.getText()));
      ps.execute();
          
  }catch (Exception e){
      
  }
showdata();
    }

    @FXML
    private void edit(ActionEvent event) {
        try{
        Conn = KONEKSI.ConnectDb();
        String value1 = txt_kursi.getText();
        String value2 = txt_jenis_tiket.getText();
        String value3 = txt_harga.getText();
        String value4 = txt_jumlah_pesanan.getText();
        String value5 = txt_total_harga.getText();
        
        String sql = "update bioskop set jenis_tiket='" +value2+"',harga='" +value3+"',jumlah_pesanan='" +value4+"',total_harga='" +value5+"' where no_kursi ='"+value1+"' ";
            ps = Conn.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");

            showdata();
        
    }catch (Exception e){
        
    }
    }
    
public void showdata(){
    ObservableList<Pelanggan>list = getDataPelanggan();
        col_no_kursi.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("no_kursi") );
        col_jenis_tiket.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("jenis_tiket") );
        col_harga.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("harga") );
        col_jumlah.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("jumlah_pesanan") );
        col_total.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("total_harga") );
            
            listM = KONEKSI.getDataPelanggan();
            tabel_tiket.setItems(listM);

}

    @FXML
    private void hapus(ActionEvent event) {
         Conn = KONEKSI.ConnectDb();
        String sql = "delete from bioskop where no_kursi=?";
        try{
            ps = Conn.prepareStatement(sql);
            ps.setString(1,txt_kursi.getText());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Berhasil di Hapus");
            showdata();
         } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
                
         }        
    }
        @FXML
    private void handleMouseAction(MouseEvent event) {
    Pelanggan klik = tabel_tiket.getSelectionModel().getSelectedItem();
    txt_kursi.setText(klik.getNo_kursi());
    txt_jenis_tiket.setText(klik.getJenis_tiket());
    txt_harga.setText(klik.getHarga());
    txt_jumlah_pesanan.setText(klik.getJumlah_pesanan());
    txt_total_harga.setText(klik.getTotal_harga());

    }

}
