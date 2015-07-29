package io.sihrc.blue_wifi.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.sihrc.blue_wifi.R;
import io.sihrc.blue_wifi.db.WifiDatabase;
import io.sihrc.blue_wifi.models.Wifi;

/**
 * Created by Chris on 7/29/15.
 */
public class WifiAdapter extends ArrayAdapter<Wifi> {
    Context context;
    WifiDatabase database;
    List<Wifi> wifis;

    public WifiAdapter(Context context) {
        super(context, R.layout.item_wifi);
        this.context = context;

        // Grab data to display
        database = WifiDatabase.getInstance(context);
        wifis = database.getAllWifis();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        WifiViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_wifi, parent);
            holder = new WifiViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (WifiViewHolder) convertView.getTag();
        }

        fillView(holder, getItem(position));

        return convertView;
    }

    private void fillView(WifiViewHolder holder, Wifi wifi) {
        holder.name.setText(wifi.name);
        holder.desc.setText(wifi.desc);
    }

    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
        WifiDropdownHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_wifi_dropdown, parent);
            holder = new WifiDropdownHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (WifiDropdownHolder) convertView.getTag();
        }

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                // TODO
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                new AlertDialog.Builder(context)
                    .setMessage(R.string.remove_warning_message)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                            WifiDatabase.getInstance(context).deleteWifi(wifis.get(i).id);
                            wifis.remove(i);
                        }
                    }).setCancelable(true)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
            }
        });

        return convertView;
    }


    @Override public int getCount() {
        return wifis.size();
    }

    @Override public Wifi getItem(int position) {
        return wifis.get(position);
    }

    class WifiViewHolder {
        @Bind(R.id.item_wifi_name) TextView name;
        @Bind(R.id.item_wifi_desc) TextView desc;

        WifiViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class WifiDropdownHolder {
        @Bind(R.id.wifi_dropdown_share) TextView share;
        @Bind(R.id.wifi_dropdown_remove) TextView remove;

        WifiDropdownHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
