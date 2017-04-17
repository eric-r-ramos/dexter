package io.dexter.services.atm;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.dexter.services.dispatcher.AtmServiceDispatcher;
import io.dexter.services.dispatcher.entities.Atm;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceDispatcherTest {

	@Autowired
	private AtmServiceDispatcher dispacther;
	
	@Test
	public void DispatcherTest() {
		List<Atm> list = dispacther.listAllAtm();
		
		assertNotNull(list);
		
	}

}
