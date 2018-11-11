/**
 * 
 * @author Cibele
 * 
 * Classe filha da classe Arquivo, que possui métodos sobrescritos específicos da classe Log (arquivo de log de erros do programa)
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log extends Arquivo {

	@Override
	public void escreveArquivo(String caminhoArquivo, String texto) throws IOException {  // Método sobrescrito da classe Arquivo que recebe o caminho que o usuário indicou para salvamento do log de erros e o texto a ser adicionado.
		
		BufferedWriter arquivoLog = new BufferedWriter(new FileWriter(caminhoArquivo, true));  // Cria o arquivo de log de erros
		
		arquivoLog.write(texto);  // Escreve o texto passado (variável texto) para o método
		arquivoLog.newLine();  // Cria uma nova linha de texto dentro do arquivo de log
		arquivoLog.close();  // Fecha a escrita do arquivo
		
	}
	

	@Override
	public void leArquivo(String caminhoArquivo, String texto) throws IOException {
		
		
	}

}
