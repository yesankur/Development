package em.app.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class Validation {
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        
        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> {
        	errorList.add(fieldError.getObjectName()+"."+fieldError.getField()+" : " +fieldError.getDefaultMessage() +" : rejected value [" +fieldError.getRejectedValue() +"]" );
        });
        result.getGlobalErrors().forEach((fieldError) -> {
        	errorList.add(fieldError.getObjectName()+" : " +fieldError.getDefaultMessage() );
        });
        
        return new Error(HttpStatus.BAD_REQUEST, ex.getMessage(), errorList);
    }

    public static class Error{
    	private int errorCode;
    	private String error;
    	private String errorMessage;
    	private List<String> fieldErrors = new ArrayList<>();
    	
    	public Error(HttpStatus status, String message, List<String> fieldErrors ) {
    		this.errorCode = status.value();
    		this.error = status.name();
    		this.errorMessage = message;
    		this.fieldErrors = fieldErrors;
    	}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public List<String> getFieldErrors() {
			return fieldErrors;
		}

		public void setFieldErrors(List<String> fieldErrors) {
			this.fieldErrors = fieldErrors;
		}
    }
}
 
//public class Validation {
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//	
//}
