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
		long[] A;
		
		//initialization: all elements 0 with length n
		public BinaryIndexTree(int n){
			this.A=new long[n+1];
		}
		
		//get the cumulative sum until pos, inclusive 
		public long query(int pos){
			pos+=1;
			long sum=0;
			while (pos>0 && pos<A.length){
				sum+=this.A[pos];
				pos-=(pos&-pos);
			}
			return sum;
		}
		
		//change the element at pos, therefore changing later elements
		public void update(int pos,long increment){
			pos+=1;
			while (pos<this.A.length){
				this.A[pos]+=increment;
				pos+=(pos&-pos);
			}
		}
		
		//debug: help to print each query i
		public void printT(){
			for (int i=1;i<A.length;i++){
				System.out.print(this.query(i-1)+" ");
			}
			System.out.println();
		}
	}
	
	//Suppose we have an int[] A, 
	//This structure supports:
	//1:Update(a,b,v):for any a<=k<=b, A[k]+=v;
	//2:Query(a,b):return sum(a<=k<=b)A[k]
	public static class rangeBinaryIndexTree{
		//B1 support point query (returns A[i]), range update (for a<=k<=b, all A[k]+=v)
		//B2 is 2nd order correction
		BinaryIndexTree B1,B2;
		
		public rangeBinaryIndexTree(int n){
			this.B1=new BinaryIndexTree(n);
			this.B2=new BinaryIndexTree(n);
		}
		
		public void rangeUpdate(int a,int b,long v){
			B1.update(a, v); B1.update(b+1, -v);
			B2.update(a, v*(a-1)); B2.update(b+1, -v*b);
		}
		
		//return Sum(A[0]..A[b])
		public long rangeQuery(int b){
			return B1.query(b)*b-B2.query(b);
		}
		
		public long rangeQuery(int a,int b){
			return this.rangeQuery(b)-this.rangeQuery(a-1);
		}
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for (int t=0;t<T;t++){
			int N=in.nextInt(); int C=in.nextInt();
			rangeBinaryIndexTree B=new rangeBinaryIndexTree(N);
			for (int c=0;c<C;c++){
				int op=in.nextInt();
				if (op==0){
					int p=in.nextInt();int q=in.nextInt();long v=in.nextInt();
					B.rangeUpdate(p-1,q-1,v);
				}
				else {
					int p=in.nextInt();int q=in.nextInt();
					System.out.println(B.rangeQuery(p-1,q-1));
				}
			}
		}
		
	}
}
