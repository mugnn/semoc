package com.ucsal.semoc.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ucsal.semoc.R;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {
  private Context context;
  private int[] imageUrls;

  public ImageSliderAdapter(Context context, int[] imageUrls) {
    this.context = context;
    this.imageUrls = imageUrls;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    int imageId = imageUrls[position % imageUrls.length];
    Glide.with(context)
            .load(imageId)
            .into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return Integer.MAX_VALUE;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    ViewHolder(View view) {
      super(view);
      imageView = view.findViewById(R.id.currentImage);
    }
  }
}