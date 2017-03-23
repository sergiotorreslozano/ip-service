package com.ip.service;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpController {

	private int counter;

	@Value("${ipservice.message}")
	private String message;

	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@RequestMapping("/")
	public @ResponseBody String home() {
		log.debug("Got a request!");
		return "Hello Spring boot and Kubernetes";
	}

	@RequestMapping("/ip")
	public IPAddress ipaddress() throws Exception {
		return new IPAddress(++counter, InetAddress.getLocalHost().getHostAddress(), message);
	}

}

class IPAddress {
	private final long id;
	private final String ipAddress;
	private final String message;

	public IPAddress(long id, String ipAddress, String message) {
		super();
		this.id = id;
		this.ipAddress = ipAddress;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getMessage() {
		return message;
	}

}

