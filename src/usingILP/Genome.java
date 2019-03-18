package usingILP;
import java.util.ArrayList;
public class Genome implements Cloneable{
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
	//��Ի���������ȵ��������ʱ������
//	public void addExtremities() {
//		this.geneNodeList.add(new Node("exhead","ex"));
//		this.geneNodeList.add(new Node("extail","ex"));
//	}
	/* �ӻ�����ͷ */
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
	

}
