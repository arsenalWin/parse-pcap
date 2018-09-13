package com.cetiti;

import com.cetiti.handler.TCPPacketHandler;
import com.cetiti.handler.TCPPacketSearchHandler;
import io.pkts.Pcap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: zhouwei
 * @Description:
 * @Date: Create in 13:47 2018-8-31
 * @Modified By:
 */
public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(final String[] args) throws IOException{
    final Pcap pcap = Pcap.openStream("dump.pcap");

    LOGGER.info("开始解析包");

//    TCPPacketSearchHandler packetHandler = new TCPPacketSearchHandler();
    TCPPacketHandler packetHandler = new TCPPacketHandler();

    pcap.loop(packetHandler);

    LOGGER.info("完成包的解析");

    packetHandler.dumpToFile("result.yml");

    LOGGER.info("写入结果成功");
  }
}
