package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.com.estacionamento.model.Movimentacao;

public class MovimentacaoDao {
	
	private Movimentacao movimentacao;
	private static final String LOCAL_ARQUIVO = "C:/Users/pedro/movimentacao.ds1";
	
	public MovimentacaoDao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	public MovimentacaoDao() {
		
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}
	
	public void salvar() {

		Path path = Paths.get(LOCAL_ARQUIVO);

		try {

			BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), 
					StandardOpenOption.WRITE, StandardOpenOption.APPEND); //append = adicionar

			writer.write(movimentacao.toStringEntrada());
			writer.newLine();
			writer.close();

		} catch (Exception e) {

		}

	}
	
	public ArrayList<Movimentacao> listarMovimentos() {
		//Procedimento para abrir um arquivo para leitura ou escrita
		
		//Passo 1 - Obter o caminho do arquivo
		Path path = Paths.get(LOCAL_ARQUIVO);
		
		//Criar o Buffer para o arquivo
		//Usar try quando algo puder dar errado
		try { 
															//Charset = Conjunto de caracteres
			BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
			
			String linha = reader.readLine(); //pega a primeira linha do arquivo e muda para a próxima
			
			ArrayList<Movimentacao> movimentos = new ArrayList<>();
			
			while (linha != null) {
				String[] vetorMovimentos = linha.split(";"); //transformando a linha em um vetor e separando as posições com ;
					
				Movimentacao movimento = new Movimentacao();
				movimento.setCodigo(vetorMovimentos[0]);
				movimento.setPlaca(vetorMovimentos[1]);
				movimento.setModelo(vetorMovimentos[2]);
				
				movimento.setDataEntrada(converteEmData(vetorMovimentos[3]));
				
				movimento.setHoraEntrada(converteEmHora(vetorMovimentos[4]));
				
				if (vetorMovimentos[5].equals("0")) {
					movimento.setDataSaida(null);
				} else {
					movimento.setDataSaida(converteEmData(vetorMovimentos[5]));
				}
				
				if (vetorMovimentos[6].equals("0")) {
					movimento.setHoraSaida(null);
				} else {
					movimento.setHoraSaida(converteEmHora(vetorMovimentos[6]));
				}
								
				movimento.setTempo(Double.parseDouble(vetorMovimentos[7]));
				movimento.setValorPago(Double.parseDouble(vetorMovimentos[8]));
				
				movimentos.add(movimento);
				
				linha = reader.readLine();
			}
			
			reader.close();
			
			return movimentos;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na tentativa de ler o arquivo!");
			e.printStackTrace();
			return null;
		}
	}
	
	private LocalDate converteEmData(String data) {
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate novaData = LocalDate.parse(data, df);
		return novaData;
		
	}
	
	private LocalTime converteEmHora(String hora) {
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime novaHora = LocalTime.parse(hora, df);
		return novaHora;
		
	}
	
	public Movimentacao buscarMovimentacao(String codigo) {
		//Procedimento para abrir um arquivo para leitura ou escrita
		
		//Passo 1 - Obter o caminho do arquivo
		Path path = Paths.get(LOCAL_ARQUIVO);
		
		//Criar o Buffer para o arquivo
		//Usar try quando algo puder dar errado
		try { 
															//Charset = Conjunto de caracteres
			BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
			
			String linha = reader.readLine(); //pega a primeira linha do arquivo e muda para a próxima
			Movimentacao movimento = new Movimentacao();
			
			while (linha != null) {
				String[] vetorMovimentos = linha.split(";"); //transformando a linha em um vetor e separando as posições com ;
				
				if (vetorMovimentos[1].equals(codigo)) {
					System.out.println(vetorMovimentos.toString());
					movimento.setCodigo(vetorMovimentos[0]);
					movimento.setPlaca(vetorMovimentos[1]);
					movimento.setModelo(vetorMovimentos[2]);
					
					movimento.setDataEntrada(converteEmData(vetorMovimentos[3]));
					movimento.setHoraEntrada(converteEmHora(vetorMovimentos[4]));
					
					if (vetorMovimentos[5].equals("0")) {
						movimento.setDataSaida(null);
					} else {
						movimento.setDataSaida(converteEmData(vetorMovimentos[5]));
					}
					
					if (vetorMovimentos[6].equals("0")) {
						movimento.setHoraSaida(null);
					} else {
						movimento.setHoraSaida(converteEmHora(vetorMovimentos[6]));
					}
					
					movimento.setTempo(Double.parseDouble(vetorMovimentos[7]));
					movimento.setValorPago(Double.parseDouble(vetorMovimentos[8]));
					break;
				}
				
				linha = reader.readLine();
			}
			
			reader.close();
			
			return movimento;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na tentativa de ler o arquivo!");
			return null;
		}
	}
	

	
}
