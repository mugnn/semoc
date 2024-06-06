package com.ucsal.semoc.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ucsal.semoc.R;
import com.ucsal.semoc.models.EntityModel;
import com.ucsal.semoc.services.ApiService;
import com.ucsal.semoc.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EntityFragment extends Fragment {
  private final int entityIndex;
  private ApiService apiService;
  private TextView entityName;
  private TextView entityBio;
  private TextView entityType;

  public EntityFragment(int entityIndex) {
    this.entityIndex = entityIndex;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.entity_fragment, container, false);
    Retrofit retrofit = RetrofitClient.getClient("https://raw.githubusercontent.com/ucsal/semoc/main/api/");
    apiService = retrofit.create(ApiService.class);

    Button backButton = view.findViewById(R.id.button_back_entity);
    backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());

    entityName = view.findViewById(R.id.sub_entity_name);
    entityBio = view.findViewById(R.id.sub_entity_bio);
    entityType = view.findViewById(R.id.sub_entity_type);

    loadData();

    return view;
  }

  private void loadData() {
    Call<List<EntityModel>> call = apiService.getEntities();
    call.enqueue(new Callback<List<EntityModel>>() {
      @Override
      public void onResponse(@NonNull Call<List<EntityModel>> call, @NonNull Response<List<EntityModel>> response) {
        if (response.isSuccessful()) {
          List<EntityModel> data = response.body();
          assert data != null;
          setText(data.get(entityIndex > 29 ? 29 : entityIndex + 1));
        } else {
          Log.e("API error", "request failed");
        }
      }

      @Override
      public void onFailure(@NonNull Call<List<EntityModel>> call, @NonNull Throwable t) {
        Log.e("API request failed", "cannot load data: " + t.getMessage());
      }
    });
  }

  private void setText(EntityModel entity) {
    entityName.setText(entity.getNome());
    entityBio.setText(entity.getBio());
    entityType.setText(entity.getTipo());
  }
}
