package br.com.laborumtech.wincomanda;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
   // EditText campoLogin = (EditText) findViewById(R.id.campoLogin);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER)
        {
            Log.d("Keycode", "Got KeyCode 9");
            return super.onKeyDown(keyCode, event);
        }

        return true;
    }
}
