package Model;
/**
 * 
 * @author Cibele
 * 
 * Classe que guarda informação sobre os semestres sem reprovação e pontuação de cada candidato. Possui também um currículo associado.
 *
 */

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class CandidatoModel {

	private int semestresSemReprovacao = 0;			// Int que recebe o número de semestres sem reprovação inseridos pelo usuário
	private String nomeCandidato;				    // Guarda o nome do candidato para facilitar a identificação das saídas e ranking de pontuações
	private int pontuacaoTotal = 0, pontuacaoSemestresSemReprovacao = 0, pontuacaoPremios = 0, pontuacaoArtigos = 0, pontuacaoEventos = 0, pontuacaoVinculoUnirio = 0;			// Variáveis que guardam a pontuação de cada saída
	private CurriculoModel xml = null;				// Arquivo XML que guarda o currículo do candidato
	
	
    /** Método construtor da classe CandidatoModel. Recebe o número de semestres sem reprovação inserido pelo usuário e o caminho para criação do XML de currículo associado a este candidato
     *   @param - Caminho do arquivo XML, necessário para se passar de parâmetro ao construtor da classe Curriculo
     *   @param - Variável que guarda o número de semestres sem reprovação do candidato
     *   @return void */  
	public CandidatoModel(String caminhoXML, int semestresSemReprovacao ) throws SAXException, IOException, ParserConfigurationException {			// Método construtor da classe Log. Recebe o caminho que o usuário especificou para a criação do arquivo txt de log
		
		this.semestresSemReprovacao = semestresSemReprovacao;
		
		xml = new CurriculoModel(caminhoXML);
			
	}

	
	
    /** Método que retorna a variável com a pontuação por semestres sem reprovação

     *   @return Integer - pontuacaoSemestresSemReprovacao */  
	public int getPontuacaoSemestresSemReprovacao() {
		return pontuacaoSemestresSemReprovacao;
	}



    /** Método que recebe e seta na variável a pontuação de semestres sem reprovação

     *   @return void */
	public void setPontuacaoSemestresSemReprovacao(int pontuacaoSemestresSemReprovacao) {
		this.pontuacaoSemestresSemReprovacao = pontuacaoSemestresSemReprovacao;
	}



    /** Método que retorna a variável com a pontuação por prêmios

     *   @return Integer - pontuacaoPremios */ 
	public int getPontuacaoPremios() {
		return pontuacaoPremios;
	}



    /** Método que recebe e seta na variável a pontuação por prêmios
     *   @param - Recebe a pontuação por prêmios a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoPremios(int pontuacaoPremios) {
		this.pontuacaoPremios = pontuacaoPremios;
	}



    /** Método que retorna a variável com a pontuação por artigos

     *   @return Integer - pontuacaoArtigos */ 
	public int getPontuacaoArtigos() {
		return pontuacaoArtigos;
	}


	
    /** Método que recebe e seta na variável a pontuação por artigos
     *   @param - Recebe a pontuação por artigos a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoArtigos(int pontuacaoArtigos) {
		this.pontuacaoArtigos = pontuacaoArtigos;
	}



    /** Método que retorna a variável com a pontuação por eventos

     *   @return Integer - pontuacaoEventos */ 
	public int getPontuacaoEventos() {
		return pontuacaoEventos;
	}



    /** Método que recebe e seta na variável a pontuação por eventos
     *   @param - Recebe a pontuação por eventos a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoEventos(int pontuacaoEventos) {
		this.pontuacaoEventos = pontuacaoEventos;
	}



    /** Método que retorna a variável com a pontuação por vínculos com a UNIRIO

     *   @return Integer - pontuacaoVinculoUnirio */ 
	public int getPontuacaoVinculoUnirio() {
		return pontuacaoVinculoUnirio;
	}



    /** Método que recebe e seta na variável a pontuação por vínculos com a UNIRIO
     *   @param - Recebe a pontuação por vínculos com a UNIRIO a ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoVinculoUnirio(int pontuacaoVinculoUnirio) {
		this.pontuacaoVinculoUnirio = pontuacaoVinculoUnirio;
	}



    /** Método que recebe e seta na variável a quantidade de semestres sem reprovação do candidato
     *   @param - Recebe a pontuação por semestres sem reprovação a ser setada no objeto de Candidato
     *   @return void */
	public void setSemestresSemReprovacao(int semestresSemReprovacao) {
		this.semestresSemReprovacao = semestresSemReprovacao;
	}



    /** Método que retorna a variável com a quantidade de semestres sem reprovação do candidato

     *   @return Integer - semestresSemReprovacao */ 
	public int getSemestresSemReprovacao() {
		
		return semestresSemReprovacao;
		
	}


	
    /** Método que recebe e seta na variável a pontuação total do candidato
     *   @param - Recebe a pontuação total ser setada no objeto de Candidato
     *   @return void */
	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = this.pontuacaoTotal + pontuacaoTotal;
	}
	
	
	
    /** Método que retorna a variável com a pontuação total do candidato

     *   @return Integer - pontuacaoTotal */ 
	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}
	
	
	
    /** Método que retorna a variável com o nome do candidato

     *   @return String - nomeCandidato */ 
	public String getNomeCandidato() {
		return nomeCandidato;
	}


	
    /** Método que recebe e seta na variável o nome do candidato
     *   @param - Recebe o nome a ser setado no objeto de Candidato
     *   @return void */
	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}

	
    /** Método que retorna a variável com o arquivo XML que contém o currículo do candidato

     *   @return XMLModel - xml */ 
	public CurriculoModel getXML() {
		return xml;
	}
		
	
	
}