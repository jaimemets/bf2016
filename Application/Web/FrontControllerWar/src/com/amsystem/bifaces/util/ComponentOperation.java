package com.amsystem.bifaces.util;

import org.primefaces.context.RequestContext;

import java.util.List;

/**
 * Title: ComponentOperation.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/01/2017.
 */

public class ComponentOperation {

    public static void updateComponent(String componentName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update(componentName);
    }

    public static void executeComponent(String componentName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(componentName);
    }

    /**
     * @param componentUpdateName
     * @param componentExecuteName
     */
    public static void updateExecute(List<String> componentUpdateName, String componentExecuteName) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (componentUpdateName != null && !componentUpdateName.isEmpty()) requestContext.update(componentUpdateName);
        if (!StringUtil.isEmptyOrNullValue(componentExecuteName)) requestContext.execute(componentExecuteName);
    }

}
