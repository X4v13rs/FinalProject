package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator{

    private static final String PHONE_PATTERN ="^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) {
        Matcher matcher = Pattern.compile(PHONE_PATTERN).matcher(value.toString());
        if (!matcher.matches()){
            FacesMessage msg =
                    new FacesMessage("Contact Number validation failed.",
                            "Invalid Contact Number format.");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            throw new ValidatorException(msg);
        }
    }
}
