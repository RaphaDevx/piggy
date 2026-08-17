package javax.ws.rs.client;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
final class FactoryFinder {
    private static final Logger LOGGER = Logger.getLogger(FactoryFinder.class.getName());

    private FactoryFinder() {
    }

    static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.ws.rs.client.FactoryFinder$$ExternalSyntheticLambda0
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return FactoryFinder.lambda$getContextClassLoader$0();
            }
        });
    }

    static /* synthetic */ ClassLoader lambda$getContextClassLoader$0() {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (SecurityException e) {
            LOGGER.log(Level.WARNING, "Unable to get context classloader instance.", (Throwable) e);
            return null;
        }
    }

    private static Object newInstance(String str, ClassLoader classLoader) throws ClassNotFoundException {
        Class<?> cls;
        try {
            try {
                if (classLoader == null) {
                    cls = Class.forName(str);
                } else {
                    try {
                        cls = Class.forName(str, false, classLoader);
                    } catch (ClassNotFoundException e) {
                        LOGGER.log(Level.FINE, "Unable to load provider class " + str + " using custom classloader " + classLoader.getClass().getName() + " trying again with current classloader.", (Throwable) e);
                        cls = Class.forName(str);
                    }
                }
                return cls.getDeclaredConstructor(null).newInstance(null);
            } catch (ClassNotFoundException e2) {
                throw e2;
            }
        } catch (Exception e3) {
            throw new ClassNotFoundException("Provider " + str + " could not be instantiated: " + e3, e3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0107 A[Catch: SecurityException -> 0x010c, TRY_LEAVE, TryCatch #8 {SecurityException -> 0x010c, blocks: (B:42:0x0101, B:44:0x0107), top: B:41:0x0101 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.lang.Object find(java.lang.String r10, java.lang.String r11, java.lang.Class<T> r12) throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.ws.rs.client.FactoryFinder.find(java.lang.String, java.lang.String, java.lang.Class):java.lang.Object");
    }
}
