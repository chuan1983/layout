package org.iii.tw.layouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View guess;
    private EditText input;
    private TextView info;
    private String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.write);         //因為guess的button的爸爸是view 所以用view可以解釋動作
        info = (TextView) findViewById(R.id.infoVew);       //而input 和 info爸爸不是view 所以要強制轉型
        answer = createAnswer(3);
        guess = findViewById(R.id.guess);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGuess();
            }
        });
    }
    public void doGuess(){
        String strInput = input.getText().toString();
        String result = checkAB(answer,strInput);
        //info.setText(strInput+":"+result);        //這邊每次猜只show一次
        info.append(strInput+":"+result+"\n");     //這邊猜會疊上去  \n換行
        input.setText("");
    }
    static String createAnswer(int n){
        // 洗牌
        int[] poker = new int[n];
        for (int i=0; i<poker.length; i++){
            int temp;
            // 檢查機制
            boolean isRepeat;
            do {
                temp = (int)(Math.random()*10);
                isRepeat = false;
                for (int j=0; j<i; j++){
                    if (temp == poker[j]){
                        isRepeat = true;
                        break;
                    }
                }
            }while(isRepeat);
            poker[i] = temp;
        }
        String ret = "";
        for (int p : poker)ret += p;

        return ret;
    }
    static String checkAB(String a, String g){
        int A, B; A = B = 0;
        for (int i=0; i<g.length(); i++){
            if (g.charAt(i) == a.charAt(i)){
                A++;
            }else if (a.indexOf(g.charAt(i))!=-1){
                B++;
            }
        }
        return A + "A" + B + "B";
    }
}
