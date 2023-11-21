package agents.SadlierGoodrich;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;
import java.util.Random;

/**
 * @author nsadlier
 */
public class Agent implements MarioAgent {
	private boolean[] action;
	private DecisionTree dTree;
	private Action actionMaker;
	
    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        this.action = new boolean[MarioActions.numberOfActions()];
        this.dTree= new DecisionTree();
        this.actionMaker = new Action();
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
    	int[][] objects = model.getMarioSceneObservation(); //gets all objects around mario
    	int[][] enemies = model.getMarioEnemiesObservation(); // gets all enemies around mario
    	float[] obs = new float[11]; //stores float values for each node in the decision tree
    	
    	//node 0 of tree: check for enemy ahead
    	if (enemies[11][8] != 0 || enemies[10][8] != 0 || enemies[9][8] != 0 || enemies[8][8] != 0) {
    		obs[0] = 1;
    	} else {
    		obs[0] = -1;
    	}
    	
    	//node 1 of tree: check for wall ahead
    	if (objects[13][8] != 0 || objects[12][8] != 0 || objects[11][8] != 0 || objects[10][8] != 0 || objects[9][8] != 0 || objects[8][8] != 0) {
    		obs[1] = 1;
    	} else {
    		obs[1] = -1;
    	}
    	
    	//node 3 of tree: check for gap ahead
    	if (objects[14][9] == 0 || objects[13][9] == 0 || objects[12][9] == 0) {
    		obs[3] = 1;
    	} else {
    		obs[3] = -1;
    	}
    	
    	//node 0 of tree: check if gap is close
    	if (objects[11][9] == 0 || objects[10][9] == 0 || objects[9][9] == 0 || objects[8][9] == 0) {
    		obs[4] = 1;
    	} else {
    		obs[4] = -1;
    	}

    	int actionNum = this.dTree.decide(obs, model, timer); //input the observations into the tree to return a number corresponding to an action
    	
    	//sets mario's action depending on the actionNum
    	if (actionNum == 0) {
    		action = actionMaker.stop(model, timer);
    	} else if (actionNum == 1) {
    		action = actionMaker.walk(model, timer);
    	} else if (actionNum == 2) {
    		action = actionMaker.run(model, timer);
    	} else if (actionNum == 3) {
    		//when jumping, mario will randomly jump while walking or running
    		Random rand = new Random();
    		int randJump = rand.nextInt(2);
    		if (randJump == 0) {
    			action = actionMaker.jump(model, timer);
    		} else {
    			action = actionMaker.jumpSlow(model, timer);
    		}
    	}
    	
        return action;
    }

    @Override
    public String getAgentName() {
        return "SadlierGoodrichAgent";
    }

}