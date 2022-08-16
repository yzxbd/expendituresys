package com.yzxbd.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTime {

    public static String getFormatTime(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
