package com.ucsal.semoc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsal.semoc.R;
import com.ucsal.semoc.models.ScheduleEventModel;
import com.ucsal.semoc.views.ItemAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScheduleFragment extends Fragment {

  private RecyclerView itemList;

  private ItemAdapter adapter;
  public static List<ScheduleEventModel> items = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.schedule_fragment, container, false);
    itemList = view.findViewById(R.id.itemList);
    initializeViews();
    adapter = new ItemAdapter(items, new ItemAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(ScheduleEventModel item) {
        openSubScheduleFragment(item);
      }
    });
    itemList.setAdapter(adapter);
    itemList.setLayoutManager(new LinearLayoutManager(view.getContext()));
    return view;
  }

  private void openSubScheduleFragment(ScheduleEventModel item) {
    SubScheduleFragment subFragment = new SubScheduleFragment();
    Bundle args = new Bundle();
    args.putSerializable("item", item);
    subFragment.setArguments(args);
    requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, subFragment)
            .addToBackStack(null)
            .commit();
  }

  private void initializeViews() {
    if (items.isEmpty()) {
      Random random = new Random();

      String[] dates = {"20/10/2023 12:00", "20/10/2023 14:00", "21/10/2023 10:00", "21/10/2023 11:00", "22/10/2023 09:00", "23/10/2023 14:00"};
      String[] themes = {"Democriacia", "Passado, Presente e Futuro", "Psicanálise", "Educação"};
      String[] activities = {"Abertura", "Mesa Redonda", "Minicurso", "Pesquisa"};

      for (int i = 0; i < 10; i++) {
        ScheduleEventModel model = new ScheduleEventModel();

        String date = dates[random.nextInt(dates.length)];
        String theme = themes[random.nextInt(themes.length)];
        String activity = activities[random.nextInt(activities.length)];

        model.setDate(date);
        model.setTheme(theme);
        model.setActivity(activity);

        items.add(model);
      }
    }
  }
}
