import java.util.ArrayList;
public class Genome implements Cloneable{
	static int count = 1;
	boolean circular = false;
	ArrayList<Node> geneNodeList = new ArrayList<Node>();
	
	public Genome() {
		
	}
	public Genome(String names[]) {
		//geneNodeList加个头
		this.addExHead();
		for(String i : names) {
			addGene(i);
		}
		this.addExTail();
		this.buildAllLineLink();
	}
	
	/* 加基因链头 */
	public void addExHead() {
		this.geneNodeList.add(new Node("exhead", "ex"));
	}
	
	
	public void addGene(String name) {
		Gene g = new Gene(name, count++);
		if (g.reverse) {
			geneNodeList.add(g.tail);
			geneNodeList.add(g.head);
		} else {
			geneNodeList.add(g.head);
			geneNodeList.add(g.tail);
		}
	}
	
	/* 加基因链尾 */
	public void addExTail() {
		this.geneNodeList.add(new Node("extail", "ex"));
		this.buildAllLineLink();  //加上extail后立刻执行全链连接
		Log.i("Genome ExTail added, auto link all line.");
	}
	
	/* 添加Node完毕后全链连接 */
	private void buildAllLineLink() {
		for(int i=0; i<this.geneNodeList.size(); i++) {
			this.geneNodeList.get(i).sameLink = this.geneNodeList.get(i + 1);
			this.geneNodeList.get(i + 1).sameLink = this.geneNodeList.get(i);
			i++;
		}
	}
	
	public boolean checkNoDiffLinkPoint() {
		for(Node n : geneNodeList) {
			if(n.diffLink == null)
				return false;
		}
		return true;
	}
	
	/* 置所有Node被找过为false */
	public void cleanSearchedOfNodes() {
		for(Node n : geneNodeList) {
			n.searched = false;
		}
	}
	
	@Override
	public Object clone() {
		Genome cloneT = new Genome();
		ArrayList<Node> gnlCloneT = new ArrayList<Node>();
		for(Node i : this.geneNodeList) {
			gnlCloneT.add((Node)i.clone());
		}
		cloneT.geneNodeList = gnlCloneT;
		//cloneT.print();
		return (Object)cloneT;
	}
	
	public static int diguiLoopNode(Genome g1, Genome g2) {
		int maxNum=0;
		boolean oh = false;
		
		for(Node i : g1.geneNodeList) {
			if(i.diffLink == null) {
				oh = true;
				for(Node j : g2.geneNodeList) {
					if (i.nameht.equals(j.nameht) && j.diffLink == null) {
						i.diffLink = j;
						j.diffLink = i;
						if(i.nameht.equals("extail")) {
							if(!g1.checkNoDiffLinkPoint()) {
								return 0;
							}
							
							System.out.println("结果之一：");
							g1.print();
							//g2.print();
							int temp = getNumOfCircles(g1, g2);
							i.diffLink = null;
							j.diffLink = null;
							System.out.println("以上连接方式的temp: "+temp);							
							return temp;
						}
						int temp = diguiLoopNode(g1, g2);
						maxNum = Math.max(maxNum, temp);
						i.diffLink = null;
						j.diffLink = null;
					}
				}
			}
			if(oh)
				break;
		}
		
		return maxNum;
	}

	private static int getNumOfCircles(Genome g1, Genome g2) {
		int num = 0;
		for(Node i : g1.geneNodeList) {
			if(!i.searched) {
				//System.out.println(i.nameht + " not searched.");
				if(i.searchLink()) {
					num++;
				}
			}
		}
		g1.cleanSearchedOfNodes();
		g2.cleanSearchedOfNodes();
		return num;
	}
	
	public void print() {
		System.out.println("Genome: ");
		for(Node n : geneNodeList) {
			n.print();
		}
		System.out.println();
	}
}
