package self.poc.designpatterns;

import java.util.HashMap;
import java.util.Map;

public class FlyweightPattern {
	public static void main(String[] args) {
		System.out.println("Flyweight Pattern Demonstration.");
		System.out.println("--------------------------------");
		// Create states
		State stateF = new State(false);
		State stateT = new State(true);
		// Get reference to (and in doing so create) flyweight
		FlyweightIF myfwkey1 = FlyweightFactory.getFlyweight("myfwkey");
		// Get new reference to the same flyweight
		FlyweightIF myfwkey2 = FlyweightFactory.getFlyweight("myfwkey");
		// Call action on both references
		System.out.println("Call flyweight action with state=false");
		myfwkey1.action(stateF);
		System.out.println("Call flyweight action with state=true");
		myfwkey2.action(stateT);
		System.out.println();
	}
}

interface FlyweightIF {
	// method to receive and act on extrinsic state.
	public void action(State parm);
}


class ConcreteFlyweight implements FlyweightIF {
	// Add state to the concrete flyweight.
	private boolean state;

	public ConcreteFlyweight(State parm) {
		this.state = parm.getState();
	}

	public void action(State parm) {
		// Display internal state and state passed by client.
		System.out.println("ConcreteFlyweight.action(" + parm.getState()
				+ ") called.");
		this.state = parm.getState();
		System.out.println("ConcreteFlyweight.state = " + this.state);
	}
}

class FlyweightFactory {
	private static Map map = new HashMap();

	public static FlyweightIF getFlyweight(String parm) {
		// Return the Flyweight if it exists,
		// or create it if it doesn't.
		FlyweightIF flyweight = null;
		try {
			if (map.containsKey(parm)) {
				// Return existing flyweight
				flyweight = (FlyweightIF) map.get(parm);
			} else {
				// Create flyweight with a 'true' state
				flyweight = new ConcreteFlyweight(new State(true));
				map.put(parm, flyweight);
				System.out.println("Created flyweight " + parm
						+ " with state=true");
				System.out.println("");
			}
		} catch (ClassCastException cce) {
			System.out.println(cce.getMessage());
		}
		return flyweight;
	}
}

class State {
	private boolean state;

	public State(boolean parm) {
		this.state = parm;
	}

	public boolean getState() {
		return state;
	}
}