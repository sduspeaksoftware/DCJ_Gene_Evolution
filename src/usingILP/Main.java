package usingILP;
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

		buildAllDiffLink(g1,g2);//������һ������difflink�����ڵ�������ͼ
		
		

		
//		System.out.println("Result: " + a);
	}
	
	public static void buildAllDiffLink(Genome g1,Genome g2) {
		for (Node i: g1.geneNodeList ) {
			for (Node j: g2.geneNodeList ) {
				if (i.nameht.contentEquals(j.nameht)) {
					i.diffLink.add(j);
					j.diffLink.add(i);
					if(!i.nameht.equals("extail")) {
						if(!i.nameht.equals("exhead")) {
							i.samegene.diffLink.add(j.samegene);
							j.samegene.diffLink.add(i.samegene);
						}
					}
				}
			}
			
		}
	}
}
