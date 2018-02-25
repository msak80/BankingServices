package my.spring.boot.banking.dao.impl;

import org.springframework.stereotype.Repository;

import my.spring.boot.banking.dao.TransactionActionDAO;
import my.spring.boot.banking.entity.TransactionAction;

@Repository
public class TransactionActionDAOImpl extends BaseDAOImpl<TransactionAction, Long> implements TransactionActionDAO {}