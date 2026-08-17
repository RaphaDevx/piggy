package com.scandit.datacapture.frameworks.barcode.generator;

import android.graphics.Bitmap;
import android.util.Base64;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.barcode.generator.BarcodeGenerator;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.barcode.generator.data.BarcodeGeneratorDataParser;
import com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.DataCaptureContextNotInitialized;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.method.FrameworksMethodCall;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import io.sentry.SentryEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeGeneratorModule.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\n\b\u0016\u0018\u0000 *2\u00020\u0001:\u0001*B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J&\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011J&\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011J5\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u001f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#J&\u0010$\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011J5\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u001f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'J&\u0010(\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010)\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/generator/BarcodeGeneratorModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "generators", "", "", "Lcom/scandit/datacapture/barcode/generator/BarcodeGenerator;", "createGenerator", "", "generatorJson", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "disposeGenerator", "generatorId", "execute", "", "method", "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "generate", "text", "imageWidth", "", "generateFromBase64EncodedData", "data", "generateFromBytesToBitmap", "Lkotlin/Result;", "Landroid/graphics/Bitmap;", "", "generateFromBytesToBitmap-0E7RQCE", "(Ljava/lang/String;[BI)Ljava/lang/Object;", "generateFromBytesToBytes", "generateToBitmap", "generateToBitmap-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/Object;", "generateToBytes", "onDestroy", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeGeneratorModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ERROR_INVALID_BASE_64 = "Invalid Base64.";
    private static final String ERROR_INVALID_GENERATOR_ID = "The generator id does not match the current generator id.";
    private static final String ERROR_WRONG_TYPE = "Unable to create a barcode generator from the given type: %s";
    private final FrameworksCaptureContext captureContext;
    private final Map<String, BarcodeGenerator> generators;
    private final FrameworksLog logger;

    public BarcodeGeneratorModule() {
        this(null, null, null, 7, null);
    }

    @JvmStatic
    public static final BarcodeGeneratorModule create() {
        return INSTANCE.create();
    }

    public /* synthetic */ BarcodeGeneratorModule(DefaultFrameworksCaptureContext defaultFrameworksCaptureContext, DefaultFrameworksLog defaultFrameworksLog, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : defaultFrameworksCaptureContext, (i & 2) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : defaultFrameworksLog, (i & 4) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeGeneratorModule(FrameworksCaptureContext captureContext, FrameworksLog logger, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.captureContext = captureContext;
        this.logger = logger;
        this.generators = new LinkedHashMap();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        super.onDestroy();
        this.generators.clear();
    }

    public final boolean execute(FrameworksMethodCall method, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(result, "result");
        String method2 = method.getMethod();
        if (method2 == null) {
            return false;
        }
        switch (method2.hashCode()) {
            case -1178176201:
                if (!method2.equals("createGenerator")) {
                    return false;
                }
                createGenerator((String) method.arguments(), result);
                return true;
            case -365492364:
                if (!method2.equals("disposeGenerator")) {
                    return false;
                }
                disposeGenerator((String) method.arguments(), result);
                return true;
            case -361743324:
                if (!method2.equals("generateFromBytesToBytes")) {
                    return false;
                }
                generateFromBytesToBytes((String) method.argument("generatorId"), (byte[]) method.argument("data"), ((Number) method.argument("imageWidth")).intValue(), result);
                return true;
            case -293602437:
                if (!method2.equals("generateToBytes")) {
                    return false;
                }
                generateToBytes((String) method.argument("generatorId"), (String) method.argument("text"), ((Number) method.argument("imageWidth")).intValue(), result);
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void createGenerator(String generatorJson, FrameworksResult result) {
        BarcodeGenerator.InterleavedTwoOfFiveBarcodeGeneratorBuilder interleavedTwoOfFiveBarcodeGeneratorBuilder;
        Intrinsics.checkNotNullParameter(generatorJson, "generatorJson");
        Intrinsics.checkNotNullParameter(result, "result");
        DataCaptureContext context = this.captureContext.getContext();
        if (context == null) {
            ExtentionsKt.reject(result, new DataCaptureContextNotInitialized());
            return;
        }
        BarcodeGeneratorDataParser fromJson = BarcodeGeneratorDataParser.INSTANCE.fromJson(generatorJson);
        String type = fromJson.getType();
        switch (type.hashCode()) {
            case -2077686777:
                if (type.equals("interleavedTwoOfFiveGenerator")) {
                    BarcodeGenerator.InterleavedTwoOfFiveBarcodeGeneratorBuilder interleavedTwoOfFiveBarcodeGeneratorBuilder2 = BarcodeGenerator.INSTANCE.interleavedTwoOfFiveBarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        interleavedTwoOfFiveBarcodeGeneratorBuilder2.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        interleavedTwoOfFiveBarcodeGeneratorBuilder2.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = interleavedTwoOfFiveBarcodeGeneratorBuilder2;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 204029129:
                if (type.equals("code128Generator")) {
                    BarcodeGenerator.Code128BarcodeGeneratorBuilder code128BarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.code128BarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        code128BarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        code128BarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = code128BarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 208407194:
                if (type.equals("upcaGenerator")) {
                    BarcodeGenerator.UpcaBarcodeGeneratorBuilder upcaBarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.upcaBarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        upcaBarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        upcaBarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = upcaBarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 274500360:
                if (type.equals("dataMatrixGenerator")) {
                    BarcodeGenerator.DataMatrixBarcodeGeneratorBuilder dataMatrixBarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.dataMatrixBarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        dataMatrixBarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        dataMatrixBarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = dataMatrixBarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 332089029:
                if (type.equals("qrCodeGenerator")) {
                    BarcodeGenerator.QrCodeBarcodeGeneratorBuilder qrCodeBarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.qrCodeBarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        qrCodeBarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        qrCodeBarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    if (fromJson.getErrorCorrectionLevel() != null) {
                        qrCodeBarcodeGeneratorBuilder.withErrorCorrectionLevel(fromJson.getErrorCorrectionLevel());
                    }
                    if (fromJson.getVersionNumber() != null) {
                        qrCodeBarcodeGeneratorBuilder.withVersionNumber(fromJson.getVersionNumber().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = qrCodeBarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 2042614528:
                if (type.equals("code39Generator")) {
                    BarcodeGenerator.Code39BarcodeGeneratorBuilder code39BarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.code39BarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        code39BarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        code39BarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = code39BarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 2066181530:
                if (type.equals("aztecGenerator")) {
                    BarcodeGenerator.AztecBarcodeGeneratorBuilder aztecBarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.aztecBarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        aztecBarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        aztecBarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    if (fromJson.getMinimumErrorCorrectionPercent() != null) {
                        aztecBarcodeGeneratorBuilder.withMinimumErrorCorrectionPercent(fromJson.getMinimumErrorCorrectionPercent());
                    }
                    if (fromJson.getLayers() != null) {
                        aztecBarcodeGeneratorBuilder.withLayers(fromJson.getLayers());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = aztecBarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            case 2104462399:
                if (type.equals("ean13Generator")) {
                    BarcodeGenerator.Ean13BarcodeGeneratorBuilder ean13BarcodeGeneratorBuilder = BarcodeGenerator.INSTANCE.ean13BarcodeGeneratorBuilder(context);
                    if (fromJson.getBackgroundColor() != null) {
                        ean13BarcodeGeneratorBuilder.withBackgroundColor(fromJson.getBackgroundColor().intValue());
                    }
                    if (fromJson.getForegroundColor() != null) {
                        ean13BarcodeGeneratorBuilder.withForegroundColor(fromJson.getForegroundColor().intValue());
                    }
                    interleavedTwoOfFiveBarcodeGeneratorBuilder = ean13BarcodeGeneratorBuilder;
                    break;
                }
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
            default:
                interleavedTwoOfFiveBarcodeGeneratorBuilder = null;
                break;
        }
        if (interleavedTwoOfFiveBarcodeGeneratorBuilder == null) {
            throw new IllegalArgumentException(ERROR_WRONG_TYPE);
        }
        this.generators.put(fromJson.getId(), interleavedTwoOfFiveBarcodeGeneratorBuilder.build());
        result.success(null);
    }

    public final void generate(String generatorId, String text, int imageWidth, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(generatorId, "generatorId");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3792generateToBitmap0E7RQCE = m3792generateToBitmap0E7RQCE(generatorId, text, imageWidth);
        if (Result.m3951isFailureimpl(m3792generateToBitmap0E7RQCE)) {
            Error m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3792generateToBitmap0E7RQCE);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Error("Unable to generate a barcode with the given data.");
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        if (Result.m3951isFailureimpl(m3792generateToBitmap0E7RQCE)) {
            m3792generateToBitmap0E7RQCE = null;
        }
        Bitmap bitmap = (Bitmap) m3792generateToBitmap0E7RQCE;
        result.success(bitmap != null ? ExtensionsKt.toBase64(bitmap) : null);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    public final void generateToBytes(String generatorId, String text, int imageWidth, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(generatorId, "generatorId");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3792generateToBitmap0E7RQCE = m3792generateToBitmap0E7RQCE(generatorId, text, imageWidth);
        if (Result.m3951isFailureimpl(m3792generateToBitmap0E7RQCE)) {
            Error m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3792generateToBitmap0E7RQCE);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Error("Unable to generate a barcode with the given data.");
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        if (Result.m3951isFailureimpl(m3792generateToBitmap0E7RQCE)) {
            m3792generateToBitmap0E7RQCE = null;
        }
        Bitmap bitmap = (Bitmap) m3792generateToBitmap0E7RQCE;
        result.success(bitmap != null ? ExtensionsKt.bytes(bitmap) : null);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /* renamed from: generateToBitmap-0E7RQCE, reason: not valid java name */
    private final Object m3792generateToBitmap0E7RQCE(String generatorId, String text, int imageWidth) {
        BarcodeGenerator barcodeGenerator = this.generators.get(generatorId);
        if (barcodeGenerator == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new IllegalArgumentException(ERROR_INVALID_GENERATOR_ID)));
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(barcodeGenerator.generate(text, imageWidth));
        } catch (RuntimeException e) {
            this.logger.error(e);
            Result.Companion companion3 = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(e));
        }
    }

    public final void generateFromBase64EncodedData(String generatorId, String data, int imageWidth, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(generatorId, "generatorId");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            byte[] decode = Base64.decode(data, 0);
            Intrinsics.checkNotNull(decode);
            Object m3791generateFromBytesToBitmap0E7RQCE = m3791generateFromBytesToBitmap0E7RQCE(generatorId, decode, imageWidth);
            if (Result.m3951isFailureimpl(m3791generateFromBytesToBitmap0E7RQCE)) {
                Throwable m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3791generateFromBytesToBitmap0E7RQCE);
                if (m3948exceptionOrNullimpl != null) {
                    ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
                    return;
                } else {
                    ExtentionsKt.reject(result, new Error("Unable to generate a barcode with the given data."));
                    return;
                }
            }
            if (Result.m3951isFailureimpl(m3791generateFromBytesToBitmap0E7RQCE)) {
                m3791generateFromBytesToBitmap0E7RQCE = null;
            }
            Bitmap bitmap = (Bitmap) m3791generateFromBytesToBitmap0E7RQCE;
            result.success(bitmap != null ? ExtensionsKt.toBase64(bitmap) : null);
            if (bitmap != null) {
                bitmap.recycle();
            }
        } catch (IllegalArgumentException e) {
            this.logger.error(e);
            result.error(ExifInterface.GPS_MEASUREMENT_3D, ERROR_INVALID_BASE_64, null);
        }
    }

    public final void generateFromBytesToBytes(String generatorId, byte[] data, int imageWidth, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(generatorId, "generatorId");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3791generateFromBytesToBitmap0E7RQCE = m3791generateFromBytesToBitmap0E7RQCE(generatorId, data, imageWidth);
        if (Result.m3951isFailureimpl(m3791generateFromBytesToBitmap0E7RQCE)) {
            Error m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3791generateFromBytesToBitmap0E7RQCE);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Error();
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        if (Result.m3951isFailureimpl(m3791generateFromBytesToBitmap0E7RQCE)) {
            m3791generateFromBytesToBitmap0E7RQCE = null;
        }
        Bitmap bitmap = (Bitmap) m3791generateFromBytesToBitmap0E7RQCE;
        result.success(bitmap != null ? ExtensionsKt.bytes(bitmap) : null);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /* renamed from: generateFromBytesToBitmap-0E7RQCE, reason: not valid java name */
    private final Object m3791generateFromBytesToBitmap0E7RQCE(String generatorId, byte[] data, int imageWidth) {
        BarcodeGenerator barcodeGenerator = this.generators.get(generatorId);
        if (barcodeGenerator == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new IllegalArgumentException(ERROR_INVALID_GENERATOR_ID)));
        }
        try {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(barcodeGenerator.generate(data, imageWidth));
        } catch (RuntimeException e) {
            this.logger.error(e);
            Result.Companion companion3 = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(e));
        }
    }

    public final void disposeGenerator(String generatorId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(generatorId, "generatorId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.generators.remove(generatorId);
        result.success(null);
    }

    /* compiled from: BarcodeGeneratorModule.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/generator/BarcodeGeneratorModule$Companion;", "", "()V", "ERROR_INVALID_BASE_64", "", "ERROR_INVALID_GENERATOR_ID", "ERROR_WRONG_TYPE", "create", "Lcom/scandit/datacapture/frameworks/barcode/generator/BarcodeGeneratorModule;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeGeneratorModule create() {
            return new BarcodeGeneratorModule(null, null, null, 7, null);
        }
    }
}
