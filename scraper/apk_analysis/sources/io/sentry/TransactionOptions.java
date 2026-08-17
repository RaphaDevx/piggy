package io.sentry;

/* loaded from: classes2.dex */
public final class TransactionOptions extends SpanOptions {
    public static final long DEFAULT_DEADLINE_TIMEOUT_AUTO_TRANSACTION = 30000;
    private CustomSamplingContext customSamplingContext = null;
    private boolean isAppStartTransaction = false;
    private boolean waitForChildren = false;
    private Long idleTimeout = null;
    private Long deadlineTimeout = null;
    private TransactionFinishedCallback transactionFinishedCallback = null;
    private ISpanFactory spanFactory = null;

    public CustomSamplingContext getCustomSamplingContext() {
        return this.customSamplingContext;
    }

    public void setCustomSamplingContext(CustomSamplingContext customSamplingContext) {
        this.customSamplingContext = customSamplingContext;
    }

    public boolean isBindToScope() {
        return ScopeBindingMode.ON == getScopeBindingMode();
    }

    public void setBindToScope(boolean z) {
        setScopeBindingMode(z ? ScopeBindingMode.ON : ScopeBindingMode.OFF);
    }

    public boolean isWaitForChildren() {
        return this.waitForChildren;
    }

    public void setWaitForChildren(boolean z) {
        this.waitForChildren = z;
    }

    public Long getIdleTimeout() {
        return this.idleTimeout;
    }

    public void setDeadlineTimeout(Long l) {
        this.deadlineTimeout = l;
    }

    public Long getDeadlineTimeout() {
        return this.deadlineTimeout;
    }

    public void setIdleTimeout(Long l) {
        this.idleTimeout = l;
    }

    public TransactionFinishedCallback getTransactionFinishedCallback() {
        return this.transactionFinishedCallback;
    }

    public void setTransactionFinishedCallback(TransactionFinishedCallback transactionFinishedCallback) {
        this.transactionFinishedCallback = transactionFinishedCallback;
    }

    public void setAppStartTransaction(boolean z) {
        this.isAppStartTransaction = z;
    }

    public boolean isAppStartTransaction() {
        return this.isAppStartTransaction;
    }

    public ISpanFactory getSpanFactory() {
        return this.spanFactory;
    }

    public void setSpanFactory(ISpanFactory iSpanFactory) {
        this.spanFactory = iSpanFactory;
    }
}
