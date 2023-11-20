package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class Decision {
	Decision trueNode;
	Decision falseNode;
	
	public Decision() {
		this.trueNode = null;
		this.falseNode = null;
	}
	
	public void setTrueNode(Decision trueNode) {
		this.trueNode = trueNode;
	}
	
	public void setFalseNode(Decision falseNode) {
		this.falseNode = falseNode;
	}
	
	public Decision getBranch(boolean testValue) {
		if (testValue) {
			return trueNode;
		}
		else {
			return falseNode;
		}
	}
	
	public Decision makeDecision(boolean testValue) {
		return this.getBranch(testValue);
	}
}