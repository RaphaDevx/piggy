package io.gsonfire.util;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.timepicker.TimeModel;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class RFC3339DateFormat extends DateFormat {
    private final SimpleDateFormat rfc3339Formatter;
    private final SimpleDateFormat rfc3339Parser;
    private final boolean serializeTime;
    private final boolean threeDigitMillis;
    private static final Pattern TIMEZONE_PATTERN = Pattern.compile("(.*)([+-][0-9][0-9])\\:?([0-9][0-9])$");
    private static final Pattern MILLISECONDS_PATTERN = Pattern.compile("(.*)\\.([0-9]+)(.*)");
    private static final Pattern DATE_ONLY_PATTERN = Pattern.compile("^[0-9]{1,4}-[0-1][0-9]-[0-3][0-9]$");

    public RFC3339DateFormat(TimeZone timeZone, boolean z, boolean z2) {
        this.rfc3339Parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (z) {
            this.rfc3339Formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        } else {
            this.rfc3339Formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        this.serializeTime = z;
        this.threeDigitMillis = z2;
        this.rfc3339Formatter.setTimeZone(timeZone);
    }

    public RFC3339DateFormat(TimeZone timeZone, boolean z) {
        this(timeZone, z, false);
    }

    public RFC3339DateFormat(TimeZone timeZone) {
        this(timeZone, true, false);
    }

    public RFC3339DateFormat(boolean z, boolean z2) {
        this(TimeZone.getTimeZone("UTC"), z, z2);
    }

    public RFC3339DateFormat(boolean z) {
        this(z, false);
    }

    public RFC3339DateFormat() {
        this(true);
    }

    private String generateTimezone(long j, TimeZone timeZone) {
        if (timeZone.getOffset(j) == 0) {
            return "Z";
        }
        int offset = (int) (timeZone.getOffset(j) / 1000);
        int i = offset / 3600;
        int abs = Math.abs((offset - (i * 3600)) / 60);
        return (i >= 0 ? "+" : "-") + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Math.abs(i))) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(abs));
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(this.rfc3339Formatter.format(date).toString());
        if (this.serializeTime) {
            long time = date.getTime();
            String format = String.format("%03d", Long.valueOf(time % 1000));
            if (!this.threeDigitMillis) {
                format = format.replaceAll("0*$", "");
            }
            if (!format.isEmpty()) {
                stringBuffer2.append("." + format);
            }
            stringBuffer2.append(generateTimezone(time, this.rfc3339Formatter.getTimeZone()));
        }
        return stringBuffer2;
    }

    @Override // java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        int i;
        String str2;
        if (DATE_ONLY_PATTERN.matcher(str).matches()) {
            str = str + "T00:00:00-0000";
        } else if (str.charAt(10) == 't') {
            str = str.substring(0, 10) + ExifInterface.GPS_DIRECTION_TRUE + str.substring(12);
        }
        if (str.contains(".")) {
            Matcher matcher = MILLISECONDS_PATTERN.matcher(str);
            i = (int) Math.round(Double.parseDouble("0." + matcher.replaceAll("$2")) * 1000.0d);
            str = matcher.replaceAll("$1") + matcher.replaceAll("$3");
        } else {
            i = 0;
        }
        if (str.endsWith("Z") || str.endsWith("z")) {
            str2 = str.substring(0, str.length() - 1) + "-0000";
        } else {
            Matcher matcher2 = TIMEZONE_PATTERN.matcher(str);
            if (matcher2.matches()) {
                str2 = matcher2.replaceAll("$1") + matcher2.replaceAll("$2") + matcher2.replaceAll("$3");
            } else {
                str2 = str + "-0000";
            }
        }
        try {
            Date parse = this.rfc3339Parser.parse(str2);
            if (i > 0) {
                parse = new Date(parse.getTime() + i);
            }
            parsePosition.setIndex(str2.length());
            return parse;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
