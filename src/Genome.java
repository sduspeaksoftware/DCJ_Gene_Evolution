import java.util.ArrayList;
public class Genome implements Cloneable{
	//int numOfGenes;
	boolean circular = false;
	ArrayList<Node> geneNodeList = new ArrayList<Node>();
	
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
	/* �ӻ�����β */
	public void addExTail() {
		this.geneNodeList.add(new Node("extail", "ex"));
		this.buildAllLineLink();  //����extail������ִ��ȫ������
		Log.i("Genome ExTail added, auto link all line.");
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
	
	/* ���Node��Ϻ�ȫ������ */
	private void buildAllLineLink() {
		for(int i=0; i<this.geneNodeList.size(); i++) {
			this.geneNodeList.get(i).sameLink = this.geneNodeList.get(i + 1);
			this.geneNodeList.get(i + 1).sameLink = this.geneNodeList.get(i);
			i++;
		}
	}
	
	@Override
	public Object clone() {
		Genome cloneT = (Genome)this.clone();
		ArrayList<Node> gnlCloneT = new ArrayList<Node>();
		for(Node i : cloneT.geneNodeList) {
			gnlCloneT.add((Node)i.clone());
		}
		cloneT.geneNodeList = gnlCloneT;
		return (Object)cloneT;
	}
	
	public static void diguiLoopNode(Genome genome1, Genome genome2) {
		Genome g1 = (Genome)genome1.clone();
		Genome g2 = (Genome)genome2.clone();
		for(Node i : g1.geneNodeList) {
			if(i.diffLink == null) {
				for(Node j:g2.geneNodeList) {
					if (i.nameht ==j.nameht && j.diffLink ==null) {
						i.diffLink =j;
						j.diffLink =i;
						diguiLoopNode(g1,g2);
					}
				}
			}
		}
		System.out.println(getNumOfCircles(g1,g2));
	}

	private static int getNumOfCircles(Genome g1, Genome g2) {
		int num = 0;
		for(Node i : g1.geneNodeList) {
			if(!i.searched) {
				if(i.searchLink()) {
					num++;
				}
			}
		}
		return num;
	}
}
