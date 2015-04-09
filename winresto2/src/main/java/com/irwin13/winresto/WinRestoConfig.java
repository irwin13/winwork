package com.irwin13.winresto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.irwin13.winwork.core.WinWorkConfiguration;
import com.irwin13.winwork.hibernate.HibernateConfiguration;
import com.irwin13.winwork.logging.LoggingConfiguration;
import com.irwin13.winwork.velocity.VelocityConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 09/04/2015.
 */
public class WinRestoConfig extends WinWorkConfiguration {

    @Valid
    @NotNull
    @JsonProperty("velocity")
    private VelocityConfiguration velocity;
    @Valid
    @NotNull
    @JsonProperty("logging")
    private LoggingConfiguration logging;
    @Valid
    @NotNull
    @JsonProperty("hibernate")
    private HibernateConfiguration hibernate;

    public VelocityConfiguration getVelocity() {
        return velocity;
    }

    public void setVelocity(VelocityConfiguration velocity) {
        this.velocity = velocity;
    }

    public LoggingConfiguration getLogging() {
        return logging;
    }

    public void setLogging(LoggingConfiguration logging) {
        this.logging = logging;
    }

    public HibernateConfiguration getHibernate() {
        return hibernate;
    }

    public void setHibernate(HibernateConfiguration hibernate) {
        this.hibernate = hibernate;
    }
}
