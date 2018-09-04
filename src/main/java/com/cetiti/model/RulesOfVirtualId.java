package com.cetiti.model;

import java.util.List;

/**
 * @Author: zhouwei
 * @Description: 对应一条虚拟身份的所有规则
 * @Date: Create in 9:56 2018-9-4
 * @Modified By:
 */
public class RulesOfVirtualId {
  /**
   * 虚拟身份的类型
   */
  private String type;

  /**
   * 虚拟身份的所有规则
   */
  private List<Rule> ruleList;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Rule> getRuleList() {
    return ruleList;
  }

  public void setRuleList(List<Rule> ruleList) {
    this.ruleList = ruleList;
  }
}
