package br.com.laborumtech.wincomanda;

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
    private static final int VERSAO_BD = 3;
    /** Mantém rastreamento do contexto da aplicação */
    private final Context contexto;

    public ContextoDados(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
        this.contexto = context;
    }

//TODO: insert dados testes
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String[] sql = contexto.getString(R.string.ContextoDados_onCreate).split("\n");
        String[] sql2 = contexto.getString(R.string.DadosTeste).split("\n");
        db.beginTransaction();

        try
        {
            // Cria a tabela e testa os dados
            ExecutarComandosSQL(db, sql);
            db.setTransactionSuccessful();
            Log.d("Database", "Tabela criada");
            ExecutarComandosSQL(db, sql2);
            db.setTransactionSuccessful();
            Log.d("Database", "Dados testes");
        }
        catch (SQLException e)
        {
            Log.e("Erro ao criar dados", e.toString());
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

    /** Retorna um FuncionariosCursor ordenado
     *
     */
    public FuncionariosCursor RetornarFuncionarios(FuncionariosCursor.OrdenarPor ordenarPor)
    {
        String sql = FuncionariosCursor.CONSULTA + (ordenarPor == FuncionariosCursor.OrdenarPor.NomeCrescente ? "ASC" : "DESC");
        SQLiteDatabase bd = getReadableDatabase();
        FuncionariosCursor cc = (FuncionariosCursor) bd.rawQueryWithFactory(new FuncionariosCursor.Factory(), sql, null, null);
        cc.moveToFirst();
        return cc;
    }

    public static class FuncionariosCursor extends SQLiteCursor
    {
        public static enum OrdenarPor{
            NomeCrescente,
            NomeDecrescente
        }

        private static final String CONSULTA = "SELECT Employee.ID, Nome, Codigo FROM Employee ORDER BY Nome ";

        private FuncionariosCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query)
        {
            super(db, driver, editTable, query);
        }

        private static class Factory implements SQLiteDatabase.CursorFactory
        {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query)
            {
                return new FuncionariosCursor(db, driver, editTable, query);
            }
        }

        public long getID()
        {
            return getLong(getColumnIndexOrThrow("Employee.ID"));
        }

        public String getNome()
        {
            return getString(getColumnIndexOrThrow("Nome"));
        }

        public String getCodigo()
        {
            return getString(getColumnIndexOrThrow("Codigo"));
        }
    }




}