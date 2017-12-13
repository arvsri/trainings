package self.poc.designpatterns;

public class FacadePattern {
	public static void main(String[] args) {
		System.out.println("Facade Pattern Demonstration.");
		System.out.println("-----------------------------");
		// Construct and call Fa�ade
		System.out.println("Constructing facade.");
		Fa�ade fa�ade = new Fa�ade();
		System.out.println("Calling facade.processOrder().");
		fa�ade.processOrder();
		System.out.println();
	}
}

class Fa�ade {
	public void processOrder() {
		// Call methods on sub-systems to complete the process
		SubSystem1 subsys1 = new SubSystem1();
		subsys1.getCustomer();
		subsys1.getSecurity();
		subsys1.priceTransaction();
		SubSystemN subsysN = new SubSystemN();
		subsysN.checkBalances();
		subsysN.completeOrder();
	}
}

class SubSystem1 {
	public void getCustomer() {
		// Place functionality here...
		System.out.println("SubSystem1.getCustomer() called.");
	}

	public void getSecurity() {
		// Place functionality here...
		System.out.println("SubSystem1.getSecurity() called.");
	}

	public void priceTransaction() {
		// Place functionality here...
		System.out.println("SubSystem1.priceTransaction() called.");
	}
}

class SubSystemN {
	public void checkBalances() {
		// Place functionality here...
		System.out.println("SubSystemN.checkBalances() called.");
	}

	public void completeOrder() {
		// Place functionality here...
		System.out.println("SubSystemN.completeOrder() called.");
	}
}