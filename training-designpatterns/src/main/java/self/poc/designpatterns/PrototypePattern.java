package self.poc.designpatterns;

public class PrototypePattern {
	
	public static void main(String[] args) {
		System.out.println("Prototype Pattern Demonstration.");
		System.out.println("--------------------------------");
		// Create prototypes
		System.out.println("Constructing prototypes.");
		PrototypeIF prototype1 = new ConcretePrototype1();
		PrototypeIF prototype2 = new ConcretePrototype2();
		// Get clones from prototypes
		System.out.println("Constructing clones from prototypes.");
		PrototypeIF clone1 = prototype1.getClone();
		PrototypeIF clone2 = prototype2.getClone();
		// Call actions on the clones
		System.out.println("Calling actions on the clones.");
		clone1.action();
		clone2.action();
		System.out.println();
	}
	
}

class ConcretePrototype1 implements PrototypeIF {
	public ConcretePrototype1() {
		System.out.println("ConcretePrototype1 constructed.");
	}

	public PrototypeIF getClone() {
		// if required, put deep copy code here
		return new ConcretePrototype1();
	}

	public void action() {
		System.out.println("ConcretePrototype1.action() called");
	}
}

class ConcretePrototype2 implements PrototypeIF {
	public ConcretePrototype2() {
		System.out.println("ConcretePrototype2 constructed.");
	}

	public PrototypeIF getClone() {
		// if required, put deep copy code here
		return new ConcretePrototype1();
	}

	public void action() {
		System.out.println("ConcretePrototype2.action() called.");
	}
}

interface PrototypeIF {
	public PrototypeIF getClone(); // as opposed to Object.clone()

	public void action();
}