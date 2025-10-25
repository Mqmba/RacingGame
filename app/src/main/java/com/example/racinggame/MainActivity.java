package com.example.racinggame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvPoint;
    EditText tvPointSelect;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;
    ImageButton ibtnPlay;
    int point = 100;
    int bet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.main);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        AnhXa();
        sbOne.setEnabled(false);
        sbTwo.setEnabled(false);
        sbThree.setEnabled(false);

        tvPoint.setText(point + "");



        // 60000ms: tổng thời gian đếm ngược
        // 300ms: thời gian lặp lại 1 hành động nào đó
        CountDownTimer countDownTimer = new CountDownTimer(60000,200) {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                int one = random.nextInt(5);
                int two = random.nextInt(5);
                int three = random.nextInt(5);

                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);

                //Kiểm tra WIN
                if (sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_LONG).show();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    // Kiểm tra đặt cược
                    if (cbOne.isChecked()) {
                        point += bet;
                        Toast.makeText(MainActivity.this, "Chính xác", Toast.LENGTH_LONG).show();
                    } else {
                        point -= bet;
                        Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_LONG).show();
                    }
                    tvPoint.setText(point + "");
                    EnableCheckBox();
                }
                if (sbTwo.getProgress() >= sbTwo.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_LONG).show();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    // Kiểm tra đặt cược
                    if (cbTwo.isChecked()) {
                        point += bet;
                        Toast.makeText(MainActivity.this, "Chính xác", Toast.LENGTH_LONG).show();
                    } else {
                        point -= bet;
                        Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_LONG).show();
                    }
                    tvPoint.setText(point + "");
                    EnableCheckBox();
                }
                if (sbThree.getProgress() >= sbThree.getMax()) {
                    this.cancel();
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_LONG).show();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    // Kiểm tra đặt cược
                    if (cbThree.isChecked()) {
                        point += bet;
                        Toast.makeText(MainActivity.this, "Chính xác", Toast.LENGTH_LONG).show();
                    } else {
                        point -= bet;
                        Toast.makeText(MainActivity.this, "Sai rồi", Toast.LENGTH_LONG).show();
                    }
                    tvPoint.setText(point + "");
                    EnableCheckBox();
                }
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Thêm chức năng check chọn điểm cược
                String check = tvPointSelect.getText().toString();
                if (TextUtils.isEmpty(check)) {
                    Toast.makeText(MainActivity.this, "Bet your point to play", Toast.LENGTH_SHORT).show();
                    ibtnPlay.setVisibility(View.VISIBLE);
                } else {
                    bet = Integer.parseInt(check);
                    if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                        // Trả về tiến trình 0
                        sbOne.setProgress(0);
                        sbTwo.setProgress(0);
                        sbThree.setProgress(0);

                        // Ẩn sau khi start
                        ibtnPlay.setVisibility(View.INVISIBLE);
                        countDownTimer.start();

                        DisableCheckBox();
                    } else {
                        Toast.makeText(MainActivity.this, "Vui lòng chọn 1 trong 3", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Bỏ check 2, 3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Bỏ check 1, 3
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Bỏ check 1, 2
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });


    }

    private void EnableCheckBox() {
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private void DisableCheckBox() {
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    public void AnhXa() {
        tvPoint = (TextView) findViewById(R.id.textViewpoint);
        cbOne = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);
        sbOne = (SeekBar) findViewById(R.id.seekbarOne);
        sbTwo = (SeekBar) findViewById(R.id.seekbarTwo);
        sbThree = (SeekBar) findViewById(R.id.seekbarThree);
        ibtnPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        tvPointSelect = (EditText) findViewById(R.id.textViewPointSelect);
    }
}