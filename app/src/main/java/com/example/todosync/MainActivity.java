package com.example.todosync;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.todosync.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import eltos.simpledialogfragment.color.SimpleColorDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_label)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        ImageButton btnUser = findViewById(R.id.btnUser);
        //mBottomSheetDialog.getWindow().setWindowAnimations(R.style.MaterialDialogSheet);


        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();

            }
        });

    }

    public void openDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    public static int current_page = 0;

    public static void setCurrentPage(int page, Context context) {
        Toast.makeText(context, (String) "asd", Toast.LENGTH_LONG);
        current_page = page;
    }

    public void showDialog(View btn) {
        if(current_page == 1) {
            showSlideUp(btn, R.layout.dialog_add_label);
        } else {
            showSlideUp(btn, R.layout.dialog_add_todo);
            createAddToDoDialog();
        }
    }

    private void createAddToDoDialog() {
        // Create Selector
        String[] todoLabels = {"Work", "Study", "Hobby"};
        Spinner label_selector = (Spinner)mBottomSheetDialog.findViewById(R.id.label_selector);

        ArrayAdapter<String> labelSelectorAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, todoLabels);
        labelSelectorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        label_selector.setAdapter(labelSelectorAdapter);

        // Create Date Picker
        final Calendar c = Calendar.getInstance();
        final int[] mYear = {c.get(Calendar.YEAR)};
        final int[] mMonth = {c.get(Calendar.MONTH)};
        final int[] mDay = {c.get(Calendar.DAY_OF_MONTH)};

        TextView AddDatePicker = (TextView)mBottomSheetDialog.findViewById(R.id.todoAddDate);
        AddDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                c.set(year, month, day);
                                String date = new SimpleDateFormat("MM/dd/yyyy").format(c.getTime());
                                AddDatePicker.setText(date);

                                mYear[0] = c.get(Calendar.YEAR);
                                mMonth[0] = c.get(Calendar.MONTH);
                                mDay[0] = c.get(Calendar.DAY_OF_MONTH);
                            }
                        }, mYear[0], mMonth[0], mDay[0]);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());

                Calendar d = Calendar.getInstance();
                d.add(Calendar.MONTH,1);

                dpd.getDatePicker().setMaxDate(d.getTimeInMillis());
                dpd.show();


            }

        });
    }

    public Dialog mBottomSheetDialog;
    public void showSlideUp(View btn, int target_layout) {
        mBottomSheetDialog = new Dialog(this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(target_layout); // your custom view.


        //mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.setCancelable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mBottomSheetDialog.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                hideSlideUp(btn);
            }
        });
        containerFocus(true);

        mBottomSheetDialog.show();
    }

    public void hideSlideUp(View btn) {
        mBottomSheetDialog.dismiss();
        containerFocus(false);
    }

    private void containerFocus(boolean isFocus) {
        View container = (View) findViewById(R.id.container);
        Animation anim_container;
        if(isFocus == true) {
            anim_container = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_scale_out);
        } else {
            anim_container = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_scale_in);
        }
        anim_container.setDuration(200);
        anim_container.setFillAfter(true);
        container.startAnimation(anim_container);
    }

    private static final String COLOR_DIALOG = "dialogTagColor";

    public void showColorPicker(View view) {
        SimpleColorDialog.build()
            .title("Pick a color")
            .allowCustom(true)
            .colorPreset(0xff009688)
            .show(this, COLOR_DIALOG);

    }
}