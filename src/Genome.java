import java.util.ArrayList;
public class Genome implements Cloneable{
	//int numOfGenes;
	boolean circular = false;
	ArrayList<Node> geneNodeList = new ArrayList<Node>();
	
	public Genome() {
		
	}
	public Genome(String names[]) {
		//geneNodeList�Ӹ�ͷ
		this.addExHead();
		for(String i : names) {
			addGene(i);
		}
		this.addExTail();
		this.buildAllLineLink();
	}
	
	/* �ӻ�����ͷ */
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
	/* �ӻ�����β */
	public void addExTail() {
		this.geneNodeList.add(new Node("extail", "ex"));
		this.buildAllLineLink();  //����extail������ִ��ȫ������
		Log.i("Genome ExTail added, auto link all line.");
	}
	
	/* ���Node��Ϻ�ȫ������ */
	private void buildAllLineLink() {
		for(int i=0; i<this.geneNodeList.size(); i++) {
			this.geneNodeList.get(i).sameLink = this.geneNodeList.get(i + 1);
			this.geneNodeList.get(i + 1).sameLink = this.geneNodeList.get(i);
			i++;
		}
	}
	
	/* ������Node���ҹ�Ϊfalse */
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
		
		for(Node i : g1.geneNodeList) {
			if(i.diffLink == null) {
				for(Node j:g2.geneNodeList) {
					if (i.nameht.equals(j.nameht) && j.diffLink == null) {
						i.diffLink = j;
						j.diffLink = i;
						if(i.nameht.equals("extail")) {
							System.out.println("���֮һ��");
							g1.print();
							g2.print();
							return getNumOfCircles(g1, g2);
						}
						int temp = diguiLoopNode(g1, g2);
						maxNum = Math.max(maxNum, temp);
						i.diffLink = null;
						j.diffLink = null;
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
				//System.out.println(i.nameht + " not searched.");
				if(i.searchLink()) {
					g1.cleanSearchedOfNodes();
					g2.cleanSearchedOfNodes();
					num++;
				}
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
