import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//�������������
		Scanner s=new Scanner(System.in);
		System.out.println("����ԭ���������У��Զ��ŷָ�����");
		String genome1=s.next();
		String[] genes1 = genome1.split(",");
		Genome g1 = new Genome(genes1);
		System.out.println("��������Ļ��������У��Կո�ָ�����");
		String genome2=s.next();
		genome2.split(" ");
		String[] genes2 = genome2.split(",");
		Genome g2 = new Genome(genes2);
		
		Genome.diguiLoopNode(g1,g2);
	}
}
