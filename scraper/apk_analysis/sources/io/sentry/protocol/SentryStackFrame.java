package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLockReason;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes2.dex */
public final class SentryStackFrame implements JsonUnknown, JsonSerializable {
    private Boolean _native;
    private String _package;
    private String absPath;
    private String addrMode;
    private Integer colno;
    private String contextLine;
    private String filename;
    private List<Integer> framesOmitted;
    private String function;
    private String imageAddr;
    private Boolean inApp;
    private String instructionAddr;
    private Integer lineno;
    private SentryLockReason lock;
    private String module;
    private String platform;
    private List<String> postContext;
    private List<String> preContext;
    private String rawFunction;
    private String symbol;
    private String symbolAddr;
    private Map<String, Object> unknown;
    private Map<String, Object> vars;

    public static final class JsonKeys {
        public static final String ABS_PATH = "abs_path";
        public static final String ADDR_MODE = "addr_mode";
        public static final String COLNO = "colno";
        public static final String CONTEXT_LINE = "context_line";
        public static final String FILENAME = "filename";
        public static final String FUNCTION = "function";
        public static final String IMAGE_ADDR = "image_addr";
        public static final String INSTRUCTION_ADDR = "instruction_addr";
        public static final String IN_APP = "in_app";
        public static final String LINENO = "lineno";
        public static final String LOCK = "lock";
        public static final String MODULE = "module";
        public static final String NATIVE = "native";
        public static final String PACKAGE = "package";
        public static final String PLATFORM = "platform";
        public static final String POST_CONTEXT = "post_context";
        public static final String PRE_CONTEXT = "pre_context";
        public static final String RAW_FUNCTION = "raw_function";
        public static final String SYMBOL = "symbol";
        public static final String SYMBOL_ADDR = "symbol_addr";
        public static final String VARS = "vars";
    }

    public List<String> getPreContext() {
        return this.preContext;
    }

    public void setPreContext(List<String> list) {
        this.preContext = list;
    }

    public List<String> getPostContext() {
        return this.postContext;
    }

    public void setPostContext(List<String> list) {
        this.postContext = list;
    }

    public Map<String, Object> getVars() {
        return this.vars;
    }

    public void setVars(Map<String, Object> map) {
        this.vars = map;
    }

    public List<Integer> getFramesOmitted() {
        return this.framesOmitted;
    }

    public void setFramesOmitted(List<Integer> list) {
        this.framesOmitted = list;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String str) {
        this.function = str;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String str) {
        this.module = str;
    }

    public Integer getLineno() {
        return this.lineno;
    }

    public void setLineno(Integer num) {
        this.lineno = num;
    }

    public Integer getColno() {
        return this.colno;
    }

    public void setColno(Integer num) {
        this.colno = num;
    }

    public String getAbsPath() {
        return this.absPath;
    }

    public void setAbsPath(String str) {
        this.absPath = str;
    }

    public String getContextLine() {
        return this.contextLine;
    }

    public void setContextLine(String str) {
        this.contextLine = str;
    }

    public Boolean isInApp() {
        return this.inApp;
    }

    public void setInApp(Boolean bool) {
        this.inApp = bool;
    }

    public String getPackage() {
        return this._package;
    }

