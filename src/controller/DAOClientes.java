package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import modelo.Cliente;
import modelo.Computador;

public class DAOClientes {
	private static HashMap<Integer, Cliente> hmClientes = new HashMap<Integer, Cliente>();
	private static int qtdClientes;
	
	public static void addCliente(String nome, String tel, String email, int horasCompradas, int computador) {
		Cliente cliente = new Cliente(qtdClientes++, nome, tel, email, horasCompradas, computador);
		hmClientes.put(cliente.getCod(), cliente);
		DAOComputadores.addHoras(cliente.getComputador(), cliente.getHorasCompradas());
	}
	
	public static int getQtdClientes() {
		return qtdClientes;
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
}
