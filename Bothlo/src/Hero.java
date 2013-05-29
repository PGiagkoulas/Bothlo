
public class Hero extends Character{

	private int special;
	private String abilityName;
	private int abilityAttribute;
	private int maxLife;
	private int maxSpecial;//τελικά ήταν απαραίτητο;
	private int equippedRune;
	private int runeDmg = 0;
	private int runeArm = 0;
	private double lifeModifier;
	private double damageModifier;
	private boolean selected = false;
	private static Hero h;

	//constructor
	private Hero(String name, int attribute, int life, int movement, int range,
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
		this.equippedRune = equippedItemAttribute;
		this.lifeModifier = lifeModifier;
		this.damageModifier = damageModifier;
	}
	//summoner!
	public static Hero createHeroInstance(String name, int attribute, int life, int movement, int range,
			int damage, int armor, int positionX, int positionY, int level,
			int special, String abilityName, int abilityAttribute, int maxLife,
			int maxSpecial, int equippedItemAttribute, double lifeModifier,
			double damageModifier){

		 h = new Hero( name,  attribute,  life,  movement,  range,
				damage,  armor,  positionX,  positionY,  level,
				special,  abilityName,  abilityAttribute,  maxLife,
				maxSpecial,  equippedItemAttribute,  lifeModifier,
				damageModifier);
		return h;
	}
	
	public static Hero getHeroInstance(){
		return h;
	}

	//selects character
	public void selectChar(){
		selected = true;
	}

	//deselects character
	public void deselectChar(){
		selected = false;
	}

	//sets new rune attribute
	public void equipRune(Rune rune){
		changeStats(rune.getDamageBonus(),rune.getArmorBonus());

		equippedRune = rune.attribute;
	}

	//changes stats
	private void changeStats(int dmgB, int armB){

		//add the bonus difference (new bonus - previous bonus)
		damage += dmgB - runeDmg;
		armor += armB - runeArm;
		//sets new rune damage and rune armor bonus 
		runeDmg = dmgB;
		runeArm = armB;
	}


	//changes Life
	public void changeLife(int change){
		life -= change;
	}

	//modifing stats
	public int modify(int stat, int level, double modifier){
		stat += level*modifier;
		return stat;
	}

	//level-up method
	public void levelUp(){
		level++;
		maxLife = modify(maxLife, level, lifeModifier);
		damage = modify(damage, level, damageModifier);
		life = maxLife;//restore hero's life to maximun
	}


	//getters&setters
	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public String getAbilityName() {
		return abilityName;
	}

	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}

	public int getAbilityAttribute() {
		return abilityAttribute;
	}

	public void setAbilityAttribute(int abilityAttribute) {
		this.abilityAttribute = abilityAttribute;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getMaxSpecial() {
		return maxSpecial;
	}

	public void setMaxSpecial(int maxSpecial) {
		this.maxSpecial = maxSpecial;
	}

	public int getEquippedRune() {
		return equippedRune;
	}

	public void setEquippedRune(int equippedRune) {
		this.equippedRune = equippedRune;
	}

	public int getRuneDmg() {
		return runeDmg;
	}

	public void setRuneDmg(int runeDmg) {
		this.runeDmg = runeDmg;
	}

	public int getRuneArm() {
		return runeArm;
	}

	public void setRuneArm(int runeArm) {
		this.runeArm = runeArm;
	}

	public double getLifeModifier() {
		return lifeModifier;
	}

	public void setLifeModifier(double lifeModifier) {
		this.lifeModifier = lifeModifier;
	}

	public double getDamageModifier() {
		return damageModifier;
	}

	public void setDamageModifier(double damageModifier) {
		this.damageModifier = damageModifier;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}


}
