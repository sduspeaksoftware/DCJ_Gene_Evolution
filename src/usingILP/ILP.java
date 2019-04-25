package usingILP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.mathworks.toolbox.javabuilder.MWException;

import mysimplexMax.compute;

public class ILP {
	int k; //��������
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
		//����x(e)��ʾx(i,j),�� e = (i-1)*k + (j-1-k)
		return (i-1)*xLength + (j-1-xLength);
	}
	public int yiToE(int i) {
		//��ÿ����10��,�����y1~y20,ӳ��Ϊx100~x119 (x(k^2 + i - 1))
		return k*k + i - 1;
	}
	public int eToI(int e) {
		//����: i = (int) e/k + 1, j = e%k + k + 1
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
