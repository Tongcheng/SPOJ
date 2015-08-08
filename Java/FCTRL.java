import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	/**Analogy:I have int[] Z, I support two operations:
	 * 1)Query (int i), returns the sum(j=0..i)Z[j]
	 * 2)Update(int i, int increment): Z[i]+=increment; 
	 *   So that all j>i is incremented by increment.
	 *   */
	/**Interface is pos [0..n)*/
	public static class BinaryIndexTree{
		int[] A;
		
		//initialization: all elements 0 with length n
		public BinaryIndexTree(int n){
			this.A=new int[n+1];
		}
		
		//get the cumulative sum until pos, inclusive 
		public int query(int pos){
			pos+=1;
			int sum=0;
			while (pos>0){
				sum+=this.A[pos];
				pos-=(pos&-pos);
			}
			return sum;
		}
		
		//change the element at pos, therefore changing later elements
		public void update(int pos,int increment){
			pos+=1;
			while (pos<this.A.length){
				this.A[pos]+=increment;
				pos+=(pos&-pos);
			}
		}
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for (int t=0;t<T;t++){
			System.out.println(getTrailingZeros(in.nextInt()));
		}
		
	}
}
