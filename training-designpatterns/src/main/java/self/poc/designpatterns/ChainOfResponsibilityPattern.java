package self.poc.designpatterns;

public class ChainOfResponsibilityPattern {
	public static void main(String[] args) {
		System.out.println("Chain Of Responsibility Pattern Demonstration.");
		System.out.println("----------------------------------------------");
		try {
			// Create Equity Order request.
			System.out.println("Creating Equity Order request.");
			Request equityOrderRequest = new Request(Request.EQUITY_ORDER);
			// Create Bond Order request.
			System.out.println("Creating Bond Order request.");
			Request bondOrderRequest = new Request(Request.BOND_ORDER);
			// Create a request handler.
			System.out.println("Creating 1st handler.");
			HandlerIF handler = new ConcreteHandler1();
			// Process the Equity Order.
			System.out.println("Calling 1st handler with Equity Order.");
			handler.processRequest(equityOrderRequest);
			// Process the Bond Order.
			System.out.println("Calling 1st handler with Bond Order");
			handler.processRequest(bondOrderRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
	}
}

interface HandlerIF {
	public void processRequest(Request request);
}



class ConcreteHandler1 implements HandlerIF {
	public void processRequest(Request parm) {
		// Start the processing chain here...
		switch (parm.getType()) {
		case Request.EQUITY_ORDER: // This object processes equity orders
			handleIt(parm); // so call the function to handle it.
			break;
		case Request.BOND_ORDER: // Another object processes bond orders so
			System.out.println("Creating 2nd handler."); // pass request along.
			new ConcreteHandler2().processRequest(parm);
			break;
		}
	}

	private void handleIt(Request parm) {
		System.out.println("ConcreteHandler1 has handled the processing.");
	}
}

class ConcreteHandler2 implements HandlerIF {
	public void processRequest(Request parm) {
		// You could add on to the processing chain here...
		handleIt(parm);
	}

	private void handleIt(Request parm) {
		System.out.println("ConcreteHandler2 has handled the processing.");
	}
}

class Request {
	// The universe of known requests that can be handled.
	public final static int EQUITY_ORDER = 100;
	public final static int BOND_ORDER = 200;
	// This objects type of request.
	private int type;

	public Request(int parm) throws Exception {
		// Validate the request type with the known universe.
		if ((parm == EQUITY_ORDER) || (parm == BOND_ORDER))
			// Store this request type.
			this.type = parm;
		else
			throw new Exception("Unknown Request type " + parm + ".");
	}

	public int getType() {
		return type;
	}
}