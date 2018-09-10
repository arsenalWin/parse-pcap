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

  /**
   * IMSI信息
   */
  private String imsi;

  /**
   * 手机号信息
   */
  private String mobileNum;

  /**
   * 163邮箱
   */
  private String user163;

  /**
   * 系统版本号
   */
  private String osVersion;

  //todo: 继续添加其他虚拟身份

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

  public String getImsi() {
    return imsi;
  }

  public void setImsi(String imsi) {
    this.imsi = imsi;
  }

  public String getMobileNum() {
    return mobileNum;
  }

  public void setMobileNum(String mobileNum) {
    this.mobileNum = mobileNum;
  }

  public String getUser163() {
    return user163;
  }

  public void setUser163(String user163) {
    this.user163 = user163;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }
}
