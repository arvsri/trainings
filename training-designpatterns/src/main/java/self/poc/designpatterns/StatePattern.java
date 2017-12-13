package self.poc.designpatterns;

public class StatePattern {
	public static void main(String[] args) {
		System.out.println("State Pattern Demonstration.");
		System.out.println("----------------------------");
		// Construct context.
		System.out.println("Constructing context.");
		MyContext context = new MyContext();
		// Call request, make state handle the request.
		System.out.println("Calling context.request().");
		context.request();
		// Flip state.
		System.out.println("Calling context.changeState().");
		context.changeState();
		// call request.
		System.out.println("Calling context.request().");
		context.request();
		System.out.println();
	}
}

class ConcreteState1 implements StateIF {
	public void handle() {
		System.out.println("ConcreteState1.handle() called.");
	}
}
class ConcreteState2 implements StateIF {
	public void handle() {
		System.out.println("ConcreteState2.handle() called.");
	}
}

interface StateIF {
	public void handle();
}

class MyContext {
	// Initial state.
	private StateIF state = new ConcreteState1();

	// Request operation.
	public void request() {
		state.handle();
	}

	// Switch states
	public void changeState() {
		if (state instanceof ConcreteState1)
			state = new ConcreteState2();
		else
			state = new ConcreteState1();
	}
}

