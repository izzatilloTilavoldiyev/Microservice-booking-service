package uz.pdp.bookingservice.utils;

public class BeanUtil {
    public static <T extends Enum<T>> boolean isValidEnumValue(Class<T> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
