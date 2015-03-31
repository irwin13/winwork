package com.irwin13.winwork.entity;

import com.irwin13.winwork.core.model.WinWorkEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by irwin on 31/03/2015.
 */
public class WinWorkRole extends WinWorkEntity {

    @Override
    public String entityName() {
        return "winWorkRole";
    }

    @NotNull
    private String name;
    private String description;

    private transient List<WinWorkMenu> winWorkMenuList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WinWorkMenu> getWinWorkMenuList() {
        return winWorkMenuList;
    }

    public void setWinWorkMenuList(List<WinWorkMenu> winWorkMenuList) {
        this.winWorkMenuList = winWorkMenuList;
    }
}
