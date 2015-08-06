import java.util.*;
import java.lang.*;

public class Main {
	
	public static boolean isValid(char c){
		return (c>='1' && c<='9');
	}
	
	public static boolean isValid(char c1, char c2){
		return (c1=='1' && c2>='0' && c2<='9')|| (c1=='2' && c2>='0' && c2<='6');
	}
	
	public static long numberWayInterpret(String s){
		long[] A=new long[s.length()+1];//A[i] is the # of ways to decompose s.substring(0,i)
		A[0]=1;
		for (int i=1;i<A.length;i++){
			A[i]=(isValid(s.charAt(i-1))?A[i-1]:0)+    (i>=2&&isValid(s.charAt(i-2),s.charAt(i-1))?A[i-2]:0);
		}
		return A[s.length()];
	}
	
	public static void main (String[] args) throws java.lang.Exception{
		
		Scanner in=new Scanner(System.in);
		while (in.hasNext()){
			String s=in.next();
			if (s.equals("0")) break;
			System.out.println(numberWayInterpret(s));
		}
		//System.out.println(numberWayInterpret("3333333333"));
		
	}
}
