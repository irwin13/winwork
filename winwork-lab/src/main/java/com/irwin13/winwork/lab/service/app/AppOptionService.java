package com.irwin13.winwork.lab.service.app;


import java.util.List;

import com.irwin13.winwork.basic.model.entity.app.AppOption;
import com.irwin13.winwork.basic.service.WinWorkService;

/**
 * @author irwin Timestamp : 12/04/13 17:33
 */
public interface AppOptionService extends WinWorkService<AppOption, String> {
    public List<AppOption> selectByCategory(String category);
}
