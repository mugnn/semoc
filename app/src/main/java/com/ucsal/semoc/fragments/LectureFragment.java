package com.ucsal.semoc.fragments;

import android.os.Bundle;

import com.ucsal.semoc.R;
import com.ucsal.semoc.abstractions.GenericList;
import com.ucsal.semoc.fragments.subfragments.SubLectureFragment;
import com.ucsal.semoc.models.LecturesModel;

import java.util.List;

import retrofit2.Call;

public class LectureFragment extends GenericList<LecturesModel> {
  @Override
  protected int getItemListResource() {
    return R.id.itemList;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.list_fragment;
  }

  @Override
  protected String getBaseUrl() {
    return "https://raw.githubusercontent.com/ucsal/semoc/main/api/";
  }

  @Override
  protected Call<List<LecturesModel>> getApiCall() {
    return apiService.getLectures();
  }

  @Override
  protected void openDetailFragment(LecturesModel item) {
    SubLectureFragment subFragment = new SubLectureFragment();
    Bundle args = new Bundle();
    args.putSerializable("item", item);
    subFragment.setArguments(args);
    requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, subFragment)
            .addToBackStack(null)
            .commit();
  }

  @Override
  protected String setTitle() {
    return "Palestras";
  }
}
