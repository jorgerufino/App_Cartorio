/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerarProcuracoes;

import Testes.EscreverPorExtensoNumero;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class GeraProcuracoes 
{
    String outorgante, endOutorgante, outorgado, endOutorgado, aRogo, endRogo, selo,filePath, filePath2, modeloProc,rgOutorgante, rgOutorgado,rgRogo,
    cpfOutorgante, cpfOutorgado, cpfRogo, profOutorgante, profOutorgado, profRogo,estCivilOutorgante,estCivilOutorgado,estCivilRogo,data,escrevente, cargo;
    int civOutorgante,civOutorgado,civRogo, indiceEscrevente;
    boolean existeRogo;

    public int getIndiceEscrevente() {
        return indiceEscrevente;
    }

    public void setIndiceEscrevente(int indiceEscrevente) {
        this.indiceEscrevente = indiceEscrevente;
    }
    public String getEscrevente() {
        return escrevente;
    }

    public void setEscrevente(String escrevente) {
        this.escrevente = escrevente;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String getRgOutorgado() {
        return rgOutorgado;
    }

    public void setRgOutorgado(String rgOutorgado) {
        this.rgOutorgado = rgOutorgado;
    }

    public String getRgRogo() {
        return rgRogo;
    }

    public void setRgRogo(String rgRogo) {
        this.rgRogo = rgRogo;
    }

    public String getCpfOutorgado() {
        return cpfOutorgado;
    }

    public void setCpfOutorgado(String cpfOutorgado) {
        this.cpfOutorgado = cpfOutorgado;
    }

    public String getCpfRogo() {
        return cpfRogo;
    }

    public void setCpfRogo(String cpfRogo) {
        this.cpfRogo = cpfRogo;
    }

    public String getProfOutorgado() {
        return profOutorgado;
    }

    public void setProfOutorgado(String profOutorgado) {
        this.profOutorgado = profOutorgado;
    }

    public String getProfRogo() {
        return profRogo;
    }

    public void setProfRogo(String profRogo) {
        this.profRogo = profRogo;
    }
    
    public int getCivOutorgado() {
        return civOutorgado;
    }

    public void setCivOutorgado(int civOutorgado) {
        this.civOutorgado = civOutorgado;
    }

    public int getCivRogo() {
        return civRogo;
    }

    public void setCivRogo(int civRogo) {
        this.civRogo = civRogo;
    }

    public String getEstCivilOutorgante() {
        return estCivilOutorgante;
    }

    public void setEstCivilOutorgante(String estCivilOutorgante) {
        this.estCivilOutorgante = estCivilOutorgante;
    }

    public String getEstCivilOutorgado() {
        return estCivilOutorgado;
    }

    public void setEstCivilOutorgado(String estCivilOutorgado) {
        this.estCivilOutorgado = estCivilOutorgado;
    }

    public String getEstCivilRogo() {
        return estCivilRogo;
    }

    public void setEstCivilRogo(String estCivilRogo) {
        this.estCivilRogo = estCivilRogo;
    }
    
    public String getOutorgante() {
        return outorgante;
    }

    public void setOutorgante(String outorgante) {
        this.outorgante = outorgante.toUpperCase();
    }

    public String getEndOutorgante() {
        return endOutorgante;
    }

    public void setEndOutorgante(String endOutorgante) {
        this.endOutorgante = endOutorgante;
    }

    public String getOutorgado() {
        return outorgado;
    }

    public void setOutorgado(String outorgado) {
        this.outorgado = outorgado.toUpperCase();
    }

    public String getEndOutorgado() {
        return endOutorgado;
    }

    public void setEndOutorgado(String endOutorgado) {
        this.endOutorgado = endOutorgado;
    }

    public String getaRogo() {
        return aRogo;
    }

    public void setaRogo(String aRogo) {
        this.aRogo = aRogo;
    }

    public String getEndRogo() {
        return endRogo;
    }

    public void setEndRogo(String endRogo) {
        this.endRogo = endRogo;
    }

    public String getSelo() {
        return selo;
    }

    public void setSelo(String selo) {
        this.selo = selo;
    }

    public boolean isExisteRogo() {
        return existeRogo;
    }

    public void setExisteRogo(boolean existeRogo) {
        this.existeRogo = existeRogo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getModeloProc() {
        return modeloProc;
    }

    public void setModeloProc(String modeloProc) {
        this.modeloProc = modeloProc;
    }

    public String getRgOutorgante() {
        return rgOutorgante;
    }

    public void setRgOutorgante(String rgOutorgante) {
        this.rgOutorgante = rgOutorgante;
    }

    public String getCpfOutorgante() {
        return cpfOutorgante;
    }

    public void setCpfOutorgante(String cpfOutorgante) {
        this.cpfOutorgante = cpfOutorgante;
    }

    public String getProfOutorgante() {
        return profOutorgante;
    }

    public void setProfOutorgante(String profOutorgante) {
        this.profOutorgante = profOutorgante;
    }

    public int getCivOutorgante() {
        return civOutorgante;
    }

    public void setCivOutorgante(int estCivilOutorgante) {
        this.civOutorgante = estCivilOutorgante;
    }
    
    public GeraProcuracoes(){}
    
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
    //metodo que gera as procuraçoes baseado nos modelos utilizados no cartorio
    public void setGeraProcuracoes()
    {
        
//        if(tipoProcuracao == 0 && existeRogo == true)
//        {
//            //caminho do arquivo usado como modelo 
//            filePath = "C:\\Arquivos Gerador PDF Java\\MODELO PREV A ROGO.doc";
//
//            modeloProc="PREVIDENCIARIA A ROGO";
//        }
//        else if(tipoProcuracao == 0 && existeRogo == false)
//        {
//            filePath = "C:\\Arquivos Gerador PDF Java\\MODELO PREV.doc";
//            modeloProc="PREVIDENCIARIA";
//        }
//        else if(tipoProcuracao == 1 && existeRogo == true)
//        {
//            filePath = "C:\\Arquivos Gerador PDF Java\\MODELO GENERICA A ROGO.doc";
//            modeloProc="GENERICA A ROGO";
//        }
//        else if(tipoProcuracao == 1 && existeRogo == false)
//        {
//            filePath = "C:\\Arquivos Gerador PDF Java\\MODELO PREV.doc";
//            modeloProc="GENERICA";
//        }
//        else if(tipoProcuracao == 2 && existeRogo == true)
//        {
//            filePath = "C:\\Arquivos Gerador PDF Java\\MODELO JUR FINANC A ROGO.doc";
//            modeloProc="JUR FINANC A ROGO";
//        }
//        else if(tipoProcuracao == 2 && existeRogo == false)
//        {
//            filePath = "C:\\Arquivos Gerador PDF Java\\MODELO JUR FINANC.doc";
//            modeloProc="JUR FINANC";
//        }
        //define o estado do Civil do Outorgante
        
        switch (civOutorgante) 
        {
            case 0:
                estCivilOutorgante = "solteiro";
                break;
            case 1:
                estCivilOutorgante = "casado";
                break;
            case 2:
                estCivilOutorgante = "divorciado";                    
                break;
            case 3:
                estCivilOutorgante = "viúvo";                    
                break;
            default:
                break;
        }
        switch (civOutorgado) 
        {
            case 0:
                estCivilOutorgado = "solteiro";
                break;
            case 1:
                estCivilOutorgado = "casado";
                break;
            case 2:
                estCivilOutorgado = "divorciado";                    
                break;
            case 3:
                estCivilOutorgado = "viúvo";                    
                break;
            default:
                break;
        }     
        
        switch (indiceEscrevente) 
        {
            case 0:
                escrevente = "Jorge Augusto Rufino Ferreira";
                cargo = "Escrevente";
                break;
            case 1:
               escrevente = "Andreya Glaucya Guimarães de Sousa";
               cargo = "Escrevente";
                break;
            case 2:
                escrevente = "Alessandra Alvares Figueiredo";
                cargo = "Escrevente";
                break;
            case 3:
                escrevente = "Renato da Silva Guimarães";    
                cargo = "Escrevente";
                break;
            case 4:
            escrevente = "Mawell Ramos Figueiredo";    
            cargo = "Tabelião titular";
                break;
            default:
                break;
        }
        //filePath = "C:\\Arquivos Gerador PDF Java\\MODELO GERAL.doc";
        filePath2 = "C:\\Arquivos Gerador PDF Java\\" + outorgante + " para " + outorgado + "."+modeloProc+".doc";
        POIFSFileSystem fs = null;        
        try {               
            fs = new POIFSFileSystem(new FileInputStream(filePath));            
            HWPFDocument doc = new HWPFDocument(fs);
            
            doc = replaceText(doc, "#DATA_EXTENSO", dataTodaPorExtenso(data));
            doc = replaceText(doc, "#OUTORGANTE", outorgante);
            doc = replaceText(doc, "#CIVIL_OUTORGANTE", estCivilOutorgante);           
            doc = replaceText(doc, "#PROF_OUTORGANTE", profOutorgante);           
            doc = replaceText(doc, "#RG_OUTORGANTE", rgOutorgante);            
            doc = replaceText(doc, "#CPF_OUTORGANTE", cpfOutorgante);            
            doc = replaceText(doc, "#END_OUTORGANTE", endOutorgante);  
            
            doc = replaceText(doc, "#OUTORGADO", outorgado);
            doc = replaceText(doc, "#PROF_OUTORGADO", profOutorgado);
            doc = replaceText(doc, "#CIVIL_OUTORGADO", estCivilOutorgado);
            doc = replaceText(doc, "#RG_OUTORGADO", rgOutorgado);
            doc = replaceText(doc, "#CPF_OUTORGADO", cpfOutorgado);
            doc = replaceText(doc, "#END_OUTORGADO", endOutorgado);
            doc = replaceText(doc, "#ESCREVENTE", escrevente);
            doc = replaceText(doc, "#CARGO", cargo);
            
            //se algum dos campos de A Rogo estivem preenchidos executa este "if"
            if (existeRogo)
            {
                //JOptionPane.showMessageDialog(null, "TESTESTES");
                switch (civRogo) 
                {
                    case 0:
                        estCivilRogo = "solteiro";
                        break;
                    case 1:
                        estCivilRogo = "casado";
                        break;
                    case 2:
                        estCivilRogo = "divorciado";                    
                        break;
                    case 3:
                        estCivilRogo = "viúvo";                    
                        break;
                    default:
                        break;
                }
                doc = replaceText(doc, "#A_ROGO", iniciaisMaisculas(aRogo.toLowerCase()));
                doc = replaceText(doc, "#PROF_ROGO", profRogo);
                doc = replaceText(doc, "#CIVIL_ROGO", estCivilRogo);
                doc = replaceText(doc, "#RG_ROGO", rgRogo);
                doc = replaceText(doc, "#CPF_ROGO", cpfRogo);
                doc = replaceText(doc, "#END_ROGO", endRogo);                
                doc = replaceText(doc, "#a_rogo", iniciaisMaisculas(aRogo.toLowerCase()));
            }
            doc = replaceText(doc, "#SELO", selo);
            doc = replaceText(doc, "#outorgante", iniciaisMaisculas(outorgante.toLowerCase()));
            doc = replaceText(doc, "#DATA", dataPorExtenso(data));
            saveWord(filePath2, doc);
            Desktop.getDesktop().open(new File(filePath2));
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText){
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

    private static void saveWord(String filePath, HWPFDocument doc) throws FileNotFoundException, IOException{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(filePath);
            doc.write(out);
        }
        finally{
            out.close();
        }
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
}