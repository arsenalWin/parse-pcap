package com.cetiti.handler;

import com.google.polo.pairing.HexDump;
import io.pkts.PacketHandler;
import io.pkts.packet.IPv4Packet;
import io.pkts.packet.MACPacket;
import io.pkts.packet.Packet;
import io.pkts.protocol.Protocol;

import java.io.IOException;

/**
 * @Author: zhouwei
 * @Description: 在TCP包数据中搜索字符串
 * @Date: Create in 10:14 2018-9-4
 * @Modified By:
 */
public class TCPPacketSearchHandler implements PacketHandler {


  public TCPPacketSearchHandler() throws IOException {
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

          if (findX(payload)) {
            System.out.println("macAddr:" + macAddr);
            System.out.println("desIp:" + destinationIp);
            System.out.println(payload);
            System.out.println();
          }
        }

      }
    }
    return true;
  }

  /**
   * 由于whireshark不能直接搜索，所以用这个来搜我们想要的字符串
   * @param payload
   * @return
   */
  private boolean findX(String payload){
    //我们想要找的字符串，16进制编码
    String findStr = "3135383538313635343139";

    if(payload.indexOf(findStr) > 0){
      return true;
    }

    return false;
  }
}
