package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class FloatDecision extends Decision {
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
	
	public FloatDecision() {
		this.maxValue = 0;
		this.minValue = 0;
	}
	
	public void setTrueNode(FloatDecision trueNode) {
		this.trueNode = trueNode;
	}
	
	public void setFalseNode(FloatDecision falseNode) {
		this.falseNode = falseNode;
	}
	
	public int makeDecision(float[] testValue, MarioForwardModel model, MarioTimer timer) {
		if (actionNum != -1) {
			return actionNum;
		} else {
			if (this.maxValue >= testValue[decIndex] && testValue[decIndex] >= this.minValue) {
				return trueNode.makeDecision(testValue, model, timer);
			}
			else {
				return falseNode.makeDecision(testValue, model, timer);
			}
		}
	}
}