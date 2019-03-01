public class Node {
	Gene father;
	Node sameLink;
	Node diffLink;
	String nameht;
	/*
	 * @param name:鍩哄洜鍚�
	 * @param type:澶村熬绫诲瀷,head鎴杢ail
	 */
	public Node(String name, String type){
		if(type.equals("head")){
			this.nameht = name + "h";
		}else if(type.equals("tail")){
			this.nameht = name + "t";
		}else if(type.contentEquals("ex")) {
			this.nameht = name;
		}else{
			assert(false);
			System.out.println("Wrong Gene Node type!");
		}
	}
}
