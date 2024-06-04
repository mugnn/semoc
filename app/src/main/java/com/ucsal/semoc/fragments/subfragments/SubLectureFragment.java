package com.ucsal.semoc.fragments.subfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ucsal.semoc.R;
import com.ucsal.semoc.models.LecturesModel;

public class SubLectureFragment extends Fragment {
  private TextView textViewDate;
  private TextView textViewTheme;
  private TextView textViewActivity;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.sub_schedule_fragment, container, false);

    Button backButton = view.findViewById(R.id.button_back);
    backButton.setOnClickListener(v -> {
      if (getFragmentManager() != null) {
        getFragmentManager().popBackStack();
      }
    });

    textViewDate = view.findViewById(R.id.sub_date);
    textViewTheme = view.findViewById(R.id.sub_theme);
    textViewActivity = view.findViewById(R.id.sub_activity);

    if (getArguments() != null) {
      LecturesModel item = (LecturesModel) getArguments().getSerializable("item");
      if (item != null) {
        updateUIWithEventDetails(item);
      }
    }
    return view;
  }
  private void updateUIWithEventDetails(LecturesModel item) {
    textViewDate.setText(item.getData());
    textViewTheme.setText(item.getTema());
    textViewActivity.setText(item.getHora());
  }
}
