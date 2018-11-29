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

public class PrincipalView {
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {      	// Inicia a execua��o do programa para a passagem de argumentos
        	
    	int parametroAtual;  		// Guarda o par�metro que est� sendo atualmente lido
    	boolean modoCompleto = false, modoVerboso = false, modoPremios = false, modoArtigos = false, modoEventos = false, modoVinculoUnirio = false;   		// Guardam quais par�metros foram passados pelo usu�rio para posterior call dos m�todos da classe XML para c�lculo da pontua��o e exibi��o restrita aos modos requisitados
    	   	
    	SaidaModel txtSaida = null;  		// Cria um objeto da classe Saida para posterior escrita da sa�da do programa
    	LogModel txtLog = null;  		    // Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	
    	ArrayList<CandidatoModel> listaDeCandidatos = new ArrayList<CandidatoModel>();  			// Lista que guarda cada um dos arquivos XML contendo os curr�culos
    	

    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  		  // FOR para verificar cada um dos par�metros passados pelo usu�rio
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o"))   			 // Verifica se o par�metro � igual a -o (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de sa�da           
            	txtSaida = new SaidaModel(args[parametroAtual + 1]);  		 // Cria um objeto da classe Saida para posterior escrita da sa�da do programa          
                  
            
            if (args[parametroAtual].equalsIgnoreCase("-l"))    		// Verifica se o par�metro � igual a -l (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de log      	
            	txtLog = new LogModel(args[parametroAtual + 1]);      	
                            	
            
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  		    // Verifica se o par�metro � gual a -a (ignorando se este for digitado em mai�sculo ou min�sculo. Caso seja, a pr�xima posi��o dos argumentos indica o caminho do curr�culo em XML           
            	CandidatoModel candidato = new CandidatoModel(args[parametroAtual + 1], Integer.parseInt(args[parametroAtual + 2])); 		 // Cria um objeto da classe XML para posterior escrita do log de erros do programa
            	listaDeCandidatos.add(candidato);
            }
         
            
            if(args[parametroAtual].equalsIgnoreCase("-pr"))           	
            	modoPremios = true;
            	
            
            if(args[parametroAtual].equalsIgnoreCase("-v"))
            	modoVerboso = true;
            	
            
            if(args[parametroAtual].equalsIgnoreCase("-ar"))
            	modoArtigos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-anr"))
            	modoArtigos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-pe"))
            	modoEventos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-vi"))
            	modoVinculoUnirio = true;
            
                           
        }
    	
    	analisaSaidasSolicitadas(txtSaida, txtLog, listaDeCandidatos, modoCompleto, modoVerboso, modoPremios, modoArtigos, modoEventos, modoVinculoUnirio);
   	
    }
    


	private static void analisaSaidasSolicitadas(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos , boolean modoCompleto, boolean modoVerboso, boolean modoPremios, boolean modoArtigos, boolean modoEventos, boolean modoVinculoUnirio) throws IOException {
		
		txtSaida.escreveArquivo("\t\t\t Resultado do arquivo de sa�da:");  		  // Escrita inicial no arquivo de sa�da    
    	txtLog.escreveArquivo("\t\t\t Erros encontrados na execu��o do programa:");  		   // Escrita inicial no arquivo de log 
    	
    	CalculadorController calculadorPontuacao = new CalculadorController(txtSaida, txtLog, listaDeCandidatos, modoCompleto, modoVerboso, modoPremios, modoArtigos, modoEventos, modoVinculoUnirio); 
    	
    	
    	calculadorPontuacao.calculaPontuacaoSemestres();			// Calcula a pontua��o por semestre sem reprova��o dos curr�culos
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("Pontua��o por semestres sem reprova��o do candidato " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoSemestresSemReprovacao()));
    	
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
    	
    	if(modoCompleto == true) 			// Caso o modo completo seja pedido pelo usu�rio, executa todas as sa�das
    		calculadorPontuacao.calculaModoCompleto(modoVerboso); 
    	
    	
    	else {			  // Caso o modo completo n�o seja pedido pelo usu�rio, executa somente as sa�das requisitadas 		
    		
    		if(modoPremios == true)
    			calculadorPontuacao.calculaModoPremios(modoVerboso);  				
    		
    		if(modoArtigos == true)
    			calculadorPontuacao.calculaModoArtigos(modoVerboso);
    		
    		if(modoEventos == true)
    			calculadorPontuacao.calculaModoEventos(modoVerboso);  		
    	}
    		
    	
    	imprimePontuacaoGeral(txtSaida,txtLog, listaDeCandidatos);
    	
    	calculadorPontuacao.calculaRankingFinal();
    	
    	imprimeRankingFinal(txtSaida,txtLog, listaDeCandidatos);

    	
	}
	
	
	
	
	
	private static void imprimePontuacaoGeral(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {
		
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("\t\t\t\t Pontua��o total de " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));
		
	}
	
	
	
	
	
	private static void imprimeRankingFinal(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {	
    	
		txtSaida.escreveArquivo("***************************************************************************************************************");
		txtSaida.escreveArquivo("\t\t\t\t\t RANKING FINAL DOS CANDIDATOS:");
    	
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) 
			txtSaida.escreveArquivo("\t\t\t\t\t" + listaDeCandidatos.get(auxCandidatos).getNomeCandidato() + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));
	
	}
	
	
}