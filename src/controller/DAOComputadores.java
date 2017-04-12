package controller;

import java.util.HashMap;

import modelo.Computador;

public class DAOComputadores {
	private static HashMap<Integer, Computador> hmComputadores = new HashMap<Integer, Computador>();
	
	public static void addComputador(Computador computador) {
		hmComputadores.put(computador.getCod(), computador);
	}
}
