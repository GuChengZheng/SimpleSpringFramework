package MySpring.Utils.FrameWork;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: gucheng.zheng
 * @create: 2019-04-07 17-25
 **/
public class MyStringUtils {

    public static String upperFirst(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }else{
            char upperChar = Character.toUpperCase(str.charAt(0));
            return str.length() > 1 ? upperChar + str.substring(1) : Character.toString(upperChar);
        }
    }
}
