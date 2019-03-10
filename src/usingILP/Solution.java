package usingILP;

public class Solution {
	public static void ilp(Genome g1,Genome g2) {
		linex(g1,g2);
		
		
		
	}

	private static void linex(Genome g1, Genome g2) {
		for(Node i:g1.geneNodeList ) {
			i.sameLink.x=1; 
		}
		for(Node i:g2.geneNodeList ) {
			i.sameLink.x=1; 
		}		
	}
	
	
}
