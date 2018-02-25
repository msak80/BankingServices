package my.spring.boot.banking.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import my.spring.boot.banking.dao.BaseDAO;

@Repository
public class BaseDAOImpl<T, PK extends Serializable> implements BaseDAO<T, PK> {

	private final static Logger LOG = Logger.getLogger(BaseDAO.class.getName());
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public T findById(Class<T> clazz, PK id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clazz) {
		return (List<T>) entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	@Override
	public void delete(Class<T> clazz, PK id) {
		entityManager.remove(findById(clazz, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByNamedQuerySingleResult(String namedQuery, Map<String, Object> parameters) {
		Query query = entityManager.createNamedQuery(namedQuery);
		if (parameters != null && !parameters.isEmpty()) {
			for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
		}
		T singleResult  = null;
		try
		{
			singleResult  = (T) query.getSingleResult();
		}
		catch (NoResultException e) {
			LOG.log(Level.WARNING,"No record found error",e);
		}
		return singleResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		Query query = entityManager.createNamedQuery(namedQuery);
		if (parameters != null && !parameters.isEmpty()) {
			for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
		}
		return (List<T>) query.getResultList();
	}

	@Override
	public Long countByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		Query query = entityManager.createNamedQuery(namedQuery);
		if (parameters != null && !parameters.isEmpty()) {
			for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
		}
		return (Long) query.getSingleResult();
	}
}
