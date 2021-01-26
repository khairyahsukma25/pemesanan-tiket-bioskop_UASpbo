
package tubes.pbo;

public class Pelanggan{
String no_kursi;
String jenis_tiket;
String harga;
String jumlah_pesanan;
String total_harga;

    public String getNo_kursi() {
        return no_kursi;
    }

    public void setNo_kursi(String no_kursi) {
        this.no_kursi = no_kursi;
    }

    public String getJenis_tiket() {
        return jenis_tiket;
    }

    public void setJenis_tiket(String jenis_tiket) {
        this.jenis_tiket = jenis_tiket;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah_pesanan() {
        return jumlah_pesanan;
    }

    public void setJumlah_pesanan(String jumlah_pesanan) {
        this.jumlah_pesanan = jumlah_pesanan;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }

    public Pelanggan(String no_kursi, String jenis_tiket, String harga, String jumlah_pesanan, String total_harga) {
        this.no_kursi = no_kursi;
        this.jenis_tiket = jenis_tiket;
        this.harga = harga;
        this.jumlah_pesanan = jumlah_pesanan;
        this.total_harga = total_harga;
    }

}

