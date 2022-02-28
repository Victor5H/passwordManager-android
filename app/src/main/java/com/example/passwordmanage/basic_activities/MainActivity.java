package com.example.passwordmanage.basic_activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordmanage.CustomHelper;
import com.example.passwordmanage.R;
import com.example.passwordmanage.RecyclerViewAdapter;
import com.example.passwordmanage.models.Entries_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.OnBalloonClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView rv;
    RecyclerView.Adapter rva;
    RecyclerView.LayoutManager rvl;
    CustomHelper customHelper;
    List<Entries_Model> list = new ArrayList<Entries_Model>();
    Context context;
    boolean isEmpty = false, searchByTag = true;
    Toolbar toolbar;
    Menu m1;
    BiometricManager biometricManager;
    TextView tv_empty;
    SearchView searchView;
    MenuItem searchBy;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        // Attach a callback used to capture the shared elements from this Activity to be used
        // by the container transform transition
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        window.setSharedElementsUseOverlay(false);

        setContentView(R.layout.activity_main);


        fab = findViewById(R.id.floatingActionButton);
        tv_empty = findViewById(R.id.tv_empty);
        tv_empty.setVisibility(View.GONE);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = MainActivity.this;
        customHelper = new CustomHelper(context);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Insert_d.class);
            startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this,fab,"shared_element_container").toBundle());
        });
        initBiometricManager();
        initRecyclerView();

        rv.scrollToPosition(rv.getScrollState());
        rv.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);
        fab.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        searchBy = menu.findItem(R.id.search_by);
//        inflater.inflate(R.menu.del_menu,menu);
        MenuItem search = menu.findItem(R.id.search);
        searchView = (SearchView) search.getActionView();
        //search.expandActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchByTag) {
                    searchView.setQueryHint("Search by tag");
                } else {
                    searchView.setQueryHint("Search by ID");
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (searchByTag) {
                    list = customHelper.searchByTag(query);
                    rva = new RecyclerViewAdapter(list, MainActivity.this);
                    rv.setAdapter(rva);
                } else {
                    list = customHelper.searchByID(query);
                    rva = new RecyclerViewAdapter(list, MainActivity.this);
                    rv.setAdapter(rva);
                }

                if (list.isEmpty()) {
                    Balloon balloon = new Balloon.Builder(context)
                            .setArrowSize(10)
                            .setArrowOrientation(ArrowOrientation.TOP)
                            .setArrowPosition(0.5f)
                            .setWidthRatio(.3f)
                            .setHeight(65)
                            .setTextSize(15f)
                            .setCornerRadius(4f)
                            .setAlpha(0.9f)
                            .setText("No records with this tag")
                            .setTextColor(ContextCompat.getColor(context, R.color.white))
                            .setTextIsHtml(true)
                            .setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary_variant))
                            .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
                            .build();
                    balloon.showAlignBottom(searchView);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchByTag) {
                    list = customHelper.searchByCharTag(newText);
                    rva = new RecyclerViewAdapter(list, MainActivity.this);
                    rv.setAdapter(rva);
                } else {
                    list = customHelper.searchByCharID(newText);
                    rva = new RecyclerViewAdapter(list, MainActivity.this);
                    rv.setAdapter(rva);
                }
                return true;
            }
        });
        searchView.setIconifiedByDefault(true);
        searchView.setOnCloseListener(() -> {
            initRecyclerView();
            return false;
        });
        this.m1 = menu;
        m1.setGroupVisible(0, false);
        return true;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        initRecyclerView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        customHelper.close();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String id = String.valueOf(item.getItemId());
        //Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.menu_atoz:
                Collections.sort(list, Entries_Model.EntriesAZcomparator);
                rva.notifyDataSetChanged();
                break;
            case R.id.menu_ztoa:
                Collections.sort(list, Entries_Model.EntriesZAcomparator);
                rva.notifyDataSetChanged();
                break;
            case R.id.ascend:
                Collections.sort(list, Entries_Model.EntriesAscendIDcomparator);
                rva.notifyDataSetChanged();
                break;
            case R.id.descend:
                Collections.sort(list, Entries_Model.EntriesDescendIDcomparator);
                rva.notifyDataSetChanged();
                break;
            case R.id.search_by_tag:
                item.setChecked(true);
                Toast.makeText(MainActivity.this, "tag", Toast.LENGTH_SHORT).show();
                searchByTag = true;
                searchBy.setIcon(R.drawable.ic_baseline_more);
                break;
            case R.id.search_by_ID:
                item.setChecked(true);
                Toast.makeText(MainActivity.this, "id", Toast.LENGTH_SHORT).show();
                searchByTag = false;
                searchBy.setIcon(R.drawable.ic_baseline_id);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void initRecyclerView() {
        list = customHelper.get_All();
        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rvl = new LinearLayoutManager(this);
        rv.setLayoutManager(rvl);
        rva = new RecyclerViewAdapter(list, MainActivity.this);
        rv.setAdapter(rva);
        isEmpty = list.isEmpty();
        if (isEmpty) {
            tv_empty.setVisibility(View.VISIBLE);
        } else {
            tv_empty.setVisibility(View.GONE);
        }

    }

    private void initBiometricManager() {
        biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "HW unavailable", Toast.LENGTH_SHORT).show();

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, " No HW", Toast.LENGTH_SHORT).show();
                tv_empty.setVisibility(View.VISIBLE);
                tv_empty.setText("No hardware");

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "No fingerprints enrolled", Toast.LENGTH_SHORT).show();
                tv_empty.setVisibility(View.VISIBLE);
                tv_empty.setText("No fingerprints Enrolled");

            case BiometricManager.BIOMETRIC_SUCCESS:

            case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED:

            case BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED:

            case BiometricManager.BIOMETRIC_STATUS_UNKNOWN:

        }
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                showHidden();
                super.onAuthenticationSucceeded(result);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Touch sensor to gain access").setNegativeButtonText("Cancel").build();
        biometricPrompt.authenticate(promptInfo);
    }

    private void showHidden() {
        fab.setEnabled(true);
        m1.setGroupVisible(0, true);
        rv.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        if (isEmpty) {
            Balloon balloon = new Balloon.Builder(context)
                    .setArrowSize(10)
                    .setArrowOrientation(ArrowOrientation.RIGHT)
                    .setArrowPosition(0.5f)
                    .setWidthRatio(.3f)
                    .setHeight(65)
                    .setTextSize(15f)
                    .setCornerRadius(14f)
                    .setAlpha(0.9f)
                    .setText("Insert a new record")
                    .setTextColor(ContextCompat.getColor(context, R.color.white))
                    .setTextIsHtml(true)
                    .setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary_variant))
                    .setBalloonAnimation(BalloonAnimation.ELASTIC)
                    .build();
            balloon.showAlignLeft(fab);
            balloon.setOnBalloonClickListener(new OnBalloonClickListener() {
                @Override
                public void onBalloonClick(@NotNull View view) {
                    balloon.dismissWithDelay(11);
                }
            });
        }
    }
}