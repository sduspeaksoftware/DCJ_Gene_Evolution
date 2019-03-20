package usingILP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ILP {
	int xLength; //x之长度
	ArrayList<ArrayList<Integer>> A;
	ArrayList<Integer> b;
	
	public ILP(int xLength) {
		this.A = new ArrayList<ArrayList<Integer>>();
		this.b = new ArrayList<Integer>();
		this.xLength = xLength;
	}
	
	/* 添加等式kx=b */
	public void addEquation(ArrayList<Integer> k, int b) {
		this.A.add(k);
		this.b.add(b);
	}
	/* 使用HashMap 键-值 对应表添加等式kx=b */
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
