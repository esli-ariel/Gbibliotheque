/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gbibliotheque.jframe;

import com.mysql.cj.jdbc.Blob;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jframe.ManageBooks;

/**
 *
 * @author kadio
 */
public class Glivres extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;
     String path = null;
     File f = null;
      private  ImageIcon format =null;
    byte userimage[] = null;
    DefaultTableModel model =new DefaultTableModel();
     String code ;
     String titre ;
     String auteur;
     String dparution;
     String img_liv ;
     String nb_exemplaire ;
    /**
     * Creates new form Glivres
     */
    public Glivres() {
        initComponents();
        setTitle("Gestion de livres");
        Tablelivre();
        showTime();
        showdate();
        JTextField G = login.Userc;
        
        welcome.setText(G.getText());
        
          model.addColumn("code");
 model.addColumn("titre");
model.addColumn("auteur");
model.addColumn("date_parution ");
model.addColumn("img_liv");
model.addColumn("nb_exemplaires");

        image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image1.setBounds(530, 60, 100, 100);
    }
    // Database connectivity
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void Tablelivre(){
    
        String []livres ={"Code","titre_liv","auteur_liv","date_parution","img_liv","nb_exemplaires"};
    
        DefaultTableModel model = new DefaultTableModel(null,livres);
        String sql ="select * from livres";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            pst  = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                 Object o[]={
                    rs.getString("code_liv"),
                    rs.getString("titre_liv"),
                    rs.getString("auteur_liv"),
                    rs.getString("date_parution"),
                    rs.getBlob("img_liv"),
                    rs.getString("nb_exemplaires")
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
            byte imageData[] =rs.getBytes("img_liv");
                
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
            pst = con.prepareStatement("SELECT * from livres where code_liv=? ");
            pst.setString(1, index);
            rs = pst.executeQuery();
            
            if(rs.next()==false)
            {
              
              JOptionPane.showMessageDialog(this, "Aucune photo trouvée");
                 txtcode.setText("");
                txttitre.setText("");
                txtauteur.setText("");
                 txtparution.setText("");
                image1.setText("");
                txtexemplaire.setText("");
            }else{
                
               
                 loadimg();
            }
        }catch(Exception e)
        {}
           
           }

     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttitre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtauteur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Ajouter = new javax.swing.JButton();
        recherche = new javax.swing.JButton();
        txtparution = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        supprimer = new javax.swing.JButton();
        modifier = new javax.swing.JButton();
        txtexemplaire = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        image1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        timelabel1 = new javax.swing.JTextField();
        datelabel1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        welcome = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTION LIVRES");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Code");

        txtcode.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Titre");

        txttitre.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txttitre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Auteur");

        txtauteur.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtauteur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Séléctionner une ");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("photo depuis ce bouton ->");

        Ajouter.setBackground(new java.awt.Color(0, 255, 204));
        Ajouter.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        Ajouter.setForeground(new java.awt.Color(255, 255, 255));
        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });

        recherche.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        recherche.setText("Recherche");
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        txtparution.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtparution.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Date parution");

        supprimer.setBackground(new java.awt.Color(0, 255, 204));
        supprimer.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        supprimer.setForeground(new java.awt.Color(255, 255, 255));
        supprimer.setText("Supprimer");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        modifier.setBackground(new java.awt.Color(0, 255, 204));
        modifier.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        modifier.setForeground(new java.awt.Color(255, 255, 255));
        modifier.setText("Modifier");
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });

        txtexemplaire.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        txtexemplaire.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204)));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Quantité");

        image1.setBackground(new java.awt.Color(0, 255, 204));
        image1.setFont(new java.awt.Font("Yu Gothic Light", 0, 18)); // NOI18N
        image1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        image1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                image1MouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addGap(158, 158, 158)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addComponent(modifier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttitre, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtexemplaire, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtparution, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(image1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtcode))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txttitre))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtparution, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtexemplaire, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(image1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(60, 60, 60)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ajouter)
                    .addComponent(modifier))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recherche)
                    .addComponent(supprimer)))
        );

        timelabel1.setBackground(new java.awt.Color(0, 255, 204));
        timelabel1.setText("time:");

        datelabel1.setBackground(new java.awt.Color(0, 255, 204));
        datelabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        datelabel1.setText("Date:");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel2.setText("Bienvenue");

        welcome.setBackground(new java.awt.Color(0, 255, 204));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(timelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(datelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton3))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        txtcode.setText("");
        txttitre.setText("");
        txtauteur.setText("");
        txtparution.setText("");
        image1.setText("");
        txtexemplaire.setText("");
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        int selectionner = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // la variable g est le numero de le code de chaque livre selectionné
        String g= model.getValueAt(selectionner, 0).toString();
        // Ce sont les informations qu'on va renvoyer sur le formulaire
        txtcode.setText(model.getValueAt(selectionner, 0).toString());
        txttitre.setText(model.getValueAt(selectionner, 1).toString());
        txtauteur.setText(model.getValueAt(selectionner, 2).toString());
        txtparution.setText(model.getValueAt(selectionner, 3).toString());
        txtexemplaire.setText(model.getValueAt(selectionner, 5).toString());
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            String sql = "select * from livres where code_liv=?";
            pst  = con.prepareStatement(sql);
            pst.setString(1, txtcode.getText());
            rs = pst.executeQuery();
            if (rs.next()== false) {

                JOptionPane.showMessageDialog(null, "Matricule n'existe pas");

            }
            else{
                txttitre.setText(rs.getString(2).trim());
                txtauteur.setText(rs.getString(3).trim());
                Blob blob1 = (Blob) rs.getBlob("img_liv");
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

    private void image1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_image1MouseClicked
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
            image1.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_image1MouseClicked

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
        try{
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            String sql = "insert into livres (code_liv,titre_liv,auteur_liv,date_parution,img_liv,nb_exemplaires) values(?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, txtcode.getText());
            pst.setString(2, txttitre.getText());
            pst.setString(3,txtauteur.getText() );
            pst.setString(4, txtparution.getText());
            pst.setBlob(5,fis);
            pst.setString(6, txtexemplaire.getText());
            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Enregistrement Réussi");
            Tablelivre();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "erreur " +e.getMessage());
            e.printStackTrace();

        }

    }//GEN-LAST:event_AjouterActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            String sql = "select * from livres where code_liv=?";
            pst  = con.prepareStatement(sql);
            pst.setString(1, txtcode.getText());
            rs = pst.executeQuery();
            if (rs.next()== false) {

                JOptionPane.showMessageDialog(null, "Matricule n'existe pas");

            }
            else{
                txttitre.setText(rs.getString(2).trim());
                txtauteur.setText(rs.getString(3).trim());
                Blob blob1 = (Blob) rs.getBlob("img_liv");
                byte[] imagebyte = blob1.getBytes(1, (int) blob1.length());

                ImageIcon image = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(135, 127, Image.SCALE_DEFAULT));
                image1.setIcon(image);
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erreur " +e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_rechercheActionPerformed

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        // TODO add your handling code here:
        /* int i = jTable1.getSelectedRow();
        DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel();
        code= tb1model.getValueAt(i, 0).toString();
        titre= tb1model.getValueAt(i, 1).toString();
        auteur = tb1model.getValueAt(i, 2).toString();
        dparution = tb1model.getValueAt(i, 3).toString();
        userimage = (byte[]) tb1model.getValueAt(i, 6);
        nb_exemplaire = (String) tb1model.getValueAt(i, 4);

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            String query = "delete from livres where code_liv=?";
            pst = con.prepareStatement(query);
            pst.setString(1, this.code);
            pst.execute();

            JOptionPane.showMessageDialog(null, "utilisateur supprimé");

            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");
            String query1 = "delete from livres where code_liv =?";
            pst = con.prepareStatement(query1);
            pst.setString(1, this.code);
            pst.execute();

            JOptionPane.showMessageDialog(null, "utilisateur supprimé");
            Tablelivre();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }*/
        int i = jTable1.getSelectedRow();
        DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel();
        code = tb1model.getValueAt(i, 0).toString();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_bibliothèque", "bib_admin", "2006");

            String query = "DELETE FROM livres WHERE code_liv=?";
            pst = con.prepareStatement(query);
            pst.setString(1, code);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Livre supprimé");

            // Rafraîchir la table après suppression
            Tablelivre();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_supprimerActionPerformed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        // TODO add your handling code here:
        /*   String code_liv = txtcode.getText();
        String titre_liv = txttitre.getText();
        String auteur_liv = txtauteur.getText();
        String date_parution = txtauteur.getText();
        String img_liv = image1.getText();
        String nb_exemplaires = txtexemplaire.getText();

        try {
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("update livres set code_liv=?,titre_liv=?,auteur_liv=?,date_parution=?,img_liv=?,nb_exemplaires=? where code_liv=? or img_liv=?");
            pst.setString(0, code_liv);
            pst.setString(1, titre_liv);
            pst.setString(2, auteur_liv);
            pst.setString(3,date_parution );
            pst.setBlob(4, fis);
            pst.setString(5, nb_exemplaires);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "modifié");
            Tablelivre();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }
        try {

            con = DriverManager.getConnection();
            String query1 = "delete from livre where isbn =?";
            pst = con.prepareStatement(query1);
            pst.setString(1, this.code_liv);
            pst.execute();

            JOptionPane.showMessageDialog(null, "livre supprimé");

            con.close();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);
        }
        */
        String code_liv = txtcode.getText();
        String titre_liv = txttitre.getText();
        String auteur_liv = txtauteur.getText();
        String date_parution = txtparution.getText(); // Suppose que txtdate contient la date de parution
        
        String nb_exemplaires = txtexemplaire.getText();

        try {
            File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_bibliothèque", "bib_admin", "2006");
            pst = con.prepareStatement("UPDATE livres SET code_liv=?,titre_liv=?, auteur_liv=?, date_parution=?, img_liv=?, nb_exemplaires=? WHERE code_liv=?");
            pst.setString(1, code_liv);
            pst.setString(2, titre_liv);
            pst.setString(3, auteur_liv);
            pst.setString(4, date_parution);
            pst.setBlob(5, fis);
            pst.setString(6, nb_exemplaires);
            //pst.setString(7, code_liv);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modifié");
            Tablelivre();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Glivres.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_modifierActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        dashboard g=new dashboard();
        g.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(Glivres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Glivres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Glivres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Glivres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Glivres().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JTextField datelabel1;
    private javax.swing.JLabel image1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton modifier;
    private javax.swing.JButton recherche;
    private javax.swing.JButton supprimer;
    private javax.swing.JTextField timelabel1;
    private javax.swing.JTextField txtauteur;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtexemplaire;
    private javax.swing.JTextField txtparution;
    private javax.swing.JTextField txttitre;
    private javax.swing.JTextField welcome;
    // End of variables declaration//GEN-END:variables

    
     

}
