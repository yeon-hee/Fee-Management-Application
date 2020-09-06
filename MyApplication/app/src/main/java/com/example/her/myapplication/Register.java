package com.example.her.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{
    // 위젯 객체 선언
    final static String TAG = "Register";

    MemberInfoHelper memberInfoHelper;

    Button bRegister, bInfo, bDel;
    EditText etName, etId;
    TextView tvInfo;
    SQLiteDatabase db;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // 이 xml과 연결
        setTitle("회원등록");
        // 위젯 객체와 연결
        memberInfoHelper = new MemberInfoHelper(this);
        etName = (EditText) findViewById(R.id.etName);
        etId = (EditText) findViewById(R.id.etId);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        bRegister = (Button) findViewById(R.id.bRegister);
        bInfo = (Button) findViewById(R.id.bInfo);
        bDel = (Button) findViewById(R.id.bDel);
        // 버튼 리스너 등록
        bRegister.setOnClickListener(this);
        bInfo.setOnClickListener(this);
        bDel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){ // 각각 버튼에 따른 분기
            case R.id.bRegister: // 회원 등록
                try {
                    db = memberInfoHelper.getWritableDatabase(); // getWritableDatabase을 통해 SQLiteDatabase 객체 가져온다
                    values = new ContentValues(); // 객체 생성
                    // put 메소드를 사용해서 DB 테이블 순서에 맞게 집어 넣는다
                    values.put("uname", etName.getText().toString());
                    values.put("uid", etId.getText().toString());

                    db.insert("member", null, values);
                    // ContentValues 객체를 insert() 메서드에 전달하여 데이터를 데이터베이스에 삽입
                    memberInfoHelper.close();
                    Toast.makeText(Register.this, "등록이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    // 메세지 뿌려주기
                    finish();      // activity 종료  홈화면으로 전환
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this, "오류", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bInfo: // 회원 정보

                try {
                    db = memberInfoHelper.getReadableDatabase();

                    String[] collums = {"uname", "uid", "umoney", "jan"};
                    Cursor cursor = db.query("member", collums, null, null, null, null, null);

                    StringBuffer sb = new StringBuffer();
                    while(cursor.moveToNext()){
                        String uname = cursor.getString(0);
                        String uid = cursor.getString(1);
                        int umoney = cursor.getInt(2);
                        int jan = cursor.getInt(3);


                        sb.append("uname : " + uname).append(", uid : " + uid).append(", jan : " + jan).append(", money : " + umoney + "\n");
                    }
                    tvInfo.setText(sb.toString());

                    cursor.close();
                    memberInfoHelper.close();
                    Toast.makeText(Register.this, "INFO OK!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this, "INFO NG!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bDel: // 초기화
                try {
                    db = memberInfoHelper.getWritableDatabase();

                    db.delete("member", null, null);

//                  String query = "DELETE FROM table;";
//                  db.execSQL(query);

                    memberInfoHelper.close();
                    Toast.makeText(Register.this, "모든 정보가 삭제되었습니다", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this, "DELETE 오류", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}