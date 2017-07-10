package br.com.laborumtech.wincomanda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button botaoInicial = (Button) findViewById(R.id.novo_pedido);

        botaoInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentvaiparalogin = new Intent(MainActivity.this, PedidoActivity.class);
                startActivity(intentvaiparalogin);

            }



        });
        CarregarInterfaceListagem();
        Log.d("Interface", "Listagem criada");
        VerificaFuncionario();
    }

    private void VerificaFuncionario() {
        TextView exibeFuncionario = new TextView(this);
        exibeFuncionario=(TextView)findViewById(R.id.funcionario_logado);
        if (1 == 1){
        }else{exibeFuncionario.setText("Selecione o funcionário:");
        }
    }
    public void LoginFuncionario(View view){
        Intent intentvaiparalogin = new Intent(MainActivity.this, PedidoActivity.class);
        startActivity(intentvaiparalogin);
    }

    public void CarregarInterfaceListagem()
    {
        setContentView(R.layout.activity_main);

        CarregarLista(this);
    }
    public void CarregarLista(Context c)
    {
        ContextoDados db = new ContextoDados(c);
        ContextoDados.FuncionariosCursor cursor = db.RetornarFuncionarios(ContextoDados.FuncionariosCursor.OrdenarPor.NomeCrescente);

        for( int i=0; i < cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            ImprimirLinha(cursor.getNome(), cursor.getCodigo());
        }
    }

    public void ImprimirLinha(String nome, String telefone)
    {
        TextView tv = (TextView)findViewById(R.id.listaContatos);

        if(tv.getText().toString().equalsIgnoreCase("Nenhum contato cadastrado."))
            tv.setText("");

        tv.setText(tv.getText() + "\r\n" + nome + " - " + telefone);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intentvaiparaconfig = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intentvaiparaconfig);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}