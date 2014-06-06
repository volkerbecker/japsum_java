package becker.japsum.solver;

import java.util.ArrayList;

public class japsumsolver {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<byte[]> hblocks = new ArrayList<byte[]>();
		ArrayList<byte[]> vblocks = new ArrayList<byte[]>();
		
		hblocks.add(new byte[]{11,4});
		hblocks.add(new byte[]{11,1});
		hblocks.add(new byte[]{4,2,8});
		hblocks.add(new byte[]{4,11});
		hblocks.add(new byte[]{5,1});
		hblocks.add(new byte[]{13,2});
		
		vblocks.add(new byte[]{14,1});
		vblocks.add(new byte[]{7,8});
		vblocks.add(new byte[]{8,7});
		vblocks.add(new byte[]{2,4,3});
		vblocks.add(new byte[]{8});
		vblocks.add(new byte[]{13,2});
		
		
		Playfield riddle=new Playfield((byte)6, (byte)5, hblocks, vblocks);
		
		riddle.displayPlayfield();
		
		riddle.solve();
		
	//	riddle.tryLines(true, 2);
	//	riddle.displayPlayfield();
		
//		sumPermutations.setMaxNumber((byte)9,(byte)11);
//		sumPermutations tessuperm = new sumPermutations(new byte[]{37,1,2});
//		System.out.println(tessuperm.getSize());
		
		

	}
}
