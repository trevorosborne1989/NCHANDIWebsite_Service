package gov.srs.spring.services.NCHANDIWebsite_Service.exceptions;

public class MissingRolesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5759063641963168030L;

	public MissingRolesException() {}

	public MissingRolesException(String message) {
		super(message);
	}
	
	public MissingRolesException(Throwable cause)	{
		super(cause);
	}

	public MissingRolesException(String message, Throwable cause)	{
		super(message, cause);
	}

	public MissingRolesException(String message, Throwable cause, 
                                       boolean enableSuppression, boolean writableStackTrace)	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
