package uk.org.taverna.server.client;

/**
 * Custom Exception Class that encapsulate all exceptions relate
 * to the network connection problems
 * 
 * @author Hyde Zhang
 *
 */
public class NetworkConnectionException extends Exception {
	
	private String defaultMessage = 
			"Network connection error, please check your internect connection";

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882819468008676319L;

	/**
	 *  Parameterless Constructor
	 */
	public NetworkConnectionException(){
	}

	public NetworkConnectionException(String message, Exception e) {
		super(message, e);
	}

	@Override
	public String getMessage() {
		return defaultMessage;
	}
	
}
