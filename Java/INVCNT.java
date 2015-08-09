import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	//in place merge sort that returns the number of inverse pairs
	public static long mergeSort(int[] A,int left, int right){
		//base case
		if (left==right) return 0;
		int mid=(left+right)/2;
		//divide original segment into [left,mid] and [mid+1,right]
		long output=0;
		output+=mergeSort(A,left,mid); output+=mergeSort(A,mid+1,right);
		int[] B=new int[right-left+1];
		int i=left; int j=mid+1; int k=0;
		while (i<=mid && j<=right){
			if (A[i]<=A[j]){B[k++]=A[i++];output+=(j)-mid-1;}
			else {B[k++]=A[j++];}
		}
		while (i<=mid) {
			B[k++]=A[i++];output+=j-mid-1;
		}
		while (j<=right){
			B[k++]=A[j++];
		}
		for (int k1=0;k1<B.length;k1++){
			A[k1+left]=B[k1];
		}
		return output;
	}
	
	public static void printL(int[] L){
		for (int a:L) System.out.print(a+" ");
		System.out.println();
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		Scanner in=new Scanner(System.in);
		//int[] A=new int[]{};
		//System.out.println(mergeSort(A,0,A.length-1));
		
		int T=in.nextInt();
		for (int t=0;t<T;t++){
			int n=in.nextInt();
			int[] A=new int[n];
			for (int i=0;i<n;i++) A[i]=in.nextInt();
			System.out.println(mergeSort(A,0,n-1));
		}
		
	}
}
