package com.irwin13.winwork.basic.test;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author irwin Timestamp : 27/08/13 10:51
 */
public abstract class DbUnitCleanInsert {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private IDatabaseConnection connection;

    public abstract void initHibernate();
    public abstract List<String> datasetList();

    public abstract String jdbcDriver();
    public abstract String jdbcUrl();
    public abstract String jdbcUser();
    public abstract String jdbcPassword();

    @Before
    public void init() throws Exception {

        LOGGER.info("Init Hibernate to AUTOMATIC CREATE TABLE ");
        initHibernate();
        LOGGER.info("Finish creating tables");

        LOGGER.info("Run DbUnit CLEAN_INSERT :");
        LOGGER.info("Driver class = {}", jdbcDriver());
        LOGGER.info("URL = {}", jdbcUrl());
        LOGGER.info("User = {}", jdbcUser());
        LOGGER.info("Password = {}", jdbcPassword());

        Class.forName(jdbcDriver());
        Connection jdbcConnection = DriverManager.getConnection(jdbcUrl(), jdbcUser(), jdbcPassword());
        connection = new DatabaseConnection(jdbcConnection);
        executeDataset();
    }

    @After
    public void cleanup() throws SQLException {
        connection.close();
        LOGGER.info("Finish running DbUnit CLEAN_INSERT. Close connection");
    }

    private void executeDataset() throws Exception {
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new org.dbunit.ext.oracle.Oracle10DataTypeFactory());
        List<String> dataSetList = datasetList();
        LOGGER.info("Load dataset : {}", dataSetList);
        for (String dataset : dataSetList) {
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(dataset));
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }
    }
}
