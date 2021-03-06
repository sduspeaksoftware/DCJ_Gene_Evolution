package usingILP;

import java.util.HashMap;

public class Solution {	
	public static void ilp2(Genome g1,Genome g2) {
		int k = g1.geneNodeList.size();
		ILP ilp = new ILP(k); //前k^2位用于表示x(i,j),后2k位用于表示yi
		//Ax=b构建
		for(Node i: g1.geneNodeList) {
			if(i.type.equals("head")) {
				HashMap<Integer, Integer> equationLeft = new HashMap<Integer, Integer>();
				int equationRight = 1;
				for(Node j: i.diffLinks) {
					//1.对位同名Node连接总数=1.仅考虑每个head结点,因head与tail同时连或不连.
					equationLeft.put(ilp.ijToE(i.number, j.number), 1); //i连j的x(i,j)系数为1
					//2.对位同名Node必须首尾同时连或同时不连( x(a,b) - x(a',b') == 0 )
					HashMap<Integer, Integer> equationLeft2 = new HashMap<Integer, Integer>();
					int equationRight2 = 0;
					equationLeft2.put(ilp.ijToE(i.number, j.number), 1);
					equationLeft2.put(ilp.ijToE(i.samegene.number, j.samegene.number), -1);
//					System.out.println("i="+i.number+" "+"j="+j.number+" "+"i.samegene="+i.samegene.number+" "+"j.samegene="+j.samegene.number);
					ilp.addEquation(equationLeft2, equationRight2);
				}
				ilp.addEquation(equationLeft, equationRight);
			}
		}
		//Ax>=b构建
		HashMap<Integer, Integer> noeq = new HashMap<Integer, Integer>();
		for(Node i: g1.geneNodeList) {
			for(Node j: g2.geneNodeList) {
				//1.0<=x(e)<=1
				noeq = new HashMap<Integer, Integer>();
				noeq.put(ilp.ijToE(i.number, j.number), 1); //1*xe
				ilp.addNoEquation(noeq, 1); //xe<=1
				noeq = new HashMap<Integer, Integer>();
				noeq.put(ilp.ijToE(i.number, j.number), -1); //-1*xe
				ilp.addNoEquation(noeq, 0); //-xe<=0
				//2.i*x(i,j)-yj+yi <= i
				noeq = new HashMap<Integer, Integer>();
				noeq.put(ilp.ijToE(i.number, j.number), i.number); //i*x(i,j)
				noeq.put(ilp.yiToE(j.number), -1); //-yj
				noeq.put(ilp.yiToE(i.number), 1); //+yi
				ilp.addNoEquation(noeq, i.number);
				//3.j*x(i,j)-yi+yj <= j
				noeq = new HashMap<Integer, Integer>();
				noeq.put(ilp.ijToE(i.number, j.number), j.number);
				noeq.put(ilp.yiToE(i.number), -1);
				noeq.put(ilp.yiToE(j.number), 1);
				ilp.addNoEquation(noeq, j.number);
			}
		}
		//4.0<=yi<=i
		for(Node i: g1.geneNodeList) {
			noeq = new HashMap<Integer, Integer>();
			noeq.put(ilp.yiToE(i.number), -1);
			ilp.addNoEquation(noeq, 0); //-yi<=0
			noeq = new HashMap<Integer, Integer>();
			noeq.put(ilp.yiToE(i.number), 1);
			ilp.addNoEquation(noeq, i.number); //yi<=i
			noeq.put(ilp.ziToE(i.number), i.number);
			ilp.addNoEquation(noeq, ilp.yiToE(i.number));//i*zi<=yi
		}
		//5.0<=yj<=j
		for(Node i: g2.geneNodeList) {
			noeq = new HashMap<Integer, Integer>();
			noeq.put(ilp.yiToE(i.number), -1);
			ilp.addNoEquation(noeq, 0); //-yi<=0
			noeq = new HashMap<Integer, Integer>();
			noeq.put(ilp.yiToE(i.number), 1);
			ilp.addNoEquation(noeq, i.number); //yi<=i
			noeq.put(ilp.ziToE(i.number), i.number);
			ilp.addNoEquation(noeq, ilp.yiToE(i.number));//i*zi<=yi
		}
		//送matlab计算
		try {
//			ilp.computeILP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
