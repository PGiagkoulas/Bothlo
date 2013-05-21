
public class Rune extends Encounter{


	private int damageBonus;
	private int armorBonus;

	//constructor
	public Rune(String name, int attribute,int damageBonus,int armorBonus) {
		super(name, attribute);

		this.damageBonus = damageBonus;
		this.armorBonus = armorBonus;
	}

	//getters & setters
	public int getDamageBonus() {
		return damageBonus;
	}
	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}
	public int getArmorBonus() {
		return armorBonus;
	}
	public void setArmorBonus(int armorBonus) {
		this.armorBonus = armorBonus;
	}






}
