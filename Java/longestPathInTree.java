import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

	
	public static class Node{
		int id; int distance;
		HashSet<Node> Neighbor;
		public Node(int id){this.id=id;this.distance=0;this.Neighbor=new HashSet<Node>();}
	}
	
	public static void addEdge(Node[] N,int i,int j){
		N[i].Neighbor.add(N[j]);
		N[j].Neighbor.add(N[i]);
	}
	
	public static Node getFarNode(int nodeNum,Node start){
		LinkedList<Node> A=new LinkedList<Node>();
		boolean[] B=new boolean[nodeNum+1];
		Node output=null;
		A.add(start);
		int curDist=0; start.distance=0;
		while (!A.isEmpty()){
			Node head=A.pollFirst(); output=head;
			curDist=head.distance;
			B[head.id]=true;
			for (Node N:head.Neighbor){
				if (!B[N.id]) {A.addLast(N); N.distance=curDist+1;}
			}
		}
		return output;
	}
	
	public static void main(String[] args) throws java.lang.Exception {
		/*Read and Write*/
		//FileWriter fw=new FileWriter("outputTest.txt");
    	//BufferedWriter bw=new BufferedWriter(fw);
		//Scanner in=new Scanner(new FileReader("testA.txt"));
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		Node[] nArray=new Node[N+1];
		for (int i=1;i<=N;i++) nArray[i]=new Node(i);
		//System.out.println(H);
		for (int i=0;i<N-1;i++){
			int a=in.nextInt(); int b=in.nextInt();
			//System.out.println(a+";"+b);
			addEdge(nArray,a,b);
		}
		
		//get one random node
		Node randomStart=nArray[1];
		Node U=getFarNode(N,randomStart);
		Node V=getFarNode(N,U);
		System.out.println(V.distance);
		
		/*Write Epilogue
		//test Write part
    	bw.write("asd is art\n");
		bw.close();
		*/
	}
	
}
