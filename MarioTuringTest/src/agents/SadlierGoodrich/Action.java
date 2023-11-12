package agents.SadlierGoodrich;

import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.GameStatus;
import engine.helper.MarioActions;

public class Action extends DecisionTree {
	private boolean[] action;
	//blah
	


	public boolean[] jump(MarioForwardModel model, MarioTimer timer) {
		action[MarioActions.RIGHT.getValue()] = true;
		action[MarioActions.SPEED.getValue()] = true;
		action[MarioActions.JUMP.getValue()] = model.mayMarioJump() || !model.isMarioOnGround();
	
		return action;
	}
	
	public boolean[] run(MarioForwardModel model, MarioTimer timer) {
		action[MarioActions.RIGHT.getValue()] = true;
		action[MarioActions.SPEED.getValue()] = true;
		
		return action;
	}
	
	public boolean[] walk(MarioForwardModel model, MarioTimer timer) {
		action[MarioActions.RIGHT.getValue()] = true;
		action[MarioActions.SPEED.getValue()] = false;
		
		return action;
	}
}