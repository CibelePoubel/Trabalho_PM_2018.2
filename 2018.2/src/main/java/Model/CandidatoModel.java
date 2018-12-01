package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe que guarda informa��o sobre os semestres sem reprova��o e pontua��o de cada candidato. Possui tamb�m um curr�culo associado.
 *
 */

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class CandidatoModel {

	private int semestresSemReprovacao = 0;			// Int que recebe o n�mero de semestres sem reprova��o inseridos pelo usu�rio
	private String nomeCandidato;				    // Guarda o nome do candidato para facilitar a identifica��o das sa�das e ranking de pontua��es
	private int pontuacaoTotal = 0, pontuacaoSemestresSemReprovacao = 0, pontuacaoPremios = 0, pontuacaoArtigos = 0, pontuacaoEventos = 0, pontuacaoVinculoUnirio = 0;			// Vari�veis que guardam a pontua��o de cada sa�da
	private CurriculoModel xml = null;				// Arquivo XML que guarda o curr�culo do candidato
	
	
    /** M�todo construtor da classe CandidatoModel. Recebe o n�mero de semestres sem reprova��o inserido pelo usu�rio e o caminho para cria��o do XML de curr�culo associado a este candidato
     *   @param - Caminho do arquivo XML, necess�rio para se passar de par�metro ao construtor da classe Curriculo
     *   @param - Vari�vel que guarda o n�mero de semestres sem reprova��o do candidato
     *   @return void */  
	public CandidatoModel(String caminhoXML, int semestresSemReprovacao ) throws SAXException, IOException, ParserConfigurationException {			// M�todo construtor da classe Log. Recebe o caminho que o usu�rio especificou para a cria��o do arquivo txt de log
		
		this.semestresSemReprovacao = semestresSemReprovacao;
		
		xml = new CurriculoModel(caminhoXML);
			
	}

	
	
    /** M�todo que retorna a vari�vel com a pontua��o por semestres sem reprova��o

     *   @return Integer - pontuacaoSemestresSemReprovacao */  
	public int getPontuacaoSemestresSemReprovacao() {
		return pontuacaoSemestresSemReprovacao;
	}



    /** M�todo que recebe e seta na vari�vel a pontua��o de semestres sem reprova��o

     *   @return void */
	public void setPontuacaoSemestresSemReprovacao(int pontuacaoSemestresSemReprovacao) {
		this.pontuacaoSemestresSemReprovacao = pontuacaoSemestresSemReprovacao;
	}



    /** M�todo que retorna a vari�vel com a pontua��o por pr�mios

     *   @return Integer - pontuacaoPremios */ 
	public int getPontuacaoPremios() {
		return pontuacaoPremios;
	}



    /** M�todo que recebe e seta na vari�vel a pontua��o por pr�mios
     *   @param - Recebe a pontua��o por pr�mios a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoPremios(int pontuacaoPremios) {
		this.pontuacaoPremios = pontuacaoPremios;
	}



    /** M�todo que retorna a vari�vel com a pontua��o por artigos

     *   @return Integer - pontuacaoArtigos */ 
	public int getPontuacaoArtigos() {
		return pontuacaoArtigos;
	}


	
    /** M�todo que recebe e seta na vari�vel a pontua��o por artigos
     *   @param - Recebe a pontua��o por artigos a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoArtigos(int pontuacaoArtigos) {
		this.pontuacaoArtigos = pontuacaoArtigos;
	}



    /** M�todo que retorna a vari�vel com a pontua��o por eventos

     *   @return Integer - pontuacaoEventos */ 
	public int getPontuacaoEventos() {
		return pontuacaoEventos;
	}



    /** M�todo que recebe e seta na vari�vel a pontua��o por eventos
     *   @param - Recebe a pontua��o por eventos a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoEventos(int pontuacaoEventos) {
		this.pontuacaoEventos = pontuacaoEventos;
	}



    /** M�todo que retorna a vari�vel com a pontua��o por v�nculos com a UNIRIO

     *   @return Integer - pontuacaoVinculoUnirio */ 
	public int getPontuacaoVinculoUnirio() {
		return pontuacaoVinculoUnirio;
	}



    /** M�todo que recebe e seta na vari�vel a pontua��o por v�nculos com a UNIRIO
     *   @param - Recebe a pontua��o por v�nculos com a UNIRIO a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoVinculoUnirio(int pontuacaoVinculoUnirio) {
		this.pontuacaoVinculoUnirio = pontuacaoVinculoUnirio;
	}



    /** M�todo que recebe e seta na vari�vel a quantidade de semestres sem reprova��o do candidato
     *   @param - Recebe a pontua��o por semestres sem reprova��o a ser setada no objeto de Candidato
     *   @return void */
	public void setSemestresSemReprovacao(int semestresSemReprovacao) {
		this.semestresSemReprovacao = semestresSemReprovacao;
	}



    /** M�todo que retorna a vari�vel com a quantidade de semestres sem reprova��o do candidato

     *   @return Integer - semestresSemReprovacao */ 
	public int getSemestresSemReprovacao() {
		
		return semestresSemReprovacao;
		
	}


	
    /** M�todo que recebe e seta na vari�vel a pontua��o total do candidato
     *   @param - Recebe a pontua��o total ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = this.pontuacaoTotal + pontuacaoTotal;
	}
	
	
	
    /** M�todo que retorna a vari�vel com a pontua��o total do candidato

     *   @return Integer - pontuacaoTotal */ 
	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}
	
	
	
    /** M�todo que retorna a vari�vel com o nome do candidato

     *   @return String - nomeCandidato */ 
	public String getNomeCandidato() {
		return nomeCandidato;
	}


	
    /** M�todo que recebe e seta na vari�vel o nome do candidato
     *   @param - Recebe o nome a ser setado no objeto de Candidato
     *   @return void */
	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}

	
    /** M�todo que retorna a vari�vel com o arquivo XML que cont�m o curr�culo do candidato

     *   @return XMLModel - xml */ 
	public CurriculoModel getXML() {
		return xml;
	}
		
	
	
}