package com.yp.spring.tx;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

public class PlatformTransactionManagerTest {

    private DataSource dataSource;

    @Before
    public void beforeMethod() {
        dataSource = new SimpleDriverDataSource();
    }
    @Test
    public void testTx() {

        PlatformTransactionManager manager = new DataSourceTransactionManager(dataSource);
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = manager.getTransaction(definition);
        try {
            //do something .....
            manager.commit(status);
        } catch(Exception e) {
            e.printStackTrace();
            manager.rollback(status);
        }
    }
}
