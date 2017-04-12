package modelo;

public class Cliente {
	private int cod;
	private String nome;
	private String tel;
	private String email;
	private int horasCompradas;
	private Computador computador;
	
	//construtores
	public Cliente() {
	}
	
	public Cliente(int cod, int horasCompradas) {
		this.cod = cod;
		this.horasCompradas = horasCompradas;
	}
	
	public Cliente(int cod, String nome, String tel, String email, int horasCompradas, Computador computador) {
		this.cod = cod;
		this.nome = nome;
		this.tel = tel;
		this.email = email;
		this.horasCompradas = horasCompradas;
		this.computador = computador;
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

	public Computador getComputador() {
		return computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}
}
