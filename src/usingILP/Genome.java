package usingILP;
import java.util.ArrayList;
public class Genome implements Cloneable{
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
	//针对基因数不相等的情况，暂时不考虑
//	public void addExtremities() {
//		this.geneNodeList.add(new Node("exhead","ex"));
//		this.geneNodeList.add(new Node("extail","ex"));
//	}
	/* 加基因链头 */
	public void addExHead() {
		this.geneNodeList.add(new Node("exhead", "ex"));
	}
	
	
	public void addGene(String name) {
		Gene g = new Gene(name);
		if (g.reverse) {
			geneNodeList.add(g.head);
			geneNodeList.add(g.tail);
		} else {
			geneNodeList.add(g.tail);
			geneNodeList.add(g.head);
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
	

}
