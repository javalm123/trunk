package com.trunk.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import com.trunk.common.dao.BaseDao;
import com.trunk.common.dao.Where;

/**
 * 
 * @author 周颖
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	
	
	//子类dao的实例化，通过注解的方式 添加一个set方法 比如
	/**
	 * private 子类dao对象类型(Dao)   子类dao对象变量(dao)
	 * @注入注解
	 * public void setBaseDao(Dao dao){
	 * 	super.setBaseDao(dao);
	 * 	this.dao=dao; 
	 * }
	 */
	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T t) {
		baseDao.save(t);
	}
	public void update(T t){
		baseDao.update(t);
		
	}
	@Override
	public T get(Serializable id) {
		return baseDao.get(id);
	}

	@Override
	public void delete(Serializable id){
		baseDao.delete(id);
		
	}
	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Override
	public List<T> findByPage(Integer start, Integer rows) {
		return baseDao.findByPage(start, rows);
	}

	@Override
	public T findByUnique(String propertyName, String value) {
		return baseDao.findByUnique(propertyName, value);
	}

	@Override
	public List<T> findBy(String propertyName, String value) {
		return baseDao.findBy( propertyName,  value);
	}

	@Override
	public List<T> find(String hql, Object... args) {
		return baseDao.find( hql,args);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> map) {
		return baseDao.find( hql,  map);
	}

	@Override
	public T findUnique(String hql, Object... args) {
		return baseDao.findUnique( hql,  args);
	}

	@Override
	public T findUnique(String hql, Map<String, Object> map) {
		return baseDao.findUnique( hql,  map);
	}

	@Override
	public List<T> find(Criterion... criterions) {
		return baseDao.find( criterions);
	}

	@Override
	public T findUnique(Criterion... criterions) {
		return baseDao.findUnique(criterions);
	}

	@Override
	public List<T> find(List<Where> wheres) {
		return baseDao.find(wheres);
	}

	@Override
	public T findUnique(List<Where> wheres) {
		return baseDao.findUnique(wheres);
	}
	
	
}
