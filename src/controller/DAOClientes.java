package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import modelo.Cliente;

public class DAOClientes {
	private static HashMap<Integer, Cliente> hmClientes = new HashMap<Integer, Cliente>();
	private static int qtdClientes;
	
	public static void addCliente(Cliente cliente) {
		hmClientes.put(cliente.getCod(), cliente);
		qtdClientes++;
	}
	
	public static int getQtdClientes() {
		return qtdClientes;
	}
	
	public static void addHoras(int cod, int horas) {
		Cliente cliente = new Cliente();
		cliente = hmClientes.get(cod);
		int total = cliente.getHorasCompradas() + horas;
		cliente.setHorasCompradas(total);
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
	
	public static void off(int cod) {
		Cliente cliente = new Cliente();
		cliente = hmClientes.get(cod);
		cliente.setAtivo(false);
	}
}
