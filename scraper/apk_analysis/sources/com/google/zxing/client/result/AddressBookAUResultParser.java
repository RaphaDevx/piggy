package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes2.dex */
public final class AddressBookAUResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        if (!massagedText.contains("MEMORY") || !massagedText.contains("\r\n")) {
            return null;
        }
        String matchSinglePrefixedField = matchSinglePrefixedField("NAME1:", massagedText, CharUtils.CR, true);
        String matchSinglePrefixedField2 = matchSinglePrefixedField("NAME2:", massagedText, CharUtils.CR, true);
        String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", massagedText);
        String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", massagedText);
        String matchSinglePrefixedField3 = matchSinglePrefixedField("MEMORY:", massagedText, CharUtils.CR, false);
        String matchSinglePrefixedField4 = matchSinglePrefixedField("ADD:", massagedText, CharUtils.CR, true);
        return new AddressBookParsedResult(maybeWrap(matchSinglePrefixedField), null, matchSinglePrefixedField2, matchMultipleValuePrefix, null, matchMultipleValuePrefix2, null, null, matchSinglePrefixedField3, matchSinglePrefixedField4 != null ? new String[]{matchSinglePrefixedField4} : null, null, null, null, null, null, null);
    }

    private static String[] matchMultipleValuePrefix(String str, String str2) {
        ArrayList arrayList = null;
        for (int i = 1; i <= 3; i++) {
            String matchSinglePrefixedField = matchSinglePrefixedField(str + i + ':', str2, CharUtils.CR, true);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(3);
            }
            arrayList.add(matchSinglePrefixedField);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(EMPTY_STR_ARRAY);
    }
}
