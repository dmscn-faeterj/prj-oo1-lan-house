package controller;

import java.util.HashMap;

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
}
