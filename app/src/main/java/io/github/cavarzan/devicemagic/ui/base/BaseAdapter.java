package io.github.cavarzan.devicemagic.ui.base;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder<T>> {

    // Lista dos itens que serão utilizados para popular a lista
    private List<T> items;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    protected BaseAdapter() {
        items = new ArrayList<>();
    }

    protected BaseAdapter(List<T> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        this.items = items;
    }

    protected void setItems(List<T> items) {
        this.items = items;
    }

    protected abstract
    @LayoutRes
    int getLayoutForType(int viewType);

    @Override
    public ViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(getLayoutForType(viewType), parent, false);
        return onCreateViewHolder(inflatedView);
    }

    protected abstract ViewHolder<T> onCreateViewHolder(View inflatedView);

    @Override
    public void onBindViewHolder(ViewHolder<T> holder, int position) {
        holder.bindItem(items.get(position), position);
    }

    public T getItem(int position) {
        if (items != null) {
            return this.items.get(position);
        }
        return null;
    }

    /**
     * Adiciona um item na lista do adapter
     *
     * @param item
     * @param position
     */
    public void add(T item, int position) {
        if (item == null || position >= items.size() || position == -1) {
            return;
        }
        items.add(position, item);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    /**
     * Adiciona um item na lista do adapter
     *
     * @param item
     */
    public void add(T item) {
        if (item == null) {
            return;
        }
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    /**
     * Remove um item da lista do adapter
     *
     * @param item
     */
    public void remove(T item) {
        if (item == null) {
            return;
        }
        int position = items.indexOf(item);
        items.remove(item);
        notifyItemRemoved(position);
    }

    /**
     * Adiciona todos os itens informados no adapter.
     * Não faz verificação de duplicidade
     *
     * @param items
     */
    public void addAll(List<T> items) {
        if (items == null) {
            return;
        }
        int length = items.size();
        int start = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(start, length);
    }

    /**
     * @param items
     */
    public void replace(List<T> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = new ArrayList<>(items);
        }

        notifyDataSetChanged();
    }

    public void clear() {
        this.items = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(ViewHolder<T> itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected final View root;
        private BaseAdapter<T> mAdapter;

        public ViewHolder(View itemView, BaseAdapter<T> adapter) {
            this(itemView);
            mAdapter = adapter;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        protected abstract void bindItem(T item, int position);

        @Override
        public void onClick(View v) {
            if (mAdapter != null) mAdapter.onItemHolderClick(this);
        }
    }

    public List<T> getItems() {
        return items;
    }
}
