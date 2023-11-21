package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class FloatDecision {
	float minValue;
	float maxValue;
	FloatDecision trueNode;
	FloatDecision falseNode;
	int actionNum;
	int decIndex;
	Action action;
	
	public FloatDecision(float[] minmax, FloatDecision falseNode, FloatDecision trueNode) {
		this.minValue = minmax[0];
		this.maxValue = minmax[1];
		this.trueNode = trueNode;
		this.falseNode = falseNode;
		this.actionNum = (int)minmax[2];
		this.decIndex = (int)minmax[3];
		this.action = new Action();
	}
	
	public void setTrueNode(FloatDecision trueNode) {
		this.trueNode = trueNode;
	}
	
	public void setFalseNode(FloatDecision falseNode) {
		this.falseNode = falseNode;
	}
	
	//goes to the next node or returns an action if it is a leaf node
	public int makeDecision(float[] testValue, MarioForwardModel model, MarioTimer timer) {
		//non zero actionNum means it is a leaf node so it returns
		if (actionNum != -1) {
			return actionNum;
		} else {
			//checks if the observation value falls in the set range
			if (this.maxValue >= testValue[decIndex] && testValue[decIndex] >= this.minValue) {
				return trueNode.makeDecision(testValue, model, timer); //if it does, returns the truenode
			}
			else {
				return falseNode.makeDecision(testValue, model, timer);//if it doesn't, returns the falsenode
			}
		}
	}
}