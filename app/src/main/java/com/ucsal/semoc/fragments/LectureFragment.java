package com.ucsal.semoc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;
import com.ucsal.semoc.fragments.subfragments.SubLectureFragment;
import com.ucsal.semoc.models.LecturesModel;
import com.ucsal.semoc.services.ApiService;
import com.ucsal.semoc.services.RetrofitClient;
import com.ucsal.semoc.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LectureFragment extends Fragment {
  private ApiService apiService;

  private RecyclerView itemList;

  private ItemAdapter adapter;

  public static List<LecturesModel> items = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.schedule_fragment, container, false);
    Retrofit retrofit = RetrofitClient.getClient("https://raw.githubusercontent.com/ucsal/semoc/main/api/");

    itemList = view.findViewById(R.id.itemList);
    apiService = retrofit.create(ApiService.class);

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemList.getContext(), DividerItemDecoration.VERTICAL);
    itemList.addItemDecoration(dividerItemDecoration);

    adapter = new ItemAdapter(items, this::openSubLectureFragment);
    itemList.setAdapter(adapter);
    itemList.setLayoutManager(new LinearLayoutManager(view.getContext()));

    loadData();

    return view;
  }

  private void openSubLectureFragment(LecturesModel item) {
    SubLectureFragment subFragment = new SubLectureFragment();
    Bundle args = new Bundle();
    args.putSerializable("item", item);
    subFragment.setArguments(args);
    requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, subFragment)
            .addToBackStack(null)
            .commit();
  }

  private void loadData() {
    Call<List<LecturesModel>> call = apiService.getLectures();
    call.enqueue(new Callback<List<LecturesModel>>() {
      @Override
      public void onResponse(Call<List<LecturesModel>> call, Response<List<LecturesModel>> response) {
        if (response.isSuccessful()) {
          List<LecturesModel> lectures = response.body();
          initializeViews(lectures);
        } else {
          Log.e("API error", "request failed");
        }
      }

      @Override
      public void onFailure(Call<List<LecturesModel>> call, Throwable t) {
        Log.e("API request failed", "cannot load data: " + t.getMessage());
      }
    });
  }

  @SuppressLint("NotifyDataSetChanged")
  private void initializeViews(List<LecturesModel> lectures) {
    if (items.isEmpty()) {
      items.addAll(lectures);
      adapter.notifyDataSetChanged();
    }
  }
}
