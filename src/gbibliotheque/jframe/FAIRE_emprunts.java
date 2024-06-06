/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gbibliotheque.jframe;


import static gbibliotheque.jframe.dashboard.welcome;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author kadio
 */
public class FAIRE_emprunts extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private  ImageIcon format =null;
     String path = null;
      File f = null;
    /**
     * Creates new form emprunts_detail
     */
    public FAIRE_emprunts() {
        initComponents();
        setTitle("page Faire emprunt");
        getBookDetails();
        getStudentDetails();
        Connect();
        setIconImage();
        JTextField G = login.Userc;
        welcome.setText(G.getText());
    }
    // set Icon method
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book.png")));

    }
    public void Connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void getBookDetails() {
        int code_liv= Integer.parseInt(txt_bookid.getText());

        try {
            File image = new File(path);
    FileInputStream fis = new FileInputStream(image);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("select code_liv,titre_liv,auteur_liv,nb_exemplaires  from livres where code_liv=?");
            pst.setInt(1, code_liv);
            rs = pst.executeQuery();
            while (rs.next()) {
                IDLIVRE.setText(rs.getString("code_liv"));
                TXTLIVRE.setText(rs.getString("titre_liv"));
                AUTEUR.setText(rs.getString("auteur_liv"));
                QUANTITE.setText(rs.getString("nb_exemplaires"));
        
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
     }
      // Loading student details from the database table
    public void getStudentDetails() {
        int student_id = Integer.parseInt(txt_studentid.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("select * from adherents where matricule_user=?");
            pst.setInt(1, student_id);
            rs = pst.executeQuery();
            if (rs.next()) {

                idadherent.setText(rs.getString("matricule_user"));
                nomadherent.setText(rs.getString("nom_user"));
                prenomadherent.setText(rs.getString("prenom_user"));
                lbl_studentid.setText(rs.getString("telephone"));
                
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    
    public void loadimgs(){
        try{
            byte imageData[] =rs.getBytes("img_liv");
                
                format = new ImageIcon(imageData);
                Image mm =format.getImage();
                Image img2 =mm.getScaledInstance(135, 127, Image.SCALE_DEFAULT);
                IMGLIVRE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ImageIcon image =new ImageIcon(img2);
                IMGLIVRE.setIcon(image);
    }catch(Exception e){
        
    }
    }
    public void loadimg(){
        try{
            byte imageData[] =rs.getBytes("img_user");
                
                format = new ImageIcon(imageData);
                Image mm =format.getImage();
                Image img2 =mm.getScaledInstance(135, 127, Image.SCALE_DEFAULT);
                imgadherent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ImageIcon image =new ImageIcon(img2);
                imgadherent.setIcon(image);
    }catch(Exception e){
        
    }
    }
        // Update book count to the database table
    public void updateBookCount() {
        try {
            int bookId = Integer.parseInt(txt_bookid.getText());
            pst = con.prepareStatement("update livres set nb_exemplaires=nb_exemplaires-1 where code_liv=?");
            pst.setInt(1, bookId);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book Count updated successfully!");
                int initialCount = Integer.parseInt(QUANTITE.getText());
                QUANTITE.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "Erreur dans la mise à jour de la quantité");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FAIRE_emprunts.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    // Checking duplicate records
    public boolean isAlreadyIssued() {
        boolean isAlreadyIssued = false;

        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());

        try {
            Connect();
            String sql = "select * from emprunts where livre_empruntés=? and emprunteurs=? and status=?";

            pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "emprunté");

            rs = pst.executeQuery();
            if (rs.next()) {
                isAlreadyIssued = true;
            } else {
                isAlreadyIssued = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAlreadyIssued;

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
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel25 = new javax.swing.JLabel();
        EFFACER = new rojerusan.RSMaterialButtonRectangle();
        empruntbutton = new rojerusan.RSMaterialButtonRectangle();
        welcome = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        IDLIVRE = new javax.swing.JLabel();
        AUTEUR = new javax.swing.JLabel();
        QUANTITE = new javax.swing.JLabel();
        txt_bookerror1 = new javax.swing.JLabel();
        TXTLIVRE = new javax.swing.JLabel();
        lbl_bookid3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        IMGLIVRE = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idadherent = new javax.swing.JLabel();
        prenomadherent = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        txt_bookerror = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nomadherent = new javax.swing.JLabel();
        lbl_bookid2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        imgadherent = new javax.swing.JLabel();
        txterrorstudent = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        dateretour = new rojeru_san.componentes.RSDateChooser();
        dateemprunt = new rojeru_san.componentes.RSDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        image2 = new javax.swing.JLabel();
        image3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 255, 204));
        jLabel11.setText("   Emprunter Livre");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, -1, -1));

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

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 170, -1, 10));

        jPanel9.setBackground(new java.awt.Color(255, 0, 0));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("X");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 0, 30, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 60, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 255, 204));
        jLabel23.setText(" Id livre:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, -1, 30));

        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 204)));
        txt_studentid.setForeground(new java.awt.Color(51, 51, 51));
        txt_studentid.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_studentid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_studentid.setPhColor(new java.awt.Color(51, 51, 51));
        txt_studentid.setPlaceholder("Entrer ID adherents");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, 280, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 255, 204));
        jLabel25.setText("Date retour");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 110, 30));

        EFFACER.setBackground(new java.awt.Color(0, 255, 204));
        EFFACER.setText("EFFACER");
        EFFACER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EFFACERActionPerformed(evt);
            }
        });
        jPanel1.add(EFFACER, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 500, 150, 50));

        empruntbutton.setBackground(new java.awt.Color(0, 255, 204));
        empruntbutton.setLabel("EMPRUNTER");
        empruntbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empruntbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(empruntbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 500, 150, 50));

        welcome.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        welcome.setForeground(new java.awt.Color(51, 51, 51));
        welcome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        welcome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                welcomeMouseClicked(evt);
            }
        });
        jPanel1.add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 150, 40));

        jPanel4.setBackground(new java.awt.Color(0, 255, 204));

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
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 40));

        jPanel3.setBackground(new java.awt.Color(0, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel3.setText("Livre");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 310, 5));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Titre livre    :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 140, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Auteur   :");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(" Id livre         :");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 150, -1));

        IDLIVRE.setBackground(new java.awt.Color(255, 255, 255));
        IDLIVRE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        IDLIVRE.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(IDLIVRE, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 130, 30));

        AUTEUR.setBackground(new java.awt.Color(255, 255, 255));
        AUTEUR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AUTEUR.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(AUTEUR, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 220, 30));

        QUANTITE.setBackground(new java.awt.Color(255, 255, 255));
        QUANTITE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        QUANTITE.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(QUANTITE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 210, 40));

        txt_bookerror1.setBackground(new java.awt.Color(255, 255, 255));
        txt_bookerror1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txt_bookerror1.setForeground(new java.awt.Color(255, 255, 0));
        jPanel3.add(txt_bookerror1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, 300, 50));

        TXTLIVRE.setBackground(new java.awt.Color(255, 255, 255));
        TXTLIVRE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TXTLIVRE.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(TXTLIVRE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 220, 40));

        lbl_bookid3.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bookid3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bookid3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_bookid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 220, 30));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Quantité :");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Photo :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        IMGLIVRE.setBackground(new java.awt.Color(0, 255, 204));
        IMGLIVRE.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        IMGLIVRE.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 255, 204), new java.awt.Color(0, 255, 204)));
        IMGLIVRE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IMGLIVREMouseClicked(evt);
            }
        });
        jPanel3.add(IMGLIVRE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 148, 178));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 110, 420, 550));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/icons/icons8_Account_50px.png"))); // NOI18N
        jLabel2.setText("Adherents");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

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

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Prenom    :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("telephone    :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id adherents");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 150, -1));

        idadherent.setBackground(new java.awt.Color(255, 255, 255));
        idadherent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        idadherent.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(idadherent, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 130, 30));

        prenomadherent.setBackground(new java.awt.Color(255, 255, 255));
        prenomadherent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prenomadherent.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(prenomadherent, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 220, 30));

        lbl_studentid.setBackground(new java.awt.Color(255, 255, 255));
        lbl_studentid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 210, 30));

        txt_bookerror.setBackground(new java.awt.Color(255, 255, 255));
        txt_bookerror.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txt_bookerror.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(txt_bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, 300, 50));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nom          :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, 30));

        nomadherent.setBackground(new java.awt.Color(255, 255, 255));
        nomadherent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nomadherent.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(nomadherent, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 220, 30));

        lbl_bookid2.setBackground(new java.awt.Color(255, 255, 255));
        lbl_bookid2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bookid2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_bookid2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 220, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Photo :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        imgadherent.setBackground(new java.awt.Color(0, 255, 204));
        imgadherent.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        imgadherent.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 255, 204), new java.awt.Color(0, 255, 204)));
        imgadherent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgadherentMouseClicked(evt);
            }
        });
        jPanel2.add(imgadherent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 148, 178));

        txterrorstudent.setText("jLabel4");
        jPanel2.add(txterrorstudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 100, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 420, 550));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Bienvenue,");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 100, 40));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 204)));
        txt_bookid.setForeground(new java.awt.Color(51, 51, 51));
        txt_bookid.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_bookid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_bookid.setPhColor(new java.awt.Color(51, 51, 51));
        txt_bookid.setPlaceholder("Entrez ID livre");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 210, 290, -1));

        dateretour.setColorBackground(new java.awt.Color(0, 255, 204));
        dateretour.setColorButtonHover(new java.awt.Color(0, 255, 204));
        dateretour.setPlaceholder("selectionner une date");
        jPanel1.add(dateretour, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 390, 280, -1));

        dateemprunt.setColorBackground(new java.awt.Color(0, 255, 204));
        dateemprunt.setColorButtonHover(new java.awt.Color(0, 255, 204));
        dateemprunt.setPlaceholder("selectionner une date");
        jPanel1.add(dateemprunt, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 330, 280, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 255, 204));
        jLabel26.setText("Date emprunt");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 330, 110, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 255, 204));
        jLabel27.setText(" Id adherent:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 110, 30));

        image2.setBackground(new java.awt.Color(0, 255, 204));
        image2.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        image2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image2MouseClicked(evt);
            }
        });
        jPanel1.add(image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        image3.setBackground(new java.awt.Color(0, 255, 204));
        image3.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        image3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image3MouseClicked(evt);
            }
        });
        jPanel1.add(image3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void image3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_image3MouseClicked

    private void image2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image2MouseClicked
       
    }//GEN-LAST:event_image2MouseClicked

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        if (!txt_bookid.getText().equals("")) {
            getBookDetails();
        }
    }//GEN-LAST:event_txt_bookidFocusLost

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void imgadherentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgadherentMouseClicked
        /*JFileChooser pic = new JFileChooser();
        pic.showOpenDialog(null);

        File picture = pic.getSelectedFile();

        path = picture.getAbsolutePath();
        BufferedImage img;
        /*try {
            img = ImageIO.read(pic.getSelectedFile());
            ImageIcon imageic = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT));
            image1.setIcon(imageic);
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];

            for (int i; (i = fis.read(buff)) != -1;) {
                bos.write(buff, 0, i);
            }

            userimage = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        JFileChooser pic = new JFileChooser();
        FileNameExtensionFilter fnx = new FileNameExtensionFilter("PNG JPG MP4 AND JPEG","png","mp4","jpeg","jpg");
        pic.addChoosableFileFilter(fnx);
        int load = pic.showOpenDialog(null);
        if(load ==pic.APPROVE_OPTION)
        {
            f = pic.getSelectedFile();
            path = f.getAbsolutePath();
            //imagePaths.setText(path);
            ImageIcon image = new ImageIcon(path);
            Image img = image.getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT);
            imgadherent.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_imgadherentMouseClicked

    private void IMGLIVREMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IMGLIVREMouseClicked
        // TODO add your handling code here:
        /*JFileChooser pic = new JFileChooser();
        pic.showOpenDialog(null);

        File picture = pic.getSelectedFile();

        path = picture.getAbsolutePath();
        BufferedImage img;
        /*try {
            img = ImageIO.read(pic.getSelectedFile());
            ImageIcon imageic = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT));
            image1.setIcon(imageic);
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];

            for (int i; (i = fis.read(buff)) != -1;) {
                bos.write(buff, 0, i);
            }

            userimage = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        JFileChooser pic = new JFileChooser();
        FileNameExtensionFilter fnx = new FileNameExtensionFilter("PNG JPG MP4 AND JPEG","png","mp4","jpeg","jpg");
        pic.addChoosableFileFilter(fnx);
        int load = pic.showOpenDialog(null);
        if(load ==pic.APPROVE_OPTION)
        {
            f = pic.getSelectedFile();
            path = f.getAbsolutePath();
            //imagePaths.setText(path);
            ImageIcon image = new ImageIcon(path);
            Image img = image.getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT);
            IMGLIVRE.setIcon(new ImageIcon(img));
        }
                                      
    }//GEN-LAST:event_IMGLIVREMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        dashboard hm = new dashboard();
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void welcomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcomeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_welcomeMouseClicked

    private void empruntbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empruntbuttonActionPerformed
        
        try {
            // TODO add your handling code here:

            if (QUANTITE.getText().equals("0")) {
                JOptionPane.showMessageDialog(this, "ce livre n'est plus disponible");
            } else {
                if (isAlreadyIssued() == false) {
                    int bookId = Integer.parseInt(txt_bookid.getText());
                    String bookName = TXTLIVRE.getText();
                    int studentId = Integer.parseInt(txt_studentid.getText());
                    String studentName = nomadherent.getText();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String issuedate = df.format(dateemprunt.getDatoFecha());
                    String duedate = df.format(dateretour.getDatoFecha());

                    pst = con.prepareStatement("insert into emprunts(emprunteurs,nom_adhe,livre_empruntés,titre_liv,date_emprumts,retour_norm,status)values(?,?,?,?,?,?,?)");
                    
                    pst.setInt(1, studentId);
                    pst.setString(2, studentName);
                    pst.setInt(3, bookId);
                    pst.setString(4, bookName);
                    pst.setString(5, issuedate);
                    pst.setString(6, duedate);
                    pst.setString(7, "emprunté");

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Emprunts enregistrer ...");
                    updateBookCount();

                } else {
                    JOptionPane.showMessageDialog(this, "Cet Etudiant a deja emprunté un livre");
                }

            }

            txt_bookid.setText("");
            txt_studentid.setText("");
            dateretour.setDatoFecha(null);
            dateemprunt.setDatoFecha(null);

        } catch (SQLException ex) {
            Logger.getLogger(FAIRE_emprunts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_empruntbuttonActionPerformed

    private void EFFACERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EFFACERActionPerformed
        // TODO add your handling code here:
        
        
          IDLIVRE.setText("");
                TXTLIVRE.setText("");
                AUTEUR.setText("");
                QUANTITE.setText("");

        
         idadherent.setText("");
                nomadherent.setText("");
                prenomadherent.setText("");
                lbl_studentid.setText("");
        
        txterrorstudent.setText("");
        txt_bookid.setText("");
        txt_studentid.setText("");
        dateemprunt.setDatoFecha(null);
        dateretour.setDatoFecha(null);
    }//GEN-LAST:event_EFFACERActionPerformed

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
         if (!txt_studentid.getText().equals("")) {
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentidFocusLost

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel22MouseClicked

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
            java.util.logging.Logger.getLogger(FAIRE_emprunts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FAIRE_emprunts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FAIRE_emprunts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FAIRE_emprunts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FAIRE_emprunts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AUTEUR;
    private rojerusan.RSMaterialButtonRectangle EFFACER;
    private javax.swing.JLabel IDLIVRE;
    private javax.swing.JLabel IMGLIVRE;
    private javax.swing.JLabel QUANTITE;
    private javax.swing.JLabel TXTLIVRE;
    private rojeru_san.componentes.RSDateChooser dateemprunt;
    private rojeru_san.componentes.RSDateChooser dateretour;
    private rojerusan.RSMaterialButtonRectangle empruntbutton;
    private javax.swing.JLabel idadherent;
    private javax.swing.JLabel image2;
    private javax.swing.JLabel image3;
    private javax.swing.JLabel imgadherent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_bookid2;
    private javax.swing.JLabel lbl_bookid3;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel nomadherent;
    private javax.swing.JLabel prenomadherent;
    private javax.swing.JLabel txt_bookerror;
    private javax.swing.JLabel txt_bookerror1;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentid;
    private javax.swing.JLabel txterrorstudent;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
