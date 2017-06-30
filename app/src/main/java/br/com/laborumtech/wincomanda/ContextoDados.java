package br.com.laborumtech.wincomanda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

/**
 * Created by rodrigo on 30/06/17.
 */

public class ContextoDados extends SQLiteOpenHelper{

    /** O nome do arquivo de base de dados*/
    private static final String NOME_BD = "Comanda";
    /** A versão da base de dados */
    private static final int VERSAO_BD = 1;
    /** Mantém rastreamento do contexto da aplicação */
    private final Context contexto;

    public ContextoDados(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        this.contexto = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String[] sql = contexto.getString(R.string.ContextoDados_onCreate).split("\n");
        db.beginTransaction();

        try
        {
            // Cria a tabela e testa os dados
            ExecutarComandosSQL(db, sql);
            db.setTransactionSuccessful();
        }
        catch (SQLException e)
        {
            Log.e("Erro ao criar as tabelas e testar os dados", e.toString());
        }
        finally
        {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Isto é apenas didático, não precisamos deste método nesse post
    }

    /**
     * Executa todos os comandos SQL passados no vetor String[]
     * @param db A base de dados onde os comandos serão executados
     * @param sql Um vetor de comandos SQL a serem executados
     */
    private void ExecutarComandosSQL(SQLiteDatabase db, String[] sql)
    {
        for( String s : sql )
            if (s.trim().length()>0)
                db.execSQL(s);
    }
}