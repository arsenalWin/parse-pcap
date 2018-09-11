package com.cetiti.handler;

import com.cetiti.model.AllRules;
import com.cetiti.model.Rule;
import com.cetiti.model.RulesOfVirtualId;
import com.cetiti.model.VirtualId;
import com.cetiti.util.Toolkit;
import com.cetiti.util.YamlUtil;
import com.google.polo.pairing.HexDump;
import io.pkts.PacketHandler;
import io.pkts.packet.IPv4Packet;
import io.pkts.packet.MACPacket;
import io.pkts.packet.Packet;
import io.pkts.protocol.Protocol;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 14:00 2018-9-3
 * @Modified By:
 */
public class TCPPacketHandler implements PacketHandler {
  /** 规则列表 */
  private AllRules rules;

  private List<RulesOfVirtualId> ruleList;

  /** 每个mac对应的所有虚拟身份 */
  private Map<String, Map<String, String>> results = new HashMap<>();


  public TCPPacketHandler() throws IOException {
    rules = YamlUtil.loadConf("rules.yml", AllRules.class);
    ruleList = rules.getRules();
  }

  @Override
  public boolean nextPacket(final Packet packet) throws IOException {
    if (packet.hasProtocol(Protocol.TCP)) {
      if (packet.getPacket(Protocol.TCP).getPayload() != null) {
        String destinationIp = ((IPv4Packet) packet.getPacket(Protocol.IPv4)).getDestinationIP();
        String macAddr = ((MACPacket) packet.getPacket(Protocol.ETHERNET_II)).getSourceMacAddress();
        byte[] bytes = packet.getPacket(Protocol.TCP).getPayload().getArray();

        if (bytes != null) {
          String payload = HexDump.toHexString(bytes);
          getVirtualId(payload, macAddr, destinationIp);
        }

      }
    }
    return true;
  }

  /**
   * 将检测到的虚拟身份写入文件
   *
   * @return
   */
  public void dumpToFile(String path) {
    try {
      YamlUtil.dumpConf(path, results);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Map<String, Map<String,String>> getResults() {
    return results;
  }

  public void setResults(Map<String, Map<String,String>> results) {
    this.results = results;
  }

  /**
   * 获取数据包中的虚拟身份
   */
  private void getVirtualId(String payload, String mac, String desIp) {
    if(results.get(mac) != null){
      results.get(mac).putAll(getVirtualIdFromPacket(payload,desIp));
    }else{
      results.put(mac, getVirtualIdFromPacket(payload,desIp));
    }

  }

  /**
   * 从packet包中解析出所有身份
   * @param payload
   * @return
   */
  private Map<String, String> getVirtualIdFromPacket(String payload, String desIp) {
    Map<String, String> virtualId = new HashMap<>();

    for(RulesOfVirtualId r : ruleList){
      List<Rule> rules = r.getRuleList();

      for(Rule rule : rules){
        if(rule.getDesIp().equals(desIp)){
          String regex = Toolkit.regexRule(payload, rule.getRegex());
          if(regex == null) { continue; }

          String str = Toolkit.hexToChar(regex);

          // 根据type设置vitualid字段
          virtualId.put(r.getType(), str);

          //退出匹配
          break;
        }
      }
    }

    return virtualId;
  }
}
