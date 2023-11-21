package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class DecisionTree {
	private FloatDecision root; //root of the decision tree
	
	//the decision tree is designed as a 2d array of floats with each array within the tree corresponding to a node in the tree.
	//the first two values in each node represent a range in which the observation value must fall between for the node to return true.
	//the third value represents the action the node will return. 0 is stop, 1 is walk, 2 is run, and 3 is jump. -1 indicates that it is not a leaf node and will not return an action.
	//the fourth value represents the node's index in the tree so that it knows which index of the observations array to grab from.
	private float[] rootData = {0, 2, -1, 0};
	private float[][] treeData1 = {{0, 1, -1, 1}, {0, 1, -1, 3}, {0, 1, 2, 7}, {0, 1, 3, 8}, {0, 1, 3, 4}, null, null};
	private float[][] treeData2 = {{0, 1, 3, 2}, null, null, null, null, null, null};
	
	public DecisionTree() {
		root = new FloatDecision(rootData, createDecision(treeData1), createDecision(treeData2));
	}
	
	//function to create a new decision
	public FloatDecision createDecision(float[][] data) {
		//if the node is null or there is no more nodes left, returns null
		if (data.length == 0 || data[0] == null) {
			return null;
		}
		
		FloatDecision dec;
		
		//splits everything after the first element into two new arrays
		float[][] tempData1 = new float[data.length/2][4];
		float[][] tempData2 = new float[data.length/2][4];
		//copies the data into the new arrays
		for (int i = 1; i < data.length/2+1; i++) {
			if (data[i] == null) {
				tempData1[i-1] = null;
			} else {
				tempData1[i-1][0] = data[i][0];
				tempData1[i-1][1] = data[i][1];
				tempData1[i-1][2] = data[i][2];
				tempData1[i-1][3] = data[i][3];
			}
			if (data[i+data.length/2] == null) {
				tempData2[i-1] = null;
			} else {
				tempData2[i-1][0] = data[i+data.length/2][0];
				tempData2[i-1][1] = data[i+data.length/2][1];
				tempData2[i-1][2] = data[i+data.length/2][2];
				tempData2[i-1][3] = data[i+data.length/2][3];
			}
		}
		
		//makes a new decision with the first array value as the node and its child nodes are recursively made from the split array
		dec = new FloatDecision(data[0], createDecision(tempData1), createDecision(tempData2));
		
		return dec;
	}
	
	public int decide(float[] obs, MarioForwardModel model, MarioTimer timer) {
		return root.makeDecision(obs, model, timer); //passes in an array of floats for the tree to decide on
	}
	
}