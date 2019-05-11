package com.nurgisa.libraryservice.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by User on 10.05.2019.
 */
public class CustomException extends Exception{

    public static final String EXCEPTION_EMPTY_DATA = "exception.empty.data";
    public static final String EXCEPTION_NO_DATA_FOUND = "exception.nodatafound";
    public static final String EXCEPTION_ID_IS_NULL = "exception.idIsNull";

    public CustomException(String message){super(message);}

    public static String getMessage(String message) {
        return ResourceBundle.getBundle("messages",new Locale("en")).getString(message);
    }

}
