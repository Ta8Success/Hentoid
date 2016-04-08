package me.devsaki.hentoid.updater;

import android.content.Context;

/**
 * Created by avluis on 8/21/15.
 * Interface for built-in updater.
 */
interface IUpdateCheck {
    void checkForUpdate(final Context context,
                        final boolean onlyWifi, final boolean showToast,
                        final UpdateCheck.UpdateCheckCallback updateCheckResult);
}