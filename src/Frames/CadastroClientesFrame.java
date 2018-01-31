package Frames;

import Classes.Metodos_Auxiliares;
import Classes.SomenteNumeros;
import DAL.ModuloConexao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroClientesFrame extends javax.swing.JInternalFrame {
    Metodos_Auxiliares obj_aux = new Metodos_Auxiliares();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean pessoaJuridica = false;
    String idCliente = "";
    
    public CadastroClientesFrame() {
        initComponents();
        
        conexao = ModuloConexao.conector();
        
        preencherTabela();
        
        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(150);
        
        //Limita o campo Nire e Cnpj para aceitarem somente numeros e qte max. de caracteres de 20 e 14 respectivamente.
//        jTextFieldNire.setDocument(new SomenteNumeros(20));
        jTextFieldCpfCnpj.setDocument(new SomenteNumeros(14));
        
        //Captura um doubleclick do mouse e joga os valores da linha selecionada nos campos 
        jTableClientes.addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e){
            if(e.getClickCount() == 2){
                //pega o cpf/cnpj da linha selecionada e faz a busca no BD
                ArrayList cliente = obj_aux.buscaClienteCpfCnpj(jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 2).toString());
                
                //Pega o id do cliente que for clicado 2x
                idCliente = cliente.get(0).toString();
                
                //verifica se é pessoa fisica ou juridica e habilita ou desabilita os campos necessários
                if(cliente.get(3).toString().length() == 11){
                    pessoaJuridica = false;
                    jComboBoxSexo.setEnabled(true);
                    jComboBoxEstCivil.setEnabled(true);
                    jTextFieldRG.setEnabled(true);
                    jTextFieldProfissao.setEnabled(true);
                    jTextFieldNIRE.setEnabled(false);
                }
                if(cliente.get(3).toString().length() == 14){
                    pessoaJuridica = true;
                    jComboBoxSexo.setEnabled(false);
                    jComboBoxEstCivil.setEnabled(false);
                    jTextFieldRG.setEnabled(false);
                    jTextFieldProfissao.setEnabled(false);
                    jTextFieldNIRE.setEnabled(true);
                }
                
                jTextFieldNome.setText(cliente.get(1).toString());
                jTextFieldCpfCnpj.setText(cliente.get(3).toString());
                jTextFieldEnd.setText(cliente.get(6).toString());
                jTextFieldCidade.setText(cliente.get(9).toString());
                
                if(cliente.get(2) == null)
                    jTextFieldRG.setText(null);
                    else
                    jTextFieldRG.setText(cliente.get(2).toString());
                
                if(cliente.get(4) == null)
                    jTextFieldProfissao.setText(null);
                    else
                    jTextFieldProfissao.setText(cliente.get(4).toString());
                
                if(cliente.get(7) == null)
                    jTextFieldNum.setText(null);
                    else
                    jTextFieldNum.setText(cliente.get(7).toString());
                
                if(cliente.get(8) == null)
                    jTextFieldBairro.setText(null);                
                    else
                    jTextFieldBairro.setText(cliente.get(8).toString());                
                
                if(cliente.get(11) == null)
                    jTextFieldNIRE.setText(null);
                    else
                    jTextFieldNIRE.setText(cliente.get(11).toString());
                
                if(cliente.get(10) != null)
                    jComboBoxSexo.setSelectedItem(cliente.get(10).toString());
                
                if(cliente.get(5) != null)
                    jComboBoxEstCivil.setSelectedItem(cliente.get(5).toString());                
            }
        }
        });
    }

    public void consultarCliente()
    {
        int contador = 0;
        String sql = "select * from tbclientes";
        String condicao = "";
        boolean existe_condicao = false;
        
        if(jTextFieldNome.getText().trim().isEmpty() == false)
        {
            condicao = " where nomecli like '%"+jTextFieldNome.getText()+"%'";
            sql = sql + condicao;
            existe_condicao = true;
        }
        if(jTextFieldRG.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and rgcli like '%"+jTextFieldRG.getText()+"%'";
            }
            else
            {
                condicao = " where rgcli like '%"+jTextFieldRG.getText()+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldCpfCnpj.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and cpfcnpjcli like '%"+jTextFieldCpfCnpj.getText()+"%'";
            }
            else
            {
                condicao = " where cpfcnpjcli like '%"+jTextFieldCpfCnpj.getText()+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldNIRE.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and nire like '%"+jTextFieldNIRE.getText()+"%'";
            }
            else
            {
                condicao = " where nire like '%"+jTextFieldNIRE.getText()+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldProfissao.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and profissao like '%"+jTextFieldProfissao.getText()+"%'";
            }
            else
            {
                condicao = " where profissao like '%"+jTextFieldProfissao.getText()+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldEnd.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and logradourocli like '%"+jTextFieldEnd.getText()+"%'";
            }
            else
            {
                condicao = " where logradourocli like '%"+jTextFieldEnd.getText()+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldBairro.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and bairrocli like '%"+jTextFieldBairro.getText()+"%'";
            }
            else
            {
                condicao = " where bairrocli like '%"+jTextFieldBairro.getText()+"%'";
            }
            sql = sql + condicao;
            existe_condicao = true;
        }
       if(jTextFieldCidade.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and cidadecli like '%"+jTextFieldCidade.getText()+"%'";
            }
            else
            {
                condicao = " where cidadecli like '%"+jTextFieldCidade.getText()+"%'";
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
            
            DefaultTableModel dtm = (DefaultTableModel)jTableClientes.getModel();
            dtm.setNumRows(0);
            
            while (rs.next())
            {
                contador++;
                String endereço = rs.getString("logradourocli")+","+rs.getString("numerocli")+","+rs.getString("bairrocli")+","+rs.getString("cidadecli"); 
                dtm.addRow(new Object[]{rs.getString("nomecli"),rs.getString("rgcli"),rs.getString("cpfcnpjcli"),rs.getString("nire"),rs.getString("profissao"),rs.getString("estcivil"),endereço});  
            }
            if(contador == 0)
            {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void preencherTabela()
    {
        String sql = "select * from tbclientes";
        String ordenar = " order by nomecli;";
        
        try {
            sql = sql + ordenar;
            pst = conexao.prepareStatement(sql);
           
            //executa a query
            rs = pst.executeQuery();
            
            DefaultTableModel dtm = (DefaultTableModel)jTableClientes.getModel();
            dtm.setNumRows(0);
            
            while (rs.next())
            {
                String endereço = rs.getString("logradourocli")+","+rs.getString("numerocli")+","+rs.getString("bairrocli")+","+rs.getString("cidadecli"); 
                dtm.addRow(new Object[]{rs.getString("nomecli"),rs.getString("rgcli"),rs.getString("cpfcnpjcli"),rs.getString("nire"),rs.getString("profissao"),rs.getString("estcivil"),endereço}); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldRG = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCpfCnpj = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNIRE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldProfissao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxEstCivil = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEnd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNum = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jButtonCadCliente = new javax.swing.JButton();
        jButtonAlterarCliente = new javax.swing.JButton();
        jButtonDeletarCliente = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cadastro de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20))); // NOI18N

        jLabel1.setText("Nome:");

        jLabel2.setText("RG:");

        jLabel3.setText("CPF/CNPJ:");

        jLabel4.setText("NIRE:");

        jLabel5.setText("Profissão:");

        jLabel6.setText("Est. Civil");

        jComboBoxEstCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));

        jLabel7.setText("Sexo:");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "feminino" }));

        jLabel8.setText("End.");

        jLabel9.setText("Nº:");

        jLabel10.setText("Bairro:");

        jLabel11.setText("Cidade:");

        jButtonCadCliente.setText("Cadastra Cliente");
        jButtonCadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadClienteActionPerformed(evt);
            }
        });

        jButtonAlterarCliente.setText("Alterar Cliente");
        jButtonAlterarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarClienteActionPerformed(evt);
            }
        });

        jButtonDeletarCliente.setText("Exluir Cliente");
        jButtonDeletarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarClienteActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("Limpar Campos");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonConsultar.setText("Consultar Cliente");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jLabel12.setText("Obs: Dê 2 clicks no cliente para alterar seus dados ou exclui-lo.");

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "RG", "Cpf/Cnpj", "Nire", "Profissão", "Estado Civil", "Endereço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel13.setText("Lista de Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxEstCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldNum)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNIRE, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                    .addComponent(jTextFieldBairro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldProfissao, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldCidade)))
                                .addGap(19, 19, 19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonCadCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonAlterarCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonDeletarCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonLimpar))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(184, 184, 184)
                                        .addComponent(jButtonConsultar)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(512, 512, 512)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldNIRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxEstCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadCliente)
                    .addComponent(jButtonAlterarCliente)
                    .addComponent(jButtonDeletarCliente)
                    .addComponent(jButtonLimpar))
                .addGap(18, 18, 18)
                .addComponent(jButtonConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(31, 31, 31)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeletarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarClienteActionPerformed
        if (idCliente.equals("")){
            JOptionPane.showMessageDialog(null, "Selecione o cliente a ser excluído!");
        }
        else{
            int excluir = JOptionPane.showConfirmDialog(null, "Deseja excluir o Cliente: "+jTextFieldNome.getText()+
            "\n Cpf/Cnpj: "+jTextFieldCpfCnpj.getText(), "Atenção!", JOptionPane.YES_NO_OPTION);
            if (excluir == JOptionPane.YES_OPTION) 
            {
                try {
                obj_aux.deletarCliente(idCliente);
                preencherTabela();
                limparCampos();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_jButtonDeletarClienteActionPerformed

    private void jButtonCadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadClienteActionPerformed
        if(jTextFieldNome.getText().isEmpty() || jTextFieldCpfCnpj.getText().isEmpty() || jTextFieldEnd.getText().isEmpty() || jTextFieldCidade.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Preencha os campos com asterisco (*)");
        }
        else if (obj_aux.buscaClienteCpfCnpj(jTextFieldCpfCnpj.getText()).isEmpty() ==  false)
        {
            JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
        }
        else if ( Metodos_Auxiliares.isCpfCnpj(jTextFieldCpfCnpj.getText()) == false)
        {
            JOptionPane.showMessageDialog(null, "Cpf/Cnpj inválido!");
        }
        else
        {
            //passa os parametros para o metodo que cadastra no BD.
            obj_aux.cadastrarCliente(jTextFieldNome.getText(), jTextFieldRG.getText(), jTextFieldCpfCnpj.getText(), jTextFieldProfissao.getText(), 
            jComboBoxEstCivil.getSelectedItem().toString(), jTextFieldEnd.getText(), jTextFieldNum.getText(), jTextFieldBairro.getText(), 
            jTextFieldCidade.getText(), jComboBoxSexo.getSelectedItem().toString(), jTextFieldNIRE.getText());
            preencherTabela();
            
            int limpar = JOptionPane.showConfirmDialog(null, "Limpar campos?", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (limpar == JOptionPane.YES_OPTION)
            {
                jTextFieldNome.setText(null);
                jTextFieldRG.setText(null);
                jTextFieldCpfCnpj.setText(null);
                jTextFieldProfissao.setText(null);
                jComboBoxEstCivil.setSelectedIndex(0);
                jTextFieldEnd.setText(null);
                jTextFieldNum.setText(null);
                jTextFieldBairro.setText(null);
                jTextFieldCidade.setText(null);
                jComboBoxSexo.setSelectedIndex(0);
                jTextFieldNIRE.setText(null);
            }
        }
    }//GEN-LAST:event_jButtonCadClienteActionPerformed

    private void jButtonAlterarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarClienteActionPerformed
        if (idCliente.equals("")){
            JOptionPane.showMessageDialog(null, "Selecione o cliente a ser alterado!");
        }
        else{
            try {
                obj_aux.alterarCliente(idCliente, jTextFieldNome.getText(), jTextFieldRG.getText(), jTextFieldCpfCnpj.getText(), jTextFieldProfissao.getText(), 
                jComboBoxEstCivil.getSelectedItem().toString(), jTextFieldEnd.getText(), jTextFieldNum.getText(), jTextFieldBairro.getText(), 
                jTextFieldCidade.getText(), jComboBoxSexo.getSelectedItem().toString(), jTextFieldNIRE.getText());     
                preencherTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
    }//GEN-LAST:event_jButtonAlterarClienteActionPerformed

    private void limparCampos()
    {
        idCliente = "";
        jTextFieldNome.setText(null);
        jTextFieldRG.setText(null);
        jTextFieldCpfCnpj.setText(null);
        jTextFieldProfissao.setText(null);
        jComboBoxEstCivil.setSelectedIndex(0);
        jTextFieldEnd.setText(null);
        jTextFieldNum.setText(null);
        jTextFieldBairro.setText(null);
        jTextFieldCidade.setText(null);
        jComboBoxSexo.setSelectedIndex(0);
        jTextFieldNIRE.setText(null);
        
        jComboBoxSexo.setEnabled(true);
        jComboBoxEstCivil.setEnabled(true);
        jTextFieldRG.setEnabled(true);
        jTextFieldProfissao.setEnabled(true);
        jTextFieldNIRE.setEnabled(true); 
    }
    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        consultarCliente();
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroClientesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterarCliente;
    private javax.swing.JButton jButtonCadCliente;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonDeletarCliente;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JComboBox<String> jComboBoxEstCivil;
    private javax.swing.JComboBox<String> jComboBoxSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldCpfCnpj;
    private javax.swing.JTextField jTextFieldEnd;
    private javax.swing.JTextField jTextFieldNIRE;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNum;
    private javax.swing.JTextField jTextFieldProfissao;
    private javax.swing.JTextField jTextFieldRG;
    // End of variables declaration//GEN-END:variables
}
