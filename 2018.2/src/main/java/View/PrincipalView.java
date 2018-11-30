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
 
    /** M�todo main do programa. Recebe os par�metros digitados pelo usu�rio e seta as booleanas das sa�das em true ou false para posterior chamada do m�todo de an�lise das sa�das

     *   @return void */   
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {      	// Inicia a execua��o do programa para a passagem de argumentos
        	
    	int parametroAtual;  		// Guarda o par�metro que est� sendo atualmente lido
    	boolean modoCompleto = false, modoVerboso = false, modoPremios = false, modoArtigos = false, modoEventos = false, modoVinculoUnirio = false;   		// Guardam quais par�metros foram passados pelo usu�rio para posterior call dos m�todos da classe XML para c�lculo da pontua��o e exibi��o restrita aos modos requisitados
    	   	
    	SaidaModel txtSaida = null;  		// Cria um objeto da classe Saida para posterior escrita da sa�da do programa
    	LogModel txtLog = null;  		    // Cria um objeto da classe Log para posterior escrita do log de erros do programa
    	
    	ArrayList<CandidatoModel> listaDeCandidatos = new ArrayList<CandidatoModel>();  			// Lista que guarda cada um dos arquivos XML contendo os curr�culos
    	

    	for(parametroAtual = 0; parametroAtual < args.length; parametroAtual++) {  		  // FOR para verificar cada um dos par�metros passados pelo usu�rio
    		System.out.println(args[parametroAtual]);
    		
            if (args[parametroAtual].equalsIgnoreCase("-o"))   			 // Verifica se o par�metro � igual a -o (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de sa�da           
            	txtSaida = new SaidaModel(args[parametroAtual + 1]);  		 // Cria um objeto da classe SaidaModel para posterior escrita da sa�da do programa          
                  
            
            if (args[parametroAtual].equalsIgnoreCase("-l"))    		// Verifica se o par�metro � igual a -l (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do arquivo de log      	
            	txtLog = new LogModel(args[parametroAtual + 1]);      	
                            	
            
            if(args[parametroAtual].equalsIgnoreCase("-a")) {  		    // Verifica se o par�metro � gual a -a (ignorando se este for digitado em mai�sculo ou min�sculo). Caso seja, a pr�xima posi��o dos argumentos indica o caminho do curr�culo em XML           
            	CandidatoModel candidato = new CandidatoModel(args[parametroAtual + 1], Integer.parseInt(args[parametroAtual + 2])); 		 // Cria um objeto da classe XML para posterior escrita do log de erros do programa
            	listaDeCandidatos.add(candidato);
            }
         
            
            if(args[parametroAtual].equalsIgnoreCase("-pr"))    	  // Seta a vari�vel booleana modoPremios em true para posterior an�lise da sa�da (requerido modo de pontua��o de pr�mios)                  	
            	modoPremios = true;
            	              	
            
            if(args[parametroAtual].equalsIgnoreCase("-ar"))		  // Seta a vari�vel booleana modoArtigos em true para posterior an�lise da sa�da (requerido modo de pontua��o de artigos)   
            	modoArtigos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-anr"))		  // Seta a vari�vel booleana modoArtigos em true para posterior an�lise da sa�da (requerido modo de pontua��o de artigos)   
            	modoArtigos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-pe"))		  // Seta a vari�vel booleana modoEventos em true para posterior an�lise da sa�da (requerido modo de pontua��o de eventos)   
            	modoEventos = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-vi"))		  // Seta a vari�vel booleana modoVinculoUnirio em true para posterior an�lise da sa�da (requerido modo de pontua��o de v�nculos com a UNIRIO)   
            	modoVinculoUnirio = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-c"))			  // Seta a vari�vel booleana modoCompleto em true para posterior an�lise da sa�da (requeridas todas as sa�das)   
            	modoCompleto = true;
            
            
            if(args[parametroAtual].equalsIgnoreCase("-v"))			  // Seta a vari�vel booleana modoVerboso em true para posterior an�lise da sa�da (requeridos que sejam impressos no arquivo .txt de sa�da ano e descri��o de pr�mios, artigos, eventos e v�nculos com a UNIRIO)   
            	modoVerboso = true;
            
                           
        }
    	
    	analisaSaidasSolicitadas(txtSaida, txtLog, listaDeCandidatos, modoCompleto, modoVerboso, modoPremios, modoArtigos, modoEventos, modoVinculoUnirio);			// Chama o m�todo da pr�pria classe PrincipalView para que sejam analisados os par�metros das sa�das requeridas pelo usu�rio
   	
    }
    
    /** M�todo que analisa quais foram os par�metros passados pelo usu�rio e chama os m�todos correspondentes na classe CalculadorController

     *   @return void */   
	private static void analisaSaidasSolicitadas(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos , boolean modoCompleto, boolean modoVerboso, boolean modoPremios, boolean modoArtigos, boolean modoEventos, boolean modoVinculoUnirio) throws IOException {
		
		txtSaida.escreveArquivo("\t\t\t Resultado do arquivo de sa�da:");  		  			     // Escrita inicial no arquivo de sa�da    
    	txtLog.escreveArquivo("\t\t\t Erros encontrados na execu��o do programa:");  		     // Escrita inicial no arquivo de log 
    	
    	CalculadorController calculadorPontuacao = new CalculadorController(txtSaida, txtLog, listaDeCandidatos); 
    	
    	
    	calculadorPontuacao.calculaPontuacaoSemestres();									     // Chamada do m�todo da classe CalculadorController para c�lculo de pontua��o por semestre sem reprova��o dos candidatos
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("Pontua��o por semestres sem reprova��o do candidato " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoSemestresSemReprovacao()));			// Imprime no arquivo .txt de sa�da a pontua��o por semestres sem reprova��o de cada candidato
    	
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
    	
    	if(modoCompleto == true) 			// Caso o modo completo seja pedido pelo usu�rio, executa todas as sa�das
    		calculadorPontuacao.calculaModoCompleto(modoVerboso); 			// Chamada ao m�todo da classe CalculadorController que calcula todas as sa�das do programa
    	
    	
    	else {			  // Caso o modo completo n�o seja pedido pelo usu�rio, executa somente as sa�das requisitadas 		
    		
    		if(modoPremios == true)
    			calculadorPontuacao.calculaModoPremios(modoVerboso);  			// Chamada do m�todo da classe CalculadorController para c�lculo de pontua��o por pr�mios 			
    		
    		if(modoArtigos == true)
    			calculadorPontuacao.calculaModoArtigos(modoVerboso);		    // Chamada do m�todo da classe CalculadorController para c�lculo de pontua��o por artigos
    		
    		if(modoEventos == true)
    			calculadorPontuacao.calculaModoEventos(modoVerboso); 			// Chamada do m�todo da classe CalculadorController para c�lculo de pontua��o por eventos  		
    	
    		if(modoVinculoUnirio == true)			
    			calculadorPontuacao.calculaModoVinculoUnirio(modoVerboso); 		// Chamada do m�todo da classe CalculadorController para c�lculo de pontua��o de v�nculos com a UNIRIO 		
    	}
    		
    	
    	imprimePontuacaoGeral(txtSaida,txtLog, listaDeCandidatos);			    // Chamada do m�todo da pr�pria classe PrincipalView para impress�o no arquivo .txt de sa�da das pontua��es de todos os candidatos, sem ordena��o
    	
    	calculadorPontuacao.calculaRankingFinal();								// Chamada do m�todo da classe CalculadorController para c�lculo do ranking final
    	
    	imprimeRankingFinal(txtSaida,txtLog, listaDeCandidatos);			    // Chamada do m�todo da pr�pria classe PrincipalView para impress�o no arquivo .txt de sa�da das pontua��es de todos os candidatos, ordenados por ranking final

    	
	}
	
	
	
	
    /** M�todo que imprime no arquivo .txt de sa�da a pontua��o total dos candidatos, sem ordena��o

     *   @return void */   
	private static void imprimePontuacaoGeral(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {
		
    	txtSaida.escreveArquivo("---------------------------------------------------------------------------------------------------------------");
		
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++)
			txtSaida.escreveArquivo("\t\t\t\t Pontua��o total de " +(listaDeCandidatos.get(auxCandidatos).getNomeCandidato()) + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));			// Imprime no arquivo .txt de sa�da as pontua��es de todos os candidatos, sem ordena��o
		
	}
	
	
	
	
    /** M�todo que imprime no arquivo .txt de sa�da a pontua��o total dos candidatos, j� ordenados de acordo com o ranking final (maiores notas)

     *   @return void */   
	private static void imprimeRankingFinal(SaidaModel txtSaida, LogModel txtLog, ArrayList<CandidatoModel> listaDeCandidatos) throws IOException {	
    	
		txtSaida.escreveArquivo("***************************************************************************************************************");
		txtSaida.escreveArquivo("\t\t\t\t\t RANKING FINAL DOS CANDIDATOS:");
    	
		for(int auxCandidatos = 0; auxCandidatos < listaDeCandidatos.size(); auxCandidatos++) 
			txtSaida.escreveArquivo("\t\t\t\t\t" + listaDeCandidatos.get(auxCandidatos).getNomeCandidato() + ": " +(listaDeCandidatos.get(auxCandidatos).getPontuacaoTotal()));			 // Imprime no arquivo .txt de sa�da as pontua��es de todos os candidatos, ordenados pelo ranking final
	
	}
	
	
}