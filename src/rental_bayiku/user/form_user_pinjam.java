package rental_bayiku.user;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class form_user_pinjam extends javax.swing.JFrame {

    DefaultTableModel tabel = new DefaultTableModel();
    DefaultTableModel tabel2 = new DefaultTableModel();
    DefaultTableModel tabel3 = new DefaultTableModel();
    String birth;
    String tanggal;
    

    public void AutoNumber() {
        try {

            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select max(right (kode_peminjaman,4)) as no_urut from t_peminjaman";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            if (line_result.first() == false) {
                kd_pinjam.setText("P0001");
            } else {
                line_result.last();
                int no = line_result.getInt(1)+1 ;
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
                    nomor = "P" + nomor;
                }
                }
                kd_pinjam.setText("P" + nomor);
            }
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
    public form_user_pinjam() {
        initComponents();


        kd_pinjam.setEditable(false);
        kd_brg.setEditable(false);
        kode_kostumer.setEditable(false);
        jum_pinjam.setEditable(false);
        stk_pinjam.setEditable(false);
        hrg_brg.setEditable(false);
        AutoNumber();
        curentdate();
        
    }

    private void itung() {
       
       String a = stk_pinjam.getText();
       String m = jum_pinjam.getText();

        
        if (jum_pinjam.getText() == null) {
            stk_pinjam.setText(a);
        }
        else {
            int b = Integer.parseInt(a);
            int h = Integer.parseInt(m);
            int jumlah = b - h;
            String s = "" + jumlah;

            stk_pinjam.setText(s);
           
        }
        
    }


    private void lihatBarang() {
        tabel.addColumn("Kode Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Stok");
        tabel.addColumn("Harga Barang");
        tabel.addColumn("Kondisi Barang");
        
        jTable1.setModel(tabel);

        TableColumn lebar_kolom;

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);

        lebar_kolom = jTable1.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(100);
        lebar_kolom = jTable1.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(2);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(3);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable1.getColumnModel().getColumn(4);
        lebar_kolom.setPreferredWidth(200);

        
        tabel.getDataVector().removeAllElements();
        tabel.fireTableDataChanged();
        try {
            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select kode_barang, nama_barang, jumlah,harga,kondisi_barang from t_inventaris_barang";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[5];
                getO[0] = line_result.getString("kode_barang");
                getO[1] = line_result.getString("nama_barang");
                getO[2] = line_result.getString("jumlah");
                getO[3] = line_result.getString("harga");
                getO[4] = line_result.getString("kondisi_barang");
                
                tabel.addRow(getO);
            }
            line_result.close();
            line_statemen.close();
        } catch (Exception e) {
        }
    }

    private void lihatPeminjam() {
        tabel2.addColumn("Kode Peminjam");
        tabel2.addColumn("Nama Peminjam");

        jTable2.setModel(tabel2);

        TableColumn lebar_kolom;

        jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_OFF);

        lebar_kolom = jTable2.getColumnModel().getColumn(0);
        lebar_kolom.setPreferredWidth(200);
        lebar_kolom = jTable2.getColumnModel().getColumn(1);
        lebar_kolom.setPreferredWidth(200);

        tabel2.getDataVector().removeAllElements();
        tabel2.fireTableDataChanged();
        try {
            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select kode_peminjam, nama_peminjam from t_peminjam";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                Object[] getO = new Object[2];
                getO[0] = line_result.getString("kode_peminjam");
                getO[1] = line_result.getString("nama_peminjam");

                tabel2.addRow(getO);
            }
            line_result.close();
            line_statemen.close();
        } catch (Exception e) {
        }
    }

   

    
    private void simpan() {

        String kd = kd_pinjam.getText();
        String kode_barang = kd_brg.getText();
        String kd_kostumer = kode_kostumer.getText();
        String jumlah = jum_pinjam.getText();
        String tanggal_pin = jTextField6.getText();
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(format.format(jDateChooser1.getDate()));
        String kondisi = knds_pinjam.getText();
        
        String total_pin = total_byr.getText();
        
        try {
            Connection sql_query_koneksi = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement sql_statement = sql_query_koneksi.createStatement();
            String query_input = "insert into t_peminjaman values ('" + kd + "','" + kode_barang + "','" + kd_kostumer + "','" +  jumlah + "','" + tanggal_pin + "','"+ tanggal + "','"+ kondisi + "','" + total_pin + "')";
            sql_statement.executeUpdate(query_input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Memasukkan Data !", "Peringatan", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private void ubah_data() {
        try {
            String kode_barang = kd_brg.getText();
            String jumlah = stk_pinjam.getText();


            Connection sql_query_koneksi = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            String sql_query_update = "update t_inventaris_barang set jumlah='" + jumlah + "' where kode_barang='" + kode_barang + "';";
            com.mysql.jdbc.PreparedStatement query_prepare = (PreparedStatement) sql_query_koneksi.prepareStatement(sql_query_update);
            query_prepare.execute();

        } catch (Exception e) {
        }
    }

    public void curentdate() {
        
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        jTextField6.setText(year + "-" + (month + 1) + "-" + day);
        
    }

    public void getData() {
        jLabel9.setText(kd_pinjam.getText());
        jLabel10.setText(kd_brg.getText());
        jLabel11.setText(kode_kostumer.getText());
        jLabel12.setText(jLabel44.getText());
        jLabel13.setText(jum_pinjam.getText());
        jLabel14.setText(jTextField6.getText());
        jLabel15.setText(total_byr.getText());
    }
    private int CekStok(){
    int stok = 0;
       String kode_brg = kd_brg.getText();
       try {
            java.sql.Connection line_konek = (Connection) rental_bayiku.koneksi.konekDB.getDB();
            java.sql.Statement line_statemen = line_konek.createStatement();
            String query_bukaTabel = "select jumlah from t_inventaris_barang where kode_barang ='"+kode_brg+"'";
            java.sql.ResultSet line_result = line_statemen.executeQuery(query_bukaTabel);
            while (line_result.next()) {
                stok = Integer.parseInt(line_result.getString(1));
            }
            
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage());
           System.out.println(""+e.getMessage());
       }
        return stok;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kd_pinjam = new javax.swing.JTextField();
        kode_kostumer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jum_pinjam = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        stk_pinjam = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        kd_brg = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        hrg_brg = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        uang_muka = new javax.swing.JTextField();
        htg_total = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        subtotal = new javax.swing.JLabel();
        total_byr = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        htg_lama = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        total_pinjam = new javax.swing.JLabel();
        knds_pinjam = new javax.swing.JTextField();

        jDialog1.setMinimumSize(new java.awt.Dimension(450, 300));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Back-32.png"))); // NOI18N
        jButton6.setText("Kembali");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jDialog2.setMinimumSize(new java.awt.Dimension(450, 350));

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane2MousePressed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Back-32.png"))); // NOI18N
        jButton7.setText("Kembali");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog3.setMinimumSize(new java.awt.Dimension(450, 350));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Back-32.png"))); // NOI18N
        jButton8.setText("Kembali");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(jDialog3Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog4.setMinimumSize(new java.awt.Dimension(450, 350));
        jDialog4.setUndecorated(true);

        jLabel25.setText("Kode Peminjaman");

        jLabel26.setText("Kode Barang yang Dipinjam");

        jLabel27.setText("Kode Nama Peminjam");

        jLabel28.setText("Lama Pinjam ");

        jLabel29.setText("Jumlah Barang");

        jLabel30.setText("Tanggal Peminjaman");

        jLabel31.setText("Kondisi Barang");

        jLabel32.setText(":");

        jLabel33.setText(":");

        jLabel34.setText(":");

        jLabel35.setText(":");

        jLabel36.setText(":");

        jLabel37.setText(":");

        jLabel38.setText(":");

        jLabel39.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel39.setText("Barang berhasil Dipinjam !");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Back-32.png"))); // NOI18N
        jButton12.setText("Kembali");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/agt_print-32.png"))); // NOI18N
        jButton13.setText("Cetak");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel9.setText("jLabel9");

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        jLabel12.setText("jLabel12");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        jLabel15.setText("jLabel15");

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)))))
                .addGap(0, 235, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton12)
                        .addGap(31, 31, 31)
                        .addComponent(jButton13)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(50, 50, 50))))
            .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog4Layout.createSequentialGroup()
                    .addGap(250, 250, 250)
                    .addComponent(jLabel40)
                    .addContainerGap(212, Short.MAX_VALUE)))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel32)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel33)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel34)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel35)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel36)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel37)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel38)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(jLabel40)
                    .addGap(285, 285, 285)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        setSize(new java.awt.Dimension(4500, 4000));

        jLabel1.setText("Lengkapi Data Berikut");

        jLabel2.setText("Kode Peminjaman");

        kd_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_pinjamActionPerformed(evt);
            }
        });

        jLabel4.setText("Kode Peminjam");

        jLabel7.setText("Kondisi Barang");

        jum_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jum_pinjamActionPerformed(evt);
            }
        });
        jum_pinjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jum_pinjamKeyReleased(evt);
            }
        });

        jLabel8.setText("Jumlah");

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Back-32.png"))); // NOI18N
        jButton4.setText("Kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/Save-32.png"))); // NOI18N
        save.setText("Simpan");
        save.setName(""); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel16.setText("Stok");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Harga"));

        jLabel3.setText("Kode Barang ");

        kd_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kd_brgKeyReleased(evt);
            }
        });

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Tarif Harga   : ");

        jLabel19.setText("Rp. ");

        hrg_brg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hrg_brgMouseClicked(evt);
            }
        });
        hrg_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrg_brgActionPerformed(evt);
            }
        });

        jLabel20.setText("Total Bayar   : ");

        jLabel21.setText("Rp. ");

        jLabel5.setText("Uang Muka/DP : ");

        jLabel43.setText("Rp. ");

        uang_muka.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uang_mukaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uang_mukaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                uang_mukaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                uang_mukaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                uang_mukaMouseReleased(evt);
            }
        });
        uang_muka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uang_mukaActionPerformed(evt);
            }
        });
        uang_muka.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                uang_mukaPropertyChange(evt);
            }
        });
        uang_muka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                uang_mukaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                uang_mukaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                uang_mukaKeyTyped(evt);
            }
        });

        htg_total.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/calculator.png"))); // NOI18N
        htg_total.setText("Hitung");
        htg_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                htg_totalActionPerformed(evt);
            }
        });

        jLabel45.setText("SubTotal : ");

        jLabel46.setText("Rp. ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(kd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(htg_total)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(14, 14, 14)
                        .addComponent(hrg_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(total_byr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel43)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(uang_muka, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19))
                    .addComponent(hrg_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel43)
                            .addComponent(uang_muka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel21))
                    .addComponent(total_byr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(htg_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Waktu"));

        jLabel6.setText("Tanggal Peminjaman");

        jLabel22.setText("Tanggal Kembali");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setPreferredSize(new java.awt.Dimension(100, 25));

        jLabel17.setText("Lama Pinjam : ");

        htg_lama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si_pergudangan/image/calculator.png"))); // NOI18N
        htg_lama.setText("Hitung");
        htg_lama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                htg_lamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(htg_lama)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField6))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel22))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel22))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(htg_lama)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel24.setText("TOTAL : ");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel41.setText("RP");

        total_pinjam.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N

        knds_pinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knds_pinjamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel7))
                                        .addGap(32, 32, 32))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(save)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(kd_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(kode_kostumer, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jum_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stk_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(knds_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(16, 16, 16)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel41)
                        .addGap(27, 27, 27)
                        .addComponent(total_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(kd_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kode_kostumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jum_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(stk_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(knds_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(save)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jLabel41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(total_pinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(80, 80, 80))
        );

        setSize(new java.awt.Dimension(737, 633));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kd_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_pinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_pinjamActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        new form_user_utama_1().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jDialog1.setLocationRelativeTo(null);
        lihatBarang();
        jDialog1.setVisible(true);
        jum_pinjam.setEditable(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jDialog2.setLocationRelativeTo(null);
        lihatPeminjam();
        jDialog2.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jDialog1.setVisible(false);
        jum_pinjam.setEditable(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        String getKodeA = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String getJumlah = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String getHarga = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        String getKondisiA = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
        
        
        kd_brg.setText(getKodeA);
        stk_pinjam.setText(getJumlah);
        hrg_brg.setText(getHarga);
        knds_pinjam.setText(getKondisiA);
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jDialog2.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jScrollPane2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MousePressed
    }//GEN-LAST:event_jScrollPane2MousePressed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        String getKodeB = jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
        
        kode_kostumer.setText(getKodeB);
        jDialog2.setVisible(false);
    }//GEN-LAST:event_jTable2MousePressed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        String getKodeC = jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
        String getHargaC = jTable3.getValueAt(jTable3.getSelectedRow(), 3).toString();
        String getKondisiC = jTable3.getValueAt(jTable3.getSelectedRow(), 4).toString();
        
      kd_brg.setText(getKodeC);
      hrg_brg.setText(getKodeC);
      knds_pinjam.setText(getKondisiC);
        jDialog3.setVisible(false);
    }//GEN-LAST:event_jTable3MousePressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jDialog3.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if (kd_pinjam.getText().equals("") || kd_brg.getText().equals("") || kode_kostumer.getText().equals("") ||  jum_pinjam.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Data tidak boleh ada yang kosong", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        } else {
            simpan();
            JOptionPane.showMessageDialog(null, "Data Tersimpan !");
            jDialog4.setVisible(true);
            getData();
            ubah_data();
            this.dispose();
        }
        kd_brg.setText("");
        kode_kostumer.setText("");
        jum_pinjam.setText("");
        stk_pinjam.setText("");
    }//GEN-LAST:event_saveActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jDialog4.setVisible(false);
        this.dispose();
        new akses_peminjaman_1().setVisible(true);
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jum_pinjamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jum_pinjamKeyReleased
    
        
    }//GEN-LAST:event_jum_pinjamKeyReleased

    private void hrg_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrg_brgActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_hrg_brgActionPerformed

    private void hrg_brgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hrg_brgMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_hrg_brgMouseClicked

    private void kd_brgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_brgKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_kd_brgKeyReleased

    private void jum_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jum_pinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jum_pinjamActionPerformed

    private void uang_mukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uang_mukaActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_uang_mukaActionPerformed

    private void uang_mukaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uang_mukaKeyReleased
        // TODO add your handling code here:
    
    }//GEN-LAST:event_uang_mukaKeyReleased

    private void uang_mukaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uang_mukaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_uang_mukaMouseExited

    private void uang_mukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uang_mukaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_uang_mukaKeyPressed

    private void uang_mukaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uang_mukaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_uang_mukaKeyTyped

    private void uang_mukaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uang_mukaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_uang_mukaMouseClicked

    private void uang_mukaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uang_mukaMouseEntered
        // TODO add your handling code here:
         
    }//GEN-LAST:event_uang_mukaMouseEntered

    private void uang_mukaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uang_mukaMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_uang_mukaMousePressed

    private void uang_mukaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uang_mukaMouseReleased
        // TODO add your handling code here:
    
    }//GEN-LAST:event_uang_mukaMouseReleased

    private void uang_mukaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_uang_mukaPropertyChange
  // TODO add your handling code here:
     
    }//GEN-LAST:event_uang_mukaPropertyChange

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        try {

  String path="F:/PI/TEMA DESKTOP/Aplikasi PI 1/PROJECT/SI_GUDANG/SI_Pergudangan/src/si_pergudangan/report/struk_pinjam.jrxml";      // letak penyimpanan report

  HashMap parameter = new HashMap(3);

  parameter.put("kode_pinjam",kd_pinjam.getText());
  parameter.put("subtotal",subtotal.getText());
  parameter.put("dp",uang_muka.getText());
// "no_faktur" => nama parameter yang dibuat
 //jTextFieldnofaktur.getText() ==> disesuaikan dengan jTextField yang digunakan

                JasperDesign JasDes = JRXmlLoader.load(path);
                JasperReport JasRep = JasperCompileManager.compileReport(JasDes);
                JasperPrint JasPri = JasperFillManager.fillReport(JasRep, parameter , DriverManager.getConnection("jdbc:mysql://localhost:3306/gudang_rental","root","achmad"));
                JasperViewer.viewReport(JasPri, false);
    } catch (Exception ex) {

  JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);

    }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void knds_pinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knds_pinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_knds_pinjamActionPerformed

    private void htg_lamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_htg_lamaActionPerformed
        // TODO add your handling code here:
        try{
        String jum = jum_pinjam.getText();
        String harga = hrg_brg.getText();
        String stok = stk_pinjam.getText();
        int sub; 
        
        int jum1 = Integer.parseInt(jum);
        int stk = Integer.parseInt(stok);
        int harga1 = Integer.parseInt(harga);
            
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tglsatu = jTextField6.getText();
        Date tglPinjam = (Date) date.parse(tglsatu);
        String tgldua = date.format(jDateChooser1.getDate());
        Date tglKembali = (Date) date.parse(tgldua);
        
        long lama = Math.abs(tglKembali.getTime() - tglPinjam.getTime());
        jLabel44.setText( TimeUnit.MILLISECONDS.toDays(lama) + " hari");
        
        if (tglKembali.before(tglPinjam)) {
        JOptionPane.showMessageDialog(this, "Tanggal tidak boleh kurang hari ini");
        }
        else if (jum1 > stk) {
        JOptionPane.showMessageDialog(this, "Jumlah tidak boleh lebih dari stok");
        }
        
        else {
        sub = (int) (jum1 * harga1 * TimeUnit.MILLISECONDS.toDays(lama)/7);
        String sub1 = String.valueOf(sub);
        subtotal.setText(sub1);
        }
        }catch(Exception e){
            
        }
        itung();
    }//GEN-LAST:event_htg_lamaActionPerformed

    private void htg_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_htg_totalActionPerformed
        // TODO add your handling code here:
        String dp = uang_muka.getText();
        String sub = subtotal.getText();
        
        int dp1 = Integer.parseInt(dp);
        int sub1 = Integer.parseInt(sub);
        
        int total;
        if(dp1 > (sub1*0.3)) {
             JOptionPane.showMessageDialog(this, "DP tidak lebih dari 30%!");
        }
        else if(dp1 < 10000) {
             JOptionPane.showMessageDialog(this, "DP minimal 10000!");
        }
        else {
        total = sub1 - dp1;
        String total1 = String.valueOf(total);
        total_byr.setText(total1);
        total_pinjam.setText(total1);
    }//GEN-LAST:event_htg_totalActionPerformed
    }
    
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
            java.util.logging.Logger.getLogger(form_user_pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_user_pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_user_pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_user_pinjam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_user_pinjam().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField hrg_brg;
    private javax.swing.JButton htg_lama;
    private javax.swing.JButton htg_total;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jum_pinjam;
    private javax.swing.JTextField kd_brg;
    private javax.swing.JTextField kd_pinjam;
    private javax.swing.JTextField knds_pinjam;
    private javax.swing.JTextField kode_kostumer;
    private javax.swing.JButton save;
    private javax.swing.JTextField stk_pinjam;
    private javax.swing.JLabel subtotal;
    private javax.swing.JLabel total_byr;
    private javax.swing.JLabel total_pinjam;
    private javax.swing.JTextField uang_muka;
    // End of variables declaration//GEN-END:variables

 
}

