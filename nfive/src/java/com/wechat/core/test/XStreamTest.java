package com.wechat.core.test;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamTest {

	public static String javaObject2Xml(Person person){
		XStream xs = new XStream(new DomDriver());
		xs.alias("person", person.getClass());
		return xs.toXML(person);
	}
	
	public static Object xml2JavaObject(String xml){
		XStream xs = new XStream(new DomDriver());
		xs.alias("person", Person.class);
		Person person =(Person) xs.fromXML(xml);
		return person;
	}
	
	
	public static void main(String[] args)
	{
		Person p1 = new Person();
		p1.setName("chenjia");
		p1.setSex("male");
		p1.setAddress("god's address");
		
		System.out.println( javaObject2Xml(p1));
		
		String xml="<person><name>chenjia</name><sex>male</sex><address>god&apos;s address</address></person>";
		
		Person p2 =(Person) xml2JavaObject(xml);
		
		System.out.println(p2.getName()+" " +p2.getSex() +" " +p2.getAddress());
		

	}

}
