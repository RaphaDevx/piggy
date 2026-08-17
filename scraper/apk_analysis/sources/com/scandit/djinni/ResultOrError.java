package com.scandit.djinni;

import java.util.Objects;

/* loaded from: classes2.dex */
public abstract class ResultOrError<Result, Error> {

    public interface ErrorHandler<R, Error> {
        R apply(Error error);
    }

    public interface ResultHandler<R, Result> {
        R apply(Result result);
    }

    public static <Result, Error> ResultOrError<Result, Error> fromError(final Error error) {
        return new ResultOrError<Result, Error>() { // from class: com.scandit.djinni.ResultOrError.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.scandit.djinni.ResultOrError
            public <R> R match(ResultHandler<R, Result> resultHandler, ErrorHandler<R, Error> errorHandler) {
                return (R) errorHandler.apply(error);
            }
        };
    }

    public static <Result, Error> ResultOrError<Result, Error> fromResult(final Result result) {
        return new ResultOrError<Result, Error>() { // from class: com.scandit.djinni.ResultOrError.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.scandit.djinni.ResultOrError
            public <R> R match(ResultHandler<R, Result> resultHandler, ErrorHandler<R, Error> errorHandler) {
                return (R) resultHandler.apply(result);
            }
        };
    }

    static /* synthetic */ Object lambda$errorOrNull$6(Object obj) {
        return null;
    }

    static /* synthetic */ Object lambda$errorOrNull$7(Object obj) {
        return obj;
    }

    static /* synthetic */ Object lambda$resultOr$4(Object obj) {
        return obj;
    }

    static /* synthetic */ Object lambda$resultOr$5(Object obj, Object obj2) {
        return obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ResultOrError)) {
            return false;
        }
        final ResultOrError resultOrError = (ResultOrError) obj;
        return ((Boolean) match(new ResultHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda2
            @Override // com.scandit.djinni.ResultOrError.ResultHandler
            public final Object apply(Object obj2) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(obj2.equals(ResultOrError.this.resultOr(null)));
                return valueOf;
            }
        }, new ErrorHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda3
            @Override // com.scandit.djinni.ResultOrError.ErrorHandler
            public final Object apply(Object obj2) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(obj2.equals(ResultOrError.this.errorOrNull()));
                return valueOf;
            }
        })).booleanValue();
    }

    public Error errorOrNull() {
        return (Error) match(new ResultHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda0
            @Override // com.scandit.djinni.ResultOrError.ResultHandler
            public final Object apply(Object obj) {
                return ResultOrError.lambda$errorOrNull$6(obj);
            }
        }, new ErrorHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda1
            @Override // com.scandit.djinni.ResultOrError.ErrorHandler
            public final Object apply(Object obj) {
                return ResultOrError.lambda$errorOrNull$7(obj);
            }
        });
    }

    public int hashCode() {
        final Class<?> cls = getClass();
        return ((Integer) match(new ResultHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda6
            @Override // com.scandit.djinni.ResultOrError.ResultHandler
            public final Object apply(Object obj) {
                Integer valueOf;
                valueOf = Integer.valueOf(Objects.hash(cls, 1, obj));
                return valueOf;
            }
        }, new ErrorHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda7
            @Override // com.scandit.djinni.ResultOrError.ErrorHandler
            public final Object apply(Object obj) {
                Integer valueOf;
                valueOf = Integer.valueOf(Objects.hash(cls, 0, obj));
                return valueOf;
            }
        })).intValue();
    }

    public abstract <R> R match(ResultHandler<R, Result> resultHandler, ErrorHandler<R, Error> errorHandler);

    public Result resultOr(final Result result) {
        return (Result) match(new ResultHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda4
            @Override // com.scandit.djinni.ResultOrError.ResultHandler
            public final Object apply(Object obj) {
                return ResultOrError.lambda$resultOr$4(obj);
            }
        }, new ErrorHandler() { // from class: com.scandit.djinni.ResultOrError$$ExternalSyntheticLambda5
            @Override // com.scandit.djinni.ResultOrError.ErrorHandler
            public final Object apply(Object obj) {
                return ResultOrError.lambda$resultOr$5(result, obj);
            }
        });
    }

    private ResultOrError() {
    }
}
