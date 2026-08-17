package org.chromium.support_lib_boundary.util;

import android.os.Build;
import android.util.LruCache;
import android.util.Pair;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes4.dex */
public class BoundaryInterfaceReflectionUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Consumer<Boolean> sCacheGetResultLogger;
    private static LruCache<Pair<Method, ClassLoader>, Method> sMethodCache;

    public static void setMethodCache(LruCache<Pair<Method, ClassLoader>, Method> lruCache, Consumer<Boolean> consumer) {
        sMethodCache = lruCache;
        sCacheGetResultLogger = consumer;
    }

    public static boolean instanceOfInOwnClassLoader(Object obj, String str) {
        try {
            return Class.forName(str, false, obj.getClass().getClassLoader()).isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static Method dupeMethod(Method method, ClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException {
        LruCache<Pair<Method, ClassLoader>, Method> lruCache = sMethodCache;
        if (lruCache != null) {
            Method method2 = lruCache.get(new Pair<>(method, classLoader));
            Consumer<Boolean> consumer = sCacheGetResultLogger;
            if (consumer != null) {
                consumer.accept(Boolean.valueOf(method2 != null));
            }
            if (method2 != null) {
                return method2;
            }
        }
        Method declaredMethod = Class.forName(method.getDeclaringClass().getName(), true, classLoader).getDeclaredMethod(method.getName(), method.getParameterTypes());
        LruCache<Pair<Method, ClassLoader>, Method> lruCache2 = sMethodCache;
        if (lruCache2 != null) {
            lruCache2.put(new Pair<>(method, classLoader), declaredMethod);
        }
        return declaredMethod;
    }

    public static <T> T castToSuppLibClass(Class<T> cls, InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return cls.cast(Proxy.newProxyInstance(BoundaryInterfaceReflectionUtil.class.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    public static InvocationHandler createInvocationHandlerFor(Object obj) {
        if (obj == null) {
            return null;
        }
        return new InvocationHandlerWithDelegateGetter(obj);
    }

    public static InvocationHandler[] createInvocationHandlersForArray(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        int length = objArr.length;
        InvocationHandler[] invocationHandlerArr = new InvocationHandler[length];
        for (int i = 0; i < length; i++) {
            invocationHandlerArr[i] = createInvocationHandlerFor(objArr[i]);
        }
        return invocationHandlerArr;
    }

    public static Object getDelegateFromInvocationHandler(InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return ((InvocationHandlerWithDelegateGetter) invocationHandler).getDelegate();
    }

    private static class InvocationHandlerWithDelegateGetter implements InvocationHandler {
        private final Object mDelegate;

        public InvocationHandlerWithDelegateGetter(Object obj) {
            this.mDelegate = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                return BoundaryInterfaceReflectionUtil.dupeMethod(method, this.mDelegate.getClass().getClassLoader()).invoke(this.mDelegate, objArr);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            } catch (ReflectiveOperationException e2) {
                throw new RuntimeException("Reflection failed for method " + method, e2);
            }
        }

        public Object getDelegate() {
            return this.mDelegate;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof InvocationHandlerWithDelegateGetter) {
                return this.mDelegate.equals(((InvocationHandlerWithDelegateGetter) obj).mDelegate);
            }
            return this.mDelegate.equals(obj);
        }

        public int hashCode() {
            return this.mDelegate.hashCode();
        }
    }

    private static boolean isDebuggable() {
        return "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }

    public static boolean containsFeature(Collection<String> collection, String str) {
        if (!collection.contains(str)) {
            if (isDebuggable()) {
                if (collection.contains(str + Features.DEV_SUFFIX)) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean containsFeature(String[] strArr, String str) {
        return containsFeature(Arrays.asList(strArr), str);
    }
}
