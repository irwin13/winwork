package id.co.quadras.winwork.validator.app;

import id.co.quadras.winwork.model.entity.app.AppPermission;
import id.co.quadras.winwork.validator.AbstractValidator;
import id.co.quadras.winwork.validator.ValidationStatus;
import id.co.quadras.winwork.validator.ValidatorResult;

/**
 * @author irwin Timestamp : 23/04/13 16:29
 */
public class AppPermissionValidator extends AbstractValidator<AppPermission> {

    @Override
    public ValidatorResult<AppPermission> onCreate(AppPermission model, String errorLang) {
        ValidatorResult validatorResult = ValidatorResult.successResult();

        if (isEmptyString(model.getName())) {
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("name", getDecoratedErrorMessage(errorLang, "error.global.required"));
        } else if(isEmptyString(model.getHttpPath())){
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("httpPath", getDecoratedErrorMessage(errorLang, "error.global.required"));
        } else if(isEmptyString(model.getHttpMethod())){
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("httpMethod", getDecoratedErrorMessage(errorLang, "error.global.required"));
        }

        validatorResult.setProcessReturnObject(model);
        return validatorResult;

    }

    @Override
    public ValidatorResult<AppPermission> onEdit(AppPermission model, String errorLang) {
        ValidatorResult validatorResult = ValidatorResult.successResult();

        if (isEmptyString(model.getName())) {
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("name", getDecoratedErrorMessage(errorLang, "error.global.required"));
        } else if(isEmptyString(model.getHttpPath())){
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("httpPath", getDecoratedErrorMessage(errorLang, "error.global.required"));
        } else if(isEmptyString(model.getHttpMethod())){
            validatorResult.setValidationStatus(ValidationStatus.ERROR);
            validatorResult.putFieldMessage("httpMethod", getDecoratedErrorMessage(errorLang, "error.global.required"));
        }

        validatorResult.setProcessReturnObject(model);
        return validatorResult;

    }

    @Override
    public ValidatorResult<AppPermission> onDelete(AppPermission model, String errorLang) {
        return ValidatorResult.successResult();
    }
}
