
public class VisibleEdge {
	private VisibleNode otherEnd;
	
	//Basic Constructor
	public VisibleEdge(){
		
	}
	
	public VisibleEdge(VisibleNode end) {
		otherEnd = end;
	}

	public VisibleNode getOtherEnd() {
		return otherEnd;
	}

	public void setOtherEnd(VisibleNode otherEnd) {
		this.otherEnd = otherEnd;
	}
	
	public String toString() {
		String s = "";
		s += "the other end (" + otherEnd.getX() + ", " + otherEnd.getY() + ")";
		return s;
	}
}
