package com.cetiti.handler;

import com.cetiti.util.Toolkit;
import com.cetiti.util.YamlUtil;
import com.google.polo.pairing.HexDump;
import io.pkts.PacketHandler;
import io.pkts.packet.IPv4Packet;
import io.pkts.packet.Packet;
import io.pkts.protocol.Protocol;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 14:00 2018-9-3
 * @Modified By:
 */
public class TCPPacketHandler implements PacketHandler {
  Map<String, Object> rules = YamlUtil.loadConf("rules.yml", Map.class);
  Map<String, String> qqRule = (Map<String, String>)rules.get("QQ");

  public TCPPacketHandler() throws IOException {
  }

  @Override
  public boolean nextPacket(final Packet packet) throws IOException {
    if (packet.hasProtocol(Protocol.TCP)) {
      if (packet.getPacket(Protocol.TCP).getPayload() != null) {
        String destinationIp =
                ((IPv4Packet) packet.getPacket(Protocol.IPv4)).getDestinationIP();
        byte[] bytes = packet.getPacket(Protocol.TCP).getPayload().getArray();
        if (bytes != null) {
          String payload = HexDump.toHexString(bytes);
          String qq = Toolkit.regexRule(payload, qqRule.get("rule"));
          if (qq != null && qqRule.get("desIp").equals(destinationIp)) {
            System.out.println(Toolkit.hexToChar(qq));
          }
        }
      }
    }
    return true;
  }
}
