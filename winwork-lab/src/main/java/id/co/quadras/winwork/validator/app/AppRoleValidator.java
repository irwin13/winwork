package id.co.quadras.winwork.validator.app;

import com.google.inject.Inject;
import id.co.quadras.winwork.model.entity.app.AppRole;
import id.co.quadras.winwork.service.app.AppRoleService;
import id.co.quadras.winwork.validator.AbstractValidator;
import id.co.quadras.winwork.validator.ValidationStatus;
import id.co.quadras.winwork.validator.ValidatorResult;

import java.util.List;

/**
 * @author irwin Timestamp : 23/04/13 16:29
 */
public class AppRoleValidator extends AbstractValidator<AppRole> {

    private final AppRoleService appRoleService;

    @Inject
    public AppRoleValidator(AppRoleService appRoleService) {
        this.appRoleService = appRoleService;
    }

    @Override
    public ValidatorResult<AppRole> onCreate(AppRole model, String errorLang) {
        ValidatorResult validatorResult = ValidatorResult.successResult();

        if (isEmptyString(model.getName())) {
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("name", getDecoratedErrorMessage(errorLang, "error.global.required"));
        } else {
            if (!isEmptyString(model.getName())) {
                AppRole filter = new AppRole();
                filter.setActive(Boolean.TRUE);
                filter.setName(model.getName());

                List<AppRole> list = appRoleService.select(filter, null);
                if (!list.isEmpty()) {
                    validatorResult.setValidationStatus(ValidationStatus.ERROR);
                    validatorResult.putFieldMessage("name", getDecoratedErrorMessage(errorLang, "error.appRole.alreadyExist"));
                }
            }
        }

        validatorResult.setProcessReturnObject(model);
        return validatorResult;
    }

    @Override
    public ValidatorResult<AppRole> onEdit(AppRole model, String errorLang) {
        return ValidatorResult.successResult();
    }

    @Override
    public ValidatorResult<AppRole> onDelete(AppRole model, String errorLang) {
        return ValidatorResult.successResult();
    }
}
