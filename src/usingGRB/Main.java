package usingGRB;

import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		//输入两组基因组
		Scanner s=new Scanner(System.in);
		System.out.println("输入原基因组序列，以逗号分隔基因");
		String genome1=s.next();
		Genome g1 = new Genome(genome1.split(","));
		System.out.println("输入变异后的基因组序列，以逗号分隔基因");
		String genome2=s.next();
		Genome g2 = new Genome(genome2.split(","));
		long b= System.currentTimeMillis();
		buildAllDiffLinks(g1,g2);
		
		System.out.print("程序执行时间为：");
		System.out.println(System.currentTimeMillis()-b+"毫秒");
	}
	int[][] difflink=new int[10][10];
	public static void buildAllDiffLinks(Genome g1,Genome g2) {
		for (Node i: g1.geneNodeList ) {
			for (Node j: g2.geneNodeList ) {
				if (i.nameht.contentEquals(j.nameht)) {
					i.diffLinks.add(j);
//					j.diffLinks.add(i);
					
					if(!i.nameht.equals("extail") && !i.nameht.equals("exhead")) {
						i.samegene.diffLinks.add(j.samegene);
						j.samegene.diffLinks.add(i.samegene);
					}
				}
			}
			
		}
	}
}
