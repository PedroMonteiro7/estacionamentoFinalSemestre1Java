package br.com.estacionamento.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.MovimentacaoDao;
import util.Util;

public class FrameVeiculo {

	private JLabel labelEntrada;
	private JLabel labelPlaca;
	private JLabel labelModelo;
	private JTextField textPlaca;
	private JTextField textModelo;
	private JButton buttonEntrar;
	private JLabel labelSaida;
	private JLabel labelPlaca2;
	private JTextField textPlaca2;
	private JButton buttonBuscar;
	private JLabel labelModelo2;
	private JTextField textModelo2;
	private JLabel labelDataEntrada;
	private JTextField textDataEntrada;
	private JLabel labelHoraEntrada;
	private JTextField textHoraEntrada;
	private JLabel labelDataSaida;
	private JTextField textDataSaida;
	private JLabel labelHoraSaida;
	private JTextField textHoraSaida;
	private JLabel labelTempo;
	private JTextField textTempo;
	private JLabel labelValorAPagar;
	private JTextField textValorAPagar;
	private JButton buttonEfetuarSaida;
	private JButton buttonFecharSistema;

	private JTable tabelaVeiculos;
	private DefaultTableModel tabelaVeiculosModel;
	private JScrollPane scrollTabelaVeiculos;

	public void criarTela() {

		JFrame tela = new JFrame();
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(700, 600);
		tela.setTitle("Estacionamento");
		tela.setLayout(null); // anula as posições padrões dos componentes e deixa para nós mesmos definirmos
		tela.setResizable(false); // para não maximizar ou alterar o tamanho da tela

		// Entrada
		labelEntrada = new JLabel();
		labelEntrada.setBounds(50, 20, 70, 30);
		labelEntrada.setForeground(Color.black);
		labelEntrada.setText("ENTRADA");

		// Placa:
		labelPlaca = new JLabel();
		labelPlaca.setBounds(50, 60, 60, 20);
		labelPlaca.setForeground(Color.black);
		labelPlaca.setText("PLACA:");

		// Placa (campo de texto/inserção)
		textPlaca = new JTextField();
		textPlaca.setBounds(40, 90, 160, 30);

		// Modelo:
		labelModelo = new JLabel();
		labelModelo.setBounds(220, 60, 60, 20);
		labelModelo.setForeground(Color.black);
		labelModelo.setText("MODELO:");

		// Modelo (campo de texto/inserção)
		textModelo = new JTextField();
		textModelo.setBounds(210, 90, 160, 30);

		// Botão ENTRAR
		buttonEntrar = new JButton("ENTRAR");
		buttonEntrar.setBounds(380, 90, 110, 30);

		// *** Modelo da tabela (colunas e linhas)
		tabelaVeiculosModel = new DefaultTableModel();
		tabelaVeiculosModel.addColumn("CÓDIGO");
		tabelaVeiculosModel.addColumn("PLACA");
		tabelaVeiculosModel.addColumn("MODELO");
		tabelaVeiculosModel.addColumn("DATA ENTRADA");

		// Vetor que representa cada linha da tabela
		MovimentacaoDao movimentoDao = new MovimentacaoDao();
		ArrayList<Movimentacao> movimentos = movimentoDao.listarMovimentos();

		for (Movimentacao movimentacao : movimentos) { // looping. A cada volta cria um cliente e o adiciona na lista
														// (clientes)
			String[] vetorMovimentacao = { movimentacao.getCodigo(), movimentacao.getPlaca(), movimentacao.getModelo(),
					movimentacao.getDataEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) }; // transformando
																										// LocalDate em
																										// String
			tabelaVeiculosModel.addRow(vetorMovimentacao);
		}

		// **Criando a tabela
		tabelaVeiculos = new JTable(tabelaVeiculosModel);

		// Definindo a largura das colunas da tabela
		tabelaVeiculos.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabelaVeiculos.getColumnModel().getColumn(1).setPreferredWidth(110);
		tabelaVeiculos.getColumnModel().getColumn(2).setPreferredWidth(110);
		tabelaVeiculos.getColumnModel().getColumn(3).setPreferredWidth(130);

		// Impedindo a reordenação das colunas (movimentação)
		tabelaVeiculos.getTableHeader().setReorderingAllowed(false);

