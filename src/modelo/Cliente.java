package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
	private int cod;
	private String nome;
	private String tel;
	private String email;
	private int horasCompradas;
	private String horaInicial;
	private boolean ativo = false;
	private int computador;
	
	//construtores
	public Cliente() {	}
	
	public Cliente(int cod, int horasCompradas) {
		this.cod = cod;
		this.horasCompradas = horasCompradas;
		
		Date data = new Date();
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		this.horaInicial = df.format(data);
		
		this.ativo = true;
	}
	
	public Cliente(int cod, String nome, String tel, String email, int horasCompradas, int computador) {
		this.cod = cod;
		this.nome = nome;
		this.tel = tel;
		this.email = email;
		this.horasCompradas = horasCompradas;
		this.computador = computador;
		
		Date data = new Date();
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		this.horaInicial = df.format(data);
		
		this.ativo = true;
	}
	
	//getters n setters

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHorasCompradas() {
		return horasCompradas;
	}

	public void setHorasCompradas(int horasCompradas) {
		this.horasCompradas = horasCompradas;
	}
	
	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getComputador() {
		return computador;
	}

	public void setComputador(int computador) {
		this.computador = computador;
	}
}
