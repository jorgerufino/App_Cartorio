
package Classes;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

public class GeneratorPDF 
{
    
    String num_protocolo,nome_requerente,telefone, servico,servicoTabela, data_entrega, data_atual, ano_atual;
    String diligencia, campoDiligencia, valorDiligencia2, qteDiligencia;
    double valorProcuracao, valorServico, valorAutenticacoes, valorDiligencia;
    int num_servico, qteAutenticacao;
    
    public GeneratorPDF(String protocolo, String requerente, String telefone, int autenticacoes, int tipoProcuracao, double diligencia, String entrega)
    {
        num_protocolo = protocolo;
        nome_requerente = requerente;
        this.telefone = telefone;
        qteAutenticacao = autenticacoes;
        num_servico = tipoProcuracao;
        valorDiligencia = diligencia; 
        data_entrega = entrega;
    }

    public void setGerarPdf() 
    {   
        // criação do documento, tamanho A4
        Document document = new Document(PageSize.A4);         
        
        //cria um objeto NumberFormat para arrendontar os valores para moeda Real (ele transforma o resultado em uma String)
        NumberFormat arredondar = NumberFormat.getCurrencyInstance();
        
        try  
       {         
            //Pega a data atual e a deixa por extenso
            Date data =  new Date();
            Locale local = new Locale("pt","BR");
            DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",local);
            data_atual = ""+formato.format(data);
            
            formato = new SimpleDateFormat("yyyy",local);
            ano_atual = ""+formato.format(data);            
            
            if (valorDiligencia != 0)
            {
                campoDiligencia = "Diligência";                
                valorDiligencia2 = ""+arredondar.format(valorDiligencia);
                qteDiligencia = "1";
            }
            else
            {
                campoDiligencia = "******************";
                valorDiligencia2 = "***";
                qteDiligencia = "***";
            }
            
            switch (num_servico) 
            {
                case 0:
                    servico = "PROCURAÇÃO PREVIDENCIÁRIA";
                    servicoTabela = "Procuração Previdenciária";
                    valorProcuracao = 45.65;
                    valorAutenticacoes = qteAutenticacao * 5.85;
                    valorServico = valorAutenticacoes +  valorProcuracao + valorDiligencia;
                    break;
                case 1:
                    servico = "PROCURAÇÃO GENÉRICA";
                    servicoTabela = "Procuração Genérica";
                    valorProcuracao = 109.95;
                    valorAutenticacoes = qteAutenticacao * 5.85;
                    valorServico = valorAutenticacoes +  valorProcuracao + valorDiligencia;
                    break;
                case 2:
                    servico = "PROCURAÇÃO JURÍDICA COM CONTEÚDO FINANCEIRO";
                    servicoTabela = "Procuração Jur. c/ Cont. Financeiro";
                    valorProcuracao = 199.85;
                    valorAutenticacoes = qteAutenticacao * 5.85;
                    valorServico = valorAutenticacoes +  valorProcuracao + valorDiligencia;
                    
                    break;
                default:
                    break;
            }
            
            //Escreve o valor do serviço por extenso
            EscreverPorExtensoValor obj = new EscreverPorExtensoValor(); 
            String valorServicoExtenso = obj.write(new BigDecimal(valorServico));
            
            String endereco_PDF = "D:\\Michele Andrade\\Desktop\\PROCURAÇÃO PÚBLICA\\PROCURACAO PUBLICA 2017\\PROTOCOLO\\" + num_protocolo + "." + ano_atual + "." + nome_requerente + ".pdf";
            //String endereco_PDF = "C:\\Arquivos Gerador PDF Java\\" + num_protocolo + "." + ano_atual + "." + nome_requerente + ".pdf";
         
            PdfWriter.getInstance(document, new FileOutputStream(endereco_PDF));
            document.open();
            
            //Inserindo Brasão
            Image img = Image.getInstance("C:\\Arquivos Gerador PDF Java\\Brasao.png");
            img.setAlignment(Element.ALIGN_CENTER);
            img.setCompressionLevel(10);
            img.scaleToFit(60, 60);
            document.add(img);
            
            // definindo formatacao das fontes
            Font com_negrito08 = new Font(Font.FontFamily.TIMES_ROMAN, 10 ,Font.BOLD);
            Font com_negrito_italico08 = new Font(Font.FontFamily.TIMES_ROMAN, 10 ,Font.BOLDITALIC);
            Font sem_negrito08 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
            Font com_negrito10 = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
            Font sem_negrito10 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font com_negrito12 = new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.BOLD);
            Font sem_negrito12 = new Font(Font.FontFamily.TIMES_ROMAN, 14);
            
            //gerando os paragrafos
            Paragraph p1 = new Paragraph("REPÚBLICA FEDERATIVA DO BRASIL\n\nCartório do Único Ofício de Benevides", com_negrito08);            
            Paragraph p2 = new Paragraph("Av. Joaquim Pereira de Queiroz, nº 1065 - Centro - Benevides - Pará - Tel: (091) 3724 – 4711.", sem_negrito08);
            Paragraph p3 = new Paragraph("Maxwell Ramos Figueiredo - Oficial Titular", com_negrito08);
            Paragraph p4 = new Paragraph("Alessandra Alvares Figueiredo – Substituta\nAndreya Glaucya Guimarães de Sousa - Escrevente\nJorge Augusto Rufino Ferreira - Escrevente", sem_negrito08);
            
            Paragraph pLinha1     = new Paragraph("____________________________________________________________________________________", sem_negrito08);            
            Paragraph pProtocolo  = new Paragraph("PROTOCOLO: "+num_protocolo, sem_negrito12);            
            Paragraph pRequerente = new Paragraph("Requerente: "+nome_requerente, sem_negrito10);
            Paragraph pTelefone   = new Paragraph("Telefone: "+telefone, sem_negrito10);
            Paragraph pAtendente  = new Paragraph("Atendente: JORGE RUFINO", sem_negrito10);
            Paragraph pServico    = new Paragraph("Serviço: "+servico, sem_negrito10);
            Paragraph pValor      = new Paragraph("Valor: "+arredondar.format(valorServico)+" ("+valorServicoExtenso+")", com_negrito10);
            Paragraph pPrevisao   = new Paragraph("Previsão de Entrega: "+data_entrega, sem_negrito10);
            Paragraph pOrcamento  = new Paragraph("Orçamento", com_negrito12);
            
            Paragraph pObservacao  = new Paragraph("\nOBSERVAÇÃO:\n", sem_negrito08);            
            Paragraph pObservacaoTexto  = new Paragraph("I.  É indispensável a apresentação deste protocolo e documento de identificação para receber a procuração;\n"
                    + "II. O prazo de entrega da procuração é de 24H;", sem_negrito08);
            
            Paragraph pLocalData  = new Paragraph("Benevides/PA, "+data_atual, sem_negrito10);
            Paragraph pLinha3     = new Paragraph("____________________________________________________________", sem_negrito08);
            
            Paragraph pJorge     = new Paragraph("Jorge Augusto Rufino Ferreira", com_negrito_italico08);
            Paragraph pProtesto  = new Paragraph("Setor de Notas e Protesto", sem_negrito08);
            Paragraph pRodaPe    = new Paragraph("Avenida Joaquim Pereira de Queiroz, nº 1.065, Bairro: Centro, Benevides - Pará, CEP: 68795-000."
                    + "\nFone: (0**91)3724-4711. E-mail: cartoriodebenevides@gmail.com", sem_negrito08);
            
            //move o paragrafo na horizontal
            pProtocolo.setIndentationLeft(55);
            pRequerente.setIndentationLeft(55);
            pTelefone.setIndentationLeft(55);
            pAtendente.setIndentationLeft(55);
            pServico.setIndentationLeft(55);
            pValor.setIndentationLeft(55);
            pPrevisao.setIndentationLeft(55);
            pObservacao.setIndentationLeft(55);
            pObservacaoTexto.setIndentationLeft(105);
            
                                    
            //definindo alinhamento e espaçamento após
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(5);                   
            
            p2.setAlignment(Element.ALIGN_CENTER);
            p3.setAlignment(Element.ALIGN_CENTER);
            p4.setAlignment(Element.ALIGN_CENTER);
            pLinha1.setAlignment(Element.ALIGN_CENTER);            
            pLinha1.setSpacingAfter(5); 
            
            pOrcamento.setAlignment(Element.ALIGN_CENTER);
            pOrcamento.setSpacingAfter(5);
            
            pObservacaoTexto.setSpacingAfter(30);            
            pLocalData.setAlignment(Element.ALIGN_CENTER);            
            pLocalData.setAlignment(Element.ALIGN_CENTER);            
            pLocalData.setSpacingAfter(15);
            pLinha3.setAlignment(Element.ALIGN_CENTER);
            
            pJorge.setAlignment(Element.ALIGN_CENTER);
            pProtesto.setAlignment(Element.ALIGN_CENTER);
            pProtesto.setSpacingAfter(20);
            pRodaPe.setAlignment(Element.ALIGN_CENTER);
            
            //gerando tabela
            //PdfPTable table = new PdfPTable(4,3); //Gera uma tabela (4x3)
            //Gera um tabela onde cada campo corresponde a uma porcentagem de 100%(Ex. 1º coluna 40%,2ª coluna 30%,3ª coluna10% e 4ª coluna 20%)
            PdfPTable table = new PdfPTable(new float[] {0.4f,0.3f,0.1f,0.2f});
            
            //criando as celulas uma por uma
            PdfPCell header1 = new PdfPCell(new Paragraph("SERVIÇO", com_negrito12));
            PdfPCell header2 = new PdfPCell(new Paragraph("VLR. UNITÁRIO", com_negrito12));
            PdfPCell header3 = new PdfPCell(new Paragraph("QTD.", com_negrito12));
            PdfPCell header4 = new PdfPCell(new Paragraph("TOTAL", com_negrito12));
            
            PdfPCell cServico = new PdfPCell(new Paragraph(servicoTabela, sem_negrito10));
            PdfPCell cValorProc = new PdfPCell(new Paragraph(""+arredondar.format(valorProcuracao), sem_negrito10));
            PdfPCell cQuantidade = new PdfPCell(new Paragraph("1", sem_negrito10));
            PdfPCell cValorTotalProc = new PdfPCell(new Paragraph(""+arredondar.format(valorProcuracao), sem_negrito10));
            
            PdfPCell cServico1 = new PdfPCell(new Paragraph("Autenticações", sem_negrito10));
            PdfPCell cValorProc1 = new PdfPCell(new Paragraph("R$ 5,85", sem_negrito10));
            PdfPCell cQuantidade1 = new PdfPCell(new Paragraph(""+qteAutenticacao, sem_negrito10));
            PdfPCell cValorTotalProc1 = new PdfPCell(new Paragraph(""+arredondar.format(valorAutenticacoes), sem_negrito10));
            
            PdfPCell cServico2 = new PdfPCell(new Paragraph(campoDiligencia, sem_negrito10));
            PdfPCell cValorProc2 = new PdfPCell(new Paragraph(valorDiligencia2, sem_negrito10));
            PdfPCell cQuantidade2 = new PdfPCell(new Paragraph(qteDiligencia, sem_negrito10));
            PdfPCell cValorTotalProc2 = new PdfPCell(new Paragraph(valorDiligencia2, sem_negrito10));
            
            //Criando nova tabela de 1 linha para fazer o Total
            PdfPTable table2 = new PdfPTable(1);            
            PdfPCell cTotal = new PdfPCell(new Paragraph("TOTAL: "+ arredondar.format(valorServico), com_negrito10));
                     
            //centralizando as celulas
            header1.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            header2.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            header3.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            header4.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cServico.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cValorProc.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cQuantidade.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cValorTotalProc.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cServico1.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cValorProc1.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cQuantidade1.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cValorTotalProc1.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cServico2.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cValorProc2.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cQuantidade2.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            cValorTotalProc2.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            
            cTotal.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            
            //adicionando as celulas na primeira tabela
            table.addCell(header1);    
            table.addCell(header2);    
            table.addCell(header3);    
            table.addCell(header4);    
            table.addCell(cServico);
            table.addCell(cValorProc);
            table.addCell(cQuantidade);
            table.addCell(cValorTotalProc);
            table.addCell(cServico1);
            table.addCell(cValorProc1);
            table.addCell(cQuantidade1);
            table.addCell(cValorTotalProc1);
            table.addCell(cServico2);
            table.addCell(cValorProc2);
            table.addCell(cQuantidade2);
            table.addCell(cValorTotalProc2);
            
            //adicionando celula a segunda tabela (Tabela que contem o Total)
            table2.addCell(cTotal); 
               
            //Adiciona um retangulo(borda) ao documento
            //                            (esq,baixo,dir,cima)
            Rectangle rect = new Rectangle(15,  15,  580, 827);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(1);            
            
            //adicionando os paragrafos e as tabelas ao pdf
            document.add(p1);            
            document.add(p2);
            document.add(p3);
            document.add(p4);
            document.add(pLinha1);
            document.add(pProtocolo);
            document.add(pRequerente);
            document.add(pTelefone);
            document.add(pAtendente);
            document.add(pServico);
            document.add(pValor);
            document.add(pPrevisao);
            document.add(pLinha1);
            document.add(pOrcamento);
            document.add(table);            
            document.add(table2);
            document.add(pObservacao);
            document.add(pObservacaoTexto);
            document.add(pLocalData);
            document.add(pLinha3);
            document.add(pJorge);
            document.add(pProtesto);
            document.add(pLinha1);
            document.add(pRodaPe);
            document.add(rect);
            
            JOptionPane.showMessageDialog(null, "Protocolo Cadastrado com sucesso!");
            
            Desktop.getDesktop().open(new File(endereco_PDF));
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
      }   
}

