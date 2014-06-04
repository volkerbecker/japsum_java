/**
 * 
 */
package becker.japsum.solver;

/**
 * @author sax
 * Enthaelt das Spielfeld bestehend aus Summenbloecken und Feldelementen, stellt methoden zur Loesung bereit
 */
public class Playfield {
	
	/**
	 * Komnstruktor, erschafft ein spielfeld
	 * @param playfieldsize größe des Spielfeldes
	 * @param maxnumber maximalwert der Eintraege
	 */
	public Playfield(byte playfieldsize,byte maxnumber) {
		this.playfieldsize=playfieldsize;
		this.maxnumber=maxnumber;
		Feldelement.setMaxnumber(maxnumber);
		Feld = new Feldelement[playfieldsize][playfieldsize];
		for(byte i=0;i<playfieldsize;++i) {
			for(byte j=0;j<playfieldsize;++j) {
				Feld[i][j]=new Feldelement();
			}
		}  // alle Feldelemente "erschaffen"
	}
	
	public Feldelement getFieldElement(byte zeile,byte spalte) {
		return Feld[zeile][spalte];
	}
	
	public void setFixedValueAt(byte zeile,byte spalte,byte value) {
		for(byte i=0;i<playfieldsize;++i) {
			Feld[zeile][i].setValueImpossible(value);
			Feld[i][spalte].setValueImpossible(value);
		} //verbiete den wert in der gesamten Zeile und Spalte
		Feld[zeile][spalte].setFixedValue(value); // Wert in der Zelle setzen, Wert in dieser Zelle wieder erlauben
	}
	
	
	private byte playfieldsize,maxnumber;
	private Feldelement[][] Feld; // Array of fieldelements
//	private summen // hier bloecke fuer die Summen einfuegen. 

}
