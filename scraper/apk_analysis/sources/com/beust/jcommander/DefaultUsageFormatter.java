package com.beust.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.internal.Lists;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class DefaultUsageFormatter implements IUsageFormatter {
    private final JCommander commander;

    public DefaultUsageFormatter(JCommander jCommander) {
        this.commander = jCommander;
    }

    @Override // com.beust.jcommander.IUsageFormatter
    public final void usage(String str) {
        StringBuilder sb = new StringBuilder();
        usage(str, sb);
        this.commander.getConsole().println(sb.toString());
    }

    @Override // com.beust.jcommander.IUsageFormatter
    public final void usage(String str, StringBuilder sb) {
        usage(str, sb, "");
    }

    @Override // com.beust.jcommander.IUsageFormatter
    public final void usage(StringBuilder sb) {
        usage(sb, "");
    }

    @Override // com.beust.jcommander.IUsageFormatter
    public final void usage(String str, StringBuilder sb, String str2) {
        String commandDescription = getCommandDescription(str);
        JCommander findCommandByAlias = this.commander.findCommandByAlias(str);
        if (commandDescription != null) {
            sb.append(str2);
            sb.append(commandDescription);
            sb.append(StringUtils.LF);
        }
        findCommandByAlias.getUsageFormatter().usage(sb, str2);
    }

    @Override // com.beust.jcommander.IUsageFormatter
    public void usage(StringBuilder sb, String str) {
        if (this.commander.getDescriptions() == null) {
            this.commander.createDescriptions();
        }
        boolean isEmpty = this.commander.getCommands().isEmpty();
        boolean z = !this.commander.getDescriptions().isEmpty();
        int length = str.length() + 6;
        appendMainLine(sb, z, !isEmpty, length, str);
        List<ParameterDescription> newArrayList = Lists.newArrayList();
        int i = 0;
        for (ParameterDescription parameterDescription : this.commander.getFields().values()) {
            if (!parameterDescription.getParameter().hidden()) {
                newArrayList.add(parameterDescription);
                int length2 = parameterDescription.getNames().length() + 2;
                if (length2 > i) {
                    i = length2;
                }
            }
        }
        newArrayList.sort(this.commander.getParameterDescriptionComparator());
        appendAllParametersDetails(sb, length, str, newArrayList);
        if (isEmpty) {
            return;
        }
        appendCommands(sb, length, 6, str);
    }

    public void appendMainLine(StringBuilder sb, boolean z, boolean z2, int i, String str) {
        String programDisplayName = this.commander.getProgramDisplayName() != null ? this.commander.getProgramDisplayName() : "<main class>";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("Usage: ");
        sb2.append(programDisplayName);
        if (z) {
            sb2.append(" [options]");
        }
        if (z2) {
            sb2.append(str);
            sb2.append(" [command] [command options]");
        }
        if (this.commander.getMainParameter() != null && this.commander.getMainParameter().getDescription() != null) {
            sb2.append(StringUtils.SPACE);
            sb2.append(this.commander.getMainParameter().getDescription().getDescription());
        }
        wrapDescription(sb, i, sb2.toString());
        sb.append(StringUtils.LF);
    }

    public void appendAllParametersDetails(StringBuilder sb, int i, String str, List<ParameterDescription> list) {
        if (list.size() > 0) {
            sb.append(str);
            sb.append("  Options:\n");
        }
        for (ParameterDescription parameterDescription : list) {
            WrappedParameter parameter = parameterDescription.getParameter();
            String description = parameterDescription.getDescription();
            boolean isEmpty = description.isEmpty();
            sb.append(str);
            sb.append("  ");
            sb.append(parameter.required() ? "* " : "  ");
            sb.append(parameterDescription.getNames());
            sb.append(StringUtils.LF);
            if (!isEmpty) {
                wrapDescription(sb, i, s(i) + description);
            }
            Object obj = parameterDescription.getDefault();
            if (parameterDescription.isDynamicParameter()) {
                String str2 = "Syntax: " + parameter.names()[0] + "key" + parameter.getAssignment() + "value";
                if (!isEmpty) {
                    sb.append(newLineAndIndent(i));
                } else {
                    sb.append(s(i));
                }
                sb.append(str2);
            }
            if (obj != null && !parameterDescription.isHelp()) {
                String obj2 = Strings.isStringEmpty(obj.toString()) ? "<empty string>" : obj.toString();
                StringBuilder sb2 = new StringBuilder("Default: ");
                if (parameter.password()) {
                    obj2 = "********";
                }
                sb2.append(obj2);
                String sb3 = sb2.toString();
                if (!isEmpty) {
                    sb.append(newLineAndIndent(i));
                } else {
                    sb.append(s(i));
                }
                sb.append(sb3);
            }
            Class<?> type = parameterDescription.getParameterized().getType();
            if (type.isEnum()) {
                String enumSet = EnumSet.allOf(type).toString();
                String str3 = "Possible Values: " + enumSet;
                if (!description.contains("Options: " + enumSet)) {
                    if (!isEmpty) {
                        sb.append(newLineAndIndent(i));
                    } else {
                        sb.append(s(i));
                    }
                    sb.append(str3);
                }
            }
            sb.append(StringUtils.LF);
        }
    }

    public void appendCommands(StringBuilder sb, int i, int i2, String str) {
        Iterator<Map.Entry<JCommander.ProgramName, JCommander>> it = this.commander.getRawCommands().entrySet().iterator();
        boolean z = true;
        while (it.hasNext()) {
            Parameters parameters = (Parameters) it.next().getValue().getObjects().get(0).getClass().getAnnotation(Parameters.class);
            if (parameters == null || !parameters.hidden()) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        sb.append(str + "  Commands:\n");
        for (Map.Entry<JCommander.ProgramName, JCommander> entry : this.commander.getRawCommands().entrySet()) {
            Parameters parameters2 = (Parameters) entry.getValue().getObjects().get(0).getClass().getAnnotation(Parameters.class);
            if (parameters2 == null || !parameters2.hidden()) {
                JCommander.ProgramName key = entry.getKey();
                wrapDescription(sb, i + i2, str + s(4) + key.getDisplayName() + s(6) + getCommandDescription(key.getName()));
                sb.append(StringUtils.LF);
                IUsageFormatter usageFormatter = this.commander.findCommandByAlias(key.getName()).getUsageFormatter();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(s(6));
                usageFormatter.usage(sb, sb2.toString());
                sb.append(StringUtils.LF);
            }
        }
    }

    @Override // com.beust.jcommander.IUsageFormatter
    public String getCommandDescription(String str) {
        java.util.ResourceBundle bundle;
        JCommander findCommandByAlias = this.commander.findCommandByAlias(str);
        if (findCommandByAlias == null) {
            throw new ParameterException("Asking description for unknown command: " + str);
        }
        Parameters parameters = (Parameters) findCommandByAlias.getObjects().get(0).getClass().getAnnotation(Parameters.class);
        if (parameters == null) {
            return null;
        }
        String commandDescription = parameters.commandDescription();
        String resourceBundle = parameters.resourceBundle();
        if (!resourceBundle.isEmpty()) {
            bundle = java.util.ResourceBundle.getBundle(resourceBundle, Locale.getDefault());
        } else {
            bundle = this.commander.getBundle();
        }
        if (bundle == null) {
            return commandDescription;
        }
        String commandDescriptionKey = parameters.commandDescriptionKey();
        return !commandDescriptionKey.isEmpty() ? getI18nString(bundle, commandDescriptionKey, parameters.commandDescription()) : commandDescription;
    }

    public void wrapDescription(StringBuilder sb, int i, int i2, String str) {
        int columnSize = this.commander.getColumnSize();
        String[] split = str.split(StringUtils.SPACE);
        for (int i3 = 0; i3 < split.length; i3++) {
            String str2 = split[i3];
            if (str2.length() > columnSize || i2 + 1 + str2.length() <= columnSize) {
                sb.append(str2);
                i2 += str2.length();
                if (i3 != split.length - 1) {
                    sb.append(StringUtils.SPACE);
                }
            } else {
                sb.append(StringUtils.LF);
                sb.append(s(i));
                sb.append(str2);
                sb.append(StringUtils.SPACE);
                i2 = str2.length() + i;
            }
            i2++;
        }
    }

    public void wrapDescription(StringBuilder sb, int i, String str) {
        wrapDescription(sb, i, 0, str);
    }

    public static String getI18nString(java.util.ResourceBundle resourceBundle, String str, String str2) {
        String string = resourceBundle != null ? resourceBundle.getString(str) : null;
        return string != null ? string : str2;
    }

    public static String s(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(StringUtils.SPACE);
        }
        return sb.toString();
    }

    private static String newLineAndIndent(int i) {
        return StringUtils.LF + s(i);
    }
}
