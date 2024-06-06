package com.ucsal.semoc.abstractions;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;


import java.util.ArrayList;
import java.util.List;

public class GenericItem<T> extends RecyclerView.Adapter<ItemView<T>> {
  protected List<T> items;
  protected List<T> filteredItems;
  protected OnItemClickListener<T> onItemClickListener;
  protected ItemBinder<T> itemBinder;

  public interface OnItemClickListener<T> {
    void onItemClick(T item);
  }

  public interface ItemBinder<T> {
    void bind(ItemView<T> holder, T item);
  }

  public GenericItem(List<T> items, OnItemClickListener<T> onItemClickListener, ItemBinder<T> itemBinder) {
    this.items = items;
    this.filteredItems = new ArrayList<>(items);
    this.onItemClickListener = onItemClickListener;
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
    T item = filteredItems.get(position);
    itemBinder.bind(holder, item);
    holder.itemView.setOnClickListener(v -> {
      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(item);
      }
    });
  }

  @Override
  public int getItemCount() {
    return filteredItems.size();
  }

  @SuppressLint("NotifyDataSetChanged")
  public void filter(String query) {
    filteredItems.clear();
    if (!query.isEmpty()) {
      for (T item : items) {
        Item currentItem = (Item) item;
        if (currentItem.getData().contains(query)) {
          filteredItems.add(item);
        }
      }
    } else {
      filteredItems.addAll(items);
    }
    notifyDataSetChanged();
  }

  @SuppressLint("NotifyDataSetChanged")
  public void updateItems(List<T> newItems) {
    this.items.clear();
    this.items.addAll(newItems);
    this.filteredItems.clear();
    this.filteredItems.addAll(newItems);
    notifyDataSetChanged();
  }
}
