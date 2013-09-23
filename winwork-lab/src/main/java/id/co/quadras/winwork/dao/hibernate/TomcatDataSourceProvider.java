package id.co.quadras.winwork.dao.hibernate;

import id.co.quadras.winwork.guice.GuiceWinWork;
import org.hibernate.HibernateException;
import org.hibernate.ejb.connection.InjectedDataSourceConnectionProvider;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author irwin Timestamp : 31/05/13 16:48
 */
public class TomcatDataSourceProvider extends InjectedDataSourceConnectionProvider {

    @Override
    public void configure(Properties props) throws HibernateException {
        DataSource dataSource = GuiceWinWork.getInjector().getInstance(DataSource.class);
        setDataSource(dataSource);
        super.configure(props);
    }
}
