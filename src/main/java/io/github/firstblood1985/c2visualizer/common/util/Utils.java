package io.github.firstblood1985.c2visualizer.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Locale;

/**
 * usage of this class: Util
 * created by limin @ 2022/5/2
 */
public class Utils {
    public static String getCurrentSiteUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null == authentication) {
            return null;
        }

        UserDetails user = (UserDetails) authentication.getPrincipal();

        return user.getUsername();
    }

    public static boolean checkUsername(Logger logger, String username) {
        if (StringUtils.isEmpty(username)) {
            logger.warn("no username found from authentication, login first");
            return false;
        }
        return true;
    }

    public static void initFields(Class clazz, Object o) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            Annotation anno = f.getAnnotation(Initable.class);
            if (anno != null) {
                String fieldName = f.getName();
                try {
                    Method m = clazz.getDeclaredMethod(fieldName);
                    m.setAccessible(true);
                    f.set(o,m.invoke(o));
                }catch (Exception e)
                {
                }
            }
        }

    }

    public static LocalDate getStartDate(String year, String month){
        return LocalDate.of(Integer.valueOf(year),Integer.valueOf(month),1);
    }
    public static LocalDate getEndDate(String year, String month){
        LocalDate firstDay = getStartDate(year,month);

        return firstDay.withDayOfMonth(
                firstDay.getMonth().length(firstDay.isLeapYear())
        );
    }
    public static Integer durationToMinutes(Duration duration){
        String durationHMS = DurationFormatUtils.formatDurationHMS(duration.toMillis());

        String[] hms = durationHMS.split(":");

        if(hms[0].equals("00") && hms[1].equals("00"))
            return 1;

        //have secs and mins
        if(hms[0].equals("00"))
            return Integer.valueOf(hms[1]);

        //have secs, mins and hours
        return Integer.valueOf(hms[0])*60 + Integer.valueOf(hms[1]);

    }

    public static String durationToString(Duration duration){
        if(null == duration)
            return null;

         String durationHMS = DurationFormatUtils.formatDurationHMS(duration.toMillis());

         String[] hms = durationHMS.split(":");

         //only have secs
         if(hms[0].equals("00") && hms[1].equals("00"))
             return hms[2].substring(0,4);

         //have secs and mins
         if(hms[0].equals("00"))
             return hms[1]+":"+hms[2].substring(0,4);

         //have secs, mins and hours
         return hms[0]+":"+hms[1]+":"+hms[2].substring(0,4);
    }

    public static Duration StringToDuration(String s){
        String[] time = s.split(":");
        StringBuilder sb = new StringBuilder();

        if(time.length==3) {
            sb.append("PT").append(time[0]).append('H').append(time[1]).append('M').append(time[2]).append('S');
            return Duration.parse(sb.toString());
        }

        if(time.length==2) {
            sb.append("PT").append(time[0]).append('M').append(time[1]).append('S');
            return Duration.parse(sb.toString());
        }
            return null;
    }

    public static String metersFormat(Integer meters){
        return NumberFormat.getNumberInstance(Locale.US).format(meters)+"m";
    }

    public static Integer paceToSpeed(String pace){
       String[] splited = pace.split(":") ;

       Float secs = Float.valueOf(splited[0])*60 + Float.valueOf(splited[1]);

       return Math.round(500/secs*60);
    }
}