		// painel de rolagem (scroll) e tabela sendo inserida nele
		scrollTabelaVeiculos = new JScrollPane(tabelaVeiculos);
		scrollTabelaVeiculos.setBounds(40, 130, 450, 130);

		// SAÍDA
		labelSaida = new JLabel();
		labelSaida.setBounds(50, 270, 50, 30);
		labelSaida.setForeground(Color.black);
		labelSaida.setText("SAÍDA");

		// PLACA:
		labelPlaca2 = new JLabel();
		labelPlaca2.setBounds(50, 310, 50, 20);
		labelPlaca2.setForeground(Color.black);
		labelPlaca2.setText("PLACA:");

		// Placa (campo de texto/inserção)
		textPlaca2 = new JTextField();
		textPlaca2.setBounds(40, 340, 120, 30);

		// Botão BUSCAR
		buttonBuscar = new JButton("BUSCAR");
		buttonBuscar.setBounds(170, 340, 100, 30);

		// MODELO:
		labelModelo2 = new JLabel();
		labelModelo2.setBounds(50, 380, 60, 20);
		labelModelo2.setForeground(Color.black);
		labelModelo2.setText("MODELO:");

		// Modelo (campo de texto/inserção)
		textModelo2 = new JTextField();
		textModelo2.setBounds(40, 410, 100, 30);

		// DATA ENTRADA:
		labelDataEntrada = new JLabel();
		labelDataEntrada.setBounds(150, 380, 100, 20);
		labelDataEntrada.setForeground(Color.black);
		labelDataEntrada.setText("DATA ENTRADA:");

		// Data Entrada (campo de texto/inserção)
		textDataEntrada = new JTextField();
		textDataEntrada.setBounds(150, 410, 100, 30);

		// HORA ENTRADA:
		labelHoraEntrada = new JLabel();
		labelHoraEntrada.setBounds(260, 380, 100, 20);
		labelHoraEntrada.setForeground(Color.black);
		labelHoraEntrada.setText("HORA ENTRADA:");

		// Hora Entrada (campo de texto/inserção)
		textHoraEntrada = new JTextField();
		textHoraEntrada.setBounds(260, 410, 100, 30);

		// DATA SAÍDA:
		labelDataSaida = new JLabel();
		labelDataSaida.setBounds(380, 380, 100, 20);
		labelDataSaida.setForeground(Color.black);
		labelDataSaida.setText("DATA SAÍDA:");

		// Data Saída (campo de texto/inserção)
		textDataSaida = new JTextField();
		textDataSaida.setBounds(370, 410, 100, 30);

		// HORA SAÍDA:
		labelHoraSaida = new JLabel();
		labelHoraSaida.setBounds(490, 380, 100, 20);
		labelHoraSaida.setForeground(Color.black);
		labelHoraSaida.setText("HORA SAÍDA:");

		// Hora Saída (campo de texto/inserção)
		textHoraSaida = new JTextField();
		textHoraSaida.setBounds(480, 410, 100, 30);

		// TEMPO:
		labelTempo = new JLabel();
		labelTempo.setBounds(600, 380, 70, 20);
		labelTempo.setForeground(Color.black);
		labelTempo.setText("TEMPO:");

		// Tempo (campo de texto/inserção)
		textTempo = new JTextField();
		textTempo.setBounds(590, 410, 60, 30);

		// VALOR A PAGAR:
		labelValorAPagar = new JLabel();
		labelValorAPagar.setBounds(50, 480, 100, 20);
		labelValorAPagar.setForeground(Color.black);
		labelValorAPagar.setText("VALOR A PAGAR:");

		// Valor a pagar (campo de texto/inserção)
		textValorAPagar = new JTextField();
		textValorAPagar.setBounds(160, 470, 150, 50);

		// Botão EFETUAR SAÍDA
		buttonEfetuarSaida = new JButton("EFETUAR SAÍDA");
		buttonEfetuarSaida.setBounds(320, 470, 150, 50);

		// Botão FECHAR SISTEMA
		buttonFecharSistema = new JButton("FECHAR SISTEMA");
		buttonFecharSistema.setBounds(480, 470, 150, 50);

