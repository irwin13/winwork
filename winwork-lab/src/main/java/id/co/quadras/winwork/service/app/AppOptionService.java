package id.co.quadras.winwork.service.app;

import id.co.quadras.winwork.model.entity.app.AppOption;
import id.co.quadras.winwork.service.BasicOperationService;

import java.util.List;

/**
 * @author irwin Timestamp : 12/04/13 17:33
 */
public interface AppOptionService extends BasicOperationService<AppOption, String> {
    public List<AppOption> selectByCategory(String category);
}
