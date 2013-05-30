import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Game extends StateBasedGame  {

	public static final String gamename = "Bothlo";
	public static final int menu = 0;
	public static final int play = 1;
	
	public Game(String gamename){
		super(gamename);
		this.addState(new MenuGUI(menu));
		//creating hero when game starts
		Hero warrior = Hero.createHeroInstance("warrior", 1, 120, 3, 1,
				10, 8, 0, 0, 1, 
				3, "Shout", 5, 120,
				3, -1, 0.5, 
				0.2);
		
		this.addState(new GameState(play));
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
		
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(900, 384, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
		

	}


}
