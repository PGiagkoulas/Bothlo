
public class Hero extends Character{

	private int special;
	private String abilityName;
	private int abilityAttribute;
	private int maxLife;
	private int maxSpecial;//τελικά ήταν απαραίτητο;
	private int equippedItemAttribute;
	private double lifeModifier;
	private double damageModifier;
	
	
	//constructor
	public Hero(String name, int attribute, int life, int movement, int range,
			int damage, int armor, int positionX, int positionY, int level,
			int special, String abilityName, int abilityAttribute, int maxLife,
			int maxSpecial, int equippedItemAttribute, double lifeModifier,
			double damageModifier) {
		
		super(name, attribute, life, movement, range, damage, armor, positionX,
				positionY, level);
		
		this.special = special;
		this.abilityName = abilityName;
		this.abilityAttribute = abilityAttribute;
		this.maxLife = maxLife;
		this.maxSpecial = maxSpecial;
		this.equippedItemAttribute = equippedItemAttribute;
		this.lifeModifier = lifeModifier;
		this.damageModifier = damageModifier;
	}
	
	
	
	

}
 