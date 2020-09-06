package com.example.her.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Information extends AppCompatActivity implements ListView.OnItemClickListener{
    // instance variable
    SQLiteDatabase db;
    MemberInfoHelper memberInfoHelper;
    ContentValues values;
    String Selected_name;     // Selected user name of list
    // method
    @Override
    /* 초기 위젯 등록 및 설정 */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setTitle("회원정보");
        memberInfoHelper = new MemberInfoHelper(this);          // Data base 읽기 위해
        // 위 객체 등록
        ListView lvList = (ListView) findViewById(R.id.lvList); // ListView 객체 등록
        // List에 등록할 data를 db에서 불러온다.
        try {

            db = memberInfoHelper.getReadableDatabase();

            String[] collums = {"uname", "uid", "umoney"};
            Cursor cursor = db.query("member", collums, null, null, null, null, null);

            ArrayList<String> names = new ArrayList<>();   // 리스트에 출력할 이름들

            while(cursor.moveToNext()){
                names.add(cursor.getString(0));
            }
            // 어댑터를 준비 - 리스트 객체 안에 저장된 데이터들을 우리가 볼 수 있게
            // ListView로 뿌려주는 역할
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, names);

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
            Toast.makeText(Information.this, "Information NG!", Toast.LENGTH_SHORT).show();
        }
    }
    // 이름 선택 되었을 경우 리스너
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Selected_name = parent.getItemAtPosition(position).toString();
        Intent intent = new Intent(getApplicationContext(), Privacy.class);
        intent.putExtra("Name", Selected_name);

        startActivity(intent);
    }
}

