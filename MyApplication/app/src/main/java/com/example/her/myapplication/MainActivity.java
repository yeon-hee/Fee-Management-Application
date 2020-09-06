package com.example.her.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bRegister, bInfo, bMoney, bUnpaid, bTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 자바에서는 xml이, xml에서는 자바가 서로 연결되어 있음을 확인
        setTitle("2016722035");


        bRegister = (Button) findViewById(R.id.bRegister); // 이 메소드를 통해 xml 요소를 불러온다
        bInfo = (Button) findViewById(R.id.bInfo);
        bMoney = (Button) findViewById(R.id.bMoney);
        bUnpaid = (Button) findViewById(R.id.bUnpaid);
        bTotal = (Button) findViewById(R.id.bTotal);
        bRegister.setOnClickListener(this); // 버튼 클릭 이벤트
        bInfo.setOnClickListener(this);
        bMoney.setOnClickListener(this);
        bUnpaid.setOnClickListener(this);
        bTotal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { // 온 클릭 리스너
        switch (v.getId()){ // 아이디로 구분
            case R.id.bRegister: // id가 bRegister인 버튼 누르면 Register.class로 이동하게 함
                startActivity(new Intent(this, Register.class)); // Go Register activity
                break;

            case R.id.bInfo:
                startActivity(new Intent(this, Information.class)); // Go Information
                break;

            case R.id.bMoney:
                startActivity(new Intent(this, Recieve_money.class)); // Go
                break;

            case R.id.bUnpaid:
                startActivity(new Intent(this,Unpaid.class)); // Go
                break;

            case R.id.bTotal:
                startActivity(new Intent(this,Total.class)); // Go
                break;

        }
    }
}
