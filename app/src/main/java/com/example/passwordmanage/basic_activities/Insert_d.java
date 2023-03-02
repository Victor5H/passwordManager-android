package com.example.passwordmanage.basic_activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.passwordmanage.CustomHelper;
import com.example.passwordmanage.R;
import com.example.passwordmanage.models.Entries_Model;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.OnBalloonClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class Insert_d extends AppCompatActivity {
    EditText name, password, tag;
    Button ins;
    String TAG = "Insert_d";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        findViewById(android.R.id.content).setTransitionName("shared_element_container");
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        window.setSharedElementEnterTransition(new MaterialContainerTransform().addTarget(R.id.insertAct).setDuration(500L));
        window.setSharedElementExitTransition(new MaterialContainerTransform().addTarget(R.id.insertAct).setDuration(500L));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        name = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonName2);
        tag = findViewById(R.id.autoCompleteTextView);
        AtomicBoolean flag = new AtomicBoolean(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tag.setAutofillHints();
        }
        ins = findViewById(R.id.button);
        context = getApplicationContext();
        Entries_Model em = new Entries_Model();
        ins.setOnClickListener(v -> {
            em.setName(name.getText().toString());
            em.setWord(password.getText().toString());
            em.setTag(tag.getText().toString());
            if (em.getName().length() != 0 && em.getWord().length() != 0 && em.getTag().length() != 0) {
                CustomHelper ch = new CustomHelper(Insert_d.this);
                long ret = ch.add(em);
                flag.set(true);
                name.setText("");
                password.setText("");
                tag.setText("");
                Log.d(TAG, "onCreate: inserted at " + ret);
                View parentLayout;
                parentLayout = findViewById(android.R.id.content);
            }
            if (name.getText().length() == 0 && !flag.get()) {
                Balloon balloon = new Balloon.Builder(context)
                        .setArrowSize(10)
                        .setArrowOrientation(ArrowOrientation.LEFT)
                        .setArrowPosition(0.5f)
                        .setWidthRatio(.3f)
                        .setHeight(65)
                        .setTextSize(15f)
                        .setCornerRadius(14f)
                        .setAlpha(0.9f)
                        .setText("Field is mandatory")
                        .setTextColor(ContextCompat.getColor(context, R.color.white))
                        .setTextIsHtml(true)
                        .setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary_variant))
                        .setBalloonAnimation(BalloonAnimation.ELASTIC)
                        .build();
                balloon.showAlignRight(name);
                balloon.setOnBalloonClickListener(new OnBalloonClickListener() {
                    @Override
                    public void onBalloonClick(@NotNull View view) {
                        balloon.dismissWithDelay(11);
                    }
                });
            }
            if (tag.getText().length() == 0 && !flag.get()) {
                Balloon balloon = new Balloon.Builder(context)
                        .setArrowSize(10)
                        .setArrowOrientation(ArrowOrientation.LEFT)
                        .setArrowPosition(0.5f)
                        .setWidthRatio(.3f)
                        .setHeight(65)
                        .setTextSize(15f)
                        .setCornerRadius(14f)
                        .setAlpha(0.9f)
                        .setText("Field is mandatory")
                        .setTextColor(ContextCompat.getColor(context, R.color.white))
                        .setTextIsHtml(true)
                        .setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary_variant))
                        .setBalloonAnimation(BalloonAnimation.ELASTIC)
                        .build();
                balloon.showAlignRight(tag);
                balloon.setOnBalloonClickListener(new OnBalloonClickListener() {
                    @Override
                    public void onBalloonClick(@NotNull View view) {
                        balloon.dismissWithDelay(11);
                    }
                });
            }
            if (password.getText().length() == 0 && !flag.get()) {
                Balloon balloon = new Balloon.Builder(context)
                        .setArrowSize(10)
                        .setArrowOrientation(ArrowOrientation.LEFT)
                        .setArrowPosition(0.5f)
                        .setWidthRatio(.3f)
                        .setHeight(65)
                        .setTextSize(15f)
                        .setCornerRadius(14f)
                        .setAlpha(0.9f)
                        .setText("Field is mandatory")
                        .setTextColor(ContextCompat.getColor(context, R.color.white))
                        .setTextIsHtml(true)
                        .setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary_variant))
                        .setBalloonAnimation(BalloonAnimation.ELASTIC)
                        .build();
                balloon.showAlignRight(password);
                balloon.setOnBalloonClickListener(new OnBalloonClickListener() {
                    @Override
                    public void onBalloonClick(@NotNull View view) {
                        balloon.dismissWithDelay(11);
                    }
                });
            }

        });
    }
}