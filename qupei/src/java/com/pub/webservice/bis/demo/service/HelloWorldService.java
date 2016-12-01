package com.pub.webservice.bis.demo.service;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Scope("prototype")
@Service("helloWorldWS")
public class HelloWorldService {
	
	public HelloWorldService(){	

	}
	
    public String hello(String name) { 
		return "Hello axis2 service!";
    }

}
