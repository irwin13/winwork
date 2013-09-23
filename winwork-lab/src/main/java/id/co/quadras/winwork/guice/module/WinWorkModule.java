package id.co.quadras.winwork.guice.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

import id.co.quadras.winwork.WinWorkConfig;
import id.co.quadras.winwork.dao.EntityResolver;
import id.co.quadras.winwork.dao.SessionFactoryEntityResolver;
import id.co.quadras.winwork.guice.interceptor.MDCLogInterceptor;
import id.co.quadras.winwork.guice.provider.DataSourceProvider;
import id.co.quadras.winwork.guice.provider.ObjectMapperProvider;
import id.co.quadras.winwork.guice.provider.SchedulerProvider;
import id.co.quadras.winwork.guice.provider.SessionFactoryProvider;
import id.co.quadras.winwork.model.annotations.MDCLog;
import id.co.quadras.winwork.service.MessageParser;
import id.co.quadras.winwork.service.MessageWrapper;
import id.co.quadras.winwork.shared.AbstractConfiguration;
import id.co.quadras.winwork.shared.BasicSchedulerManager;
import id.co.quadras.winwork.util.RestClient;
import id.co.quadras.winwork.util.StringCompressor;
import id.co.quadras.winwork.util.WinWorkVelocityUtil;
import org.hibernate.SessionFactory;
import org.quartz.Scheduler;

import javax.sql.DataSource;

/**
 * @author irwin Timestamp : 22/09/13 0:02
 */
public class WinWorkModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StringCompressor.class);

        bind(MessageWrapper.class);
        bind(MessageParser.class);

        bind(RestClient.class);

        // JACKSON JSON MAPPER
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);

        // scheduler components
        bind(Scheduler.class).toProvider(SchedulerProvider.class).in(Singleton.class);
        bind(BasicSchedulerManager.class);

        // MDC Log AOP Interceptor
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(MDCLog.class), new MDCLogInterceptor());

        // Web Components
        bind(WinWorkVelocityUtil.class);

        // config
        bind(String.class).annotatedWith(Names.named("configFile")).toInstance("common-config.xml");
        bind(AbstractConfiguration.class).to(WinWorkConfig.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("webSessionStorageSystem")).toInstance("HttpSession");

        bind(DataSource.class).toProvider(DataSourceProvider.class).in(Singleton.class);
        bind(SessionFactory.class).toProvider(SessionFactoryProvider.class).in(Singleton.class);

        bind(EntityResolver.class).to(SessionFactoryEntityResolver.class);
    }
}
