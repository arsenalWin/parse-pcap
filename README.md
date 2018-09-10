# parse-pcap

#### 简介
利用java解析抓包文件，并从中提取出虚拟身份信息，现在已经解析出QQ,IMEI,IMSI,手机号，163邮箱，京东账号，手机操作系统
版本等信息
#### 运行说明
- 确定电脑上安装了jdk8
- 将项目导入到eclipse或者intelliJ IDEA中
- 运行Main函数
- 程序将解析项目目录下的“dump1.pcap”文件（通过wireshark等抓包软件抓取），并输出“result.yml”文件
#### 配置文件说明
本项目使用yaml作为配置文件，配置项如下：
```yaml
rules:
  - type: QQ
    ruleList:
      - desIp: 117.144.244.33
        regex: 000000000D(\d+).
      - desIp: 61.151.225.24
        regex: 000000000D(\d+)
  - type: IMEI
    ruleList:
      - desIp: 106.39.169.231 # 京东app
        regex: 757569643D(\d+)2D # uuid=(imei)-
  - type: JD
    ruleList:
      - desIp: 106.39.169.231 # 京东app
        regex: 436F6F6B69653A2070696E3D(\w+)3B # Cookie: pin=(jd);

#继续添加新的规则。。。

```
其中type字段表示虚拟身份的名称，ruleList字段表示能够匹配这个虚拟身份的规则（可以不止一条），desIp表示目标地址的IP，regex表示去匹配虚拟身份的正则表达式。
因为我们需要匹配的是一串十六进制数，所以正则匹配表达式也需要写成十六进制的格式。为了方便编
写这种十六进制的正则匹配表达式，我们做了一下处理，用“(\d+)”来匹配一串数字，用“(\w+)”来
匹配数字，大小写字母以及下划线。
#### 依赖库说明
1. snakeyaml: yaml读写操作库
2. io.pkts: pcap包解析库，github地址：https://github.com/aboutsip/pkts
#### 如何添加规则
一般来说，想要添加一条新规则的步骤如下（以QQ号为例）：
1. 以电脑作为AP，手机连上电脑的wifi后，使用wireshark开始抓包。如果只想抓取一个APP的数据，可以限制其他软件的上网；
2. 使用wireshark查看所抓取的包，查看其中的敏感信息，多看几个包   
![](http://ww1.sinaimg.cn/large/93f8d068gy1fuxobrymdoj20ye0n4ad9.jpg)
![](http://ww1.sinaimg.cn/large/93f8d068gy1fuxoc2pr0gj20y20m3419.jpg)
3. 从上面的截图中，我们基本可以归纳出QQ号的规则如下：
```yaml
rules:
  - type: QQ
    ruleList:
      - desIp: 61.151.225.24
        regex: 000000000D(\d+)
```
4.在程序中添加相关代码片段
 - 在 VirtualId.java 中添加字段“qq”，以及相关getter，setter方法;
 
 
5.将程序中的dump.pcap包替换成上面抓取的包，执行程序。如果result.yml中出现QQ号，则实验成功。
一般来说需要多人多次重复进行试验。


#### wireshark教程
wireshark的相关操作可以查看这篇教程：https://zhuanlan.zhihu.com/p/32980127


