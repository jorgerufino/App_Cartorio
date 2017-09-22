/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Testes.GeneratorPDF;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Michele Andrade
 */
public class ProtocoloFrame extends javax.swing.JFrame {

    /**
     * Creates new form ProtocoloFrame
     */
    
    //Lista todos os documentos da pasta protocolo e pega os 3 primeiros Strings(no caso são o numero do protocolo
    String pastaProtocolo ="D:\\Michele Andrade\\Desktop\\PROCURAÇÃO PÚBLICA\\PROCURACAO PUBLICA 2017\\PROTOCOLO";
    File file = new File(pastaProtocolo);
    File[] arquivos = file.listFiles();
    boolean protocoloExistente;
    
    public ProtocoloFrame() {
        initComponents();
        
        //insere título na Janela do aplicativo
        super.setTitle("Cadastro de Procolo de Procuração - Cartório do Único Ofício de Benevides");
        
        //desabilita botao maximizar da janela
        super.setResizable(false);
                
        //pega a data atual e inseri no campo Previsão de Entrega        
        Date data =  new Date();
        Locale local = new Locale("pt","BR");
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy",local);
        String data_atual=""+formato.format(data);
        jFormattedTextDataEntrega.setText(data_atual);
        
        //Pega o nome do ultimo arquivo da pasta
        String teste = ""+arquivos[arquivos.length-1].getName();
        //transforma as 3 primeiras letras do nome do arquivo (numeros) em inteiro e soma mais 1 (proximo protocolo a ser usado)
        int indiceProtocolo = Integer.parseInt(teste.substring(0, 3)) + 1;
        //transforma em String para poder adicionar no TextField (jTextField so aceita String)
        jTextFieldProtocolo.setText(String.valueOf(indiceProtocolo));

        //centraliza o janela no meio da tela
        this.setLocationRelativeTo(null);  
        
        //ao teclar Enter chama o botão de Confirmar
        getRootPane().setDefaultButton(jButtonCadastrar);
        
        //Chama o foco para o campo Requerente
        jTextFieldRequerente.requestFocusInWindow();
        
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
        jTextFieldProtocolo = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter apenasNumeros = new javax.swing.text.MaskFormatter("###");

            jTextFieldProtocolo = new javax.swing.JFormattedTextField(apenasNumeros);
        }catch(Exception e){
        }
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAutenticacoes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTipoProcuracao = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDiligencia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabelBrasao = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextDataEntrega = new javax.swing.JFormattedTextField();
        jTextFieldRequerente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Protocolo de Procuração", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nº Protocolo:");

        jTextFieldProtocolo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Requerente:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Tefelone:");

        jTextFieldTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldTelefone.setText("(91)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Autenticações:");

        jTextFieldAutenticacoes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldAutenticacoes.setText("1");
        jTextFieldAutenticacoes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAutenticacoesFocusGained(evt);
            }
        });
        jTextFieldAutenticacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAutenticacoesActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Tipo de Procuração:");

        jComboBoxTipoProcuracao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxTipoProcuracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PROCURAÇÃO PREVIDENCIÁRIA", "PROCURAÇÃO GENÉRICA", "PROCURAÇÃO JURÍDICA COM CONTEUDO FINANCEIRO" }));
        jComboBoxTipoProcuracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoProcuracaoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Valor Diligência:");

        jTextFieldDiligencia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Previsão de Entrega:");

        jButtonCadastrar.setText("Cadastrar Protocolo");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelBrasao.setIcon(new javax.swing.ImageIcon("C:\\Arquivos Gerador PDF Java\\Brasao.png")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("CARTÓRIO DO ÚNICO OFÍCIO DE BENEVIDES");

        try {
            jFormattedTextDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextDataEntrega.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextFieldRequerente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldRequerente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRequerenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(144, 144, 144))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jButtonCadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabelBrasao))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBoxTipoProcuracao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldAutenticacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(50, 50, 50)
                                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldRequerente, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(46, 46, 46)
                                .addComponent(jTextFieldDiligencia, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBrasao, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRequerente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldAutenticacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxTipoProcuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldDiligencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jComboBoxTipoProcuracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoProcuracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoProcuracaoActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        //pega os valores dos campos do formFrame e chama o metodo para GerarPDF    
        
        //Verifica se os campos estão em branco (Único campo que pode ficar em Branco é a diligencia)
        if((jTextFieldProtocolo.getText() == null) || jTextFieldProtocolo.getText().trim().isEmpty() || jTextFieldRequerente.getText() == null || jTextFieldRequerente.getText().trim().isEmpty() ||
            jTextFieldTelefone.getText() == null || jTextFieldTelefone.getText().trim().isEmpty() || jTextFieldAutenticacoes.getText() == null || jTextFieldAutenticacoes.getText().trim().isEmpty() ||
            jFormattedTextDataEntrega.getText() == null || jFormattedTextDataEntrega.getText().trim().isEmpty()) 
        {
               JOptionPane.showMessageDialog(null, "Preencher todos os campos!");
        }
        else
        {
            
            String protocolo = jTextFieldProtocolo.getText();
            String requerente = jTextFieldRequerente.getText().toUpperCase();
            String telefone = jTextFieldTelefone.getText();
            int autenticacoes = Integer.parseInt(jTextFieldAutenticacoes.getText());
            int tipoProcuracao = jComboBoxTipoProcuracao.getSelectedIndex();
            String diligencia = jTextFieldDiligencia.getText();              
            String dataEntrega = jFormattedTextDataEntrega.getText();
            
            double valorDiligencia = 0;
            
            //se o campo diligencia estiver em branco vai utilizar o valor 0 para não ocorrer erro no momento de converter em Double
            if(jTextFieldDiligencia.getText() == null || jTextFieldDiligencia.getText().trim().isEmpty())
            {
                valorDiligencia = 0;
            }
            else
            {
                valorDiligencia = Double.parseDouble(diligencia);
            }
            
            if(telefone.equals("(91)"))
            {
                telefone = telefone+"************";
            }            
            
            //Lista todos arquivos (nomes deles)
            for (File arquivo : arquivos) 
            {
                //pega somente o nome do arquivo em vez de pegar o caminho todo
                String teste = arquivo.getName();
                //Pega as 3 primeiras letras (numero do protocolo) do nome do arquivo
                String indiceProtocolo = teste.substring(0, 3);
               
                if(protocolo.equals(indiceProtocolo))
                {
                    protocoloExistente = true;                    
                }
                else
                {
                    protocoloExistente = false;
                }
            }
            
            if (protocoloExistente)
            {  
               ProtocoloExistenteFrame obj = new ProtocoloExistenteFrame(protocolo, requerente, telefone, autenticacoes, tipoProcuracao, valorDiligencia, dataEntrega);
               obj.setVisible(true); 
            }
            else
            {
                //Chama o construtor e passa todos os parametros
                GeneratorPDF obj = new GeneratorPDF(protocolo, requerente, telefone, autenticacoes, tipoProcuracao, valorDiligencia, dataEntrega);

                //chama o metodo para gerar o PDF
                obj.setGerarPdf();    

                //Fecha o frame ProtocoloFrame
                ProtocoloFrame.this.dispose();
                
                //Cria um obj para chamar o Frame/Janela Inicial
                CadastrarNovoProcoloFrame obj2 = new CadastrarNovoProcoloFrame();
                obj2.setVisible(true);
            }
            
        }
        
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        ProtocoloFrame.this.dispose(); // Referência this do formulário        
        TelaInicialFrame obj = new TelaInicialFrame();

        obj.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldAutenticacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAutenticacoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAutenticacoesActionPerformed

    private void jTextFieldAutenticacoesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAutenticacoesFocusGained
        // TODO add your handling code here:
        this.jTextFieldAutenticacoes.selectAll();
    }//GEN-LAST:event_jTextFieldAutenticacoesFocusGained

    private void jTextFieldRequerenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRequerenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRequerenteActionPerformed

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
            java.util.logging.Logger.getLogger(ProtocoloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProtocoloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProtocoloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProtocoloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProtocoloFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox<String> jComboBoxTipoProcuracao;
    private javax.swing.JFormattedTextField jFormattedTextDataEntrega;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBrasao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldAutenticacoes;
    private javax.swing.JTextField jTextFieldDiligencia;
    private javax.swing.JTextField jTextFieldProtocolo;
    private javax.swing.JTextField jTextFieldRequerente;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
