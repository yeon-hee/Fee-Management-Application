package com.example.her.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Total extends AppCompatActivity {
    // widget 등록
    MemberInfoHelper memberInfoHelper;
    SQLiteDatabase db;
    TextView[] tvs = new TextView[14];       // text view array 생성
    Integer[] tvIDs = {0, R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6,
            R.id.tv_7, R.id.tv_8, R.id.tv_9, R.id.tv_10, R.id.tv_11, R.id.tv_12, R.id.tv_13};
    int i;
    int total_1, total_2, total_3, total_4, total_5, total_6, total_7, total_8, total_9, total_10, total_11, total_12;
    int gross_total = 0;       // 월 단위 전체 회비의 합

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        setTitle("회비합계");

        // 위젯 연결
        memberInfoHelper = new MemberInfoHelper(this);
        for (i = 1; i < tvIDs.length; i++) {
            tvs[i] = (TextView) findViewById(tvIDs[i]);
        }

        // 월별 합계 계산하기
        try {
            db = memberInfoHelper.getReadableDatabase();
            String[] collums = {"uname", "jan", "feb", "mar", "apr", "may", "jun", "jul", "agu", "sep", "oct", "nov", "dec"};
            Cursor cursor = db.query("member", collums, null, null, null, null, null);

            while (cursor.moveToNext()) {

                total_1 += cursor.getInt(1);
                total_2 += cursor.getInt(2);
                total_3 += cursor.getInt(3);
                total_4 += cursor.getInt(4);
                total_5 += cursor.getInt(5);
                total_6 += cursor.getInt(6);
                total_7 += cursor.getInt(7);
                total_8 += cursor.getInt(8);
                total_9 += cursor.getInt(9);
                total_10 += cursor.getInt(10);
                total_11 += cursor.getInt(11);
                total_12 += cursor.getInt(12);
            }
            gross_total = total_1 + total_2 + total_3 + total_4 + total_5 + total_6 + total_7
                    + total_8 + total_9 + total_10 + total_11 + total_12;

            // text view에 출력하기
            tvs[1].setText(total_1 + "원");
            tvs[2].setText(total_2 + "원");
            tvs[3].setText(total_3 + "원");
            tvs[4].setText(total_4 + "원");
            tvs[5].setText(total_5 + "원");
            tvs[6].setText(total_6 + "원");
            tvs[7].setText(total_7 + "원");
            tvs[8].setText(total_8 + "원");
            tvs[9].setText(total_9 + "원");
            tvs[10].setText(total_10 + "원");
            tvs[11].setText(total_11 + "원");
            tvs[12].setText(total_12 + "원");
            tvs[13].setText(gross_total + "원");

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Total.this, "INSERT NG!", Toast.LENGTH_SHORT).show();
        }
    } // onCreate


}
