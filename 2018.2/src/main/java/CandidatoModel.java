/**
 * 
 * @author Cibele
 * 
 * Classe que guarda informação sobre os semestres sem reprovação de cada candidato e possui um currículo
 *
 */


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class CandidatoModel {

	private int semestresSemReprovacao = 0;
	private String nomeCandidato;
	private int pontuacaoTotal = 0, pontuacaoSemestresSemReprovacao = 0, pontuacaoPremios = 0, pontuacaoArtigos = 0, pontuacaoEventos = 0, pontuacaoVinculoUnirio = 0;
	private XMLModel curriculo = null;
	
	
	public CandidatoModel(String caminhoXML, int semestresSemReprovacao ) throws SAXException, IOException, ParserConfigurationException {			// Método construtor da classe Log. Recebe o caminho que o usuário especificou para a criação do arquivo txt de log
		
		this.semestresSemReprovacao = semestresSemReprovacao;
		
		curriculo = new XMLModel(caminhoXML);
			
	}

	
	
	public int getPontuacaoSemestresSemReprovacao() {
		return pontuacaoSemestresSemReprovacao;
	}





	public void setPontuacaoSemestresSemReprovacao(int pontuacaoSemestresSemReprovacao) {
		this.pontuacaoSemestresSemReprovacao = pontuacaoSemestresSemReprovacao;
	}





	public int getPontuacaoPremios() {
		return pontuacaoPremios;
	}





	public void setPontuacaoPremios(int pontuacaoPremios) {
		this.pontuacaoPremios = pontuacaoPremios;
	}






	public int getPontuacaoArtigos() {
		return pontuacaoArtigos;
	}


	
	

	public void setPontuacaoArtigos(int pontuacaoArtigos) {
		this.pontuacaoArtigos = pontuacaoArtigos;
	}



	
	
	public int getPontuacaoEventos() {
		return pontuacaoEventos;
	}





	public void setPontuacaoEventos(int pontuacaoEventos) {
		this.pontuacaoEventos = pontuacaoEventos;
	}





	public int getPontuacaoVinculoUnirio() {
		return pontuacaoVinculoUnirio;
	}





	public void setPontuacaoVinculoUnirio(int pontuacaoVinculoUnirio) {
		this.pontuacaoVinculoUnirio = pontuacaoVinculoUnirio;
	}





	public void setSemestresSemReprovacao(int semestresSemReprovacao) {
		this.semestresSemReprovacao = semestresSemReprovacao;
	}



	

	public int getSemestresSemReprovacao() {
		
		return semestresSemReprovacao;
		
	}


	
	
	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = this.pontuacaoTotal + pontuacaoTotal;
	}
	
	
	
	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}
	
	
	
	

	public String getNomeCandidato() {
		return nomeCandidato;
	}


	

	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}

	


	public XMLModel getCurriculo() {
		return curriculo;
	}
		
	
}