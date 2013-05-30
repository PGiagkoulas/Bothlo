
public class Enemy extends Character{

	private int closestTarget = -1;
	
	
	//constructor
	public Enemy(String name, int attribute, int life, int movement, int range,
			int damage, int armor, int positionX, int positionY, int level, double lifeModifier,
			double damageModifier) {
		super(name, attribute, life, movement, range, damage, armor, positionX,
				positionY, level,lifeModifier,
				 damageModifier);
		
	}


	
	//getters&setters
	public int getClosestTarget() {
		return closestTarget;
	}


	public void setClosestTarget(int closestTarget) {
		this.closestTarget = closestTarget;
	}
	
	public int modify(int stat, int level, double modifier){
		stat += level*modifier;
		return stat;
	}
	
	public void levelUp(){
		level++;
		life = modify(life, level, lifeModifier);
		damage = modify(damage, level, damageModifier);
	}
	

}
