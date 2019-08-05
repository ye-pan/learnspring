package com.yp.spring.tx;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

public class TransactionTemplateTest {
    private DataSource dataSource;

    @Before
    public void beforeMethod() {
        dataSource = new SimpleDriverDataSource();
    }

    @Test
    public void testTx() {
        PlatformTransactionManager manager = new DataSourceTransactionManager(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(manager);
        transactionTemplate.execute((status)->{
            return null;
        });
    }
}
