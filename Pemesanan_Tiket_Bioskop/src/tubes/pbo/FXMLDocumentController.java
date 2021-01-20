/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes.pbo;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableList;
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
import javax.swing.JOptionPane;
import static tubes.pbo.KONEKSI.getDataPelanggan;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TableView<Pelanggan> tabel_tiket;

    @FXML
    private TableColumn<Pelanggan, String> col_no_kursi;

    @FXML
    private TableColumn<Pelanggan, String> col_jenis_tiket;

    @FXML
    private TableColumn<Pelanggan, Integer> col_harga;

    @FXML
    private TableColumn<Pelanggan, Integer> col_jumlah;

    @FXML
    private TableColumn<Pelanggan, Integer> col_total;
    
    @FXML
    private TextField txt_kursi;

    @FXML
    private TextField txt_jenis_tiket;

    @FXML
    private TextField txt_harga;

    @FXML
    private TextField txt_jumlah_pesanan;

    @FXML
    private TextField txt_total_harga;
    
    ObservableList<Pelanggan>listM;
int index = -1;
Connection conn = null;
ResultSet res = null;
PreparedStatement pst = null;


public void tambah(){
     conn = KONEKSI.ConnectDb();
     String sql= "insert into bioskop (no_kursi, jenis_tiket, harga, jumlah_pesanan, total_harga)value(?,?,?,?,?)";
     try{
         pst= conn.prepareStatement(sql);
         pst.setString(1, txt_kursi.getText());
         pst.setString(2, txt_jenis_tiket.getText());
         pst.setInt(3,Integer.parseInt( txt_harga.getText()));
         pst.setInt(4,Integer.parseInt (txt_jumlah_pesanan.getText()));
         pst.setInt(5,Integer.parseInt ( txt_total_harga.getText()));
         pst.execute();
         
         JOptionPane.showMessageDialog(null, "Pesanan Berhasil di Tambahkan");
          Update();
            showdata();
     }
     catch (Exception e){
         JOptionPane.showMessageDialog(null, e);
     }
}

void getSelected (MouseEvent event){
    index = tabel_tiket.getSelectionModel().getSelectedIndex();
    if (index <= -1){
        return;
    }
    txt_kursi.setText(col_no_kursi.getCellData(index));
   txt_jenis_tiket.setText(col_jenis_tiket.getCellData(index));
   txt_harga.setText( col_harga.getCellData(index).toString());
   txt_jumlah_pesanan.setText( col_jumlah.getCellData(index).toString());
   txt_total_harga.setText( col_total.getCellData(index).toString());
    }
    


public void edit(){
            try{
            conn = KONEKSI.ConnectDb();
            String value1 = txt_kursi.getText();
            String value2 = txt_jenis_tiket.getText();
            String value3 = txt_harga.getText();
            String value4 = txt_jumlah_pesanan.getText();
            String value5 = txt_total_harga.getText();
            
            String sql = "update Data set no_kursi='" +value1+"',no_kursi='" +value1+"',jenis_tiket='" +value2+"',harga='" +value3+"',jumlah_pesanan='" +value4+"',total_harga='" +value5+"' where no_kursi ='"+value1+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");
            Update();
            showdata();
            
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       
    }
 public void Delete(){
        conn = KONEKSI.ConnectDb();
        String sql = "delete from Data where no_kursi = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_kursi.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "data behasil di hapus");
            showdata();
                }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       showdata();
               } 
    
     public void Update(){
col_no_kursi.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("no_kursi"));
col_jenis_tiket.setCellValueFactory(new PropertyValueFactory<Pelanggan,String>("jenis_tiket"));
col_harga.setCellValueFactory(new PropertyValueFactory<Pelanggan,Integer>("harga"));
col_jumlah.setCellValueFactory(new PropertyValueFactory<Pelanggan,Integer>("jumlah_pesanan"));
col_total.setCellValueFactory(new PropertyValueFactory<Pelanggan,Integer>("harga"));

    listM = KONEKSI.getDataPelanggan();
    tabel_tiket.setItems(listM);
    
     }
        public void showdata(){
         ObservableList<Pelanggan> list = getDataPelanggan();
         Update();
    }

}

    
    

