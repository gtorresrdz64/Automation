package com.intercom.framework.automation.core.definition.webpage;

public interface WebComponent<T,R> {

    public R action(T logic, Exception ex);
}
