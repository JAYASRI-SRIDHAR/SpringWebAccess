package com.exampledemo.model;

import java.io.Serializable;

public class AccessibilityBean implements Serializable {
	private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
