package id.co.quadras.winwork.validator.app;

import com.google.inject.Inject;
import id.co.quadras.winwork.model.entity.app.AppOption;
import id.co.quadras.winwork.service.app.AppOptionService;
import id.co.quadras.winwork.validator.AbstractValidator;
import id.co.quadras.winwork.validator.ValidationStatus;
import id.co.quadras.winwork.validator.ValidatorResult;

import java.util.List;

/**
 * @author irwin Timestamp : 22/04/13 14:31
 */
public class AppOptionValidator extends AbstractValidator<AppOption> {

    private final AppOptionService appOptionService;

    @Inject
    public AppOptionValidator(AppOptionService appOptionService) {
        this.appOptionService = appOptionService;
    }

    @Override
    public ValidatorResult<AppOption> onCreate(AppOption model, String errorLang) {
        ValidatorResult validatorResult = ValidatorResult.successResult();

        if (isEmptyString(model.getName())) {
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("name", getDecoratedErrorMessage(errorLang, "error.global.required"));
        } else {
            if (!isEmptyString(model.getName()) && !isEmptyString(model.getOptionCategory())) {
                AppOption filter = new AppOption();
                filter.setActive(Boolean.TRUE);
                filter.setName(model.getName());
                filter.setOptionCategory(model.getOptionCategory());

                List<AppOption> list = appOptionService.select(filter, null);
                if (!list.isEmpty()) {
                    validatorResult.setValidationStatus(ValidationStatus.ERROR);
                    validatorResult.putFieldMessage("name", getDecoratedErrorMessage(errorLang, "error.appOption.alreadyExist"));
                }
            }

        }

        validatorResult.setProcessReturnObject(model);
        return validatorResult;
    }

    @Override
    public ValidatorResult<AppOption> onEdit(AppOption model, String errorLang) {
        return ValidatorResult.successResult();
    }

    @Override
    public ValidatorResult<AppOption> onDelete(AppOption model, String errorLang) {
        return ValidatorResult.successResult();
    }
}
