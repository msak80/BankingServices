package my.spring.boot.banking.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDAO<T, PK extends Serializable> {
	T findById(Class<T> clazz,PK id);

	List<T> findAll(Class<T> clazz);

	void save(T t);

	void update(T t);

	void delete(T t);
	
	void delete(Class<T> clazz,PK id);

	T findByNamedQuerySingleResult(String namedQuery, Map<String, Object> parameters);

	List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters);

	Long countByNamedQuery(String namedQuery, Map<String, Object> parameters);
}