package com.example.yuuki.health;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.math.BigDecimal;

public class MainActivity extends ActionBarActivity {

    public static final BigDecimal TEI_TAIJYU = new BigDecimal("18.5") ;
    public static final BigDecimal FTUU_TAIJYU = new BigDecimal("25") ;
    @Override

    // この中にボタンのイベントドリブンを書く
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_my);　変更必須
        setContentView(R.layout.activity_main);
        EditText txtSintyou  = (EditText) findViewById(R.id.txtSintyou);
        EditText txtTaijyu  = (EditText) findViewById(R.id.texttaijyuu);
        EditText txtBmi  = (EditText) findViewById(R.id.txtBmi);
        EditText txtHyujyun  = (EditText) findViewById(R.id.txtHyujyunTaijyu);

        // buttonを取得
        //クリアボタン情報取得
        Button btn = (Button) findViewById(R.id.btClear);
        //計算ボタン情報取得
        Button btn2 = (Button) findViewById(R.id.btcalc);

//クリアボタンイベント
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btclear();
            }
        });

        //計算ボタンイベント
        btn2.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                EditText txtSintyou  = (EditText) findViewById(R.id.txtSintyou);
                EditText txtTaijyu  = (EditText) findViewById(R.id.texttaijyuu);

                if (txtSintyou.getText().equals("") ){
                    showDialog("身長");
                    return;
                }

                if (txtTaijyu.getText().equals("") ){
                    showDialog("体重");
                    return;
                }

                btCaluc(txtSintyou.getText().toString(),txtTaijyu.getText().toString());

            }
        });
    //クリアボタン処理
    public void btclear(){
        EditText txtSintyou  = (EditText) findViewById(R.id.txtSintyou);
        EditText txtTaijyu  = (EditText) findViewById(R.id.texttaijyuu);
        EditText txtBmi  = (EditText) findViewById(R.id.txtBmi);
        EditText txtHyujyun  = (EditText) findViewById(R.id.txtHyujyunTaijyu);
        TextView msg  = (TextView) findViewById(R.id.viewMsg);


        txtSintyou.setText("19,5");
        txtTaijyu.setText("");
        txtBmi.setText("");
        txtHyujyun.setText("");
        msg.setText("");


    }

//計算ボタン処理
     public void btCaluc(String strShintyou,String strTaijyu){
         BigDecimal  decshintyou =  new BigDecimal(strShintyou);
         BigDecimal  dectaijyu =  new BigDecimal(strTaijyu);
         BigDecimal decbmi = new BigDecimal("0.0");
         BigDecimal dechyoujyu = new BigDecimal("0.0");
         String strmsg = "";
         EditText txtBmi  = (EditText) findViewById(R.id.txtBmi);
         EditText txtHyujyun  = (EditText) findViewById(R.id.txtHyujyunTaijyu);
         TextView msg  = (TextView) findViewById(R.id.viewMsg);


         decbmi = dectaijyu.divide(decshintyou.multiply(decshintyou),2,BigDecimal.ROUND_HALF_DOWN);
         dechyoujyu = decbmi.multiply(decshintyou.multiply(decshintyou));
         if (decbmi.compareTo(TEI_TAIJYU) < 0){
            strmsg = "痩せ気味";
         }else if ((decbmi.compareTo(TEI_TAIJYU) >= 0) && (decbmi.compareTo(FTUU_TAIJYU) < 0)){
             strmsg = "痩せ気味";
         }else {
            strmsg = "肥満";
          }

     txtBmi.setText(decbmi.toString());
     txtHyujyun.setText(dechyoujyu.toString());
     msg.setText(strmsg);





 }

    public  void showDialog(String Msg) {


        // 確認ダイアログの生成
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("入力エラー");
        alertDlg.setMessage(Msg + "入力されていません。");

    }
}

