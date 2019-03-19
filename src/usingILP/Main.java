package usingILP;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//�������������
		Scanner scan = new Scanner(System.in);
		
		System.out.println("����ԭ���������У��Զ��ŷָ�����");
		Genome g1 = new Genome(scan.next().split(","));
		System.out.println("��������Ļ��������У��Զ��ŷָ�����");
		Genome g2 = new Genome(scan.next().split(","));

		buildAllDiffLinks(g1,g2); //������һ������difflink�����ڵ�������ͼ
		
		

		
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
