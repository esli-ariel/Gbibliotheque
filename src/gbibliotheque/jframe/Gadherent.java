/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gbibliotheque.jframe;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.SQLException;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author kadio
 */
public class Gadherent extends javax.swing.JFrame {
    
    String path = null;
    byte[] userimage = null;
    Connection con;
    File f = null;
    private  ImageIcon format =null;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;
     DefaultTableModel model =new DefaultTableModel();
    String nom;
    String matricule;
    String prenom;
    String adresse;
    String telephone;
    String id;
    String email;
    String pass;
    Blob img_user;
    /**
     * Creates new form Guser
     */
    public Gadherent() {
        initComponents();
        setIconImage();
        Tableuser();
        showdate();
        showTime();
        JTextField G = login.Userc;
        welcome.setText(G.getText());
        
        model.addColumn("Matricule");
 model.addColumn("nom");
model.addColumn("prenom");
model.addColumn("adresse ");
model.addColumn("email ");
model.addColumn("telephone");
model.addColumn("img_liv");
model.addColumn("nb_exemplaires");

        image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image1.setBounds(530, 60, 100, 100);
        setTitle("Gestion Des Adherents");
    }
     private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));

    }
    void showdate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyy");
        datelabel1.setText(s.format(d));
    }

    void showTime() {
        new Timer(0, new ActionListener() { // pendant 0 secande fait moi une action qui s'appele 'ActionListener'
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                timelabel1.setText(s.format(d));

            }

        }).start();
}
    
    public void loadimg(){
           try{
            byte imageData[] =rs.getBytes("img_user");
                
                format = new ImageIcon(imageData);
                Image mm =format.getImage();
                Image img2 =mm.getScaledInstance(135, 127, Image.SCALE_DEFAULT);
                image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ImageIcon image =new ImageIcon(img2);
                image1.setIcon(image);
    }catch(Exception e){
        
    }
    }
    public void recherchePhpto( String index )
           {
                try{
            pst = con.prepareStatement("SELECT * from adherents where matricule_user=? ");
            pst.setString(1, index);
            rs = pst.executeQuery();
            
            if(rs.next()==false)
            {
              
              JOptionPane.showMessageDialog(this, "Aucune photo trouvée");
                 txtcode.setText("");
                txtnom.setText("");
                txtprenom.setText("");
                 txtadresse.setText("");
                image1.setText("");
                txtemail.setText("");
                txttelephone.setText("");
            }else{
                
               
                 loadimg();
            }
        }catch(Exception e)
        {}
           
           }
    
     public boolean checkDublicateStudent() {
        boolean isExits = false;
        try {
            String id = txtcode.getText();

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
            pst = con.prepareStatement("select * from adherents where matricule_user=?");
            pst.setString(1, id);

            rs = pst.executeQuery();
            if (rs.next()) {
                isExits = true;

            } else {
                isExits = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExits;

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtprenom = new javax.swing.JTextField();
        image1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        txtadresse = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttelephone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Supprimer = new javax.swing.JButton();
        Modifier = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        rechercher = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        timelabel1 = new javax.swing.JTextField();
        datelabel1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        welcome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("X");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel2FocusLost(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Matricule");

        txtcode.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));
        txtcode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcodeFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nom");

        txtnom.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtnom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Prenom");

        txtprenom.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtprenom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        image1.setBackground(new java.awt.Color(0, 255, 204));
        image1.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("photo  ->");

        Ajouter.setBackground(new java.awt.Color(0, 255, 204));
        Ajouter.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        Ajouter.setForeground(new java.awt.Color(255, 255, 255));
        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });

        txtadresse.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtadresse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Adresse");

        txttelephone.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txttelephone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Telephone");

        Supprimer.setBackground(new java.awt.Color(0, 255, 204));
        Supprimer.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        Supprimer.setForeground(new java.awt.Color(255, 255, 255));
        Supprimer.setText("Supprimer");
        Supprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SupprimerMouseClicked(evt);
            }
        });
        Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerActionPerformed(evt);
            }
        });

        Modifier.setBackground(new java.awt.Color(0, 255, 204));
        Modifier.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        Modifier.setForeground(new java.awt.Color(255, 255, 255));
        Modifier.setText("Modifier");
        Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel2.setText("Email");

        txtemail.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gbibliotheque/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jButton1.setText("Retour");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        rechercher.setText("RECHERCHER");
        rechercher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rechercherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtadresse, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Modifier, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(rechercher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(40, 40, 40)
                            .addComponent(image1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10))
                            .addGap(216, 216, 216)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnom)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtadresse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(image1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel9)))
                        .addGap(89, 89, 89))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Modifier)
                            .addComponent(Ajouter))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Supprimer)
                    .addComponent(rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Titre", "Auteur", "date parution", "Image", "nb exemplaires"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTION UTILISATEURS");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        timelabel1.setBackground(new java.awt.Color(0, 255, 204));
        timelabel1.setText("time:");

        datelabel1.setBackground(new java.awt.Color(0, 255, 204));
        datelabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        datelabel1.setText("Date:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel7.setText("Bienvenue");

        welcome.setBackground(new java.awt.Color(0, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(timelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(datelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void image1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image1MouseClicked
        /*  JFileChooser pic = new JFileChooser();
        pic.showOpenDialog(null);

        File picture = pic.getSelectedFile();

        path = picture.getAbsolutePath();
        BufferedImage img;
        try {
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
            image1.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_image1MouseClicked

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
       
         String code = txtcode.getText();
        String nom = txtnom.getText();
        String prenom = txtprenom.getText();
        String adresse = txtadresse.getText();
        String telephone = txttelephone.getText();
        String email = txtemail.getText();

        try {
            
            
        File image = new File(path);
        //FileInputStream fis = new FileInputStream(image);
           
           
            FileInputStream fis = new FileInputStream(image);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("insert into adherents (nom_user,prenom_user,adresse,email,telephone,img_user)" + "values(?,?,?,?,?,?)");
            pst.setString(1, code);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setString(4, adresse);
            pst.setString(5, telephone);
            pst.setString(6, email);
            pst.setBlob(7, fis);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Adherents ajouter avec succès!");
            
            
            txtcode.setText("");
         txtnom.setText("");
        txtprenom.setText("");
        txtadresse.setText("");
        txttelephone.setText("");
        txtemail.setText("");
            
            Tableuser();
            con.close();
        } catch (SQLException ex) {
           
            Logger.getLogger(Gadherent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gadherent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gadherent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AjouterActionPerformed

    private void SupprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupprimerMouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel();
        matricule = tb1model.getValueAt(i, 0).toString();
        nom = tb1model.getValueAt(i, 1).toString();
        prenom = tb1model.getValueAt(i, 2).toString();
        adresse = tb1model.getValueAt(i, 3).toString();
        telephone = tb1model.getValueAt(i, 4).toString();
        email = tb1model.getValueAt(i, 5).toString();
        img_user =  (Blob) tb1model.getValueAt(i, 6);
       
        try {
            tb1model =(DefaultTableModel)jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();
            
             String cde =d.getValueAt(i, 0).toString();
             String name =txtnom.getText();
             String prename =txtprenom.getText();
             String adress =txtadresse.getText();
             String tel =txttelephone.getText();
             String mail =txtemail.getText();
            
             
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            String query = "delete from adherents where matricule_user=?";
            pst = con.prepareStatement(query);
            pst.setString(1, cde);
            pst.setString(2, name);
            
            
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "utilisateur supprimé");

            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            String query1 = "delete from livre where matricule_user =?";
            pst = con.prepareStatement(query1);
            pst.setString(1, this.matricule);
            pst.execute();

            JOptionPane.showMessageDialog(null, "utilisateur supprimé");

            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }
    }//GEN-LAST:event_SupprimerMouseClicked

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_SupprimerActionPerformed

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
        // TODO add your handling code here:
        boolean isUpdated =false;
        int i = jTable1.getSelectedRow();
        DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel();
        matricule = txtcode.getText();
        //integer.parseInt(txtcode.getText());
        nom = txtnom.getText();
        prenom = txtprenom.getText();
        adresse = txtadresse.getText();
        telephone = txttelephone.getText();
        email = txtemail.getText();
        
        
        try {
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            String query = "update adherents set matricule_user=?,nom_user=?,prenom_user=?,adresse=?,email=?,telephone=?,img_user=? where matricule_user=?";
            pst = con.prepareStatement(query);
            pst.setString(1, this.matricule);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setString(4, adresse);
            pst.setString(5, telephone);
            pst.setString(6, email);
            pst.setBlob(7, fis);
             
            pst.execute();

            JOptionPane.showMessageDialog(null, "utilisateur modifié");

            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            String query1 = "delete from livre where matricule_user =?";
            pst = con.prepareStatement(query1);
            pst.setString(1, this.matricule);
            pst.execute();

            JOptionPane.showMessageDialog(null, "utilisateur modifié");

            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }
    }//GEN-LAST:event_ModifierActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        int selectionner = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // la variable g est le numero de le code de chaque livre selectionné
        String g= model.getValueAt(selectionner, 0).toString();
        // Ce sont les informations qu'on va renvoyer sur le formulaire
        txtcode.setText(model.getValueAt(selectionner, 0).toString());
        txtnom.setText(model.getValueAt(selectionner, 1).toString());
        txtprenom.setText(model.getValueAt(selectionner, 2).toString());
        txtadresse.setText(model.getValueAt(selectionner, 3).toString());
        txtemail.setText(model.getValueAt(selectionner, 5).toString());
        txttelephone.setText(model.getValueAt(selectionner, 5).toString());
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            String sql = "select * from adherents where matricule_user=?";
            pst  = con.prepareStatement(sql);
            pst.setString(1, txtcode.getText());
            rs = pst.executeQuery();
            if (rs.next()== false) {

                JOptionPane.showMessageDialog(null, "Matricule n'existe pas");

            }
            else{
                txtnom.setText(rs.getString(2).trim());
                txtprenom.setText(rs.getString(3).trim());
                txtadresse.setText(rs.getString(4).trim());
                txtemail.setText(rs.getString(5).trim());
                txttelephone.setText(rs.getString(6).trim());
                Blob blob1 = rs.getBlob("img_user");
                byte[] imagebyte = blob1.getBytes(1, (int) blob1.length());

                ImageIcon image = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT));
                image1.setIcon(image);
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erreur " +e.getMessage());
            e.printStackTrace();
        }
        recherchePhpto(g);
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        dashboard d =new dashboard();
        d.setVisible(true);
        d.pack();
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
         txtcode.setText("");
                txtnom.setText("");
                txtprenom.setText("");
                 txtadresse.setText("");
                image1.setText("");
                txtemail.setText("");
                txttelephone.setText("");
    }//GEN-LAST:event_jLabel1MouseClicked

    private void rechercherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechercherMouseClicked
        // TODO add your handling code here:
        try {

           
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            String sql = "select * from adherents where matricule_user=?";
            pst  = con.prepareStatement(sql);
            pst.setString(1, txtcode.getText());
            rs = pst.executeQuery();
            if (rs.next()== false) {

                JOptionPane.showMessageDialog(null, "Matricule n'existe pas");

            }
            else{
                txtnom.setText(rs.getString(2).trim());
                txtprenom.setText(rs.getString(3).trim());
                txtadresse.setText(rs.getString(4).trim());
                txtemail.setText(rs.getString(5).trim());
                txttelephone.setText(rs.getString(6).trim());
                com.mysql.cj.jdbc.Blob blob1 = (com.mysql.cj.jdbc.Blob) rs.getBlob("img_liv");
                byte[] imagebyte = blob1.getBytes(1, (int) blob1.length());

                ImageIcon image = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT));
                image1.setIcon(image);
            }
            con.close();
        } catch (SQLException ex) {
           Logger.getLogger(Gadherent.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }//GEN-LAST:event_rechercherMouseClicked

    private void jPanel2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel2FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel2FocusLost

    private void txtcodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodeFocusLost
        // TODO add your handling code here
        if (checkDublicateStudent() == true) {
            JOptionPane.showMessageDialog(this, "l'adherent existe deja!");
            txtcode.setText("");

        }
    }//GEN-LAST:event_txtcodeFocusLost

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
            java.util.logging.Logger.getLogger(Gadherent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gadherent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gadherent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gadherent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gadherent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JButton Modifier;
    private javax.swing.JButton Supprimer;
    private javax.swing.JTextField datelabel1;
    private javax.swing.JLabel image1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton rechercher;
    private javax.swing.JTextField timelabel1;
    private javax.swing.JTextField txtadresse;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtprenom;
    private javax.swing.JTextField txttelephone;
    private javax.swing.JTextField welcome;
    // End of variables declaration//GEN-END:variables

    
      public void Tableuser(){
    
        String []user ={"matricule_user", "nom_user", "prenom_user", "adresse","email", "telephone", "img_user"};
    
        DefaultTableModel model = new DefaultTableModel(null,user);
        String sql ="select * from adherents";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            pst  = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                 Object o[]={
                    rs.getString("matricule_user"),
                    rs.getString("nom_user"),
                    rs.getString("prenom_user"),
                    rs.getString("adresse"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getBlob("img_user")
                    };
                 model.addRow(o);
            }
            jTable1.setModel(model);
            con.close();
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "erreur " +e.getMessage());
            e.printStackTrace();
        }   
    }


}
