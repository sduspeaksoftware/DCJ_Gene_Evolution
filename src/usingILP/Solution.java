package usingILP;

import java.util.HashMap;

public class Solution {
	public static void ilp(Genome g1,Genome g2) {
		for (Node i:g1.geneNodeList) {
			//i.link(i,i.sameLink)=1;
			for (Node j:i.diffLinks ) {
				//sum(i.link(i, j))=1;
				//j.link(j,i)=j.samegene.link(j.samegene,i.samegene);
			}
		}
		for (Node i:g2.geneNodeList) {
			//i.link(i,i.sameLink)=1;
			for (Node j:i.diffLinks ) {
				//sum(i.link(i, j))=1;
				//j.link(j,i)=j.samegene.link(j.samegene,i.samegene);
			}
		}		
		//以上注释是希望实现的constraints
		/*for (Node i:g1&g2.geneNodeList){
			 * 0<=i.y<=i.number;
			 * for (Node j:g1&g2.geneNodeList){
			 	* i.y<=j.y+i.number*(1-i.link(i,j));
			 	* j.y<=i.y+j.number*(1-i.link(i,j));
			 	* }
			 * 以上是对于每个Node的y的限制
		*if (i.number<=i.y){i.z=1;}else{i.z=0;}	 
		
		}*/
	}
	
	public static void ilp2(Genome g1,Genome g2) {
		//12长Node,x标号i除以13整数部分表示g1中Node标号,i取余13表示g2中Node标号,类13进制,因此x标号i最大值为13^2-1
		int genomeLength = g1.geneNodeList.size();
		int system = genomeLength + 1; //进制
		ILP ilp = new ILP((int) Math.pow(system, 2) - 1);
		//Ax=b构建
		for(Node n: g1.geneNodeList) {
			if(n.type.equals("head")) {
				HashMap<Integer, Integer> equationLeft = new HashMap<Integer, Integer>();
				int equationRight = 1;
				for(Node i: n.diffLinks) {
					//1.对位同名Node连接总数=1.仅考虑每个head结点,因head与tail同时连或不连.
					equationLeft.put(n.number * system + (i.number - genomeLength), 1); //n连i的x(n,i)系数为1
					//2.对位同名Node必须首尾同时连或同时不连( x(a,b) - x(a',b') == 0 )
					HashMap<Integer, Integer> equationLeft2 = new HashMap<Integer, Integer>();
					int equationRight2 = 0;
					equationLeft2.put(n.number * system + (i.number - genomeLength), 1);
					equationLeft2.put(n.samegene.number * system + (i.samegene.number - genomeLength), -1);
					ilp.addEquation(equationLeft2, equationRight2);
				}
				ilp.addEquation(equationLeft, equationRight);
			}
		}
		ilp.compute();
	}
}
