package devbuy;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(
		locations = { 
		"classpath:/spring/applicationContext-dao.xml",  
		"classpath:/spring/applicationContext-service.xml", 
		"classpath:/spring/applicationContext-transaction.xml",
		"classpath:/spring/springmvc.xml"
		}
		)


public class TestUserService {

	
    private static final Logger LOGGER = Logger.getLogger(TestUserService.class);  
	@Autowired
	UserService userServie;
	
	@Test
	public void test() {
		
		User user = userServie.findUserById(1);
		LOGGER.info("----------------------" + user);
		
		
	}

}
