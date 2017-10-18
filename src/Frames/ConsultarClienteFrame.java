package Frames;
import DAL.ModuloConexao;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ConsultarClienteFrame extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public ConsultarClienteFrame() {
        initComponents();
        
        conexao = ModuloConexao.conector();
        consultar();
        //ao teclar enter chama o botao Buscar
        this.getRootPane().setDefaultButton(jButtonBuscar);
        
        //mudando o tamanho das colunas
        jtListaClientes.getColumnModel().getColumn(0).setPreferredWidth(150);
        jtListaClientes.getColumnModel().getColumn(1).setPreferredWidth(10);
        jtListaClientes.getColumnModel().getColumn(2).setPreferredWidth(10);
        jtListaClientes.getColumnModel().getColumn(3).setPreferredWidth(10);
        jtListaClientes.getColumnModel().getColumn(4).setPreferredWidth(10);
        jtListaClientes.getColumnModel().getColumn(5).setPreferredWidth(300);
    }
    
    public void consultar()
    {
        String sql = "select * from tbclientes";
        String condicao = "", nome = "", tipoPessoa="", rg="";
        boolean existe_condicao = false;
        
        if(jTextFieldCpjCnpj.getText().trim().isEmpty() == false)
        {
            String cpfcnpj = jTextFieldCpjCnpj.getText();
            condicao = " where cpfcnpjcli like '%"+cpfcnpj+"%'";
            sql = sql + condicao;
            existe_condicao = true;
        }
        if(jTextFieldNome.getText().trim().isEmpty() == false)
        {
            nome = jTextFieldNome.getText();
            if (existe_condicao){
                condicao = " and nomecli like '%"+nome+"%'";
            }
            else
            {
                condicao = " where nomecli like '%"+nome+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldRG.getText().trim().isEmpty() == false)
        {
            rg = jTextFieldRG.getText();
            if (existe_condicao){
                condicao = " and rgcli like '%"+rg+"%'";
            }
            else
            {
                condicao = " where rgcli like '%"+rg+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            sql = sql+" order by nomecli";
            pst = conexao.prepareStatement(sql);
           
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            
            DefaultTableModel dtm = (DefaultTableModel)jtListaClientes.getModel();
            dtm.setNumRows(0);
            
            while (rs.next())
            {
                String endereço = rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10); 
                dtm.addRow(new Object[]{rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),endereço}); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void substituirOutorgante(String cpfcnpj)
    {
        String sql = "select * from tbclientes where cpfcnpjcli=?";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cpfcnpj);
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            if (rs.next())
            {
                GeradorProcuracoesFrame.jTextFieldOutorgante.setText(rs.getString(2));
                GeradorProcuracoesFrame.jTextFieldRGOutorgante.setText(rs.getString(3));
                GeradorProcuracoesFrame.jFormattedTextCPFOutorgante.setText(rs.getString(4));
                GeradorProcuracoesFrame.jTextFieldProfOutorgante.setText(rs.getString(5));
                GeradorProcuracoesFrame.jComboBoxEstCivilOutorgante.setSelectedItem(rs.getString(6));
                GeradorProcuracoesFrame.jTextFieldEndOutorgante.setText(rs.getString(7));
                GeradorProcuracoesFrame.jTextFieldNumOutorgante.setText(rs.getString(8));
                GeradorProcuracoesFrame.jTextFieldBairroOutorgante.setText(rs.getString(9));
                GeradorProcuracoesFrame.jTextFieldCidadeOutorgante.setText(rs.getString(10));
                GeradorProcuracoesFrame.jComboBoxSexoOutorgante.setSelectedItem(rs.getString(11));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void substituirOutorgado(String cpfcnpj)
    {
        String sql = "select * from tbclientes where cpfcnpjcli=?";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cpfcnpj);
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            if (rs.next())
            {
                GeradorProcuracoesFrame.jTextFieldOutorgado.setText(rs.getString(2));
                GeradorProcuracoesFrame.jTextFieldRGOutorgado.setText(rs.getString(3));
                GeradorProcuracoesFrame.jFormattedTextCPFOutorgado.setText(rs.getString(4));
                GeradorProcuracoesFrame.jTextFieldProfOutorgado.setText(rs.getString(5));
                GeradorProcuracoesFrame.jComboBoxEstCivilOutorgado.setSelectedItem(rs.getString(6));
                GeradorProcuracoesFrame.jTextFieldEndOutorgado.setText(rs.getString(7));
                GeradorProcuracoesFrame.jTextFieldNumOutorgado.setText(rs.getString(8));
                GeradorProcuracoesFrame.jTextFieldBairroOutorgado.setText(rs.getString(9));
                GeradorProcuracoesFrame.jTextFieldCidadeOutorgado.setText(rs.getString(10));
                GeradorProcuracoesFrame.jComboBoxSexoOutorgado.setSelectedItem(rs.getString(11));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void substituirPJ(String cpfcnpj)
    {
        String sql = "select * from tbclientes where cpfcnpjcli=?";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cpfcnpj);
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            if (rs.next())
            {
                GeradorProcuracoesFrame.jtfNomePJ.setText(rs.getString(2));
                GeradorProcuracoesFrame.jftfCNPJ.setText(rs.getString(4));
                GeradorProcuracoesFrame.jtfSedePJ.setText(rs.getString(7));
                GeradorProcuracoesFrame.jtfNumPJ.setText(rs.getString(8));
                GeradorProcuracoesFrame.jtfBairroPJ.setText(rs.getString(9));
                GeradorProcuracoesFrame.jtfCidadePJ.setText(rs.getString(10));
                GeradorProcuracoesFrame.jtfNIRE.setText(rs.getString(12));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButtonSubOutorgante = new javax.swing.JButton();
        jButtonSubOutorgado = new javax.swing.JButton();
        jButtonSubPJ = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jButtonLimparCampos = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldRG = new javax.swing.JTextField();
        jTextFieldCpjCnpj = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Clientes Cadastrados");

        jtListaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Rg", "Cpf", "Profissao", "Est.Civil", "Endereço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtListaClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtListaClientes);
        if (jtListaClientes.getColumnModel().getColumnCount() > 0) {
            jtListaClientes.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtListaClientes.getColumnModel().getColumn(2).setPreferredWidth(50);
            jtListaClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jLabel2.setText("Cpf/cnpj:");

        jButtonSubOutorgante.setText("Substituir Outorgante");
        jButtonSubOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubOutorganteActionPerformed(evt);
            }
        });

        jButtonSubOutorgado.setText("Substituir Outorgado");
        jButtonSubOutorgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubOutorgadoActionPerformed(evt);
            }
        });

        jButtonSubPJ.setText("Substituir PJ");
        jButtonSubPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubPJActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome:");

        jButtonLimparCampos.setText("Limpar campos");
        jButtonLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparCamposActionPerformed(evt);
            }
        });

        jLabel5.setText("RG:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSubOutorgante)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSubOutorgado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSubPJ))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jButtonBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLimparCampos))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jLabel1)))
                .addGap(0, 311, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCpjCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCpjCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonLimparCampos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSubOutorgante)
                    .addComponent(jButtonSubOutorgado)
                    .addComponent(jButtonSubPJ))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSubOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubOutorganteActionPerformed
        //chamadno o metodo para substituir os campos outorgante e transformando o cpf em string
        try {
            substituirOutorgante(jtListaClientes.getValueAt(jtListaClientes.getSelectedRow(), 2).toString());
            JOptionPane.showMessageDialog(null, "Outorgante substituido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela!");
        }
        
    }//GEN-LAST:event_jButtonSubOutorganteActionPerformed

    private void jButtonSubOutorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubOutorgadoActionPerformed
        // TODO add your handling code here:
        try {
            substituirOutorgado(jtListaClientes.getValueAt(jtListaClientes.getSelectedRow(), 2).toString());
            JOptionPane.showMessageDialog(null, "Outorgado substituido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela!");
        }
        
    }//GEN-LAST:event_jButtonSubOutorgadoActionPerformed

    private void jButtonSubPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubPJActionPerformed
        // TODO add your handling code here:
        try {
            substituirPJ(jtListaClientes.getValueAt(jtListaClientes.getSelectedRow(), 2).toString());
            JOptionPane.showMessageDialog(null, "PJ substituido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela!");
        }
    }//GEN-LAST:event_jButtonSubPJActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparCamposActionPerformed
        // TODO add your handling code here:
        jTextFieldNome.setText("");
        jTextFieldCpjCnpj.setText("");
        jTextFieldRG.setText("");
    }//GEN-LAST:event_jButtonLimparCamposActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarClienteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonLimparCampos;
    private javax.swing.JButton jButtonSubOutorgado;
    private javax.swing.JButton jButtonSubOutorgante;
    private javax.swing.JButton jButtonSubPJ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCpjCnpj;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldRG;
    private javax.swing.JTable jtListaClientes;
    // End of variables declaration//GEN-END:variables
}
