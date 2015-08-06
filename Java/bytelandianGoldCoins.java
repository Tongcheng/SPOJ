import java.util.*;
import java.io.FileReader;
import java.lang.*;

public class Main {
	
	//returns the max possible value for a N-value coin
	//Also add its value to HashMap H
	public static long getN(long n,HashMap<Long,Long> H){
		//base case
		if (H.containsKey(n)) return H.get(n);
		
		long N4=H.containsKey(n/4)?H.get(n/4):getN(n/4,H);
		long N3=H.containsKey(n/3)?H.get(n/3):getN(n/3,H);
		long N2=H.containsKey(n/2)?H.get(n/2):getN(n/2,H);
		
		long Nval=Math.max(N2+N3+N4,n);
		H.put(n,Nval);
		return Nval;
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		//Scanner in=new Scanner(new FileReader("myTest.txt"));
		Scanner in=new Scanner(System.in);
		//H: Value of coin-> max possible value get from this coin
		HashMap<Long,Long> H=new HashMap<Long,Long>();
		for (long i=0;i<12;i++) H.put(i,i);
		
		while (in.hasNext()){
			long n=in.nextLong();
			long nVal=getN(n,H);
			System.out.println(nVal);
		}
		
		/**
		//long startTime = System.currentTimeMillis();
		//long N=1000000000;
		//long endTime   = System.currentTimeMillis();
        //long totalTime = endTime - startTime;
        //System.out.println("totalTime is "+totalTime);
		*/
		
		/**
		//long startTime = System.currentTimeMillis();
		
		
        //long endTime   = System.currentTimeMillis();
        //long totalTime = endTime - startTime;
        //System.out.println("totalTime is "+totalTime);
		*/
	}
	
}
