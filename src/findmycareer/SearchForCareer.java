/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findmycareer;

import static findmycareer.FMCdb.getCareers;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * Andrei Rico
 * @author 3106107616
 */
public class SearchForCareer extends javax.swing.JFrame {
    private Client client;
    private JFrame login_screen;
    private Industry industry;
    private SubCategory subCategory;
    private Option option;
    private Career car;
    /**
     * Creates new form SearchForCareer
     */
    
        public SearchForCareer() {
       initComponents();
      // client = FMCdb.getClientById(1);
       ArrayList<Industry> industyList = null;
       dropDownBoxIndustry.removeAllItems();
       dropDownBoxIndustry.addItem("Select Industry...");
      try{
         industyList  = FMCdb.getIndustries();
         for (int i = 0; i < industyList.size(); i++){
              dropDownBoxIndustry.addItem(industyList.get(i).cbxString());
          }
       }catch (Exception e){
       
       }

    }
    public SearchForCareer(JFrame login, Client c) {
       initComponents();
      // client = FMCdb.getClientById(1);
       
       login_screen = login;
       client = c;
       ArrayList<Industry> industyList = null;
       dropDownBoxIndustry.removeAllItems();
       dropDownBoxIndustry.addItem("Select Industry...");
      try{
         industyList  = FMCdb.getIndustries();
         for (int i = 0; i < industyList.size(); i++){
              dropDownBoxIndustry.addItem(industyList.get(i).cbxString());
          }
       }catch (Exception e){
       
       }

    }
    
    
     private int cbxGetID(JComboBox cbx){
        //String selected = (String) cbx.getSelectedItem();
        int id = Integer.parseInt(cbx.getSelectedItem().toString().split(" ")[0]);
        return id;
    }
           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dropDownBoxSubCategory = new javax.swing.JComboBox();
        dropDownBoxPathway = new javax.swing.JComboBox();
        dropDownBoxOutcome = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dropDownBoxIndustry = new javax.swing.JComboBox();
        jlabel1 = new javax.swing.JLabel();
        category = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dropDownBoxSubCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropDownBoxSubCategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                getPathway(evt);
            }
        });

        dropDownBoxPathway.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropDownBoxPathway.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                getOutcome(evt);
            }
        });

        dropDownBoxOutcome.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setBackground(new java.awt.Color(204, 255, 255));
        btnSearch.setText("<html><b><font color = \"blue\"> Select</b></font>\n</html>");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedCareerClick(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setText("<html>\n<b><font color=\"blue\">Modify Account</b>\n\n</html>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtn(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setText("<html>\n<b><font color=\"blue\">Sign-Off</b></font>\n</html>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOff(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html> <h1> <font color = \"blue\">Search for you next career</font></h1>  <b>Get Started!</b> Select your search criteria below. You can select as many as you like. <br>\nThe more fields you select, the more accurate your initial search result will be.     </html>");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setIconTextGap(0);

        dropDownBoxIndustry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropDownBoxIndustry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                getSub(evt);
            }
        });

        jlabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabel1.setText("Industry:");

        category.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        category.setText("Sub-Category:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Career Pathway:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Possible Job Outcome:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dropDownBoxIndustry, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dropDownBoxOutcome, 0, 179, Short.MAX_VALUE)
                                .addComponent(dropDownBoxPathway, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dropDownBoxSubCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(category)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jlabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropDownBoxIndustry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(category)
                .addGap(13, 13, 13)
                .addComponent(dropDownBoxSubCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addComponent(dropDownBoxPathway, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(13, 13, 13)
                .addComponent(dropDownBoxOutcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("<html> <h1> <font color = \"blue\">Search for you next career</font></h1>  <b>Get Started!</b> Select your search criteria below. You can select as many as you like. The more fields you select, the more accurate your initial search result will be.     </html>");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectedCareerClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedCareerClick
        // TODO add your handling code here:
        
         
         
             if(Validate.isCbxValid(dropDownBoxPathway, "Select Career Pathway...")){
                int id = cbxGetID(dropDownBoxPathway);
                Career career = FMCdb.getCareerById(id);
               new SelectedCareer(this, career, client, industry, subCategory, option).setVisible(true);
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
     
    }//GEN-LAST:event_selectedCareerClick

    private void logOff(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOff
        // TODO add your handling code here:
        login_screen.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.dispose();
        
    }//GEN-LAST:event_logOff

    private void getSub(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_getSub
        // TODO add your handling code here:
        
        dropDownBoxSubCategory.removeAllItems();
        dropDownBoxSubCategory.addItem("Select Sub-Category...");
        if(Validate.isCbxValid(dropDownBoxIndustry, "Select Industry...")){
                dropDownBoxSubCategory.removeAllItems();
                dropDownBoxSubCategory.addItem("Select Sub-Category...");
                int id = cbxGetID(dropDownBoxIndustry);
                industry = FMCdb.getIndustryById(id);
                ArrayList<SubCategory> subCatList = FMCdb.getSubCategoryByIndustryId(id);
                 
                for(int i = 0; i < subCatList.size(); i++){
                    dropDownBoxSubCategory.addItem(subCatList.get(i).cbxString());
                   
                    
                }
            }
    }//GEN-LAST:event_getSub

    private void getPathway(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_getPathway
        // TODO add your handling code here:
        
            dropDownBoxPathway.removeAllItems();
            dropDownBoxPathway.addItem("Select Career Pathway...");
         if(Validate.isCbxValid(dropDownBoxSubCategory, "Select Sub-Category...")){
                dropDownBoxPathway.removeAllItems();
                dropDownBoxPathway.addItem("Select Career Pathway...");
                int id = cbxGetID(dropDownBoxSubCategory);
                subCategory = FMCdb.getSubCategoryById(id);
                ArrayList<Option> optionList = FMCdb.getOptionBySubCatId(id);
                for(int i = 0; i < optionList.size(); i++){
                    dropDownBoxPathway.addItem(optionList.get(i).cbxString());
                }
            }
    }//GEN-LAST:event_getPathway

    private void getOutcome(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_getOutcome
        // TODO add your handling code here:
        
        dropDownBoxOutcome.removeAllItems();
        dropDownBoxOutcome.addItem("Select Possible Job Outcome...");
        if(Validate.isCbxValid(dropDownBoxPathway, "Select Career Pathway...")){
                dropDownBoxOutcome.removeAllItems();
                dropDownBoxOutcome.addItem("Select Possible Job Outcome...");
                int id = cbxGetID(dropDownBoxPathway);
                option = FMCdb.getOptionById(id);
                ArrayList<Career> careerList = FMCdb.getCareers();
                for(int i = 0; i < careerList.size(); i++){
                    dropDownBoxOutcome.addItem(careerList.get(i).cbxString());
                }
            }
    }//GEN-LAST:event_getOutcome

    private void updateBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtn
        // TODO add your handling code here: 
         new UpdateClient(this, client).setVisible(true);
         this.setVisible(false);
         //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_updateBtn
    
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
            java.util.logging.Logger.getLogger(SearchForCareer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchForCareer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchForCareer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchForCareer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
           
            
            public void run() {
                new SearchForCareer().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel category;
    private javax.swing.JComboBox dropDownBoxIndustry;
    private javax.swing.JComboBox dropDownBoxOutcome;
    private javax.swing.JComboBox dropDownBoxPathway;
    private javax.swing.JComboBox dropDownBoxSubCategory;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jlabel1;
    // End of variables declaration//GEN-END:variables

    private int cbxGetID(JButton btnSearch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
