package self.poc.designpatterns;

public class BridgePattern {
	public static void main(String[] args) {
		System.out.println("Bridge Pattern Demonstration.");
		System.out.println("-----------------------------");
		System.out.println("Constructing SportsCar and EconomyCar.");
		AbstractionIF car1 = new SportsCar();
		AbstractionIF car2 = new EconomyCar();
		System.out.println("Calling action() on SportsCar and EconomyCar.");
		car1.action();
		car2.action();
		System.out.println();
	}
}

interface AbstractionIF {
	public void action();
}

interface ImplementorIF {
	public void actionImplemented();
}


class SportsCar implements AbstractionIF {
	ImplementorIF implementor = new SportsCarImplementor();

	public SportsCar() {
		System.out.println("SportsCar constructed.");
	}

	public void action() {
		implementor.actionImplemented();
	}
}

class EconomyCar implements AbstractionIF {
	ImplementorIF implementor = new EconomyCarImplementor();

	public EconomyCar() {
		System.out.println("EconomyCar constructed.");
	}

	public void action() {
		implementor.actionImplemented();
	}
}

class SportsCarImplementor implements ImplementorIF {
	public SportsCarImplementor() {
		System.out.println("SportsCarImplementor constructed.");
	}

	public void actionImplemented() {
		System.out.println("SportsCarImplementor.actionImplemented() called.");
	}
}

class EconomyCarImplementor implements ImplementorIF {
	public EconomyCarImplementor() {
		System.out.println("EconomyCarImplementor constructed.");
	}

	public void actionImplemented() {
		System.out.println("EconomyCarImplementor.actionImplemented() called.");
	}
}

