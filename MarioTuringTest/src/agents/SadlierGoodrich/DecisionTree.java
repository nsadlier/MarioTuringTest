package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class DecisionTree {
	private Action action;
	private FloatDecision root;
	private float[] rootData = {0, 2, -1, 0};
	private float[][] treeData1 = {{0, 1, -1, 1}, {0, 1, -1, 3}, {0, 1, 2, 7}, {0, 1, 3, 8}, {0, 1, 3, 4}, null, null};
	private float[][] treeData2 = {{0, 1, 3, 2}, null, null, null, null, null, null};
	
	public DecisionTree() {
		action = new Action();
		root = new FloatDecision(rootData, createDecision(treeData1), createDecision(treeData2));
	}
	
	public FloatDecision createDecision(float[][] data) {
		if (data.length == 0 || data[0] == null) {
			return null;
		}
		FloatDecision dec;
		if (data.length == 0) {
			dec = new FloatDecision(data[0], null, null);
			return dec;
		}
		float[][] tempData1 = new float[data.length/2][4];
		float[][] tempData2 = new float[data.length/2][4];
		for (int i = 1; i < data.length/2+1; i++) {
			if (data[i] == null) {
				tempData1[i-1] = null;
			} else {
				System.out.println(tempData1[i-1].length);
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
		
		dec = new FloatDecision(data[0], createDecision(tempData1), createDecision(tempData2));
		
		return dec;
	}
	
	public int decide(float[] obs, MarioForwardModel model, MarioTimer timer) {
		
		return root.makeDecision(obs, model, timer);
	}
	
}