		tela.getContentPane().add(labelEntrada);
		tela.getContentPane().add(labelPlaca);
		tela.getContentPane().add(textPlaca);
		tela.getContentPane().add(labelModelo);
		tela.getContentPane().add(textModelo);
		tela.getContentPane().add(buttonEntrar);
		tela.getContentPane().add(scrollTabelaVeiculos);
		tela.getContentPane().add(labelSaida);
		tela.getContentPane().add(labelPlaca2);
		tela.getContentPane().add(textPlaca2);
		tela.getContentPane().add(buttonBuscar);
		tela.getContentPane().add(labelModelo2);
		tela.getContentPane().add(textModelo2);
		tela.getContentPane().add(labelDataEntrada);
		tela.getContentPane().add(textDataEntrada);
		tela.getContentPane().add(labelHoraEntrada);
		tela.getContentPane().add(textHoraEntrada);
		tela.getContentPane().add(labelDataSaida);
		tela.getContentPane().add(textDataSaida);
		tela.getContentPane().add(labelHoraSaida);
		tela.getContentPane().add(textHoraSaida);
		tela.getContentPane().add(labelTempo);
		tela.getContentPane().add(textTempo);
		tela.getContentPane().add(labelValorAPagar);
		tela.getContentPane().add(textValorAPagar);
		tela.getContentPane().add(buttonEfetuarSaida);
		tela.getContentPane().add(buttonFecharSistema);

		tela.setVisible(true);

		// Ouvinte de ação/evento para o botão ENTRAR
		buttonEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (validarFormulario()) {
					Movimentacao movimentacao = new Movimentacao();
					movimentacao.setCodigo(Util.gerarCodigo());
					movimentacao.setPlaca(textPlaca.getText().toUpperCase()); // getText pega o texto que está dentro do componente
					movimentacao.setModelo(textModelo.getText().toUpperCase()); // toUpperCase deixa a letra maiúscula
					movimentacao.setDataEntrada(LocalDate.now());
					movimentacao.setHoraEntrada(LocalTime.now());

					MovimentacaoDao dao = new MovimentacaoDao(movimentacao);
					dao.salvar();

					JOptionPane.showMessageDialog( // mensagem de cadastro realizado
							null, movimentacao.getPlaca() + " foi cadastrado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

					limparFormulario();

				} else {
					JOptionPane.showMessageDialog( // mensagem de erro
							null, "Você deve preencher todos os campos!", // mensagem
							"Atenção", // título
							JOptionPane.WARNING_MESSAGE); // desenho/ícone de aviso
				}

			}
		});

		buttonBuscar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override // ao clicar no botão BUSCAR, seus dados são apresentados
			public void mouseClicked(MouseEvent e) {

				String placa = textPlaca2.getText().toString();

				MovimentacaoDao dao = new MovimentacaoDao();
				Movimentacao movimentacao = dao.buscarMovimentacao(placa);
				
				textModelo2.setText(movimentacao.getModelo());
				textDataEntrada.setText(formatarData(movimentacao.getDataEntrada()));
				textHoraEntrada.setText(formatarHora(movimentacao.getHoraEntrada()));
				textDataSaida.setText(formatarData(LocalDate.now()));
				textHoraSaida.setText(formatarHora(LocalTime.now()));
				
				Duration duracao = Duration.between(movimentacao.getHoraEntrada(), LocalTime.now());
				
				if (duracao.toHours() >= 1) {
					BigDecimal horasEmBigDecimal = new BigDecimal(duracao.toHours());
					textTempo.setText(horasEmBigDecimal.toString());
				} else {
					BigDecimal minutosEmBigDecimal = new BigDecimal(duracao.toMinutes());
					textTempo.setText(minutosEmBigDecimal.toString());
				}
				
				
			}

		});

	}
	
	public String formatarData(LocalDate data) {
	return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
	}
	
	public String formatarHora(LocalTime hora) {
		return hora.format(DateTimeFormatter.ofPattern("HH:mm")).toString();
	}
	

	// Verifica se todos os campos foram preenchidos e estabelece uma condição para cada um
	private boolean validarFormulario() {

		boolean valido = true;

		if (textPlaca.getText().trim().length() == 0) {
			labelPlaca.setForeground(Color.red);
			valido = false;
		} else if (textModelo.getText().trim().length() == 0) {
			labelModelo.setForeground(Color.red);
			valido = false;
		}

		return valido;

	}

	private void limparFormulario() {
		textPlaca.setText("");
		textModelo.setText("");
		textPlaca.requestFocus(); // após cadastrar o veículo, volta o curso do mouse ao campo de inserção da placa
									
	}
	
	

}
