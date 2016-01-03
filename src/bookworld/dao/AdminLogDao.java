package bookworld.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//验证管理员登陆的过程中所用到的数据库访问而设计的
public class AdminLogDao extends HibernateDaoSupport{
	//根据管理员账号和密码来寻找与该管理员信息对应的持久化对象
	private String hql = "from Admin u where u.admin_no=? and u.password=?";
	
	//Log对象是Log4j提供的日志记录对象，如果在进行数据库操作的过程中出现任何问题，都会通过Log对象记录下来
	private static final Log log = LogFactory.getLog(LogUserDao.class);
	
	//传入的参数分别是管理员账号和密码，这两个参数是传递给hql语句的
	//传入参数后，通过调用find函数来查找正在登陆的管理员账号是否有效
	//如果找到了则说明有效，返回true；如果找不到，说明登陆信息无效，返回false
	@SuppressWarnings("rawtypes")
	public boolean IsUserValid(String userid, String password){
		String[] userlist = new String[2];
		userlist[0] = userid;
		userlist[1] = password;
		
		List ret = null;
		
		try{
			//获取hibernateTemplate对象，然后调用该对象的find函数到持久化层去查找指定账户和密码的用户
			ret = this.getHibernateTemplate().find(hql,userlist);
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
		
		if(ret.size() > 0)
			return true;
		else
			return false;
	}
}
