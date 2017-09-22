/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerarProcuracoes;

import Testes.Metodos_Auxiliares;
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
    Metodos_Auxiliares obj_auxiliar = new Metodos_Auxiliares();
    
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
    
    //metodo que gera as procuraçoes baseado nos modelos utilizados no cartorio
    public void setGeraProcuracoes()
    {
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
        //verifica quem é o Escrevente/Tabelião
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
        POIFSFileSystem fs = null;        
        try {               
            fs = new POIFSFileSystem(new FileInputStream(filePath));            
            HWPFDocument doc = new HWPFDocument(fs);
            
            //substitui os campos com # com os valores recebidos
            doc = obj_auxiliar.replaceText(doc, "#DATA_EXTENSO", obj_auxiliar.dataTodaPorExtenso(data));
            doc = obj_auxiliar.replaceText(doc, "#OUTORGANTE", outorgante);
            doc = obj_auxiliar.replaceText(doc, "#CIVIL_OUTORGANTE", estCivilOutorgante);           
            doc = obj_auxiliar.replaceText(doc, "#PROF_OUTORGANTE", profOutorgante);           
            doc = obj_auxiliar.replaceText(doc, "#RG_OUTORGANTE", rgOutorgante);            
            doc = obj_auxiliar.replaceText(doc, "#CPF_OUTORGANTE", cpfOutorgante);            
            doc = obj_auxiliar.replaceText(doc, "#END_OUTORGANTE", endOutorgante);  
            
            doc = obj_auxiliar.replaceText(doc, "#OUTORGADO", outorgado);
            doc = obj_auxiliar.replaceText(doc, "#PROF_OUTORGADO", profOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#CIVIL_OUTORGADO", estCivilOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#RG_OUTORGADO", rgOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#CPF_OUTORGADO", cpfOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#END_OUTORGADO", endOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#ESCREVENTE", escrevente);
            doc = obj_auxiliar.replaceText(doc, "#CARGO", cargo);
            
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
                doc = obj_auxiliar.replaceText(doc, "#A_ROGO", obj_auxiliar.iniciaisMaisculas(aRogo.toLowerCase()));
                doc = obj_auxiliar.replaceText(doc, "#PROF_ROGO", profRogo);
                doc = obj_auxiliar.replaceText(doc, "#CIVIL_ROGO", estCivilRogo);
                doc = obj_auxiliar.replaceText(doc, "#RG_ROGO", rgRogo);
                doc = obj_auxiliar.replaceText(doc, "#CPF_ROGO", cpfRogo);
                doc = obj_auxiliar.replaceText(doc, "#END_ROGO", endRogo);                
                doc = obj_auxiliar.replaceText(doc, "#a_rogo", obj_auxiliar.iniciaisMaisculas(aRogo.toLowerCase()));
            }
            doc = obj_auxiliar.replaceText(doc, "#SELO", selo);
            doc = obj_auxiliar.replaceText(doc, "#outorgante", obj_auxiliar.iniciaisMaisculas(outorgante.toLowerCase()));
            doc = obj_auxiliar.replaceText(doc, "#DATA", obj_auxiliar.dataPorExtenso(data));
            
            //pega o arquivo modificado e cria um novo arquivo
            filePath2 = "D:\\Michele Andrade\\Desktop\\PROCURAÇÃO PÚBLICA\\PROCURACAO PUBLICA 2017\\PROCURAÇOES GERADAS NO DIA\\" + outorgante + " para " + outorgado + "."+modeloProc+".doc";
            obj_auxiliar.saveWord(filePath2, doc);
            //abre o novo arquivo gerado anteriormente
            Desktop.getDesktop().open(new File(filePath2));
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}