package becker.japsum.solver;

import java.util.ArrayList;
import java.util.Collections;

/**
 * erzeugt alle Permutastionen fuer eine Zeile Spalte mit gegebenen summenbloecken
 * @author sax
 *
 */
public class sumPermutations {
	
	public static void setMaxNumber(byte _maxnumber,byte _playfieldsize) {
		maxnumber=_maxnumber;
		lineSize=_playfieldsize;
	}
	
	public sumPermutations(byte[] sumsin) {
		if(maxnumber==0) {
			throw new IllegalStateException("Call setMaxnumber(byte maxnumber) before creating a sumPermutatin");
		}
		sums = sumsin.clone();
	}
	
	private byte qsum(byte[] folge){
		byte result=0;
		for(int i=0;i<folge.length;++i) {
			result+=folge[i];
		}
		return result;
	}
	
	
	private void increment(byte[] M,int i)
	{
		if(i>0) {
			if( M[i]+1 < M[i-1]) {
				++ M[i];
			} else {
				M[i]=0;
				this.increment(M,i-1);
			}
		} else {
				++ M[i];
		}
	}

	private boolean incrementindex(int[] index,ArrayList<ArrayList<byte[]> > posibleblogs)
	{
		//vector<int>::reverse_iterator iter1 = index.rbegin();
		int[] mnb=new int[index.length];
		boolean nichtfertig;

		for(int i=0;i<mnb.length;++i) {
			mnb[i]=posibleblogs.get(i).size();
		}
		
		int i=mnb.length-1;
		
		do{
			if( index[i] < mnb[i]-1 || i==0 ) {
				nichtfertig=false;
				++index[i];
			} else {
				nichtfertig=true;
				index[i]=0;
				--i;
			}
		} while(nichtfertig);
		if( index[0] >= mnb[0] )
			return false;
		else
			return true;
	}
		
	public ArrayList<byte[] > possibleNumbers(int summe)
	{
		byte[] M;
		ArrayList<byte[]>  result = new ArrayList<byte[]>();
		ArrayList<Byte> tmp = new ArrayList<Byte>();

		result.clear();

		M = new byte[maxnumber];
		M[0]=0;
			while(M[0]<=maxnumber) {
				int momSumme=qsum(M);
				if(summe==momSumme) {
					tmp.clear();
					for(int j=0;j<M.length;++j) {
						if(M[j]!=0) {
							tmp.add(M[j]);
						}
				    }
					Collections.sort(tmp);
					byte[] tmpar=new byte[tmp.size()];
					for(byte i=0;i<tmpar.length;++i) {
						tmpar[i]=tmp.get(i);
					}
					result.add(tmpar);
				}
				increment(M,maxnumber-1);
			}
			return result;
	}

	public ArrayList<ArrayList<byte[] > > findCompatibleBlogs()
	{
		int N=sums.length; // Anzahl der Blöcke
		ArrayList<ArrayList<byte[]> > posibleblogs=new ArrayList<ArrayList<byte[]> >();
		ArrayList<ArrayList<byte[]> >  result =new ArrayList<ArrayList<byte[]> >();
		ArrayList<byte[]> zeile; // Zeile, bestehend aus sims.size Blöckewn
		boolean[] used;
		int[] index;

		boolean zeileok;

		index = new int[N];

		result.clear();


		//Bestimme fuer jede Summe alle moeglichen bloecke
		for(int i=0;i<N;++i)
		{
			posibleblogs.add(possibleNumbers(sums[i]));
		}

		do{
			used = new boolean[maxnumber]; // wird mit false initialisaiert
			zeile=new ArrayList<byte[]>();
			zeileok=true;
			for(int i=0;i<N;++i) {
					for(int j=0;j<posibleblogs.get(i).get(index[i]).length;++j) {
						byte anumber=posibleblogs.get(i).get(index[i])[j];
						if(!used[anumber-1]) {
							used[anumber-1]=true;
							//zeile.push_back(anumber); // Wenn Zahl nochj nicht benutzt dranhängen (old version)
						} else {
							zeileok=false;
							break; //ansonten test abrechen
						}
					}
					if(!zeileok) 
						break; // außere schleife verlassen
					else 
						zeile.add(posibleblogs.get(i).get(index[i])); //Den Block anhaengen
			}
			if(zeileok) result.add(zeile);
		} while(incrementindex(index,posibleblogs));
		return result;
	}
	
	public void calculateAllPermutyations(){
		zeilen = new ArrayList<byte[]>();
		int i=0;
		
		ArrayList<ArrayList<byte[] > > compatibleBlogs= findCompatibleBlogs();
		
		
		
		for(ArrayList<byte[] > blogset : compatibleBlogs ) {
			int digits = 0;
			for(byte[] block : blogset) {
				digits+=block.length;
			} // Anzahl der stellen bestimmen
			int freezeros=lineSize-digits-(blogset.size()-1);
			if(freezeros<0) continue; //dieser blog passt nicht in die Zeile
			
			int[] zerosAtPlace = new int[blogset.size()+2];
			zerosAtPlace[zerosAtPlace.length-1]=freezeros;
			
			
			int j=blogset.size()-1;
			int momplaceforzeros=0;
			do{
				byte[] zeile=new byte[lineSize];
				for(i=0;i<zerosAtPlace[momplaceforzeros];++i) {
					zeile[i]=0;
				}
				for(byte[] block : blogset) {  
					for(int k=0;k<block.length;++k) {
						zeile[i++]=block[k];
					}
					++momplaceforzeros;
					for(int k=0;k<zerosAtPlace[momplaceforzeros];++k) {
						zeile[i++]=0;
					}
					//zeile[i++]=0; // obligatorische Trennungsnull
				}
				i=0;
				zeilen.add(zeile);
				while(!LittleHelpers.next_permutation(blogset.get(j) ) ) {
					--j;
					if(j<0) break;
				}
			} while(j>=0);
		}
		//* For debugging and testing: 
		
		
		for(byte[] zeile : zeilen) {
			for(int k=0;k<zeile.length;++k) {
				System.out.print(zeile[k]);
			}
			System.out.println("");
		}
	}
	
	static private byte maxnumber,lineSize;
	private int recentpermutation; // sagt welche permutation als letztes zur�ckgegeben wurde.
	private byte[] sums; // enthaelt die Summen
	ArrayList<byte[]> zeilen;  // enthaelt alle moeglichen Zeilen fuer die gegebenen Summen	
	
	
	
	
}
