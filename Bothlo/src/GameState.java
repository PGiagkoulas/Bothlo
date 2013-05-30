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
	private Animation Bothlo, upM ,downM,leftM,rightM;

	private Image inGameMenu;

	private boolean[][] traps;
	private boolean[][] warps;
	private boolean quit = false;	//bool for in-game menu
	private boolean attack = false; //bool for attack mode
	private boolean enemyTurn = false; //bool for enemy turn
	private boolean tookDamage = false; //bool for trap damage taken

	private static final int SIZE = 32;

	private float x = 240f, y = 569f;

	private float k = 240f, l = 32f;

	private Point enemyPos = new Point((int)k,(int) l);


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

		Image [] movementBothlo = {new Image("res/Bothlo.png"), new Image("res/Bothlo.png")};
		/*
        Image [] movementRogue = {new Image("res/rogue.png"), new Image("res/rogue.png")};
        Image [] movementCleric = {new Image("res/cleric.png"),new Image("res/cleric.png")};
		 */
		grassMap = new TiledMap("res/Starting room.tmx");
		int [] duration = {1, 1};

		upW = new Animation(movementWarrior, duration, false);

		upM = new Animation(movementBothlo, duration, false);
		/*
        upR = new Animation(movementRogue, duration,false );
        upC = new Animation(movementCleric, duration, false);
		 */
		leftW = new Animation(movementWarrior, duration, false);

		leftM = new Animation(movementBothlo, duration, false);
		/*
        leftR = new Animation(movementCleric, duration, false);     
        leftC = new Animation(movementCleric, duration, false);
		 */



		rightW = new Animation(movementWarrior, duration, false);

		rightM = new Animation(movementBothlo, duration, false);
		/*
        rightR = new Animation(movementRogue, duration, false);
        rightC = new Animation(movementCleric, duration, false);
		 */
		downW = new Animation(movementWarrior, duration, false);

		downM = new Animation(movementBothlo, duration, false);
		/*
        downR = new Animation(movementRogue, duration, false);
        downC = new Animation(movementCleric, duration, false);
		 */
		Warrior = downW;
		Bothlo = downM;
		/*
        Rogue = downR;

        Cleric = downC;
		 */
		warps = new boolean[grassMap.getWidth()][grassMap.getHeight()];
		traps = new boolean[grassMap.getWidth()][grassMap.getHeight()];

		String Layername = grassMap.getLayerProperty(1, "trap", "true");

		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
		{
			for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
			{
				int tileID = grassMap.getTileId(xAxis, yAxis,1);
				if (tileID!= 0){
					traps[xAxis][yAxis] = true;

				}

			}
		}
		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
		{
			for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
			{
				int tileID = grassMap.getTileId(xAxis, yAxis,2);
				if (tileID!= 0){
					warps[xAxis][yAxis] = true;

				}

			}
		}
		


	}

	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g) throws SlickException {
		grassMap.render(0,0);
		Warrior.draw((int)x, (int)y);

		Bothlo.draw((int)enemyPos.x, (int)enemyPos.y);
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
		//if quit is false move
		if(quit == false && attack == false && enemyTurn == false){ 						

			//checking maximum movement reached
			if(heroMovement <= Hero.getHeroInstance().getMovement()*SIZE){
				if (input.isKeyDown(Input.KEY_UP))
				{
					Warrior = upW;
					Bothlo = upM;
					/*
			Rogue = upR;

			Cleric = upC;
					 */
					if(y <0)
						y=0;
					Warrior.update(delta);
					Bothlo.update(delta);
					
					// The lower the delta the slowest the sprite will animate.
					y -= fdelta;
					heroMovement += fdelta;
					if ((isTrap(x, y- fdelta) || isTrap(x+SIZE-1, y - fdelta)))
					{
						if(!tookDamage){
						Hero.getHeroInstance().changeLife(3);
						System.out.println(Hero.getHeroInstance().getLife());
						tookDamage = true;
						}
					}
				}
				else if (input.isKeyDown(Input.KEY_DOWN))
				{
					Warrior = downW;
					Bothlo = downM;
					

					//character limit on back movement
					if (y>569)
						y=569;

					Warrior.update(delta);
					Bothlo.update(delta);
					
					y += fdelta;
					heroMovement += fdelta;
					
					if ((isTrap(x, y + SIZE + fdelta) || isTrap(x+SIZE-1, y + SIZE + fdelta)))
					{
						if(!tookDamage){
							Hero.getHeroInstance().changeLife(3);
							System.out.println(Hero.getHeroInstance().getLife());
							tookDamage = true;
							}
					}
				}
				else if (input.isKeyDown(Input.KEY_LEFT))
				{
					Warrior = leftW;
					Bothlo = leftM;
				

					//character limit on left movement
					if(x<0)
						x=0;
					
					Warrior.update(delta);
					Bothlo.update(delta);
				
					x -= fdelta;
					heroMovement += fdelta;
					
					if ((isTrap(x - fdelta, y) || isTrap(x - fdelta, y+SIZE-1)))
					{
						if(!tookDamage){
							Hero.getHeroInstance().changeLife(3);
							System.out.println(Hero.getHeroInstance().getLife());
							tookDamage = true;
							}
					}
				}
				else if (input.isKeyDown(Input.KEY_RIGHT))
				{
					Warrior = rightW;
					Bothlo = rightM;
					

					//character limit on right movement
					if(x>452)
						x=452;
					
					Warrior.update(delta);
					Bothlo.update(delta);
				
					x += fdelta;
					heroMovement += fdelta;
					
					if ((isTrap(x + SIZE + fdelta, y) || isTrap(x + SIZE + fdelta, y+SIZE-1)))
					{
						if(!tookDamage){
							Hero.getHeroInstance().changeLife(3);
							System.out.println(Hero.getHeroInstance().getLife());
							tookDamage = true;
							}
					}

				}
				enemyMovement = 0;

			}

		}
		//attack mode
		if(input.isKeyDown(Input.KEY_A)){
			attack = true;
			System.out.println("Currently in attack mode");

		}
		if(attack == true){
			if(input.isKeyDown(Input.KEY_S)){
				attack = false;
			}
		}

		//end turn mode
		if(input.isKeyPressed(Input.KEY_E)){			
			enemyTurn = true;	
			tookDamage = false;
			heroMovement = 0;	
			System.out.println("Currently in enemy turn");
		}

		if(enemyTurn == true){
			//checking maximum movement for enemy 
			float xdif;
			float ydif;
			if(enemyMovement<=4*SIZE)	{
				//magic calculations!!
				xdif = Math.abs(enemyPos.x - x);
				ydif = Math.abs(enemyPos.y - y);
				if ( xdif > ydif ) {
					k = 2;
					l = 1;
					enemyMovement += k+l;
				}
				if ( ydif > xdif ) {
					l = 2;
					k = 1;
					enemyMovement += k+l;
				}
				if ( xdif == 0 ) {
					l = 3;
					k = 0;
					enemyMovement += k+l;
				}
				if ( ydif == 0 ) {
					k = 3;
					l = 0;
					enemyMovement += k+l;
				}
				if ( xdif == ydif ) {
					k = 1;
					l = 1;
					enemyMovement += k+l;
				}
				if ( enemyPos.x > x )
					k*= -1;
				if ( enemyPos.y > y )
					l*=-1;
				enemyPos.translate((int)k, (int)l);
			}
			//signal end of enemy turn when maximum movement reached
			if(enemyMovement > 4*SIZE){
				/*
				 * if(xdif<=32 && ydif<=32){
				 * 
				 * }
				 */
				
				enemyTurn = false;
			}
			
			//delaying update for smoooooooooth movement
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
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

	private boolean isWarp(float x, float y)
	{
		int xBlock = (int)x / SIZE;
		int yBlock = (int)y / SIZE;
		return warps[xBlock][yBlock];
	}
	private boolean isTrap(float x, float y)
	{
		int xBlock = (int)x / SIZE;
		int yBlock = (int)y / SIZE;
		return traps[xBlock][yBlock];
		}


	@Override
	public int getID() {
		return 1;
	}

}
