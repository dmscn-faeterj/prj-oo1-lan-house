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
import modelo.Computador;

public class DAOComputadores {
	private static HashMap<Integer, Computador> hmComputadores = new HashMap<Integer, Computador>();
	private static int qtdComp = 0;
	
	public static void addComputador(String so) {
		Computador computador = new Computador(qtdComp++, so);
		hmComputadores.put(computador.getCod(), computador);
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
		List<Computador> lstComputadores = new ArrayList();
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
		List<Computador> lstComputadores = new ArrayList();
		
		lstComputadores = getAll();
		
		for(Computador computador : lstComputadores) {
			horas += computador.getHorasLigado();
		}
		
		return horas;
	}
	
	public static void salvar() {
		File arq = new File("computadores.dat");
		
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
}
