package com.mercapp.infra.persistencia;

import android.database.sqlite.SQLiteDatabase;

import com.mercapp.usuario.gui.CriptografiaSenha;

public class CarregamentoDadosBD {

    private static CriptografiaSenha criptografiaSenha;

    private static String inserirUsuarioBD = "INSERT INTO " + BDHelper.TBL_USUARIO + " (" +
            BDHelper.COLUNA_ID +" , " +
            BDHelper.COLUNA_EMAIL +" , " +
            BDHelper.COLUNA_SENHA +
            ") VALUES  ";

    private static String inserirPessoaBD = "INSERT INTO " + BDHelper.TBL_PESSOA + " (" +
            BDHelper.COLUNA_ID_USUARIO_PESSOA +" , " +
            BDHelper.COLUNA_NOME +" , " +
            BDHelper.COLUNA_TELEFONE +" , " +
            BDHelper.COLUNA_NUMEROCARTAO +
            ") VALUES  ";


    public static void carregaUsuarios(SQLiteDatabase db){

        String senhaCriogragadaJose = senhaCriptografadaUsuario("jose");
        String senhaCriogragadaAna = senhaCriptografadaUsuario("ana");
        String senhaCriogragadaJoao = senhaCriptografadaUsuario("joao");
        String senhaCriogragadaMaria = senhaCriptografadaUsuario("maria");

        db.execSQL(inserirUsuarioBD +"(1, 'jose@com.br', '"+senhaCriogragadaJose+"');");
        db.execSQL(inserirPessoaBD +"(1, 'José Silva', '12345678', '4139857451257454');");

        db.execSQL(inserirUsuarioBD +"(2, 'ana@com.br', '"+senhaCriogragadaAna+"');");
        db.execSQL(inserirPessoaBD +"(2, 'Ana Silva', '812536547', '4539520003155420');");

        db.execSQL(inserirUsuarioBD +"(3, 'joao@com.br', '"+senhaCriogragadaJoao+"');");
        db.execSQL(inserirPessoaBD +"(3, 'João Silva', '853625232', '4916405237508789');");

        db.execSQL(inserirUsuarioBD +"(4, 'maria@com.br', '"+senhaCriogragadaMaria+"');");
        db.execSQL(inserirPessoaBD +"(4, 'Maria Silva', '110089542', '5521381109480304');");

    }

    private static String senhaCriptografadaUsuario(String senhaUsuario) {
        CriptografiaSenha criptografiaJose =  criptografiaSenha.getInstancia(senhaUsuario);
        String senhaCriptografada = criptografiaJose.getSenhaCriptografada();
        return senhaCriptografada;
    }

}
