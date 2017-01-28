package com.mercapp.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.mercapp.usuario.gui.CriptografiaSenha;

import junit.framework.Assert;

public class CarregamentoDadosBD extends AppCompatActivity {

    private static CriptografiaSenha criptografiaSenha;

    private final Context context = getApplicationContext();

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

    private static String inserirProdutosBD = "INSERT INTO " + BDHelper.TBL_PRODUTO + " (" +
            BDHelper.COLUNA_NOME_PRODUTO +" , " +
            BDHelper.COLUNA_DESCRICAO_PRODUTO +" , " +
            BDHelper.COLUNA_PRECO_PRODUTO +" , " +
            BDHelper.COLUNA_IMAGEM_PRODUTO +" , " +
            BDHelper.COLUNA_ID_SUPERMERCADO_PRODUTO +" , " +
            BDHelper.COLUNA_PRODUTO_DEPARTAMENTO +" , " +
            BDHelper.COLUNA_POSICAO_SPINNER_SUPERMERCADO +" , " +
            BDHelper.COLUNA_POSICAO_SPINNER_IMAGEM_PRODUTO +
            ") VALUES  ";

    public static void carregarUsuariosBD(SQLiteDatabase db){
        carregarUsuario(db, 1, "jose@com.br", "jose", "José Silva", "12345678", "4139857451257454");
        carregarUsuario(db, 2, "ana@com.br", "ana", "Ana Araújo", "812536547", "4539520003155420");
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

    /*
    // ========== Departamentos ==============
    0- Padaria, 1- Frios, 2- Açougue, 3- Frutas, 4- Bebidas, 5- Mercearia, 6- Higiene, 7- Limpeza, 8- Bazar //Departamentos

    ========================== Códigos Imagens ===========================
    0: img_arroz = 2130837844
    1: img_creme_pele = 2130837845
    2: img_leite = 2130837846
    3: img_maca = 2130837847
    4: img_pao = 2130837848
    5: img_pizza = 2130837849
    6: img_refrigerante = 2130837850
    7: img_sabonete = 2130837851
    8: img_shampoo = 2130837852

    // ============ Sequência dos atributos para cadastrar os produtos ================
    nome, descrição, preço, nomeImagem, idSupermercado, nDepartemento, posiçãoImagem
    */
    public static void carregarProdutosBD(SQLiteDatabase db){
        // Produtos Supermercado1
        carregarProduto(db, "arroz", "integral", 4.50, "img_arroz", 1, 5, 0);
        carregarProduto(db, "coca-cola", "lata", 3.20, "img_refrigerante", 1, 4, 6);
        carregarProduto(db, "shampoo", "neutro", 8.10, "img_shampoo", 1, 6, 8);
        carregarProduto(db, "sabonete", "líquido", 9.30, "img_sabonete", 1, 6, 7);
        carregarProduto(db, "leite", "garrafa", 3.90, "img_leite", 1, 1, 2);
        carregarProduto(db, "pão", "forma", 4.60, "img_pao", 1, 0, 4);
        carregarProduto(db, "maçã", "verde", 2.00, "img_maca", 1, 3, 3);
        carregarProduto(db, "creme", "pele", 5.80, "img_creme_pele", 1, 6, 1);
        carregarProduto(db, "pizza", "calabresa", 12.99, "img_pizza", 1, 0, 5);
        // Produtos Supermercado2
        carregarProduto(db, "arroz", "branco", 4.80, "img_arroz", 2, 5, 0);
        carregarProduto(db, "guaraná", "lata", 3.55, "img_refrigerante", 2, 4, 6);
        carregarProduto(db, "shampoo", "neutro", 9.08, "img_shampoo", 2, 6, 8);
        carregarProduto(db, "sabonete", "líquido", 10.22, "img_sabonete", 2, 6, 7);
        carregarProduto(db, "leite", "garrafa", 4.20, "img_leite", 2, 1, 2);
        carregarProduto(db, "pão", "forma", 4.90, "img_pao", 2, 0, 4);
        carregarProduto(db, "maçã", "nacional", 2.60, "img_maca", 2, 3, 3);
        carregarProduto(db, "creme", "desodorante", 4.90, "img_creme_pele", 2, 6, 1);
        carregarProduto(db, "pizza", "muzzarella", 15.60, "img_pizza", 2, 0, 5);
        // Produtos Supermercado3
        carregarProduto(db, "arroz", "parbonizado", 4.20, "img_arroz", 3, 5, 0);
        carregarProduto(db, "soda", "lata", 3.10, "img_refrigerante", 3, 4, 6);
        carregarProduto(db, "shampoo", "condicionador", 8.00, "img_shampoo", 3, 6, 8);
        carregarProduto(db, "sabonete", "líquido", 9.15, "img_sabonete", 3, 6, 7);
        carregarProduto(db, "leite", "garrafa", 3.50, "img_leite", 3, 1, 2);
        carregarProduto(db, "pão", "forma light", 4.40, "img_pao", 3, 0, 4);
        carregarProduto(db, "maçã", "importada", 2.20, "img_maca", 3, 3, 3);
        carregarProduto(db, "creme", "cabelo", 4.80, "img_creme_pele", 3, 6, 1);
        carregarProduto(db, "pizza", "bacon", 11.59, "img_pizza", 3, 0, 5);
        // Produtos Supermercado4
        carregarProduto(db, "arroz", "integral", 4.44, "img_arroz", 4, 5, 0);
        carregarProduto(db, "pepsi", "lata", 3.26, "img_refrigerante", 4, 4, 6);
        carregarProduto(db, "shampoo", "hidratante", 8.02, "img_shampoo", 4, 6, 8);
        carregarProduto(db, "sabonete", "líquido neutro", 9.06, "img_sabonete", 4, 6, 7);
        carregarProduto(db, "leite", "garrafa sem lactose", 3.77, "img_leite",4 , 1, 2);
        carregarProduto(db, "pão", "forma integral", 4.12, "img_pao", 4, 0, 4);
        carregarProduto(db, "maçã", "vermelha", 2.14, "img_maca", 4, 3, 3);
        carregarProduto(db, "creme", "para os mãos", 5.36, "img_creme_pele", 4, 6, 1);
        carregarProduto(db, "pizza", "chocolate", 12.29, "img_pizza", 4, 0, 5);
    }

    private static void carregarProduto(SQLiteDatabase db, String nome, String descricao, Double preco, String nomeImagem, int idSupermercado, int nDepartamento, int nPosicaoImagemProduto )
    {
        int codImagem = 9999; // Valor Inicial da variável
        switch (nomeImagem)
        {
            case "img_arroz":
                codImagem = 2130837844;
                break;
            case "img_creme_pele":
                codImagem = 2130837845;
                break;
            case "img_leite":
                codImagem = 2130837846;
                break;
            case "img_maca":
                codImagem = 2130837847;
                break;
            case "img_pao":
                codImagem = 2130837848;
                break;
            case "img_pizza":
                codImagem = 2130837849;
                break;
            case "img_refrigerante":
                codImagem = 2130837850;
                break;
            case "img_sabonete":
                codImagem = 2130837851;
                break;
            case "img_shampoo":
                codImagem = 2130837852;
                break;
        }
        db.execSQL(inserirProdutosBD +"('"+nome+"', '"+descricao+"',  '"+preco+"',  '"+codImagem+"',  '"+idSupermercado+"',  '"+nDepartamento+"', '"+idSupermercado+"', '"+nPosicaoImagemProduto+"');");
    }


}
