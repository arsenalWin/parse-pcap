package com.cetiti.model;

/**
 * @Author: zhouwei
 * @Description: 虚拟身份类
 * @Date: Create in 10:23 2018-9-4
 * @Modified By:
 */
public class VirtualId {
  /**
   *  QQ号
   */
  private String qq;

  /**
   * IMEI信息
   */
  private String imei;

  /**
   * 京东账号信息
   */
  private String jd;

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getJd() {
    return jd;
  }

  public void setJd(String jd) {
    this.jd = jd;
  }
}
