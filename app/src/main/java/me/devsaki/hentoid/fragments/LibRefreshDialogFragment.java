package me.devsaki.hentoid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import me.devsaki.hentoid.R;
import me.devsaki.hentoid.activities.ImportActivity;
import me.devsaki.hentoid.util.BundleManager;

import static android.support.v4.view.ViewCompat.requireViewById;

/**
 * Created by Robb on 11/2018
 * Launcher dialog for the library refresh feature
 */
public class LibRefreshDialogFragment extends DialogFragment {

    private CheckBox renameChk;
    private CheckBox cleanAbsentChk;
    private CheckBox cleanNoImagesChk;
    private CheckBox cleanUnreadableChk;

    public static void invoke(FragmentManager fragmentManager) {
        LibRefreshDialogFragment fragment = new LibRefreshDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.PrefsThemeDialog);
        fragment.show(fragmentManager, null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        return inflater.inflate(R.layout.dialog_refresh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        renameChk = requireViewById(view, R.id.refresh_options_rename);
        cleanAbsentChk = requireViewById(view, R.id.refresh_options_remove_1);
        cleanNoImagesChk = requireViewById(view, R.id.refresh_options_remove_2);
        cleanUnreadableChk = requireViewById(view, R.id.refresh_options_remove_3);

        Button okBtn = requireViewById(view, R.id.refresh_ok);
        okBtn.setOnClickListener(v -> launchRefreshImport(renameChk.isChecked(), cleanAbsentChk.isChecked(), cleanNoImagesChk.isChecked(), cleanUnreadableChk.isChecked()));
    }

    private void launchRefreshImport(boolean rename, boolean cleanAbsent, boolean cleanNoImages, boolean cleanUnreadable) {
        Intent refresh = new Intent(requireContext(), ImportActivity.class);
        refresh.setAction("android.intent.action.APPLICATION_PREFERENCES"); // Is only a constant since API 24 -> using the string

        BundleManager manager = new BundleManager();

        manager.setRefresh(true);
        manager.setRefreshRename(rename);
        manager.setRefreshCleanAbsent(cleanAbsent);
        manager.setRefreshCleanNoImages(cleanNoImages);
        manager.setRefreshCleanUnreadable(cleanUnreadable);

        refresh.putExtras(manager.getBundle());

        startActivity(refresh);
        dismiss();
    }
}
