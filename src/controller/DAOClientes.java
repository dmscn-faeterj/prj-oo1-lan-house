package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
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
		DAOComputadores.setStatus(true, computador);
	}
	
	public static void addCliente(Cliente cliente) {
		hmClientes.put(cliente.getCod(), cliente);
		qtdClientes++;
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
	
	public static void turnOff(int cod) {
		Cliente c = hmClientes.get(cod);
		c.setAtivo(false);
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
	
	public static void salvar(String pasta) {
		File arq = new File(pasta + "/clientes.dat");
		
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
	
	public static void ler(String pasta) {
		try {
			Scanner in = new Scanner(new FileReader(pasta + "/clientes.dat"));
			String line;
			
			int aux = 1;
			Cliente c = new Cliente();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				System.out.println("entrou");
				System.out.println(line);
				System.out.println("aux: " + aux);
				
				switch(aux) {
					case 1: 
						c.setCod(Integer.parseInt(line));
						break;
						
					case 2: 
						c.setNome(line);
						break;
						
					case 3: 
						c.setTel(line);
						break;
						
					case 4:
						c.setEmail(line);
						break;
						
					case 5:
						c.setHorasCompradas(Integer.parseInt(line));
						break;
						
					case 6:
						c.setHoraInicial(line);
						break;
						
					case 7:
						c.setComputador(Integer.parseInt(line));
						addCliente(c);
						c = new Cliente();
						aux = 0;
						break;
				}
				aux++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao tentar ler o arquivo computadores.dat");
			e.printStackTrace();
		}
	}
}
