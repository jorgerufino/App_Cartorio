package Classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import java.sql.*;
import DAL.ModuloConexao;
import java.util.ArrayList;

public class Metodos_Auxiliares {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Metodos_Auxiliares() {
        conexao = ModuloConexao.conector();
    }
    
    public ArrayList buscaUsuario(String login)
    {
        ArrayList usuario = new ArrayList();
        
        String sql = "select * from tbusuarios where login=?";
        
        try {
            //as linhas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            //pega os valores dos campos de texto e substitue nas interrogaçoes 
            pst.setString(1, login);
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            if (rs.next())
            {
                usuario.add(rs.getString("iduser"));//indice 0
                usuario.add(rs.getString("usuario"));//1
                usuario.add(rs.getString("fone"));//2
                usuario.add(rs.getString("login"));//3
                usuario.add(rs.getString("perfil"));//4
                usuario.add(rs.getString("ativo"));//5
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return usuario;
    }
    
    public void alterarUsuario(String nome, String telefone, String login, String senha, String perfil, String id)
    {
        String sql = "update tbusuarios set usuario =?,fone=?,login=?,senha=?,perfil=? where iduser=?";
        
        try {
            //remove os excessos de espaços em branco antes de salvar no banco
            nome = nome.replaceAll("\\s+", " ");
            login = login.replaceAll("\\s+", " ");
            
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome.toUpperCase());
            pst.setString(2, telefone);
            pst.setString(3, login);
            pst.setString(4, senha);
            pst.setString(5, perfil);
            pst.setString(6, id);
            
            //executa a query
            int adicionado = pst.executeUpdate();
            if (adicionado >0 ){
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cadastrarUsuario(String nome, String telefone, String login, String senha, String perfil, String ativo)
    {
        String sql = "insert into tbusuarios(usuario,fone,login,senha,ativo,perfil)"
                                            + "values (?,?,?,?,?,?);";
        
        try {
            nome = nome.replaceAll("\\s+", " ");
            login = login.replaceAll("\\s+", " ");
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome.toUpperCase().trim());
            pst.setString(2, telefone);
            pst.setString(3, login);
            pst.setString(4, senha);
            pst.setString(5, ativo);
            pst.setString(6, perfil);
            
            //executa a query
            int adicionado = pst.executeUpdate();
            if (adicionado >0 ){
                JOptionPane.showMessageDialog(null, "Uusário cadastrado com sucesso!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    } 
    
    //Devolve um ArrayList com o ultimo Livro e Folha
    public ArrayList getUltimoLivroFolha()
    { 
        ArrayList folhaLivro = new ArrayList();
        String sql = "SELECT PC.id, C.idcli as idOutorgante, C2.idcli as idOutorgado, P.tipo, C.nomecli as outorgante, C2.nomecli as outorgado, PC.livro, PC.folha FROM tbprocuracaocliente AS PC\n" +
                    "INNER JOIN tbclientes AS C ON (PC.idoutorgante = C.idcli)\n" +
                    "INNER JOIN tbclientes AS C2 ON (PC.idoutorgado = C2.idcli)\n" +
                    "INNER JOIN tbprocuracao AS P ON (PC.idproc = P.id)\n" +
                    "where PC.livro <> ''\n" +
                    "order by PC.livro desc, PC.folha desc limit 1;";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            if (rs.next())
            {
                folhaLivro.add(rs.getString("livro"));
                folhaLivro.add(rs.getString("folha"));
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return folhaLivro;
    }
    
    public ArrayList buscaClienteCpfCnpj(String cpfCnpj)
    {
        String cpfcnpjSemFormatacao = cpfCnpj;
        //removendo os pontos e o traço do cpf
        cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("-", "");
        //somente no caso do ponto é obrigatorio usar os colchetes
        cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("[.]", "");
        cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("/", "");
        ArrayList cliente = new ArrayList();
        
        String sql = "select * from tbclientes where cpfcnpjcli=?";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            pst = conexao.prepareStatement(sql);
            //pega os valores dos campos de texto e substitue nas interrogaçoes 
            pst.setString(1, cpfcnpjSemFormatacao);
            //executa a query
            rs = pst.executeQuery();
            //se existir usuario e senha correspondentes
            if (rs.next())
            {
                cliente.add(rs.getString("idcli"));//indice 0
                cliente.add(rs.getString("nomecli"));//1
                cliente.add(rs.getString("rgcli"));//2
                cliente.add(rs.getString("cpfcnpjcli"));//3
                cliente.add(rs.getString("profissao"));//4
                cliente.add(rs.getString("estcivil"));//5
                cliente.add(rs.getString("logradourocli"));//6
                cliente.add(rs.getString("numerocli"));//7
                cliente.add(rs.getString("bairrocli"));//8
                cliente.add(rs.getString("cidadecli"));//9
                cliente.add(rs.getString("sexo"));//10
                cliente.add(rs.getString("nire"));//11
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return cliente;
    }
    
    public void alterarCliente(String id, String nome,String rg,  String cpfcnpj, String profissao, String estCivil, String end,
                               String num, String bairro, String cidade, String sexo, String nire)
    {
        //debug(idcli+"/"+idproc+"/"+tipocliente+"/"+livro+"/"+folha);
        String sql = "update tbclientes set nomecli =?,rgcli=?, cpfcnpjcli=?, profissao=?,estcivil=?,logradourocli=?,\n" +
                     "numerocli=?, bairrocli=?,cidadecli=?, sexo=?, nire=? where idcli = ?;";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, rg);            
            pst.setString(3, cpfcnpj);            
            pst.setString(4, profissao);
            pst.setString(5, estCivil);
            pst.setString(6, end);
            pst.setString(7, num);
            pst.setString(8, bairro);
            pst.setString(9, cidade);
            pst.setString(10, sexo);
            pst.setString(11, nire);
            pst.setString(12, id);
            
            //executa a query
            
            //chama o metodo para preencher a tabela depois de adicionar outorgante
            int alterado = pst.executeUpdate();
            if (alterado >0 ){
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deletarCliente(String id)
    {
        String sql = "delete from tbclientes where idcli=?;";
        
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id);
            
            //chama o metodo para preencher a tabela depois de adicionar outorgante
            int alterado = pst.executeUpdate();
            if (alterado >0 ){
                JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cadastrarOutorganteOutorgadoPj(int idOutorgante, int idOutorgado, int idproc, String livro, String folha)
    {
        //debug(idcli+"/"+idproc+"/"+tipocliente+"/"+livro+"/"+folha);
        String sql = "insert into tbprocuracaocliente (idoutorgante, idoutorgado, idproc,livro,folha) \n" +
                     "values (?,?,?,?,?);";
        try {
            //as linas abaixo preparam a consulta ao banco de dados
            
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idOutorgante);
            pst.setInt(2, idOutorgado);            
            pst.setInt(3, idproc);            
            pst.setString(4, livro);
            pst.setString(5, folha);
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
    
    public void cadastrarCliente(String nome, String rg, String cpfcnpj, String profissao, String estCivil, String end, String num,
                                 String bairro, String cidade, String sexo, String nire)
    {
        String sql = "insert into tbclientes(nomecli,rgcli,cpfcnpjcli,profissao,estcivil, logradourocli, numerocli,bairrocli,cidadecli,sexo,nire) "
        + "values (?,?,?,?,?,?,?,?,?,?,?);";
        
        try {
            String cpfcnpjSemFormatacao = cpfcnpj;
            //removendo os pontos e o traço do cpf
            cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("-", "");
            //somente no caso do ponto é obrigatorio usar os colchetes
            cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("[.]", "");
            cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("/", "");
            
            //as linas abaixo preparam a consulta ao banco de dados
            
            nome = nome.replaceAll("\\s+", " ");
            end = end.replaceAll("\\s+", " ");
            cidade = cidade.replaceAll("\\s+", " ");
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome.toUpperCase());
            pst.setString(2, rg.toUpperCase());
            pst.setString(3, cpfcnpjSemFormatacao);
            pst.setString(4, profissao.toLowerCase());
            pst.setString(5, estCivil);
            pst.setString(6, end);
            pst.setString(7, num);
            pst.setString(8, bairro);
            pst.setString(9, cidade);
            pst.setString(10, sexo);
            pst.setString(11, nire);
            //executa a query

            int adicionado = pst.executeUpdate();
            if (adicionado >0 ){
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     public static boolean isCpfCnpj(String valor) {
         if(valor.length() == 11)
         {
           String CPF = valor;

       // considera-se erro CPF's formados por uma sequencia de numeros iguais
           if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
               CPF.equals("22222222222") || CPF.equals("33333333333") ||
               CPF.equals("44444444444") || CPF.equals("55555555555") ||
               CPF.equals("66666666666") || CPF.equals("77777777777") ||
               CPF.equals("88888888888") || CPF.equals("99999999999") ||
              (CPF.length() != 11))
              return(false);

           char dig10, dig11;
           int sm, i, r, num, peso;

       // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
           try {
       // Calculo do 1o. Digito Verificador
             sm = 0;
             peso = 10;
             for (i=0; i<9; i++) {              
       // converte o i-esimo caractere do CPF em um numero:
       // por exemplo, transforma o caractere '0' no inteiro 0         
       // (48 eh a posicao de '0' na tabela ASCII)         
               num = (int)(CPF.charAt(i) - 48); 
               sm = sm + (num * peso);
               peso = peso - 1;
             }

             r = 11 - (sm % 11);
             if ((r == 10) || (r == 11))
                dig10 = '0';
             else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

       // Calculo do 2o. Digito Verificador
             sm = 0;
             peso = 11;
             for(i=0; i<10; i++) {
               num = (int)(CPF.charAt(i) - 48);
               sm = sm + (num * peso);
               peso = peso - 1;
             }

             r = 11 - (sm % 11);
             if ((r == 10) || (r == 11))
                dig11 = '0';
             else dig11 = (char)(r + 48);

       // Verifica se os digitos calculados conferem com os digitos informados.
             if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
             else return(false);
           } catch (InputMismatchException erro) {
               return(false);
           }
         }
         
         if(valor.length() == 14)
         {
           String CNPJ = valor;

   // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
           if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                   || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                   || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                   || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                   || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                   || (CNPJ.length() != 14)) {
               return (false);
           }

           char dig13, dig14;
           int sm, i, r, num, peso;

   // "try" - protege o código para eventuais erros de conversao de tipo (int)
           try {
   // Calculo do 1o. Digito Verificador
               sm = 0;
               peso = 2;
               for (i = 11; i >= 0; i--) {
   // converte o i-ésimo caractere do CNPJ em um número:
   // por exemplo, transforma o caractere '0' no inteiro 0
   // (48 eh a posição de '0' na tabela ASCII)
                   num = (int) (CNPJ.charAt(i) - 48);
                   sm = sm + (num * peso);
                   peso = peso + 1;
                   if (peso == 10) {
                       peso = 2;
                   }
               }

               r = sm % 11;
               if ((r == 0) || (r == 1)) {
                   dig13 = '0';
               } else {
                   dig13 = (char) ((11 - r) + 48);
               }

   // Calculo do 2o. Digito Verificador
               sm = 0;
               peso = 2;
               for (i = 12; i >= 0; i--) {
                   num = (int) (CNPJ.charAt(i) - 48);
                   sm = sm + (num * peso);
                   peso = peso + 1;
                   if (peso == 10) {
                       peso = 2;
                   }
               }

               r = sm % 11;
               if ((r == 0) || (r == 1)) {
                   dig14 = '0';
               } else {
                   dig14 = (char) ((11 - r) + 48);
               }

   // Verifica se os dígitos calculados conferem com os dígitos informados.
               if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                   return (true);
               } else {
                   return (false);
               }
           } catch (InputMismatchException erro) {
               return (false);
           }
         }
         else{
             return false;
         }
    }
    
    public static boolean isCNPJ(String valor) {        
        String teste[] = valor.split("[.,/,-]");
        String CNPJ = teste[0]+teste[1]+teste[2]+teste[3]+teste[4];
        
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
    
    //este metodo recebe um cpf do tipo formatado. Ex. 111.222.333-44
    public static boolean isCPF(String valor) {
        //pega o cpf e quebra em vetores de acordo com os marcadores "." e "-"
        String teste[] = valor.split("[.,-]");
        String CPF = teste[0]+teste[1]+teste[2]+teste[3];
        
    // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
           (CPF.length() != 11))
           return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

    // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
    // Calculo do 1o. Digito Verificador
          sm = 0;
          peso = 10;
          for (i=0; i<9; i++) {              
    // converte o i-esimo caractere do CPF em um numero:
    // por exemplo, transforma o caractere '0' no inteiro 0         
    // (48 eh a posicao de '0' na tabela ASCII)         
            num = (int)(CPF.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
          }

          r = 11 - (sm % 11);
          if ((r == 10) || (r == 11))
             dig10 = '0';
          else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

    // Calculo do 2o. Digito Verificador
          sm = 0;
          peso = 11;
          for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
          }

          r = 11 - (sm % 11);
          if ((r == 10) || (r == 11))
             dig11 = '0';
          else dig11 = (char)(r + 48);

    // Verifica se os digitos calculados conferem com os digitos informados.
          if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
             return(true);
          else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
      }
    //Escreve data toda por extens. EX: dezenove de maio de mil novecentos e oitenta e seis
    public String dataTodaPorExtenso(String data)
    {
         //cria um array onde cada posiçao vai receber o valor do Separador (/), no caso XX/YYYY/
        String dataExtenso[] = data.split("/");  
        
        //posiçao 0 do array é o dia
        String dia = dataExtenso[0];        
        
        //posiçao 1 do array é o mês (transformar em inteiro para poder jogar no Switch/Case para definir o mês
        int numeroDoMes = Integer.parseInt(dataExtenso[1]);
        
        //posicao 2 do array é o ano
        String ano = dataExtenso[2];
        String mes="";
        
        //define qual o mês
        switch (numeroDoMes) 
        {
            case 1:
                mes = "Janeiro";
                break;
            case 2:
                mes = "Fevereiro";
                break;
            case 3:
                mes = "Março";                    
                break;
            case 4:
                mes = "Abril";                    
                break;
            case 5:
                mes = "Maio";                    
                break;
            case 6:
                mes = "Junho";                    
                break;
            case 7:
                mes = "Julho";                    
                break;
            case 8:
                mes = "Agosto";                    
                break;
            case 9:
                mes = "Setembro";                    
                break;
            case 10:
                mes = "Outubro";                    
                break;
            case 11:
                mes = "Novembro";                    
                break;
            case 12:
                mes = "Dezembro";                    
                break;
            default:
                break;
        }
        //Cria um obj da classe EscreverPorExtensoNumero para escrever os dias e o ano por extenso
        EscreverPorExtensoNumero objExtenso = new EscreverPorExtensoNumero();
        
        String dataPorExtenso = objExtenso.write(new BigDecimal(dia)).toLowerCase()+"("+dia+") "+"dias do mês de "+mes.toLowerCase()
        +" do ano de "+objExtenso.write(new BigDecimal(ano)).toLowerCase()+"("+ano+")";
        
        return dataPorExtenso;
    }   
    
    public static void debug(Object s)
    {
        JOptionPane.showMessageDialog(null, s);
        System.exit(0);
    }
    //Escreve data por extenso. EX: 19 de Maio de 1986
    public String dataPorExtenso(String data)
    {
         //cria um array onde cada posiçao vai receber o valor do Separador (/), no caso XX/YYYY/
        String dataExtenso[] = data.split("/");  
        String dia = dataExtenso[0];
        String mes="";
        int numeroDoMes = Integer.parseInt(dataExtenso[1]);
        String ano = dataExtenso[2];
        
        switch (numeroDoMes) 
        {
            case 1:
                mes = "Janeiro";
                break;
            case 2:
                mes = "Fevereiro";
                break;
            case 3:
                mes = "Março";                    
                break;
            case 4:
                mes = "Abril";                    
                break;
            case 5:
                mes = "Maio";                    
                break;
            case 6:
                mes = "Junho";                    
                break;
            case 7:
                mes = "Julho";                    
                break;
            case 8:
                mes = "Agosto";                    
                break;
            case 9:
                mes = "Setembro";                    
                break;
            case 10:
                mes = "Outubro";                    
                break;
            case 11:
                mes = "Novembro";                    
                break;
            case 12:
                mes = "Dezembro";                    
                break;
            default:
                break;
        }
        String dataPorExtenso = dia +" de "+ mes +" de " + ano;
        
        return dataPorExtenso;
    }
    
    //Transforma as iniciais em maisculas
    public static String iniciaisMaisculas(String frase)
    {
       String posicao="";
       String mais="";
       String tudo="";
       String maiuscula2="";  
       
        posicao=""+frase.charAt(0);//pega a primeira letra que sera maiuscula
        String pos=posicao.toUpperCase();    //transforma em maiuscula
        
        for(int i=1;i<frase.length();i++){  //coloca o for de acordo com o tamanho
            mais=mais+frase.charAt(i); //acrescenta as letras
            if( frase.charAt(i) == ' '){  //se houver um espaço a próxima vai ser transformada em maisucula.
                String maiuscula=""+frase.charAt(i+1);//pega a próxima apos o espaço  
                maiuscula2=maiuscula.toUpperCase();       //transforma em maiuscula.
                mais=mais+maiuscula2;//acrescenta a maiuscula a palavra completa, que se chama mais
                i=i+1; //soma um, pois uma letra minuscula foi substituida e ja foi acrescentada                                 
            } 
        }
        tudo=pos+mais;        
      return tudo; 
    }
    
    //Metodo para reescrever num documento Word
    public HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText){
        Range r1 = doc.getRange(); 

        for (int i = 0; i < r1.numSections(); ++i ) { 
            Section s = r1.getSection(i); 
            for (int x = 0; x < s.numParagraphs(); x++) { 
                Paragraph p = s.getParagraph(x); 
                for (int z = 0; z < p.numCharacterRuns(); z++) { 
                    CharacterRun run = p.getCharacterRun(z); 
                    String text = run.text();
                    if(text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    } 
                }
            }
        } 
        return doc;
    }
    //Metodo para reescrever num documento Word
    public void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath);
            doc.write(out);
        }
        finally{
            out.close();
        }
    }
}
