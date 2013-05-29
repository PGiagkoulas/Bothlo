import java.lang.reflect.Array;
import java.util.ArrayList;


public class Data {

	private Encounter[][] stageList;
	private ArrayList<Encounter> inventoryList;

	//constructor
	public Data(){
		stageList = new Encounter[20][16];
		inventoryList = new ArrayList<Encounter>(20);
	}

	//adding elements to the stage data structure
	public void addStageElement(Encounter e, int posX, int posY){
		stageList[posX][posY] = e;
	}

	//adding elements to the inventory data structure
	public void addInventoryElement(Encounter e, int posX, int posY){
		inventoryList.add(posX*posY, e);		
	}

	//removing elements from stage data structure
	public void removeStageElement(int posX, int posY){
		stageList[posX][posY] = null;
	}

	//removing elements from inventory data structure
	public void removeInventoryElement(int posX, int posY){
		inventoryList.remove(posX*posY);
	}
	
	//changing the position of an element in the stage data structure
	public void moveStageElement(int initPosX, int initPosY, int finalPosX, int finalPosY){
		stageList[finalPosX][finalPosY] = stageList[initPosX][initPosY];  
		stageList[initPosX][initPosY] = null;
	}
	
	//changing the position of an element in the inventory data structure
	public void moveInventoryElement(int initPosX, int initPosY, int finalPosX, int finalPosY){
		inventoryList.add(finalPosX*finalPosY, inventoryList.get(initPosX*initPosY));
		inventoryList.remove(initPosX*initPosY);		
	}
	
	//retrieving element from stage data structure
	public Encounter getStageElement(int posX, int posY){
		return stageList[posX][posY];
	}
}
