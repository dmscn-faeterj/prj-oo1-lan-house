package controller;

import java.util.HashMap;
import java.util.List;

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
	
	public static HashMap<Integer, Computador> getAll() {
		return hmComputadores;
	}
}
