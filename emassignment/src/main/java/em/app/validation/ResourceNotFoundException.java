package em.app.validation;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String exception,String exString, Long long1) {
		super("ResourceNot Available");
	}
}
