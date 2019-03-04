public class Node {
	Gene father;
	Node sameLink;
	Node diffLink;
	String nameht;
	boolean searched = false;
	
	public Node() {
		
	}
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
	
	@Override
	public Object clone() {
		Node n = new Node();
		n.father = father;
		n.sameLink = sameLink;
		n.diffLink = diffLink;
		n.nameht = nameht;
		n.searched = searched;
		return n;
	}
	
	public void print() {
		String aa = (sameLink==null ? "  " : sameLink.nameht);
		String bb = (diffLink==null ? "  " : diffLink.nameht);
		System.out.println("\tNode " + this.nameht + ": sameLink->" + aa + ", diffLink->" + bb + ", searched->" + searched);
	}
	
	/* 标记所有与该点相连的环上的点,有 null link 返回 false */
	public boolean searchLink() {
		boolean flagLink = true;
		if(!this.searched) {
			this.searched = true;
			//如果有左右link,判断左右link是否断了
			if(this.sameLink != null) {  //判空
				boolean f = this.sameLink.searchLink();
				if(f == false)
					flagLink = false;  //只要samelink断就false了
			}
			if(this.diffLink != null) {  //判空
				boolean f = this.diffLink.searchLink();
				if(f == false)
					flagLink = false;  //只要difflink断就false了
			}
			//如果连到这里断了
			if(this.sameLink == null || this.diffLink == null)
				flagLink = false;  //这种情况不是个环
			return flagLink;
		}
		return true;
	}
}
