package com.cetiti.model;

/**
 * @Author: zhouwei
 * @Description: 一条规则
 * @Date: Create in 9:51 2018-9-4
 * @Modified By:
 */
public class Rule {
  /**
   * 目标地址ip
   */
  private String desIp;

  /**
   * 正则匹配规则
   */
  private String regex;

  public String getDesIp() {
    return desIp;
  }

  public void setDesIp(String desIp) {
    this.desIp = desIp;
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }
}
