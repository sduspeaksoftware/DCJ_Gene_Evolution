import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		long b= System.currentTimeMillis();
		//�������������
		Scanner s=new Scanner(System.in);
		
		System.out.println("����ԭ���������У��Զ��ŷָ�����");
		String genome1=s.next();
		Genome g1 = new Genome(genome1.split(","));
		System.out.println("��������Ļ��������У��Զ��ŷָ�����");
		String genome2=s.next();
		Genome g2 = new Genome(genome2.split(","));
		
		g1.print();
		g2.print();
		
		int a = Genome.diguiLoopNode(g1,g2);
		System.out.println("Result: " + a);
		System.out.print("����ִ��ʱ��Ϊ��");
		System.out.println(System.currentTimeMillis()-b+"����");
	}	 
}
