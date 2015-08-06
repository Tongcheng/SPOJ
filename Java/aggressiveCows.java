import java.util.*;
import java.lang.*;

public class Main {
	
	//returns true iff it's possible to insert C cows with min distance x
	public static boolean feasible(int[] A,int x,int C){
		int counter=1; int curDistance=0;//insert a cow into A[0] by default
		for (int i=1;i<A.length;i++){
			curDistance+=A[i]-A[i-1];
			if (curDistance>=x){
				counter++; curDistance=0;
			}
			if (counter>=C) break;
		}
		return counter>=C;
	}
	
	//precondition: A is already sorted
	//low always feasible, since C>=2
	public static int minDistance(int[] A, int C){
		int high=A[A.length-1]-A[0]; int low=high;
		for (int i=1;i<A.length;i++) low=Math.min(low, A[i]-A[i-1]);
		while (low<high){
			int mid=low+(high-low+1)/2; // mid for last of fst boolean segment
			// mid for first of snd boolean segment: mid=low + (high-low)/2
			if (feasible(A,mid,C)) low=mid;
			else high=mid-1;
		}
		return low;
	}
	
	
	public static void main (String[] args) throws java.lang.Exception{
		
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for (int t=0;t<T;t++){
			int N=in.nextInt(); int C=in.nextInt();
			int[] A=new int[N];
			for (int n=0;n<N;n++) A[n]=in.nextInt();
			Arrays.sort(A);
			System.out.println(minDistance(A,C));
		}
		//int[] A=new int[]{1,2};
		//Arrays.sort(A);
		//System.out.println(minDistance(A,1));
		
	}
}
