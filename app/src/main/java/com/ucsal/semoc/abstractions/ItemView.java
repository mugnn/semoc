package com.ucsal.semoc.abstractions;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;

import java.util.List;

public class ItemView<T> extends RecyclerView.ViewHolder {
  protected List<T> items;
  protected GenericItem.OnItemClickListener<T> onItemClickListener;
  private final TextView Date;
  private final TextView Theme;
  private final TextView Activity;
  public ItemView(@NonNull View itemView) {
    super(itemView);
    Date = itemView.findViewById(R.id.date);
    Theme = itemView.findViewById(R.id.theme);
    Activity = itemView.findViewById(R.id.activity);
    itemView.setOnClickListener(v -> {
      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(items.get(getAdapterPosition()));
      }
    });
  }
  public void setDate(String date) {
    this.Date.setText(date);
  }
  public void setTheme(String theme) {
    this.Theme.setText(theme);
  }
  public void setActivity(String activity) {
    this.Activity.setText(activity);
  }
}