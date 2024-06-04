package com.ucsal.semoc.activities;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.ucsal.semoc.fragments.HomeFragment;
import com.ucsal.semoc.R;
import com.ucsal.semoc.fragments.InfoFragment;
import com.ucsal.semoc.fragments.LectureFragment;
import com.ucsal.semoc.fragments.MinicoursesFragment;

public class MainActivity extends AppCompatActivity {
  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    settingUp(savedInstanceState);
    setUpNavigationView();
  }

  private void setUpNavigationView() {
    NavigationView navigationView = findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(menuItem -> {
      int id = menuItem.getItemId();

      if (id == R.id.nav_home) {
        replaceFragment(new HomeFragment());
      } else if (id == R.id.nav_lectures) {
        replaceFragment(new LectureFragment());
      } else if (id == R.id.nav_minicourses) {
        replaceFragment(new MinicoursesFragment());
      } else if (id == R.id.nav_info) {
        replaceFragment(new InfoFragment());
      }

      drawerLayout.closeDrawer(GravityCompat.START);
      return true;
    });
  }

  private void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
  }

  private void settingUp(Bundle savedInstanceState) {
    drawerLayout = findViewById(R.id.main);
    ImageButton menuButton = findViewById(R.id.imageButton4);
    menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .add(R.id.fragment_container, new HomeFragment())
              .commit();
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}