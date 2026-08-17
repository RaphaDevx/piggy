package io.sentry;

import io.sentry.protocol.Feedback;
import io.sentry.protocol.SentryId;

/* loaded from: classes2.dex */
public final class SentryFeedbackOptions {
    private CharSequence cancelButtonLabel;
    private CharSequence emailLabel;
    private CharSequence emailPlaceholder;
    private CharSequence formTitle;
    private IDialogHandler iDialogHandler;
    private boolean isEmailRequired;
    private boolean isNameRequired;
    private CharSequence isRequiredLabel;
    private CharSequence messageLabel;
    private CharSequence messagePlaceholder;
    private CharSequence nameLabel;
    private CharSequence namePlaceholder;
    private Runnable onFormClose;
    private Runnable onFormOpen;
    private SentryFeedbackCallback onSubmitError;
    private SentryFeedbackCallback onSubmitSuccess;
    private boolean showBranding;
    private boolean showEmail;
    private boolean showName;
    private CharSequence submitButtonLabel;
    private CharSequence successMessageText;
    private boolean useSentryUser;

    public interface IDialogHandler {
        void showDialog(SentryId sentryId, OptionsConfigurator optionsConfigurator);
    }

    public interface OptionsConfigurator {
        void configure(SentryFeedbackOptions sentryFeedbackOptions);
    }

    public interface SentryFeedbackCallback {
        void call(Feedback feedback);
    }

    public SentryFeedbackOptions(IDialogHandler iDialogHandler) {
        this.isNameRequired = false;
        this.showName = true;
        this.isEmailRequired = false;
        this.showEmail = true;
        this.useSentryUser = true;
        this.showBranding = true;
        this.formTitle = "Report a Bug";
        this.submitButtonLabel = "Send Bug Report";
        this.cancelButtonLabel = "Cancel";
        this.nameLabel = "Name";
        this.namePlaceholder = "Your Name";
        this.emailLabel = "Email";
        this.emailPlaceholder = "your.email@example.org";
        this.isRequiredLabel = " (Required)";
        this.messageLabel = "Description";
        this.messagePlaceholder = "What's the bug? What did you expect?";
        this.successMessageText = "Thank you for your report!";
        this.iDialogHandler = iDialogHandler;
    }

    public SentryFeedbackOptions(SentryFeedbackOptions sentryFeedbackOptions) {
        this.isNameRequired = false;
        this.showName = true;
        this.isEmailRequired = false;
        this.showEmail = true;
        this.useSentryUser = true;
        this.showBranding = true;
        this.formTitle = "Report a Bug";
        this.submitButtonLabel = "Send Bug Report";
        this.cancelButtonLabel = "Cancel";
        this.nameLabel = "Name";
        this.namePlaceholder = "Your Name";
        this.emailLabel = "Email";
        this.emailPlaceholder = "your.email@example.org";
        this.isRequiredLabel = " (Required)";
        this.messageLabel = "Description";
        this.messagePlaceholder = "What's the bug? What did you expect?";
        this.successMessageText = "Thank you for your report!";
        this.isNameRequired = sentryFeedbackOptions.isNameRequired;
        this.showName = sentryFeedbackOptions.showName;
        this.isEmailRequired = sentryFeedbackOptions.isEmailRequired;
        this.showEmail = sentryFeedbackOptions.showEmail;
        this.useSentryUser = sentryFeedbackOptions.useSentryUser;
        this.showBranding = sentryFeedbackOptions.showBranding;
        this.formTitle = sentryFeedbackOptions.formTitle;
        this.submitButtonLabel = sentryFeedbackOptions.submitButtonLabel;
        this.cancelButtonLabel = sentryFeedbackOptions.cancelButtonLabel;
        this.nameLabel = sentryFeedbackOptions.nameLabel;
        this.namePlaceholder = sentryFeedbackOptions.namePlaceholder;
        this.emailLabel = sentryFeedbackOptions.emailLabel;
        this.emailPlaceholder = sentryFeedbackOptions.emailPlaceholder;
        this.isRequiredLabel = sentryFeedbackOptions.isRequiredLabel;
        this.messageLabel = sentryFeedbackOptions.messageLabel;
        this.messagePlaceholder = sentryFeedbackOptions.messagePlaceholder;
        this.successMessageText = sentryFeedbackOptions.successMessageText;
        this.onFormOpen = sentryFeedbackOptions.onFormOpen;
        this.onFormClose = sentryFeedbackOptions.onFormClose;
        this.onSubmitSuccess = sentryFeedbackOptions.onSubmitSuccess;
        this.onSubmitError = sentryFeedbackOptions.onSubmitError;
        this.iDialogHandler = sentryFeedbackOptions.iDialogHandler;
    }

    public boolean isNameRequired() {
        return this.isNameRequired;
    }

    public void setNameRequired(boolean z) {
        this.isNameRequired = z;
    }

    public boolean isShowName() {
        return this.showName;
    }

