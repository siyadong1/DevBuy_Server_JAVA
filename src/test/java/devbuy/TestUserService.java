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
	
	
	/**
	 * 插入用户
	 */
	@Test
	public void testInsertUser(){
		User user = new User();
		user.setUsername("syd");
		user.setPassword("123456");
		userServie.insertUser(user);
		
	}
	
	/**
	 * 更新用户
	 */

	@Test
	public void testUpdateUser(){
		
		User user = userServie.findUserByUsername("syd88");
		user.setPassword("1111");
		
		userServie.updateUser(user);
	}
}
