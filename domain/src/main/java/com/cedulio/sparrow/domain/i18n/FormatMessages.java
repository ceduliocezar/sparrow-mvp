package com.cedulio.sparrow.domain.i18n;


import com.cedulio.sparrow.domain.utilities.DefaultLocale;

import java.util.ResourceBundle;

public class FormatMessages {

    private static ResourceBundle formatsBundle = ResourceBundle
            .getBundle("Formats", DefaultLocale.getLocale());

    public static String getString(String key){
        return formatsBundle.getString(key);
    }

}
