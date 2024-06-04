package com.ucsal.semoc.abstractions;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;


import java.util.List;

public class GenericItem<T> extends RecyclerView.Adapter<ItemView<T>> {
  protected List<T> items;
  protected OnItemClickListener<T> onItemClickListener;
  protected ItemBinder<T> itemBinder;

  public interface OnItemClickListener<T> {
    void onItemClick(T item);
  }

  public interface ItemBinder<T> {
    void bind(ItemView<T> holder, T item);
  }

  public GenericItem(List<T> items, OnItemClickListener<T> onItemClickListener, ItemBinder<T> itemBinder) {
    this.onItemClickListener = onItemClickListener;
    this.items = items;
    this.itemBinder = itemBinder;
  }

  @NonNull
  @Override
  public ItemView<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    return new ItemView<>(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ItemView<T> holder, int position) {
    T item = items.get(position);
    itemBinder.bind(holder, item);
    holder.itemView.setOnClickListener(v -> {
      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(item);
      }
    });
  }

  @Override
  public int getItemCount() {
    return items.size();
  }
}
