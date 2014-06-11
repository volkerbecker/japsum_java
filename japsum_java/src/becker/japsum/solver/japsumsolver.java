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
		
//		hblocks.add(new byte[]{7,8});
//		hblocks.add(new byte[]{6,7});
//		hblocks.add(new byte[]{11});
//		hblocks.add(new byte[]{2,8});
//		hblocks.add(new byte[]{10});
//		hblocks.add(new byte[]{5,4,3});
//		
//		vblocks.add(new byte[]{4,2,5});
//		vblocks.add(new byte[]{11,3});
//		vblocks.add(new byte[]{1,9});
//		vblocks.add(new byte[]{5,6});
//		vblocks.add(new byte[]{13,2});
//		vblocks.add(new byte[]{6,3,1});
//		
		hblocks.add(new byte[]{20,25});
		hblocks.add(new byte[]{5,30,4});
		hblocks.add(new byte[]{27,6,5,2});
		hblocks.add(new byte[]{12,24,7});
		hblocks.add(new byte[]{20,25});
		hblocks.add(new byte[]{25,20});
		hblocks.add(new byte[]{13,1,4,13});
		hblocks.add(new byte[]{6,2,37});
		hblocks.add(new byte[]{3,10,5,15});
		hblocks.add(new byte[]{36,6,3});
		hblocks.add(new byte[]{1,6,2,28});
		
		vblocks.add(new byte[]{22,17});
		vblocks.add(new byte[]{5,28,1});
		vblocks.add(new byte[]{3,8,30});
		vblocks.add(new byte[]{13,12,15});
		vblocks.add(new byte[]{15,3,21});
		vblocks.add(new byte[]{30,1,11});
		vblocks.add(new byte[]{15,8,14});
		vblocks.add(new byte[]{30,8,4});
		vblocks.add(new byte[]{10,19,15});
		vblocks.add(new byte[]{1,37,7});
		vblocks.add(new byte[]{13,8,24});
		
//		hblocks.add(new byte[]{2,4});
//		hblocks.add(new byte[]{12,3});
//		hblocks.add(new byte[]{4,3,7});
//		hblocks.add(new byte[]{15});
//		hblocks.add(new byte[]{8,1,4});
//		hblocks.add(new byte[]{1,2,5});
//		
//		vblocks.add(new byte[]{11,4});
//		vblocks.add(new byte[]{3,6});
//		vblocks.add(new byte[]{13,2});
//		vblocks.add(new byte[]{3,3});
//		vblocks.add(new byte[]{11});
//		vblocks.add(new byte[]{15});
		
		
		
		Playfield riddle=new Playfield((byte)11, (byte)9, hblocks, vblocks);
		
		riddle.displayPlayfield();
		
		if(riddle.solve() ){
			riddle.displayPlayfield();
		} else
		{
			riddle.displayPlayfield();
			System.out.println("The algorithm did not found a solution, please contact the author!");
		}
		
	//	riddle.tryLines(true, 2);
	//	riddle.displayPlayfield();
		
//		sumPermutations.setMaxNumber((byte)9,(byte)11);
//		sumPermutations tessuperm = new sumPermutations(new byte[]{37,1,2});
//		System.out.println(tessuperm.getSize());
		
//		sumPermutations.setMaxNumber((byte)5,(byte)6);
//		sumPermutations testsp=new sumPermutations(new byte[]{12,3});
//		testsp.findCompatibleBlogs();
//		
//		ArrayList<ArrayList<byte[] > > testre = testsp.findCompatibleBlogs(); ;
//		
//		for(int i=0;i<testre.size();++i) {
//			ArrayList<byte[] > s1 = testre.get(i);
//			for(int j=0;j<s1.size();++j) {
//				byte[] s2=s1.get(j);
//				for(int k=0;k<s2.length;++k) {
//					System.out.print(s2[k]);
//				}
//				System.out.print("|");
//			}
//			System.out.println("");
//		}
	}
}
