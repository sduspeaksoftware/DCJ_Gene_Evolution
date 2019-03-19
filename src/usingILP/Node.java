package usingILP;

import java.util.ArrayList;

public class Node {
	static int count = 1;
	String nameht;
	Gene father;
	Node sameLink; //同侧连接的Node
	Node samegene; //同Gene异端Node
	ArrayList<Node> diffLinks = new ArrayList<Node>();
	boolean searched = false;
	int x;
	int y;
	int z;
	int number = count++;
	
	public Node() {
		
	}

	public int link(Node i,Node j) {
		if (i.sameLink.equals(j)||i.diffLinks.contains(j)) {
			x=1;
		}
		else
			x=0;
		return x;
	}

	public Node(String name, String type){
		if(type.equals("head")){
			this.nameht = name + "h";
		}else if(type.equals("tail")){
			this.nameht = name + "t";
		}else if(type.contentEquals("ex")) {
			this.nameht = name;
			this.father = new Gene();
		}else{
			assert(false);
			System.out.println("Wrong Gene Node type!");
		}
		
	}

	@Override
	public Object clone() {
		Node n = new Node();
		n.father = father;
		n.sameLink = sameLink;
		n.diffLinks = diffLinks;
		n.nameht = nameht;
		n.searched = searched;
		n.samegene = samegene;
		return n;
	}
}
