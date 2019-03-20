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
		//����ע����ϣ��ʵ�ֵ�constraints
		/*for (Node i:g1&g2.geneNodeList){
			 * 0<=i.y<=i.number;
			 * for (Node j:g1&g2.geneNodeList){
			 	* i.y<=j.y+i.number*(1-i.link(i,j));
			 	* j.y<=i.y+j.number*(1-i.link(i,j));
			 	* }
			 * �����Ƕ���ÿ��Node��y������
		*if (i.number<=i.y){i.z=1;}else{i.z=0;}	 
		
		}*/
	}
	
	public static void ilp2(Genome g1,Genome g2) {
		//12��Node,x���i����13�������ֱ�ʾg1��Node���,iȡ��13��ʾg2��Node���,��13����,���x���i���ֵΪ13^2-1
		int genomeLength = g1.geneNodeList.size();
		int system = genomeLength + 1; //����
		ILP ilp = new ILP((int) Math.pow(system, 2) - 1);
		//Ax=b����
		for(Node n: g1.geneNodeList) {
			if(n.type.equals("head")) {
				HashMap<Integer, Integer> equationLeft = new HashMap<Integer, Integer>();
				int equationRight = 1;
				for(Node i: n.diffLinks) {
					//1.��λͬ��Node��������=1.������ÿ��head���,��head��tailͬʱ������.
					equationLeft.put(n.number * system + (i.number - genomeLength), 1); //n��i��x(n,i)ϵ��Ϊ1
					//2.��λͬ��Node������βͬʱ����ͬʱ����( x(a,b) - x(a',b') == 0 )
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
