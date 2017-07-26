package com.trunk.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author 周颖
 *
 * @param <T>
 */
public class BaseDaoImpl<T>  implements BaseDao<T>{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Class<?> clazz;
	
	
	public BaseDaoImpl() {
		//根据反射获取T的Class对象
		Class<?> c = this.getClass();
		ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<?>) types[0];
	}
	
	
	
	
	/**
	 * 获取Hibernate中的Session对象
	 * @return
	 */
	public Session getSession() {
		return sessionfactory.getCurrentSession();
	}
	
	/**
	 * 添加或修改对象
	 * @param t
	 */
	public void save(T t) {
		getSession().saveOrUpdate(t);
	}
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(clazz, id);
	}
	
	/**
	 * 获取全部对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria c = getSession().createCriteria(clazz);
		return c.list();
	}
	
	/**
	 * 根据分页参数获取对象
	 * @param start 起始行数
	 * @param rows 每页显示的行数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(Integer start,Integer rows) {
		Criteria c = getSession().createCriteria(clazz);
		c.setFirstResult(start);
		c.setMaxResults(rows);
		return c.list();
	}
	
	
	/**
	 * 根据属性名和值获取唯一的对象
	 * @param propertyName
	 * @param value
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public T findByUnique(String propertyName,String value) {
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propertyName, value));
		return (T) c.uniqueResult();
	}
	
	/**
	 * 根据属性名和值获取对象集合
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBy(String propertyName,String value) {
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propertyName, value));
		return c.list();
	}
	
	/**
	 * 根据HQL获取对象集合
	 * @param hql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Object...args) {
		return createQuery(hql, args).list();
	}
	
	/**
	 * 根据HQL获取对象集合 HQL使用的是引用占位符
	 * @param hql
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Map<String,Object> map) {
		return createQuery(hql, map).list();
	}
	
	/**
	 * 根据HQL获取唯一对象
	 * @param hql
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findUnique(String hql,Object...args) {
		return (T) createQuery(hql, args).uniqueResult();
	}
	
	/**
	 * 根据HQL获取唯一对象 HQL使用的是引用占位符
	 * @param hql
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findUnique(String hql,Map<String,Object> map) {
		return (T) createQuery(hql, map).uniqueResult();
	}
	
	/**
	 * 根据Criterion列表获取集合对象
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	
	/**
	 * 根据Criterion列表获取唯一对象
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findUnique(Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}
	
	/**
	 * 根据Where集合获取对象集合
	 * @param wheres
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(List<Where> wheres){
		return builderCriteriaByWhereList(wheres).list();
	}
	
	/**
	 * 根据Where集合获取唯一对象
	 * @param wheres
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findUnique(List<Where> wheres) {
		return (T) builderCriteriaByWhereList(wheres).uniqueResult();
	}
	
	/**
	 * 根据Wheres集合构建Criteria对象(工具方法)
	 * @param wheres
	 * @return
	 */
	private Criteria builderCriteriaByWhereList(List<Where> wheres) {
		Criteria c = getSession().createCriteria(clazz);
		for(Where where : wheres) {
			//如果多个属性之间使用or来进行查询 写法例如：username_OR_loginname_OR_firstname
			if(where.getProperty().contains("_OR_")) {
				Criterion criterion = builderORCriterionByWhere(where);
				c.add(criterion);
			} else {
				Criterion criterion = builderCriterionByWhere(where);
				if(criterion != null) {
					c.add(criterion);
				}
			}
		}
		return c;
	}

	/**
	 * 根据OR关系构建Criterion对象(工具方法)
	 * @param where
	 * @return
	 */
	private Criterion builderORCriterionByWhere(Where where) {
		String[] propertyNames = where.getProperty().split("_OR_");
		
		//Disjunction对象可以将多个Criterion条件使用or关系连接起来
		Disjunction disjunction = Restrictions.disjunction();
		for(String property : propertyNames) {
			Criterion criterion = builderCriterionByWhere(where.getMatchType(), property, where.getValue());
			disjunction.add(criterion);
		}
		return disjunction;
	}



	/**
	 * 根据Where对象构建Criterion对象(工具方法)
	 * @param where
	 * @return
	 */
	private Criterion builderCriterionByWhere(Where where) {
		if(where != null) {
			String matchType = where.getMatchType();
			String property = where.getProperty();
			String value = where.getValue();
			return builderCriterionByWhere(matchType, property, value);
		}
		return null;
	}


	/**
	 * 根据比较类型、属性名和值构建Criterion对象(工具方法)
	 * @param matchType 比较类型 可以从MatchType接口中获取
	 * @param property
	 * @param value
	 * @return
	 */
	public Criterion builderCriterionByWhere(String matchType, String property, String value) {
		if(MatchType.EQ.equalsIgnoreCase(matchType)) {
			return Restrictions.eq(property, value);
		} else if(MatchType.GE.equalsIgnoreCase(matchType)) {
			return Restrictions.ge(property, value);
		} else if(MatchType.GT.equalsIgnoreCase(matchType)) {
			return Restrictions.gt(property, value);
		} else if(MatchType.LE.equalsIgnoreCase(matchType)) {
			return Restrictions.le(property, value);
		} else if(MatchType.LT.equalsIgnoreCase(matchType)) {
			return Restrictions.lt(property, value);
		} else if(MatchType.LIKE.equalsIgnoreCase(matchType)) {
			return Restrictions.like(property, value, MatchMode.ANYWHERE);
		}
		return null;
	}



	/**
	 * 根据Criterion列表构建Criteria对象(工具方法)
	 * @param criterions
	 * @return
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria c = getSession().createCriteria(clazz);
		for(Criterion cri : criterions) {
			c.add(cri);
		}
		return c;
	}
	
	/**
	 * 根据HQL及参数构建Query对象(工具方法)
	 * @param hql
	 * @param args
	 * @return
	 */
	public Query createQuery(String hql,Object...args) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query;
	}
	
	/**
	 * 根据HQL及参数构建Query对象(工具方法)
	 * @param hql
	 * @param map
	 * @return
	 */
	public Query createQuery(String hql,Map<String,Object> map) {
		Query query = getSession().createQuery(hql);
		query.setProperties(map);
		return query;
	}
	
}
