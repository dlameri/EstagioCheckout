package domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerSystemTests extends AbstractSystemTest {
	private static final String CHECKOUT_CUSTOMER_URL = "http://localhost:9082/Checkout/customer";
	@Test
	public void googleThatForMe() throws Exception {
		driver.get("http://google.com");
		type("gbqfq","Like a Régua");
		findElementById("gbqfq");
		findElementById("gbqfb").click();
		Thread.sleep(1000L);
		
		assertTrue( driver.getPageSource().contains("Like a Régua #1 - Brasileirismo - YouTube") );
	}
	
	@Test
	public void registroDeUsuario() throws Exception{
		driver.get(CHECKOUT_CUSTOMER_URL+"/new");
		type("customer.name","Gustavo");
		type("customer.surname","Lima");
		type("customer.phoneNumber","9 999-9999");
		type("customer.cpf","13577744442");
		type("customer.email","gustavo.lima@ideais.com.br");
		type("customer.username","gustavolima");
		type("address.street","Rua exemplo");
		type("address.number","42");
		type("address.complement","casa 3");
		type("address.city","Nova Iguaçu");
		type("address.state","Rio de Janeiro");
		type("address.country","Brasil");
		type("address.zipcode","29020-005");
		
		findElementById("submitBtn").click();
		Thread.sleep(1000L);
		
		
		
	}
	
}
