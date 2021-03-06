package modelo;

public class Computador {
	private int cod;
	private String so;
	private int horasLigado;
	private boolean ativo = false;
	
	public Computador() {
	}
	
	public Computador(int cod, String so) {
		super();
		this.cod = cod;
		this.so = so;
	} //construtor
	
	//getters n setters
	
	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public int getHorasLigado() {
		return horasLigado;
	}

	public void setHorasLigado(int horasLigado) {
		this.horasLigado = horasLigado;
	}
	
	public boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
