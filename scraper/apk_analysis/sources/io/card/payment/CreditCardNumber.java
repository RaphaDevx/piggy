package io.card.payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
class CreditCardNumber {
    public static boolean passesLuhnChecksum(String str) {
        int[][] iArr = {new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9}};
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
        char last = stringCharacterIterator.last();
        int i = 0;
        int i2 = 0;
        while (last != 65535) {
            if (!Character.isDigit(last)) {
                return false;
            }
            i += iArr[i2 & 1][last - '0'];
            last = stringCharacterIterator.previous();
            i2++;
        }
        return i % 10 == 0;
    }

    public static String formatString(String str) {
        return formatString(str, true, null);
    }

    public static String formatString(String str, boolean z, CardType cardType) {
        String digitsOnlyString = z ? StringHelper.getDigitsOnlyString(str) : str;
        if (cardType == null) {
            cardType = CardType.fromCardNumber(digitsOnlyString);
        }
        int numberLength = cardType.numberLength();
        if (digitsOnlyString.length() != numberLength) {
            return str;
        }
        if (numberLength == 16) {
            return formatSixteenString(digitsOnlyString);
        }
        return numberLength == 15 ? formatFifteenString(digitsOnlyString) : str;
    }

    private static String formatFifteenString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            if (i == 4 || i == 10) {
                sb.append(' ');
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    private static String formatSixteenString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if (i != 0 && i % 4 == 0) {
                sb.append(' ');
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static boolean isDateValid(int i, int i2) {
        if (i < 1 || 12 < i) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(1);
        int i4 = calendar.get(2) + 1;
        if (i2 < i3) {
            return false;
        }
        return (i2 != i3 || i >= i4) && i2 <= i3 + 15;
    }

    public static SimpleDateFormat getDateFormatForLength(int i) {
        if (i == 4) {
            return new SimpleDateFormat("MMyy");
        }
        if (i == 6) {
            return new SimpleDateFormat("MMyyyy");
        }
        return null;
    }

    public static Date getDateForString(String str) {
        String digitsOnlyString = StringHelper.getDigitsOnlyString(str);
        SimpleDateFormat dateFormatForLength = getDateFormatForLength(digitsOnlyString.length());
        if (dateFormatForLength != null) {
            try {
                dateFormatForLength.setLenient(false);
                return dateFormatForLength.parse(digitsOnlyString);
            } catch (ParseException unused) {
            }
        }
        return null;
    }
}
