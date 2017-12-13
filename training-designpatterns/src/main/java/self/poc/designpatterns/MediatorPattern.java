package self.poc.designpatterns;

public class MediatorPattern {
	public static void main(String[] args) {
		System.out.println("Mediator Pattern Demonstration.");
		System.out.println("-------------------------------");
		// Construct mediator and colleagues
		System.out.println("Constructing mediator and colleagues.");
		MediatorIF mediator = new ConcreteMediator();
		ColleagueIF colleague1 = new ConcreteColleague1(mediator);
		ColleagueIF colleague2 = new ConcreteColleague2(mediator);
		// Display colleague values.
		System.out.println("Displaying colleague states.");
		System.out.println("colleague1.toString()=" + colleague1);
		System.out.println("colleague2.toString()=" + colleague2);
		// Change state on colleague1 and the mediator
		// will coordinate the change with colleague2.
		System.out.println("Calling colleague1.changeState()");
		((ConcreteColleague1) colleague1).changeState();
		// Display colleague values now.
		System.out.println("Displaying colleague states now.");
		System.out.println("colleague1.toString()=" + colleague1);
		System.out.println("colleague2.toString()=" + colleague2);
		// Change state on colleague2 and see what happens.
		System.out.println("Calling colleague2.changeState()");
		((ConcreteColleague2) colleague2).changeState();
		// Display colleague values now.
		System.out.println("Displaying colleague states again.");
		System.out.println("colleague1.toString()=" + colleague1);
		System.out.println("colleague2.toString()=" + colleague2);
		System.out.println();
	}
}

interface ColleagueIF {
}

class ConcreteColleague1 implements ColleagueIF {
	private MediatorIF mediator;
	// This colleague uses a boolean for it's state.
	private boolean state;

	public ConcreteColleague1(MediatorIF parm) {
		this.mediator = parm;
		this.mediator.registerColleague1(this);
	}

	public void setState(boolean parm) {
		this.state = parm;
	}

	public void changeState() {
		state = state ? false : true;
		mediator.state1Changed();
	}

	public String toString() {
		return new Boolean(state).toString();
	}
}

class ConcreteColleague2 implements ColleagueIF {
	private MediatorIF mediator;
	// This colleague uses a string for its state.
	private String state = "false";

	public ConcreteColleague2(MediatorIF parm) {
		this.mediator = parm;
		this.mediator.registerColleague2(this);
	}

	public void setState(String parm) {
		this.state = parm;
	}

	public void changeState() {
		state = state.equals("false") ? "true" : "false";
		mediator.state2Changed();
	}

	public String toString() {
		return state;
	}
}

class ConcreteMediator implements MediatorIF {
	ColleagueIF colleague1;
	ColleagueIF colleague2;

	public void registerColleague1(ColleagueIF parm) {
		this.colleague1 = (ConcreteColleague1) parm;
	}

	public void registerColleague2(ColleagueIF parm) {
		this.colleague2 = (ConcreteColleague2) parm;
	}

	public void state1Changed() {
		String s = (colleague2.toString().equals("true")) ? "false" : "true";
		((ConcreteColleague2) colleague2).setState(s);
	}

	public void state2Changed() {
		boolean b = (colleague1.toString().equals("true")) ? false : true;
		((ConcreteColleague1) colleague1).setState(b);
	}
}

interface MediatorIF {
	// Interface for communicating with colleagues
	public void registerColleague1(ColleagueIF parm);

	public void registerColleague2(ColleagueIF parm);

	public void state1Changed();

	public void state2Changed();
}