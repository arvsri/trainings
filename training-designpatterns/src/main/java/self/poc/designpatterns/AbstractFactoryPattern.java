package self.poc.designpatterns;

// Usage java.awt.Toolkit.
class AbstractFactoryPattern {
	public static void main(String[] args) {
		
		System.out.println("Abstract Factory Pattern Demonstration.");
		System.out.println("---------------------------------------");
		// Create abstract factories
		System.out.println("Constructing abstract factories.");
		AbstractFactoryIF factoryOne = new FordFactory();
		AbstractFactoryIF factoryTwo = new GMFactory();
		
		// Create cars via abstract factories
		System.out.println("Constructing cars.");
		AbstractSportsCarIF car1 = factoryOne.createSportsCar();
		AbstractEconomyCarIF car2 = factoryOne.createEconomyCar();
		AbstractSportsCarIF car3 = factoryTwo.createSportsCar();
		AbstractEconomyCarIF car4 = factoryTwo.createEconomyCar();
		
		// Execute drive on the cars
		System.out.println("Calling drive on the cars.");
		car1.driveFast();
		car2.driveSlow();
		car3.driveFast();
		car4.driveSlow();
		System.out.println();
	}
}

interface AbstractFactoryIF {
	AbstractSportsCarIF createSportsCar();

	AbstractEconomyCarIF createEconomyCar();
}

interface AbstractSportsCarIF {
	void driveFast();
}

interface AbstractEconomyCarIF {
	void driveSlow();
}

class FordFactory implements AbstractFactoryIF {
	public AbstractSportsCarIF createSportsCar() {
		return new Mustang();
	}

	public AbstractEconomyCarIF createEconomyCar() {
		return new Focus();
	}
}

class GMFactory implements AbstractFactoryIF {
	public AbstractSportsCarIF createSportsCar() {
		return new Corvette();
	}

	public AbstractEconomyCarIF createEconomyCar() {
		return new Cavalier();
	}
}

class Mustang implements AbstractSportsCarIF {
	public void driveFast() {
		System.out.println("Mustang.driveFast() called.");
	}
}

class Focus implements AbstractEconomyCarIF {
	public void driveSlow() {
		System.out.println("Focus.driveSlow() called.");
	}
}

class Corvette implements AbstractSportsCarIF {
	public void driveFast() {
		System.out.println("Corvette.driveFast() called.");
	}
}

class Cavalier implements AbstractEconomyCarIF {
	public void driveSlow() {
		System.out.println("Cavalier.driveSlow() called.");
	}
}