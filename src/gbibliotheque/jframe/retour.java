/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gbibliotheque.jframe;

import static gbibliotheque.jframe.dashboard.welcome;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jframe.ManageBooks;
import jframe.ManageStudents;

/**
 *
 * @author kadio
 */
public class retour extends javax.swing.JFrame {
        
      
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;  
    
      
    /**
     * Creates new form Gretour
     */
     public retour() {
        initComponents();
        Connect();
       
        setIconImage() ;
        setTitle("Faire Retour");
        JTextField G = login.Userc;
        welcome.setText(G.getText());
    }
     
      // Set Icon method
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book.png")));

    }
     
     public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
        } catch (SQLException ex) {
            Logger.getLogger(Gadherent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gadherent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      // Loading Issued book details
    public void getIssueBookDetails() {
        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());

        try {
            boolean success;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("select * from emprunts where livre_empruntés=? and emprunteurs=? and status=?");
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "emprunté");
            rs = pst.executeQuery();
            if(rs.next()) {
                success =true;
                lbl_issueid.setText(rs.getString("Num_emp"));
                lbl_bookid.setText(rs.getString("livre_empruntés"));
                lbl_bookname.setText(rs.getString("titre_liv"));
                lbl_studentid.setText(rs.getString("emprunteurs"));
                lbl_studentname.setText(rs.getString("nom_adhe"));
                lbl_issuedate.setText(rs.getString("date_emprumts"));
                lbl_duedate.setText(rs.getString("retour_norm"));
                txtbookerror.setText("");

            }else{
                 txtbookerror.setText("Livre non trouvé");
                 success=false;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    
    // Return the book
    public boolean returnBook() {
        boolean isReturned = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("update emprunts set status=? where livre_empruntés=? and emprunteurs=? and status=?");
            pst.setString(1, "retourné");
            pst.setInt(2, bookId);
            pst.setInt(3, studentId);
            pst.setString(4, "emprunté");
            int rowcount = pst.executeUpdate();
            if (rowcount > 0) {
                isReturned = true;
            } else {
                isReturned = false;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return isReturned;
    }
    
     // Update the book count method
    public void updateBookCount() {
        try {
            int bookId = Integer.parseInt(txt_bookid.getText());
            pst = con.prepareStatement("update livres set quantité=quantité+1 where code_liv=?");
            pst.setInt(1, bookId);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "le nombre de livre a été mis a jour !");

            } else {
                JOptionPane.showMessageDialog(this, "Erreur,le nombre de livre n'a pas été mis a jour");
            }

        } catch (SQLException ex) {
            Logger.getLogger(retour.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbl_duedate = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_issueid = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_bookerror = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_issuedate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        lbl_bookid2 = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtbookerror = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel25 = new javax.swing.JLabel();
        returnbutton = new rojerusan.RSMaterialButtonRectangle();
        findbutton = new rojerusan.RSMaterialButtonRectangle();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/adminIcons/books-26.png"))); // NOI18N
        jLabel2.setText("Livre");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 240, 100));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 310, 5));

        lbl_duedate.setBackground(new java.awt.Color(255, 255, 255));
        lbl_duedate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_duedate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 140, 40));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Titre livre    :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nom adherent    :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID emprunts   :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 150, -1));

        lbl_issueid.setBackground(new java.awt.Color(255, 255, 255));
        lbl_issueid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_issueid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_issueid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 130, 30));

        lbl_bookname.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bookname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 220, 30));

        lbl_studentid.setBackground(new java.awt.Color(255, 255, 255));
        lbl_studentid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 210, 40));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date retour  :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        txt_bookerror.setBackground(new java.awt.Color(255, 255, 255));
        txt_bookerror.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txt_bookerror.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(txt_bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, 300, 50));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Date  emprunt    :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        lbl_issuedate.setBackground(new java.awt.Color(255, 255, 255));
        lbl_issuedate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_issuedate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 140, 40));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ID livre    :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, 30));

        lbl_bookid.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bookid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 220, 40));

        lbl_bookid2.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bookid2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bookid2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_bookid2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 220, 30));

        lbl_studentname.setBackground(new java.awt.Color(255, 255, 255));
        lbl_studentname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 190, 20));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID adherent    :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        txtbookerror.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtbookerror.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(txtbookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 240, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 420, 550));

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 255, 204));
        jLabel11.setText("Retourner Livre");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, -1));

        jPanel8.setBackground(new java.awt.Color(0, 255, 204));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, -1, 10));

        jPanel9.setBackground(new java.awt.Color(51, 153, 255));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("X");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 60, 30));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 204)));
        txt_bookid.setForeground(new java.awt.Color(51, 51, 51));
        txt_bookid.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_bookid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_bookid.setPhColor(new java.awt.Color(51, 51, 51));
        txt_bookid.setPlaceholder("entrer ID livre");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 250, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 255, 204));
        jLabel23.setText("ID livre :");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, -1, 30));

        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 204)));
        txt_studentid.setForeground(new java.awt.Color(51, 51, 51));
        txt_studentid.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_studentid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_studentid.setPhColor(new java.awt.Color(51, 51, 51));
        txt_studentid.setPlaceholder("entrer ID adherent");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 240, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 255, 204));
        jLabel25.setText("ID adherent :");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 110, 30));

        returnbutton.setBackground(new java.awt.Color(0, 255, 204));
        returnbutton.setText("RETOURNER LIVRE");
        returnbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(returnbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 520, 150, 50));

        findbutton.setBackground(new java.awt.Color(0, 102, 102));
        findbutton.setText("CHERCHER");
        findbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(findbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, 150, 50));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Bienvenue,");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 100, 40));

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("RETOUR");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 40));

        welcome.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        welcome.setForeground(new java.awt.Color(0, 102, 102));
        jPanel1.add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 120, 60));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nom adherent :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 270, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1028, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        getIssueBookDetails();
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost

    }//GEN-LAST:event_txt_studentidFocusLost

    private void returnbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnbuttonActionPerformed
        // TODO add your handling code here:
        if (returnBook() == true) {
            JOptionPane.showMessageDialog(this, "Livre retourné avec succès!");
            updateBookCount();
            lbl_issueid.setText("");
            lbl_bookid.setText("");
            lbl_bookname.setText("");
            lbl_studentid.setText("");
            lbl_studentname.setText("");
            lbl_issuedate.setText("");
            lbl_duedate.setText("");
            txt_bookid.setText("");
            txt_studentid.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "livre non retourné!");
        }
    }//GEN-LAST:event_returnbuttonActionPerformed

    private void findbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findbuttonActionPerformed
        getIssueBookDetails();
    }//GEN-LAST:event_findbuttonActionPerformed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        dashboard hm = new dashboard();
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(retour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(retour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(retour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(retour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new retour().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle findbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookid2;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_duedate;
    private javax.swing.JLabel lbl_issuedate;
    private javax.swing.JLabel lbl_issueid;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_studentname;
    private rojerusan.RSMaterialButtonRectangle returnbutton;
    private javax.swing.JLabel txt_bookerror;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentid;
    private javax.swing.JLabel txtbookerror;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
