package com.irwin13.winwork.entity;

import com.irwin13.winwork.core.model.WinWorkEntity;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 31/03/2015.
 */
public class WinWorkSequence extends WinWorkEntity {

    @Override
    public String entityName() {
        return "winWorkSequence";
    }

    @NotNull
    private String sequencePrefix;
    private int lastSequence;

    public String getSequencePrefix() {
        return sequencePrefix;
    }

    public void setSequencePrefix(String sequencePrefix) {
        this.sequencePrefix = sequencePrefix;
    }

    public int getLastSequence() {
        return lastSequence;
    }

    public void setLastSequence(int lastSequence) {
        this.lastSequence = lastSequence;
    }
}
