package com.example.her.myapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recieve_money extends AppCompatActivity implements ListView.OnItemClickListener {
    // instance variable
    int total_money;         // 한 사람이 지금까지 낸 총 금액
    SQLiteDatabase db;
    MemberInfoHelper memberInfoHelper;
    ContentValues values;
    ListView lvList;
    EditText etmoney, etMonth;
    TextView tvAmount, tvMonth;
    Button bmoney;
    String Selected_name;     // Selected user name of list

    // method
    @Override
    // 초기 위젯 등록 및 설정
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_money);
        setTitle("회비기입");
        // 위젯 등록
        memberInfoHelper = new MemberInfoHelper(this);      // Database에 쓰기 위해
        lvList = (ListView) findViewById(R.id.lvList);      // ListView  위젯
        tvAmount = (TextView) findViewById(R.id.tvAmount);  // 금액 Textview
        tvMonth = (TextView) findViewById(R.id.tvMonth);    // 월 textview
        etmoney = (EditText) findViewById(R.id.etmoney);    // 금액입력란 EditText
        etMonth = (EditText) findViewById(R.id.etMonth);    // 월 edit text
        bmoney = (Button) findViewById(R.id.bmoney);        // 기입 버튼  Button
        tvAmount.setVisibility(View.INVISIBLE);             // 처음에 안보이게 설정
        tvMonth.setVisibility(View.INVISIBLE);
        etMonth.setVisibility(View.INVISIBLE);
        etmoney.setVisibility(View.INVISIBLE);
        bmoney.setVisibility(View.INVISIBLE);               // 처음에 안보이게 설정
        /* 기입 버튼 리스너 등록 */
        bmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 사람당 회비 지출 합계 먼저 저장 하기 위해서 */
                try {
                    db = memberInfoHelper.getReadableDatabase();

                    String[] collums = {"uname", "umoney"};
                    Cursor cursor = db.query("member", collums, null, null, null, null, null);

                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        int umoney = cursor.getInt(1);

                        if (uname.equals(Selected_name))  // 이 사람에 대해서 total 금액 최신화시킬것임
                        {
                            total_money = umoney;    // 지금까지 낸 total_money 받아오기
                            break;
                        }
                    }
                    cursor.close();
                    memberInfoHelper.close();
                    finish();         // activity 끝내기
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Recieve_money.this, "NG!", Toast.LENGTH_SHORT).show();
                }
                /* 회비 본격적으로 기입하기 Database update 하기 */
                try {
                    db = memberInfoHelper.getWritableDatabase();
                    values = new ContentValues();          // db에 insert 하기 위해
                    int cash = Integer.parseInt(etmoney.getText().toString());  // 입력받은 금액 정수형으로 변환
                    int month = Integer.parseInt(etMonth.getText().toString()); // 입력받은 월 정수형으로 변환
                    if (month == 1) values.put("jan", cash);
                    else if (month == 2) values.put("feb", cash);
                    else if (month == 3) values.put("mar", cash);
                    else if (month == 4) values.put("apr", cash);
                    else if (month == 5) values.put("may", cash);
                    else if (month == 6) values.put("jun", cash);
                    else if (month == 7) values.put("jul", cash);
                    else if (month == 8) values.put("agu", cash);
                    else if (month == 9) values.put("sep", cash);
                    else if (month == 10) values.put("oct", cash);
                    else if (month == 11) values.put("nov", cash);
                    else if (month == 12) values.put("dec", cash);
                    else;
                    total_money += cash;      // total money를 최신화 시키기
                    values.put("umoney", total_money);
                    String[] params = new String[]{""};
                    params[0] = Selected_name;
                    db.update("member", values, "uname=?", params);

                    memberInfoHelper.close();

                    Toast.makeText(Recieve_money.this, "입력 되었습니다.", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Recieve_money.this, "다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*  List 보이게 할 사용자 이름 Database 에서 불러오기 */
        try {

            db = memberInfoHelper.getReadableDatabase();

            String[] collums = {"uname", "uid", "umoney"};
            Cursor cursor = db.query("member", collums, null, null, null, null, null);

            ArrayList<String> names = new ArrayList<>();

            while (cursor.moveToNext()) {
                names.add(cursor.getString(0));
            }
            // 어댑터를 준비
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, names);

            // 어댑터 연결
            lvList.setAdapter(adapter);

            // 선택모드 추가
            lvList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            // 리스너 등록
            lvList.setOnItemClickListener(this);

            // 포커스가 없도록 설정
            lvList.setItemsCanFocus(false);

            cursor.close();
            memberInfoHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Recieve_money.this, "다시 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }

    /* 이름 선택되었을 경우 리스너 */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tvAmount.setVisibility(View.VISIBLE);            // 숨어있던 위젯들 화면에 보이게 설정
        tvMonth.setVisibility(View.VISIBLE);
        bmoney.setVisibility(View.VISIBLE);
        etmoney.setVisibility(View.VISIBLE);
        etMonth.setVisibility(View.VISIBLE);

        Selected_name = parent.getItemAtPosition(position).toString();
        // 클릭된 아이템의 포지션을 이용해 스트링 레이어에서 아이템을 꺼내온다.
        Toast.makeText(Recieve_money.this, "금액을 입력해주세요", Toast.LENGTH_SHORT).show();
    }
}
