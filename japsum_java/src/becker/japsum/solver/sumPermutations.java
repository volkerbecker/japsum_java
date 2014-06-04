package becker.japsum.solver;

import java.util.ArrayList;

/**
 * erzeugt alle Permutastionen fuer eine Zeile Spalte mit gegebenen summenbloecken
 * @author sax
 *
 */
public class sumPermutations {
	
	public sumPermutations(byte[] sumsin) {
		sums = sumsin.clone();
	}
	
	
	
	private int recentpermutation; // sagt welche permutation als letztes zurückgegeben wurde.
	private byte[] sums; // enthaelt die Summen
	ArrayList<byte[]> zeile;  // enthaelt alle moeglichen Zeilen fuer die gegebenen Summen	
	
}
