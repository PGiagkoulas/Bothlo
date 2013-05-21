
public class Enemy extends Character{

	private int closestTarget = -1;
	
	
	//constructor
	public Enemy(String name, int attribute, int life, int movement, int range,
			int damage, int armor, int positionX, int positionY, int level) {
		super(name, attribute, life, movement, range, damage, armor, positionX,
				positionY, level);
		
	}


	
	//getters&setters
	public int getClosestTarget() {
		return closestTarget;
	}


	public void setClosestTarget(int closestTarget) {
		this.closestTarget = closestTarget;
	}
	
	
	

}
