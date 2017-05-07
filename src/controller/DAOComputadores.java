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

import modelo.Computador;

public class DAOComputadores {
	private static HashMap<Integer, Computador> hmComputadores = new HashMap<Integer, Computador>();
	private static int qtdComp = 0;
	
	public static void addComputador(String so) {
		Computador computador = new Computador(qtdComp++, so);
		hmComputadores.put(computador.getCod(), computador);
	}
	
	public static void addComputador(Computador computador) {
		hmComputadores.put(computador.getCod(), computador);
		qtdComp++;
	}
	
	public static void dropComputador(int cod) {
		Set<Integer> chaves = hmComputadores.keySet();
		int aux = -1;
		
		for(int chave : chaves) {
			if(chave == cod) {
				aux = cod;
			}
		}
		
		if(aux != -1) {
			hmComputadores.remove(aux);	
		}
	}
	
	public static int getQtdComp() {
		return qtdComp;
	}
	
	public static Computador getComputador(int cod) {
		return hmComputadores.get(cod);
	}
	
	public static void setUser(String user, int cod) {
		Computador computador = getComputador(cod);
		computador.setUltCliente(user);
	}
	
	public static List<Computador> getAll() {
		List<Computador> lstComputadores = new ArrayList<Computador>();
		Set<Integer> chaves = hmComputadores.keySet();
		
		for(int chave : chaves) {
			lstComputadores.add(hmComputadores.get(chave));
		}
		
		return lstComputadores;
	}
	
	public static void addHoras(int cod, int horas) {
		Computador computador = new Computador();
		computador = hmComputadores.get(cod);
		
		int aux = computador.getHorasLigado(); 
		computador.setHorasLigado(aux + horas);
	}
	
	public static int getTotalHoras() {
		int horas = 0;
		List<Computador> lstComputadores = new ArrayList<Computador>();
		
		lstComputadores = getAll();
		
		for(Computador computador : lstComputadores) {
			horas += computador.getHorasLigado();
		}
		
		return horas;
	}
	
	public static void salvar(String pasta) {
		File arq = new File(pasta + "/computadores.dat");
		
		try {
			FileWriter fWriter = new FileWriter(arq, true);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			
			Computador c = new Computador();
			Set<Integer> chaves = hmComputadores.keySet();
			
			for(int chave : chaves) {
				c = hmComputadores.get(chave);
				bWriter.write(c.getCod() + "\n");
				bWriter.write(c.getSo() + "\n");
				bWriter.write(c.getHorasLigado() + "\n");
				bWriter.write(c.getUltCliente() + "\n");
				bWriter.write(c.getAtivo() + "\n");
			}
			
			bWriter.close();
			fWriter.close();
			
		} catch (IOException e) {
			System.out.println("Erro ao tentar salvar os computadores");
			e.printStackTrace();
		}
	}
	
	public static void ler(String pasta) {
		try {
			Scanner in = new Scanner(new FileReader(pasta + "/computadores.dat"));
			String line;
			
			int aux = 1;
			Computador c = new Computador();
			
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
						c.setSo(line);
						break;
						
					case 3:
						c.setHorasLigado(Integer.parseInt(line));
						break;
						
					case 4:
						c.setUltCliente(line);
						break;
						
					case 5:
						c.setAtivo(Boolean.parseBoolean(line));
						addComputador(c);
						c = new Computador();
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
