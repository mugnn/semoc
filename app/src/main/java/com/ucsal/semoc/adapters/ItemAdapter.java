package com.ucsal.semoc.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;
import com.ucsal.semoc.models.LecturesModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemView> {
  private List<LecturesModel> items;
  private OnItemClickListener onItemClickListener;
  public interface OnItemClickListener {
    void onItemClick(LecturesModel item);
  }

  public ItemAdapter(List<LecturesModel> items, OnItemClickListener onItemClickListener) {
    this.items = items;
    this.onItemClickListener = onItemClickListener;
  }

  @NonNull
  @Override
  public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
    return new ItemView(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ItemView holder, int position) {
    LecturesModel itemModel = items.get(position);
    holder.setDate(itemModel.getData());
    holder.setTheme(itemModel.getTema());
    holder.setActivity(itemModel.getHora());
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  class ItemView extends RecyclerView.ViewHolder {
    private TextView Date;
    private TextView Theme;
    private TextView Activity;
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
}