/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe ArquivoModel, que possui m�todos sobrescritos espec�ficos da classe Saida (arquivo de sa�da do resultado do programa)
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaidaModel extends ArquivoModel {

	
	public SaidaModel(String caminhoDoArquivo) {			// M�todo construtor da classe Saida. Recebe o caminho que o usu�rio especificou para a cria��o do arquivo txt de sa�da
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
	
	
	@Override
	public void escreveArquivo(String texto) throws IOException {  		     // M�todo sobrescrito da classe Arquivo que recebe o caminho que o usu�rio indicou para salvamento do arquivo e o texto a ser adicionado.
			
		BufferedWriter arquivoSaida = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));      // Cria o arquivo de sa�da
		
		arquivoSaida.write(texto);  		 // Escreve o texto passado (vari�vel texto) para o m�todo
		arquivoSaida.newLine();  		     // Cria uma nova linha de texto dentro do arquivo de sa�da
		arquivoSaida.close();  				 // Fecha a escrita do arquivo
		
	}
	
	
	
	
	
	@Override
	public String getCaminhoDoArquivo() {		  // M�todo que retorna o caminho onde se encontra o arquivo de sa�da
		
		return caminhoDoArquivo;
		
	}
	
}