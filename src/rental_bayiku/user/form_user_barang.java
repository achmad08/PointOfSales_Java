package rental_bayiku.user;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class form_user_barang extends javax.swing.JFrame {

    DefaultTableModel tabel = new DefaultTableModel();
    DefaultTableModel tabel2 = new DefaultTableModel();

    public form_user_barang() {
        initComponents();

        disableForm();
        getTable();
        lebarKolom();
        loadData();
    }

    private void disableForm() {
        Simpan1.setEnabled(true);
        Tambah.setEnabled(true);
        Edit.setEnabled(true);
        Hapus.setEnabled(true);
        Cari.setEnabled(true); 
        kd_barang.setEditable(false);
        nama_barang.setEditable(false);
        merek_brg.setEditable(false);
        status_brg.setEditable(false);
        jumlah_brg.setEditable(false);
        jTextField7.setEditable(false);
        harga_brg.setEditable(false);
        masuk_brg.setEditable(false);
        buat_brg.setEnabled(false);
        jenis_brg.setEnabled(false);
        batal.setVisible(false);
        Simpan2.setVisible(false);
        Simpan1.setVisible(false);
        kd_barang.setText("");
        nama_barang.setText("");
        merek_brg.setText("");
        status_brg.setText("");
        jumlah_brg.setText("");
        jTextField7.setText("");
        harga_brg.setText("");
        masuk_brg.setText("");
        buat_brg.setSelectedItem("");
        jenis_brg.setSelectedItem("");
    }

    private void getTable() {
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jenis Barang");
        tabel.addColumn("Merek Barang");
        tabel.addColumn("Status Barang");
        tabel.addColumn("Jumlah");
        tabel.addColumn("Tahun Buat");
        tabel.addColumn("Kondisi Barang");
        tabel.addColumn("Harga");
        tabel.addColumn("Tanggal Masuk");

        Table_brg.setModel(tabel);
    }

    private void lebarKolom() {
        TableColumn lebar_kolom;

        Table_brg.setAutoResizeMode(Table_brg.AUTO_RESIZE_OFF);

        lebar_kolom = Table_brg.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(3);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(4);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(5);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(6);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(7);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(8);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = Table_brg.getColumnModel().getColumn(9);
        lebar_kolom.setPreferredWidth(100);

    }

    private void loadData() {
        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select * from t_inventaris_barang";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[10];
                getO[0] = line_result.getString("kode_barang");
                getO[1] = line_result.getString("nama_barang");
                getO[2] = line_result.getString("jenis_barang");
                getO[3] = line_result.getString("merek_barang");
                getO[4] = line_result.getString("status_barang");
                getO[5] = line_result.getString("jumlah");
                getO[6] = line_result.getString("tahun_buat");
                getO[7] = line_result.getString("kondisi_barang");
                getO[8] = line_result.getString("harga");
                getO[9] = line_result.getString("tgl_masuk");
                

                tabel.addRow(getO);
            }
            line_result.close();
            line_statemen.close();
        } catch (Exception e) {
        }
    }

    private void simpan() {

        String kode_barang = kd_barang.getText();
        String nm_barang = nama_barang.getText();
        String mrk_barang = merek_brg.getText();
        String status = status_brg.getText();
        String jmlh_barang = jumlah_brg.getText();
        String kondisi_barang = jTextField7.getText();
        String harga = harga_brg.getText();
        String tgl_msk = masuk_brg.getText();
        String tgl = buat_brg.getSelectedItem().toString();
        String jenis = jenis_brg.getSelectedItem().toString();
        try {
            
            Connection sql_query_koneksi = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement sql_statement = sql_query_koneksi.createStatement();
            String query_input = "insert into t_inventaris_barang values ('" + kode_barang + "','" + nm_barang + "','"+ jenis + "','" + mrk_barang + "','" + status + "','" + jmlh_barang + "','" + tgl + "','" + kondisi_barang + "','" +  harga + "','" + tgl_msk + "')";
            sql_statement.executeUpdate(query_input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Memasukkan Data !", "Peringatan", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private void ubah_data() {
        try {
            String kode_barang = kd_barang.getText();
            String nm_barang = nama_barang.getText();
            String mrk_barang = merek_brg.getText();
            String sts_barang = status_brg.getText();
            String jml_barang = jumlah_brg.getText();
            String kondisi_barang = jTextField7.getText();
            String harga = harga_brg.getText();
            String tgl_masuk = masuk_brg.getText();
            String tgl_buat = buat_brg.getSelectedItem().toString();
            String jenis = jenis_brg.getSelectedItem().toString();

            Connection sql_query_koneksi = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            String sql_query_update = "update t_inventaris_barang set nama_barang='" + nm_barang + "', jenis_barang='" + jenis + "', merek_barang='" + mrk_barang + "',status_barang='" + sts_barang + "',jumlah = "+ jml_barang+ " ,tahun_buat='" + tgl_buat + "',kondisi_barang ='" + kondisi_barang +  "', harga ='" + harga +"', tgl_masuk ='" + tgl_masuk +  "' where kode_barang='" + kode_barang + "'";
            com.mysql.jdbc.PreparedStatement query_prepare = (PreparedStatement) sql_query_koneksi.prepareStatement(sql_query_update);
            query_prepare.execute();

        } catch (Exception e) {
        }
    }

    private void hapus() {
        int s_row = Table_brg.getSelectedRow();
        if (s_row == -1) {
            return;
        }
        String get_dataDelete = (String) Table_brg.getValueAt(s_row, 0);
        try {
            Connection sql_query_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            String query_delete = "delete from t_inventaris_barang where kode_barang=?";
            PreparedStatement sql_statemen = (PreparedStatement) sql_query_konek.prepareStatement(query_delete);
            sql_statemen.setString(1, get_dataDelete);
            sql_statemen.executeUpdate();
            sql_statemen.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tidak bisa menghapus data", "Hapus Data", JOptionPane.WARNING_MESSAGE);
        } finally {
            loadData();
        }
    }

    public void AutoNumber() {
        try {

            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select max(right(kode_barang,4)) as no_urut from t_inventaris_barang";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            if (line_result.first() == false) {
                kd_barang.setText("B0001");
            } else {
                line_result.last();
                int no = line_result.getInt(1) + 1;
                String nomor = String.valueOf(no);
                int oto = nomor.length();
                if (no < 10){
                for (int i = 0; i < 2 - oto; i++) {
                    nomor = "000" + nomor;
                }
                }
                if (no >=10 && no <100){
                for (int i = 0; i < 3 - oto; i++) {
                    nomor = "00" + nomor;
                }
                }
                if (no >=100 && no <1000){
                for (int i = 0; i < 4 - oto; i++) {
                    nomor = "0" + nomor;
                }
                }
                if (no >=1000  && no <9999){
                for (int i = 0; i < 4 - oto; i++) {
                    nomor = "B" + nomor;
                }
                }
                kd_barang.setText("B" + nomor);
            }
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }

    private void klik() {
        String getKode = Table_brg.getValueAt(Table_brg.getSelectedRow(), 0).toString();
        String getNama = Table_brg.getValueAt(Table_brg.getSelectedRow(), 1).toString();
        String getJenis = Table_brg.getValueAt(Table_brg.getSelectedRow(), 2).toString();
        String getMerek = Table_brg.getValueAt(Table_brg.getSelectedRow(), 3).toString();
        String getStatus = Table_brg.getValueAt(Table_brg.getSelectedRow(), 4).toString();
        String getJumlah = Table_brg.getValueAt(Table_brg.getSelectedRow(), 5).toString();
        String getTahun = Table_brg.getValueAt(Table_brg.getSelectedRow(), 6).toString();
        String getKondisi = Table_brg.getValueAt(Table_brg.getSelectedRow(), 7).toString();
        String getHarga = Table_brg.getValueAt(Table_brg.getSelectedRow(), 8).toString();
        String getTanggal = Table_brg.getValueAt(Table_brg.getSelectedRow(), 9).toString();

        kd_barang.setText(getKode);
        nama_barang.setText(getNama);
        jenis_brg.setSelectedItem(getJenis);
        merek_brg.setText(getMerek);
        status_brg.setText(getStatus);
        jumlah_brg.setText(getJumlah);
        buat_brg.setSelectedItem(getTahun);
        jTextField7.setText(getKondisi);
        harga_brg.setText(getHarga);
        masuk_brg.setText(getTanggal);

    }

    public void cleanTable(){
    DefaultTableModel tabel = (DefaultTableModel) Table_brg.getModel(); 
        while(tabel.getRowCount() > 0){
         tabel.removeRow(0);
        
        }      
    }
    
    public void searchdata(){
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table_brg = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        kd_barang = new javax.swing.JTextField();
        nama_barang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        merek_brg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        status_brg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jumlah_brg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        harga_brg = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        masuk_brg = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Tambah = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Hapus = new javax.swing.JButton();
        Simpan1 = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        kembali_brg = new javax.swing.JButton();
        Simpan2 = new javax.swing.JButton();
        buat_brg = new javax.swing.JComboBox();
        refresh = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jenis_brg = new javax.swing.JComboBox<>();
        Cari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(5000, 6000));

        Table_brg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_brg.setPreferredSize(new java.awt.Dimension(1000, 2000));
        jScrollPane1.setViewportView(Table_brg);

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Nama");

        merek_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                merek_brgActionPerformed(evt);
            }
        });

        jLabel3.setText("Merek");

        status_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status_brgActionPerformed(evt);
            }
        });

        jLabel4.setText("Status");

        jLabel5.setText("Jumlah Barang");

        jLabel6.setText("Tahun Pembuatan");

        jLabel7.setText("Kondisi Barang");

        jLabel9.setText("Harga");

        jLabel10.setText("Tanggal Masuk");

        Tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Add-sheet-32.png"))); // NOI18N
        Tambah.setText("New");
        Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahActionPerformed(evt);
            }
        });

        Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Edit-32.png"))); // NOI18N
        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/delete-32.png"))); // NOI18N
        Hapus.setText("Delete");
        Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusActionPerformed(evt);
            }
        });

        Simpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Save-32.png"))); // NOI18N
        Simpan1.setText("Simpan");
        Simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan1ActionPerformed(evt);
            }
        });

        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Cancel-32.png"))); // NOI18N
        batal.setText("Batal");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        kembali_brg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Back-32.png"))); // NOI18N
        kembali_brg.setText("Kembali");
        kembali_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembali_brgActionPerformed(evt);
            }
        });

        Simpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Save-32.png"))); // NOI18N
        Simpan2.setText("Simpan");
        Simpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Simpan2ActionPerformed(evt);
            }
        });

        buat_brg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" }));

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Refresh-32.png"))); // NOI18N
        refresh.setText("REFRESH ");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel8.setText("Jenis Barang ");

        jenis_brg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baby Box", "Baby Carrier", "Baby Seats ", "Bouncer", "Breast Pump", "Buku ", "Car Seat", "Mainan", "Pakaian & Kostum", "Perlengkapan Musim Dingin", "Sepatu ", "Stoller", "Tas & Koper ", "Lain - lain" }));

        Cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/search.jpg"))); // NOI18N
        Cari.setText("CARI");
        Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(kembali_brg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel8)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(harga_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jenis_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(nama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                                .addComponent(masuk_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(refresh)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(merek_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(status_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jumlah_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buat_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Tambah, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                        .addComponent(Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(Hapus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Simpan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Simpan2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tambah))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Edit)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(kd_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(harga_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(masuk_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jenis_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Hapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Simpan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Simpan2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(merek_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(status_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jumlah_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(buat_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kembali_brg)
                            .addComponent(batal))
                        .addGap(29, 29, 29)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        setSize(new java.awt.Dimension(900, 550));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kembali_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembali_brgActionPerformed
        this.dispose();
        new form_user_utama_1().setVisible(true);
    }//GEN-LAST:event_kembali_brgActionPerformed

    private void status_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_status_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_status_brgActionPerformed

    private void Simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan1ActionPerformed
        if (kd_barang.getText().equals("") || nama_barang.getText().equals("") || merek_brg.getText().equals("") || status_brg.getText().equals("") || jumlah_brg.getText().equals("") || jTextField7.getText().equals("") ||  harga_brg.getText().equals("") || masuk_brg.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Data tidak boleh ada yang kosong", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        } else {
            simpan();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan !");
            AutoNumber();
        }
        loadData();
        nama_barang.setText("");
        merek_brg.setText("");
        status_brg.setText("");
        jumlah_brg.setText("");
        jTextField7.setText("");
        harga_brg.setText("");
        masuk_brg.setText("");
    }//GEN-LAST:event_Simpan1ActionPerformed

    private void TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahActionPerformed
        Tambah.setEnabled(true);
        Edit.setEnabled(false);
        Hapus.setEnabled(false);
        nama_barang.setEditable(true);
        merek_brg.setEditable(true);
        status_brg.setEditable(true);
        jumlah_brg.setEditable(true);
        jTextField7.setEditable(true);
        harga_brg.setEditable(true);
        masuk_brg.setEditable(true);
        buat_brg.setEnabled(true);
        jenis_brg.setEnabled(true);
        batal.setVisible(true);
        Simpan1.setVisible(true);
        AutoNumber();
    }//GEN-LAST:event_TambahActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        disableForm();
        kd_barang.setText("");
    }//GEN-LAST:event_batalActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        try {
            klik();
            Simpan1.setEnabled(false);
            Tambah.setEnabled(false);
            Hapus.setEnabled(false);
            nama_barang.setEditable(true);
            merek_brg.setEditable(true);
            status_brg.setEditable(true);
            jumlah_brg.setEditable(true);
            jTextField7.setEditable(true);
            harga_brg.setEditable(true);
            masuk_brg.setEditable(true);
            buat_brg.setEnabled(true);
            jenis_brg.setEnabled(true);
            nama_barang.setEnabled(true);
            Simpan2.setVisible(true);
            kembali_brg.setVisible(true);
            batal.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang dipilih !");
        }
    }//GEN-LAST:event_EditActionPerformed

    private void Simpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Simpan2ActionPerformed
        if (kd_barang.getText().equals("") || nama_barang.getText().equals("") || merek_brg.getText().equals("") || status_brg.getText().equals("") || jumlah_brg.getText().equals("") || jTextField7.getText().equals("") ||  harga_brg.getText().equals("") || masuk_brg.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Data tidak boleh ada yang kosong", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        } else {
            ubah_data();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah !");
        }
        loadData();
        kd_barang.setText("");
        nama_barang.setText("");
        merek_brg.setText("");
        status_brg.setText("");
        jumlah_brg.setText("");
        jTextField7.setText("");
        harga_brg.setText("");
        masuk_brg.setText("");
    }//GEN-LAST:event_Simpan2ActionPerformed

    private void HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusActionPerformed
       int result= JOptionPane.showConfirmDialog(rootPane, "Anda ingin menghapus ?");
        if (result == JOptionPane.YES_OPTION) {
        hapus();
// yes option
} 
        if (result == JOptionPane.NO_OPTION) {
            loadData();
        }
        else {
    // no option
    loadData();
}
        
    }//GEN-LAST:event_HapusActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        kd_barang.setText("");
        nama_barang.setText("");
        merek_brg.setText("");
        status_brg.setText("");
        jumlah_brg.setText("");
        jTextField7.setText("");
        harga_brg.setText("");
        masuk_brg.setText("");
        loadData();
    }//GEN-LAST:event_refreshActionPerformed

    private void merek_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_merek_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_merek_brgActionPerformed

    private void CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariActionPerformed
        // TODO add your handling code here:
        String cari=null;
        cari = JOptionPane.showInputDialog("Masukkan data yang dibutuhkan");
        cleanTable();
        try {
            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select * from t_inventaris_barang where kode_barang='" +cari+"';";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[10];
                getO[0] = line_result.getString("kode_barang");
                getO[1] = line_result.getString("nama_barang");
                getO[2] = line_result.getString("jenis_barang");
                getO[3] = line_result.getString("merek_barang");
                getO[4] = line_result.getString("status_barang");
                getO[5] = line_result.getString("jumlah");
                getO[6] = line_result.getString("tahun_buat");
                getO[7] = line_result.getString("kondisi_barang");
                getO[8] = line_result.getString("harga");
                getO[9] = line_result.getString("tgl_masuk");
                

                tabel.addRow(getO);
            }
            line_result.close();
            line_statemen.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_user_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_user_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_user_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_user_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_user_barang().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cari;
    private javax.swing.JButton Edit;
    private javax.swing.JButton Hapus;
    private javax.swing.JButton Simpan1;
    private javax.swing.JButton Simpan2;
    private javax.swing.JTable Table_brg;
    private javax.swing.JButton Tambah;
    private javax.swing.JButton batal;
    private javax.swing.JComboBox buat_brg;
    private javax.swing.JTextField harga_brg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JComboBox<String> jenis_brg;
    private javax.swing.JTextField jumlah_brg;
    private javax.swing.JTextField kd_barang;
    private javax.swing.JButton kembali_brg;
    private javax.swing.JTextField masuk_brg;
    private javax.swing.JTextField merek_brg;
    private javax.swing.JTextField nama_barang;
    private javax.swing.JButton refresh;
    private javax.swing.JTextField status_brg;
    // End of variables declaration//GEN-END:variables
}
