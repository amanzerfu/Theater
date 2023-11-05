package et.com.qena.theater.validations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class RegistrationValidation {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$");
    private static  final Pattern MIN_MAX_LENGTH_PATTERN = Pattern.compile("^.{6,30}$");
    private static Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]+$");

    private static Matcher matcher;



    public static boolean isValidEmail(String email)
    {
         matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidUsername(String username)
    {
        Matcher matcher = USERNAME_PATTERN.matcher(username);
        return matcher.matches();
    }
    public static boolean isValidLength(String str, int minLength, int maxLength) {
        matcher = MIN_MAX_LENGTH_PATTERN.matcher(str);
        return matcher.matches() && matcher.group(0).length() >= minLength && matcher.group(0).length() <= maxLength;
    }

}
