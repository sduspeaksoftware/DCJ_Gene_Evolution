public class Node {
	Node sameLink;
	Node diffLink;
	String nameht;
	/*
	 * @param name:基因名
	 * @param type:头尾类型,head或tail
	 */
	public Node(String name, String type){
		if(type.equals("head")){
			this.nameht = name + "h";
		}else if(type.equals("tail")){
			this.nameht = name + "t";
		}else{
			assert(false);
			System.out.println("Wrong Gene Node type!");
		}
	}
}
