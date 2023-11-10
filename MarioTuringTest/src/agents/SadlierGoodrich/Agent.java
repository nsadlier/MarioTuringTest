package agents.SadlierGoodrich;

import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

/**
 * @author nsadlier
 */
public class Agent implements MarioAgent {
	private boolean[] action;
	
    @Override
    public void initialize(MarioForwardModel model, MarioTimer timer) {
        this.action = new boolean[MarioActions.numberOfActions()];
    }

    @Override
    public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
    	action[MarioActions.RIGHT.getValue()] = true;
        return action;
    }

    @Override
    public String getAgentName() {
        return "SadlierGoodrichAgent";
    }

}