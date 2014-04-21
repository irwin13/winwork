package com.irwin13.winwork.mybatis.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import com.irwin13.winwork.mybatis.guice.MyBatisModule;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 21/04/2014 19:59
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AppSettingEmbedder.class})
public class AppSettingDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingDaoTest.class);

    private static IDatabaseConnection connection;
    public static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new MyBatisModule());
        WinWorkConfig config = injector.getInstance(WinWorkConfig.class);
        Connection jdbcConnection;

        try {
            @SuppressWarnings("unused")
            Class<?> driverClass = Class.forName(config.getString("test.jdbc.driver"));

            jdbcConnection = DriverManager.getConnection(config.getString("test.jdbc.url"),
                    config.getString("test.jdbc.username"),
                    config.getString("test.jdbc.password"));

            connection = new DatabaseConnection(jdbcConnection);
            executeDataset();

        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (DatabaseUnitException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

    }

    public static IDatabaseConnection getConnection() {
        return connection;
    }

    @AfterClass
    public static void cleanup() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    private static List<String> datasetList() {
        List<String> datasetList = new LinkedList<String>();
        datasetList.add("dataset/AppSetting.xml");
        return datasetList;
    }

    private static void executeDataset() {
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new org.dbunit.ext.oracle.Oracle10DataTypeFactory());

        List<String> datasetList = datasetList();
        try {
            for (String dataset : datasetList) {
                IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(dataset));
                DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
            }
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (DatabaseUnitException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

}
