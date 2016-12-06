package com.liuyonggang;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceApplicationTests {

	@Test
	public void contextLoads() {
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			Client client = dcf.createClient("http://localhost:8080/soap/user?wsdl");
			Object[] objects = client.invoke("getUser", 10002L);
			//输出调用结果
			System.out.println(objects[0].getClass());
			System.out.println(objects[0].toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
