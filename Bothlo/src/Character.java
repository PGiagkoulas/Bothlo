
public class Character extends Encounter{
	
	protected int life;
	protected int movement;
	protected int range;
	protected int damage;
	protected int armor;
	protected double lifeModifier;
	protected double damageModifier;
	protected int positionX;
	protected int positionY;
	protected int level;
	
	//constructor
	public Character(String name, int attribute, int life, int movement, int range, int damage, int armor,
			int positionX, int positionY, int level,double lifeModifier,
			double damageModifier) {
		
		super(name, attribute);
		
		this.life = life;
		this.movement = movement;
		this.range = range;
		this.damage = damage;
		this.armor = armor;
		this.positionX = positionX;
		this.positionY = positionY;
		this.level = level;
		this.lifeModifier = lifeModifier;
		this.damageModifier = damageModifier;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getMovement() {
		return movement;
	}
	public void setMovement(int movement) {
		this.movement = movement;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	//getters and setters for positions
	public void setPositionX(int posX){
		this.positionX = posX;
	}
	public void setPositionY(int posY){
		this.positionY = posY;
	}
	public int getPositionX(){
		return positionX;
	}
	
	public int getPositionY(){
		return positionY;
	}

}
