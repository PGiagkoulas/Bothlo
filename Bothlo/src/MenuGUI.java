import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuGUI extends BasicGameState{

	Image backroundImg;
	Image buttonImg;
	Image exitImg;
	String mouse = "faesfa";
	
	public MenuGUI(int state){
			
		}
		
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		backroundImg = new Image("res/backroundImg.jpg");
		buttonImg = new Image("res/buttonImg.png");
		exitImg = new Image("res/exitImg.png");
			
	}
		
	public void render(GameContainer gr, StateBasedGame sbg, Graphics g) throws SlickException{
			backroundImg.draw();
			g.drawString(mouse, 700,270);
			buttonImg.draw(340,30);
			buttonImg.draw(340, 182);
			buttonImg.draw(340, 242);
			exitImg.draw(375, 322);
			g.setColor(Color.black);
			g.drawString("Play",422, 43);
			
			
		}
		
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
			Input input = gc.getInput();
			int xpos = Mouse.getX();
			int ypos = Mouse.getY();
			mouse = "X: "+xpos+" Y: "+ypos;
			// play button
			if((xpos > 340 && xpos <547 )&&(ypos>320)&&(ypos<351)){
				if(input.isMouseButtonDown(0)){
					AppGameContainer apgc = (AppGameContainer)gc;
					apgc.setDisplayMode(482, 600, false);
					sbg.enterState(1);
				}
			}
			//exit button
			if((xpos > 375 && xpos <519 )&&(ypos>33)&&(ypos<60)){
				if(input.isMouseButtonDown(0)){
					System.exit(0);
				}
			}
		}
		
		@Override
	public int getID() {
		return 0;
	}
		
	
	
	



	

}
