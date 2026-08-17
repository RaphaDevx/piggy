package com.beust.jcommander;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class UnixStyleUsageFormatter extends DefaultUsageFormatter {
    public UnixStyleUsageFormatter(JCommander jCommander) {
        super(jCommander);
    }

    @Override // com.beust.jcommander.DefaultUsageFormatter
    public void appendAllParametersDetails(StringBuilder sb, int i, String str, List<ParameterDescription> list) {
        if (list.size() > 0) {
            sb.append(str);
            sb.append("  Options:\n");
        }
        Iterator<ParameterDescription> it = list.iterator();
        char c = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ParameterDescription next = it.next();
            WrappedParameter parameter = next.getParameter();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(parameter.required() ? "* " : "  ");
            sb2.append(next.getNames());
            String sb3 = sb2.toString();
            if (sb3.length() > i2) {
                i2 = sb3.length();
            }
        }
        for (ParameterDescription parameterDescription : list) {
            WrappedParameter parameter2 = parameterDescription.getParameter();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(parameter2.required() ? "* " : "  ");
            sb4.append(parameterDescription.getNames());
            String sb5 = sb4.toString();
            sb.append(str);
            sb.append("  ");
            sb.append(sb5);
            sb.append(s(i2 - sb5.length()));
            String str2 = StringUtils.SPACE;
            sb.append(StringUtils.SPACE);
            int length = str.length() + i2 + 3;
            String description = parameterDescription.getDescription();
            Object obj = parameterDescription.getDefault();
            if (parameterDescription.isDynamicParameter()) {
                String str3 = "(syntax: " + parameter2.names()[c] + "key" + parameter2.getAssignment() + "value)";
                StringBuilder sb6 = new StringBuilder();
                sb6.append(description);
                sb6.append(description.length() == 0 ? "" : StringUtils.SPACE);
                sb6.append(str3);
                description = sb6.toString();
            }
            if (obj != null && !parameterDescription.isHelp()) {
                String obj2 = Strings.isStringEmpty(obj.toString()) ? "<empty string>" : obj.toString();
                StringBuilder sb7 = new StringBuilder("(default: ");
                if (parameter2.password()) {
                    obj2 = "********";
                }
                sb7.append(obj2);
                sb7.append(")");
                String sb8 = sb7.toString();
                StringBuilder sb9 = new StringBuilder();
                sb9.append(description);
                sb9.append(description.length() == 0 ? "" : StringUtils.SPACE);
                sb9.append(sb8);
                description = sb9.toString();
            }
            Class<?> type = parameterDescription.getParameterized().getType();
            if (type.isEnum()) {
                String enumSet = EnumSet.allOf(type).toString();
                if (!description.contains("Options: " + enumSet)) {
                    String str4 = "(values: " + enumSet + ")";
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append(description);
                    if (description.length() == 0) {
                        str2 = "";
                    }
                    sb10.append(str2);
                    sb10.append(str4);
                    description = sb10.toString();
                }
            }
            wrapDescription(sb, (i + i2) - 3, length, description);
            sb.append(StringUtils.LF);
            c = 0;
        }
    }
}
