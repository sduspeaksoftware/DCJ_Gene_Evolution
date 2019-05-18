package usingGRB;

import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		//�������������
		Scanner s=new Scanner(System.in);
		System.out.println("����ԭ���������У��Զ��ŷָ�����");
		String genome1=s.next();
		Genome g1 = new Genome(genome1.split(","));
		System.out.println("��������Ļ��������У��Զ��ŷָ�����");
		String genome2=s.next();
		Genome g2 = new Genome(genome2.split(","));
		long b= System.currentTimeMillis();
		buildAllDiffLinks(g1,g2);
		
		System.out.print("����ִ��ʱ��Ϊ��");
		System.out.println(System.currentTimeMillis()-b+"����");
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
