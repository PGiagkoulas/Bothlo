
public class Character extends Encounter{
	
	protected int life;
	protected int movement;
	protected int range;
	protected int damage;
	protected int armor;
	protected int positionX;
	protected int positionY;
	protected int level;
	
	//constructor
	public Character(String name, int attribute, int life, int movement, int range, int damage, int armor,
			int positionX, int positionY, int level) {
		
		super(name, attribute);
		
		this.life = life;
		this.movement = movement;
		this.range = range;
		this.damage = damage;
		this.armor = armor;
		this.positionX = positionX;
		this.positionY = positionY;
		this.level = level;
	}
	
	

}
