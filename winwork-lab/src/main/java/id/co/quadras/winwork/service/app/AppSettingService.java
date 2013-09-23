package id.co.quadras.winwork.service.app;

import id.co.quadras.winwork.model.entity.app.AppSetting;
import id.co.quadras.winwork.service.BasicOperationService;

import java.util.List;

/**
 * @author irwin Timestamp : 15/04/13 14:21
 *
 * Edit By : Nova, 02/05/13
 * Add method getByCategory
 */
public interface AppSettingService extends BasicOperationService<AppSetting, String> {

    public AppSetting getByCode(String code);
    public List<AppSetting> getByCategory(String category);

    public String getSettingStringValue(String code);
    public int getSettingIntValue(String code);
}
