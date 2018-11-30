package View;


import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Model.CandidatoModel;
import Model.LogModel;
import Model.SaidaModel;
import Controller.CalculadorController;

public class PrincipalView {
 
    /** Método main do programa. Recebe os parâmetros digitados pelo usuário e seta as booleanas das saídas em true ou false para posterior chamada do método de análise das saídas

     *   @return void */   
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {      	// Inicia a execuação do programa para a passagem de argumentos
        	
    	int parametroAtual;  		// Guarda o parâmetro que está sendo atualmente lido
    	boolean modoCompleto = false, modoVerboso = false, modoPremios = false, modoArtigos = false, modoEventos = false, modoVinculoUnirio = false;   		// Guardam quais parâmetros foram passados pelo usuário para posterior call dos métodos da classe XML para cálculo da pontuação e exibição restrita aos modos requisitados
    	   	
    	SaidaModel txtSaida = null;  		// Cria um objeto da classe Saida para posterior escrita da saída do programa
    	LogModel txtLog = null;  		    // Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	
    	ArrayList<CandidatoModel> listaDeCandidatos = new ArrayList<CandidatoModel>();  			// Lista que guarda cada um dos arquivos XML contendo os currículos
    	

    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  		  // FOR para verificar cada um dos parâmetros passados pelo usuário
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o"))   			 // Verifica se o parâmetro é igual a -o (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de saída           
            	txtSaida = new SaidaModel(args[parametroAtual + 1]);  		 // Cria um objeto da classe SaidaModel para posterior escrita da saída do programa          
                  
            
            if (args[parametroAtual].equalsIgnoreCase("-l"))    		// Verifica se o parâmetro é igual a -l (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do arquivo de log      	
            	txtLog = new LogModel(args[parametroAtual + 1]);      	
                            	
            
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  		    // Verifica se o parâmetro é gual a -a (ignorando se este for digitado em maiúsculo ou minúsculo). Caso seja, a próxima posição dos argumentos indica o caminho do currículo em XML           
            	CandidatoModel candidato = new CandidatoModel(args[parametroAtual + 1], Integer.parseInt(args[parametroAtual + 2])); 		 // Cria um objeto da classe XML para posterior escrita do log de erros do programa
            	listaDeCandidatos.add(candidato);
            }
         
            
            if(args[parametroAtual].equalsIgnoreCase("-pr"))    	  // Seta a variável booleana modoPremios em true para posterior análise da saída (requerido modo de pontuação de prêmios)                  	
            	modoPremios = true;
            	              	
            
            if(args[parametroAtual].equalsIgnoreCase("-ar"))		  // Seta a variável booleana modoArtigos em true para posterior análise da saída (requerido modo de pontuação de artigos)   
            	modoArtigos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-anr"))		  // Seta a variável booleana modoArtigos em true para posterior análise da saída (requerido modo de pontuação de artigos)   
            	modoArtigos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-pe"))		  // Seta a variável booleana modoEventos em true para posterior análise da saída (requerido modo de pontuação de eventos)   
            	modoEventos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-vi"))		  // Seta a variável booleana modoVinculoUnirio em true para posterior análise da saída (requerido modo de pontuação de vínculos com a UNIRIO)   
            	modoVinculoUnirio = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-c"))			  // Seta a variável booleana modoCompleto em true para posterior análise da saída (requeridas todas as saídas)   
            	modoCompleto = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-v"))			  // Seta a variável booleana modoVerboso em true para posterior análise da saída (requeridos que sejam impressos no arquivo .txt de saída ano e descrição de prêmios, artigos, eventos e vínculos com a UNIRIO)   
            	modoVerboso = true;
            
                           
        }
    	
    	analisaSaidasSolicitadas(txtSaida, txtLog, listaDeCandidatos, modoCompleto, modoVerboso, modoPremios, modoArtigos, modoEventos, modoVinculoUnirio);			// Chama o método da própria classe PrincipalView para que sejam analisados os parâmetros das saídas requeridas pelo usuário
   	
    }
    
    /** Método que analisa quais foram os parâmetros passados pelo usuário e chama os métodos correspondentes na classe CalculadorController

     *   @return void */   
	private static void analisaSaidasSolicitadas(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos , boolean modoCompleto, boolean modoVerboso, boolean modoPremios, boolean modoArtigos, boolean modoEventos, boolean modoVinculoUnirio) throws IOException {
		
		txtSaida.escreveArquivo("\t\t\t Resultado do arquivo de saída:");  		  			     // Escrita inicial no arquivo de saída    
    	txtLog.escreveArquivo("\t\t\t Erros encontrados na execução do programa:");  		     // Escrita inicial no arquivo de log 
    	
    	CalculadorController calculadorPontuacao = new CalculadorController(txtSaida, txtLog, listaDeCandidatos); 
    	
    	
    	calculadorPontuacao.calculaPontuacaoSemestres();									     // Chamada do método da classe CalculadorController para cálculo de pontuação por semestre sem reprovação dos candidatos
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("Pontuação por semestres sem reprovação do candidato " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoSemestresSemReprovacao()));			// Imprime no arquivo .txt de saída a pontuação por semestres sem reprovação de cada candidato
    	
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
    	
    	if(modoCompleto == true) 			// Caso o modo completo seja pedido pelo usuário, executa todas as saídas
    		calculadorPontuacao.calculaModoCompleto(modoVerboso); 			// Chamada ao método da classe CalculadorController que calcula todas as saídas do programa
    	
    	
    	else {			  // Caso o modo completo não seja pedido pelo usuário, executa somente as saídas requisitadas 		
    		
    		if(modoPremios == true)
    			calculadorPontuacao.calculaModoPremios(modoVerboso);  			// Chamada do método da classe CalculadorController para cálculo de pontuação por prêmios 			
    		
    		if(modoArtigos == true)
    			calculadorPontuacao.calculaModoArtigos(modoVerboso);		    // Chamada do método da classe CalculadorController para cálculo de pontuação por artigos
    		
    		if(modoEventos == true)
    			calculadorPontuacao.calculaModoEventos(modoVerboso); 			// Chamada do método da classe CalculadorController para cálculo de pontuação por eventos  		
    	
    		if(modoVinculoUnirio == true)			
    			calculadorPontuacao.calculaModoVinculoUnirio(modoVerboso); 		// Chamada do método da classe CalculadorController para cálculo de pontuação de vínculos com a UNIRIO 		
    	}
    		
    	
    	imprimePontuacaoGeral(txtSaida,txtLog, listaDeCandidatos);			    // Chamada do método da própria classe PrincipalView para impressão no arquivo .txt de saída das pontuações de todos os candidatos, sem ordenação
    	
    	calculadorPontuacao.calculaRankingFinal();								// Chamada do método da classe CalculadorController para cálculo do ranking final
    	
    	imprimeRankingFinal(txtSaida,txtLog, listaDeCandidatos);			    // Chamada do método da própria classe PrincipalView para impressão no arquivo .txt de saída das pontuações de todos os candidatos, ordenados por ranking final

    	
	}
	
	
	
	
    /** Método que imprime no arquivo .txt de saída a pontuação total dos candidatos, sem ordenação

     *   @return void */   
	private static void imprimePontuacaoGeral(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {
		
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("\t\t\t\t Pontuação total de " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));			// Imprime no arquivo .txt de saída as pontuações de todos os candidatos, sem ordenação
		
	}
	
	
	
	
    /** Método que imprime no arquivo .txt de saída a pontuação total dos candidatos, já ordenados de acordo com o ranking final (maiores notas)

     *   @return void */   
	private static void imprimeRankingFinal(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {	
    	
		txtSaida.escreveArquivo("***************************************************************************************************************");
		txtSaida.escreveArquivo("\t\t\t\t\t RANKING FINAL DOS CANDIDATOS:");
    	
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) 
			txtSaida.escreveArquivo("\t\t\t\t\t" + listaDeCandidatos.get(auxCandidatos).getNomeCandidato() + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));			 // Imprime no arquivo .txt de saída as pontuações de todos os candidatos, ordenados pelo ranking final
	
	}
	
	
}