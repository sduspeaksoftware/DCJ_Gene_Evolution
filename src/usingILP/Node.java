package usingILP;

import java.util.ArrayList;

public class Node {
	Gene father;
	Node sameLink;
	ArrayList<Node> diffLink=new ArrayList<Node>();
	String nameht;
	Node samegene;
	boolean searched = false;
	int x=0;
	
	boolean alreadyx=false;
	public Node() {
		
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
		n.diffLink = diffLink;
		n.nameht = nameht;
		n.searched = searched;
		n.samegene = samegene;
		n.x = x;
		return n;
	}
	
}
