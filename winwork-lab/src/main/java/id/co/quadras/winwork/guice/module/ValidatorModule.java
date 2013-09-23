package id.co.quadras.winwork.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import id.co.quadras.winwork.model.entity.app.*;
import id.co.quadras.winwork.validator.AbstractValidator;
import id.co.quadras.winwork.validator.app.*;

/**
 * @author irwin Timestamp : 22/09/13 0:37
 */
public class ValidatorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AbstractValidator.class).annotatedWith(Names.named(AppOption.MODEL_NAME)).to(AppOptionValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppSetting.MODEL_NAME)).to(AppSettingValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppUser.MODEL_NAME)).to(AppUserValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppPermission.MODEL_NAME)).to(AppPermissionValidator.class);
        bind(AbstractValidator.class).annotatedWith(Names.named(AppRole.MODEL_NAME)).to(AppRoleValidator.class);
    }
}
