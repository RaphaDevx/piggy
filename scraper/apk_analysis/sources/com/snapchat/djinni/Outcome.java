package com.snapchat.djinni;

import java.util.Objects;

/* loaded from: classes2.dex */
public abstract class Outcome<Result, Error> {

    public interface ErrorHandler<R, Error> {
        R apply(Error error);
    }

    public interface ResultHandler<R, Result> {
        R apply(Result result);
    }

    public static <Result, Error> Outcome<Result, Error> fromError(final Error error) {
        return new Outcome<Result, Error>() { // from class: com.snapchat.djinni.Outcome.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.snapchat.djinni.Outcome
            public <R> R match(ResultHandler<R, Result> resultHandler, ErrorHandler<R, Error> errorHandler) {
                return (R) errorHandler.apply(error);
            }
        };
    }

    public static <Result, Error> Outcome<Result, Error> fromResult(final Result result) {
        return new Outcome<Result, Error>() { // from class: com.snapchat.djinni.Outcome.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.snapchat.djinni.Outcome
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
        if (obj == null || !(obj instanceof Outcome)) {
            return false;
        }
        final Outcome outcome = (Outcome) obj;
        return ((Boolean) match(new ResultHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda4
            @Override // com.snapchat.djinni.Outcome.ResultHandler
            public final Object apply(Object obj2) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(obj2.equals(Outcome.this.resultOr(null)));
                return valueOf;
            }
        }, new ErrorHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda5
            @Override // com.snapchat.djinni.Outcome.ErrorHandler
            public final Object apply(Object obj2) {
                Boolean valueOf;
                valueOf = Boolean.valueOf(obj2.equals(Outcome.this.errorOrNull()));
                return valueOf;
            }
        })).booleanValue();
    }

    public Error errorOrNull() {
        return (Error) match(new ResultHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda0
            @Override // com.snapchat.djinni.Outcome.ResultHandler
            public final Object apply(Object obj) {
                return Outcome.lambda$errorOrNull$6(obj);
            }
        }, new ErrorHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda1
            @Override // com.snapchat.djinni.Outcome.ErrorHandler
            public final Object apply(Object obj) {
                return Outcome.lambda$errorOrNull$7(obj);
            }
        });
    }

    public int hashCode() {
        final Class<?> cls = getClass();
        return ((Integer) match(new ResultHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda2
            @Override // com.snapchat.djinni.Outcome.ResultHandler
            public final Object apply(Object obj) {
                Integer valueOf;
                valueOf = Integer.valueOf(Objects.hash(cls, 1, obj));
                return valueOf;
            }
        }, new ErrorHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda3
            @Override // com.snapchat.djinni.Outcome.ErrorHandler
            public final Object apply(Object obj) {
                Integer valueOf;
                valueOf = Integer.valueOf(Objects.hash(cls, 0, obj));
                return valueOf;
            }
        })).intValue();
    }

    public abstract <R> R match(ResultHandler<R, Result> resultHandler, ErrorHandler<R, Error> errorHandler);

    public Result resultOr(final Result result) {
        return (Result) match(new ResultHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda6
            @Override // com.snapchat.djinni.Outcome.ResultHandler
            public final Object apply(Object obj) {
                return Outcome.lambda$resultOr$4(obj);
            }
        }, new ErrorHandler() { // from class: com.snapchat.djinni.Outcome$$ExternalSyntheticLambda7
            @Override // com.snapchat.djinni.Outcome.ErrorHandler
            public final Object apply(Object obj) {
                return Outcome.lambda$resultOr$5(result, obj);
            }
        });
    }

    private Outcome() {
    }
}
