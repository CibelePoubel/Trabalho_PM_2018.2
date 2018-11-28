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
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {      	// Inicia a execuação do programa para a passagem de argumentos
        	
    	Saida txtSaida = null;  		// Cria um objeto da classe Saida para posterior escrita da saída do programa
    	Log txtLog = null;  		// Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	ArrayList<XML> listaDeCurriculos = new ArrayList<XML>();  			// Lista que guarda cada um dos arquivos XML contendo os currículos
    	int parametroAtual;  		// Guarda o parâmetro que está sendo atualmente lido
    	int auxCurriculos = 0;  	// Variável auxiliar para percorrer a lista de currículos para que seja escrito no arquivo de saída a pontuação por semestres sem reprovação
    	boolean modoCompleto = false, modoVerboso = false, modoPremios = false, modoQualis = false, modoQualisRestritos = false, modoEventos = false, modoVinculoUnirio = false;   		// Guardam quais parâmetros foram passados pelo usuário para posterior call dos métodos da classe XML para cálculo da pontuação e exibição restrita aos modos requisitados
    	
    	
    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  		  // FOR para verificar cada um dos parâmetros passados pelo usuário
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o"))   			 // Verifica se o parâmetro é igual a -o (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de saída           
            	txtSaida = new Saida(args[parametroAtual + 1]);  		 // Cria um objeto da classe Saida para posterior escrita da saída do programa          
                  
            
            if (args[parametroAtual].equalsIgnoreCase("-l"))    		// Verifica se o parâmetro é igual a -l (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de log      	
            	txtLog = new Log(args[parametroAtual + 1]);      	
                            	
            
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  		    // Verifica se o parâmetro é gual a -a (ignorando se este for digitado em maiúsculo ou minúsculo. Caso seja, a próxima posição dos argumentos indica o caminho do currículo em XML           
            	XML arquivoXML = new XML(args[parametroAtual + 1], Integer.parseInt(args[parametroAtual + 2])); 		 // Cria um objeto da classe XML para posterior escrita do log de erros do programa
            	listaDeCurriculos.add(arquivoXML);
            }
         
            
            if(args[parametroAtual].equalsIgnoreCase("-pr"))           	
            	modoPremios = true;
            	
            
            if(args[parametroAtual].equalsIgnoreCase("-v"))
            	modoVerboso = true;
            	
            
            if(args[parametroAtual].equalsIgnoreCase("-ar"))
            	modoQualisRestritos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-anr"))
            	modoQualis = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-pe"))
            	modoEventos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-vi"))
            	modoVinculoUnirio = true;
              	            
        }
    	
    	
    	
    	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "\t Resultado do arquivo de saída:");  		  // Escrita inicial no arquivo de saída    
    	txtLog.escreveArquivo(txtLog.getCaminhoDoArquivo(), "\t Erros encontrados na execução do programa:");  		   // Escrita inicial no arquivo de log 
    	
    	
    	
    	if(modoCompleto == true) {			// Caso o modo completo seja pedido pelo usuário, executa todas as saídas
    		
    		for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
        		listaDeCurriculos.get(auxCurriculos).calculaPontuacaoSemestres();		// Calcula a pontuação por semestre sem reprovação do currículo atualmente lido
    			listaDeCurriculos.get(auxCurriculos).calculaPontuacaoPremios(txtSaida, modoVerboso);       // Calcula a pontuação por prêmios currículo atualmente lido
    		
    	}
    	
    	else {			// Caso o modo completo não seja pedido pelo usuário, executa somente as saídas requisitadas
    		
    		for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
    			listaDeCurriculos.get(auxCurriculos).calculaPontuacaoSemestres(); 		  // Calcula a pontuação por semestre sem reprovação do currículo atualmente lido
    	
    		if(modoPremios == true) 
    			for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
    				listaDeCurriculos.get(auxCurriculos).calculaPontuacaoPremios(txtSaida, modoVerboso);       	               	
        		
    	}
    	
    	
    	for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 		// Escreve no arquivo de saída a pontuação de cada candidato em ordem crescente
    		txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "PONTUAÇÃO TOTAL DO CURRÍCULO " + auxCurriculos + ": " + listaDeCurriculos.get(auxCurriculos).getPontuacaoCurriculo());   
    	
    	
    	
    	ArrayList<Integer> pontuacaoCurriculosOrdenada = new ArrayList<Integer>();			// Cria uma nova lista com a pontuação de cada candidato, onde será posteriormente ordenada para exibição
    	for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
    		pontuacaoCurriculosOrdenada.add(listaDeCurriculos.get(auxCurriculos).getPontuacaoCurriculo());		  // Adiciona as pontuações para a nova lista
    	
        			
        for (int i = 0; i < pontuacaoCurriculosOrdenada.size() -1 ; i++) 			// Algoritmo Bubble Sort para ordenar a lista que guarda a pontuação dos candidatos
            for (int j = 0; j < pontuacaoCurriculosOrdenada.size() -i -1; j++) 
                if (pontuacaoCurriculosOrdenada.get(j) < pontuacaoCurriculosOrdenada.get(j + 1)) {                 
                    int temp = pontuacaoCurriculosOrdenada.get(j); 
                    pontuacaoCurriculosOrdenada.set(j, pontuacaoCurriculosOrdenada.get(j + 1));
                    pontuacaoCurriculosOrdenada.set(j + 1, temp); 
                } 
        
         
        txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "PONTUAÇÃO TOTAL DOS CURRÍCULOS ORDENADA:");
        for(auxCurriculos = 0; auxCurriculos < pontuacaoCurriculosOrdenada.size(); auxCurriculos++) 		// Escreve no arquivo de saída a pontuação de cada candidato em ordem crescente
    		txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), Integer.toString(pontuacaoCurriculosOrdenada.get(auxCurriculos)));   
    	
    }
    
}
    
