
public class Potion extends Encounter{

	private int restore;
		
	//constructor
	public Potion(String name, int attribute, int restore) {
		super(name, attribute);
		this.restore = restore;
	}

	//getters&setters
	public int getRestore() {
		return restore;
	}
	public void setRestore(int restore) {
		this.restore = restore;
	}

	
}
