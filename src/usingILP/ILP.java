package usingILP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ILP {
	int xLength; //x֮����
	ArrayList<ArrayList<Integer>> A;
	ArrayList<Integer> b;
	
	public ILP(int xLength) {
		this.A = new ArrayList<ArrayList<Integer>>();
		this.b = new ArrayList<Integer>();
		this.xLength = xLength;
	}
	
	/* ��ӵ�ʽkx=b */
	public void addEquation(ArrayList<Integer> k, int b) {
		this.A.add(k);
		this.b.add(b);
	}
	/* ʹ��HashMap ��-ֵ ��Ӧ����ӵ�ʽkx=b */
	public void addEquation(HashMap<Integer, Integer> k, int b) {
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
	
	public void compute() {
		
	}
}
