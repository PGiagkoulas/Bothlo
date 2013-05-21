
public class Trap extends Encounter{

	private int damage;
	private int posX;
	private int posY;
	
	//constructor
	public Trap(String name, int attribute,int damage, int posX, int posY) {
		super(name, attribute);
		
		this.damage = damage;
		this.posX = posX;
		this.posY = posY;
		
	}

	
	//getters&setters
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	
}
