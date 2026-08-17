package com.beust.jcommander;

import com.beust.jcommander.validators.NoValidator;
import com.beust.jcommander.validators.NoValueValidator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public class ParameterDescription {
    private java.util.ResourceBundle bundle;
    private Object defaultObject;
    private String description;
    private DynamicParameter dynamicParameterAnnotation;
    private JCommander jCommander;
    private Object object;
    private Parameter parameterAnnotation;
    private Parameterized parameterized;
    private WrappedParameter wrappedParameter;
    private boolean assigned = false;
    private String longestName = "";

    public ParameterDescription(Object obj, DynamicParameter dynamicParameter, Parameterized parameterized, java.util.ResourceBundle resourceBundle, JCommander jCommander) {
        if (!Map.class.isAssignableFrom(parameterized.getType())) {
            throw new ParameterException("@DynamicParameter " + parameterized.getName() + " should be of type Map but is " + parameterized.getType().getName());
        }
        this.dynamicParameterAnnotation = dynamicParameter;
        this.wrappedParameter = new WrappedParameter(dynamicParameter);
        init(obj, parameterized, resourceBundle, jCommander);
    }

    public ParameterDescription(Object obj, Parameter parameter, Parameterized parameterized, java.util.ResourceBundle resourceBundle, JCommander jCommander) {
        this.parameterAnnotation = parameter;
        this.wrappedParameter = new WrappedParameter(parameter);
        init(obj, parameterized, resourceBundle, jCommander);
    }

    private java.util.ResourceBundle findResourceBundle(Object obj) {
        Parameters parameters = (Parameters) obj.getClass().getAnnotation(Parameters.class);
        if (parameters != null && !isEmpty(parameters.resourceBundle())) {
            return java.util.ResourceBundle.getBundle(parameters.resourceBundle(), Locale.getDefault());
        }
        ResourceBundle resourceBundle = (ResourceBundle) obj.getClass().getAnnotation(ResourceBundle.class);
        if (resourceBundle == null || isEmpty(resourceBundle.value())) {
            return null;
        }
        return java.util.ResourceBundle.getBundle(resourceBundle.value(), Locale.getDefault());
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    private void initDescription(String str, String str2, String[] strArr) {
        java.util.ResourceBundle resourceBundle;
        this.description = str;
        if (!"".equals(str2) && (resourceBundle = this.bundle) != null) {
            this.description = resourceBundle.getString(str2);
        }
        for (String str3 : strArr) {
            if (str3.length() > this.longestName.length()) {
                this.longestName = str3;
            }
        }
    }

    private void init(Object obj, Parameterized parameterized, java.util.ResourceBundle resourceBundle, JCommander jCommander) {
        Parameter parameter;
        String description;
        this.object = obj;
        this.parameterized = parameterized;
        this.bundle = resourceBundle;
        if (resourceBundle == null) {
            this.bundle = findResourceBundle(obj);
        }
        this.jCommander = jCommander;
        if (this.parameterAnnotation != null) {
            if (Enum.class.isAssignableFrom(parameterized.getType()) && this.parameterAnnotation.description().isEmpty()) {
                description = "Options: " + EnumSet.allOf(parameterized.getType());
            } else {
                description = this.parameterAnnotation.description();
            }
            initDescription(description, this.parameterAnnotation.descriptionKey(), this.parameterAnnotation.names());
        } else {
            DynamicParameter dynamicParameter = this.dynamicParameterAnnotation;
            if (dynamicParameter != null) {
                initDescription(dynamicParameter.description(), this.dynamicParameterAnnotation.descriptionKey(), this.dynamicParameterAnnotation.names());
            } else {
                throw new AssertionError("Shound never happen");
            }
        }
        try {
            this.defaultObject = parameterized.get(obj);
        } catch (Exception unused) {
        }
        if (this.defaultObject == null || (parameter = this.parameterAnnotation) == null) {
            return;
        }
        validateDefaultValues(parameter.names());
    }

    private void validateDefaultValues(String[] strArr) {
        validateValueParameter(strArr.length > 0 ? strArr[0] : "", this.defaultObject);
    }

    public String getLongestName() {
        return this.longestName;
    }

    public Object getDefault() {
        return this.defaultObject;
    }

    public String getDescription() {
        return this.description;
    }

    public Object getObject() {
        return this.object;
    }

    public String getNames() {
        StringBuilder sb = new StringBuilder();
        String[] names = this.wrappedParameter.names();
        for (int i = 0; i < names.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(names[i]);
        }
        return sb.toString();
    }

    public WrappedParameter getParameter() {
        return this.wrappedParameter;
    }

    public Parameterized getParameterized() {
        return this.parameterized;
    }

    private boolean isMultiOption() {
        Class<?> type = this.parameterized.getType();
        return type.equals(List.class) || type.equals(Set.class) || this.parameterized.isDynamicParameter();
    }

    public void addValue(String str) {
        addValue(str, false);
    }

    public boolean isAssigned() {
        return this.assigned;
    }

    public void setAssigned(boolean z) {
        this.assigned = z;
    }

    public void addValue(String str, boolean z) {
        addValue(null, str, z, true, -1);
    }

    Object addValue(String str, String str2, boolean z, boolean z2, int i) {
        StringBuilder sb = new StringBuilder("Adding ");
        sb.append(z ? "default " : "");
        sb.append("value:");
        sb.append(str2);
        sb.append(" to parameter:");
        sb.append(this.parameterized.getName());
        p(sb.toString());
        if (str == null) {
            str = this.wrappedParameter.names()[0];
        }
        if ((i == 0 && this.assigned && !isMultiOption() && !this.jCommander.isParameterOverwritingAllowed()) || isNonOverwritableForced()) {
            throw new ParameterException("Can only specify option " + str + " once.");
        }
        if (z2) {
            validateParameter(str, str2);
        }
        Class<?> type = this.parameterized.getType();
        Object convertValue = this.jCommander.convertValue(getParameterized(), getParameterized().getType(), str, str2);
        if (z2) {
            validateValueParameter(str, convertValue);
        }
        if (Collection.class.isAssignableFrom(type)) {
            Collection<Object> collection = (Collection) this.parameterized.get(this.object);
            if (collection == null || fieldIsSetForTheFirstTime(z)) {
                collection = newCollection(type);
                this.parameterized.set(this.object, collection);
            }
            if (convertValue instanceof Collection) {
                collection.addAll((Collection) convertValue);
            } else {
                collection.add(convertValue);
            }
            convertValue = collection;
        } else {
            List<SubParameterIndex> findSubParameters = findSubParameters(type);
            if (!findSubParameters.isEmpty()) {
                convertValue = handleSubParameters(str2, i, type, findSubParameters);
            } else {
                this.wrappedParameter.addValue(this.parameterized, this.object, convertValue);
            }
        }
        if (!z) {
            this.assigned = true;
        }
        return convertValue;
    }

    private Object handleSubParameters(String str, int i, Class<?> cls, List<SubParameterIndex> list) {
        SubParameterIndex subParameterIndex;
        Iterator<SubParameterIndex> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                subParameterIndex = null;
                break;
            }
            subParameterIndex = it.next();
            if (subParameterIndex.order == i) {
                break;
            }
        }
        if (subParameterIndex != null) {
            Object obj = this.parameterized.get(this.object);
            if (obj == null) {
                try {
                    obj = cls.newInstance();
                    this.parameterized.set(this.object, obj);
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new ParameterException("Couldn't instantiate " + cls, e);
                }
            }
            this.wrappedParameter.addValue(this.parameterized, obj, str, subParameterIndex.field);
            return obj;
        }
        throw new ParameterException("Couldn't find where to assign parameter " + str + " in " + cls);
    }

    public Parameter getParameterAnnotation() {
        return this.parameterAnnotation;
    }

    class SubParameterIndex {
        Field field;
        int order;

        public SubParameterIndex(int i, Field field) {
            this.order = i;
            this.field = field;
        }
    }

    private List<SubParameterIndex> findSubParameters(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        for (Field field : cls.getDeclaredFields()) {
            Annotation annotation = field.getAnnotation(SubParameter.class);
            if (annotation != null) {
                arrayList.add(new SubParameterIndex(((SubParameter) annotation).order(), field));
            }
        }
        return arrayList;
    }

    private void validateParameter(String str, String str2) {
        Class<? extends IParameterValidator>[] validateWith = this.wrappedParameter.validateWith();
        if (validateWith == null || validateWith.length <= 0) {
            return;
        }
        for (Class<? extends IParameterValidator> cls : validateWith) {
            validateParameter(cls, str, str2);
        }
    }

    void validateValueParameter(String str, Object obj) {
        Class<? extends IValueValidator>[] validateValueWith = this.wrappedParameter.validateValueWith();
        if (validateValueWith == null || validateValueWith.length <= 0) {
            return;
        }
        for (Class<? extends IValueValidator> cls : validateValueWith) {
            validateValueParameter(cls, str, obj);
        }
    }

    public void validateValueParameter(Class<? extends IValueValidator> cls, String str, Object obj) {
        if (cls != NoValueValidator.class) {
            try {
                p("Validating value parameter:" + str + " value:" + obj + " validator:" + cls);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new ParameterException("Can't instantiate validator:" + e);
            }
        }
        cls.newInstance().validate(str, obj);
    }

    public void validateParameter(Class<? extends IParameterValidator> cls, String str, String str2) {
        if (cls != NoValidator.class) {
            try {
                p("Validating parameter:" + str + " value:" + str2 + " validator:" + cls);
            } catch (ParameterException e) {
                throw e;
            } catch (IllegalAccessException | InstantiationException e2) {
                throw new ParameterException("Can't instantiate validator:" + e2);
            } catch (Exception e3) {
                throw new ParameterException(e3);
            }
        }
        cls.newInstance().validate(str, str2);
        if (IParameterValidator2.class.isAssignableFrom(cls)) {
            ((IParameterValidator2) cls.newInstance()).validate(str, str2, this);
        }
    }

    private Collection<Object> newCollection(Class<?> cls) {
        if (SortedSet.class.isAssignableFrom(cls)) {
            return new TreeSet();
        }
        if (LinkedHashSet.class.isAssignableFrom(cls)) {
            return new LinkedHashSet();
        }
        if (Set.class.isAssignableFrom(cls)) {
            return new HashSet();
        }
        if (List.class.isAssignableFrom(cls)) {
            return new ArrayList();
        }
        throw new ParameterException("Parameters of Collection type '" + cls.getSimpleName() + "' are not supported. Please use List or Set instead.");
    }

    private boolean fieldIsSetForTheFirstTime(boolean z) {
        return (z || this.assigned) ? false : true;
    }

    private void p(String str) {
        if (System.getProperty(JCommander.DEBUG_PROPERTY) != null) {
            this.jCommander.getConsole().println("[ParameterDescription] " + str);
        }
    }

    public String toString() {
        return "[ParameterDescription " + this.parameterized.getName() + "]";
    }

    public boolean isDynamicParameter() {
        return this.dynamicParameterAnnotation != null;
    }

    public boolean isHelp() {
        return this.wrappedParameter.isHelp();
    }

    public boolean isNonOverwritableForced() {
        return this.wrappedParameter.isNonOverwritableForced();
    }
}
