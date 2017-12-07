package com.highrock.pdf.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by user on 2017/12/7.
 */
public class IpAddressHelper {
    public static String getHostAddress() throws UnknownHostException {
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
/*    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121

            String hostAddress = address.getHostAddress();//192.168.0.121    
            System.out.println(hostAddress);//  
            InetAddress address1 = InetAddress.getByName("www.wodexiangce.cn");//获取的是该网站的ip地址，比如我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地
            String hostAddress1 = address1.getHostAddress();//124.237.121.122
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");//根据主机名返回其可能的所有InetAddress对象
            for (InetAddress addr : addresses) {
                System.out.println(addr);//www.baidu.com/14.215.177.38
                //www.baidu.com/14.215.177.37
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    }*/


