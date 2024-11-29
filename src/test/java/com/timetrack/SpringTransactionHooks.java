package com.timetrack;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class SpringTransactionHooks implements BeanFactoryAware {

    private BeanFactory beanFactory;
    private TransactionStatus transactionStatus;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Before
    public void startTransaction() {
        transactionStatus = obtainPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
    }

    @After
    public void rollBackTransaction() {
        obtainPlatformTransactionManager().rollback(transactionStatus);
    }

    public PlatformTransactionManager obtainPlatformTransactionManager() {
        return beanFactory.getBean(PlatformTransactionManager.class);
    }

}