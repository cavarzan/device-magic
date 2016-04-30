package io.github.cavarzan.devicemagic.ui.main;


import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.cavarzan.devicemagic.R;
import io.github.cavarzan.devicemagic.model.Download;
import io.github.cavarzan.devicemagic.ui.base.BaseAdapter;

/**
 * Created by deividi on 27/04/16.
 */
public class DownloadAdapter extends BaseAdapter<Download> {
    @Override
    protected int getLayoutForType(int viewType) {
        return R.layout.item_download;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View inflatedView) {
        return new ViewHolder(inflatedView, this);
    }

    public class ViewHolder extends BaseAdapter.ViewHolder<Download> {

        @Bind(R.id.item_name_view)
        TextView name;

        public ViewHolder(View itemView, BaseAdapter<Download> adapter) {
            super(itemView, adapter);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindItem(Download download, int position) {
            name.setText(download.item().value());
        }
    }
}
