/**
 * 
 * @author Cibele
 * 
 * Este programa tem como objetivo pontuar os curr�culos lattes recebidos do site lattes.cnpq.br e gerar um ranking final.
 * 
 * Nesta classe Principal,ser�o recebidos os par�metros que o usu�rio digitar, de acordo com a sa�da desejada.
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Principal {
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {  // Inicia a execua��o do programa para a passagem de argumentos
        	
    	Saida txtSaida = new Saida();  // Cria um objeto da classe Saida para posterior escrita da sa�da do programa
    	Log txtLog = new Log();  // Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	ArrayList<XML> listaDeCurriculos = new ArrayList<XML>();
    	String caminhoArquivo = null, caminhoLog = null;  // Strings que guardam o caminho especificado pelo usu�rio para salvamento dos arquivos
    	int contadorCurriculos = 0;  // Guarda quantos curr�culos ja foram analisados para identifica��o
    	int parametroAtual, auxCurriculos = 0;  
    	
    	
    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  // FOR para verificar cada um dos par�metros passados pelo usu�rio
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o")) {   // Verifica se o par�metro � igual a -o (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de sa�da           
            	caminhoArquivo = args[parametroAtual + 1];
            	txtSaida.escreveArquivo(caminhoArquivo, "\t Resultado do arquivo de sa�da:");  // Escreve no arquivo de sa�da                 
            }
            
            
            if (args[parametroAtual].equalsIgnoreCase("-l")) {   // Verifica se o par�metro � igual a -l (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de log      	
            	caminhoLog = args[parametroAtual + 1];
            	txtLog.escreveArquivo(args[parametroAtual + 1], "Erros encontrados na execu��o do programa:");  // Escreve no arquivo de log         
            }	
            
            	
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  // Verifica se o par�metro � gual a -a (ignorando se este for digitado em mai�sculo ou min�sculo. Caso seja, a pr�xima posi��o dos argumentos indica o caminho do curr�culo em XML
            	XML arquivoXML = new XML(args[parametroAtual + 1], Integer.parseInt(args[parametroAtual + 2]));  // Cria um objeto da classe XML para posterior escrita do log de erros do programa
            	listaDeCurriculos.add(arquivoXML);
            	contadorCurriculos++;
            	
            	while(auxCurriculos < listaDeCurriculos.size()) {
					txtLog.escreveArquivo(caminhoArquivo, "Pontua��o por semestres sem reprova��o do curr�culo " +contadorCurriculos+": " +Integer.toString(listaDeCurriculos.get(auxCurriculos).calculaPontuacaoSemestres()));        	
					auxCurriculos++;
            	}
            	
            }
              	            
    	}
    	
    }
    
}
    




//private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
// + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
         