package br.com.laborumtech.wincomanda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
    }
   // EditText campoCodigoProduto = (EditText) findViewById(R.id.campoCodigoProduto);

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER)
        {
            Log.d("Keycode", "Got KeyCode 9");
            return super.onKeyDown(keyCode, event);
        }

        return true;
    } */
   @Override
   public boolean onKeyDown(int keyCode, KeyEvent event)
   {
       if(keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER)
       {
           Log.d("Keycode", "Got KeyCode 9");
           return true;
       }

       return super.onKeyDown(keyCode, event);
   }
}
