import org.junit.Test;

import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.dao.IUserDao;
import com.cduestc.tyr.online_shopping.dao.impl.UserDao;
import com.cduestc.tyr.online_shopping.utils.SendEmail;


public class MyTest {
	
	@Test
	public void testUserTable() throws Exception {
		UserBean user = new UserBean();
		user.setNickname("唐晏忍ttt");
		IUserDao dao = new UserDao();
		dao.addUser(user);
	}
	
	@Test
	public void testSendMail() {
		String to[] = {"tangyanrentyr123456@163.com"};
		SendEmail.send(to, "邮箱验证", "今天下午一起吃饭，好嘛", "text/html;charset=utf-8");
	}
	
	public void Test3() {
		//IUserDao dao = new UserDao();
		//UserBean user = dao.getUserByNickname("");
	}
	
}
