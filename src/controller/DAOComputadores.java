package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import modelo.Computador;

public class DAOComputadores {
	private static HashMap<Integer, Computador> hmComputadores = new HashMap<Integer, Computador>();
	private static int qtdComp;
	
	public static void addComputador(Computador computador) {
		hmComputadores.put(computador.getCod(), computador);
		qtdComp++;
	}
	
	public static int getQtdComp() {
		return qtdComp;
	}
	
	public static Computador getComputador(int cod) {
		return hmComputadores.get(cod);
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
