/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import java.sql.*;

public class ModuloConexao {
    //metodo responsavel por estabelecer a conexao com o banco
    public static Connection conector()
    {
        java.sql.Connection conexao = null;
        //chama o driver do banco de dados 
        String driver = "com.mysql.jdbc.Driver";
        //armazenando informaçoes referentes ao banco 
        String url = "jdbc:mysql://localhost:3306/dbcartorio";
        String user = "root";
        String password = "";
        //estabelecendo conexao com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }
    
}
