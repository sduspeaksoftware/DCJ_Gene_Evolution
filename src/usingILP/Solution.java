package usingILP;

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
		ILP ilp = new ILP(g1.geneNodeList.size());
		//1.对位同名Node连接总数=1
		
		//2.对位同名Node必须首尾同时连或同时不连
		
	}
}
