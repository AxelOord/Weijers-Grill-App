package com.hhs.testproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.hhs.testproject.adapters.PresetCardAdapter;
import com.hhs.testproject.databinding.ActivityMainBinding;
import com.hhs.testproject.models.PresetsModel;
import com.hhs.testproject.models.PresetsModelFactory;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // Init dependencies
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private PresetsModel presetsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // New global dependency
        presetsModel = new ViewModelProvider(this,
                new PresetsModelFactory(getApplication(),
                        new PresetCardAdapter(this)))
                .get(PresetsModel.class);

        // Add presetsModel to custom dependency manager
        ((MainApplication) getApplication()).setPresetsModel(presetsModel);


        // TODO: try to understand what is happening here
        //       https://developer.android.com/topic/libraries/data-binding/expressions
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


//        FragmentContainerView fragmentContainerView = findViewById(R.id.nav_host_fragment_content_main);

        // TODO: Comment
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//                presetsModel.changeDataOfCard();
                presetsModel.addCard();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}