package com.cetiti;

import com.cetiti.handler.TCPPacketHandler;
import com.cetiti.handler.TCPPacketSearchHandler;
import io.pkts.Pcap;
import io.pkts.framer.FramingException;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 13:47 2018-8-31
 * @Modified By:
 */
public class Main {
  public static void main(final String[] args) throws IOException, FramingException, IntrospectionException {
    final Pcap pcap = Pcap.openStream("dump.pcap");

//    TCPPacketSearchHandler packetHandler = new TCPPacketSearchHandler();
    TCPPacketHandler packetHandler = new TCPPacketHandler();

    pcap.loop(packetHandler);

    packetHandler.dumpToFile("result.yml");
  }
}
