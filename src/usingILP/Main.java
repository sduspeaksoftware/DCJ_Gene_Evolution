package usingILP;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//输入两组基因组
		Scanner scan = new Scanner(System.in);
		
		System.out.println("输入原基因组序列，以逗号分隔基因");
		Genome g1 = new Genome(scan.next().split(","));
		System.out.println("输入变异后的基因组序列，以逗号分隔基因");
		Genome g2 = new Genome(scan.next().split(","));

		buildAllDiffLinks(g1,g2); //现在是一个所有difflink都存在的完整的图
		
		

		
//		System.out.println("Result: " + a);
	}
	
	public static void buildAllDiffLinks(Genome g1,Genome g2) {
		for (Node i: g1.geneNodeList ) {
			for (Node j: g2.geneNodeList ) {
				if (i.nameht.contentEquals(j.nameht)) {
					i.diffLinks.add(j);
					j.diffLinks.add(i);
					if(!i.nameht.equals("extail") && !i.nameht.equals("exhead")) {
						i.samegene.diffLinks.add(j.samegene);
						j.samegene.diffLinks.add(i.samegene);
					}
				}
			}
			
		}
	}
}
