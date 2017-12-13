package self.poc.designpatterns;

public class CommandPattern {
	public static void main(String[] args) {
		System.out.println("Command Pattern Demonstration.");
		System.out.println("------------------------------");
		// Create receiver objects.
		System.out.println("Creating receivers.");
		ReceiverIF order = new Order();
		ReceiverIF trade = new Trade();
		// Create commands passing in receiver objects.
		System.out.println("Creating commands.");
		CommandAbstract cmdOrder = new ConcreteCommand(order);
		CommandAbstract cmdTrade = new ConcreteCommand(trade);
		// Create invokers.
		System.out.println("Creating invokers.");
		Invoker invOrder = new Invoker();
		Invoker invTrade = new Invoker();
		// Storing commands in invokers respectively.
		System.out.println("Storing commands in invokers.");
		invOrder.storeCommand(cmdOrder);
		invTrade.storeCommand(cmdTrade);
		// Call invoke on the invoker to execute the command.
		System.out.println("Invoking the invokers.");
		invOrder.invoke();
		invTrade.invoke();
		System.out.println();
	}
}

abstract class CommandAbstract {
	public abstract void execute();
}

interface ReceiverIF {
	public void action();
}



class ConcreteCommand extends CommandAbstract {
	// The binding between action and receiver
	private ReceiverIF receiver;

	public ConcreteCommand(ReceiverIF receive) {
		this.receiver = receive;
	}

	public void execute() {
		receiver.action();
	}
}

class Invoker {
	private CommandAbstract command;

	public void storeCommand(CommandAbstract cmd) {
		this.command = cmd;
	}

	public void invoke() {
		command.execute();
	}
}

class Order implements ReceiverIF {
	public void action() {
		System.out.println("Order.action() called.");
	}
}

class Trade implements ReceiverIF {
	public void action() {
		System.out.println("Trade.action() called.");
	}
}