package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import modelo.Cliente;

public class DAOClientes {
	private static HashMap<Integer, Cliente> hmClientes = new HashMap<Integer, Cliente>();
	private static int qtdClientes = 0;
	
	public static void addCliente(String nome, String tel, String email, int horasCompradas, int computador) {
		Cliente cliente = new Cliente(qtdClientes++, nome, tel, email, horasCompradas, computador);
		DAOComputadores.setUser(nome, computador);
		hmClientes.put(cliente.getCod(), cliente);
		DAOComputadores.addHoras(cliente.getComputador(), cliente.getHorasCompradas());
	}
	
	public static void dropCliente(int cod) {
		Set<Integer> chaves = hmClientes.keySet();
		int aux = -1;
		
		for(int chave : chaves) {
			if(chave == cod) {
				aux = chave;
			}
		}
		
		if(aux != -1) {
			hmClientes.remove(aux);
		}
	}
	
	public static int getQtdClientes() {
		return qtdClientes;
	}
	
	public static Cliente getCliente(int cod) {
		return hmClientes.get(cod);
	}
	
	public static void addHoras(int cod, int horas) {
		Cliente cliente = new Cliente();
		cliente = hmClientes.get(cod);
		int total = cliente.getHorasCompradas();
		cliente.setHorasCompradas(total + horas);
		DAOComputadores.addHoras(cliente.getComputador(), horas);
	}
	
	public static List<Cliente> getAll() {
		List<Cliente> lstClientes = new ArrayList<Cliente>();
		Set<Integer> chaves = hmClientes.keySet();
		
		for(int chave : chaves) {
			lstClientes.add(hmClientes.get(chave));
		}
		
		return lstClientes;
	}
	
	public static boolean isAtivo(int cod) {
		Cliente cliente = new Cliente();
		cliente = hmClientes.get(cod);
		return cliente.getAtivo();
	}
	
	public static int getCodByName(String nome) {
		int cod = -1;
		Set<Integer> chaves = hmClientes.keySet();
		
		for(int chave : chaves) {
			if(hmClientes.get(chave).getNome().equals(nome)) {
				cod = hmClientes.get(chave).getCod();
				break;
			}
		}
		
		return cod;
	}
	
	public static void off(int cod) {
		Cliente cliente = new Cliente();
		cliente = hmClientes.get(cod);
		cliente.setAtivo(false);
	}
	
	public static void salvar() {
		File arq = new File("clientes.dat");
		
		try {
			FileWriter fWriter = new FileWriter(arq, true);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			
			Cliente c = new Cliente();
			Set<Integer> chaves = hmClientes.keySet();
			
			for(int chave : chaves) {
				c = hmClientes.get(chave);
				bWriter.write(c.getCod() + "\n");
				bWriter.write(c.getNome() + "\n");
				bWriter.write(c.getTel() + "\n");
				bWriter.write(c.getEmail() + "\n");
				bWriter.write(c.getHorasCompradas() + "\n");
				bWriter.write(c.getHoraInicial() + "\n");
				bWriter.write(c.getComputador() + "\n");
			}
			
			bWriter.close();
			fWriter.close();
			
		} catch (IOException e) {
			System.out.println("Erro ao tentar salvar os clientes");
			e.printStackTrace();
		}
		
	}
}
