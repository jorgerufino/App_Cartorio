package Testes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;

public class Metodos_Auxiliares {
    
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
