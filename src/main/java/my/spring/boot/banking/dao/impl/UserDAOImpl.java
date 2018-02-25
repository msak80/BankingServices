package my.spring.boot.banking.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import my.spring.boot.banking.dao.UserDAO;
import my.spring.boot.banking.entity.User;

@Repository
public class UserDAOImpl  extends BaseDAOImpl<User, Long> implements UserDAO {

	@Override
	public User findUserByEmail(String email) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		return findByNamedQuerySingleResult("findByEmail", parameters);
	}
}
