/**
 * 
 */
package becker.japsum.solver;

import java.util.ArrayList;

import org.omg.CORBA.BooleanSeqHolder;

/**
 * @author sax
 * Enthaelt das Spielfeld bestehend aus Summenbloecken und Feldelementen, stellt methoden zur Loesung bereit
 */
public class Playfield {
	
	/**
	 * Komnstruktor, erschafft ein spielfeld
	 * @param playfieldsize gr��e des Spielfeldes
	 * @param maxnumber maximalwert der Eintraege
	 */
	public Playfield(byte playfieldsize,byte maxnumber,ArrayList<byte[] > ihblocks, ArrayList<byte[] > ivblocks) {
		this.playfieldsize=playfieldsize;
		this.maxnumber=maxnumber;
		if(ihblocks.size()!=playfieldsize || ivblocks.size()!=playfieldsize) {
			throw new IllegalArgumentException("number of sum blogs must equal number of lines/collums");
		}
		Feldelement.setMaxnumber(maxnumber);
		Feld = new Feldelement[playfieldsize][playfieldsize];
		
		hblocks=ihblocks; //die uebergebenen Werte als Objektwerte nutzen
		vblocks=ivblocks;
		
		ihblocks=null;
		ivblocks=null; // zugriff auf die Objektwerte von au�en unmoeglich machen
		sumPermutations.setMaxNumber(this.maxnumber, this.playfieldsize);
		for(byte i=0;i<playfieldsize;++i) {
			for(byte j=0;j<playfieldsize;++j) {
				Feld[i][j]=new Feldelement(); // alle Feldelemente "erschaffen"
			}
			vpertubations.add(new sumPermutations(vblocks.get(i))); // Die moeglichen Permutationen vorberechnen
			hpertubations.add(new sumPermutations(hblocks.get(i)));
		}  
	}
	
	public void displayPlayfield() {
		for(int i=0;i<playfieldsize;++i) { 
			for(int j=0;j<playfieldsize;++j) {
				System.out.print(" "+getFieldElement((byte)i, (byte)j).getValue());
				if(getFieldElement((byte)i, (byte)j).isFixed()) {
					System.out.print(".|");
				}
				else{
					System.out.print(" |");
				}
			}
			System.out.println("");
			for(int j=0;j<playfieldsize;++j) {
				System.out.print("----");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}
	
	public Feldelement getFieldElement(byte zeile,byte spalte) {
		return Feld[zeile][spalte];
	}
	
	public void setFixedValueAt(byte zeile,byte spalte,byte value) {
		if(value !=0) {
			for(byte i=0;i<playfieldsize;++i) {
				Feld[zeile][i].setValueImpossible(value);
				Feld[i][spalte].setValueImpossible(value);
			} //verbiete den wert in der gesamten Zeile und Spalte
		}
		Feld[zeile][spalte].setFixedValue(value); // Wert in der Zelle setzen, Wert in dieser Zelle wieder erlauben
	}
	
	public boolean tryLines(boolean hv,int line) {
		sumPermutations lineToTest;
		boolean retvalue=false;
		
		ArrayList<boolean[]> inThislinepossible=new ArrayList<boolean[]>();
		for(int i=0;i<playfieldsize;++i) {
			inThislinepossible.add(new boolean[maxnumber+1]);
		}
		
		if(hv)
			lineToTest = hpertubations.get(line);
		else
			lineToTest = vpertubations.get(line);
		
		byte[] recentpermutation= new byte[playfieldsize];
		
		int zeile=0;
		int spalte=0;
		while(lineToTest.getnextpermuation(recentpermutation)) {
			for(int i=0;i<playfieldsize;++i) {
				if(hv) {
					zeile=line;
					spalte=i;
					} else {
						spalte=line;
						zeile=i;
					}
				
				if(!Feld[zeile][spalte].isValuePossible(recentpermutation[i])){
					lineToTest.removeRecentPermutation();
					retvalue=true;
					break;
				} else {
					inThislinepossible.get(i)[recentpermutation[i]]=true;
				}	
			}
		}
		
		for(int i=0;i<playfieldsize;++i) {
			for(byte j=0;j<=maxnumber;++j) {
				if(!inThislinepossible.get(i)[j]) {
					if(hv) {
						zeile=line;
						spalte=i;
						} else {
							spalte=line;
							zeile=i;
						}
					if(Feld[zeile][spalte].isValuePossible(j)){
						retvalue = true;
						Feld[zeile][spalte].setValueImpossible(j);
						byte[] wert = new byte[1];
						if(Feld[zeile][spalte].isValueAlreadyFixed(wert) ) {
							this.setFixedValueAt((byte)zeile, (byte)spalte, wert[0]);
						}
					}
					
				}
			}
		}
				
	return retvalue;	
	}
	
	public boolean solve() {
		boolean somthinChanges=false;
		
		do{
			somthinChanges=false;
			for(int i=0;i<playfieldsize;++i) {
				somthinChanges |= tryLines(true,i);
				//displayPlayfield();
				somthinChanges |= tryLines(false,i);
				//displayPlayfield();
			}
			//displayPlayfield();
		
			for(int i=0;i<playfieldsize;++i) {
				for(int j=0;j<playfieldsize;++j) {
					byte[] wert= new byte[1];
					if(!Feld[i][j].isFixed()) {
						if(Feld[i][j].isValueAlreadyFixed(wert) ) {
							this.setFixedValueAt((byte)i, (byte)j, wert[0]);
							somthinChanges=true;
						}
					}
				}
			}
			//displayPlayfield();
		} while(somthinChanges);
		boolean retalue=true;
		for(int i=0;i<playfieldsize;++i) {
			for(int j=0;j<playfieldsize;++j) {
				retalue &= Feld[i][j].isFixed();
			}
		}
		return retalue;
	}
	
	private byte playfieldsize,maxnumber;
	private Feldelement[][] Feld; // Array of fieldelements
	
	private ArrayList<byte[]> hblocks;
	private ArrayList<byte[]> vblocks;
	
	private ArrayList<sumPermutations> hpertubations=new ArrayList<sumPermutations>();
	private ArrayList<sumPermutations> vpertubations=new ArrayList<sumPermutations>();
	
	
//	private summen // hier bloecke fuer die Summen einfuegen. 

}
