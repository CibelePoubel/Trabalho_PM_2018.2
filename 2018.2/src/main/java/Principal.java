/**
 * 
 * @author Cibele
 * 
 * Este programa tem como objetivo pontuar os currículos lattes recebidos do site lattes.cnpq.br e gerar um ranking final.
 * 
 * Nesta classe Principal,serão recebidos os parâmetros que o usuário digitar, de acordo com a saída desejada.
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
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {  // Inicia a execuação do programa para a passagem de argumentos
        	
    	Saida txtSaida = new Saida();  // Cria um objeto da classe Saida para posterior escrita da saída do programa
    	Log txtLog = new Log();  // Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	ArrayList<XML> listaDeCurriculos = new ArrayList<XML>();
    	String caminhoArquivo = null, caminhoLog = null;  // Strings que guardam o caminho especificado pelo usuário para salvamento dos arquivos
    	int contadorCurriculos = 0;  // Guarda quantos currículos ja foram analisados para identificação
    	int parametroAtual, auxCurriculos = 0;  
    	
    	
    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  // FOR para verificar cada um dos parâmetros passados pelo usuário
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o")) {   // Verifica se o parâmetro é igual a -o (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de saída           
            	caminhoArquivo = args[parametroAtual + 1];
            	txtSaida.escreveArquivo(caminhoArquivo, "\t Resultado do arquivo de saída:");  // Escreve no arquivo de saída                 
            }
            
            
            if (args[parametroAtual].equalsIgnoreCase("-l")) {   // Verifica se o parâmetro é igual a -l (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de log      	
            	caminhoLog = args[parametroAtual + 1];
            	txtLog.escreveArquivo(args[parametroAtual + 1], "Erros encontrados na execução do programa:");  // Escreve no arquivo de log         
            }	
            
            	
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  // Verifica se o parâmetro é gual a -a (ignorando se este for digitado em maiúsculo ou minúsculo. Caso seja, a próxima posição dos argumentos indica o caminho do currículo em XML
            	XML arquivoXML = new XML(args[parametroAtual + 1], Integer.parseInt(args[parametroAtual + 2]));  // Cria um objeto da classe XML para posterior escrita do log de erros do programa
            	listaDeCurriculos.add(arquivoXML);
            	contadorCurriculos++;
            	
            	while(auxCurriculos < listaDeCurriculos.size()) {
					txtLog.escreveArquivo(caminhoArquivo, "Pontuação por semestres sem reprovação do currículo " +contadorCurriculos+": " +Integer.toString(listaDeCurriculos.get(auxCurriculos).calculaPontuacaoSemestres()));        	
					auxCurriculos++;
            	}
            	
            }
              	            
    	}
    	
    }
    
}
    




//private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
// + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
         