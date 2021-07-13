package br.com.estacionamento.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {

	private String codigo;
	private String placa;
	private String modelo;
	private LocalDate dataEntrada;
	private LocalTime horaEntrada;
	private LocalDate dataSaida;
	private LocalTime horaSaida;
	private double tempo;
	private double valorPago;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	
	@Override
	public String toString() {
		return codigo + ";" + placa + ";" + modelo + ";" + dataEntrada + ";"
				+ horaEntrada + ";" + dataSaida + ";" + horaSaida + ";" + tempo + ";" + valorPago;
	}
	

	public String toStringEntrada() {
		return codigo + ";" + placa + ";" + modelo + ";" + dataEntrada + ";"
				+ formatarHora(horaEntrada) + ";0;0;0;0";
	} 
	
	public String formatarHora(LocalTime hora) {
		return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
	
//	public String formatarData(LocalDate data) {
//		return data.format(DateTimeFormatter.ofPattern("DD/MM/YYYY"));
//	}

}
