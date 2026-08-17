package io.sentry.internal.modules;

import io.sentry.ILogger;
import io.sentry.ISentryLifecycleToken;
import io.sentry.SentryLevel;
import io.sentry.util.AutoClosableReentrantLock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public abstract class ModulesLoader implements IModulesLoader {
    public static final String EXTERNAL_MODULES_FILENAME = "sentry-external-modules.txt";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    protected final ILogger logger;
    private final AutoClosableReentrantLock modulesLock = new AutoClosableReentrantLock();
    private volatile Map<String, String> cachedModules = null;

    protected abstract Map<String, String> loadModules();

    public ModulesLoader(ILogger iLogger) {
        this.logger = iLogger;
    }

    @Override // io.sentry.internal.modules.IModulesLoader
    public Map<String, String> getOrLoadModules() {
        if (this.cachedModules == null) {
            ISentryLifecycleToken acquire = this.modulesLock.acquire();
            try {
                if (this.cachedModules == null) {
                    this.cachedModules = loadModules();
                }
                if (acquire != null) {
                    acquire.close();
                }
            } catch (Throwable th) {
                if (acquire != null) {
                    try {
                        acquire.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        return this.cachedModules;
    }

    protected Map<String, String> parseStream(InputStream inputStream) {
        TreeMap treeMap = new TreeMap();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    int lastIndexOf = readLine.lastIndexOf(58);
                    treeMap.put(readLine.substring(0, lastIndexOf), readLine.substring(lastIndexOf + 1));
                }
                this.logger.log(SentryLevel.DEBUG, "Extracted %d modules from resources.", Integer.valueOf(treeMap.size()));
                bufferedReader.close();
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            this.logger.log(SentryLevel.ERROR, "Error extracting modules.", e);
        } catch (RuntimeException e2) {
            this.logger.log(SentryLevel.ERROR, e2, "%s file is malformed.", EXTERNAL_MODULES_FILENAME);
        }
        return treeMap;
    }
}
