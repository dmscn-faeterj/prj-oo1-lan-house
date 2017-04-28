package controller;

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
}
