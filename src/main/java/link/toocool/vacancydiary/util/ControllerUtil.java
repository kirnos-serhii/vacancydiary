package link.toocool.vacancydiary.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ControllerUtil {

    public static String getErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .reduce((s, s2) -> s + Strings.LINE_SEPARATOR + s2).orElse(Strings.EMPTY);
    }
}
