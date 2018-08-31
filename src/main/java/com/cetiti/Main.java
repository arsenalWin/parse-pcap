package com.cetiti;

import com.cetiti.util.YamlUtil;
import com.google.polo.pairing.HexDump;
import io.pkts.PacketHandler;
import io.pkts.Pcap;
import io.pkts.framer.FramingException;
import io.pkts.packet.IPv4Packet;
import io.pkts.packet.Packet;
import io.pkts.protocol.Protocol;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 13:47 2018-8-31
 * @Modified By:
 */
public class Main {
  public static void main(final String[] args) throws IOException, FramingException {
    final Pcap pcap = Pcap.openStream("dump.pcap");

    Map<String, Object> rules = YamlUtil.loadConf("rules.yml", Map.class);
    Map<String, String> qqRule = (Map<String, String>)rules.get("QQ");
    pcap.loop(
        new PacketHandler() {
          public boolean nextPacket(final Packet packet) throws IOException {
            if (packet.hasProtocol(Protocol.TCP)) {
              if (packet.getPacket(Protocol.TCP).getPayload() != null) {
                String destinationIp =
                    ((IPv4Packet) packet.getPacket(Protocol.IPv4)).getDestinationIP();
                byte[] bytes = packet.getPacket(Protocol.TCP).getPayload().getArray();
                if (bytes != null) {
                  String payload = HexDump.toHexString(bytes);
                  String qq = regexRule(payload, qqRule.get("rule"));
                  if (qq != null && qqRule.get("desIp").equals(destinationIp)) {
                    System.out.println(hexToChar(qq));
                  }
                }
              }
            }
            return true;
          }
        });
  }

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
