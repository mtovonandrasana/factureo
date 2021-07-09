package mg.mtovonandrasana.factureo.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class FactureoUtils {

    private FactureoUtils() {}

    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String MG_PHONE_REGEX = "^(((\\+[2]{1}[6]{1}[1]{1}( )?)(\\([0]{1}\\))?( )?([3]{1}[2-4]{1}))|([0]{1}[3]{1}[2-4]{1}))( )?(\\d{2})( )?(\\d{3})( )?(\\d{2})( )?$";
    private static final String STAT_REGEX = "^([1-9]{1}\\d{4}( )?\\d{2}( )?\\d{4}( )?\\d{1}( )?\\d{5})$"; 
    private static final String NIF_REGEX = "^([1-9]{1}\\d{3}( )?\\d{3}( )?\\d{3}( )?)$";
    private static final String CIN_REGEX = "^([1-9]{1}\\d{2}( )?\\d{3}( )?\\d{3}( )?\\d{3}( )?)$";
    private static final String POSTAL_CODE_REGEX = "^([1-9]{1}\\d{2}( )?)$";
    
    public static boolean validateNif(String nif) {
        if(StringUtils.isBlank(nif))
            return false;
        var pattern = Pattern.compile(NIF_REGEX);
        var matcher = pattern.matcher(nif);
        return matcher.matches();
    }

    public static boolean validateStat(String stat) {
        if(StringUtils.isBlank(stat))
            return false;
        var pattern = Pattern.compile(STAT_REGEX);
        var matcher = pattern.matcher(stat);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        if(StringUtils.isBlank(email))
            return false;
        var pattern = Pattern.compile(EMAIL_REGEX);
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if(StringUtils.isBlank(phoneNumber))
            return false;
        var pattern = Pattern.compile(MG_PHONE_REGEX);
        var matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean validateCin(String cin) {
        if(StringUtils.isBlank(cin))
            return false;
        var pattern = Pattern.compile(CIN_REGEX);
        var matcher = pattern.matcher(cin);
        return matcher.matches();
    }

    public static boolean validatePostalCode(String postalCode) {
        if(StringUtils.isBlank(postalCode))
            return false;
        var pattern = Pattern.compile(POSTAL_CODE_REGEX);
        var matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}
