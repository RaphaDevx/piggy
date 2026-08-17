package ch.coop.supercardapp.core.data;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcard;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTypeEnum;
import ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin;
import ch.coop.capacitor.extensionconnector.widget.WidgetCardType;
import ch.coop.capacitor.extensionconnector.widget.WidgetInfo;
import ch.coop.capacitor.extensionconnector.widget.WidgetSize;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import ch.coop.supercardapp.AppContainer;
import ch.coop.supercardapp.SupercardWidget;
import ch.coop.supercardapp.SupercardWidgetBig;
import ch.coop.supercardapp.containers.cards.CardIdentifier;
import ch.coop.supercardapp.containers.cards.GiftcardState;
import ch.coop.supercardapp.widget.GiftcardWidget;
import ch.coop.supercardapp.widget.PaymentCardWidget;
import ch.coop.supercardapp.widget.SupercardPayWidget;
import ch.coop.supercardapp.widget.WidgetHelper$$ExternalSyntheticBackport0;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class DataUpdateManager implements ExtensionConnectorPlugin.DataUpdateManagerInterface {
    private static final int DATA_UPDATE_REPEAT_INTERVAL_MINUTES = 15;
    static final String WORK_NAME_DATA_UPDATE = "data-update";
    static final String WORK_NAME_UI_DATA_UPDATE = "ui-data-update";
    private final AppWidgetManager appWidgetManager;
    private final Context context;

    public DataUpdateManager(Context context, AppWidgetManager appWidgetManager) {
        this.context = context;
        this.appWidgetManager = appWidgetManager;
    }

    @Override // ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.DataUpdateManagerInterface
    public void startOrRestartPeriodicDataUpdate() {
        if (areWidgetsInstalled().booleanValue()) {
            WorkManager.getInstance(this.context).enqueueUniquePeriodicWork(WORK_NAME_DATA_UPDATE, ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) DataUpdateWorker.class, 15L, TimeUnit.MINUTES).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).build());
            WorkManager.getInstance(this.context).enqueueUniquePeriodicWork(WORK_NAME_UI_DATA_UPDATE, ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) UiDataUpdateWorker.class, 1L, TimeUnit.DAYS).setInitialDelay(Duration.between(LocalDateTime.now(), LocalDate.now().atTime(LocalTime.MIDNIGHT).plusDays(1L).plusMinutes(1L))).build());
        }
    }

    @Override // ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.DataUpdateManagerInterface
    public void updateAppLanguage(String str) {
        if (AppContainer.getInstance(this.context).getAppState().setAppLanguage(str)) {
            startOrRestartPeriodicDataUpdate();
        }
    }

    @Override // ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.DataUpdateManagerInterface
    public void updateSuperpoints(OffsetDateTime offsetDateTime, int i) {
        List<WidgetType> m;
        AppContainer.getInstance(this.context).getSupercardState().updateWithDataFromPhone(offsetDateTime, i);
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{WidgetType.SUPERCARD});
        triggerWidgetsUIUpdate(m);
    }

    @Override // ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.DataUpdateManagerInterface
    public void updateGiftcardsBalance(Map<String, Integer> map) {
        List<WidgetType> m;
        AppContainer.getInstance(this.context).getGiftcardState().updateWithDataFromPhone(map);
        m = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{WidgetType.GIFTCARD, WidgetType.PAYMENTCARD});
        triggerWidgetsUIUpdate(m);
    }

    public void widgetWasDisabled() {
        if (areWidgetsInstalled().booleanValue()) {
            return;
        }
        WorkManager.getInstance(this.context).cancelUniqueWork(WORK_NAME_DATA_UPDATE);
        WorkManager.getInstance(this.context).cancelUniqueWork(WORK_NAME_UI_DATA_UPDATE);
    }

    public List<ComponentName> getWidgetProviders() {
        return getWidgetProviders(null);
    }

    public List<ComponentName> getWidgetProviders(@Nullable final List<WidgetType> list) {
        List<AppWidgetProviderInfo> installedProviders = this.appWidgetManager.getInstalledProviders();
        final String packageName = this.context.getApplicationContext().getPackageName();
        return (List) installedProviders.stream().map(new Function() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ComponentName componentName;
                componentName = ((AppWidgetProviderInfo) obj).provider;
                return componentName;
            }
        }).filter(new Predicate() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((ComponentName) obj).getPackageName().equals(packageName);
                return equals;
            }
        }).filter(new Predicate() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$getWidgetProviders$2;
                lambda$getWidgetProviders$2 = DataUpdateManager.this.lambda$getWidgetProviders$2(list, (ComponentName) obj);
                return lambda$getWidgetProviders$2;
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getWidgetProviders$2(List list, ComponentName componentName) {
        return list == null || list.isEmpty() || list.contains(getWidgetTypeOfComponent(componentName));
    }

    public WidgetType getWidgetTypeOfComponent(ComponentName componentName) {
        String className = componentName.getClassName();
        WidgetType widgetType = WidgetType.UNKNOWN;
        if (className.equals(SupercardWidget.class.getName()) || className.equals(SupercardWidgetBig.class.getName())) {
            return WidgetType.SUPERCARD;
        }
        if (className.equals(GiftcardWidget.class.getName())) {
            return WidgetType.GIFTCARD;
        }
        if (className.equals(PaymentCardWidget.class.getName())) {
            return WidgetType.PAYMENTCARD;
        }
        return className.equals(SupercardPayWidget.class.getName()) ? WidgetType.SUPERCARDPAY : widgetType;
    }

    public List<WidgetInfo> getWidgetInfoFromComponent(ComponentName componentName) {
        WidgetType widgetTypeOfComponent = getWidgetTypeOfComponent(componentName);
        List<WidgetInfo> list = null;
        if (widgetTypeOfComponent == WidgetType.SUPERCARD) {
            String className = componentName.getClassName();
            if (className.equals(SupercardWidget.class.getName())) {
                list = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{new WidgetInfo(WidgetType.SUPERCARD, WidgetSize.SMALL, null)});
            } else if (className.equals(SupercardWidgetBig.class.getName())) {
                list = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{new WidgetInfo(WidgetType.SUPERCARD, WidgetSize.MEDIUM, null)});
            }
        } else if (widgetTypeOfComponent == WidgetType.GIFTCARD) {
            int[] appWidgetIds = getAppWidgetIds(componentName);
            AppContainer appContainer = AppContainer.getInstance(this.context);
            final DataUpdateState dataUpdateState = appContainer.getDataUpdateState();
            final GiftcardState giftcardState = appContainer.getGiftcardState();
            list = (List) Arrays.stream(appWidgetIds).mapToObj(new IntFunction() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda11
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return DataUpdateManager.lambda$getWidgetInfoFromComponent$3(DataUpdateState.this, giftcardState, i);
                }
            }).collect(Collectors.toList());
        } else if (widgetTypeOfComponent == WidgetType.PAYMENTCARD) {
            list = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{new WidgetInfo(WidgetType.PAYMENTCARD, WidgetSize.MEDIUM, null)});
        } else if (widgetTypeOfComponent == WidgetType.SUPERCARDPAY && componentName.getClassName().equals(SupercardPayWidget.class.getName())) {
            list = WidgetHelper$$ExternalSyntheticBackport0.m(new Object[]{new WidgetInfo(WidgetType.SUPERCARDPAY, WidgetSize.UNKNOWN, null)});
        }
        return list == null ? Collections.emptyList() : list;
    }

    static /* synthetic */ WidgetInfo lambda$getWidgetInfoFromComponent$3(DataUpdateState dataUpdateState, GiftcardState giftcardState, int i) {
        WidgetCardType widgetCardType;
        WidgetCardType widgetCardType2 = WidgetCardType.UNKNOWN;
        CardIdentifier cardIdentifierForWidgetId = dataUpdateState.getCardIdentifierForWidgetId(i);
        if (cardIdentifierForWidgetId != null) {
            CardIdentifier.CardType cardType = cardIdentifierForWidgetId.getCardType();
            if (cardType == CardIdentifier.CardType.GIFTCARD) {
                Optional<CardsGiftcard> giftcard = giftcardState.getGiftcard(cardIdentifierForWidgetId.getCardNr());
                if (giftcard.isPresent()) {
                    CardsGiftcardTypeEnum type = giftcard.get().getType();
                    if (type == CardsGiftcardTypeEnum.GIFTCARD) {
                        widgetCardType = WidgetCardType.GIFTCARD;
                    } else if (type == CardsGiftcardTypeEnum.EMPLOYEE_GIFTCARD) {
                        widgetCardType = WidgetCardType.EMPLOYEE_GIFTCARD;
                    }
                    widgetCardType2 = widgetCardType;
                }
            } else if (cardType == CardIdentifier.CardType.EMPLOYEE_DISCOUNT_CARD) {
                widgetCardType2 = WidgetCardType.EMPLOYEE_DISCOUNT_CARD;
            }
        }
        return new WidgetInfo(WidgetType.GIFTCARD, WidgetSize.MEDIUM, widgetCardType2);
    }

    public int[] getAppWidgetIds(ComponentName componentName) {
        return this.appWidgetManager.getAppWidgetIds(componentName);
    }

    public Boolean areWidgetsInstalled() {
        return Boolean.valueOf(getWidgetProviders().stream().anyMatch(new Predicate() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lambda$areWidgetsInstalled$4;
                lambda$areWidgetsInstalled$4 = DataUpdateManager.this.lambda$areWidgetsInstalled$4((ComponentName) obj);
                return lambda$areWidgetsInstalled$4;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$areWidgetsInstalled$4(ComponentName componentName) {
        return getAppWidgetIds(componentName).length > 0;
    }

    private Stream<Pair<ComponentName, int[]>> getComponentWithIds() {
        return getWidgetProviders().stream().map(new Function() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda12
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Pair lambda$getComponentWithIds$5;
                lambda$getComponentWithIds$5 = DataUpdateManager.this.lambda$getComponentWithIds$5((ComponentName) obj);
                return lambda$getComponentWithIds$5;
            }
        }).filter(new Predicate() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DataUpdateManager.lambda$getComponentWithIds$6((Pair) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Pair lambda$getComponentWithIds$5(ComponentName componentName) {
        return new Pair(componentName, getAppWidgetIds(componentName));
    }

    static /* synthetic */ boolean lambda$getComponentWithIds$6(Pair pair) {
        return ((int[]) pair.second).length > 0;
    }

    public Set<WidgetType> getInstalledWidgetTypes() {
        return (Set) getComponentWithIds().map(new Function() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                WidgetType lambda$getInstalledWidgetTypes$7;
                lambda$getInstalledWidgetTypes$7 = DataUpdateManager.this.lambda$getInstalledWidgetTypes$7((Pair) obj);
                return lambda$getInstalledWidgetTypes$7;
            }
        }).collect(Collectors.toSet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WidgetType lambda$getInstalledWidgetTypes$7(Pair pair) {
        return getWidgetTypeOfComponent((ComponentName) pair.first);
    }

    @Override // ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.DataUpdateManagerInterface
    public Set<WidgetInfo> getInstalledWidgetInfo() {
        return (Set) getComponentWithIds().map(new Function() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List lambda$getInstalledWidgetInfo$8;
                lambda$getInstalledWidgetInfo$8 = DataUpdateManager.this.lambda$getInstalledWidgetInfo$8((Pair) obj);
                return lambda$getInstalledWidgetInfo$8;
            }
        }).flatMap(new Function() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((List) obj).stream();
            }
        }).collect(Collectors.toSet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$getInstalledWidgetInfo$8(Pair pair) {
        return getWidgetInfoFromComponent((ComponentName) pair.first);
    }

    public void triggerWidgetsUIUpdate() {
        triggerWidgetsUIUpdate(null);
    }

    public void triggerWidgetsUIUpdate(@Nullable List<WidgetType> list) {
        getWidgetProviders(list).forEach(new Consumer() { // from class: ch.coop.supercardapp.core.data.DataUpdateManager$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DataUpdateManager.this.lambda$triggerWidgetsUIUpdate$9((ComponentName) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerWidgetsUIUpdate$9(ComponentName componentName) {
        int[] appWidgetIds = getAppWidgetIds(componentName);
        if (appWidgetIds.length > 0) {
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
            intent.putExtra("appWidgetIds", appWidgetIds);
            this.context.sendBroadcast(intent);
        }
    }
}
