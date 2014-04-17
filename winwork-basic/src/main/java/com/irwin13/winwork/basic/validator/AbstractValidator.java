package com.irwin13.winwork.basic.validator;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.irwin13.winwork.basic.WinWorkConstants;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class AbstractValidator<M> {

    private WinWorkConfig config;

    @Inject
    public void setConfig(WinWorkConfig config) {
        this.config = config;
    }

    public abstract ValidatorResult<M> onCreate(M model, String errorLang);
    public abstract ValidatorResult<M> onEdit(M model, String errorLang);
    public abstract ValidatorResult<M> onDelete(M model, String errorLang);

    protected boolean isEmptyString(String value) {
        return Strings.isNullOrEmpty(value);
    }

    protected boolean isAlphaNumeric(String value) {
        return StringUtils.isAlphanumeric(value);
    }

    protected boolean isNumeric(String value) {
        return StringUtils.isNumeric(value);
    }

    protected boolean checkRegex(String value,String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        return  m.matches();
    }

    protected String getErrorMessage(String lang, String key) {
        if (Strings.isNullOrEmpty(lang)) lang = WinWorkConstants.DEFAULT_LANG;
        String langKey = lang + "." + key;
        return config.getString(langKey, "Missing Error Message for key = " + langKey);
    }

    protected String getDecoratedErrorMessage(String lang, String key) {
        return "<font color='red'>" + getErrorMessage(lang, key) + "</font>";
    }

}
