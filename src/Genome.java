import java.util.ArrayList;
public class Genome implements Cloneable{
	//int numOfGenes;
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
		Gene g = new Gene(name);
		if (!g.reverse) {
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
	
	public static int diguiLoopNode(Genome genome1, Genome genome2) {
		int maxNum=0;
		
		Genome g1 = (Genome)genome1.clone();
		Genome g2 = (Genome)genome2.clone();
		for(Node i : g1.geneNodeList) {
			//System.out.println("Hello");
			if(i.diffLink == null) {
				//System.out.println("Hi");
				for(Node j:g2.geneNodeList) {
					//System.out.println("i: " + i.nameht);
					//System.out.println("j: " + j.nameht + j.diffLink );
					if (i.nameht.equals(j.nameht) && j.diffLink == null) {
						//System.out.println("Link: " +i.nameht + " with " + j.nameht);
						i.diffLink =j;
						j.diffLink =i;
						if(i.nameht.equals("extail")) {
							g1.print();
							return getNumOfCircles(g1, g2);
						}
						int temp = diguiLoopNode(g1,g2);
						maxNum = Math.max(maxNum, temp);
					}
				}
			}
		}
		
		return maxNum;
	}

	private static int getNumOfCircles(Genome g1, Genome g2) {
		int num = 0;
		for(Node i : g1.geneNodeList) {
			if(!i.searched) {
				//System.out.print("Search Circle from point " + i.nameht + ": ");
				if(i.searchLink()) {
					num++;
				}
				//System.out.println();
			}
		}
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
