/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Classes.Metodos_Auxiliares;
import DAL.ModuloConexao;
import GerarProcuracoes.GeraProcuracoes;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Michele Andrade
 */
public class GeradorProcuracoesFrame extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    boolean existeRogo = true, existePJ = true;
    boolean campos_PJ_Ativo = false, campos_Rogo_Ativo = false;
    String urlModProcuracoes = "C:\\Arquivos Gerador PDF Java\\Modelos de Procuracoes";
    GeraProcuracoes obj = new GeraProcuracoes();
    Metodos_Auxiliares obj_auxiliar = new Metodos_Auxiliares();
    
    public GeradorProcuracoesFrame() {
        initComponents();
        //cria conexao com o banco de dados
        conexao = ModuloConexao.conector();
        //insere título na Janela do aplicativo
        super.setTitle("Gerar Procuração - Cartório do Único Ofício de Benevides");
        //pega a data atual e inseri no campo Previsão de Entrega        
        Date data =  new Date();
        Locale local = new Locale("pt","BR");
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy",local);
        String data_atual=""+formato.format(data);
        jFormattedTextData.setText(data_atual);
        
        //ao teclar Enter chama o botão de Gerar
        getRootPane().setDefaultButton(jButtonGerar);
        
        //deixa os campos abaixo desabilitados
        jTextFieldRogo.setEnabled(false);
        jTextFieldRGRogo.setEnabled(false);
        jTextFieldEndRogo.setEnabled(false);
        jTextFieldNumRogo.setEnabled(false);
        jTextFieldBairroRogo.setEnabled(false);
        jTextFieldCidadeRogo.setEnabled(false);
        jFormattedTextCPFRogo.setEnabled(false);
        jTextFieldProfRogo.setEnabled(false);
        jComboBoxEstCivilRogo.setEnabled(false);
        jComboBoxSexoRogo.setEnabled(false);
        //deixa os campos abaixo desabilitados
        jtfNomePJ.setEnabled(false);
        jftfCNPJ.setEnabled(false);
        jtfNIRE.setEnabled(false);
        jtfSedePJ.setEnabled(false);
        jtfNumPJ.setEnabled(false);
        jtfBairroPJ.setEnabled(false);
        jtfCidadePJ.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel4.setEnabled(false);
        jLabel5.setEnabled(false);
        jlNPJ.setEnabled(false);
        jlBPJ.setEnabled(false);
        jlCPJ.setEnabled(false);
        
        //ao Iniciar a janela, chama o foco para o campo Outorgante
        jTextFieldOutorgante.requestFocusInWindow();
    }

    public void cadastrarOutorgante()
    {
        String sql = "insert into tbclientes(nomecli,rgcli,cpfcnpjcli,profissao,estcivil, logradourocli, numerocli,bairrocli,cidadecli,sexo) "
        + "values (?,?,?,?,?,?,?,?,?,?);";
        
        try {
            if ((jTextFieldOutorgante.getText() == null || jTextFieldOutorgante.getText().trim().isEmpty()))
            {
                JOptionPane.showMessageDialog(null, "Pheencha o campo Outorgante!");     
                jTextFieldOutorgante.requestFocusInWindow();
            }
            else if (obj_auxiliar.isCPF(jFormattedTextCPFOutorgante.getText()) == false)
            {
                JOptionPane.showMessageDialog(null, "CPF do Outorgante inválido!");
            }
            else if ((jTextFieldEndOutorgante.getText() == null || jTextFieldEndOutorgante.getText().trim().isEmpty()))
            {
                JOptionPane.showMessageDialog(null, "Preencha o endereço do Outorgante!");  
                jTextFieldEndOutorgante.requestFocusInWindow();
            }
            else
            {
                String cpf = jFormattedTextCPFOutorgante.getText();
                //removendo os pontos e o traço do cpf
                cpf = cpf.replaceAll("-", "");
                //somente no caso do ponto é obrigatorio usar os colchetes
                cpf = cpf.replaceAll("[.]", "");

                //as linas abaixo preparam a consulta ao banco de dados
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jTextFieldOutorgante.getText().toUpperCase());
                pst.setString(2, jTextFieldRGOutorgante.getText().toUpperCase());
                pst.setString(3, cpf);
                pst.setString(4, jTextFieldProfOutorgante.getText().toLowerCase());
                pst.setString(5, jComboBoxEstCivilOutorgante.getSelectedItem().toString());
                pst.setString(6, jTextFieldEndOutorgante.getText());
                pst.setString(7, jTextFieldNumOutorgante.getText());
                pst.setString(8, jTextFieldBairroOutorgante.getText());
                pst.setString(9, jTextFieldCidadeOutorgante.getText());
                pst.setString(10, jComboBoxSexoOutorgante.getSelectedItem().toString());
                //executa a query

                int adicionado = pst.executeUpdate();
                if (adicionado >0 ){
                    JOptionPane.showMessageDialog(null, "Outorgante cadastrado com sucesso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cadastrarOutorgado()
    {
        String sql = "insert into tbclientes(nomecli,rgcli,cpfcnpjcli,profissao,estcivil, logradourocli, numerocli,bairrocli,cidadecli,sexo) "
        + "values (?,?,?,?,?,?,?,?,?,?);";
        
        try {
            if ((jTextFieldOutorgado.getText() == null || jTextFieldOutorgado.getText().trim().isEmpty()))
            {
                JOptionPane.showMessageDialog(null, "Pheencha o campo Outorgado!");     
                jTextFieldOutorgado.requestFocusInWindow();
            }
            else if (obj_auxiliar.isCPF(jFormattedTextCPFOutorgado.getText()) == false)
            {
                JOptionPane.showMessageDialog(null, "CPF do Outorgado inválido!");
            }
            else if ((jTextFieldEndOutorgado.getText() == null || jTextFieldEndOutorgado.getText().trim().isEmpty()))
            {
                JOptionPane.showMessageDialog(null, "Preencha o endereço do Outorgado!");  
                jTextFieldEndOutorgado.requestFocusInWindow();
            }
            else
            {
                String cpf = jFormattedTextCPFOutorgado.getText();
                //removendo os pontos e o traço do cpf
                cpf = cpf.replaceAll("-", "");
                //somente no caso do ponto é obrigatorio usar os colchetes
                cpf = cpf.replaceAll("[.]", "");

                //as linas abaixo preparam a consulta ao banco de dados
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jTextFieldOutorgado.getText().toUpperCase());
                pst.setString(2, jTextFieldRGOutorgado.getText().toUpperCase());
                pst.setString(3, cpf);
                pst.setString(4, jTextFieldProfOutorgado.getText().toLowerCase());
                pst.setString(5, jComboBoxEstCivilOutorgado.getSelectedItem().toString());
                pst.setString(6, jTextFieldEndOutorgado.getText());
                pst.setString(7, jTextFieldNumOutorgado.getText());
                pst.setString(8, jTextFieldBairroOutorgado.getText());
                pst.setString(9, jTextFieldCidadeOutorgado.getText());
                pst.setString(10, jComboBoxSexoOutorgado.getSelectedItem().toString());
                //executa a query

                int adicionado = pst.executeUpdate();
                if (adicionado >0 ){
                    JOptionPane.showMessageDialog(null, "Outorgado cadastrado com sucesso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cadastrarPJ()
    {
        String sql = "insert into tbclientes(nomecli,cpfcnpjcli,logradourocli, numerocli,bairrocli,cidadecli,nire) "
                                    + "values (?,?,?,?,?,?,?);";
        
        try {
            if ((jtfNomePJ.getText() == null || jtfNomePJ.getText().trim().isEmpty()) && campos_PJ_Ativo)
            {
                JOptionPane.showMessageDialog(null, "Pheencha o nome da Pessoa Jurídica!");            
                jtfNomePJ.requestFocusInWindow();
            }
            else if ((jftfCNPJ.getText().equals("  .   .   /    -  ")) && campos_PJ_Ativo)
            {
                JOptionPane.showMessageDialog(null, "Pheencha o CNPJ!");  
                jftfCNPJ.requestFocusInWindow();
            }
            else if ((jtfNIRE.getText() == null || jtfNIRE.getText().trim().isEmpty()) && campos_PJ_Ativo)
            {
                JOptionPane.showMessageDialog(null, "Pheencha o NIRE!");            
                jtfNIRE.requestFocusInWindow();
            }
            else if ((jtfSedePJ.getText() == null || jtfSedePJ.getText().trim().isEmpty()) && campos_PJ_Ativo)
            {
                JOptionPane.showMessageDialog(null, "Pheencha a sede da Pessoa Jurídica!");            
                jtfSedePJ.requestFocusInWindow();
            }
            else
            {
                String cnpj = jftfCNPJ.getText();
                //removendo a barra do cnpj
                cnpj = cnpj.replaceAll("/", "");
                cnpj = cnpj.replaceAll("-", "");
                //somente no caso do ponto é obrigatorio usar os colchetes
                cnpj = cnpj.replaceAll("[.]", "");

                //as linas abaixo preparam a consulta ao banco de dados
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtfNomePJ.getText().toUpperCase());
                pst.setString(2, cnpj);
                pst.setString(3, jtfSedePJ.getText());
                pst.setString(4, jtfNumPJ.getText());
                pst.setString(5, jtfBairroPJ.getText());
                pst.setString(6, jtfCidadePJ.getText());
                pst.setString(7, jtfNIRE.getText());
                
                //executa a query

                int adicionado = pst.executeUpdate();
                if (adicionado >0 ){
                    JOptionPane.showMessageDialog(null, "Pessoa Jurídica cadastrada com sucesso!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelOutorgante = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldEndOutorgante = new javax.swing.JTextField();
        jButtonGerar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldOutorgante = new javax.swing.JTextField();
        jLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNumOutorgante = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBairroOutorgante = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldCidadeOutorgante = new javax.swing.JTextField();
        jTextFieldNumOutorgado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldBairroOutorgado = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldCidadeOutorgado = new javax.swing.JTextField();
        jTextFieldOutorgado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldEndOutorgado = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldNumRogo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldBairroRogo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldCidadeRogo = new javax.swing.JTextField();
        jTextFieldRogo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldEndRogo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldRGOutorgante = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextCPFOutorgante = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldProfOutorgante = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxEstCivilOutorgante = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldRGOutorgado = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jFormattedTextCPFOutorgado = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldProfOutorgado = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jComboBoxEstCivilOutorgado = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldRGRogo = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextCPFRogo = new javax.swing.JFormattedTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldProfRogo = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jComboBoxEstCivilRogo = new javax.swing.JComboBox<>();
        jFormattedTextSelo = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextData = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxEscrevente = new javax.swing.JComboBox<>();
        jButtonTipoProcuracao = new javax.swing.JButton();
        jTextFieldCaminhoModeloProc = new javax.swing.JTextField();
        jButtonTeste = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jComboBoxSexoOutorgante = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jComboBoxSexoOutorgado = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jComboBoxSexoRogo = new javax.swing.JComboBox<>();
        jButtonHabJuridica = new javax.swing.JButton();
        jtfNomePJ = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jftfCNPJ = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfNIRE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfSedePJ = new javax.swing.JTextField();
        jlNPJ = new javax.swing.JLabel();
        jtfNumPJ = new javax.swing.JTextField();
        jlBPJ = new javax.swing.JLabel();
        jtfBairroPJ = new javax.swing.JTextField();
        jlCPJ = new javax.swing.JLabel();
        jtfCidadePJ = new javax.swing.JTextField();
        jButtonSelCliente = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jComboBoxTipoProcuracao = new javax.swing.JComboBox<>();
        jButtonSalvar = new javax.swing.JButton();
        jTextFieldCaminhoSalvar = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldComplementoArquivo = new javax.swing.JTextField();
        jButtonMesmoEndOutorgante = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Gerador de Procuração", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabelOutorgante.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelOutorgante.setText("Outorgante*:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("End. Outorgante:");

        jButtonGerar.setText("Gerar Procuração");
        jButtonGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("CARTÓRIO DO ÚNICO OFÍCIO DE BENEVIDES");

        jTextFieldOutorgante.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldOutorgante.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldOutorganteFocusGained(evt);
            }
        });
        jTextFieldOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOutorganteActionPerformed(evt);
            }
        });

        jLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel.setText("Selo:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setText("Nº");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setText("Bairro:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText("Cidade:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText("Bairro:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText("Cidade:");

        jTextFieldOutorgado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldOutorgadoFocusGained(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("Outorgado:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("End. Outorgado:");

        jTextFieldEndOutorgado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEndOutorgadoFocusGained(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText("Nº");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText("Bairro:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setText("Cidade:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setText("A Rogo:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setText("End. a Rogo:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setText("Nº");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setText("RG:");

        jTextFieldRGOutorgante.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldRGOutorganteFocusGained(evt);
            }
        });
        jTextFieldRGOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRGOutorganteActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setText("CPF:");

        try {
            jFormattedTextCPFOutorgante.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel24.setText("Profissão:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setText("Est. Civil:");

        jComboBoxEstCivilOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));
        jComboBoxEstCivilOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEstCivilOutorganteActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel26.setText("RG:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel27.setText("CPF:");

        try {
            jFormattedTextCPFOutorgado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("Profissão:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setText("Est. Civil:");

        jComboBoxEstCivilOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));
        jComboBoxEstCivilOutorgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEstCivilOutorgadoActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel30.setText("RG:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel31.setText("CPF:");

        try {
            jFormattedTextCPFRogo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel32.setText("Profissão:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel33.setText("Est. Civil:");

        jComboBoxEstCivilRogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));

        try {
            jFormattedTextSelo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setText("Data da Procuração:");

        try {
            jFormattedTextData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextData.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jFormattedTextData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextDataActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("Escrevente");

        jComboBoxEscrevente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboBoxEscrevente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ANDREYA GLAUCYA GUIMARAES DE SOUSA", "JORGE AUGUSTO RUFINO FERREIRA", "ALESSANDRA ALVARES FIGUEIREDO - SUBSTITUTA", "RENATO DA SILVA GUIMARAES", "MAXWELL RAMOS FIGUEIREDO - TABELIÃO" }));
        jComboBoxEscrevente.setSelectedIndex(1);
        jComboBoxEscrevente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEscreventeActionPerformed(evt);
            }
        });

        jButtonTipoProcuracao.setText("Modelo da Procuração*");
        jButtonTipoProcuracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTipoProcuracaoActionPerformed(evt);
            }
        });

        jTextFieldCaminhoModeloProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCaminhoModeloProcActionPerformed(evt);
            }
        });

        jButtonTeste.setText("Habilitar A Rogo");
        jButtonTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTesteActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel34.setText("Sexo:");

        jComboBoxSexoOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "feminino" }));
        jComboBoxSexoOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSexoOutorganteActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel37.setText("Sexo:");

        jComboBoxSexoOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "feminino" }));
        jComboBoxSexoOutorgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSexoOutorgadoActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel38.setText("Sexo:");

        jComboBoxSexoRogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "feminino" }));
        jComboBoxSexoRogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSexoRogoActionPerformed(evt);
            }
        });

        jButtonHabJuridica.setText("Habilitar PJ");
        jButtonHabJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHabJuridicaActionPerformed(evt);
            }
        });

        jtfNomePJ.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfNomePJ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfNomePJFocusGained(evt);
            }
        });
        jtfNomePJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomePJActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("Emp. Outorgante:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("CNPJ:");

        try {
            jftfCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("NIRE:");

        jtfNIRE.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfNIRE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfNIREFocusGained(evt);
            }
        });
        jtfNIRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNIREActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("Sede da Empresa:");

        jtfSedePJ.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfSedePJ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfSedePJFocusGained(evt);
            }
        });
        jtfSedePJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSedePJActionPerformed(evt);
            }
        });

        jlNPJ.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlNPJ.setText("Nº");

        jlBPJ.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlBPJ.setText("Bairro:");

        jlCPJ.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jlCPJ.setText("Cidade:");

        jButtonSelCliente.setText("Selecionar Cliente Cadastrado");
        jButtonSelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelClienteActionPerformed(evt);
            }
        });

        jLabel35.setText("Tipo de Procuração:");

        jComboBoxTipoProcuracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "previdenciaria", "generica", "juridica" }));

        jButtonSalvar.setText("Salvar em...");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel36.setText("Complemento p/ nome do Arquivo:");

        jButtonMesmoEndOutorgante.setText("Usar mesmo endereço do(a) Outorgante");
        jButtonMesmoEndOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMesmoEndOutorganteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(342, 342, 342))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonHabJuridica)
                                        .addGap(32, 32, 32)
                                        .addComponent(jButtonSelCliente))
                                    .addComponent(jButtonTeste, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldEndRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldNumRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldBairroRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldCidadeRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButtonGerar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButtonCancelar))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jFormattedTextSelo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jFormattedTextData, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBoxEscrevente, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(174, 478, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfNomePJ, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jftfCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfNIRE, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelOutorgante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldRGOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextCPFOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldProfOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxEstCivilOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxSexoOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldRGOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextCPFOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldProfOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxEstCivilOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxSexoOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldRGRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextCPFRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldProfRogo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxEstCivilRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxSexoRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfSedePJ, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlNPJ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNumPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlBPJ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfBairroPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlCPJ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCidadePJ, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxTipoProcuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonTipoProcuracao))
                                    .addComponent(jTextFieldCaminhoSalvar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCaminhoModeloProc, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldComplementoArquivo)))))
                        .addGap(0, 209, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldEndOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNumOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldBairroOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCidadeOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonMesmoEndOutorgante))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldEndOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNumOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldBairroOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCidadeOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonSalvar))
                        .addContainerGap(276, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHabJuridica)
                    .addComponent(jButtonSelCliente))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jftfCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jtfNIRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfNomePJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfSedePJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(jComboBoxSexoOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelOutorgante)
                                .addComponent(jTextFieldOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)
                                .addComponent(jTextFieldRGOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23)
                                .addComponent(jFormattedTextCPFOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24)
                                .addComponent(jTextFieldProfOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25)
                                .addComponent(jComboBoxEstCivilOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jTextFieldEndOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(jTextFieldNumOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jTextFieldBairroOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jTextFieldCidadeOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37)
                                .addComponent(jComboBoxSexoOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(jTextFieldRGOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)
                                .addComponent(jFormattedTextCPFOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28)
                                .addComponent(jTextFieldProfOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29)
                                .addComponent(jComboBoxEstCivilOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jTextFieldOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jTextFieldCidadeOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonMesmoEndOutorgante))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jTextFieldEndOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(jTextFieldNumOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(jTextFieldBairroOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlNPJ)
                        .addComponent(jtfNumPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlBPJ)
                        .addComponent(jtfBairroPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlCPJ)
                        .addComponent(jtfCidadePJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTeste)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(jComboBoxSexoRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jTextFieldProfRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)
                        .addComponent(jComboBoxEstCivilRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(jFormattedTextCPFRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jTextFieldRGRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jTextFieldRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jTextFieldCidadeRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextFieldEndRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(jTextFieldNumRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(jTextFieldBairroRogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel)
                    .addComponent(jFormattedTextSelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxEscrevente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jComboBoxTipoProcuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTipoProcuracao)
                    .addComponent(jTextFieldCaminhoModeloProc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jTextFieldCaminhoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jTextFieldComplementoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGerar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelClienteActionPerformed
        // TODO add your handling code here:
        ConsultarClienteFrame frameCliente = new ConsultarClienteFrame();
        frameCliente.setVisible(true);
    }//GEN-LAST:event_jButtonSelClienteActionPerformed

    private void jtfSedePJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSedePJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSedePJActionPerformed

    private void jtfSedePJFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSedePJFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSedePJFocusGained

    private void jtfNIREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNIREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNIREActionPerformed

    private void jtfNIREFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNIREFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNIREFocusGained

    private void jtfNomePJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomePJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomePJActionPerformed

    private void jtfNomePJFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNomePJFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomePJFocusGained

    private void jButtonHabJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHabJuridicaActionPerformed
        //verifica se o valor da variavel auxiliar para habilitar ou desabilitar os campos
        if (campos_PJ_Ativo == false)
        {
            //chama o foco para campo Emp. Outorngate
            jtfNomePJ.requestFocusInWindow();

            jLabelOutorgante.setText("Rep. Legal:*");
            jtfNomePJ.setEnabled(true);
            jftfCNPJ.setEnabled(true);
            jtfNIRE.setEnabled(true);
            jtfSedePJ.setEnabled(true);
            jtfNumPJ.setEnabled(true);
            jtfBairroPJ.setEnabled(true);
            jtfCidadePJ.setEnabled(true);
            jLabel1.setEnabled(true);
            jLabel2.setEnabled(true);
            jLabel4.setEnabled(true);
            jLabel5.setEnabled(true);
            jlNPJ.setEnabled(true);
            jlBPJ.setEnabled(true);
            jlCPJ.setEnabled(true);
            campos_PJ_Ativo = true;
        }
        else
        {
            //chama o foco para o campo Outorgante
            jTextFieldOutorgante.requestFocusInWindow();

            jLabelOutorgante.setText("Outorgante:*");
            jtfNomePJ.setEnabled(false);
            jftfCNPJ.setEnabled(false);
            jtfNIRE.setEnabled(false);
            jtfSedePJ.setEnabled(false);
            jtfNumPJ.setEnabled(false);
            jtfBairroPJ.setEnabled(false);
            jtfCidadePJ.setEnabled(false);
            jLabel1.setEnabled(false);
            jLabel2.setEnabled(false);
            jLabel4.setEnabled(false);
            jLabel5.setEnabled(false);
            jlNPJ.setEnabled(false);
            jlBPJ.setEnabled(false);
            jlCPJ.setEnabled(false);
            campos_PJ_Ativo = false;
        }

    }//GEN-LAST:event_jButtonHabJuridicaActionPerformed

    private void jComboBoxSexoRogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexoRogoActionPerformed
        //pega o indice selecionado para saber qual o sexo (masc = 0/femin = 1)
        int sexo = jComboBoxSexoRogo.getSelectedIndex();
        int indiceComboBoxEstCivil = jComboBoxEstCivilRogo.getSelectedIndex();
        //se sexo feminino
        if(sexo == 1)
        {
            //cria um novo combobox com os valores no feminino
            jComboBoxEstCivilRogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteira", "casada", "divorciada", "viúva" }));
            //mantem o item selecionado
            jComboBoxEstCivilRogo.setSelectedIndex(indiceComboBoxEstCivil);
        }
        if(sexo == 0)
        {
            jComboBoxEstCivilRogo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));
            jComboBoxEstCivilRogo.setSelectedIndex(indiceComboBoxEstCivil);
        }
    }//GEN-LAST:event_jComboBoxSexoRogoActionPerformed

    private void jComboBoxSexoOutorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexoOutorgadoActionPerformed
        //pega o indice selecionado para saber qual o sexo (masc = 0/femin = 1)
        int sexo = jComboBoxSexoOutorgado.getSelectedIndex();
        int indiceComboBoxEstCivil = jComboBoxEstCivilOutorgado.getSelectedIndex();
        //se sexo feminino
        if(sexo == 1)
        {
            //cria um novo combobox com os valores no feminino
            jComboBoxEstCivilOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteira", "casada", "divorciada", "viúva" }));
            //mantem o item selecionado
            jComboBoxEstCivilOutorgado.setSelectedIndex(indiceComboBoxEstCivil);
        }
        if(sexo == 0)
        {
            jComboBoxEstCivilOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));
            jComboBoxEstCivilOutorgado.setSelectedIndex(indiceComboBoxEstCivil);
        }
    }//GEN-LAST:event_jComboBoxSexoOutorgadoActionPerformed

    //vai verificar qual o sexo escolhido e mudar automaticamente o gênero do estado civil do outorgante
    private void jComboBoxSexoOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexoOutorganteActionPerformed
        //pega o indice selecionado para saber qual o sexo (masc = 0/femin = 1)
        int sexoOut = jComboBoxSexoOutorgante.getSelectedIndex();
        int indiceComboBoxEstCivil = jComboBoxEstCivilOutorgante.getSelectedIndex();
        //se sexo feminino
        if(sexoOut == 1)
        {
            //cria um novo combobox com os valores no feminino
            jComboBoxEstCivilOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteira", "casada", "divorciada", "viúva" }));
            //mantem o item selecionado
            jComboBoxEstCivilOutorgante.setSelectedIndex(indiceComboBoxEstCivil);
        }
        if(sexoOut == 0)
        {
            jComboBoxEstCivilOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));
            jComboBoxEstCivilOutorgante.setSelectedIndex(indiceComboBoxEstCivil);
        }
    }//GEN-LAST:event_jComboBoxSexoOutorganteActionPerformed

    private void jButtonTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTesteActionPerformed
        //verifica se o valor da variavel auxiliar para habilitar ou desabilitar os campos
        if(campos_Rogo_Ativo == false)
        {
            jTextFieldRogo.requestFocusInWindow();
            jTextFieldRogo.setEnabled(true);
            jTextFieldRGRogo.setEnabled(true);
            jTextFieldEndRogo.setEnabled(true);
            jTextFieldNumRogo.setEnabled(true);
            jTextFieldBairroRogo.setEnabled(true);
            jTextFieldCidadeRogo.setEnabled(true);
            jFormattedTextCPFRogo.setEnabled(true);
            jTextFieldProfRogo.setEnabled(true);
            jComboBoxEstCivilRogo.setEnabled(true);
            jComboBoxSexoRogo.setEnabled(true);
            campos_Rogo_Ativo = true;
        }
        else
        {
            jFormattedTextSelo.requestFocusInWindow();
            jTextFieldRogo.setEnabled(false);
            jTextFieldRGRogo.setEnabled(false);
            jTextFieldEndRogo.setEnabled(false);
            jTextFieldNumRogo.setEnabled(false);
            jTextFieldBairroRogo.setEnabled(false);
            jTextFieldCidadeRogo.setEnabled(false);
            jFormattedTextCPFRogo.setEnabled(false);
            jTextFieldProfRogo.setEnabled(false);
            jComboBoxEstCivilRogo.setEnabled(false);
            jComboBoxSexoRogo.setEnabled(false);
            campos_Rogo_Ativo = false;
        }

    }//GEN-LAST:event_jButtonTesteActionPerformed

    private void jTextFieldCaminhoModeloProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCaminhoModeloProcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCaminhoModeloProcActionPerformed

    private void jButtonTipoProcuracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoProcuracaoActionPerformed
        //se a procuracao for de Pessoa Juridica, ele muda o caminho/pasta dos modelos de procuracao
        if (campos_PJ_Ativo){
            urlModProcuracoes = "C:\\Arquivos Gerador PDF Java\\Modelos de Procuracoes\\MODELOS PJ";
        }
        //se a procuracao tiver a Rogo, ele muda o caminho/pasta dos modelos de procuracao
        if (campos_Rogo_Ativo){
            urlModProcuracoes = "C:\\Arquivos Gerador PDF Java\\Modelos de Procuracoes\\MODELOS A ROGO";
        }
        JFileChooser abrir = new JFileChooser(urlModProcuracoes);
        int retorno = abrir.showOpenDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION)
        jTextFieldCaminhoModeloProc.setText(abrir.getSelectedFile().getAbsolutePath());
        obj.setModeloProc(abrir.getSelectedFile().getName());
    }//GEN-LAST:event_jButtonTipoProcuracaoActionPerformed

    private void jComboBoxEscreventeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEscreventeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEscreventeActionPerformed

    private void jFormattedTextDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextDataActionPerformed

    private void jComboBoxEstCivilOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEstCivilOutorganteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEstCivilOutorganteActionPerformed

    private void jTextFieldRGOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRGOutorganteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRGOutorganteActionPerformed

    private void jTextFieldRGOutorganteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRGOutorganteFocusGained
        // TODO add your handling code here:
        this.jTextFieldRGOutorgante.selectAll();
    }//GEN-LAST:event_jTextFieldRGOutorganteFocusGained

    private void jTextFieldOutorgadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldOutorgadoFocusGained
        // TODO add your handling code here:
        jTextFieldOutorgado.selectAll();
    }//GEN-LAST:event_jTextFieldOutorgadoFocusGained

    private void jTextFieldOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOutorganteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldOutorganteActionPerformed

    private void jTextFieldOutorganteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldOutorganteFocusGained
        // TODO add your handling code here:
        this.jTextFieldOutorgante.selectAll();
    }//GEN-LAST:event_jTextFieldOutorganteFocusGained

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
       this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarActionPerformed
        //pega os valores dos campos do formFrame e chama o metodo para GerarPDF
        
        obj.setOutorgante(jTextFieldOutorgante.getText());
        obj.setEstCivilOutorgante(jComboBoxEstCivilOutorgante.getSelectedItem().toString());
        obj.setRgOutorgante(jTextFieldRGOutorgante.getText());
        obj.setCpfOutorgante(jFormattedTextCPFOutorgante.getText());
        obj.setProfOutorgante(jTextFieldProfOutorgante.getText());

        String endOutorgante = jTextFieldEndOutorgante.getText();
        String bairroOutorgante = jTextFieldBairroOutorgante.getText();
        String numOutorgante = jTextFieldNumOutorgante.getText();
        String cidadeOutorgante = jTextFieldCidadeOutorgante.getText();

        obj.setOutorgado(jTextFieldOutorgado.getText());
        obj.setEstCivilOutorgado(jComboBoxEstCivilOutorgado.getSelectedItem().toString());
        obj.setRgOutorgado(jTextFieldRGOutorgado.getText());
        obj.setCpfOutorgado(jFormattedTextCPFOutorgado.getText());
        obj.setProfOutorgado(jTextFieldProfOutorgado.getText());

        String endOutorgado = jTextFieldEndOutorgado.getText();
        String bairroOutorgado = jTextFieldBairroOutorgado.getText();
        String numOutorgado = jTextFieldNumOutorgado.getText();
        String cidOutorgado = jTextFieldCidadeOutorgado.getText();

        obj.setSelo(jFormattedTextSelo.getText());
        obj.setData(jFormattedTextData.getText());
        obj.setFilePath(jTextFieldCaminhoModeloProc.getText());
        obj.setIndiceEscrevente(jComboBoxEscrevente.getSelectedIndex());

        obj.setSexoOutorgante(jComboBoxSexoOutorgante.getSelectedIndex());
        obj.setSexoOutorgado(jComboBoxSexoOutorgado.getSelectedIndex());
        obj.setSexoRogo(jComboBoxSexoRogo.getSelectedIndex());

        //verifica se os campos estão preenchidos, caso estejam passa o valor para a String correspondente
        if(jTextFieldNumOutorgante.getText().trim().isEmpty() == false)
        {
            numOutorgante = ", nº "+numOutorgante;
        }
        if(jTextFieldBairroOutorgante.getText().trim().isEmpty() == false)
        {
            bairroOutorgante = ", Bairro: "+bairroOutorgante;
        }
        if(jTextFieldCidadeOutorgante.getText().trim().isEmpty() == false)
        {
            cidadeOutorgante = ", Município de "+cidadeOutorgante;
        }
        //passa endereço do Outorgante
        obj.setEndOutorgante(endOutorgante+numOutorgante+bairroOutorgante+cidadeOutorgante);

        if(jTextFieldNumOutorgado.getText().trim().isEmpty() == false)
        {
            numOutorgado = ", nº "+numOutorgado;
        }
        if(jTextFieldBairroOutorgado.getText().trim().isEmpty() == false)
        {
            bairroOutorgado = ", Bairro: "+bairroOutorgado;
        }
        if(jTextFieldCidadeOutorgado.getText().trim().isEmpty() == false)
        {
            cidOutorgado = ", Município de "+cidOutorgado;
        }
        //passa endereço do Outorgado
        obj.setEndOutorgado(endOutorgado+numOutorgado+bairroOutorgado+cidOutorgado);

        //Se os campos to A rogo estiverem ativos...
        if(campos_Rogo_Ativo)
        {
            obj.setaRogo(jTextFieldRogo.getText());
            obj.setEstCivilRogo(jComboBoxEstCivilRogo.getSelectedItem().toString());
            obj.setRgRogo(jTextFieldRGRogo.getText());
            obj.setCpfRogo(jFormattedTextCPFRogo.getText());
            obj.setProfRogo(jTextFieldProfRogo.getText());

            String endRogo = jTextFieldEndRogo.getText();
            String numRogo = jTextFieldNumRogo.getText();
            String bairroRogo = jTextFieldBairroRogo.getText();
            String cidadeRogo = jTextFieldCidadeRogo.getText();

            if(jTextFieldNumRogo.getText().trim().isEmpty() == false)
            {
                numRogo = ", nº "+numRogo;
            }
            if(jTextFieldBairroRogo.getText().trim().isEmpty() == false)
            {
                bairroRogo = ", Bairro: "+bairroRogo;
            }
            if(jTextFieldCidadeRogo.getText().trim().isEmpty() == false)
            {
                cidadeRogo = ", Município de "+cidadeRogo;
            }
            //passa endereço do Outorgado
            obj.setEndRogo(endRogo+numRogo+bairroRogo+cidadeRogo);
        }

        //Verifica se os campos de Pessoa Juridica estao preenchidos
        if(campos_PJ_Ativo)
        {
            existePJ = true;

            obj.setNome_PJ(jtfNomePJ.getText());
            obj.setCnpj(jftfCNPJ.getText());
            obj.setNire(jtfNIRE.getText());

            String endPJ = jtfSedePJ.getText();
            String bairroPJ = jtfBairroPJ.getText();
            String numPJ = jtfNumPJ.getText();
            String cidPJ = jtfCidadePJ.getText();

            if(jtfNumPJ.getText().trim().isEmpty() == false)
            {
                numPJ = ", nº "+numPJ;
            }
            if(jtfBairroPJ.getText().trim().isEmpty() == false)
            {
                bairroPJ = ", Bairro: "+bairroPJ;
            }
            if(jtfCidadePJ.getText().trim().isEmpty() == false)
            {
                cidPJ = ", Município de "+cidPJ;
            }

            obj.setSede_PJ(endPJ+numPJ+bairroPJ+cidPJ);
        }

        //Como o separador que estou querendo usar é o "." (ponto), é necessário coloca-lo entre Colchetes para não dar erro.
        //Lembrando que os Colchetes significam grupo de separadores, ou seja, podereia colocar outrso separados juntos com o ponto, como a virgula, travessao, etc.
        String valorSelo[] = jFormattedTextSelo.getText().split("[.]");

        //Verifica se os principais campos estão preenchidos
        if ((jtfNomePJ.getText() == null || jtfNomePJ.getText().trim().isEmpty()) && campos_PJ_Ativo)
        {
            JOptionPane.showMessageDialog(null, "Pheencha o nome da Pessoa Jurídica!");
            jtfNomePJ.requestFocusInWindow();
        }
        else if ((jftfCNPJ.getText().equals("  .   .   /    -  ")) && campos_PJ_Ativo)
        {
            JOptionPane.showMessageDialog(null, "Pheencha o CNPJ!");
            jftfCNPJ.requestFocusInWindow();
        }
        else if ((jtfNIRE.getText() == null || jtfNIRE.getText().trim().isEmpty()) && campos_PJ_Ativo)
        {
            JOptionPane.showMessageDialog(null, "Pheencha o NIRE!");
            jtfNIRE.requestFocusInWindow();
        }
        else if ((jtfSedePJ.getText() == null || jtfSedePJ.getText().trim().isEmpty()) && campos_PJ_Ativo)
        {
            JOptionPane.showMessageDialog(null, "Pheencha a sede da Pessoa Jurídica!");
            jtfSedePJ.requestFocusInWindow();
        }
        else if ((jTextFieldOutorgante.getText() == null || jTextFieldOutorgante.getText().trim().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Pheencha o campo Outorgante!");
            jTextFieldOutorgante.requestFocusInWindow();
        }
        else if (obj_auxiliar.isCPF(jFormattedTextCPFOutorgante.getText()) == false)
        {
            JOptionPane.showMessageDialog(null, "CPF do Outorgante inválido!");
        }
        else if ((jTextFieldEndOutorgante.getText() == null || jTextFieldEndOutorgante.getText().trim().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Preencha o endereço do Outorgante!");
            jTextFieldEndOutorgante.requestFocusInWindow();
        }
        else if ((jTextFieldOutorgado.getText() == null || jTextFieldOutorgado.getText().trim().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Preencha o campo Outorgado!");
            jTextFieldOutorgado.requestFocusInWindow();
        }
        else if (obj_auxiliar.isCPF(jFormattedTextCPFOutorgado.getText()) == false)
        {
            JOptionPane.showMessageDialog(null, "CPF do Outorgado inválido!");
        }
        else if ((jTextFieldEndOutorgado.getText() == null || jTextFieldEndOutorgado.getText().trim().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Preencha o endereço do Outorgado!");
            jTextFieldEndOutorgado.requestFocusInWindow();
        }
        else if ((jTextFieldRogo.getText() == null || jTextFieldRogo.getText().trim().isEmpty()) && campos_Rogo_Ativo)
        {
            JOptionPane.showMessageDialog(null, "Pheencha o nome do a Rogo!");
            jTextFieldRogo.requestFocusInWindow();
        }
        else if ((obj_auxiliar.isCPF(jFormattedTextCPFRogo.getText()) == false) && campos_Rogo_Ativo )
        {
            JOptionPane.showMessageDialog(null, "CPF do a Rogo inválido!");
        }
        else if ((jTextFieldEndRogo.getText() == null || jTextFieldEndRogo.getText().trim().isEmpty())&& campos_Rogo_Ativo)
        {
            JOptionPane.showMessageDialog(null, "Pheencha o endereço do a Rogo!");
            jTextFieldRogo.requestFocusInWindow();
        }
        else if ((jTextFieldCaminhoModeloProc.getText() == null || jTextFieldCaminhoModeloProc.getText().trim().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Escolha o modelo de procuração que será usado!");
            jTextFieldCaminhoModeloProc.requestFocusInWindow();
        }
        //como o campo selo tem uma mascara de 3 digitos, caso nao seja digitado nada, o metodo passa 3 espaços vazios, por isso esta condiçao
        else if (valorSelo[0].equals("   ") || valorSelo[1].equals("   ") || valorSelo[2].equals("   "))
        {
            JOptionPane.showMessageDialog(null, "Preencha o campo Selo!");
            jFormattedTextSelo.requestFocusInWindow();
        }
        else if((jTextFieldCaminhoSalvar.getText() == null || jTextFieldCaminhoSalvar.getText().trim().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Escolha o local onde salvar a procuração!");
            jTextFieldCaminhoSalvar.requestFocusInWindow();
        }
        else
        {
            obj.setExistePJ(campos_PJ_Ativo);
            obj.setExisteRogo(campos_Rogo_Ativo);
            
            //verifica se o cpf/cnpj já está no banco, se for false é porque cliente ainda não está cadastrado
            if (obj_auxiliar.buscaClienteCpfCnpj(jFormattedTextCPFOutorgante.getText()).isEmpty())
            {
                //cadastra os dados do Outorgante no banco
                obj_auxiliar.cadastrarCliente(jTextFieldOutorgante.getText(), jTextFieldRGOutorgante.getText(), jFormattedTextCPFOutorgante.getText(), 
                jTextFieldProfOutorgante.getText(), jComboBoxEstCivilOutorgante.getSelectedItem().toString(), jTextFieldEndOutorgante.getText(), 
                jTextFieldNumOutorgante.getText(), jTextFieldBairroOutorgante.getText(), jTextFieldCidadeOutorgante.getText(), jComboBoxSexoOutorgante.getSelectedItem().toString(),"");
            }
            
            if (obj_auxiliar.buscaClienteCpfCnpj(jFormattedTextCPFOutorgado.getText()).isEmpty())
            {
                String end= jTextFieldEndOutorgado.getText(), num = jTextFieldNumOutorgado.getText(), 
                bairro = jTextFieldBairroOutorgado.getText(), cidade = jTextFieldCidadeOutorgado.getText();
                
                if(jTextFieldEndOutorgado.getText().equals("mesmo endereço do outorgante"))
                {
                    end = jTextFieldEndOutorgante.getText();
                    num = jTextFieldNumOutorgante.getText();
                    bairro = jTextFieldBairroOutorgante.getText();
                    cidade = jTextFieldCidadeOutorgante.getText();
                }
                //cadastra os dados do outorgado
                obj_auxiliar.cadastrarCliente(jTextFieldOutorgado.getText(), jTextFieldRGOutorgado.getText(), jFormattedTextCPFOutorgado.getText(), 
                jTextFieldProfOutorgado.getText(), jComboBoxEstCivilOutorgado.getSelectedItem().toString(), end,num, bairro, cidade, 
                jComboBoxSexoOutorgado.getSelectedItem().toString(),"");
            }
                        
            if(campos_PJ_Ativo){
                //verifica se o cnpj já está cadastrado no banco
                if (obj_auxiliar.buscaClienteCpfCnpj(jftfCNPJ.getText()).isEmpty()){
                    //cadastra a PJ no banco
                    obj_auxiliar.cadastrarCliente(jtfNomePJ.getText(), "", jftfCNPJ.getText(), "", "", jtfSedePJ.getText(), 
                    jtfNumPJ.getText(), jtfBairroPJ.getText(), jtfCidadePJ.getText(), "", jtfNIRE.getText());
                }
            }
            ArrayList outorgante = obj_auxiliar.buscaClienteCpfCnpj(jFormattedTextCPFOutorgante.getText());
            ArrayList outorgado = obj_auxiliar.buscaClienteCpfCnpj(jFormattedTextCPFOutorgado.getText());
            ArrayList pj = obj_auxiliar.buscaClienteCpfCnpj(jftfCNPJ.getText());
            ArrayList livroFolha = obj_auxiliar.getUltimoLivroFolha();
            
            int livroAtual = Integer.parseInt(livroFolha.get(0).toString());
            int folhaAtual = Integer.parseInt(livroFolha.get(1).toString());
            int proxFolha, proxLivro;
            
            if(folhaAtual == 300)
            {
                proxLivro = livroAtual+1;
                proxFolha = 1;
            }
            else
            {
                proxLivro = livroAtual;
                proxFolha = folhaAtual+1;
            }
            String livro = "";
            String folha = "";
            
            int cadLivroFolha = JOptionPane.showConfirmDialog(null, "Deseja cadastar a procuraçao com livro e folha? (L:"+proxLivro+" / F:"+proxFolha+")", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (cadLivroFolha == JOptionPane.YES_OPTION){
                livro = "" + proxLivro;
                
                if(proxFolha <= 9)
                    folha = "00"+proxFolha;
                if(proxFolha > 9 && proxFolha <= 99)
                    folha = "0"+proxFolha;
                if(proxFolha >= 100)
                    folha = ""+proxFolha;
            }
            
            obj_auxiliar.cadastrarOutorganteOutorgadoPj(Integer.parseInt(outorgante.get(0).toString()), Integer.parseInt(outorgado.get(0).toString()),jComboBoxTipoProcuracao.getSelectedIndex()+1, livro, folha);
            if(campos_PJ_Ativo){
                obj_auxiliar.cadastrarOutorganteOutorgadoPj(Integer.parseInt(pj.get(0).toString()), Integer.parseInt(outorgado.get(0).toString()),jComboBoxTipoProcuracao.getSelectedIndex()+1, livro, folha);
            }
            
            obj.setCaminhoSalvar(jTextFieldCaminhoSalvar.getText()+"\\"+jTextFieldOutorgante.getText()+" para "+jTextFieldOutorgado.getText()
            +" "+jTextFieldComplementoArquivo.getText()+".doc");
            obj.setGeraProcuracoes();
            GeradorProcuracoesFrame.this.dispose(); // Referência this do formulário
        }    
    }//GEN-LAST:event_jButtonGerarActionPerformed

    private void jTextFieldEndOutorgadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEndOutorgadoFocusGained
        // TODO add your handling code here:
        jTextFieldEndOutorgado.selectAll();
    }//GEN-LAST:event_jTextFieldEndOutorgadoFocusGained

    private void jComboBoxEstCivilOutorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEstCivilOutorgadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEstCivilOutorgadoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        urlModProcuracoes = "D:\\Michele Andrade\\Desktop\\PROCURAÇÃO PÚBLICA\\PROCURACAO PUBLICA 2017";
        
        JFileChooser abrir = new JFileChooser(urlModProcuracoes);
        abrir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retorno = abrir.showOpenDialog(null);
        if (retorno==JFileChooser.APPROVE_OPTION)
        jTextFieldCaminhoSalvar.setText(abrir.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonMesmoEndOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMesmoEndOutorganteActionPerformed
        // TODO add your handling code here:
        jTextFieldEndOutorgado.setText(jTextFieldEndOutorgante.getText());
        jTextFieldNumOutorgado.setText(jTextFieldNumOutorgante.getText());
        jTextFieldBairroOutorgado.setText(jTextFieldBairroOutorgante.getText());
        jTextFieldCidadeOutorgado.setText(jTextFieldCidadeOutorgante.getText());
    }//GEN-LAST:event_jButtonMesmoEndOutorganteActionPerformed
    
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
            java.util.logging.Logger.getLogger(GeradorProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeradorProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeradorProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeradorProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeradorProcuracoesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JButton jButtonHabJuridica;
    private javax.swing.JButton jButtonMesmoEndOutorgante;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSelCliente;
    private javax.swing.JButton jButtonTeste;
    private javax.swing.JButton jButtonTipoProcuracao;
    private javax.swing.JComboBox<String> jComboBoxEscrevente;
    public static javax.swing.JComboBox<String> jComboBoxEstCivilOutorgado;
    public static javax.swing.JComboBox<String> jComboBoxEstCivilOutorgante;
    private javax.swing.JComboBox<String> jComboBoxEstCivilRogo;
    public static javax.swing.JComboBox<String> jComboBoxSexoOutorgado;
    public static javax.swing.JComboBox<String> jComboBoxSexoOutorgante;
    private javax.swing.JComboBox<String> jComboBoxSexoRogo;
    public static javax.swing.JComboBox<String> jComboBoxTipoProcuracao;
    public static javax.swing.JFormattedTextField jFormattedTextCPFOutorgado;
    public static javax.swing.JFormattedTextField jFormattedTextCPFOutorgante;
    private javax.swing.JFormattedTextField jFormattedTextCPFRogo;
    private javax.swing.JFormattedTextField jFormattedTextData;
    private javax.swing.JFormattedTextField jFormattedTextSelo;
    private javax.swing.JLabel jLabel;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelOutorgante;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField jTextFieldBairroOutorgado;
    public static javax.swing.JTextField jTextFieldBairroOutorgante;
    private javax.swing.JTextField jTextFieldBairroRogo;
    private javax.swing.JTextField jTextFieldCaminhoModeloProc;
    private javax.swing.JTextField jTextFieldCaminhoSalvar;
    public static javax.swing.JTextField jTextFieldCidadeOutorgado;
    public static javax.swing.JTextField jTextFieldCidadeOutorgante;
    private javax.swing.JTextField jTextFieldCidadeRogo;
    private javax.swing.JTextField jTextFieldComplementoArquivo;
    public static javax.swing.JTextField jTextFieldEndOutorgado;
    public static javax.swing.JTextField jTextFieldEndOutorgante;
    private javax.swing.JTextField jTextFieldEndRogo;
    public static javax.swing.JTextField jTextFieldNumOutorgado;
    public static javax.swing.JTextField jTextFieldNumOutorgante;
    private javax.swing.JTextField jTextFieldNumRogo;
    public static javax.swing.JTextField jTextFieldOutorgado;
    public static javax.swing.JTextField jTextFieldOutorgante;
    public static javax.swing.JTextField jTextFieldProfOutorgado;
    public static javax.swing.JTextField jTextFieldProfOutorgante;
    private javax.swing.JTextField jTextFieldProfRogo;
    public static javax.swing.JTextField jTextFieldRGOutorgado;
    public static javax.swing.JTextField jTextFieldRGOutorgante;
    private javax.swing.JTextField jTextFieldRGRogo;
    private javax.swing.JTextField jTextFieldRogo;
    public static javax.swing.JFormattedTextField jftfCNPJ;
    private javax.swing.JLabel jlBPJ;
    private javax.swing.JLabel jlCPJ;
    private javax.swing.JLabel jlNPJ;
    public static javax.swing.JTextField jtfBairroPJ;
    public static javax.swing.JTextField jtfCidadePJ;
    public static javax.swing.JTextField jtfNIRE;
    public static javax.swing.JTextField jtfNomePJ;
    public static javax.swing.JTextField jtfNumPJ;
    public static javax.swing.JTextField jtfSedePJ;
    // End of variables declaration//GEN-END:variables
}
