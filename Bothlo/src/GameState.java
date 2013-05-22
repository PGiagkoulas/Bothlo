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
	private Animation Mage , upM ,downM,leftM,rightM;
	private Animation Rogue , upR ,downR,leftR,rightR;
	private Animation Cleric , upC ,downC,leftC,rightC;
	
	private boolean[][] blocked;
	
	private static final int SIZE = 32;
	
	private float x = 240f, y = 540f;
	
	public GameState(int State){
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		//changing resolution
		//AppGameContainer apgc = (AppGameContainer)gc;
		//apgc.setDisplayMode(482, 600, false);
		
		
		Image [] movementWarrior = {new Image("res/warrior.png"),new Image("res/warrior.png")};
        Image [] movementMage = {new Image("res/Mage.png"), new Image("res/Mage.png")};
        Image [] movementRogue = {new Image("res/rogue.png"), new Image("res/rogue.png")};
        Image [] movementCleric = {new Image("res/cleric.png"),new Image("res/cleric.png")};
		grassMap = new TiledMap("res/testproject1.tmx");
		int [] duration = {1, 1};
		
		upW = new Animation(movementWarrior, duration, false);
        upM = new Animation(movementMage, duration, false);
        upR = new Animation(movementRogue, duration,false );
        upC = new Animation(movementCleric, duration, false);
        
        leftW = new Animation(movementWarrior, duration, false);
        leftM = new Animation(movementMage, duration, false);   
        leftR = new Animation(movementRogue, duration, false);     
        leftC = new Animation(movementCleric, duration, false);
        

        
        rightW = new Animation(movementWarrior, duration, false);
        rightM = new Animation(movementMage, duration, false);
        rightR = new Animation(movementRogue, duration, false);
        rightC = new Animation(movementCleric, duration, false);
        
        downW = new Animation(movementWarrior, duration, false);
        downM = new Animation(movementMage, duration, false);
        downR = new Animation(movementRogue, duration, false);
        downC = new Animation(movementCleric, duration, false);
        
        Warrior = downW;
        Rogue = downR;
        Mage = downM;
        Cleric = downC;
        
        blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
        String Layername = grassMap.getLayerProperty(2, "blocked", "true");
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
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		grassMap.render(0,0);
		Warrior.draw((int)x, (int)y);
		Mage.draw((int)x-30, (int)y+30);
		Rogue.draw((int)x, (int)y+30);
		Cleric.draw((int)x+30, (int)y+30);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException {
		Input input = gc.getInput();
		float fdelta=delta*0.1f;
		if (input.isKeyDown(Input.KEY_UP))
		{
			Warrior = upW;
			Rogue = upR;
			Mage = upM;
			Cleric = upC;
			if(y <0)
				y=0;
			if (!(isBlocked(x, y- fdelta) || isBlocked(x+SIZE-1, y - fdelta)))
			{
				Warrior.update(delta);
				Rogue.update(delta);
				Mage.update(delta);
				Cleric.update(delta);
				// The lower the delta the slowest the sprite will animate.
				y -= fdelta;
			}
		}
		else if (input.isKeyDown(Input.KEY_DOWN))
		{
			Warrior = downW;
			Rogue =downR;
			Mage = downM;
			Cleric = downC;
			if (y>540)
				y=540;
			if (!(isBlocked(x, y + SIZE + fdelta) || isBlocked(x+SIZE-1, y + SIZE + fdelta)))
			{
				Warrior.update(delta);
				Rogue.update(delta);
				Mage.update(delta);
				Cleric.update(delta);
				y += fdelta;
			}
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			Warrior = leftW;
			Rogue = leftR;
			Mage = leftM;
			Cleric = leftC;
			if(x<30)
				x=30;
			if (!(isBlocked(x - fdelta, y) || isBlocked(x - fdelta, y+SIZE-1)))
			{
				Warrior.update(delta);
				Rogue.update(delta);
				Mage.update(delta);
				Cleric.update(delta);
				x -= fdelta;
			}
		}
		else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			Warrior = rightW;
			Rogue = rightR;
			Mage = rightM;
			Cleric = rightC;
			if(x>420)
				x=420;
			if (!(isBlocked(x + SIZE + fdelta, y) || isBlocked(x + SIZE + fdelta, y+SIZE-1)))
			{
				Warrior.update(delta);
				Rogue.update(delta);
				Mage.update(delta);
				Cleric.update(delta);
				x += fdelta;
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
