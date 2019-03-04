public class Gene {
	boolean reverse = false;  //正反
	Node head;
	Node tail;
	String name;
	public Gene(String name){
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
	}
}
