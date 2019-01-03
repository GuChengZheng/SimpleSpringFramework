package MySpring.Utils;

import java.util.Collection;

public class CommonUtils {
    public static boolean isNotEmpty(Collection<?> collection){
        return (null != collection && collection.size() > 0);
    }

    public boolean isNotEmpty(String string){
        return (null != string && !"".equals(string));
    }
}
