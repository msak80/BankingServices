package my.spring.boot.banking.service;

import my.spring.boot.banking.entity.User;

public interface UserService {

	void addUser(User user);

	void updateUser(User user);

	User getUserByUserId(Long userId);

	User getUserByEmail(String email);
}
