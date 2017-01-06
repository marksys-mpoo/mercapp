package com.mercapp.usuario.gui;

import java.util.HashMap;
import java.util.Map;

public final class CriptografiaSenha {

    private static final int V_ALFA = 52;
    private static final int V_NUME = 10;
    private final char[] alfa = new char[V_ALFA];
    private final char[] nume = new char[V_NUME];

    private static final String LETRAS = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    private static final String NUMEROS = "0123456789";

    private Map<Character, String> valores = new HashMap<>();
    private String senha = "senha";

    private static CriptografiaSenha criptografia = new CriptografiaSenha();

    private CriptografiaSenha() {
        for (int i = 0; i < V_ALFA; i++) {
            alfa[i] = LETRAS.charAt(i);
        }
        for (int i = 0; i < V_NUME; i ++) {
            nume[i] = NUMEROS.charAt(i);
        }
    }

    public static CriptografiaSenha getInstancia(String senha) {
        criptografia.setSenhaOriginal(senha);
        return criptografia;
    }

    public String getSenhaCriptografada() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < senha.length(); i++) {
            sb.append(valores.get(senha.charAt(i)));
        }
        return sb.toString();
    }

    private void setSenhaOriginal(String recebeSenha) {
        this.senha = recebeSenha;

        for (int i = 0; i < V_ALFA; i++) {
            if ((i % 2) == 0) { // minúsculas
                final int mod2016 = 2016;
                valores.put(alfa[i], String.format("%02X", logica((recebeSenha.length() * i) % mod2016)));
            }
            else if ((i % 2) != 0) { // maiúsculas
                final int mod2015 = 2015;
                valores.put(alfa[i], String.format("%02x", logica((recebeSenha.length() * i) % mod2015)));
            }
        }
        for (int i = 0; i < V_NUME; i++) {
            if ((i % 2) == 0) { // pares
                final int mod2014 = 2014;
                valores.put(nume[i], String.format("%02X", logica((recebeSenha.length() * i) % mod2014)));
            }
            else if ((i % 2) != 0) { // ímpares
                final int mod2013 = 2013;
                valores.put(nume[i], String.format("%02x", logica((recebeSenha.length() * i) % mod2013)));
            }
        }
    }

    // Método com a lógica pra gerar os valores
    private long logica(long n) {
        long cubo = n * n * n;

        final int numeroVezesCubo = 157;
        long x = cubo + numeroVezesCubo;
        final int mutiplicacaoVezesCuboeN = 21 * 2007;
        long y = (cubo * n) * mutiplicacaoVezesCuboeN;

        return 2 + x + y;
    }


}


