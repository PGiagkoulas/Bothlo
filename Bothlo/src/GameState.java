import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


import java.awt.*;

public class GameState extends BasicGameState {

	private TiledMap grassMap;
	private Animation Warrior , upW ,downW,leftW,rightW;
	/*
	private Animation Mage , upM ,downM,leftM,rightM;
	private Animation Rogue , upR ,downR,leftR,rightR;
	private Animation Cleric , upC ,downC,leftC,rightC;
	 */
	private Animation Mage, upM ,downM,leftM,rightM;

	private Image inGameMenu;

	private boolean[][] blocked;
	private boolean quit = false;	//bool for in-game menu
	private boolean attack = false; //bool for attack mode

	private static final int SIZE = 32;

	private float x = 240f, y = 569f;

	private float k = 240f, l = 32f;

	private float heroMovement = 0;
	private int enemyMovement = 0;

	public GameState(int State){

	}
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		//changing resolution
		//AppGameContainer apgc = (AppGameContainer)gc;
		//apgc.setDisplayMode(482, 600, false);

		inGameMenu = new Image("res/inGameMenu.png");


		Image [] movementWarrior = {new Image("res/warrior.png"),new Image("res/warrior.png")};

		Image [] movementMage = {new Image("res/Mage.png"), new Image("res/Mage.png")};
		/*
        Image [] movementRogue = {new Image("res/rogue.png"), new Image("res/rogue.png")};
        Image [] movementCleric = {new Image("res/cleric.png"),new Image("res/cleric.png")};
		 */
		grassMap = new TiledMap("res/Starting room.tmx");
		int [] duration = {1, 1};

		upW = new Animation(movementWarrior, duration, false);

		upM = new Animation(movementMage, duration, false);
		/*
        upR = new Animation(movementRogue, duration,false );
        upC = new Animation(movementCleric, duration, false);
		 */
		leftW = new Animation(movementWarrior, duration, false);

		leftM = new Animation(movementMage, duration, false);
		/*
        leftR = new Animation(movementCleric, duration, false);     
        leftC = new Animation(movementCleric, duration, false);
		 */



		rightW = new Animation(movementWarrior, duration, false);

		rightM = new Animation(movementMage, duration, false);
		/*
        rightR = new Animation(movementRogue, duration, false);
        rightC = new Animation(movementCleric, duration, false);
		 */
		downW = new Animation(movementWarrior, duration, false);

		downM = new Animation(movementMage, duration, false);
		/*
        downR = new Animation(movementRogue, duration, false);
        downC = new Animation(movementCleric, duration, false);
		 */
		Warrior = downW;
		Mage = downM;
		/*
        Rogue = downR;

        Cleric = downC;
		 */
		blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
		String Layername = grassMap.getLayerProperty(1, "trap", "true");

		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
		{
			for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
			{
				int tileID = grassMap.getTileId(xAxis, yAxis,1);
				if (tileID!= 0){
					blocked[xAxis][yAxis] = true;

				}

			}
		}
		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
		{
			for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
			{
				int tileID = grassMap.getTileId(xAxis, yAxis,2);
				if (tileID!= 0){
					blocked[xAxis][yAxis] = true;

				}

			}
		}


	}

	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g) throws SlickException {
		grassMap.render(0,0);
		Warrior.draw((int)x, (int)y);

		Mage.draw((int)k, (int)l);
		/*
		Rogue.draw((int)x, (int)y+30);
		Cleric.draw((int)x+30, (int)y+30);
		 */
		//when they press escape
		if(quit==true){
			inGameMenu.drawCentered(250, 300);	
			if(quit==false){
				g.clear();
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		float fdelta=delta*0.1f;
		if(quit == false && attack == false){ 						//if quit is false move

			//checking maximum movement reached
			if(heroMovement <= Hero.getHeroInstance().getMovement()*SIZE){
				if (input.isKeyDown(Input.KEY_UP))
				{
					Warrior = upW;
					Mage = upM;
					/*
			Rogue = upR;

			Cleric = upC;
					 */
					if(y <0)
						y=0;
					if (!(isBlocked(x, y- fdelta) || isBlocked(x+SIZE-1, y - fdelta)))
					{
						Warrior.update(delta);
						Mage.update(delta);
						/*
				Rogue.update(delta);

				Cleric.update(delta);
						 */
						// The lower the delta the slowest the sprite will animate.
						y -= fdelta;
						heroMovement += fdelta;
					}
				}
				else if (input.isKeyDown(Input.KEY_DOWN))
				{
					Warrior = downW;
					Mage = downM;
					/*
			Rogue =downR;

			Cleric = downC;
					 */

					//character limit on back movement
					if (y>569)
						y=569;

					if (!(isBlocked(x, y + SIZE + fdelta) || isBlocked(x+SIZE-1, y + SIZE + fdelta)))
					{
						Warrior.update(delta);
						Mage.update(delta);
						/*
				Rogue.update(delta);

				Cleric.update(delta);
						 */
						y += fdelta;
						heroMovement += fdelta;
					}
				}
				else if (input.isKeyDown(Input.KEY_LEFT))
				{
					Warrior = leftW;
					Mage = leftM;
					/*
			Rogue = leftR;

			Cleric = leftC;
					 */

					//character limit on left movement
					if(x<0)
						x=0;
					if (!(isBlocked(x - fdelta, y) || isBlocked(x - fdelta, y+SIZE-1)))
					{
						Warrior.update(delta);
						Mage.update(delta);
						/*
				Rogue.update(delta);

				Cleric.update(delta);
						 */
						x -= fdelta;
						heroMovement += fdelta;
					}
				}
				else if (input.isKeyDown(Input.KEY_RIGHT))
				{
					Warrior = rightW;
					Mage = rightM;
					/*
			Rogue = rightR;

			Cleric = rightC;
					 */

					//character limit on right movement
					if(x>452)
						x=452;
					if (!(isBlocked(x + SIZE + fdelta, y) || isBlocked(x + SIZE + fdelta, y+SIZE-1)))
					{
						Warrior.update(delta);
						Mage.update(delta);
						/*
				Rogue.update(delta);

				Cleric.update(delta);
						 */
						x += fdelta;
						heroMovement += fdelta;
					}
				}

			}

		}
		//attack mode
		if(input.isKeyDown(Input.KEY_A)){
			attack = true;
			System.out.println(Hero.getHeroInstance().getAbilityAttribute());

		}
		if(attack == true){
			if(input.isKeyDown(Input.KEY_S)){
				attack = false;
			}
		}

		//end turn mode
		if(input.isKeyDown(Input.KEY_E)){
			k += 0;
			l +=  fdelta;
			
			heroMovement = 0;
		}



		//escape
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;

		}      

		//when they hit escape
		if(quit==true){
			if(input.isKeyDown(Input.KEY_R)){
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_M)){
				AppGameContainer apgc = (AppGameContainer)gc;
				apgc.setDisplayMode(900, 384, false);
				sbg.enterState(0);
				try{
					Thread.sleep(250);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			if(input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
		}
	}

	private boolean isBlocked(float x, float y)
	{
		int xBlock = (int)x / SIZE;
		int yBlock = (int)y / SIZE;
		return blocked[xBlock][yBlock];
	}


	@Override
	public int getID() {
		return 1;
	}

}
