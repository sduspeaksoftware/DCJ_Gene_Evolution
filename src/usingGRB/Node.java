package usingGRB;

import java.util.ArrayList;

public class Node {
	static int count = 1;
	String nameht;
	String type; //head or tail
	Gene father;
	Node sameLink; //同侧连接的Node
	Node samegene; //同Gene异端Node
	ArrayList<Node> diffLinks = new ArrayList<Node>();
	boolean searched = false;
	int number = count++;
	
	public Node() {
		
	}

	public Node(String name, String type){
		this.type = type;
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
