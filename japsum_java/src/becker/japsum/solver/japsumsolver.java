package becker.japsum.solver;

import java.util.ArrayList;

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
		
		
		
		sumPermutations.setMaxNumber((byte)9,(byte)11);
		sumPermutations tessuperm = new sumPermutations(new byte[]{5,7,6});
		
		ArrayList<ArrayList<byte[] > > compbloglist;
		tessuperm.calculateAllPermutyations();
		
		compbloglist=tessuperm.findCompatibleBlogs();
		for(int j=0;j<compbloglist.size();++j) {
			for(int k=0;k<compbloglist.get(j).size();k++) {
				byte[] ab=compbloglist.get(j).get(k);
				for(int l=0;l<ab.length;++l) {
					System.out.print(ab[l]);
				}
				System.out.print("|");
			}
			System.out.println("");
		}
		

	}
}
