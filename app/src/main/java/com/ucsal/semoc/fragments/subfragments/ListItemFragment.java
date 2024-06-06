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
import com.ucsal.semoc.abstractions.Item;
import com.ucsal.semoc.fragments.EntityFragment;

public class ListItemFragment extends Fragment {
  private TextView textViewDate;
  private TextView textViewTheme;
  private TextView textViewName;
  private TextView textViewHour;
  private TextView textViewDesc;
  private TextView textViewLocal;
  private TextView textViewFormato;
  private TextView textViewNivel;
  private final String nextButtonName;
  private Button nextButton;

  private Item currentItem;

  public ListItemFragment(String nextButtonName) {
    this.nextButtonName = "Ver " + nextButtonName;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.sub_list_fragment, container, false);

    Button backButton = view.findViewById(R.id.button_back);
    backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());

    nextButton = view.findViewById(R.id.button_entity_run);
    nextButton.setText(nextButtonName);

    textViewDate = view.findViewById(R.id.sub_date);
    textViewTheme = view.findViewById(R.id.sub_theme);
    textViewName = view.findViewById(R.id.sub_name);
    textViewHour = view.findViewById(R.id.sub_hour);
    textViewDesc = view.findViewById(R.id.sub_desc);
    textViewLocal = view.findViewById(R.id.sub_local);
    textViewFormato = view.findViewById(R.id.sub_formato);
    textViewNivel = view.findViewById(R.id.sub_nivel);

    if (getArguments() != null) {
      Item item = (Item) getArguments().getSerializable("item");
      this.currentItem = item;
      if (item != null) {
        updateUIWithEventDetails(item);
      }
    }
    return view;
  }

  @Override
  public void onResume() {
    nextButton.setOnClickListener(v -> {
      requireActivity()
              .getSupportFragmentManager()
              .beginTransaction()
              .replace(R.id.fragment_container, new EntityFragment(currentItem.getEntity()))
              .addToBackStack(null)
              .commit();
    });
    super.onResume();
  }

  private void updateUIWithEventDetails(Item item) {
    textViewDate.setText(item.getData());
    textViewTheme.setText(item.getTema());
    textViewHour.setText(item.getHora());
    textViewName.setText(item.getNome());
    textViewDesc.setText(item.getDescricao());
    textViewLocal.setText(item.getLocal());
    textViewFormato.setText(item.getFormato());
    textViewNivel.setText(item.getNivel());
  }
}