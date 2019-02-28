import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("输入比对序列的基因数");
		int n=s.nextInt();
		System.out.println("输入原基因组序列，以空格分隔基因");
		String gene1=s.next();		
		System.out.println("输入变异后的基因组序列，以空格分隔基因");
		String gene2=s.next();
		
	}
}
