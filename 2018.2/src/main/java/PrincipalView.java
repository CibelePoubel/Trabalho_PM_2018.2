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

public class PrincipalView {
 
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {      	// Inicia a execuação do programa para a passagem de argumentos
        	
    	int parametroAtual;  		// Guarda o parâmetro que está sendo atualmente lido
    	boolean modoCompleto = false, modoVerboso = false, modoPremios = false, modoArtigos = false, modoEventos = false, modoVinculoUnirio = false;   		// Guardam quais parâmetros foram passados pelo usuário para posterior call dos métodos da classe XML para cálculo da pontuação e exibição restrita aos modos requisitados
    	   	
    	SaidaModel txtSaida = null;  		// Cria um objeto da classe Saida para posterior escrita da saída do programa
    	LogModel txtLog = null;  		    // Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	
    	ArrayList<CandidatoModel> listaDeCandidatos = new ArrayList<CandidatoModel>();  			// Lista que guarda cada um dos arquivos XML contendo os currículos
    	

    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  		  // FOR para verificar cada um dos parâmetros passados pelo usuário
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o"))   			 // Verifica se o parâmetro é igual a -o (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de saída           
            	txtSaida = new SaidaModel(args[parametroAtual + 1]);  		 // Cria um objeto da classe Saida para posterior escrita da saída do programa          
                  
            
            if (args[parametroAtual].equalsIgnoreCase("-l"))    		// Verifica se o parâmetro é igual a -l (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de log      	
            	txtLog = new LogModel(args[parametroAtual + 1]);      	
                            	
            
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  		    // Verifica se o parâmetro é gual a -a (ignorando se este for digitado em maiúsculo ou minúsculo. Caso seja, a próxima posição dos argumentos indica o caminho do currículo em XML           
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
		
		txtSaida.escreveArquivo("\t\t\t Resultado do arquivo de saída:");  		  // Escrita inicial no arquivo de saída    
    	txtLog.escreveArquivo("\t\t\t Erros encontrados na execução do programa:");  		   // Escrita inicial no arquivo de log 
    	
    	CalculadorController calculadorPontuacao = new CalculadorController(txtSaida, txtLog, listaDeCandidatos, modoCompleto, modoVerboso, modoPremios, modoArtigos, modoEventos, modoVinculoUnirio); 
    	
    	
    	calculadorPontuacao.calculaPontuacaoSemestres();			// Calcula a pontuação por semestre sem reprovação dos currículos
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("Pontuação por semestres sem reprovação do candidato " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoSemestresSemReprovacao()));
    	
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
    	
    	if(modoCompleto == true) 			// Caso o modo completo seja pedido pelo usuário, executa todas as saídas
    		calculadorPontuacao.calculaModoCompleto(modoVerboso); 
    	
    	
    	else {			  // Caso o modo completo não seja pedido pelo usuário, executa somente as saídas requisitadas 		
    		
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
			txtSaida.escreveArquivo("\t\t\t\t Pontuação total de " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));
		
	}
	
	
	
	
	
	private static void imprimeRankingFinal(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {	
    	
		txtSaida.escreveArquivo("***************************************************************************************************************");
		txtSaida.escreveArquivo("\t\t\t\t\t RANKING FINAL DOS CANDIDATOS:");
    	
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) 
			txtSaida.escreveArquivo("\t\t\t\t\t" + listaDeCandidatos.get(auxCandidatos).getNomeCandidato() + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));
	
	}
	
	
}