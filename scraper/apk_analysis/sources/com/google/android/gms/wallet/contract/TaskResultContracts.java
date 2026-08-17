package com.google.android.gms.wallet.contract;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class TaskResultContracts {

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public static final class GetPaymentData extends UnpackApiTaskResult<PaymentData> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        public PaymentData parseResult(int i, Intent intent) {
            if (intent != null) {
                return PaymentData.getFromIntent(intent);
            }
            return null;
        }
    }

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public static abstract class ResolveApiTaskResult<I, O> extends ActivityResultContract<Task<I>, O> {
        Status zza;
        private PendingIntent zzb;

        @Override // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context, Task<I> task) {
            return new Intent(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST).putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST, new IntentSenderRequest.Builder(this.zzb).build());
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public ActivityResultContract.SynchronousResult<O> getSynchronousResult(Context context, Task<I> task) {
            if (!task.isComplete()) {
                throw new IllegalArgumentException("The task has to be executed before using this API to resolve its result.");
            }
            Exception exception = task.getException();
            if (exception instanceof ApiException) {
                this.zza = ((ApiException) exception).getStatus();
                if (exception instanceof ResolvableApiException) {
                    this.zzb = ((ResolvableApiException) exception).getResolution();
                }
            }
            if (this.zzb == null) {
                return new ActivityResultContract.SynchronousResult<>(outputFromTask(task));
            }
            return null;
        }

        protected abstract O outputFromTask(Task<I> task);
    }

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public static abstract class UnpackApiTaskResult<T> extends ResolveApiTaskResult<T, T> {
        @Override // com.google.android.gms.wallet.contract.TaskResultContracts.ResolveApiTaskResult
        protected T outputFromTask(Task<T> task) {
            if (task.isSuccessful()) {
                return task.getResult();
            }
            return null;
        }
    }

    private TaskResultContracts() {
    }

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public static final class GetPaymentDataResult extends GetApiTaskResult<PaymentData> {
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.android.gms.wallet.contract.TaskResultContracts.GetApiTaskResult
        public PaymentData taskResultFromIntent(Intent intent) {
            return PaymentData.getFromIntent(intent);
        }

        @Override // com.google.android.gms.wallet.contract.TaskResultContracts.GetApiTaskResult, androidx.activity.result.contract.ActivityResultContract
        public ApiTaskResult<PaymentData> parseResult(int i, Intent intent) {
            if (i != 1) {
                return super.parseResult(i, intent);
            }
            Status statusFromIntent = AutoResolveHelper.getStatusFromIntent(intent);
            if (statusFromIntent == null) {
                statusFromIntent = Status.RESULT_INTERNAL_ERROR;
            }
            return new ApiTaskResult<>(statusFromIntent);
        }

        @Override // com.google.android.gms.wallet.contract.TaskResultContracts.GetApiTaskResult, androidx.activity.result.contract.ActivityResultContract
        public final /* bridge */ /* synthetic */ Object parseResult(int i, Intent intent) {
            return parseResult(i, intent);
        }
    }

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public static abstract class GetApiTaskResult<T> extends ResolveApiTaskResult<T, ApiTaskResult<T>> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.wallet.contract.TaskResultContracts.ResolveApiTaskResult
        public ApiTaskResult<T> outputFromTask(Task<T> task) {
            if (task.isSuccessful()) {
                return new ApiTaskResult<>(task.getResult(), Status.RESULT_SUCCESS);
            }
            if (task.isCanceled()) {
                return new ApiTaskResult<>(new Status(16, "The task has been canceled."));
            }
            Status status = this.zza;
            return status != null ? new ApiTaskResult<>(status) : new ApiTaskResult<>(Status.RESULT_INTERNAL_ERROR);
        }

        protected abstract T taskResultFromIntent(Intent intent);

        @Override // androidx.activity.result.contract.ActivityResultContract
        public ApiTaskResult<T> parseResult(int i, Intent intent) {
            if (i != -1) {
                return i != 0 ? new ApiTaskResult<>(null, Status.RESULT_INTERNAL_ERROR) : new ApiTaskResult<>(null, Status.RESULT_CANCELED);
            }
            T taskResultFromIntent = intent != null ? taskResultFromIntent(intent) : null;
            return taskResultFromIntent != null ? new ApiTaskResult<>(taskResultFromIntent, Status.RESULT_SUCCESS) : new ApiTaskResult<>(null, Status.RESULT_INTERNAL_ERROR);
        }
    }
}
