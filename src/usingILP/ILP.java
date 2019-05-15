package usingILP;

//import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//
//import matlabcontrol.MatlabProxy;
//import matlabcontrol.MatlabProxyFactory;
//import matlabcontrol.MatlabProxyFactoryOptions;

public class ILP {
	public static int k; //��������
	int xLength; //x֮����
	ArrayList<ArrayList<Integer>> A, Aeq;
	ArrayList<Integer> b, beq;
	
	public ILP(int k) {
		this.A = new ArrayList<ArrayList<Integer>>();
		this.Aeq = new ArrayList<ArrayList<Integer>>();
		this.b = new ArrayList<Integer>();
		this.beq = new ArrayList<Integer>();
		this.k = k;
		this.xLength = k * k + k * 4;
	}
	
	/* ��ӵ�ʽkx=b */
	public void addEquation(ArrayList<Integer> k, int b) {
		this.Aeq.add(k);
		this.beq.add(b);
	}
	/* ʹ��HashMap ��-ֵ ��Ӧ����ӵ�ʽkx=b */
	public void addEquation(HashMap<Integer, Integer> k, int b) {
		int[] k1 = new int[xLength];
		Arrays.fill(k1, 0);
		for(int i: k.keySet()) {
			//System.out.println(k+" "+i);
			k1[i] = k.get(i);
		}
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i: k1) {
			array.add(i);
		}
		this.Aeq.add(array);
		this.beq.add(b);
	}
	/* ��Ӳ���ʽ */
	public void addNoEquation(HashMap<Integer, Integer> k, int b) {
		int[] k1 = new int[xLength];
		Arrays.fill(k1, 0);
		for(int i: k.keySet()) {
			k1[i] = k.get(i);
		}
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i: k1) {
			array.add(i);
		}
		this.A.add(array);
		this.b.add(b);
	}
//	public void computeILP(){
//		MatlabProxyFactory factory = new MatlabProxyFactory(); 
//		MatlabProxy proxy;
//		
//		File file = new File("C:\\Program Files\\MATLAB");
//		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
//				.setProxyTimeout(300000L)//java����matlab�ĳ�ʱʱ�䣬Ĭ��180000 ms
//		        .setMatlabStartingDirectory(file)//����MATLAB�Ŀ�ʼĿ¼��
//		        .setMatlabLocation("C:\\Program Files\\MATLAB")
//		        .setHidden(false)
//		        .build();
//		MatlabProxyFactory factory = new MatlabProxyFactory(options);
//		MatlabProxy proxy = null;
//		try {
//			proxy = factory.getProxy(); 
//			Object[] result1;
//			result1 = proxy.returningFeval("mysimplexMax",3,A,b,Aeq,beq,k);
//			double[] r=(double[]) result1[0];
//			System.out.print("f:");
//			for (int i = 0; i < r.length; i++) {
//				System.out.print(r[i]);
//				}
//			double[] x=(double[]) result1[1];
//			System.out.println();
//			System.out.print("x:");
//			for (int i = 0; i < x.length; i++) {
//				System.out.print(x[i]);
//			}
//			proxy.disconnect();
//		} catch (Exception e){
//			System.out.println("�������Լ��׳���һ���쳣��");
//			System.out.println(e.getMessage());
//		}
//	}

	public int ijToE(int i, int j) {
		//����x(e)��ʾx(i,j),�� e = (i-1)*k + (j-1-k)
		return (i-1)*k + (j-1-k);
	}
	public int yiToE(int i) {
		//��ÿ����10��,�����y1~y20,ӳ��Ϊx100~x119 (x(k^2 + i - 1))
		return k*k + i - 1;
	}
	public int eToI(int e) {
		//����: i = (int) e/k + 1, j = e%k + k + 1
		return (int)(e/k) + 1;
	}
	public int eToJ(int e) {
		return e%k + k + 1;
	}
	public int eToYi(int e) {
		return e - k*k + 1;
	}

	public int ziToE(int i) {
		return k*k+i-1+2*k;
	}
}
