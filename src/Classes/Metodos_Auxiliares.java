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

public class Metodos_Auxiliares {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Metodos_Auxiliares() {
        conexao = ModuloConexao.conector();
    }
    
    public boolean verificaCpfCnpj(String cpfCnpj)
    {
        String cpfcnpjSemFormatacao = cpfCnpj;
        //removendo os pontos e o traço do cpf
        cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("-", "");
        //somente no caso do ponto é obrigatorio usar os colchetes
        cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("[.]", "");
        cpfcnpjSemFormatacao = cpfcnpjSemFormatacao.replaceAll("/", "");
        boolean existe = false;
        
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
                existe = true;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return existe;
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
    //este metodo recebe um cpf do tipo formatado. Ex. 111.222.333-44
    public boolean isCPF(String valor) {
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
    
    public void debug(Object s)
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
    public String iniciaisMaisculas(String frase)
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
       } } tudo=pos+mais;        
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