    public void setShowName(boolean z) {
        this.showName = z;
    }

    public boolean isEmailRequired() {
        return this.isEmailRequired;
    }

    public void setEmailRequired(boolean z) {
        this.isEmailRequired = z;
    }

    public boolean isShowEmail() {
        return this.showEmail;
    }

    public void setShowEmail(boolean z) {
        this.showEmail = z;
    }

    public boolean isUseSentryUser() {
        return this.useSentryUser;
    }

    public void setUseSentryUser(boolean z) {
        this.useSentryUser = z;
    }

    public boolean isShowBranding() {
        return this.showBranding;
    }

    public void setShowBranding(boolean z) {
        this.showBranding = z;
    }

    public CharSequence getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(CharSequence charSequence) {
        this.formTitle = charSequence;
    }

    public CharSequence getSubmitButtonLabel() {
        return this.submitButtonLabel;
    }

    public void setSubmitButtonLabel(CharSequence charSequence) {
        this.submitButtonLabel = charSequence;
    }

    public CharSequence getCancelButtonLabel() {
        return this.cancelButtonLabel;
    }

    public void setCancelButtonLabel(CharSequence charSequence) {
        this.cancelButtonLabel = charSequence;
    }

    public CharSequence getNameLabel() {
        return this.nameLabel;
    }

    public void setNameLabel(CharSequence charSequence) {
        this.nameLabel = charSequence;
    }

    public CharSequence getNamePlaceholder() {
        return this.namePlaceholder;
    }

    public void setNamePlaceholder(CharSequence charSequence) {
        this.namePlaceholder = charSequence;
    }

    public CharSequence getEmailLabel() {
        return this.emailLabel;
    }

    public void setEmailLabel(CharSequence charSequence) {
        this.emailLabel = charSequence;
    }

    public CharSequence getEmailPlaceholder() {
        return this.emailPlaceholder;
    }

    public void setEmailPlaceholder(CharSequence charSequence) {
        this.emailPlaceholder = charSequence;
    }

    public CharSequence getIsRequiredLabel() {
        return this.isRequiredLabel;
    }

    public void setIsRequiredLabel(CharSequence charSequence) {
        this.isRequiredLabel = charSequence;
    }

    public CharSequence getMessageLabel() {
        return this.messageLabel;
    }

    public void setMessageLabel(CharSequence charSequence) {
        this.messageLabel = charSequence;
    }

    public CharSequence getMessagePlaceholder() {
        return this.messagePlaceholder;
    }

    public void setMessagePlaceholder(CharSequence charSequence) {
        this.messagePlaceholder = charSequence;
    }

    public CharSequence getSuccessMessageText() {
        return this.successMessageText;
    }

    public void setSuccessMessageText(CharSequence charSequence) {
        this.successMessageText = charSequence;
    }

    public Runnable getOnFormOpen() {
        return this.onFormOpen;
    }

    public void setOnFormOpen(Runnable runnable) {
        this.onFormOpen = runnable;
    }

    public Runnable getOnFormClose() {
        return this.onFormClose;
    }

    public void setOnFormClose(Runnable runnable) {
        this.onFormClose = runnable;
    }

    public SentryFeedbackCallback getOnSubmitSuccess() {
        return this.onSubmitSuccess;
    }

    public void setOnSubmitSuccess(SentryFeedbackCallback sentryFeedbackCallback) {
        this.onSubmitSuccess = sentryFeedbackCallback;
    }

    public SentryFeedbackCallback getOnSubmitError() {
        return this.onSubmitError;
    }

    public void setOnSubmitError(SentryFeedbackCallback sentryFeedbackCallback) {
        this.onSubmitError = sentryFeedbackCallback;
    }

    public void setDialogHandler(IDialogHandler iDialogHandler) {
        this.iDialogHandler = iDialogHandler;
    }

    public IDialogHandler getDialogHandler() {
        return this.iDialogHandler;
    }

    public String toString() {
        return "SentryFeedbackOptions{isNameRequired=" + this.isNameRequired + ", showName=" + this.showName + ", isEmailRequired=" + this.isEmailRequired + ", showEmail=" + this.showEmail + ", useSentryUser=" + this.useSentryUser + ", showBranding=" + this.showBranding + ", formTitle='" + ((Object) this.formTitle) + "', submitButtonLabel='" + ((Object) this.submitButtonLabel) + "', cancelButtonLabel='" + ((Object) this.cancelButtonLabel) + "', nameLabel='" + ((Object) this.nameLabel) + "', namePlaceholder='" + ((Object) this.namePlaceholder) + "', emailLabel='" + ((Object) this.emailLabel) + "', emailPlaceholder='" + ((Object) this.emailPlaceholder) + "', isRequiredLabel='" + ((Object) this.isRequiredLabel) + "', messageLabel='" + ((Object) this.messageLabel) + "', messagePlaceholder='" + ((Object) this.messagePlaceholder) + "'}";
    }
}
