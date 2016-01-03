package bookworld.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//��֤����Ա��½�Ĺ��������õ������ݿ���ʶ���Ƶ�
public class AdminLogDao extends HibernateDaoSupport{
	//���ݹ���Ա�˺ź�������Ѱ����ù���Ա��Ϣ��Ӧ�ĳ־û�����
	private String hql = "from Admin u where u.admin_no=? and u.password=?";
	
	//Log������Log4j�ṩ����־��¼��������ڽ������ݿ�����Ĺ����г����κ����⣬����ͨ��Log�����¼����
	private static final Log log = LogFactory.getLog(LogUserDao.class);
	
	//����Ĳ����ֱ��ǹ���Ա�˺ź����룬�����������Ǵ��ݸ�hql����
	//���������ͨ������find�������������ڵ�½�Ĺ���Ա�˺��Ƿ���Ч
	//����ҵ�����˵����Ч������true������Ҳ�����˵����½��Ϣ��Ч������false
	@SuppressWarnings("rawtypes")
	public boolean IsUserValid(String userid, String password){
		String[] userlist = new String[2];
		userlist[0] = userid;
		userlist[1] = password;
		
		List ret = null;
		
		try{
			//��ȡhibernateTemplate����Ȼ����øö����find�������־û���ȥ����ָ���˻���������û�
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
