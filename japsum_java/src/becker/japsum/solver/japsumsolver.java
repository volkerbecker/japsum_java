package becker.japsum.solver;

public class japsumsolver {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Playfield riddle = new Playfield((byte)6, (byte)5);
		
		Feldelement zelle;
		
		riddle.setFixedValueAt((byte)2,(byte)2,(byte)4);
		
		zelle=riddle.getFieldElement((byte)2, (byte)2);
		
	
		try{
			System.out.println("Wert steht fest: "+zelle.isValueAlreadyFixed());
		} 
		catch (IllegalStateException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		System.out.println("Wert ist: "+zelle.getValue());
		zelle=riddle.getFieldElement((byte)2,(byte)3);
		System.out.println("Wert steht fest: "+zelle.isValueAlreadyFixed());
		System.out.println("Wert 4 ist moeglich: "+zelle.isValuePossible((byte)4));
		System.out.println("Wert 3 ist moeglich: "+zelle.isValuePossible((byte)3));
		System.out.println("Wert ist: "+zelle.getValue());
		
		
	}
}
