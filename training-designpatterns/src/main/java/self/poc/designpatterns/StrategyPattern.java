package self.poc.designpatterns;

public class StrategyPattern {
	public static void main(String[] args) {
		System.out.println("Strategy Pattern Demonstration.");
		System.out.println("-------------------------------");
		// Construct strategies.
		System.out.println("Constructing strategies.");
		StrategyIF strategy1 = new ConcreteStrategy1();
		StrategyIF strategy2 = new ConcreteStrategy2();
		// Construct contexts.
		System.out.println("Constructing contexts.");
		Context context1 = new Context(strategy1);
		Context context2 = new Context(strategy2);
		// Execute contextInterface.
		System.out.println("Constructing context interfaces.");
		context1.contextInterface("J2EE Unleashed");
		context2.contextInterface("J2EE Unleashed");
		context1.contextInterface("The Secret Commissions");
		context2.contextInterface("The Secret Commissions");
		System.out.println();
	}
}

interface StrategyIF {
	public void algorithmInterface(String parm);
}

class ConcreteStrategy1 implements StrategyIF {
	// Switch text to all upper case.
	public void algorithmInterface(String parm) {
		System.out.println(parm.toUpperCase());
	}
}

class ConcreteStrategy2 implements StrategyIF {
	// Switch text beginning with "the".
	public void algorithmInterface(String parm) {
		System.out.println((parm.toLowerCase().startsWith("the ")) ? parm
				.substring(4) + ", " + parm.substring(0, 4) : parm);
	}
}

class Context {
	// Reference to the strategy.
	StrategyIF strategy;

	// Register reference to strategy on construction.
	public Context(StrategyIF parm) {
		this.strategy = parm;
	}

	// Call strategy's method.
	public void contextInterface(String parm) {
		strategy.algorithmInterface(parm);
	}
}

