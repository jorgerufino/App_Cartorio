
package Frames;
import Classes.Metodos_Auxiliares;
import DAL.ModuloConexao;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroProcuracoesFrame extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ArrayList idOutorgante = new ArrayList();
    ArrayList idOutorgado = new ArrayList();
    ArrayList idProcuracao = new ArrayList();
  
    public CadastroProcuracoesFrame() {
        initComponents();
        //faz conexao com o banco de dados
        conexao = ModuloConexao.conector();
        preencherTipoProcuracao();
        preencherOutorgante();
        preencherOutorgado();
        preencherTabela();
        
        jtProcuracoes.getColumnModel().getColumn(3).setPreferredWidth(10);
        jtProcuracoes.getColumnModel().getColumn(4).setPreferredWidth(10);
        
        Metodos_Auxiliares obj = new Metodos_Auxiliares();
        ArrayList livroFolha = obj.getUltimoLivroFolha();
            
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
        jTextFieldLivro.setText(""+proxLivro);
        jTextFieldFolha.setText(""+proxFolha);
    }
    
    public void updateProcuracao(String id, String livro, String folha)
    {
        String sql = "update tbprocuracaocliente set livro=? , folha=? where id=?;";
        
        try {            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, livro);
            pst.setString(2, folha);
            pst.setString(3, id);
            
            //chama o metodo para preencher a tabela depois de adicionar outorgante
            int update = pst.executeUpdate();
            if (update >0 ){
                preencherTabela();
                JOptionPane.showMessageDialog(null, "Livro/Folha alterado(s) com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deletarProcuracao(String id)
    {
        String sql = "delete from tbprocuracaocliente where id=?;";
        
        try {            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            
            //chama o metodo para preencher a tabela depois de adicionar outorgante
            int excluido = pst.executeUpdate();
            if (excluido >0 ){
                preencherTabela();
                JOptionPane.showMessageDialog(null, "Procuração excluída com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void preencherTipoProcuracao()
    {
        String sql = "select * from tbprocuracao;";
        
        try {            
            pst = conexao.prepareStatement(sql);
           
            //executa a query
            rs = pst.executeQuery();
            
            //jComboBoxTipoProc.removeAllItems();
            
            while (rs.next())
            {
                jComboBoxTipoProc.addItem(rs.getString("tipo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void preencherOutorgante()
    {
        String sql = "select * from tbclientes where nomecli like '%"+jTextFieldBusca.getText()+"%' or cpfcnpjcli like '%"+jTextFieldBusca.getText()+"%' order by nomecli;";
        
        try {            
            pst = conexao.prepareStatement(sql);
           
            //executa a query
            rs = pst.executeQuery();
            //remove todos os itens do Jcombobox
            //jComboBoxOutorgante.removeAllItems();
            
            //limpa o Array para evitar que os itens fiquem se acumulando ao usar o filtro
            idOutorgante.clear();
            
            while (rs.next())
            {
                jComboBoxOutorgante.addItem(rs.getString("nomecli")+"-"+rs.getString("cpfcnpjcli"));
                idOutorgante.add(rs.getString("idcli"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void preencherOutorgado()
    {
        String sql = "select * from tbclientes where nomecli like '%"+jTextFieldBusca.getText()+"%' or cpfcnpjcli like '%"+jTextFieldBusca.getText()+"%' order by nomecli;";
        
        try {            
            pst = conexao.prepareStatement(sql);
           
            //executa a query
            rs = pst.executeQuery();
            
            //jComboBoxOutorgado.removeAllItems();
            idOutorgado.clear();
            
            while (rs.next())
            {
                jComboBoxOutorgado.addItem(rs.getString("nomecli")+"-"+rs.getString("cpfcnpjcli"));
                idOutorgado.add(rs.getString("idcli"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void preencherTabela()
    {
        String sql = "SELECT PC.id, C.idcli as idOutorgante, C2.idcli as idOutorgado, P.tipo, C.nomecli as outorgante, C2.nomecli as outorgado, PC.livro, PC.folha FROM tbprocuracaocliente AS PC\n" +
                    "INNER JOIN tbclientes AS C ON (PC.idoutorgante = C.idcli)\n" +
                    "INNER JOIN tbclientes AS C2 ON (PC.idoutorgado = C2.idcli)\n" +
                    "INNER JOIN tbprocuracao AS P ON (PC.idproc = P.id)\n" +
                    "WHERE C.nomecli like '%"+jTextFieldBusca.getText()+"%' or C.cpfcnpjcli like '%"+jTextFieldBusca.getText()+"%' or\n" +
                    "C2.nomecli like '%"+jTextFieldBusca.getText()+"%' or C2.cpfcnpjcli like '%"+jTextFieldBusca.getText()+"%'\n" +
                    "order by PC.livro desc,PC.folha desc;";
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
           
            //executa a query
            rs = pst.executeQuery();
            
            //se existir usuario e senha correspondentes
            
            DefaultTableModel dtm = (DefaultTableModel)jtProcuracoes.getModel();
            dtm.setNumRows(0);
            
            idProcuracao.clear();
            while (rs.next())
            {
                dtm.addRow(new Object[]{rs.getString("outorgante"),rs.getString("outorgado"),rs.getString("tipo"),rs.getString("livro"),rs.getString("folha")}); 
                idProcuracao.add(rs.getString("id"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cadastrarProcuracao()
    {
        String sql = "insert into tbprocuracaocliente (idoutorgante, idoutorgado, idproc,livro,folha) \n" +
                     "values (?,?,?,?,?);";
        if(idOutorgante.get(jComboBoxOutorgante.getSelectedIndex()-1) == idOutorgado.get(jComboBoxOutorgante.getSelectedIndex()-1)){
            JOptionPane.showMessageDialog(null, "Outorgante e Outorgado são a mesma pessoa!");
        }
        else{
           try {
            //as linas abaixo preparam a consulta ao banco de dados
            //String idcliOutorgante[] = jComboBoxOutorgante.getSelectedItem().toString().split("-");            
            String tipoProcuracao="";
            
            if(jComboBoxTipoProc.getSelectedItem().toString().equals("generica"))
            {
                tipoProcuracao = "2";
            }
            if(jComboBoxTipoProc.getSelectedItem().toString().equals("previdenciaria"))
            {
                tipoProcuracao = "1";
            }
            if(jComboBoxTipoProc.getSelectedItem().toString().equals("juridica"))
            {
                tipoProcuracao = "3";
            }
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idOutorgante.get(jComboBoxOutorgante.getSelectedIndex()-1).toString());
            pst.setString(2, idOutorgado.get(jComboBoxOutorgante.getSelectedIndex()-1).toString());            
            pst.setString(3, tipoProcuracao);            
            pst.setString(4, jTextFieldLivro.getText());
            pst.setString(5, jTextFieldFolha.getText());
            //executa a query
            
            //chama o metodo para preencher a tabela depois de adicionar outorgante
            int adicionado = pst.executeUpdate();
            if (adicionado >0 ){
                JOptionPane.showMessageDialog(null, "Procuração cadastrada com sucesso!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            } 
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxOutorgante = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxTipoProc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxOutorgado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProcuracoes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtonCadProc = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldLivro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldFolha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldBusca = new javax.swing.JTextField();
        jButtonFiltrarOutorgante = new javax.swing.JButton();
        jButtonLimpaFiltro = new javax.swing.JButton();
        jButtonFiltraOutorgado = new javax.swing.JButton();
        jButtonBuscaProc = new javax.swing.JButton();
        jButtonDelProc = new javax.swing.JButton();
        jButtonAltProc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Procurações");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CARTÓRIO DO ÚNICO OFÍCIO DE BENEVIDES");

        jLabel2.setText("Outorgante:");

        jComboBoxOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Outorgante..." }));
        jComboBoxOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOutorganteActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Procuração:");

        jComboBoxTipoProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoProcActionPerformed(evt);
            }
        });

        jLabel4.setText("Outorgado:");

        jComboBoxOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Outorgado..." }));
        jComboBoxOutorgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOutorgadoActionPerformed(evt);
            }
        });

        jtProcuracoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Outorgante", "Outorgado", "Tipo de Proc.", "Livro", "Folha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtProcuracoes);
        if (jtProcuracoes.getColumnModel().getColumnCount() > 0) {
            jtProcuracoes.getColumnModel().getColumn(2).setResizable(false);
            jtProcuracoes.getColumnModel().getColumn(3).setResizable(false);
            jtProcuracoes.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Lista de Procurações");

        jButtonCadProc.setText("Cadastrar Nova Procuração");
        jButtonCadProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadProcActionPerformed(evt);
            }
        });

        jLabel6.setText("Livro:");

        jTextFieldLivro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLivroFocusGained(evt);
            }
        });

        jLabel7.setText("Folha:");

        jTextFieldFolha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldFolhaFocusGained(evt);
            }
        });

        jLabel8.setText("Campo de busca:");

        jButtonFiltrarOutorgante.setText("Filtrar Outorgante");
        jButtonFiltrarOutorgante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarOutorganteActionPerformed(evt);
            }
        });

        jButtonLimpaFiltro.setText("Limpar Filtros");
        jButtonLimpaFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpaFiltroActionPerformed(evt);
            }
        });

        jButtonFiltraOutorgado.setText("Filtrar Outorgado");
        jButtonFiltraOutorgado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltraOutorgadoActionPerformed(evt);
            }
        });

        jButtonBuscaProc.setText("Buscar procuração");
        jButtonBuscaProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscaProcActionPerformed(evt);
            }
        });

        jButtonDelProc.setText("Deletar Procuração");
        jButtonDelProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelProcActionPerformed(evt);
            }
        });

        jButtonAltProc.setText("Alterar Livro/Folha");
        jButtonAltProc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltProcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTipoProc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonFiltrarOutorgante)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonFiltraOutorgado))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBoxOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jComboBoxOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addComponent(jButtonBuscaProc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonCadProc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonAltProc)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonDelProc)
                                    .addComponent(jButtonLimpaFiltro)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel5)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButtonFiltrarOutorgante)
                    .addComponent(jButtonLimpaFiltro)
                    .addComponent(jButtonFiltraOutorgado))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxOutorgante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxOutorgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxTipoProc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscaProc)
                    .addComponent(jButtonDelProc)
                    .addComponent(jButtonCadProc)
                    .addComponent(jButtonAltProc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(873, 580));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxOutorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOutorgadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxOutorgadoActionPerformed

    private void jButtonCadProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadProcActionPerformed
        // TODO add your handling code here:
        
        if(jComboBoxOutorgante.getSelectedIndex() ==0)
        {
            JOptionPane.showMessageDialog(null, "Selecione o Outorgante!");
        }
        else if(jComboBoxOutorgado.getSelectedIndex() ==0)
        {
            JOptionPane.showMessageDialog(null, "Selecione o Outorgado!");
        }             
        else{
            try {
             cadastrarProcuracao();
             jTextFieldBusca.setText(null);
             preencherTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
       
        }
        
    }//GEN-LAST:event_jButtonCadProcActionPerformed

    private void jButtonFiltrarOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarOutorganteActionPerformed
        // TODO add your handling code here:
        jComboBoxOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Campo filtrado..." }));
        preencherOutorgante();        
    }//GEN-LAST:event_jButtonFiltrarOutorganteActionPerformed

    private void jComboBoxOutorganteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOutorganteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxOutorganteActionPerformed

    private void jButtonLimpaFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpaFiltroActionPerformed
        // TODO add your handling code here:
        jComboBoxOutorgante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Outorgante..." }));
        preencherOutorgante();
        jComboBoxOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Outorgado..." }));
        preencherOutorgado();
    }//GEN-LAST:event_jButtonLimpaFiltroActionPerformed

    private void jButtonFiltraOutorgadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltraOutorgadoActionPerformed
        // TODO add your handling code here:
        jComboBoxOutorgado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Campo filtrado..." }));
        preencherOutorgado();
    }//GEN-LAST:event_jButtonFiltraOutorgadoActionPerformed

    private void jButtonBuscaProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscaProcActionPerformed
        // TODO add your handling code here:
        preencherTabela();
    }//GEN-LAST:event_jButtonBuscaProcActionPerformed

    private void jTextFieldLivroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLivroFocusGained
        // TODO add your handling code here:
        jTextFieldLivro.selectAll();
    }//GEN-LAST:event_jTextFieldLivroFocusGained

    private void jTextFieldFolhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldFolhaFocusGained
        // TODO add your handling code here:
        jTextFieldFolha.selectAll();
    }//GEN-LAST:event_jTextFieldFolhaFocusGained

    private void jButtonDelProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelProcActionPerformed
        // TODO add your handling code here:
        deletarProcuracao(idProcuracao.get(jtProcuracoes.getSelectedRow()).toString());
    }//GEN-LAST:event_jButtonDelProcActionPerformed

    private void jButtonAltProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltProcActionPerformed
        // TODO add your handling code here:
        try {
            updateProcuracao(idProcuracao.get(jtProcuracoes.getSelectedRow()).toString(), jtProcuracoes.getValueAt(jtProcuracoes.getSelectedRow(), 3).toString(), jtProcuracoes.getValueAt(jtProcuracoes.getSelectedRow(), 4).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButtonAltProcActionPerformed

    private void jComboBoxTipoProcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoProcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoProcActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProcuracoesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroProcuracoesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAltProc;
    private javax.swing.JButton jButtonBuscaProc;
    private javax.swing.JButton jButtonCadProc;
    private javax.swing.JButton jButtonDelProc;
    private javax.swing.JButton jButtonFiltraOutorgado;
    private javax.swing.JButton jButtonFiltrarOutorgante;
    private javax.swing.JButton jButtonLimpaFiltro;
    private javax.swing.JComboBox<String> jComboBoxOutorgado;
    private javax.swing.JComboBox<String> jComboBoxOutorgante;
    private javax.swing.JComboBox<String> jComboBoxTipoProc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldBusca;
    private javax.swing.JTextField jTextFieldFolha;
    private javax.swing.JTextField jTextFieldLivro;
    private javax.swing.JTable jtProcuracoes;
    // End of variables declaration//GEN-END:variables
}
