package myPackage;

import java.util.Iterator;
import java.util.List;

import hibernate.HibernateUtil;

import org.hibernate.Transaction;
import org.hibernate.Session;
/**
 *查询数据库
 * @author kiwi
 *
 */
public class FindStuff {
	/**
	 * 根据id号查询数据库，返回SearchResult的Bean
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static SearchResult  findStuffById(String id){
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		List pl =sess.createQuery("select stuff from SearchResult as stuff where id = :id").setString("id", id).list();
		Iterator pit = pl.iterator();
		SearchResult s = (SearchResult)pit.next();
		tx.commit();
		HibernateUtil.closeSession();
		return s;
	}
	
	public SearchResult  getStuffById(String id){
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		List pl =sess.createQuery("select stuff from SearchResult as stuff where id = :id").setString("id", id).list();
		Iterator pit = pl.iterator();
		SearchResult s = (SearchResult)pit.next();
		tx.commit();
		HibernateUtil.closeSession();
		return s;
	}

}
