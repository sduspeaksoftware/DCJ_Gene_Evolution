import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//输入两组基因组
		Scanner s=new Scanner(System.in);
		System.out.println("输入原基因组序列，以逗号分隔基因");
		String genome1=s.next();
		String[] genes1 = genome1.split(",");
		Genome g1 = new Genome(genes1);
		System.out.println("输入变异后的基因组序列，以空格分隔基因");
		String genome2=s.next();
		genome2.split(" ");
		String[] genes2 = genome2.split(",");
		Genome g2 = new Genome(genes2);
		
		Genome.diguiLoopNode(g1,g2);
	}
}
