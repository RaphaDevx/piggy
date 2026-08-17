package com.beust.jcommander.defaultprovider;

import com.beust.jcommander.IDefaultProvider;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public final class EnvironmentVariableDefaultProvider implements IDefaultProvider {
    private static final String DEFAULT_PREFIXES_PATTERN = "-/";
    private static final String DEFAULT_VARIABLE_NAME = "JCOMMANDER_OPTS";
    private final String environmentVariableValue;
    private final String optionPrefixesPattern;

    public EnvironmentVariableDefaultProvider() {
        this(DEFAULT_VARIABLE_NAME, DEFAULT_PREFIXES_PATTERN);
    }

    public EnvironmentVariableDefaultProvider(String str, String str2) {
        this((String) Objects.requireNonNull(str), (String) Objects.requireNonNull(str2), new Function() { // from class: com.beust.jcommander.defaultprovider.EnvironmentVariableDefaultProvider$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return System.getenv((String) obj);
            }
        });
    }

    EnvironmentVariableDefaultProvider(String str, String str2, Function<String, String> function) {
        this.environmentVariableValue = function.apply(str);
        this.optionPrefixesPattern = (String) Objects.requireNonNull(str2);
    }

    @Override // com.beust.jcommander.IDefaultProvider
    public final String getDefaultValueFor(String str) {
        if (this.environmentVariableValue == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("(?:(?:.*\\s+)|(?:^))(" + Pattern.quote(str) + ")\\s*((?:'[^']*(?='))|(?:\"[^\"]*(?=\"))|(?:[^" + this.optionPrefixesPattern + "\\s]+))?.*").matcher(this.environmentVariableValue);
        if (!matcher.matches()) {
            return null;
        }
        String group = matcher.group(2);
        if (group == null) {
            return "true";
        }
        char charAt = group.charAt(0);
        return (charAt == '\'' || charAt == '\"') ? group.substring(1) : group;
    }
}
