package self.poc.designpatterns;

class ProxyPattern {
	public static void main(String[] args) {
		System.out.println("Proxy Pattern Demonstration.");
		System.out.println("----------------------------");
		// Create service proxy (instantiates service too)
		System.out.println("Creating proxy to service.");
		ServiceIF proxy = new Proxy();
		// Call action method on service via proxy
		System.out.println("Calling action method on proxy.");
		proxy.action();
		System.out.println();
	}
}

interface ServiceIF {
	// Interface for Service and Proxy
	public void action();
}

class Proxy implements ServiceIF {
	// Proxy to be the service
	private Service service = new Service();

	public void action() {
		service.action();
	}
}

class Service implements ServiceIF {
	// Service to be proxied
	public Service() {
		System.out.println("Service constructed.");
	}

	public void action() {
		System.out.println("Service.action() called.");
	}
}

