import java.util.ArrayList;

public class Genome {
	int numOfGenes;
	boolean circular = false;
	ArrayList<Node> geneNodeList = new ArrayList<Node>();
	public Genome(Node node) {
		//geneNodeList加个头
		this.addExHead();
	}
	
	/* 加基因链头 */
	public void addExHead() {
		this.geneNodeList.add(new Node("exhead", "ex"));
	}
	/* 加基因链尾 */
	public void addExTail() {
		this.geneNodeList.add(new Node("extail", "ex"));
		this.buildAllLineLink();  //加上extail后立刻执行全链连接
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
	
	/* 添加Node完毕后全链连接 */
	private void buildAllLineLink() {
		for(int i=0; i<this.geneNodeList.size(); i++) {
			this.geneNodeList.get(i).sameLink = this.geneNodeList.get(i + 1);
			this.geneNodeList.get(i + 1).sameLink = this.geneNodeList.get(i);
			i++;
		}
	}
	
	public Node getFirstNoDiffNodeByName(String name) {
		for(Node n : this.geneNodeList) {
			if(n.diffLink == null && n.nameht == name) {
				return n;
			}
		}
		return null;
	}
}
