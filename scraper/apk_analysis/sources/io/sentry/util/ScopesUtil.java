package io.sentry.util;

import io.sentry.HubAdapter;
import io.sentry.HubScopesWrapper;
import io.sentry.IScopes;
import io.sentry.Scopes;
import io.sentry.ScopesAdapter;
import io.sentry.Sentry;

/* loaded from: classes3.dex */
public final class ScopesUtil {
    public static void printScopesChain(IScopes iScopes) {
        System.out.println("==========================================");
        System.out.println("=============== v Scopes v ===============");
        System.out.println("==========================================");
        printScopesChainInternal(iScopes);
        System.out.println("==========================================");
        System.out.println("=============== ^ Scopes ^ ===============");
        System.out.println("==========================================");
    }

    private static void printScopesChainInternal(IScopes iScopes) {
        if (iScopes != null) {
            if (iScopes instanceof Scopes) {
                Scopes scopes = (Scopes) iScopes;
                System.out.println(String.format("%-25s {g=%-25s, i=%-25s, c=%-25s} [%s]", scopes, scopes.getGlobalScope(), scopes.getIsolationScope(), scopes.getScope(), scopes.getCreator()));
                printScopesChainInternal(iScopes.getParentScopes());
                return;
            }
            if ((iScopes instanceof ScopesAdapter) || (iScopes instanceof HubAdapter)) {
                printScopesChainInternal(Sentry.getCurrentScopes());
                return;
            }
            if (iScopes instanceof HubScopesWrapper) {
                printScopesChainInternal(((HubScopesWrapper) iScopes).getScopes());
                return;
            }
            System.out.println("Hit unhandled Scopes class" + iScopes.getClass());
            return;
        }
        System.out.println("-");
    }
}
