package com.example.her.myapplication;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Unpaid extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // 객체선언
    MemberInfoHelper memberInfoHelper;
    SQLiteDatabase db;
    TextView tvInfo, tvInfo_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid);
        setTitle("미납자 명단");

        // 위젯 등록 및 핸들러등록
        memberInfoHelper = new MemberInfoHelper(this);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo_2 = (TextView) findViewById(R.id.tvInfo_2);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.add("01월");
        adapter.add("02월");
        adapter.add("03월");
        adapter.add("04월");
        adapter.add("05월");
        adapter.add("06월");
        adapter.add("07월");
        adapter.add("08월");
        adapter.add("09월");
        adapter.add("10월");
        adapter.add("11월");
        adapter.add("12월");

        //스피너에 어댑터 설정하기
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /* 스피너 이벤트 구현 */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getSelectedItem();
        /* data base 읽어서 월별 미납자 명부 출력하기 */
        try {
            db = memberInfoHelper.getReadableDatabase();
            String[] collums = {"uname", "jan", "feb", "mar", "apr", "may", "jun", "jul", "agu", "sep", "oct", "nov", "dec"};
            Cursor cursor = db.query("member", collums, null, null, null, null, null);
            StringBuffer sb = new StringBuffer();
            int month = Integer.parseInt(item.substring(0, 2));       // 선택된 '월' 정수로 변환
            int month_money = 0;       // 선택된 월에 낸 회비
            int gross_money = 0;       // 월 단위 전체 회비의 합

            switch(month)
            {
                case 1:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(1);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("1월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 2:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(2);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("2월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 3:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(3);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("3월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 4:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(4);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("4월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 5:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(5);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("5월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 6:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(6);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("6월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 7:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(7);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("7월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 8:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(8);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("8월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 9:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(9);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("9월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 10:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(10);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("10월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 11:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(11);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("11월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                case 12:
                    while (cursor.moveToNext()) {
                        String uname = cursor.getString(0);
                        month_money = cursor.getInt(12);
                        gross_money += month_money;
                        if (month_money == 0) sb.append(uname + "\n");   // 미납자들 따로 저장
                    }
                    if(sb.length()==0)  tvInfo.setText("12월 미납자가 없습니다.");
                    else                tvInfo.setText(sb.toString());
                    tvInfo_2.setText("걷은회비 : " + gross_money + "원");
                    break;

                default:
                    break;
            }

            cursor.close();
            memberInfoHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Unpaid.this, "INSERT NG!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}