    public void setPackage(String str) {
        this._package = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getImageAddr() {
        return this.imageAddr;
    }

    public void setImageAddr(String str) {
        this.imageAddr = str;
    }

    public String getSymbolAddr() {
        return this.symbolAddr;
    }

    public void setSymbolAddr(String str) {
        this.symbolAddr = str;
    }

    public String getInstructionAddr() {
        return this.instructionAddr;
    }

    public void setInstructionAddr(String str) {
        this.instructionAddr = str;
    }

    public String getAddrMode() {
        return this.addrMode;
    }

    public void setAddrMode(String str) {
        this.addrMode = str;
    }

    public Boolean isNative() {
        return this._native;
    }

    public void setNative(Boolean bool) {
        this._native = bool;
    }

    public String getRawFunction() {
        return this.rawFunction;
    }

    public void setRawFunction(String str) {
        this.rawFunction = str;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public SentryLockReason getLock() {
        return this.lock;
    }

    public void setLock(SentryLockReason sentryLockReason) {
        this.lock = sentryLockReason;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SentryStackFrame sentryStackFrame = (SentryStackFrame) obj;
        return Objects.equals(this.preContext, sentryStackFrame.preContext) && Objects.equals(this.postContext, sentryStackFrame.postContext) && Objects.equals(this.vars, sentryStackFrame.vars) && Objects.equals(this.framesOmitted, sentryStackFrame.framesOmitted) && Objects.equals(this.filename, sentryStackFrame.filename) && Objects.equals(this.function, sentryStackFrame.function) && Objects.equals(this.module, sentryStackFrame.module) && Objects.equals(this.lineno, sentryStackFrame.lineno) && Objects.equals(this.colno, sentryStackFrame.colno) && Objects.equals(this.absPath, sentryStackFrame.absPath) && Objects.equals(this.contextLine, sentryStackFrame.contextLine) && Objects.equals(this.inApp, sentryStackFrame.inApp) && Objects.equals(this._package, sentryStackFrame._package) && Objects.equals(this._native, sentryStackFrame._native) && Objects.equals(this.platform, sentryStackFrame.platform) && Objects.equals(this.imageAddr, sentryStackFrame.imageAddr) && Objects.equals(this.symbolAddr, sentryStackFrame.symbolAddr) && Objects.equals(this.instructionAddr, sentryStackFrame.instructionAddr) && Objects.equals(this.addrMode, sentryStackFrame.addrMode) && Objects.equals(this.symbol, sentryStackFrame.symbol) && Objects.equals(this.unknown, sentryStackFrame.unknown) && Objects.equals(this.rawFunction, sentryStackFrame.rawFunction) && Objects.equals(this.lock, sentryStackFrame.lock);
    }

    public int hashCode() {
        return Objects.hash(this.preContext, this.postContext, this.vars, this.framesOmitted, this.filename, this.function, this.module, this.lineno, this.colno, this.absPath, this.contextLine, this.inApp, this._package, this._native, this.platform, this.imageAddr, this.symbolAddr, this.instructionAddr, this.addrMode, this.symbol, this.unknown, this.rawFunction, this.lock);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.filename != null) {
            objectWriter.name("filename").value(this.filename);
        }
        if (this.function != null) {
            objectWriter.name(JsonKeys.FUNCTION).value(this.function);
        }
        if (this.module != null) {
            objectWriter.name("module").value(this.module);
        }
        if (this.lineno != null) {
            objectWriter.name(JsonKeys.LINENO).value(this.lineno);
        }
        if (this.colno != null) {
            objectWriter.name(JsonKeys.COLNO).value(this.colno);
        }
        if (this.absPath != null) {
            objectWriter.name(JsonKeys.ABS_PATH).value(this.absPath);
        }
        if (this.contextLine != null) {
            objectWriter.name(JsonKeys.CONTEXT_LINE).value(this.contextLine);
        }
        if (this.inApp != null) {
            objectWriter.name(JsonKeys.IN_APP).value(this.inApp);
        }
        if (this._package != null) {
            objectWriter.name(JsonKeys.PACKAGE).value(this._package);
        }
        if (this._native != null) {
            objectWriter.name("native").value(this._native);
        }
        if (this.platform != null) {
            objectWriter.name("platform").value(this.platform);
        }
        if (this.imageAddr != null) {
            objectWriter.name("image_addr").value(this.imageAddr);
        }
        if (this.symbolAddr != null) {
            objectWriter.name(JsonKeys.SYMBOL_ADDR).value(this.symbolAddr);
        }
        if (this.instructionAddr != null) {
            objectWriter.name(JsonKeys.INSTRUCTION_ADDR).value(this.instructionAddr);
        }
        if (this.addrMode != null) {
            objectWriter.name(JsonKeys.ADDR_MODE).value(this.addrMode);
        }
        if (this.rawFunction != null) {
            objectWriter.name(JsonKeys.RAW_FUNCTION).value(this.rawFunction);
        }
        if (this.symbol != null) {
            objectWriter.name(JsonKeys.SYMBOL).value(this.symbol);
        }
        if (this.lock != null) {
            objectWriter.name(JsonKeys.LOCK).value(iLogger, this.lock);
        }
        List<String> list = this.preContext;
        if (list != null && !list.isEmpty()) {
            objectWriter.name(JsonKeys.PRE_CONTEXT).value(iLogger, this.preContext);
        }
        List<String> list2 = this.postContext;
        if (list2 != null && !list2.isEmpty()) {
            objectWriter.name(JsonKeys.POST_CONTEXT).value(iLogger, this.postContext);
        }
        Map<String, Object> map = this.vars;
        if (map != null && !map.isEmpty()) {
            objectWriter.name(JsonKeys.VARS).value(iLogger, this.vars);
        }
        Map<String, Object> map2 = this.unknown;
        if (map2 != null) {
            for (String str : map2.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<SentryStackFrame> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // io.sentry.JsonDeserializer
        public SentryStackFrame deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            SentryStackFrame sentryStackFrame = new SentryStackFrame();
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -1641491184:
                        if (nextName.equals(JsonKeys.POST_CONTEXT)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1443345323:
                        if (nextName.equals("image_addr")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1184392185:
                        if (nextName.equals(JsonKeys.IN_APP)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1113875953:
                        if (nextName.equals(JsonKeys.RAW_FUNCTION)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1102671691:
                        if (nextName.equals(JsonKeys.LINENO)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1068784020:
                        if (nextName.equals("module")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -1052618729:
                        if (nextName.equals("native")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -887523944:
                        if (nextName.equals(JsonKeys.SYMBOL)) {
                            c = 7;
                            break;
                        }
                        break;
                    case -807062458:
                        if (nextName.equals(JsonKeys.PACKAGE)) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -734768633:
                        if (nextName.equals("filename")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case -330260936:
                        if (nextName.equals(JsonKeys.SYMBOL_ADDR)) {
                            c = '\n';
                            break;
                        }
                        break;
                    case 3327275:
                        if (nextName.equals(JsonKeys.LOCK)) {
                            c = 11;
                            break;
                        }
                        break;
                    case 3612204:
                        if (nextName.equals(JsonKeys.VARS)) {
                            c = '\f';
                            break;
                        }
                        break;
                    case 94842689:
                        if (nextName.equals(JsonKeys.COLNO)) {
                            c = CharUtils.CR;
                            break;
                        }
                        break;
                    case 410194178:
                        if (nextName.equals(JsonKeys.INSTRUCTION_ADDR)) {
                            c = 14;
                            break;
                        }
                        break;
                    case 822688787:
                        if (nextName.equals(JsonKeys.PRE_CONTEXT)) {
                            c = 15;
                            break;
                        }
                        break;
                    case 868820273:
                        if (nextName.equals(JsonKeys.ADDR_MODE)) {
                            c = 16;
                            break;
                        }
                        break;
                    case 1116694660:
                        if (nextName.equals(JsonKeys.CONTEXT_LINE)) {
                            c = 17;
                            break;
                        }
                        break;
                    case 1380938712:
                        if (nextName.equals(JsonKeys.FUNCTION)) {
                            c = 18;
                            break;
                        }
                        break;
                    case 1713445842:
                        if (nextName.equals(JsonKeys.ABS_PATH)) {
                            c = 19;
                            break;
                        }
                        break;
                    case 1874684019:
                        if (nextName.equals("platform")) {
                            c = 20;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        sentryStackFrame.postContext = (List) objectReader.nextObjectOrNull();
                        break;
                    case 1:
                        sentryStackFrame.imageAddr = objectReader.nextStringOrNull();
                        break;
                    case 2:
                        sentryStackFrame.inApp = objectReader.nextBooleanOrNull();
                        break;
                    case 3:
                        sentryStackFrame.rawFunction = objectReader.nextStringOrNull();
                        break;
                    case 4:
                        sentryStackFrame.lineno = objectReader.nextIntegerOrNull();
                        break;
                    case 5:
                        sentryStackFrame.module = objectReader.nextStringOrNull();
                        break;
                    case 6:
                        sentryStackFrame._native = objectReader.nextBooleanOrNull();
                        break;
                    case 7:
                        sentryStackFrame.symbol = objectReader.nextStringOrNull();
                        break;
                    case '\b':
                        sentryStackFrame._package = objectReader.nextStringOrNull();
                        break;
                    case '\t':
                        sentryStackFrame.filename = objectReader.nextStringOrNull();
                        break;
                    case '\n':
                        sentryStackFrame.symbolAddr = objectReader.nextStringOrNull();
                        break;
                    case 11:
                        sentryStackFrame.lock = (SentryLockReason) objectReader.nextOrNull(iLogger, new SentryLockReason.Deserializer());
                        break;
                    case '\f':
                        sentryStackFrame.vars = (Map) objectReader.nextObjectOrNull();
                        break;
                    case '\r':
                        sentryStackFrame.colno = objectReader.nextIntegerOrNull();
                        break;
                    case 14:
                        sentryStackFrame.instructionAddr = objectReader.nextStringOrNull();
                        break;
                    case 15:
                        sentryStackFrame.preContext = (List) objectReader.nextObjectOrNull();
                        break;
                    case 16:
                        sentryStackFrame.addrMode = objectReader.nextStringOrNull();
                        break;
                    case 17:
                        sentryStackFrame.contextLine = objectReader.nextStringOrNull();
                        break;
                    case 18:
                        sentryStackFrame.function = objectReader.nextStringOrNull();
                        break;
                    case 19:
                        sentryStackFrame.absPath = objectReader.nextStringOrNull();
                        break;
                    case 20:
                        sentryStackFrame.platform = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            sentryStackFrame.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return sentryStackFrame;
        }
    }
}
