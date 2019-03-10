package usingILP;

public class Gene {
	boolean reverse = false;  //正反
	int number = 0;
	Node head;
	Node tail;
	String name;
	public Gene() {
		
	}
	public Gene(String name, int number){
		this.number = number;
		//判断正反
		if(name.charAt(0) == '-'){
			this.reverse = true;
			this.name = name.substring(1);
		}else{
			this.name = name;
		}
		//头尾节点���ð�
		this.head = new Node(name, "head");
		this.tail = new Node(name, "tail");
		this.head.father = this;
		this.tail.father = this;
		this.head.samegene=this.tail;
		this.tail.samegene=this.head;
	}
}
