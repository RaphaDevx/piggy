package com.beust.jcommander;

import com.beust.jcommander.FuzzyMap;
import com.beust.jcommander.converters.DefaultListConverter;
import com.beust.jcommander.converters.EnumConverter;
import com.beust.jcommander.converters.IParameterSplitter;
import com.beust.jcommander.converters.NoConverter;
import com.beust.jcommander.converters.StringConverter;
import com.beust.jcommander.internal.Console;
import com.beust.jcommander.internal.DefaultConsole;
import com.beust.jcommander.internal.DefaultConverterFactory;
import com.beust.jcommander.internal.JDK6Console;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import com.beust.jcommander.internal.Nullable;
import com.beust.jcommander.parser.DefaultParameterizedParser;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class JCommander {
    public static final String DEBUG_PROPERTY = "jcommander.debug";
    private final IVariableArity DEFAULT_VARIABLE_ARITY;
    private Map<FuzzyMap.IKey, ProgramName> aliasMap;
    private Map<ProgramName, JCommander> commands;
    private Console console;
    private Map<FuzzyMap.IKey, ParameterDescription> descriptions;
    private Map<Parameterized, ParameterDescription> fields;
    private boolean helpWasSpecified;
    private MainParameter mainParameter;
    private List<Object> objects;
    private final Options options;
    protected IParameterizedParser parameterizedParser;
    private String parsedAlias;
    private String parsedCommand;
    private ProgramName programName;
    private Map<Parameterized, ParameterDescription> requiredFields;
    private List<String> unknownArgs;
    private IUsageFormatter usageFormatter;

    private static String pluralize(int i, String str, String str2) {
        return i == 1 ? str : str2;
    }

    static class MainParameter {
        private Parameter annotation;
        private ParameterDescription description;
        Object object;
        Parameterized parameterized;
        private List<Object> multipleValue = null;
        private Object singleValue = null;
        private boolean firstTimeMainParameter = true;

        MainParameter() {
        }

        public ParameterDescription getDescription() {
            return this.description;
        }

        public void addValue(Object obj) {
            List<Object> list = this.multipleValue;
            if (list != null) {
                list.add(obj);
                return;
            }
            if (this.singleValue != null) {
                throw new ParameterException("Only one main parameter allowed but found several: \"" + this.singleValue + "\" and \"" + obj + "\"");
            }
            this.singleValue = obj;
            this.parameterized.set(this.object, obj);
        }
    }

    private static class Options {
        private boolean acceptUnknownOptions;
        private boolean allowAbbreviatedOptions;
        private boolean allowParameterOverwriting;
        private Charset atFileCharset;
        private java.util.ResourceBundle bundle;
        private boolean caseSensitiveOptions;
        private int columnSize;
        private final List<IStringConverterInstanceFactory> converterInstanceFactories;
        private IDefaultProvider defaultProvider;
        private boolean expandAtSign;
        private Comparator<? super ParameterDescription> parameterDescriptionComparator;
        private int verbose;

        private Options() {
            this.parameterDescriptionComparator = new Comparator<ParameterDescription>() { // from class: com.beust.jcommander.JCommander.Options.1
                @Override // java.util.Comparator
                public int compare(ParameterDescription parameterDescription, ParameterDescription parameterDescription2) {
                    WrappedParameter parameter = parameterDescription.getParameter();
                    WrappedParameter parameter2 = parameterDescription2.getParameter();
                    if (parameter != null && parameter.order() != -1 && parameter2 != null && parameter2.order() != -1) {
                        return Integer.compare(parameter.order(), parameter2.order());
                    }
                    if (parameter != null && parameter.order() != -1) {
                        return -1;
                    }
                    if (parameter2 == null || parameter2.order() == -1) {
                        return parameterDescription.getLongestName().compareTo(parameterDescription2.getLongestName());
                    }
                    return 1;
                }
            };
            this.columnSize = 79;
            this.acceptUnknownOptions = false;
            this.allowParameterOverwriting = false;
            this.expandAtSign = true;
            this.verbose = 0;
            this.caseSensitiveOptions = true;
            this.allowAbbreviatedOptions = false;
            this.converterInstanceFactories = new CopyOnWriteArrayList();
            this.atFileCharset = Charset.defaultCharset();
        }
    }

    private JCommander(Options options) {
        this.parameterizedParser = new DefaultParameterizedParser();
        this.objects = Lists.newArrayList();
        this.usageFormatter = new DefaultUsageFormatter(this);
        this.mainParameter = null;
        this.requiredFields = Maps.newHashMap();
        this.fields = Maps.newHashMap();
        this.commands = Maps.newLinkedHashMap();
        this.aliasMap = Maps.newLinkedHashMap();
        this.unknownArgs = Lists.newArrayList();
        this.DEFAULT_VARIABLE_ARITY = new DefaultVariableArity();
        if (options == null) {
            throw new NullPointerException(RRWebOptionsEvent.EVENT_TAG);
        }
        this.options = options;
        if (options.converterInstanceFactories.isEmpty()) {
            addConverterFactory(new DefaultConverterFactory());
        }
    }

    public JCommander() {
        this(new Options());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JCommander(Object obj) {
        this(obj, (java.util.ResourceBundle) null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JCommander(Object obj, @Nullable java.util.ResourceBundle resourceBundle) {
        this(obj, resourceBundle, null);
    }

    public JCommander(Object obj, @Nullable java.util.ResourceBundle resourceBundle, String... strArr) {
        this();
        addObject(obj);
        if (resourceBundle != null) {
            setDescriptionsBundle(resourceBundle);
        }
        createDescriptions();
        if (strArr != null) {
            parse(strArr);
        }
    }

    @Deprecated
    public JCommander(Object obj, String... strArr) {
        this(obj);
        parse(strArr);
    }

    public void setParameterizedParser(IParameterizedParser iParameterizedParser) {
        this.parameterizedParser = iParameterizedParser;
    }

    public void setExpandAtSign(boolean z) {
        this.options.expandAtSign = z;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public synchronized Console getConsole() {
        if (this.console == null) {
            try {
                this.console = new JDK6Console(System.class.getDeclaredMethod("console", null).invoke(null, null));
            } catch (Throwable unused) {
                this.console = new DefaultConsole();
            }
        }
        return this.console;
    }

    public final void addObject(Object obj) {
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                this.objects.add(it.next());
            }
            return;
        }
        if (obj.getClass().isArray()) {
            for (Object obj2 : (Object[]) obj) {
                this.objects.add(obj2);
            }
            return;
        }
        this.objects.add(obj);
    }

    public final void setDescriptionsBundle(java.util.ResourceBundle resourceBundle) {
        this.options.bundle = resourceBundle;
    }

    public void parse(String... strArr) {
        try {
            parse(true, strArr);
        } catch (ParameterException e) {
            e.setJCommander(this);
            throw e;
        }
    }

    public void parseWithoutValidation(String... strArr) {
        parse(false, strArr);
    }

    private void parse(boolean z, String... strArr) {
        p("Parsing \"" + Strings.join(StringUtils.SPACE, strArr) + "\"\n  with:" + Strings.join(StringUtils.SPACE, this.objects.toArray()));
        if (this.descriptions == null) {
            createDescriptions();
        }
        initializeDefaultValues();
        parseValues(expandArgs(strArr), z);
        if (z) {
            validateOptions();
        }
    }

    private void initializeDefaultValues() {
        if (this.options.defaultProvider != null) {
            Iterator<ParameterDescription> it = this.descriptions.values().iterator();
            while (it.hasNext()) {
                initializeDefaultValue(it.next());
            }
            Iterator<Map.Entry<ProgramName, JCommander>> it2 = this.commands.entrySet().iterator();
            while (it2.hasNext()) {
                it2.next().getValue().initializeDefaultValues();
            }
        }
    }

    private void validateOptions() {
        int size;
        if (this.helpWasSpecified) {
            return;
        }
        if (!this.requiredFields.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<ParameterDescription> it = this.requiredFields.values().iterator();
            while (it.hasNext()) {
                arrayList.add("[" + Strings.join(" | ", it.next().getParameter().names()) + "]");
            }
            throw new ParameterException("The following " + pluralize(this.requiredFields.size(), "option is required: ", "options are required: ") + Strings.join(", ", arrayList));
        }
        MainParameter mainParameter = this.mainParameter;
        if (mainParameter == null || mainParameter.description == null) {
            return;
        }
        ParameterDescription parameterDescription = this.mainParameter.description;
        if (parameterDescription.getParameter().required() && !parameterDescription.isAssigned()) {
            throw new ParameterException("Main parameters are required (\"" + parameterDescription.getDescription() + "\")");
        }
        int arity = parameterDescription.getParameter().arity();
        if (arity != -1) {
            Object obj = parameterDescription.getParameterized().get(this.mainParameter.object);
            if (!List.class.isAssignableFrom(obj.getClass()) || (size = ((List) obj).size()) == arity) {
                return;
            }
            throw new ParameterException("There should be exactly " + arity + " main parameters but " + size + " were found");
        }
    }

    private String[] expandArgs(String[] strArr) {
        List<String> newArrayList = Lists.newArrayList();
        for (String str : strArr) {
            if (str.startsWith("@") && this.options.expandAtSign) {
                newArrayList.addAll(readFile(str.substring(1)));
            } else {
                newArrayList.addAll(expandDynamicArg(str));
            }
        }
        List newArrayList2 = Lists.newArrayList();
        for (String str2 : newArrayList) {
            if (isOption(str2)) {
                String separatorFor = getSeparatorFor(str2);
                if (!StringUtils.SPACE.equals(separatorFor)) {
                    String[] split = str2.split("[" + separatorFor + "]", 2);
                    int length = split.length;
                    for (int i = 0; i < length; i++) {
                        newArrayList2.add(split[i]);
                    }
                } else {
                    newArrayList2.add(str2);
                }
            } else {
                newArrayList2.add(str2);
            }
        }
        return (String[]) newArrayList2.toArray(new String[newArrayList2.size()]);
    }

    private List<String> expandDynamicArg(String str) {
        for (ParameterDescription parameterDescription : this.descriptions.values()) {
            if (parameterDescription.isDynamicParameter()) {
                for (String str2 : parameterDescription.getParameter().names()) {
                    if (str.startsWith(str2) && !str.equals(str2)) {
                        return Arrays.asList(str2, str.substring(str2.length()));
                    }
                }
            }
        }
        return Arrays.asList(str);
    }

    private boolean matchArg(String str, FuzzyMap.IKey iKey) {
        String lowerCase;
        if (this.options.caseSensitiveOptions) {
            lowerCase = iKey.getName();
        } else {
            lowerCase = iKey.getName().toLowerCase();
        }
        return this.options.allowAbbreviatedOptions ? lowerCase.startsWith(str) : this.descriptions.get(iKey) != null ? !StringUtils.SPACE.equals(getSeparatorFor(str)) ? str.startsWith(lowerCase) : lowerCase.equals(str) : lowerCase.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOption(String str) {
        if (this.options.acceptUnknownOptions) {
            return true;
        }
        if (!this.options.caseSensitiveOptions) {
            str = str.toLowerCase();
        }
        Iterator<FuzzyMap.IKey> it = this.descriptions.keySet().iterator();
        while (it.hasNext()) {
            if (matchArg(str, it.next())) {
                return true;
            }
        }
        Iterator<ProgramName> it2 = this.commands.keySet().iterator();
        while (it2.hasNext()) {
            if (matchArg(str, it2.next())) {
                return true;
            }
        }
        return false;
    }

    private ParameterDescription getPrefixDescriptionFor(String str) {
        for (Map.Entry<FuzzyMap.IKey, ParameterDescription> entry : this.descriptions.entrySet()) {
            if (Strings.startsWith(str, entry.getKey().getName(), this.options.caseSensitiveOptions)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private ParameterDescription getDescriptionFor(String str) {
        return getPrefixDescriptionFor(str);
    }

    private String getSeparatorFor(String str) {
        Parameters parameters;
        ParameterDescription descriptionFor = getDescriptionFor(str);
        if (descriptionFor != null && (parameters = (Parameters) descriptionFor.getObject().getClass().getAnnotation(Parameters.class)) != null) {
            return parameters.separators();
        }
        return StringUtils.SPACE;
    }

    private List<String> readFile(String str) {
        List<String> newArrayList = Lists.newArrayList();
        try {
            BufferedReader newBufferedReader = Files.newBufferedReader(Paths.get(str, new String[0]), this.options.atFileCharset);
            while (true) {
                try {
                    String readLine = newBufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (readLine.length() > 0 && !readLine.trim().startsWith("#")) {
                        newArrayList.add(readLine);
                    }
                } finally {
                }
            }
            if (newBufferedReader != null) {
                newBufferedReader.close();
            }
            return newArrayList;
        } catch (IOException e) {
            throw new ParameterException("Could not read file " + str + ": " + e);
        }
    }

    private static String trim(String str) {
        String trim = str.trim();
        return (trim.startsWith("\"") && trim.endsWith("\"") && trim.length() > 1) ? trim.substring(1, trim.length() - 1) : trim;
    }

    public void createDescriptions() {
        this.descriptions = Maps.newHashMap();
        Iterator<Object> it = this.objects.iterator();
        while (it.hasNext()) {
            addDescription(it.next());
        }
    }

    private void addDescription(Object obj) {
        Object obj2 = obj;
        obj.getClass();
        Iterator<Parameterized> it = this.parameterizedParser.parseArg(obj2).iterator();
        while (it.hasNext()) {
            Parameterized next = it.next();
            WrappedParameter wrappedParameter = next.getWrappedParameter();
            int i = 0;
            if (wrappedParameter != null && wrappedParameter.getParameter() != null) {
                Parameter parameter = wrappedParameter.getParameter();
                if (parameter.names().length == 0) {
                    p("Found main parameter:" + next);
                    if (this.mainParameter != null) {
                        throw new ParameterException("Only one @Parameter with no names attribute is allowed, found:" + this.mainParameter + " and " + next);
                    }
                    MainParameter mainParameter = new MainParameter();
                    this.mainParameter = mainParameter;
                    mainParameter.parameterized = next;
                    this.mainParameter.object = obj2;
                    this.mainParameter.annotation = parameter;
                    this.mainParameter.description = new ParameterDescription(obj, parameter, next, this.options.bundle, this);
                } else {
                    ParameterDescription parameterDescription = new ParameterDescription(obj, parameter, next, this.options.bundle, this);
                    String[] names = parameter.names();
                    int length = names.length;
                    while (i < length) {
                        String str = names[i];
                        if (this.descriptions.containsKey(new StringKey(str))) {
                            throw new ParameterException("Found the option " + str + " multiple times");
                        }
                        p("Adding description for " + str);
                        this.fields.put(next, parameterDescription);
                        this.descriptions.put(new StringKey(str), parameterDescription);
                        if (parameter.required()) {
                            this.requiredFields.put(next, parameterDescription);
                        }
                        i++;
                    }
                }
            } else if (next.getDelegateAnnotation() != null) {
                Object obj3 = next.get(obj2);
                if (obj3 == null) {
                    throw new ParameterException("Delegate field '" + next.getName() + "' cannot be null.");
                }
                addDescription(obj3);
            } else if (wrappedParameter != null && wrappedParameter.getDynamicParameter() != null) {
                DynamicParameter dynamicParameter = wrappedParameter.getDynamicParameter();
                String[] names2 = dynamicParameter.names();
                int length2 = names2.length;
                while (i < length2) {
                    String str2 = names2[i];
                    if (this.descriptions.containsKey(str2)) {
                        throw new ParameterException("Found the option " + str2 + " multiple times");
                    }
                    p("Adding description for " + str2);
                    Iterator<Parameterized> it2 = it;
                    int i2 = length2;
                    ParameterDescription parameterDescription2 = new ParameterDescription(obj, dynamicParameter, next, this.options.bundle, this);
                    this.fields.put(next, parameterDescription2);
                    this.descriptions.put(new StringKey(str2), parameterDescription2);
                    if (dynamicParameter.required()) {
                        this.requiredFields.put(next, parameterDescription2);
                    }
                    i++;
                    length2 = i2;
                    it = it2;
                }
            }
            obj2 = obj;
            it = it;
        }
    }

    private void initializeDefaultValue(ParameterDescription parameterDescription) {
        for (String str : parameterDescription.getParameter().names()) {
            String defaultValueFor = this.options.defaultProvider.getDefaultValueFor(str);
            if (defaultValueFor != null) {
                p("Initializing " + str + " with default value:" + defaultValueFor);
                parameterDescription.addValue(defaultValueFor, true);
                this.requiredFields.remove(parameterDescription.getParameterized());
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x018f, code lost:
    
        r14 = r13.descriptions.values().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x019d, code lost:
    
        if (r14.hasNext() == false) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x019f, code lost:
    
        r15 = r14.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01a9, code lost:
    
        if (r15.isAssigned() == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01ab, code lost:
    
        r13.fields.get(r15.getParameterized()).setAssigned(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01bb, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseValues(java.lang.String[] r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beust.jcommander.JCommander.parseValues(java.lang.String[], boolean):void");
    }

    private boolean isBooleanType(Class<?> cls) {
        return Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls);
    }

    private void handleBooleanOption(ParameterDescription parameterDescription, Class<?> cls) {
        Boolean bool = (Boolean) parameterDescription.getParameterized().get(parameterDescription.getObject());
        if (bool != null) {
            parameterDescription.addValue(bool.booleanValue() ? BooleanUtils.FALSE : "true");
        } else if (!cls.isPrimitive()) {
            parameterDescription.addValue("true");
        }
        this.requiredFields.remove(parameterDescription.getParameterized());
    }

    private class DefaultVariableArity implements IVariableArity {
        private DefaultVariableArity() {
        }

        @Override // com.beust.jcommander.IVariableArity
        public int processVariableArity(String str, String[] strArr) {
            int i = 0;
            while (i < strArr.length && !JCommander.this.isOption(strArr[i])) {
                i++;
            }
            return i;
        }
    }

    private final int determineArity(String[] strArr, int i, ParameterDescription parameterDescription, IVariableArity iVariableArity) {
        List newArrayList = Lists.newArrayList();
        while (true) {
            i++;
            if (i < strArr.length) {
                newArrayList.add(strArr[i]);
            } else {
                return iVariableArity.processVariableArity(parameterDescription.getParameter().names()[0], (String[]) newArrayList.toArray(new String[0]));
            }
        }
    }

    private int processPassword(String[] strArr, int i, ParameterDescription parameterDescription, boolean z) {
        int determineArity = determineArity(strArr, i, parameterDescription, this.DEFAULT_VARIABLE_ARITY);
        if (determineArity == 0) {
            parameterDescription.addValue(new String(readPassword(parameterDescription.getDescription(), parameterDescription.getParameter().echoInput())));
            this.requiredFields.remove(parameterDescription.getParameterized());
            return 1;
        }
        if (determineArity == 1) {
            return processFixedArity(strArr, i, parameterDescription, z, List.class, 1);
        }
        throw new ParameterException("Password parameter must have at most 1 argument.");
    }

    private int processVariableArity(String[] strArr, int i, ParameterDescription parameterDescription, boolean z) {
        IVariableArity iVariableArity;
        Object object = parameterDescription.getObject();
        if (!(object instanceof IVariableArity)) {
            iVariableArity = this.DEFAULT_VARIABLE_ARITY;
        } else {
            iVariableArity = (IVariableArity) object;
        }
        return processFixedArity(strArr, i, parameterDescription, z, List.class, determineArity(strArr, i, parameterDescription, iVariableArity));
    }

    private int processFixedArity(String[] strArr, int i, ParameterDescription parameterDescription, boolean z, Class<?> cls) {
        int arity = parameterDescription.getParameter().arity();
        if (arity == -1) {
            arity = 1;
        }
        return processFixedArity(strArr, i, parameterDescription, z, cls, arity);
    }

    private int processFixedArity(String[] strArr, int i, ParameterDescription parameterDescription, boolean z, Class<?> cls, int i2) {
        String str = strArr[i];
        if (i2 == 0 && isBooleanType(cls)) {
            handleBooleanOption(parameterDescription, cls);
        } else {
            if (i2 == 0) {
                throw new ParameterException("Expected a value after parameter " + str);
            }
            if (i >= strArr.length - 1) {
                throw new ParameterException("Expected a value after parameter " + str);
            }
            boolean equals = "--".equals(strArr[i + 1]);
            if (i + i2 >= strArr.length) {
                throw new ParameterException("Expected " + i2 + " values after " + str);
            }
            Object obj = null;
            for (int i3 = 1; i3 <= i2; i3++) {
                obj = parameterDescription.addValue(str, strArr[i + i3 + (equals ? 1 : 0)], false, z, i3 - 1);
                this.requiredFields.remove(parameterDescription.getParameterized());
            }
            if (obj != null && z) {
                parameterDescription.validateValueParameter(str, obj);
            }
        }
        return i2 + 1;
    }

    private char[] readPassword(String str, boolean z) {
        getConsole().print(str + ": ");
        return getConsole().readPassword(z);
    }

    private String[] subArray(String[] strArr, int i) {
        int length = strArr.length - i;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, i, strArr2, 0, length);
        return strArr2;
    }

    private void initMainParameterValue(String str) {
        List list;
        MainParameter mainParameter = this.mainParameter;
        if (mainParameter == null) {
            throw new ParameterException("Was passed main parameter '" + str + "' but no main parameter was defined in your arg class");
        }
        Object obj = mainParameter.parameterized.get(this.mainParameter.object);
        if (List.class.isAssignableFrom(this.mainParameter.parameterized.getType())) {
            if (obj == null) {
                list = Lists.newArrayList();
            } else {
                list = (List) obj;
            }
            if (this.mainParameter.firstTimeMainParameter) {
                list.clear();
                this.mainParameter.firstTimeMainParameter = false;
            }
            this.mainParameter.multipleValue = list;
            this.mainParameter.parameterized.set(this.mainParameter.object, list);
        }
    }

    public String getMainParameterDescription() {
        if (this.descriptions == null) {
            createDescriptions();
        }
        if (this.mainParameter.annotation != null) {
            return this.mainParameter.annotation.description();
        }
        return null;
    }

    public void setProgramName(String str) {
        setProgramName(str, new String[0]);
    }

    public String getProgramName() {
        ProgramName programName = this.programName;
        if (programName == null) {
            return null;
        }
        return programName.getName();
    }

    public String getProgramDisplayName() {
        ProgramName programName = this.programName;
        if (programName == null) {
            return null;
        }
        return programName.getDisplayName();
    }

    public void setProgramName(String str, String... strArr) {
        this.programName = new ProgramName(str, Arrays.asList(strArr));
    }

    public void usage() {
        StringBuilder sb = new StringBuilder();
        this.usageFormatter.usage(sb);
        getConsole().println(sb.toString());
    }

    public void setUsageFormatter(IUsageFormatter iUsageFormatter) {
        if (iUsageFormatter == null) {
            throw new IllegalArgumentException("Argument UsageFormatter must not be null");
        }
        this.usageFormatter = iUsageFormatter;
    }

    public IUsageFormatter getUsageFormatter() {
        return this.usageFormatter;
    }

    public Options getOptions() {
        return this.options;
    }

    public Map<FuzzyMap.IKey, ParameterDescription> getDescriptions() {
        return this.descriptions;
    }

    public MainParameter getMainParameter() {
        return this.mainParameter;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private JCommander jCommander = new JCommander();
        private String[] args = null;

        public Builder addObject(Object obj) {
            this.jCommander.addObject(obj);
            return this;
        }

        public Builder resourceBundle(java.util.ResourceBundle resourceBundle) {
            this.jCommander.setDescriptionsBundle(resourceBundle);
            return this;
        }

        public Builder args(String[] strArr) {
            this.args = strArr;
            return this;
        }

        public Builder console(Console console) {
            this.jCommander.setConsole(console);
            return this;
        }

        public Builder expandAtSign(Boolean bool) {
            this.jCommander.setExpandAtSign(bool.booleanValue());
            return this;
        }

        public Builder programName(String str) {
            this.jCommander.setProgramName(str);
            return this;
        }

        public Builder columnSize(int i) {
            this.jCommander.setColumnSize(i);
            return this;
        }

        public Builder defaultProvider(IDefaultProvider iDefaultProvider) {
            this.jCommander.setDefaultProvider(iDefaultProvider);
            return this;
        }

        public Builder addConverterFactory(IStringConverterFactory iStringConverterFactory) {
            this.jCommander.addConverterFactory(iStringConverterFactory);
            return this;
        }

        public Builder verbose(int i) {
            this.jCommander.setVerbose(i);
            return this;
        }

        public Builder allowAbbreviatedOptions(boolean z) {
            this.jCommander.setAllowAbbreviatedOptions(z);
            return this;
        }

        public Builder acceptUnknownOptions(boolean z) {
            this.jCommander.setAcceptUnknownOptions(z);
            return this;
        }

        public Builder allowParameterOverwriting(boolean z) {
            this.jCommander.setAllowParameterOverwriting(z);
            return this;
        }

        public Builder atFileCharset(Charset charset) {
            this.jCommander.setAtFileCharset(charset);
            return this;
        }

        public Builder addConverterInstanceFactory(IStringConverterInstanceFactory iStringConverterInstanceFactory) {
            this.jCommander.addConverterInstanceFactory(iStringConverterInstanceFactory);
            return this;
        }

        public Builder addCommand(Object obj) {
            this.jCommander.addCommand(obj);
            return this;
        }

        public Builder addCommand(String str, Object obj, String... strArr) {
            this.jCommander.addCommand(str, obj, strArr);
            return this;
        }

        public Builder usageFormatter(IUsageFormatter iUsageFormatter) {
            this.jCommander.setUsageFormatter(iUsageFormatter);
            return this;
        }

        public JCommander build() {
            String[] strArr = this.args;
            if (strArr != null) {
                this.jCommander.parse(strArr);
            }
            return this.jCommander;
        }
    }

    public Map<Parameterized, ParameterDescription> getFields() {
        return this.fields;
    }

    public Comparator<? super ParameterDescription> getParameterDescriptionComparator() {
        return this.options.parameterDescriptionComparator;
    }

    public void setParameterDescriptionComparator(Comparator<? super ParameterDescription> comparator) {
        this.options.parameterDescriptionComparator = comparator;
    }

    public void setColumnSize(int i) {
        this.options.columnSize = i;
    }

    public int getColumnSize() {
        return this.options.columnSize;
    }

    public java.util.ResourceBundle getBundle() {
        return this.options.bundle;
    }

    public List<ParameterDescription> getParameters() {
        return new ArrayList(this.fields.values());
    }

    public ParameterDescription getMainParameterValue() {
        return this.mainParameter.description;
    }

    private void p(String str) {
        if (this.options.verbose > 0 || System.getProperty(DEBUG_PROPERTY) != null) {
            getConsole().println("[JCommander] " + str);
        }
    }

    public void setDefaultProvider(IDefaultProvider iDefaultProvider) {
        this.options.defaultProvider = iDefaultProvider;
    }

    public void addConverterFactory(final IStringConverterFactory iStringConverterFactory) {
        addConverterInstanceFactory(new IStringConverterInstanceFactory() { // from class: com.beust.jcommander.JCommander.1
            @Override // com.beust.jcommander.IStringConverterInstanceFactory
            public IStringConverter<?> getConverterInstance(Parameter parameter, Class<?> cls, String str) {
                Class<? extends IStringConverter<?>> converter = iStringConverterFactory.getConverter(cls);
                if (str == null) {
                    try {
                        str = parameter.names().length > 0 ? parameter.names()[0] : "[Main class]";
                    } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        throw new ParameterException(e);
                    }
                }
                if (converter != null) {
                    return (IStringConverter) JCommander.instantiateConverter(str, converter);
                }
                return null;
            }
        });
    }

    public void addConverterInstanceFactory(IStringConverterInstanceFactory iStringConverterInstanceFactory) {
        this.options.converterInstanceFactories.add(0, iStringConverterInstanceFactory);
    }

    private IStringConverter<?> findConverterInstance(Parameter parameter, Class<?> cls, String str) {
        Iterator it = this.options.converterInstanceFactories.iterator();
        while (it.hasNext()) {
            IStringConverter<?> converterInstance = ((IStringConverterInstanceFactory) it.next()).getConverterInstance(parameter, cls, str);
            if (converterInstance != null) {
                return converterInstance;
            }
        }
        return null;
    }

    public Object convertValue(final Parameterized parameterized, Class cls, String str, String str2) {
        Parameter parameter = parameterized.getParameter();
        if (parameter == null) {
            return str2;
        }
        if (str == null) {
            str = parameter.names().length > 0 ? parameter.names()[0] : "[Main class]";
        }
        IStringConverter<?> iStringConverter = cls.isAssignableFrom(List.class) ? (IStringConverter) tryInstantiateConverter(str, parameter.listConverter()) : null;
        if (cls.isAssignableFrom(List.class) && iStringConverter == null) {
            iStringConverter = new DefaultListConverter((IParameterSplitter) tryInstantiateConverter(null, parameter.splitter()), new IStringConverter() { // from class: com.beust.jcommander.JCommander.2
                @Override // com.beust.jcommander.IStringConverter
                public Object convert(String str3) {
                    Type findFieldGenericType = parameterized.findFieldGenericType();
                    return JCommander.this.convertValue(parameterized, findFieldGenericType instanceof Class ? (Class) findFieldGenericType : String.class, null, str3);
                }
            });
        }
        if (iStringConverter == null) {
            iStringConverter = (IStringConverter) tryInstantiateConverter(str, parameter.converter());
        }
        if (iStringConverter == null) {
            iStringConverter = findConverterInstance(parameter, cls, str);
        }
        if (iStringConverter == null && cls.isEnum()) {
            iStringConverter = new EnumConverter<>(str, cls);
        }
        if (iStringConverter == null) {
            iStringConverter = new StringConverter();
        }
        return iStringConverter.convert(str2);
    }

    private static <T> T tryInstantiateConverter(String str, Class<T> cls) {
        if (cls != NoConverter.class && cls != null) {
            try {
                return (T) instantiateConverter(str, cls);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T instantiateConverter(String str, Class<? extends T> cls) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> constructor = null;
        Constructor<?> constructor2 = null;
        for (Constructor<?> constructor3 : cls.getDeclaredConstructors()) {
            constructor3.setAccessible(true);
            Class<?>[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(String.class)) {
                constructor = constructor3;
            } else if (parameterTypes.length == 0) {
                constructor2 = constructor3;
            }
        }
        if (constructor != null) {
            return (T) constructor.newInstance(str);
        }
        if (constructor2 != null) {
            return (T) constructor2.newInstance(null);
        }
        return null;
    }

    public void addCommand(String str, Object obj) {
        addCommand(str, obj, new String[0]);
    }

    public void addCommand(Object obj) {
        Parameters parameters = (Parameters) obj.getClass().getAnnotation(Parameters.class);
        if (parameters != null && parameters.commandNames().length > 0) {
            for (String str : parameters.commandNames()) {
                addCommand(str, obj);
            }
            return;
        }
        throw new ParameterException("Trying to add command " + obj.getClass().getName() + " without specifying its names in @Parameters");
    }

    public void addCommand(String str, Object obj, String... strArr) {
        JCommander jCommander = new JCommander(this.options);
        jCommander.addObject(obj);
        jCommander.createDescriptions();
        jCommander.setProgramName(str, strArr);
        ProgramName programName = jCommander.programName;
        this.commands.put(programName, jCommander);
        this.aliasMap.put(new StringKey(str), programName);
        for (String str2 : strArr) {
            StringKey stringKey = new StringKey(str2);
            if (!stringKey.equals(str)) {
                ProgramName programName2 = this.aliasMap.get(stringKey);
                if (programName2 != null && !programName2.equals(programName)) {
                    throw new ParameterException("Cannot set alias " + stringKey + " for " + str + " command because it has already been defined for " + programName2.name + " command");
                }
                this.aliasMap.put(stringKey, programName);
            }
        }
    }

    public Map<String, JCommander> getCommands() {
        Map<String, JCommander> newLinkedHashMap = Maps.newLinkedHashMap();
        for (Map.Entry<ProgramName, JCommander> entry : this.commands.entrySet()) {
            newLinkedHashMap.put(entry.getKey().name, entry.getValue());
        }
        return newLinkedHashMap;
    }

    public Map<ProgramName, JCommander> getRawCommands() {
        Map<ProgramName, JCommander> newLinkedHashMap = Maps.newLinkedHashMap();
        for (Map.Entry<ProgramName, JCommander> entry : this.commands.entrySet()) {
            newLinkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return newLinkedHashMap;
    }

    public String getParsedCommand() {
        return this.parsedCommand;
    }

    public String getParsedAlias() {
        return this.parsedAlias;
    }

    private String s(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(StringUtils.SPACE);
        }
        return sb.toString();
    }

    public List<Object> getObjects() {
        return this.objects;
    }

    private ParameterDescription findParameterDescription(String str) {
        return (ParameterDescription) FuzzyMap.findInMap(this.descriptions, new StringKey(str), this.options.caseSensitiveOptions, this.options.allowAbbreviatedOptions);
    }

    private JCommander findCommand(ProgramName programName) {
        return (JCommander) FuzzyMap.findInMap(this.commands, programName, this.options.caseSensitiveOptions, this.options.allowAbbreviatedOptions);
    }

    private ProgramName findProgramName(String str) {
        return (ProgramName) FuzzyMap.findInMap(this.aliasMap, new StringKey(str), this.options.caseSensitiveOptions, this.options.allowAbbreviatedOptions);
    }

    public JCommander findCommandByAlias(String str) {
        ProgramName findProgramName = findProgramName(str);
        if (findProgramName == null) {
            return null;
        }
        JCommander findCommand = findCommand(findProgramName);
        if (findCommand != null) {
            return findCommand;
        }
        throw new IllegalStateException("There appears to be inconsistency in the internal command database.  This is likely a bug. Please report.");
    }

    public static final class ProgramName implements FuzzyMap.IKey {
        private final List<String> aliases;
        private final String name;

        ProgramName(String str, List<String> list) {
            this.name = str;
            this.aliases = list;
        }

        @Override // com.beust.jcommander.FuzzyMap.IKey
        public String getName() {
            return this.name;
        }

        public String getDisplayName() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            if (!this.aliases.isEmpty()) {
                sb.append("(");
                Iterator<String> it = this.aliases.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    if (it.hasNext()) {
                        sb.append(FirebaseConstants.SEPARATOR);
                    }
                }
                sb.append(")");
            }
            return sb.toString();
        }

        public int hashCode() {
            String str = this.name;
            return 31 + (str == null ? 0 : str.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ProgramName programName = (ProgramName) obj;
            String str = this.name;
            if (str == null) {
                if (programName.name != null) {
                    return false;
                }
            } else if (!str.equals(programName.name)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return getDisplayName();
        }
    }

    public void setVerbose(int i) {
        this.options.verbose = i;
    }

    public void setCaseSensitiveOptions(boolean z) {
        this.options.caseSensitiveOptions = z;
    }

    public void setAllowAbbreviatedOptions(boolean z) {
        this.options.allowAbbreviatedOptions = z;
    }

    public void setAcceptUnknownOptions(boolean z) {
        this.options.acceptUnknownOptions = z;
    }

    public List<String> getUnknownOptions() {
        return this.unknownArgs;
    }

    public void setAllowParameterOverwriting(boolean z) {
        this.options.allowParameterOverwriting = z;
    }

    public boolean isParameterOverwritingAllowed() {
        return this.options.allowParameterOverwriting;
    }

    public void setAtFileCharset(Charset charset) {
        this.options.atFileCharset = charset;
    }
}
