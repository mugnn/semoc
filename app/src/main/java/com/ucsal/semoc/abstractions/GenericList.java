package com.ucsal.semoc.abstractions;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;
import com.ucsal.semoc.services.ApiService;
import com.ucsal.semoc.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public abstract class GenericList<T> extends Fragment {
  protected ApiService apiService;
  protected RecyclerView itemList;
  protected GenericItem<T> adapter;
  protected List<T> items = new ArrayList<>();
  protected TextView title;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResId(), container, false);
    Retrofit retrofit = RetrofitClient.getClient(getBaseUrl());

    title = view.findViewById(R.id.list_fragment_title);
    title.setText(setTitle());
    itemList = view.findViewById(getItemListResource());
    apiService = retrofit.create(ApiService.class);

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemList.getContext(), DividerItemDecoration.VERTICAL);
    itemList.addItemDecoration(dividerItemDecoration);

    adapter = new GenericItem<>(items, this::openDetailFragment, (holder, item) -> {
      holder.setDate(((Item) item).getData());
      holder.setTheme(((Item) item).getTema());
      holder.setActivity(((Item) item).getHora());
    });
    itemList.setAdapter(adapter);
    itemList.setLayoutManager(new LinearLayoutManager(view.getContext()));

    loadData();

    return view;
  }

  protected abstract int getItemListResource();

  protected abstract int getLayoutResId();

  protected abstract String getBaseUrl();

  protected abstract Call<List<T>> getApiCall();

  protected abstract void openDetailFragment(T item);

  protected abstract String setTitle();

  private void loadData() {
    Call<List<T>> call = getApiCall();
    call.enqueue(new Callback<List<T>>() {
      @Override
      public void onResponse(@NonNull Call<List<T>> call, @NonNull Response<List<T>> response) {
        if (response.isSuccessful()) {
          List<T> data = response.body();
          initializeViews(data);
        } else {
          Log.e("API error", "request failed");
        }
      }

      @Override
      public void onFailure(@NonNull Call<List<T>> call, @NonNull Throwable t) {
        Log.e("API request failed", "cannot load data: " + t.getMessage());
      }
    });
  }

  @SuppressLint("NotifyDataSetChanged")
  private void initializeViews(List<T> data) {
    items.clear();
    items.addAll(data);
    adapter.notifyDataSetChanged();
  }
}
