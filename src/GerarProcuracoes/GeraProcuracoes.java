package GerarProcuracoes;

import Classes.Metodos_Auxiliares;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class GeraProcuracoes 
{
    String outorgante, endOutorgante, outorgado, endOutorgado, aRogo, endRogo, selo,filePath, filePath2, modeloProc,rgOutorgante, rgOutorgado,rgRogo,
    cpfOutorgante, cpfOutorgado, cpfRogo, profOutorgante, profOutorgado, profRogo,estCivilOutorgante,estCivilOutorgado,estCivilRogo,data,escrevente, cargo;
    String nome_PJ, cnpj, nire, sede_PJ;
    int indiceEscrevente, sexoOutorgante, sexoOutorgado, sexoRogo;
    boolean existeRogo, existePJ;
    Metodos_Auxiliares obj_auxiliar = new Metodos_Auxiliares();
    
    public void setNome_PJ(String nome_PJ) {
        this.nome_PJ = nome_PJ.toUpperCase();
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNire(String nire) {
        this.nire = nire;
    }

    public void setSede_PJ(String sede_PJ) {
        this.sede_PJ = sede_PJ;
    }

    public void setExistePJ(boolean existePJ) {
        this.existePJ = existePJ;
    }
    public void setSexoOutorgante(int sexoOutorgante) {
        this.sexoOutorgante = sexoOutorgante;
    }

    public void setSexoOutorgado(int sexoOutorgado) {
        this.sexoOutorgado = sexoOutorgado;
    }

    public void setSexoRogo(int sexoRogo) {
        this.sexoRogo = sexoRogo;
    }
    
    
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
    
    public String getEstCivilOutorgante() {
        return estCivilOutorgante;
    }

    public void setEstCivilOutorgante(String estCivilOutorgante) {
        this.estCivilOutorgante = estCivilOutorgante.toLowerCase();
    }

    public String getEstCivilOutorgado() {
        return estCivilOutorgado;
    }

    public void setEstCivilOutorgado(String estCivilOutorgado) {
        this.estCivilOutorgado = estCivilOutorgado.toLowerCase();
    }

    public String getEstCivilRogo() {
        return estCivilRogo;
    }

    public void setEstCivilRogo(String estCivilRogo) {
        this.estCivilRogo = estCivilRogo.toLowerCase();
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

    public GeraProcuracoes(){}
    
    //metodo que gera as procuraçoes baseado nos modelos utilizados no cartorio
    public void setGeraProcuracoes()
    {    
        //obj_auxiliar.debug(modeloProc);
        //verifica quem é o Escrevente/Tabelião
        switch (indiceEscrevente) 
        {
            case 0:
                escrevente = "Andreya Glaucya Guimarães de Sousa";
                cargo = "Escrevente";
                break;
            case 1:               
               escrevente = "Jorge Augusto Rufino Ferreira";
               cargo = "Escrevente";
                break;
            case 2:
                escrevente = "Alessandra Alvares Figueiredo";
                cargo = "Tabeliã substituta";
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
            doc = obj_auxiliar.replaceText(doc, "#END_OUTORGANTE", obj_auxiliar.iniciaisMaisculas(endOutorgante));  
            
            //Sexo = 0 é masculino, senao....
            if(sexoOutorgante == 0)
            {
                doc = obj_auxiliar.replaceText(doc, "#a_o1", "o");  
                doc = obj_auxiliar.replaceText(doc, "#portador1", "portador");  
                doc = obj_auxiliar.replaceText(doc, "#ao", "ao");  
            }
            else
            {
                doc = obj_auxiliar.replaceText(doc, "#a_o1", "a");  
                doc = obj_auxiliar.replaceText(doc, "#portador1", "portadora");  
                doc = obj_auxiliar.replaceText(doc, "#ao", "a");
            }
             
            //substitue os campos do Outorgado
            doc = obj_auxiliar.replaceText(doc, "#OUTORGADO", outorgado);
            doc = obj_auxiliar.replaceText(doc, "#CIVIL_OUTORGADO", estCivilOutorgado); 
            doc = obj_auxiliar.replaceText(doc, "#PROF_OUTORGADO", profOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#RG_OUTORGADO", rgOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#CPF_OUTORGADO", cpfOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#END_OUTORGADO", endOutorgado);
            doc = obj_auxiliar.replaceText(doc, "#ESCREVENTE", escrevente);
            doc = obj_auxiliar.replaceText(doc, "#CARGO", cargo);
            
            if(sexoOutorgado == 0)
            {  
                doc = obj_auxiliar.replaceText(doc, "#OUT_MANDATARIO", "OUTORGADO MANDATÁRIO");
                doc = obj_auxiliar.replaceText(doc, "#a_o2", "o");  
                doc = obj_auxiliar.replaceText(doc, "#portador2", "portador");  
                doc = obj_auxiliar.replaceText(doc, "#seu", "seu");  
            }
            else
            {
                doc = obj_auxiliar.replaceText(doc, "#OUT_MANDATARIO", "OUTORGADA MANDATÁRIA");            
                doc = obj_auxiliar.replaceText(doc, "#a_o2", "a");  
                doc = obj_auxiliar.replaceText(doc, "#portador2", "portadora"); 
                doc = obj_auxiliar.replaceText(doc, "#seu", "sua");  
            }
            
            if (existeRogo)
            {
                doc = obj_auxiliar.replaceText(doc, "#A_ROGO", obj_auxiliar.iniciaisMaisculas(aRogo.toLowerCase()));
                doc = obj_auxiliar.replaceText(doc, "#CIVIL_ROGO", estCivilRogo);  
                doc = obj_auxiliar.replaceText(doc, "#PROF_ROGO", profRogo);
                doc = obj_auxiliar.replaceText(doc, "#RG_ROGO", rgRogo);
                doc = obj_auxiliar.replaceText(doc, "#CPF_ROGO", cpfRogo);
                doc = obj_auxiliar.replaceText(doc, "#END_ROGO", endRogo);                
                doc = obj_auxiliar.replaceText(doc, "#a_rogo", obj_auxiliar.iniciaisMaisculas(aRogo.toLowerCase()));
                
                if(sexoRogo == 0)
                {                        
                    doc = obj_auxiliar.replaceText(doc, "#a_o3", "o");  
                    doc = obj_auxiliar.replaceText(doc, "#portador3", "portador");  
                }
                else
                {
                    doc = obj_auxiliar.replaceText(doc, "#a_o3", "a");  
                    doc = obj_auxiliar.replaceText(doc, "#portador3", "portadora"); 
                }
            }
            
            if(existePJ)
            {
                doc = obj_auxiliar.replaceText(doc, "#NOME_PJ", nome_PJ);
                doc = obj_auxiliar.replaceText(doc, "#CNPJ", cnpj);
                doc = obj_auxiliar.replaceText(doc, "#NIRE", nire);
                doc = obj_auxiliar.replaceText(doc, "#END_PJ", sede_PJ);
            }
            doc = obj_auxiliar.replaceText(doc, "#SELO", selo);
            doc = obj_auxiliar.replaceText(doc, "#outorgante", obj_auxiliar.iniciaisMaisculas(outorgante.toLowerCase()));
            doc = obj_auxiliar.replaceText(doc, "#DATA", obj_auxiliar.dataPorExtenso(data));
            doc = obj_auxiliar.replaceText(doc, "#_data", data);
            
            //pega as 3 primeiras letras do modelo do arquivo para saber em qual pasta salvar a procuraçao
            String codProc = modeloProc.substring(0,3);
            //pasta default
            String pastaProc = "PROCURAÇOES GERADAS NO DIA";
            //verifica qual o código (as 3 primeiras letras) e muda a pasta default
            if(codProc.equals("PRV"))
            {
                pastaProc = "PROCURACAO PARA FINS PREVIDENCIARIOS";
            }
            if(codProc.equals("GEN"))
            {
                pastaProc = "PROCURACAO GENERICA";
            }
            if(codProc.equals("JUR"))
            {
                pastaProc = "PROCURACAO JURIDICA COM CONTEUDO FINANCEIRO";
            }
            
            filePath2 = "D:\\Michele Andrade\\Desktop\\PROCURAÇÃO PÚBLICA\\PROCURACAO PUBLICA 2017\\"+ pastaProc +"\\" + outorgante + " para " + outorgado + "."+modeloProc;
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