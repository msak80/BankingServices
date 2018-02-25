package my.spring.boot.banking.dao;

import my.spring.boot.banking.entity.User;

public interface UserDAO extends BaseDAO<User, Long> {
	User findUserByEmail(String email);
}