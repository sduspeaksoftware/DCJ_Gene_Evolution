package usingILP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.mathworks.toolbox.javabuilder.MWException;

import mysimplexMax.compute;

public class ILP {
	int k; //基因单链长
	int xLength; //x之长度
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
	
	/* 添加等式kx=b */
	public void addEquation(ArrayList<Integer> k, int b) {
		this.Aeq.add(k);
		this.beq.add(b);
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
		this.Aeq.add(array);
		this.beq.add(b);
	}
	/* 添加不等式 */
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
	public void computeILP() {
		compute computations=null;
		try {
			computations=new compute();
			computations.mysimplexMax(A,b);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int ijToE(int i, int j) {
		//若用x(e)表示x(i,j),则 e = (i-1)*k + (j-1-k)
		return (i-1)*xLength + (j-1-xLength);
	}
	public int yiToE(int i) {
		//如每条链10个,则存在y1~y20,映射为x100~x119 (x(k^2 + i - 1))
		return k*k + i - 1;
	}
	public int eToI(int e) {
		//反求: i = (int) e/k + 1, j = e%k + k + 1
		return (int)(e/xLength) + 1;
	}
	public int eToJ(int e) {
		return e%xLength + xLength + 1;
	}
	public int eToYi(int e) {
		return e - k*k + 1;
	}

	public int ziToE(int i) {
		return k*k+i-1+2*k;
	}
}
