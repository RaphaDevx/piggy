package io.card.payment;

/* loaded from: classes2.dex */
class StringHelper {
    public static String getDigitsOnlyString(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
