package ba.unsa.etf.rma.ena.dms_android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elvedin on 11/01/2018.
 *
 */

public class Utils {
    public static final String URL="http://192.168.0.18:8080/dms/";

    public static boolean matchRegex(String stringToCheck, String patternToMatch){
        Pattern pattern = Pattern.compile(patternToMatch);
        Matcher matcher = pattern.matcher(stringToCheck);
        return matcher.matches();
    }

}
