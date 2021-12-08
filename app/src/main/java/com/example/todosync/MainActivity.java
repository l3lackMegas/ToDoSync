package com.example.todosync;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Vector;

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
        current_page = page;
    }

    public void showDialog(View btn) {
        switch (current_page) {
            case 0 : {
                showSlideUp(btn, R.layout.dialog_add_todo);
                createAddToDoDialog();
                break;
            }

            case 1 : {
                showSlideUp(btn, R.layout.dialog_add_label);
                break;
            }
        }
    }

    public void btnLogin(View v) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        showSlideUp(v, R.layout.dialog_login);
        (new Handler()).postDelayed(()->{
            drawer.closeDrawers();
        }, 100);
    }

    public void btnRegister(View v) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        showSlideUp(v, R.layout.dialog_register);
        (new Handler()).postDelayed(()->{
            drawer.closeDrawers();
        }, 100);
    }

    public void btnSwitchLogin(View v) {
        hideSlideUp(v);
        (new Handler()).postDelayed(()->{
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            showSlideUp(v, R.layout.dialog_login);
            (new Handler()).postDelayed(()->{
                drawer.closeDrawers();
            }, 100);
        }, 300);
    }

    public void btnSwitchRegister(View v) {
        hideSlideUp(v);
        (new Handler()).postDelayed(()-> {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            showSlideUp(v, R.layout.dialog_register);
            (new Handler()).postDelayed(()->{
                drawer.closeDrawers();
            }, 100);
        }, 300);
    }



    public boolean isLogin = false;

    public void btnLoginAction(View v) {
        isLogin = true;
        EditText inputEmail = (EditText) mBottomSheetDialog.findViewById(R.id.editTextLoginEmail);
        EditText inputPass = (EditText) mBottomSheetDialog.findViewById(R.id.editTextLoginPassword);
        if(
            inputEmail.getText().toString().equals("user@email.com") &&
            inputPass.getText().toString().equals("123456")
        ) {
            findViewById(R.id.header_login).setVisibility(View.GONE);
            findViewById(R.id.header_user).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_manage_account).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_logout).setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, (String) "Login successfully with Email " + inputEmail.getText().toString(), Toast.LENGTH_LONG).show();
            hideSlideUp(v);
            (new Handler()).postDelayed(()->{
                openDrawer();
            }, 100);
        } else {
            Toast.makeText(MainActivity.this, (String) "Sorry, Your email or password is incorrect", Toast.LENGTH_LONG).show();
        }

    }

    public void btnRegisterAction(View v) {
        isLogin = true;
        EditText inputEmail = (EditText) mBottomSheetDialog.findViewById(R.id.editTextRegEmail);
        EditText inputPass = (EditText) mBottomSheetDialog.findViewById(R.id.editTextRegPassword);
        EditText inputRePass = (EditText) mBottomSheetDialog.findViewById(R.id.editTextRepeatPassword);
        if(
                !inputEmail.getText().toString().equals("") &&
                !inputPass.getText().toString().equals("") &&
                !inputRePass.getText().toString().equals("")
        ) {
            findViewById(R.id.header_login).setVisibility(View.GONE);
            findViewById(R.id.header_user).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_manage_account).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_logout).setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, (String) "Register successfully with Email " + inputEmail.getText().toString(), Toast.LENGTH_LONG).show();
            hideSlideUp(v);
            (new Handler()).postDelayed(()->{
                openDrawer();
            }, 100);
        } else {
            Toast.makeText(MainActivity.this, (String) "Please fill all form before register!", Toast.LENGTH_LONG).show();
        }

    }

    public void btnLogoutAction(View v) {
        findViewById(R.id.header_login).setVisibility(View.VISIBLE);
        findViewById(R.id.header_user).setVisibility(View.GONE);
        findViewById(R.id.btn_manage_account).setVisibility(View.GONE);
        findViewById(R.id.btn_logout).setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, (String) "You are logged out.", Toast.LENGTH_LONG).show();
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

    public void btnCloudAction(View v) {
        if(isLogin == true) {
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Uploading to cloud...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            (new Handler()).postDelayed(()->{

                ImageButton btnCloud = (ImageButton) findViewById(R.id.btnCloud);
                btnCloud.setImageResource(R.drawable.ic_baseline_cloud_done_24);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, (String) "Your ToDoList has been backup.", Toast.LENGTH_LONG).show();
            }, 2000);
        } else {
            Toast.makeText(MainActivity.this, (String) "Please login before upload to cloud.", Toast.LENGTH_LONG).show();
            openDrawer();
        }
    }

    public void btnOpenSetting(View v) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideup, R.anim.slidedown);
    }

    public void btnOpenHelp(View v) {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideup, R.anim.slidedown);
    }
}