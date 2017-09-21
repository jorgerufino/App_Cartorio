/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.ArrayList;  
  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JTextField;  
  
public class GeradorJTextFields extends JFrame {  
    private static final long serialVersionUID = 1L;  
    private ArrayList<JTextField> listTextField;  
    private JTextField text; //Não precisa dessa variavel já que você não a usa  
  
    public GeradorJTextFields() {  
        initComponents();  
    } //Fecha o escopo do construtor  
  
    public void initComponents() { //Cria o método initComponents  
        setDefaultCloseOperation(EXIT_ON_CLOSE);//Ação a ser tomada quando fecha no "X" o JFrame.  
        setSize(300, 300); //Dimensão do JFrame.  
        listTextField = new ArrayList<>();  
        final JPanel pnlTela = new JPanel();  
        JButton btnNovo = new JButton("Criar NOVO TÓPICO");  
        btnNovo.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent arg0) {  
                JTextField text = new JTextField();  
                listTextField.add(text);  
                text.setText("Text " + listTextField.size());  
                pnlTela.add(text);  
                paintAll(getGraphics());  
            }  
        });  
        pnlTela.add(btnNovo); //add o botão ao JPanel  
        getContentPane().add(pnlTela); //add o JPanel ao JFRame.  
    } //fecha o método initComponents  
      
    public static void main(String[] args) {  
        GeradorJTextFields text = new GeradorJTextFields();  
        text.setVisible(true);  
    }  
}
