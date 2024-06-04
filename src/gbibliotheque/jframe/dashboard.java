/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gbibliotheque.jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author kadio
 */
public class dashboard extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;
    
    /**
     * Creates new form dashboard
     */
    public dashboard() {
        initComponents();
         initComponents();
        Connect();
        showPieChart();
        Book_Load();
        Student_Load();
        setIconImage();
        updateStatistics();
        JTextField G = login.Userc;
        welcome.setText(G.getText());
        
    }
    
     public void Book_Load() {
        int c;
        try {
            pst = con.prepareStatement("select * from livres");
            rs = pst.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            d = (DefaultTableModel) jTablelivre.getModel();
            d.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("code_liv"));
                    v2.add(rs.getString("titre_liv"));
                    v2.add(rs.getString("auteur_liv"));
                    v2.add(rs.getString("nb_exemplaires"));

                }
                d.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      // Loading student details from the database table
    public void Student_Load() {
        int c;
        try {
            pst = con.prepareStatement("select * from adherents");
            rs = pst.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            d = (DefaultTableModel) jTableadherent.getModel();
            d.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("matricule_user"));
                    v2.add(rs.getString("nom_user"));
                    v2.add(rs.getString("prenom_user"));
                    v2.add(rs.getString("telephone"));

                }
                d.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     // set Icon method
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));

    }
    
      // Database connectivity method
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
        } catch (SQLException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private int getCountFromDatabase(String query) {
        int count = 0;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            
            pst = con.prepareStatement(query);
            
            rs= pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }    
      private int getLivres() {
        return getCountFromDatabase("SELECT COUNT(*) FROM livres ");
    }
   
    private int getNombreUser() {
        return getCountFromDatabase("SELECT COUNT(*) FROM utilisateur ");
    }
    private int getEmprunts() {
        return getCountFromDatabase("SELECT COUNT(*) FROM  emprunts ");
    }
    private int getNombreRetour() {
        return getCountFromDatabase("SELECT COUNT(*) FROM emprunts where status=retourné ");
    }
      private int getNombrepasRetour() {
        return getCountFromDatabase("SELECT COUNT(*) FROM emprunts where status=pending ");
    }
    
     public void updateStatistics() {
        int nombreUser = getNombreUser();
        int nombreDeLivres = getLivres();
        int nombreEmprunts = getEmprunts();
        int nombreRetour = getNombreRetour();
        int nombrepasRetour = getNombrepasRetour();

        jllivres.setText( ": "+nombreDeLivres);
        juser.setText( ": "+nombreUser);
        jLempruntés.setText( ": "+nombreEmprunts);
        retour.setText( ": "+nombreRetour);
        pasretournés.setText(":"+nombrepasRetour);
        
        
    }
     // Counting No.of.Issued books from the database table
    public void countEmpruntsretour() {

        int noOfIssuedBooksCount = 0;
        try {
            pst = con.prepareStatement("select * from emprunts where status=?");
            pst.setString(1, "retourné");
            rs = pst.executeQuery();
            while (rs.next()) {
                noOfIssuedBooksCount++;

            }
            retour.setText(Integer.toString(noOfIssuedBooksCount));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void showPieChart() {

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();

        try {
            pst = con.prepareStatement("select titre_liv, count(*) as issue_count from emprunts group by titre_liv");
            rs = pst.executeQuery();
            while (rs.next()) {
                barDataset.setValue(rs.getString("titre_liv"), new Double(rs.getDouble("issue_count")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Statistiques", barDataset, true, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelpiechart.removeAll();
        panelpiechart.add(barChartPanel, BorderLayout.CENTER);
        panelpiechart.validate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        juser = new javax.swing.JLabel();
        panelpiechart = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jllivres = new javax.swing.JLabel();
        p = new javax.swing.JPanel();
        pasretournés = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLempruntés = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbllivre = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        retour = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablelivre = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableadherent = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1500, 750));

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jLabel1.setText("Gestion Bibliothèque");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel2.setText("Bienvenue :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 138, -1));

        welcome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        welcome.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 180, 44));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 0, 48, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, -1, -1));

        jPanel3.setBackground(new java.awt.Color(4, 125, 101));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Exit_26px_2.png"))); // NOI18N
        jButton1.setText("Retour");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 590, -1, -1));

        jPanel4.setBackground(new java.awt.Color(102, 255, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel4.setText("Tableau de bord");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 261, -1));

        jPanel5.setBackground(new java.awt.Color(102, 255, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/books-26.png"))); // NOI18N
        jLabel5.setText("Gestion Livres");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 228, -1));

        jPanel6.setBackground(new java.awt.Color(102, 255, 204));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel6.setText("Gestion Adherents");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 247, -1, -1));

        jPanel7.setBackground(new java.awt.Color(102, 255, 204));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/sell-26.png"))); // NOI18N
        jLabel7.setText("Emprunter livre");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 228, -1));

        jPanel8.setBackground(new java.awt.Color(102, 255, 204));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel8.setText("Retourner Livre");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 433, 228, -1));

        jPanel9.setBackground(new java.awt.Color(102, 255, 204));
        jPanel9.setAutoscrolls(true);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel9.setText("Details Emprunts");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 526, 228, -1));

        jPanel11.setBackground(new java.awt.Color(102, 102, 102));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel21.setText("Ajouter Admin");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 228, 60));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        juser.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        juser.setForeground(new java.awt.Color(0, 255, 204));
        juser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel12.add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 125, 76));

        panelpiechart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelpiechart.setLayout(new java.awt.BorderLayout());
        jPanel10.add(panelpiechart, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 420, 360));

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jllivres.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jllivres.setForeground(new java.awt.Color(0, 255, 204));
        jllivres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        jPanel14.add(jllivres, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel10.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 34, 125, 76));

        p.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pasretournés.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        pasretournés.setForeground(new java.awt.Color(0, 255, 204));
        pasretournés.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        p.add(pasretournés, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel10.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 125, 76));

        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLempruntés.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLempruntés.setForeground(new java.awt.Color(0, 255, 204));
        jLempruntés.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        jPanel16.add(jLempruntés, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel10.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 125, 76));

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel10.setText("Nbr livres retournés");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, 30));

        lbllivre.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lbllivre.setText("Nombres Livres");
        jPanel10.add(lbllivre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel16.setText("Nbr pas retournés");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, 30));

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel17.setText("Nbr Adherents");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, -1, 30));

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel18.setText("Nbr livres empruntés");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, 30));

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        retour.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        retour.setForeground(new java.awt.Color(0, 255, 204));
        retour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        jPanel17.add(retour, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel10.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 125, 76));

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 255, 204));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        jLabel20.setText("10");
        jPanel18.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel10.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 125, 76));

        jTablelivre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 255, 204), new java.awt.Color(0, 255, 204)));
        jTablelivre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID livre", "Titre", "Auteur", "Quantité"
            }
        ));
        jScrollPane1.setViewportView(jTablelivre);

        jPanel10.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, 230));

        jTableadherent.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 255, 204), new java.awt.Color(0, 255, 204)));
        jTableadherent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID adherent", "Nom", "Prenom", "Telephone"
            }
        ));
        jScrollPane2.setViewportView(jTableadherent);

        jPanel10.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, -1, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
          FAIRE_emprunts e = new FAIRE_emprunts();
        e.setVisible(true);
        e.pack();
        e.setLocationRelativeTo(null);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        retour r = new retour();
        r.setVisible(true);
        r.pack();
        r.setLocationRelativeTo(null);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        sign s = new sign();
        s.setVisible(true);
        s.pack();
        s.setLocationRelativeTo(null);
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        FAIRE_emprunts ed = new FAIRE_emprunts();
        ed.setVisible(true);
        ed.pack();
        ed.setLocationRelativeTo(null);
        ed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Glivres g = new Glivres();
        g.setVisible(true);
        g.pack();
        g.setLocationRelativeTo(null);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        dashboard d = new dashboard();
        d.setVisible(true);
        d.pack();
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Gadherent a = new Gadherent();
        a.setVisible(true);
        a.pack();
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        login l= new login();
        l.setVisible(true);
        l.pack();
        l.setLocationRelativeTo(null);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLempruntés;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableadherent;
    private javax.swing.JTable jTablelivre;
    private javax.swing.JLabel jllivres;
    private javax.swing.JLabel juser;
    private javax.swing.JLabel lbllivre;
    private javax.swing.JPanel p;
    private javax.swing.JPanel panelpiechart;
    private javax.swing.JLabel pasretournés;
    private javax.swing.JLabel retour;
    public static javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
