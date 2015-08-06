import java.util.*;
import java.lang.*;

public class Main {
	
	public static class Node{
		String S; char Op;
		public Node(String S,char Op){this.S=S;this.Op=Op;}
	}
	
	public static String polish(Node N,String curS){return N.S+curS+N.Op;}
	
	/**Machine Status:
	Status0: Only stack, curS is empty
	Status1: curS is not empty
	*/
	public static void RPNtransform(String inputS,HashMap<Character,Integer> H){
		Stack<Node> myStack=new Stack<Node>();
		String curS="";
		//scan every char and case analysis
		for (int i=0;i<inputS.length();i++){
			char c=inputS.charAt(i);
			//case 1: a-z, to accept a-z char, machine guaranteed to be at status 0.
			if (c>='a' && c<='z'){curS=""+c;}
			//case 2: + - * / ^
			else if (H.containsKey(c)){
				//2.1: Environmentally empty
				if (myStack.isEmpty() || myStack.peek().Op=='('){myStack.push(new Node(curS,c)); curS="";}
				//2.2: Non empty
				else {
					Node head=myStack.peek();
					if (H.get(head.Op)>=H.get(c)){
						curS=polish(myStack.pop(),curS); myStack.push(new Node(curS,c));
					}
					else {
						myStack.push(new Node(curS,c));
					}
				}
			}
			//case 3: ( )
			else {
				if (c=='(') myStack.push(new Node("",'('));
				else {
					while (myStack.peek().Op!='('){
						curS=polish(myStack.pop(),curS);
					}
					myStack.pop();
				}
			}
		}
		//tail case
		while (!myStack.isEmpty()){
			curS=polish(myStack.pop(),curS);
		}
		System.out.println(curS);
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		Scanner in=new Scanner(System.in);
		
		HashMap<Character,Integer> H=new HashMap<Character,Integer>();
		H.put('+',1);H.put('-',2);H.put('*',3);H.put('/',4);H.put('^',5);
		
		int T=in.nextInt();
		for (int t=0;t<T;t++){
			String expression=in.next();
			RPNtransform(expression,H);
		}
		
		//RPNtransform("((a+t)*((b+(a+c))^(c+d)))",H);
	}
}
