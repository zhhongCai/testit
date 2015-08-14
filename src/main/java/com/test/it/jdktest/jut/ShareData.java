package com.test.it.jdktest.jut;

import com.google.common.base.Objects;

import java.util.Observable;

/**
 * Created by caizh on 2015/8/14.
 */
public class ShareData extends Observable {
    private String name;
    private String description;

    public ShareData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers("Share Data Changed!!!");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        setChanged();
        notifyObservers("Share Data Changed!!!");
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", this.name)
                .add("description", this.description)
                .toString();
    }
}
