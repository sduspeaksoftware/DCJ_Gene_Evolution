import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		long b= System.currentTimeMillis();
		//输入两组基因组
		Scanner s=new Scanner(System.in);
		
		System.out.println("输入原基因组序列，以逗号分隔基因");
		String genome1=s.next();
		Genome g1 = new Genome(genome1.split(","));
		System.out.println("输入变异后的基因组序列，以逗号分隔基因");
		String genome2=s.next();
		Genome g2 = new Genome(genome2.split(","));
		
		g1.print();
		g2.print();
		
		int a = Genome.diguiLoopNode(g1,g2);
		System.out.println("Result: " + a);
		System.out.print("程序执行时间为：");
		System.out.println(System.currentTimeMillis()-b+"毫秒");
	}	 
}
