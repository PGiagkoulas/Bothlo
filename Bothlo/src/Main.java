
public class Main {

	
	public static void main(String[] args) {
		/*
		  public Hero(String name, int attribute, int life, int movement, int range,
		 
				int damage, int armor, int positionX, int positionY, int level,
				int special, String abilityName, int abilityAttribute, int maxLife,
				int maxSpecial, int equippedItemAttribute, double lifeModifier,
				double damageModifier)
				*/
		Hero warrior = Hero.createHeroInstance("warrior", 1, 120, 3, 1,
									10, 8, 0, 0, 1, 
									3, "Shout", 5, 120,
									3, -1, 0.5, 
									0.2);
		
		Hero x = Hero.createHeroInstance("", -1, -120, -3, -1,
				-10,- 8, 0, 0,- 1, 
				-3, "Shout", -5, -120,
				-3, -1, -0.5, 
				-0.2);
		
		Data d = new Data();
		
		
		d.addStageElement(warrior, warrior.getPositionX() ,warrior.getPositionY());
		
		if(d.getStageElement(warrior.getPositionX(), warrior.getPositionY()) instanceof  Hero){
			x = ( (Hero)(d.getStageElement(warrior.getPositionX(), warrior.getPositionY())) );
			System.out.println( x.getEquippedRune()); 
		}
		
		
		warrior.changeLife(10);
		System.out.println("Life changed to: "+warrior.getLife());
		
		warrior.setPositionX(warrior.getPositionX()+32);
		warrior.setPositionY(warrior.getPositionY()+32);
		System.out.println("Position changed to: "+warrior.getPositionX()+" , "+warrior.getPositionY());
	}

}
