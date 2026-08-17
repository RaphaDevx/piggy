package com.scandit.datacapture.frameworks.core.frames;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.LruCache;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.graphic.ImageBuffer;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.data.FrameDataSerializerKt;
import com.scandit.datacapture.core.internal.sdk.common.graphic.ImageBufferFormat;
import com.scandit.datacapture.core.internal.sdk.common.graphic.ImageBufferUtilsKt;
import com.scandit.datacapture.frameworks.core.frames.configuration.FramesHandlingConfiguration;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.DefaultWorkerThread;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.WorkerThread;
import io.sentry.SentryEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: DefaultFrameDataHandler.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\t\u0018\u0000 :2\u00020\u0001:\u0001:B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001c\u001a\u00020\tH\u0002J\u0018\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0018H\u0016J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\tH\u0016J&\u0010#\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\t2\u0014\u0010$\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00180%H\u0016J4\u0010&\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\t2\"\u0010$\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020(\u0018\u00010'\u0012\u0006\u0012\u0004\u0018\u00010\u00180%H\u0016J0\u0010)\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\t2\u0006\u0010*\u001a\u00020\n2\u0016\u0010$\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\u00180%H\u0002J\u001c\u0010+\u001a\u00020\t*\u00020,2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J(\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020(0'*\u00020,2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J \u0010-\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020(0'*\u00020\n2\u0006\u0010\"\u001a\u00020\tH\u0002J\u0014\u0010.\u001a\u00020/*\u00020,2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u00100\u001a\u00020/*\u00020,2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u00101\u001a\u000202*\u00020,2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u00103\u001a\u000202*\u00020,2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u00104\u001a\u00020\t*\u00020,2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0014\u00105\u001a\u000202*\u00020,2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u00106\u001a\u00020\t*\u00020,2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001e\u00107\u001a\u00020\t*\u00020/2\u0006\u0010\"\u001a\u00020\t2\b\b\u0002\u00108\u001a\u00020\u000eH\u0002J \u00109\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020(0'*\u00020,2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0018\u00109\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020(0'*\u00020\nH\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/frames/DefaultFrameDataHandler;", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "workerThread", "Lcom/scandit/datacapture/frameworks/core/utils/WorkerThread;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "(Lcom/scandit/datacapture/frameworks/core/utils/WorkerThread;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;)V", "cache", "Landroid/util/LruCache;", "", "Lcom/scandit/datacapture/core/data/FrameData;", "configuration", "Lcom/scandit/datacapture/frameworks/core/frames/configuration/FramesHandlingConfiguration;", "isFileSystemCacheEnabled", "", "()Z", "workingDir", "Ljava/io/File;", "addToCache", "frameData", "bitmapToFile", "bitmap", "Landroid/graphics/Bitmap;", "configure", "", "context", "Landroid/content/Context;", "getFromCache", "id", "getRotatedBitmap", "orientation", "", "release", "removeFromCache", "frameId", "retrieveDataAsJsonString", "result", "Lkotlin/Function1;", "retrieveDataAsMap", "", "", "saveFrameToDisk", TypedValues.AttributesType.S_FRAME, "asFileData", "Lcom/scandit/datacapture/core/common/graphic/ImageBuffer;", "asFileToMap", "byteArrayFromArgbImage", "Ljava/io/ByteArrayOutputStream;", "byteArrayFromYuvImage", "data", "", "decodeArgbImage", "decodeArgbImageToFile", "decodeYuvImage", "decodeYuvImageToFile", "toFile", "isRetryAttempt", "toMap", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultFrameDataHandler implements FrameDataHandler {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultFrameDataHandler instance = new DefaultFrameDataHandler(DefaultWorkerThread.INSTANCE.getInstance(), DefaultFrameworksLog.INSTANCE.getInstance());
    private final LruCache<String, FrameData> cache;
    private FramesHandlingConfiguration configuration;
    private final FrameworksLog logger;
    private final WorkerThread workerThread;
    private File workingDir;

    /* compiled from: DefaultFrameDataHandler.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImageBufferFormat.values().length];
            try {
                iArr[ImageBufferFormat.ARGB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ImageBufferFormat.YUV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final DefaultFrameDataHandler getInstance() {
        return INSTANCE.getInstance();
    }

    public DefaultFrameDataHandler(WorkerThread workerThread, FrameworksLog logger) {
        Intrinsics.checkNotNullParameter(workerThread, "workerThread");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.workerThread = workerThread;
        this.logger = logger;
        this.cache = new LruCache<>(2);
        this.configuration = FramesHandlingConfiguration.INSTANCE.getDefaultConfig();
        this.workingDir = new File("");
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public boolean isFileSystemCacheEnabled() {
        return this.configuration.getIsFileSystemCacheEnabled();
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public void configure(final Context context, final FramesHandlingConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.workerThread.runOnBackgroundThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler$configure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                File file;
                File file2;
                File file3;
                DefaultFrameDataHandler.this.workingDir = new File(context.getCacheDir(), "sc_frames");
                file = DefaultFrameDataHandler.this.workingDir;
                if (file.exists()) {
                    file3 = DefaultFrameDataHandler.this.workingDir;
                    FilesKt.deleteRecursively(file3);
                }
                file2 = DefaultFrameDataHandler.this.workingDir;
                file2.mkdir();
                DefaultFrameDataHandler.this.configuration = configuration;
            }
        });
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public String addToCache(FrameData frameData) {
        String valueOf;
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        synchronized (this.cache) {
            valueOf = String.valueOf(System.nanoTime());
            this.cache.put(valueOf, frameData);
        }
        return valueOf;
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public void removeFromCache(String frameId) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        synchronized (this.cache) {
            this.cache.remove(frameId);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public void retrieveDataAsJsonString(final String frameId, final Function1<? super String, Unit> result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        final FrameData fromCache = getFromCache(frameId);
        if (fromCache == null) {
            result.invoke(null);
        } else {
            this.workerThread.runOnBackgroundThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler$retrieveDataAsJsonString$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    FramesHandlingConfiguration framesHandlingConfiguration;
                    framesHandlingConfiguration = DefaultFrameDataHandler.this.configuration;
                    if (framesHandlingConfiguration.getIsFileSystemCacheEnabled()) {
                        DefaultFrameDataHandler.this.saveFrameToDisk(frameId, fromCache, result);
                    } else {
                        result.invoke(FrameDataSerializerKt.toJson(fromCache));
                    }
                }
            });
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public void retrieveDataAsMap(final String frameId, final Function1<? super Map<String, ? extends Object>, Unit> result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        final FrameData fromCache = getFromCache(frameId);
        if (fromCache == null) {
            result.invoke(null);
        } else {
            this.workerThread.runOnBackgroundThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler$retrieveDataAsMap$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    FramesHandlingConfiguration framesHandlingConfiguration;
                    Map<String, ? extends Object> map;
                    Map<String, ? extends Object> asFileToMap;
                    framesHandlingConfiguration = DefaultFrameDataHandler.this.configuration;
                    if (framesHandlingConfiguration.getIsFileSystemCacheEnabled()) {
                        Function1<Map<String, ? extends Object>, Unit> function1 = result;
                        asFileToMap = DefaultFrameDataHandler.this.asFileToMap(fromCache, frameId);
                        function1.invoke(asFileToMap);
                    } else {
                        Function1<Map<String, ? extends Object>, Unit> function12 = result;
                        map = DefaultFrameDataHandler.this.toMap(fromCache);
                        function12.invoke(map);
                    }
                }
            });
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public void release() {
        synchronized (this.cache) {
            this.cache.evictAll();
            Unit unit = Unit.INSTANCE;
        }
        this.workerThread.runOnBackgroundThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler$release$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                File file;
                File file2;
                file = DefaultFrameDataHandler.this.workingDir;
                if (file.exists()) {
                    try {
                        file2 = DefaultFrameDataHandler.this.workingDir;
                        FilesKt.deleteRecursively(file2);
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    @Override // com.scandit.datacapture.frameworks.core.frames.FrameDataHandler
    public String bitmapToFile(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            bitmap.compress(Bitmap.CompressFormat.JPEG, this.configuration.getImageQuality(), byteArrayOutputStream2);
            String file$default = toFile$default(this, byteArrayOutputStream2, String.valueOf(System.currentTimeMillis()), false, 2, null);
            CloseableKt.closeFinally(byteArrayOutputStream, null);
            return file$default;
        } finally {
        }
    }

    private final FrameData getFromCache(String id) {
        FrameData remove;
        synchronized (this.cache) {
            remove = this.cache.remove(id);
        }
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveFrameToDisk(String frameId, FrameData frame, Function1<? super String, Unit> result) {
        result.invoke(new JSONObject(asFileToMap(frame, frameId)).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, Object> toMap(FrameData frameData) {
        return MapsKt.mapOf(TuplesKt.to("imageBuffers", CollectionsKt.mutableListOf(toMap(frameData.getImageBuffer(), frameData.getOrientation()))), TuplesKt.to("orientation", Integer.valueOf(frameData.getOrientation())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, Object> asFileToMap(FrameData frameData, String str) {
        return MapsKt.mapOf(TuplesKt.to("imageBuffers", CollectionsKt.mutableListOf(asFileToMap(frameData.getImageBuffer(), str, frameData.getOrientation()))), TuplesKt.to("orientation", Integer.valueOf(frameData.getOrientation())));
    }

    private final Map<String, Object> toMap(ImageBuffer imageBuffer, int i) {
        return MapsKt.mapOf(TuplesKt.to("height", Integer.valueOf(imageBuffer.getHeight())), TuplesKt.to("width", Integer.valueOf(imageBuffer.getWidth())), TuplesKt.to("data", data(imageBuffer, i)));
    }

    private final Map<String, Object> asFileToMap(ImageBuffer imageBuffer, String str, int i) {
        return MapsKt.mapOf(TuplesKt.to("height", Integer.valueOf(imageBuffer.getHeight())), TuplesKt.to("width", Integer.valueOf(imageBuffer.getWidth())), TuplesKt.to("data", asFileData(imageBuffer, str, i)));
    }

    private final byte[] data(ImageBuffer imageBuffer, int i) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[ImageBufferUtilsKt.getFormat(imageBuffer.getA()).ordinal()];
        if (i2 == 1) {
            return decodeArgbImage(imageBuffer, i);
        }
        if (i2 == 2) {
            return decodeYuvImage(imageBuffer, i);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String asFileData(ImageBuffer imageBuffer, String str, int i) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[ImageBufferUtilsKt.getFormat(imageBuffer.getA()).ordinal()];
        if (i2 == 1) {
            return decodeArgbImageToFile(imageBuffer, str, i);
        }
        if (i2 == 2) {
            return decodeYuvImageToFile(imageBuffer, str, i);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final byte[] decodeArgbImage(ImageBuffer imageBuffer, int i) {
        byte[] byteArray = byteArrayFromArgbImage(imageBuffer, i).toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    private final String decodeArgbImageToFile(ImageBuffer imageBuffer, String str, int i) {
        return toFile$default(this, byteArrayFromArgbImage(imageBuffer, i), str, false, 2, null);
    }

    private final byte[] decodeYuvImage(ImageBuffer imageBuffer, int i) {
        byte[] byteArray = byteArrayFromYuvImage(imageBuffer, i).toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    private final String decodeYuvImageToFile(ImageBuffer imageBuffer, String str, int i) {
        return toFile$default(this, byteArrayFromYuvImage(imageBuffer, i), str, false, 2, null);
    }

    private final ByteArrayOutputStream byteArrayFromArgbImage(ImageBuffer imageBuffer, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(imageBuffer.getWidth(), imageBuffer.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(imageBuffer.getA().toBitmap()));
        Bitmap rotatedBitmap = getRotatedBitmap(i, createBitmap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, this.configuration.getImageQuality(), byteArrayOutputStream);
            return byteArrayOutputStream;
        } finally {
            if (!Intrinsics.areEqual(rotatedBitmap, createBitmap) && !rotatedBitmap.isRecycled()) {
                rotatedBitmap.recycle();
            }
            if (!createBitmap.isRecycled()) {
                createBitmap.recycle();
            }
        }
    }

    private final ByteArrayOutputStream byteArrayFromYuvImage(ImageBuffer imageBuffer, int i) {
        YuvImage yuvImage = new YuvImage(imageBuffer.getA().toBitmap(), 17, imageBuffer.getWidth(), imageBuffer.getHeight(), null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, imageBuffer.getWidth(), imageBuffer.getHeight()), this.configuration.getImageQuality(), byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        if (decodeByteArray == null) {
            return new ByteArrayOutputStream();
        }
        Bitmap rotatedBitmap = getRotatedBitmap(i, decodeByteArray);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, this.configuration.getImageQuality(), byteArrayOutputStream2);
            return byteArrayOutputStream2;
        } finally {
            if (!Intrinsics.areEqual(rotatedBitmap, decodeByteArray) && !rotatedBitmap.isRecycled()) {
                rotatedBitmap.recycle();
            }
            if (!decodeByteArray.isRecycled()) {
                decodeByteArray.recycle();
            }
        }
    }

    private final Bitmap getRotatedBitmap(int orientation, Bitmap bitmap) {
        if (this.configuration.getAutoRotateImages() && orientation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);
            try {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            } catch (OutOfMemoryError e) {
                this.logger.error("Error while rotating the frame: " + e);
            }
        }
        Intrinsics.checkNotNull(bitmap);
        return bitmap;
    }

    static /* synthetic */ String toFile$default(DefaultFrameDataHandler defaultFrameDataHandler, ByteArrayOutputStream byteArrayOutputStream, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return defaultFrameDataHandler.toFile(byteArrayOutputStream, str, z);
    }

    private final String toFile(ByteArrayOutputStream byteArrayOutputStream, String str, boolean z) {
        File file = new File(this.workingDir, "frame_" + str + ".jpeg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                FileOutputStream fileOutputStream2 = fileOutputStream;
                byteArrayOutputStream.writeTo(fileOutputStream2);
                fileOutputStream2.flush();
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
                String absolutePath = file.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                return absolutePath;
            } finally {
            }
        } catch (Exception e) {
            if (!z) {
                if (!this.workingDir.exists()) {
                    this.workingDir.mkdirs();
                }
                return toFile(byteArrayOutputStream, str, true);
            }
            this.logger.error("Error while writing the frame to the FileSystem: " + e);
            return "";
        }
    }

    /* compiled from: DefaultFrameDataHandler.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/frames/DefaultFrameDataHandler$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/frames/DefaultFrameDataHandler;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DefaultFrameDataHandler getInstance() {
            return DefaultFrameDataHandler.instance;
        }
    }
}
