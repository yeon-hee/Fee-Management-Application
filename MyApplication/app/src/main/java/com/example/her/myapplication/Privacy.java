package com.example.her.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Privacy extends AppCompatActivity implements View.OnClickListener{
    // wiget
    TextView tvName;
    MemberInfoHelper memberInfoHelper;
    SQLiteDatabase db;
    TextView[] tvs = new TextView[14];       // text view array 생성
    Integer[] tvIDs = {0, R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6,
            R.id.tv_7, R.id.tv_8, R.id.tv_9, R.id.tv_10, R.id.tv_11, R.id.tv_12, R.id.tv_13};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        setTitle("회원정보");
        // Information class로부터 data 받아오기
        Intent intent = getIntent();
        String Selected_name = intent.getStringExtra("Name");

        // wiget 등록 및 핸들러 등록

        memberInfoHelper = new MemberInfoHelper(this);
        tvName = (TextView)findViewById(R.id.tvName);
        for (i = 1; i < tvIDs.length; i++) {
            tvs[i] = (TextView) findViewById(tvIDs[i]);
        }

        // 누구에 관한 정보인지 출력
        tvName.setText(Selected_name);

        try {
            db = memberInfoHelper.getReadableDatabase();
            String[] collums = {"uname", "jan", "feb", "mar", "apr", "may", "jun", "jul", "agu", "sep", "oct", "nov", "dec", "umoney"};
            Cursor cursor = db.query("member", collums, null, null, null, null, null);

            while (cursor.moveToNext()) {
                String name = cursor.getString(0);           // db에서 이름 얻어오기

                if(Selected_name.equals(name)){              // db에서 얻어온 정보가 선택된 사람에 대한 정보라면
                    // text view에 출력하기
                    tvs[1].setText(cursor.getInt(1) + "원");
                    tvs[2].setText(cursor.getInt(2) + "원");
                    tvs[3].setText(cursor.getInt(3) + "원");
                    tvs[4].setText(cursor.getInt(4) + "원");
                    tvs[5].setText(cursor.getInt(5) + "원");
                    tvs[6].setText(cursor.getInt(6) + "원");
                    tvs[7].setText(cursor.getInt(7) + "원");
                    tvs[8].setText(cursor.getInt(8) + "원");
                    tvs[9].setText(cursor.getInt(9) + "원");
                    tvs[10].setText(cursor.getInt(10) + "원");
                    tvs[11].setText(cursor.getInt(11) + "원");
                    tvs[12].setText(cursor.getInt(12) + "원");
                    tvs[13].setText(cursor.getInt(13) + "원");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Privacy.this, "NG", Toast.LENGTH_SHORT).show();
        }
    } // onCreat

    @Override
    public void onClick(View v) {
        finish();
    }
}
