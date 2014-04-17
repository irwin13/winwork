package com.irwin13.winwork.basic.test;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author irwin Timestamp : 19/03/13 18:56
 */
public class TestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUtil.class);

    public static void generateDbUnitDataset(String driverClassName, String dbUrl, String dbUser, String dbPassword,
                                             Map<String, String> tableNameAndQuery, String fileDestination) {

        Connection jdbcConnection = null;
        try {
            Class.forName(driverClassName);
            jdbcConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

            QueryDataSet dataSet = new QueryDataSet(connection);

            if (tableNameAndQuery != null) {
                for (Map.Entry<String, String> entry : tableNameAndQuery.entrySet()) {
                    dataSet.addTable(entry.getKey(), entry.getValue());
                }
            }

            FlatXmlDataSet.write(dataSet, new FileOutputStream(fileDestination));

        } catch (DataSetException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (SQLException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (DatabaseUnitException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            if (jdbcConnection != null) {
                try {
                    jdbcConnection.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                }
            }
        }
    }
}
