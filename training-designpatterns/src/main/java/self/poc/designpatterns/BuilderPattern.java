package self.poc.designpatterns;

public class BuilderPattern {
	
	public static void main(String[] args) {
		System.out.println("Builder Pattern Demonstration.");
		System.out.println("------------------------------");
		// Create builder
		System.out.println("Constructing builder.");
		BuilderIF builder = new ConcreteBuilder();
		// Create director
		System.out.println("Constructing director.");
		Director director = new Director(builder);
		// Construct customer via director
		System.out.println("Constructing customer.");
		director.construct();
		// Get customer via builder
		CustomerIF customer = builder.getCustomer();
		// Use customer method
		System.out.println("Calling action on the customer.");
		customer.action();
		System.out.println();
	}
}

interface BuilderIF {
	public void buildCustomer();

	public CustomerIF getCustomer();
}

class ConcreteBuilder implements BuilderIF {
	CustomerIF customer;

	public void buildCustomer() {
		customer = new ConcreteCustomer();
		// You could add more customer processing here...
	}

	public CustomerIF getCustomer() {
		return customer;
	}
}

class ConcreteCustomer implements CustomerIF {
	public ConcreteCustomer() {
		System.out.println("ConcreteCustomer constructed.");
	}

	public void action() {
		System.out.println("ConcreteCustomer.action() called.");
	}
}

interface CustomerIF {
	public void action();
}

class Director {
	BuilderIF builder;

	public Director(BuilderIF parm) {
		this.builder = parm;
	}

	public void construct() {
		builder.buildCustomer();
	}
}