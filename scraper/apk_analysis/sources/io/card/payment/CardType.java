package io.card.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Pair;
import io.card.payment.i18n.LocalizedStrings;
import io.card.payment.i18n.StringKey;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes2.dex */
public enum CardType {
    AMEX("AmEx"),
    DINERSCLUB("DinersClub"),
    DISCOVER("Discover"),
    JCB("JCB"),
    MASTERCARD("MasterCard"),
    VISA("Visa"),
    MAESTRO("Maestro"),
    UNKNOWN("Unknown"),
    INSUFFICIENT_DIGITS("More digits required");

    private static HashMap<Pair<String, String>, CardType> intervalLookup;
    private static int minDigits;
    public final String name;

    static {
        CardType cardType = AMEX;
        CardType cardType2 = DINERSCLUB;
        CardType cardType3 = DISCOVER;
        CardType cardType4 = JCB;
        CardType cardType5 = MASTERCARD;
        CardType cardType6 = VISA;
        CardType cardType7 = MAESTRO;
        minDigits = 1;
        HashMap<Pair<String, String>, CardType> hashMap = new HashMap<>();
        intervalLookup = hashMap;
        hashMap.put(getNewPair("2221", "2720"), cardType5);
        intervalLookup.put(getNewPair("300", "305"), cardType2);
        intervalLookup.put(getNewPair("309", null), cardType2);
        intervalLookup.put(getNewPair("34", null), cardType);
        intervalLookup.put(getNewPair("3528", "3589"), cardType4);
        intervalLookup.put(getNewPair("36", null), cardType2);
        intervalLookup.put(getNewPair("37", null), cardType);
        intervalLookup.put(getNewPair("38", "39"), cardType2);
        intervalLookup.put(getNewPair("4", null), cardType6);
        intervalLookup.put(getNewPair("50", null), cardType7);
        intervalLookup.put(getNewPair("51", "55"), cardType5);
        intervalLookup.put(getNewPair("56", "59"), cardType7);
        intervalLookup.put(getNewPair("6011", null), cardType3);
        intervalLookup.put(getNewPair("61", null), cardType7);
        intervalLookup.put(getNewPair("62", null), cardType3);
        intervalLookup.put(getNewPair("63", null), cardType7);
        intervalLookup.put(getNewPair("644", "649"), cardType3);
        intervalLookup.put(getNewPair("65", null), cardType3);
        intervalLookup.put(getNewPair("66", "69"), cardType7);
        intervalLookup.put(getNewPair("88", null), cardType3);
        for (Map.Entry<Pair<String, String>, CardType> entry : getIntervalLookup().entrySet()) {
            minDigits = Math.max(minDigits, ((String) entry.getKey().first).length());
            if (entry.getKey().second != null) {
                minDigits = Math.max(minDigits, ((String) entry.getKey().second).length());
            }
        }
    }

    CardType(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }

    /* renamed from: io.card.payment.CardType$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$card$payment$CardType;

        static {
            int[] iArr = new int[CardType.values().length];
            $SwitchMap$io$card$payment$CardType = iArr;
            try {
                iArr[CardType.AMEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.DINERSCLUB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.DISCOVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.JCB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.MASTERCARD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.MAESTRO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.VISA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.INSUFFICIENT_DIGITS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$card$payment$CardType[CardType.UNKNOWN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public String getDisplayName(String str) {
        switch (AnonymousClass1.$SwitchMap$io$card$payment$CardType[ordinal()]) {
            case 1:
                return LocalizedStrings.getString(StringKey.CARDTYPE_AMERICANEXPRESS, str);
            case 2:
            case 3:
                return LocalizedStrings.getString(StringKey.CARDTYPE_DISCOVER, str);
            case 4:
                return LocalizedStrings.getString(StringKey.CARDTYPE_JCB, str);
            case 5:
                return LocalizedStrings.getString(StringKey.CARDTYPE_MASTERCARD, str);
            case 6:
                return LocalizedStrings.getString(StringKey.CARDTYPE_MAESTRO, str);
            case 7:
                return LocalizedStrings.getString(StringKey.CARDTYPE_VISA, str);
            default:
                return null;
        }
    }

    public int numberLength() {
        switch (AnonymousClass1.$SwitchMap$io$card$payment$CardType[ordinal()]) {
            case 1:
                return 15;
            case 2:
                return 14;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 16;
            case 8:
                return minDigits;
            default:
                return -1;
        }
    }

    public int cvvLength() {
        switch (AnonymousClass1.$SwitchMap$io$card$payment$CardType[ordinal()]) {
            case 1:
                return 4;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 3;
            default:
                return -1;
        }
    }

    public Bitmap imageBitmap(Context context) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$io$card$payment$CardType[ordinal()];
        if (i2 == 1) {
            i = R.drawable.cio_ic_amex;
        } else if (i2 == 2 || i2 == 3) {
            i = R.drawable.cio_ic_discover;
        } else if (i2 == 4) {
            i = R.drawable.cio_ic_jcb;
        } else if (i2 != 5) {
            i = i2 != 7 ? -1 : R.drawable.cio_ic_visa;
        } else {
            i = R.drawable.cio_ic_mastercard;
        }
        if (i != -1) {
            return BitmapFactory.decodeResource(context.getResources(), i);
        }
        return null;
    }

    private static boolean isNumberInInterval(String str, String str2, String str3) {
        int min = Math.min(str.length(), str2.length());
        int min2 = Math.min(str.length(), str3.length());
        return Integer.parseInt(str.substring(0, min)) >= Integer.parseInt(str2.substring(0, min)) && Integer.parseInt(str.substring(0, min2)) <= Integer.parseInt(str3.substring(0, min2));
    }

    private static HashMap<Pair<String, String>, CardType> getIntervalLookup() {
        return intervalLookup;
    }

    private static Pair<String, String> getNewPair(String str, String str2) {
        if (str2 == null) {
            str2 = str;
        }
        return new Pair<>(str, str2);
    }

    public static CardType fromString(String str) {
        if (str == null) {
            return UNKNOWN;
        }
        for (CardType cardType : values()) {
            if (cardType != UNKNOWN && cardType != INSUFFICIENT_DIGITS && str.equalsIgnoreCase(cardType.toString())) {
                return cardType;
            }
        }
        return UNKNOWN;
    }

    public static CardType fromCardNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry<Pair<String, String>, CardType> entry : getIntervalLookup().entrySet()) {
            if (isNumberInInterval(str, (String) entry.getKey().first, (String) entry.getKey().second)) {
                hashSet.add(entry.getValue());
            }
        }
        if (hashSet.size() > 1) {
            return INSUFFICIENT_DIGITS;
        }
        if (hashSet.size() == 1) {
            return (CardType) hashSet.iterator().next();
        }
        return UNKNOWN;
    }
}
