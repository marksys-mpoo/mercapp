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

    private static String inserirSupermercadoBD = "INSERT INTO " + BDHelper.TBL_SUPERMERCADO + " (" +
            BDHelper.COLUNA_NOME_SUPERMERCADO +" , " +
            BDHelper.COLUNA_TELEFONE_SUPERMERCADO +" , " +
            BDHelper.COLUNA_LATITUDE_SUPERMERCADO +" , " +
            BDHelper.COLUNA_LONGITUDE_SUPERMERCADO +
            ") VALUES  ";

    public static void carregarUsuariosBD(SQLiteDatabase db){
        carregarUsuario(db, 1, "jose@com.br", "jose", "José Silva", "12345678", "4139857451257454");
        carregarUsuario(db, 2, "ana@com.br", "ana", "Ana Araujo", "812536547", "4539520003155420");
        carregarUsuario(db, 3, "joao@com.br", "joao", "João Jordan", "853625232", "4916405237508789");
        carregarUsuario(db, 4, "maria@com.br", "maria", "Maria Mendonça", "110089542", "5521381109480304");
    }

    private static void carregarUsuario(SQLiteDatabase db, Integer id, String email, String senha, String nome, String fone, String numeroCartao ) {
        String senhaCriogragada = senhaCriptografadaUsuario(senha);
        db.execSQL(inserirUsuarioBD +"('"+id+"', '"+email+"', '"+senhaCriogragada+"');");
        db.execSQL(inserirPessoaBD +"('"+id+"', '"+nome+"', '"+fone+"', '"+numeroCartao+"');");
    }

    private static String senhaCriptografadaUsuario(String senhaUsuario) {
        CriptografiaSenha criptografiaJose =  criptografiaSenha.getInstancia(senhaUsuario);
        String senhaCriptografada = criptografiaJose.getSenhaCriptografada();
        return senhaCriptografada;
    }

    public static void carregarSupermercadosBD(SQLiteDatabase db){
        carregarSupermercado(db, "Extra-Caxangá", "81456456", -8.031318722762279, -34.95355814695358);
        carregarSupermercado(db, "Extra-Graças", "81325362", -8.040822755006179, -34.904181361198425);
        carregarSupermercado(db, "Extra-DoisIrmãos", "813201234", -8.017785683292104, -34.942001178860664);
        carregarSupermercado(db, "Extra-Várzea", "8132710088", -8.04335807767294, -34.95855409651994);
    }

    private static void carregarSupermercado(SQLiteDatabase db, String nome, String fone, Double latitude, Double longitude) {
        db.execSQL(inserirSupermercadoBD +"('"+nome+"', '"+fone+"',  '"+latitude+"', '"+longitude+"');");
    }


    public static void carregarProdutosBD(SQLiteDatabase db){
        carregarProduto(db, "Extra-Caxangá", "81456456", -8.031318722762279, -34.95355814695358);
        carregarProduto(db, "Extra-Graças", "81325362", -8.040822755006179, -34.904181361198425);
        carregarProduto(db, "Extra-DoisIrmãos", "813201234", -8.017785683292104, -34.942001178860664);
        carregarProduto(db, "Extra-Várzea", "8132710088", -8.04335807767294, -34.95855409651994);
    }

    private static void carregarProduto(SQLiteDatabase db, String nome, String fone, Double latitude, Double longitude) {
        db.execSQL(inserirSupermercadoBD +"('"+nome+"', '"+fone+"',  '"+latitude+"', '"+longitude+"');");
    }

}
