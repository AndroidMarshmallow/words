package com.yukon.absenceplanner.mobile.android.main_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yukon.absenceplanner.mobile.android.R;
import com.yukon.absenceplanner.mobile.android.Values;
import com.yukon.absenceplanner.mobile.android.authorization_activity.view.AuthorizationActivity;
import com.yukon.absenceplanner.mobile.android.enteties.Absences;
import com.yukon.absenceplanner.mobile.android.enteties.Employees;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.presenter.AbsenceInfoPersonsFragmentPresenter;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.presenter.AbsenceInfoPersonsFragmentPresenterImpl;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.view.AbsenceInfoPersonFragment;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.view.AbsensInfoPersonFragmentView;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.view.AbsenceInfoFragmentForManager;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.view.AddAbsenceFragment;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.view.AddAbsenceFragmentForManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AbsensInfoPersonFragmentView, View.OnClickListener{


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nvView)
    NavigationView nvDrawer;

    @BindView(R.id.tvToolbarText)
    TextView tvToolbarText;

    @BindView(R.id.btUserSetingMenu)
    Button btUserSetingMenu;



    private ActionBarDrawerToggle mDrawerToggle;
    private LayoutInflater inflater;
    private PopupWindow pw;
    private View popupView;
    private Intent intent;
    private TextView tvChangeLanguage;
    private TextView tvLogOff;
    private TextView tvEmployeePerson;
    private TextView addRequest;
    private TextView infoAbsencePerson;
    private AbsenceInfoPersonsFragmentPresenter absenceInfoPersonsFragmentPresente;
    private Employees employee;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            popupView = inflater.inflate(R.layout.users_menu_layout, (ViewGroup) findViewById(R.id.users_menu_grup), false);
            mDrawerToggle = setupDrawerToggle();
            setSupportActionBar(toolbar);
            absenceInfoPersonsFragmentPresente = new AbsenceInfoPersonsFragmentPresenterImpl(this);
            absenceInfoPersonsFragmentPresente.getEmployee();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        View headerView = navigationView.getHeaderView(0);


        addRequest = ButterKnife.findById(headerView, R.id.addRequest);
        infoAbsencePerson = ButterKnife.findById(headerView, R.id.infoAbsencePerson);

        addRequest.setOnClickListener(this);
        infoAbsencePerson.setOnClickListener(this);
        btUserSetingMenu.setOnClickListener(this);


    }


    public void showPopup(View view){
            tvChangeLanguage=(TextView) popupView.findViewById(R.id.tvChangeLanguage);
            tvLogOff=(TextView) popupView.findViewById(R.id.tvLogOff);
            tvEmployeePerson=(TextView) popupView.findViewById(R.id.tvEmployeePerson);
            try {
                SpannableString strLastName = new SpannableString(employee.getEmployees().get(0).getLastName() + ", " + employee.getEmployees().get(0).getFirstName());
                strLastName.setSpan(new StyleSpan(Typeface.BOLD), 0, employee.getEmployees().get(0).getLastName().length(), 0);
                tvEmployeePerson.setText(strLastName);
            } catch(Exception e) { }
            intent = new Intent(this, AuthorizationActivity.class);
            tvChangeLanguage.setTextColor(Color.rgb(62,62,62));
            tvChangeLanguage.setBackgroundColor(Color.rgb(255,255,255));
            tvLogOff.setTextColor(Color.rgb(62,62,62));
            tvLogOff.setBackgroundColor(Color.rgb(255,255,255));
            pw = new PopupWindow(getApplicationContext());
            pw.setTouchable(true);
            pw.setFocusable(true);
            pw.setOutsideTouchable(true);
            pw.setTouchInterceptor(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        pw.dismiss();

                        return true;
                    }

                    return false;
                }
            });

            pw.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
            pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            pw.setOutsideTouchable(false);
            pw.setContentView(popupView);
            pw.showAsDropDown(view, 0, 30);
            tvChangeLanguage.setOnClickListener(this);
            tvLogOff.setOnClickListener(this);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btUserSetingMenu:
                showPopup(v);
                break;
            case R.id.addRequest:
                selectedFragmentForAddRequests();
                break;
            case R.id.infoAbsencePerson:
                selectedFragmentWithListRequest();
                break;
            case R.id.tvChangeLanguage:
                popupMenuItemClickListenerForChangteLanguage(v);
                break;
            case R.id.tvLogOff:
                popupMenuItemClickListenerForLogOff(v);
                break;
        }


    }
    public void popupMenuItemClickListenerForChangteLanguage(View v){
        tvChangeLanguage.setTextColor(Color.rgb(83,28,128));
        tvChangeLanguage.setBackgroundColor(Color.rgb(192,192,192));
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                pw.dismiss();
            }
        },500);
    }

    public void popupMenuItemClickListenerForLogOff(View v){
        tvLogOff.setTextColor(Color.rgb(83,28,128));
        tvLogOff.setBackgroundColor(Color.rgb(192,192,192));
        startActivity(intent);
        intent = new Intent(this, AuthorizationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Exit me", true);
        startActivity(intent);
        finish();
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                pw.dismiss();
            }
        },500);
    }

    @SuppressLint("SetTextI18n")
    public void selectedFragmentForAddRequests() {
        Fragment fragment = null;
        switch (employee.getEmployees().get(0).getRole()) {
            case Values.MANAGER:
                tvToolbarText.setText(R.string.add_request_manager);
                fragment = AddAbsenceFragmentForManager.newInstance();
                addRequest.setTextColor(Color.rgb(83, 28, 128));
                addRequest.setBackgroundColor(Color.rgb(192, 192, 192));
                break;
            case Values.EMPLOYEE:
                tvToolbarText.setText(R.string.add_request);
                fragment = AddAbsenceFragment.newInstance();
                addRequest.setTextColor(Color.rgb(83, 28, 128));
                addRequest.setBackgroundColor(Color.rgb(192, 192, 192));
                break;
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        drawerLayout.closeDrawers();
    }

    @SuppressLint("SetTextI18n")
    public void selectedFragmentWithListRequest() {
        Fragment fragment = null;
            switch (employee.getEmployees().get(0).getRole()) {
                case Values.MANAGER:
                    tvToolbarText.setText(R.string.add_request_manager);
                    fragment = AbsenceInfoFragmentForManager.newInstance();
                    infoAbsencePerson.setTextColor(Color.rgb(83,28,128));
                    infoAbsencePerson.setBackgroundColor(Color.rgb(192,192,192));
                    break;
                case Values.EMPLOYEE:
                    tvToolbarText.setText(R.string.requests);
                    fragment = AbsenceInfoPersonFragment.newInstance();
                    infoAbsencePerson.setTextColor(Color.rgb(83,28,128));
                    infoAbsencePerson.setBackgroundColor(Color.rgb(192,192,192));
                    break;
            }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        addRequest.setTextColor(Color.rgb(28,77,127));
        infoAbsencePerson.setTextColor(Color.rgb(28,77,127));
        addRequest.setBackgroundColor(Color.rgb(255,255,255));
        infoAbsencePerson.setBackgroundColor(Color.rgb(255,255,255));
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(this, AuthorizationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit me", true);
            startActivity(intent);
            finish();
    }

    @Override
    public void onSuccessForAbsences(Absences absences) {

    }

    @Override
    public void onSuccessForEmployees(Employees employees) {
        switch (employees.getEmployees().get(0).getRole()) {
            case Values.MANAGER:
                tvToolbarText.setText(R.string.add_request_manager);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent,
                        AbsenceInfoFragmentForManager.newInstance()).commit();
                break;
            case Values.EMPLOYEE:
                tvToolbarText.setText(R.string.requests);
                FragmentManager fragmentEmployee = getSupportFragmentManager();
                fragmentEmployee.beginTransaction().replace(R.id.flContent,
                        AbsenceInfoPersonFragment.newInstance()).commit();
                break;
        }
        this.employee =employees;
    }

    @Override
    public void onFailure() {

    }
}


