package com.ucsal.semoc.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.ucsal.semoc.R;
import com.ucsal.semoc.views.ImageSliderAdapter;

public class HomeFragment extends Fragment {
  private ViewPager2 viewPager;
  private Handler handler = new Handler();
  private int[] images = {
          R.drawable.image_carousel_1,
          R.drawable.image_carousel_2,
          R.drawable.image_carousel_3
  };
  private int delay = 6000;

  private final Runnable slideRunnable = new Runnable() {
    @Override
    public void run() {
      if (isAdded()) {
        int currentPosition = viewPager.getCurrentItem();
        int nextPosition = currentPosition + 1;
        animateToPage(nextPosition);
        handler.postDelayed(this, delay);
      }
    }
  };

  private void animateToPage(int page) {
    RecyclerView recyclerView = (RecyclerView) viewPager.getChildAt(0);
    if (recyclerView != null) {
      RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
        @Override
        protected int calculateTimeForScrolling(int dx) {
          return super.calculateTimeForScrolling(dx) * 10;
        }
      };
      smoothScroller.setTargetPosition(page);
      recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.home_fragment, container, false);
    viewPager = view.findViewById(R.id.viewPager);
    viewPager.setAdapter(new ImageSliderAdapter(getContext(), images));
    viewPager.setCurrentItem(0, false);
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    handler.postDelayed(slideRunnable, delay);
  }

  @Override
  public void onPause() {
    handler.removeCallbacks(slideRunnable);
    super.onPause();
  }
}
