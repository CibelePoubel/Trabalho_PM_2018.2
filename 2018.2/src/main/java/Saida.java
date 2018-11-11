/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui m�todos sobrescritos espec�ficos da classe Saida (arquivo de sa�da do resultado do programa)
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Saida extends Arquivo {

	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) throws IOException {  // M�todo sobrescrito da classe Arquivo que recebe o caminho que o usu�rio indicou para salvamento do arquivo e o texto a ser adicionado.
		
		BufferedWriter arquivoSaida = new BufferedWriter(new FileWriter(caminhoArquivo, true));  // Cria o arquivo de sa�da
		
		arquivoSaida.write(texto);  // Escreve o texto passado (vari�vel texto) para o m�todo
		arquivoSaida.newLine();  // Cria uma nova linha de texto dentro do arquivo de sa�da
		arquivoSaida.close();  // Fecha a escrita do arquivo
		
	}
	

	@Override
	public void leArquivo(String caminhoArquivo, String texto) throws IOException {
				
	}

}
