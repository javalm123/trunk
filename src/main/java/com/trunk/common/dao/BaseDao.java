package com.trunk.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

/**
 * 
 * @author 周颖
 *
 * @param <T>
 */
public interface BaseDao<T> {

	
	
	
	
	
	
	/**
	 * 添加或修改对象
	 * @param t
	 */
	public void save(T t);
	public void update(T t);
	/**
	 * 根据主键获取对象
	 * @param id
	 * @return
	 */
	public T get(Serializable id);
	
	/**
	 * 获取全部对象
	 * @return
	 */

	public void delete(Serializable id);
	
	
	public List<T> findAll() ;
	
	/**
	 * 根据分页参数获取对象
	 * @param start 起始行数
	 * @param rows 每页显示的行数
	 * @return
	 */

	public List<T> findByPage(Integer start,Integer rows) ;
	
	
	/**
	 * 根据属性名和值获取唯一的对象
	 * @param propertyName
	 * @param value
	 * @return
	 */
	

	public T findByUnique(String propertyName,String value);
	
	/**
	 * 根据属性名和值获取对象集合
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findBy(String propertyName,String value) ;
	/**
	 * 根据HQL获取对象集合
	 * @param hql
	 * @param args
	 * @return
	 */
	public List<T> find(String hql,Object...args);
	
	/**
	 * 根据HQL获取对象集合 HQL使用的是引用占位符
	 * @param hql
	 * @param map
	 * @return
	 */
	public List<T> find(String hql,Map<String,Object> map);
	/**
	 * 根据HQL获取唯一对象
	 * @param hql
	 * @param args
	 * @return
	 */
	
	public T findUnique(String hql,Object...args) ;
	/**
	 * 根据HQL获取唯一对象 HQL使用的是引用占位符
	 * @param hql
	 * @param map
	 * @return
	 */

	public T findUnique(String hql,Map<String,Object> map) ;
	
	/**
	 * 根据Criterion列表获取集合对象
	 * @param criterions
	 * @return
	 */
	public List<T> find(Criterion... criterions) ;
	
	/**
	 * 根据Criterion列表获取唯一对象
	 * @param criterions
	 * @return
	 */
	public T findUnique(Criterion... criterions) ;
	
	/**
	 * 根据Where集合获取对象集合
	 * @param wheres
	 * @return
	 */
	public List<T> find(List<Where> wheres);
	/**
	 * 根据Where集合获取唯一对象
	 * @param wheres
	 * @return
	 */
	public T findUnique(List<Where> wheres);
	
	
	
}
