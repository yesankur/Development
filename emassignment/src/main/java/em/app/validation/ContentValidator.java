package em.app.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class ContentValidator implements ConstraintValidator<ValidContent, String> {
//	 private String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//	 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
private String regex ="[^\\w\\d]*(([0-9]+.*[A-Za-z]+.*)|[A-Za-z]+.*([0-9]+.*))";
	 @Override
	 public boolean isValid(String email, ConstraintValidatorContext context) {
	 if (StringUtils.isBlank(email))
	 return true;

	 Pattern pat = Pattern.compile(regex);
	 return pat.matcher(email).matches();
	 }

	}