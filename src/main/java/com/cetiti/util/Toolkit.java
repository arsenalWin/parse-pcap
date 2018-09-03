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
    rule = rule.replace("\\d+", "((30|31|32|33|34|35|36|37|38|39)+)");
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
