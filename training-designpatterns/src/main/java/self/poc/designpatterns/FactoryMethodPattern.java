package self.poc.designpatterns;

public class FactoryMethodPattern {
	public static void main(String[] args) {
		System.out.println("FactoryMethod Pattern Demonstration.");
		System.out.println("------------------------------------");
		// Create creator, which uses the FactoryMethod
		CreatorIF creator = new ConcreteCreator();
		// Create trade via factory method
		TradeIF trade = creator.factoryMethod();
		// Call trade action method
		trade.action();
		System.out.println();
	}
}

class ConcreteCreator implements CreatorIF {
	public TradeIF factoryMethod() {
		return new ConcreteTrade();
	}
}

class ConcreteTrade implements TradeIF {
	public void action() {
		System.out.println("ConcreteTrade.action() called.");
	}
}

interface CreatorIF {
	public abstract TradeIF factoryMethod();
}

interface TradeIF {
	public void action();
}