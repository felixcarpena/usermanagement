package app.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class Email {
    final private String value;

    public Email(String value) throws InvalidMailException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new InvalidMailException(value);
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        return obj == this || this.value.equals(((Email) obj).value);
    }

}
