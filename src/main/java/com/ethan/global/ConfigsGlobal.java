package com.ethan.global;

import helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String URI = PropertiesHelper.getValue("URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String ACCEPT_JSON = PropertiesHelper.getValue("ACCEPT.JSON");
    public static String CONTENT_TYPE_JSON = PropertiesHelper.getValue("CONTENT.TYPE.JSON");
}
