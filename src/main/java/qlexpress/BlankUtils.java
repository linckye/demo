package qlexpress;

import java.util.Collection;
import java.util.Map;

/**
 * @author linckye
 */
public class BlankUtils {

    public static boolean isEmpty(String o) {
        return o == null || o.isEmpty();
    }

    public static boolean isEmpty(Collection o) {
        return o == null || o.isEmpty();
    }

    public static boolean isEmpty(Map o) {
        return o == null || o.isEmpty();
    }

}
