package Model;
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

    /** M�todo construtor da classe SaidaModel. Recebe o caminho desejado pelo usu�rio para salvamento do arquivo .txt de sa�da (resultado do programa)

     *   @return void */  
	public SaidaModel(String caminhoDoArquivo) {			
		
		this.caminhoDoArquivo = caminhoDoArquivo;
			
	}
	
	
	
    /** M�todo sobrescrito da classe Arquivo que efetua a escrita da String passada no arquivo .txt de sa�da

     *   @return void */ 
	@Override
	public void escreveArquivo(String texto) throws IOException {  		     
			
		BufferedWriter arquivoSaida = new BufferedWriter(new FileWriter(caminhoDoArquivo, true));      // Cria o arquivo de sa�da
		
		arquivoSaida.write(texto);  		 // Escreve o texto passado (vari�vel texto) para o m�todo
		arquivoSaida.newLine();  		     // Cria uma nova linha de texto dentro do arquivo de sa�da
		arquivoSaida.close();  				 // Fecha a escrita do arquivo
		
	}
	
	
	
    /** M�todo que retorna o caminho de salvamento/leitura do arquivo de sa�da

     *   @return String - CaminhoDoArquivo */  
	@Override
	public String getCaminhoDoArquivo() {		
		
		return caminhoDoArquivo;			 // Retorna a string com o caminho do arquivo .txt de sa�da
		
	}
	
	
	
}