package self.poc.designpatterns;

public class DecoratorPattern {
	
	public static void main(String[] args) {
		System.out.println("Decorator Pattern Demonstration.");
		System.out.println("--------------------------------");
		// Create object decorated with A
		System.out.println("Creating component decorated with A.");
		ComponentIF decorated1 = new ConcreteDecoratorA();
		// Call action on object decorated with A
		System.out.println("Calling action() on component decorated with A.");
		decorated1.action();
		// Create object decorated with B
		System.out.println("Creating component decorated with B.");
		ComponentIF decorated2 = new ConcreteDecoratorB();
		// Call action on object decorated with B
		System.out.println("Calling action() on component decorated with B.");
		decorated2.action();
		System.out.println();
	}
	
}

interface ComponentIF {
	public void action();
}

class ConcreteComponent implements ComponentIF {
	public void action() {
		System.out.println("ConcreteComponent.action() called.");
	}
}

class ConcreteDecoratorA extends Decorator {
	String addedVariable;

	public void action() {
		super.action();
		System.out.println("ConcreteDecoratorA.action() called.");
		addedVariable = "extra";
		System.out.println("ConcreteDecoratorA.addedVariable=" + addedVariable);
	}
}

class ConcreteDecoratorB extends Decorator {
	public void action() {
		super.action();
		System.out.println("ConcreteDecoratorB.action() called.");
		addedMethod();
	}

	private void addedMethod() {
		System.out.println("ConcreteDecoratorB.addedMethod() called.");
	}
}

class Decorator implements ComponentIF {
	ComponentIF component = new ConcreteComponent();

	public void action() {
		component.action();
	}
}