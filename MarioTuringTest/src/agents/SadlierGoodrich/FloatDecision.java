package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class FloatDecision extends Decision {
	float minValue;
	float maxValue;
	float testValue;
	DecisionTree trueNode;
	DecisionTree falseNode;
	
	public void createFloatDecision (float max, float min) {
		this.maxValue = max;
		this.minValue = min;
	}
	
	public void setTrueNode(DecisionTree trueNode) {
		this.trueNode = trueNode;
	}
	
	public void setFalseNode(DecisionTree falseNode) {
		this.falseNode = falseNode;
	}
	
	public void setTestValue(float testValue) {
		this.testValue = testValue;
	}
	
	public DecisionTree getBranch() {
		if (this.maxValue >= this.testValue >= this.minValue) {
			return trueNode;
		}
		else {
			return falseNode;
		}
	}
}