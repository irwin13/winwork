package com.irwin13.winwork.lab.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.irwin13.winwork.basic.model.entity.app.AppOption;
import com.irwin13.winwork.basic.model.entity.app.AppPermission;
import com.irwin13.winwork.basic.model.entity.app.AppRole;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.basic.model.entity.app.AppUser;
import com.irwin13.winwork.basic.validator.AbstractValidator;
import com.irwin13.winwork.lab.validator.app.AppOptionValidator;
import com.irwin13.winwork.lab.validator.app.AppPermissionValidator;
import com.irwin13.winwork.lab.validator.app.AppRoleValidator;
import com.irwin13.winwork.lab.validator.app.AppSettingValidator;
import com.irwin13.winwork.lab.validator.app.AppUserValidator;

/**
 * @author irwin Timestamp : 22/09/13 0:37
 */
public class ValidatorModule extends AbstractModule {

    @Override
    protected void configure() {
        // APP
        bind(AbstractValidator.class).annotatedWith(Names.named(AppOption.MODEL_NAME)).to(AppOptionValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppSetting.MODEL_NAME)).to(AppSettingValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppUser.MODEL_NAME)).to(AppUserValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppPermission.MODEL_NAME)).to(AppPermissionValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppRole.MODEL_NAME)).to(AppRoleValidator.class);

    }
}
