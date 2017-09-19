package com.coderdream.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: SystemTool
 * @description: 与系统相关的一些常用工具方法. 目前实现的有：获取MAC地址、IP地址、主机名
 */
public class SystemTool {

	private static Logger logger = LoggerFactory.getLogger(SystemTool.class);

	public static void main(String[] args) throws Exception {
		logger.debug("\tMacAddress:\t" + getMacAddress());
		logger.debug("IpAddress:\t" + getIpAddress());
	}

	// 获取mac地址
	public static String getMacAddress() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
							.getNetworkInterfaces();
			byte[] mac = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
								.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual()
								|| !netInterface.isUp()) {
					continue;
				} else {
					mac = netInterface.getHardwareAddress();
					if (mac != null) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < mac.length; i++) {
							sb.append(String.format("%02X%s", mac[i],
											(i < mac.length - 1) ? "-" : ""));
						}
						if (sb.length() > 0) {
							return sb.toString();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("MAC地址获取失败", e);
		}
		return "";
	}

	// 获取ip地址
	public static String getIpAddress() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
							.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
								.nextElement();
				if (netInterface.isLoopback() || netInterface.isVirtual()
								|| !netInterface.isUp()) {
					continue;
				} else {
					Enumeration<InetAddress> addresses = netInterface
									.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("IP地址获取失败", e);
		}
		return "";
	}
}
