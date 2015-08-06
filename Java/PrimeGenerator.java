import java.util.*;
import java.io.FileReader;
import java.lang.*;

public class Main {
	
	public static void printBoolean(boolean[] B){
		for (boolean b:B) System.out.print(b+" ");
		System.out.println();
	}
	
	public static void printPrime(int m,int n){
		//A[i]==true iff i is prime
		int Alen=(int)Math.ceil(Math.sqrt(n))+1;
		boolean[] A=new boolean [Alen];
		boolean[] B=new boolean [n-m+1];//B[i] stands for m+i;
		for (int i=0;i<A.length;i++) A[i]=true;
		for (int i=0;i<B.length;i++) B[i]=true;
		//go through every element represented by A, start from 2
		for (int i=2;i<A.length;i++){
			//if i happens to be a prime
			if (A[i]){
				//System.out.println("alpha "+i);
				int sweepA=i;
				//1)sweep all multiples of i in A
				while (sweepA<A.length){
					A[sweepA]=false;
					sweepA+=i;
				}
				//2)find the first potential element of multiple of i in B
				int sweepB=(int)(i*Math.ceil(m/(double)(i)));
				//System.out.println("initial Sweep B"+sweepB);
				//3)sweep all of B
				while (sweepB<=n){if (sweepB!=i) {B[sweepB-m]=false;} sweepB+=i;}
				
			}
		}
		for (int i=0;i<B.length;i++){
			if (B[i] && i+m>=2) {System.out.println(i+m);}
		}
		System.out.println();
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		//Scanner in=new Scanner(new FileReader("myTest.txt"));
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for (int t=0;t<T;t++){
			int m=in.nextInt();int n=in.nextInt();
			printPrime(m,n);
		}
	}
	
}
