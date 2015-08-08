import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	static class InputReader {
	    private InputStream stream;
	    private byte[] buf = new byte[1024];
	    private int curChar;
	    private int numChars;
	    private SpaceCharFilter filter;

	    public InputReader(InputStream stream) {
	        this.stream = stream;
	    }

	    public int read() {
	        if (numChars == -1)
	            throw new InputMismatchException();
	        if (curChar >= numChars) {
	            curChar = 0;
	            try {
	                numChars = stream.read(buf);
	            } catch (IOException e) {
	                throw new InputMismatchException();
	            }
	            if (numChars <= 0)
	                return -1;
	        }
	        return buf[curChar++];
	    }

	    public int readInt() {
	        int c = read();
	    while (isSpaceChar(c))
	            c = read();
	    int sgn = 1;
	    if (c == '-') {
	            sgn = -1;
	            c = read();
	    }
	        int res = 0;
	    do {
	            if (c < '0' || c > '9')
	                throw new InputMismatchException();
	            res *= 10;
	            res += c - '0';
	            c = read();
	        } while (!isSpaceChar(c));
	        return res * sgn;
	    }

	    public long readLong() {
	        int c = read();
	        while (isSpaceChar(c))
	            c = read();
	        int sgn = 1;
	        if (c == '-') {
	            sgn = -1;
	            c = read();
	        }
	    long res = 0;
	        do {
	            if (c < '0' || c > '9')
	                throw new InputMismatchException();
	        res *= 10;
	        res += c - '0';
	                c = read();
	        } while (!isSpaceChar(c));
	        return res * sgn;
	    }

	    public boolean isSpaceChar(int c) {
	        if (filter != null)
	            return filter.isSpaceChar(c);
	        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	    }
	    public interface SpaceCharFilter {
	        public boolean isSpaceChar(int ch);
	    }
	}
	
	public static class Node{
		int leftLimit; int rightLimit; 
		int maxVal; int minVal;
		int delta;
		Node leftNode; Node rightNode;
		
		public Node(int left, int right){
			this.leftLimit=left; this.rightLimit=right;
		}
		
		public String toString(){
			return "leftLimit:"+this.leftLimit+";rightLimit:"+this.rightLimit+";maxVal:"+this.maxVal+";minVal:"+this.minVal+";delta:"+this.delta;
		}
		
	}
	
	//returns the root node 
	public static Node build(int[] cumA, int left, int right){
		Node root=new Node(left,right);
		if (left==right){
			root.delta=-655360; root.maxVal=cumA[left]; root.minVal=cumA[left];
			return root;
		}
		int mid=(left+right)/2;
		Node leftNode=build(cumA,left,mid);
		Node rightNode=build(cumA,mid+1,right);
		root.leftNode=leftNode; root.rightNode=rightNode;
		root.maxVal=Math.max(leftNode.maxVal,rightNode.maxVal);
		root.minVal=Math.min(leftNode.minVal,rightNode.minVal);
		root.delta=Math.max(Math.max(leftNode.delta,rightNode.delta),rightNode.maxVal-leftNode.minVal);
		
		return root;
	}
	
	
	
	//returns new int[]{maxVal in range, minVal in range, delta in range}
	public static int[] query(Node root, int x, int y){
		//root range no intersection
		if (root.rightLimit<x || root.leftLimit>y) return new int[]{0,0,0}; // this happens iff the toplevel root out of query range
		//information currently depends on only current root node
		if (root.leftNode==null && root.rightNode==null || (root.leftLimit>=x && root.rightLimit<=y)) return new int[]{root.maxVal,root.minVal,root.delta};
		//branch
		//1) only to left (right does not exist or no intersection with query range)
		if (root.rightNode==null || root.rightNode.leftLimit>y) return query(root.leftNode,x,y);
		//2) only to right (left does not exist or no intersection with query range)
		if (root.leftNode==null || root.leftNode.rightLimit<x) return query(root.rightNode,x,y);
		//3) combine left & right 
		int[] leftResponse=query(root.leftNode,x,y);
		int[] rightResponse=query(root.rightNode,x,y);
		int rootMaxVal=Math.max(leftResponse[0],rightResponse[0]);
		int rootMinVal=Math.min(leftResponse[1],rightResponse[1]);
		int rootDelta=Math.max(rightResponse[0]-leftResponse[1],Math.max(leftResponse[2],rightResponse[2]));
		return new int[]{rootMaxVal,rootMinVal,rootDelta};
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		//Scanner in=new Scanner(System.in);
		InputReader in= new InputReader(System.in);
		int n=in.readInt();
		int[] A=new int[n];
		for (int i=0;i<A.length;i++) A[i]=in.readInt();
		int[] cumA=new int[A.length+1];
		cumA[0]=0;
		for (int i=1;i<cumA.length;i++) cumA[i]=cumA[i-1]+A[i-1];
		Node root=build(cumA,0,cumA.length-1);
		StringBuilder SB=new StringBuilder();
		int m=in.readInt();
		for (int i=0;i<m;i++){
			int x=in.readInt(); int y=in.readInt();
			int out=(query(root,x-1,y)[2]);
			SB.append(out);SB.append("\n"); 
		}
		System.out.print(SB.toString());
		
		
	}
}
