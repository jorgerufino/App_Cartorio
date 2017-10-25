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

public class CadastroClienteDialog extends javax.swing.JDialog {
    Metodos_Auxiliares obj_aux = new Metodos_Auxiliares();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean pessoaJuridica = false;
    String idCliente = "";
    
    public CadastroClienteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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
                    jComboBoxEstCiv.setEnabled(true);
                    jTextFieldRG.setEnabled(true);
                    jTextFieldProfissao.setEnabled(true);
                    jTextFieldNire.setEnabled(false);
                }
                if(cliente.get(3).toString().length() == 14){
                    pessoaJuridica = true;
                    jComboBoxSexo.setEnabled(false);
                    jComboBoxEstCiv.setEnabled(false);
                    jTextFieldRG.setEnabled(false);
                    jTextFieldProfissao.setEnabled(false);
                    jTextFieldNire.setEnabled(true);
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
                    jTextFieldNire.setText(null);
                    else
                    jTextFieldNire.setText(cliente.get(11).toString());
                
                if(cliente.get(10) != null)
                    jComboBoxSexo.setSelectedItem(cliente.get(10).toString());
                
                if(cliente.get(5) != null)
                    jComboBoxEstCiv.setSelectedItem(cliente.get(5).toString());                
            }
        }
        });
    }
    
    public void consultarCliente()
    {
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
       if(jTextFieldNire.getText().trim().isEmpty() == false)
        {
            if (existe_condicao){
                condicao = " and nire like '%"+jTextFieldNire.getText()+"%'";
            }
            else
            {
                condicao = " where nire like '%"+jTextFieldNire.getText()+"%'";
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
                String endereço = rs.getString("logradourocli")+","+rs.getString("numerocli")+","+rs.getString("bairrocli")+","+rs.getString("cidadecli"); 
                dtm.addRow(new Object[]{rs.getString("nomecli"),rs.getString("rgcli"),rs.getString("cpfcnpjcli"),rs.getString("nire"),rs.getString("profissao"),rs.getString("estcivil"),endereço});  
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldRG = new javax.swing.JTextField();
        jButtonCadCliente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNire = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCpfCnpj = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldProfissao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxEstCiv = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox<>();
        jTextFieldEnd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldNum = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonLimpar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButtonExcluir = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CARTÓRIO DO ÚNICO OFÍCIO DE BENEVIDES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 3, 20))); // NOI18N

        jLabel1.setText("Nome*:");

        jLabel2.setText("RG:");

        jTextFieldNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNomeFocusGained(evt);
            }
        });

        jTextFieldRG.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldRGFocusGained(evt);
            }
        });

        jButtonCadCliente.setText("Cadastar Cliente");
        jButtonCadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Nire:");

        jTextFieldNire.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNireFocusGained(evt);
            }
        });

        jLabel4.setText("Cpf/Cnpj*:");

        jTextFieldCpfCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCpfCnpjFocusGained(evt);
            }
        });

        jLabel5.setText("Profissão:");

        jTextFieldProfissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldProfissaoFocusGained(evt);
            }
        });

        jLabel6.setText("Est.Civil:");

        jComboBoxEstCiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));

        jLabel7.setText("Sexo:");

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "feminino" }));
        jComboBoxSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSexoActionPerformed(evt);
            }
        });

        jTextFieldEnd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEndFocusGained(evt);
            }
        });

        jLabel8.setText("End*:");

        jTextFieldNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNumFocusGained(evt);
            }
        });

        jLabel9.setText("Nº:");

        jLabel10.setText("Bairro:");

        jTextFieldBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBairroFocusGained(evt);
            }
        });

        jLabel11.setText("Cidade*:");

        jTextFieldCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCidadeFocusGained(evt);
            }
        });

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Rg", "Cpf/Cnpj", "Nire", "Profissao", "Est.Civil", "Endereço"
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

        jButtonLimpar.setText("Limpar Campos");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("Alterar Cliente");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jLabel12.setText("Obs: Dê 2 clicks no cliente para alterar seus dados ou exclui-lo.");

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonConsultar.setText("Consultar Cliente");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldNire, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldProfissao))
                    .addComponent(jTextFieldNome)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCidade))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEstCiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jButtonCadCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLimpar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(55, 55, 55)
                        .addComponent(jButtonConsultar)))
                .addGap(0, 262, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxEstCiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadCliente)
                    .addComponent(jButtonLimpar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jButtonConsultar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(931, 593));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            jComboBoxEstCiv.getSelectedItem().toString(), jTextFieldEnd.getText(), jTextFieldNum.getText(), jTextFieldBairro.getText(), 
            jTextFieldCidade.getText(), jComboBoxSexo.getSelectedItem().toString(), jTextFieldNire.getText());
            preencherTabela();
            
            int limpar = JOptionPane.showConfirmDialog(null, "Limpar campos?", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (limpar == JOptionPane.YES_OPTION)
            {
                jTextFieldNome.setText(null);
                jTextFieldRG.setText(null);
                jTextFieldCpfCnpj.setText(null);
                jTextFieldProfissao.setText(null);
                jComboBoxEstCiv.setSelectedIndex(0);
                jTextFieldEnd.setText(null);
                jTextFieldNum.setText(null);
                jTextFieldBairro.setText(null);
                jTextFieldCidade.setText(null);
                jComboBoxSexo.setSelectedIndex(0);
                jTextFieldNire.setText(null);
            }
        }
    }//GEN-LAST:event_jButtonCadClienteActionPerformed

    private void jComboBoxSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSexoActionPerformed
        //pega o indice selecionado para saber qual o sexo (masc = 0/femin = 1)
        int sexoOut = jComboBoxSexo.getSelectedIndex();
        int indiceComboBoxEstCivil = jComboBoxEstCiv.getSelectedIndex();
        //se sexo feminino
        if(sexoOut == 1)
        {
            //cria um novo combobox com os valores no feminino
            jComboBoxEstCiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteira", "casada", "divorciada", "viúva" }));
            //mantem o item selecionado
            jComboBoxEstCiv.setSelectedIndex(indiceComboBoxEstCivil);
        }
        if(sexoOut == 0)
        {
            jComboBoxEstCiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "solteiro", "casado", "divorciado", "viúvo" }));
            jComboBoxEstCiv.setSelectedIndex(indiceComboBoxEstCivil);
        }
    }//GEN-LAST:event_jComboBoxSexoActionPerformed

    private void jTextFieldNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNomeFocusGained
        // TODO add your handling code here:
        jTextFieldNome.selectAll();
    }//GEN-LAST:event_jTextFieldNomeFocusGained

    private void jTextFieldRGFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRGFocusGained
        // TODO add your handling code here:
        jTextFieldRG.selectAll();
    }//GEN-LAST:event_jTextFieldRGFocusGained

    private void jTextFieldCpfCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCpfCnpjFocusGained
        // TODO add your handling code here:
        jTextFieldCpfCnpj.selectAll();
    }//GEN-LAST:event_jTextFieldCpfCnpjFocusGained

    private void jTextFieldNireFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNireFocusGained
        // TODO add your handling code here:
        jTextFieldNire.selectAll();
    }//GEN-LAST:event_jTextFieldNireFocusGained

    private void jTextFieldProfissaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldProfissaoFocusGained
        // TODO add your handling code here:
        jTextFieldProfissao.selectAll();
    }//GEN-LAST:event_jTextFieldProfissaoFocusGained

    private void jTextFieldEndFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEndFocusGained
        // TODO add your handling code here:
        jTextFieldEnd.selectAll();
    }//GEN-LAST:event_jTextFieldEndFocusGained

    private void jTextFieldNumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNumFocusGained
        // TODO add your handling code here:
        jTextFieldNum.selectAll();
    }//GEN-LAST:event_jTextFieldNumFocusGained

    private void jTextFieldBairroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBairroFocusGained
        // TODO add your handling code here:
        jTextFieldBairro.selectAll();
    }//GEN-LAST:event_jTextFieldBairroFocusGained

    private void jTextFieldCidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCidadeFocusGained
        // TODO add your handling code here:
        jTextFieldCidade.selectAll();
    }//GEN-LAST:event_jTextFieldCidadeFocusGained

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        // TODO add your handling code here:
        idCliente = "";
        jTextFieldNome.setText(null);
        jTextFieldRG.setText(null);
        jTextFieldCpfCnpj.setText(null);
        jTextFieldProfissao.setText(null);
        jComboBoxEstCiv.setSelectedIndex(0);
        jTextFieldEnd.setText(null);
        jTextFieldNum.setText(null);
        jTextFieldBairro.setText(null);
        jTextFieldCidade.setText(null);
        jComboBoxSexo.setSelectedIndex(0);
        jTextFieldNire.setText(null);
        
        jComboBoxSexo.setEnabled(true);
        jComboBoxEstCiv.setEnabled(true);
        jTextFieldRG.setEnabled(true);
        jTextFieldProfissao.setEnabled(true);
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:        
        if (idCliente.equals("")){
            JOptionPane.showMessageDialog(null, "Selecione o cliente a ser alterado!");
        }
        else{
            try {
                obj_aux.alterarCliente(idCliente, jTextFieldNome.getText(), jTextFieldRG.getText(), jTextFieldCpfCnpj.getText(), jTextFieldProfissao.getText(), 
                jComboBoxEstCiv.getSelectedItem().toString(), jTextFieldEnd.getText(), jTextFieldNum.getText(), jTextFieldBairro.getText(), 
                jTextFieldCidade.getText(), jComboBoxSexo.getSelectedItem().toString(), jTextFieldNire.getText());     
                preencherTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
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
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        // TODO add your handling code here:
        consultarCliente();
    }//GEN-LAST:event_jButtonConsultarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroClienteDialog dialog = new CadastroClienteDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadCliente;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JComboBox<String> jComboBoxEstCiv;
    private javax.swing.JComboBox<String> jComboBoxSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldCpfCnpj;
    private javax.swing.JTextField jTextFieldEnd;
    private javax.swing.JTextField jTextFieldNire;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNum;
    private javax.swing.JTextField jTextFieldProfissao;
    private javax.swing.JTextField jTextFieldRG;
    // End of variables declaration//GEN-END:variables
}
