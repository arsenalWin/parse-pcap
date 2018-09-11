package com.cetiti;

import com.cetiti.handler.TCPPacketHandler;
import com.cetiti.handler.TCPPacketSearchHandler;
import io.pkts.Pcap;

import java.io.IOException;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 13:47 2018-8-31
 * @Modified By:
 */
public class Main {
  public static void main(final String[] args) throws IOException{
    final Pcap pcap = Pcap.openStream("dump.pcap");

//    TCPPacketSearchHandler packetHandler = new TCPPacketSearchHandler();
    TCPPacketHandler packetHandler = new TCPPacketHandler();

    pcap.loop(packetHandler);

    packetHandler.dumpToFile("result.yml");
  }
}
