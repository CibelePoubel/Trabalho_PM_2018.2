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
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {      	// Inicia a execua��o do programa para a passagem de argumentos
        	
    	Saida txtSaida = null;  		// Cria um objeto da classe Saida para posterior escrita da sa�da do programa
    	Log txtLog = null;  		// Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	ArrayList<XML> listaDeCurriculos = new ArrayList<XML>();  			// Lista que guarda cada um dos arquivos XML contendo os curr�culos
    	int parametroAtual;  		// Guarda o par�metro que est� sendo atualmente lido
    	int auxCurriculos = 0;  	// Vari�vel auxiliar para percorrer a lista de curr�culos para que seja escrito no arquivo de sa�da a pontua��o por semestres sem reprova��o
    	boolean modoCompleto = false, modoVerboso = false, modoPremios = false, modoQualis = false, modoQualisRestritos = false, modoEventos = false, modoVinculoUnirio = false;   		// Guardam quais par�metros foram passados pelo usu�rio para posterior call dos m�todos da classe XML para c�lculo da pontua��o e exibi��o restrita aos modos requisitados
    	
    	
    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  		  // FOR para verificar cada um dos par�metros passados pelo usu�rio
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o"))   			 // Verifica se o par�metro � igual a -o (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de sa�da           
            	txtSaida = new Saida(args[parametroAtual + 1]);  		 // Cria um objeto da classe Saida para posterior escrita da sa�da do programa          
                  
            
            if (args[parametroAtual].equalsIgnoreCase("-l"))    		// Verifica se o par�metro � igual a -l (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de log      	
            	txtLog = new Log(args[parametroAtual + 1]);      	
                            	
            
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  		    // Verifica se o par�metro � gual a -a (ignorando se este for digitado em mai�sculo ou min�sculo. Caso seja, a pr�xima posi��o dos argumentos indica o caminho do curr�culo em XML           
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
    	
    	
    	
    	txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "\t Resultado do arquivo de sa�da:");  		  // Escrita inicial no arquivo de sa�da    
    	txtLog.escreveArquivo(txtLog.getCaminhoDoArquivo(), "\t Erros encontrados na execu��o do programa:");  		   // Escrita inicial no arquivo de log 
    	
    	
    	
    	if(modoCompleto == true) {			// Caso o modo completo seja pedido pelo usu�rio, executa todas as sa�das
    		
    		for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
        		listaDeCurriculos.get(auxCurriculos).calculaPontuacaoSemestres();		// Calcula a pontua��o por semestre sem reprova��o do curr�culo atualmente lido
    			listaDeCurriculos.get(auxCurriculos).calculaPontuacaoPremios(txtSaida, modoVerboso);       // Calcula a pontua��o por pr�mios curr�culo atualmente lido
    		
    	}
    	
    	else {			// Caso o modo completo n�o seja pedido pelo usu�rio, executa somente as sa�das requisitadas
    		
    		for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
    			listaDeCurriculos.get(auxCurriculos).calculaPontuacaoSemestres(); 		  // Calcula a pontua��o por semestre sem reprova��o do curr�culo atualmente lido
    	
    		if(modoPremios == true) 
    			for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
    				listaDeCurriculos.get(auxCurriculos).calculaPontuacaoPremios(txtSaida, modoVerboso);       	               	
        		
    	}
    	
    	
    	for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 		// Escreve no arquivo de sa�da a pontua��o de cada candidato em ordem crescente
    		txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "PONTUA��O TOTAL DO CURR�CULO " + auxCurriculos + ": " + listaDeCurriculos.get(auxCurriculos).getPontuacaoCurriculo());   
    	
    	
    	
    	ArrayList<Integer> pontuacaoCurriculosOrdenada = new ArrayList<Integer>();			// Cria uma nova lista com a pontua��o de cada candidato, onde ser� posteriormente ordenada para exibi��o
    	for(auxCurriculos = 0; auxCurriculos < listaDeCurriculos.size(); auxCurriculos++) 
    		pontuacaoCurriculosOrdenada.add(listaDeCurriculos.get(auxCurriculos).getPontuacaoCurriculo());		  // Adiciona as pontua��es para a nova lista
    	
        			
        for (int i = 0; i < pontuacaoCurriculosOrdenada.size() -1 ; i++) 			// Algoritmo Bubble Sort para ordenar a lista que guarda a pontua��o dos candidatos
            for (int j = 0; j < pontuacaoCurriculosOrdenada.size() -i -1; j++) 
                if (pontuacaoCurriculosOrdenada.get(j) < pontuacaoCurriculosOrdenada.get(j + 1)) {                 
                    int temp = pontuacaoCurriculosOrdenada.get(j); 
                    pontuacaoCurriculosOrdenada.set(j, pontuacaoCurriculosOrdenada.get(j + 1));
                    pontuacaoCurriculosOrdenada.set(j + 1, temp); 
                } 
        
         
        txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), "PONTUA��O TOTAL DOS CURR�CULOS ORDENADA:");
        for(auxCurriculos = 0; auxCurriculos < pontuacaoCurriculosOrdenada.size(); auxCurriculos++) 		// Escreve no arquivo de sa�da a pontua��o de cada candidato em ordem crescente
    		txtSaida.escreveArquivo(txtSaida.getCaminhoDoArquivo(), Integer.toString(pontuacaoCurriculosOrdenada.get(auxCurriculos)));   
    	
    }
    
}
    
