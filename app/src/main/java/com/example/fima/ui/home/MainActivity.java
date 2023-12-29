package com.example.fima.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fima.R;
import com.example.fima.databinding.ActivityMainBinding;
import com.example.fima.models.DBHandler;
import com.example.fima.models.User;
import com.example.fima.ui.SignIn.LogInActivity;
import com.example.fima.ui.settings.changeInfor;
import com.example.fima.ui.settings.changePassword;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    public DBHandler dbHandler;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        drawerLayout = findViewById(R.id.container1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).setDrawerLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        AppBarConfiguration appBarConfiguration =  new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        toggle.syncState();
        //
        NavigationView navigationView = findViewById(R.id.navigation_viewV);
        // Ánh xạ các thành phần
        View headerView = navigationView.getHeaderView(0);
        TextView tvUser = headerView.findViewById(R.id.tvHeaderUser);
        TextView tvEmail = headerView.findViewById(R.id.tvHeaderEmail);
        // Đặt text
        String fullname = User.getInstance().getFirstname().toString() + " " + User.getInstance().getLastname().toString();
        tvUser.setText(fullname);
        tvEmail.setText(User.getInstance().getEmail().toString());


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_changeInfo)
        {
            Intent intent = new Intent(MainActivity.this, changeInfor.class);
            startActivity(intent);
        }
        if (id == R.id.nav_ChangePassword)
        {
            Intent intent = new Intent(MainActivity.this, changePassword.class);
            startActivity(intent);
        }
        if (id == R.id.nav_LogOut)
        {
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    // Xu ly click nut back
    @Override
    public void onBackPressed() {
        // Dang mo back dong lai
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // Dong roi back thoat app
        else {
            super.onBackPressed();
        }
    }
}