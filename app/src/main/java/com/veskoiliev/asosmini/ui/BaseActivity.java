package com.veskoiliev.asosmini.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.veskoiliev.asosmini.R;
import com.veskoiliev.asosmini.model.pojo.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Since all Activities share the same ActionBar menu, we can just build it here.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Display an {@link AlertDialog} with the favorites in the format:
     * "id -> title".
     *
     * @param favorites to be displayed.
     */
    protected void displayFavorites(HashMap<Long, Product> favorites) {
        StringBuilder message = new StringBuilder();
        message.append(getString(R.string.favorites_list)).append("\n");
        for (Map.Entry<Long, Product> entry : favorites.entrySet()) {
            message.append(entry.getKey()).append(" -> ").append(entry.getValue().getTitle()).append("\n");
        }

        displayAlertDialog(message.toString());
    }

    /**
     * Display and {@link AlertDialog} with the shopping bag items in the format:
     * "id -> count".
     *
     * @param bag of shopping items to be displayed.
     */
    protected void displayBag(HashMap<Long, Integer> bag) {
        StringBuilder message = new StringBuilder();
        message.append(getString(R.string.bag_contents)).append("\n");
        for (Map.Entry<Long, Integer> entry : bag.entrySet()) {
            message.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }

        displayAlertDialog(message.toString());
    }

    private void displayAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
