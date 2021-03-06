package com.cetiti.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 14:04 2018-9-3
 * @Modified By:
 */
public class Toolkit {
  /**
   * 从TCP数据中匹配出符合规则的字符串信息
   * @param payload
   * @param rule
   * @return
   */
  public static String regexRule(String payload, String rule){
    //纯数字
    rule = rule.replace("(\\d+)", "((30|31|32|33|34|35|36|37|38|39)+)");
    //（0-9）| .
    rule = rule.replace("(\\ddot+)", "((30|31|32|33|34|35|36|37|38|39|2E)+)");
    //数字，字母，下划线，@，dot
    rule =
        rule.replace(
            "(\\w+)",
            "((30|31|32|33|34|35|36|37|38|39|61|62|63|64|65|66|67|68|69|6A|6B|6C|6D|6E|6F|62|71|72|73|74|75|76|77|78|79|7A|41|42|43|44|45|46|47|48|49|4A|4B|4C|4D|4E|4F|42|51|52|53|54|55|56|57|58|59|5A|5F|2E|40)+)");
    Pattern pattern = Pattern.compile(rule);
    Matcher matcher = pattern.matcher(payload);

    while(matcher.find()){
      String str = matcher.group();
      return str.replaceAll(rule,"$1");
    }
    return null;
  }

  /**
   * 将十六进制转成字符
   * 393830363530383030 -> 980650800
   * @param s 十六进制字符串
   * @return 字符
   */
  public static String hexToChar(String s){
    byte[] baKeyword = new byte[s.length() / 2];
    for (int i = 0; i < baKeyword.length; i++) {
      try {
        baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                i * 2, i * 2 + 2), 16));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    try {
      s = new String(baKeyword, "utf-8");
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return s;
  }
}
