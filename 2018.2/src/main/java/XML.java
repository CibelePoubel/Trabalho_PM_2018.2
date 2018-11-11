/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui métodos sobrescritos específicos da classe XML (arquivo de currículo do candidato)
 *
 */

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XML extends Arquivo { 

	
	private int pontuacaoSemestresSemReprovacao;
	private int semestresSemReprovacao;
	
	public XML(String caminhoArquivo, int semestresSemReprovacao) throws SAXException, IOException, ParserConfigurationException{
		this.semestresSemReprovacao = semestresSemReprovacao;
		File arquivoXML = new File(caminhoArquivo);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document XML = dBuilder.parse(arquivoXML);
	}
	
		
	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) {
	
	}
	

	@Override
	public void leArquivo(String caminhoArquivo, String texto) throws IOException, SAXException, ParserConfigurationException {
		
	}
	
	
	public int calculaPontuacaoSemestres() {
		
		this.pontuacaoSemestresSemReprovacao = 1 * semestresSemReprovacao;
		return pontuacaoSemestresSemReprovacao;
		
	}
	
	
	public int getPontuacaoSemestres() {
		
		return pontuacaoSemestresSemReprovacao;
		
	}
		
}
