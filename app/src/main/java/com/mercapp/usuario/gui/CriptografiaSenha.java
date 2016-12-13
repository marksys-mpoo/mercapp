package com.mercapp.usuario.gui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class CriptografiaSenha {

//    private String usuario;
//    private String senha;
//
//    public CriptografiaSenha() {
//    }
//
//    public CriptografiaSenha(String usuario, String senha) {
//        this.criptografaLogin(usuario, senha);
//    }
//
//    public String criptografaLogin(String codUsuario, String senha) {
//
//        String senhaResult;
//        String arrumaResult = new String("");
//
//        StringBuffer codUsuarioAsciiAux;
//        StringBuffer senhaAsciiAux;
//        StringBuffer senhaResultAux;
//        StringBuffer valorAscii;
//        StringBuffer shortBuffer;
//
//        Short codUsuarioShort;
//        Short senhaShort;
//        Short shortAux;
//
//        short somaSenha = 0;
//
//        codUsuarioAsciiAux = new StringBuffer("000000000000000000000000");
//        senhaAsciiAux = new StringBuffer("000000000000000000000000");
//        valorAscii = new StringBuffer("000000000000000000000000");
//
//        int ind2 = 1;
//        for (int ind1 = codUsuario.length(); ind1 >= 1; ind1--) {
//            codUsuarioAsciiAux.replace(
//                    ind2 - 1,
//                    ind2 + 1,
//                    obtemValorAscii(codUsuario.substring(ind1 - 1)));
//            ind2 = ind2 + 3;
//
//        }
//
//        codUsuarioAsciiAux.setLength(24);
//        // Truncando p/ os 24 caracteres da criptografia
//        this.usuario = (String) codUsuarioAsciiAux.toString();
//        // Obtendo usuario criptografado
//
//        ind2 = 1;
//        for (int ind1 = 0; ind1 < senha.length(); ind1++) {
//            senhaAsciiAux.replace(
//                    ind2 - 1,
//                    ind2 + 1,
//                    obtemValorAscii(senha.substring(ind1)));
//            ind2 = ind2 + 3;
//        }
//
//        senhaAsciiAux.setLength(24);
//        // Truncando p/ os 24 caracteres da criptografia
//
//        for (int ind1 = 0; ind1 < 22; ind1 += 3) {
//            codUsuarioShort =
//                    new Short(
//                            codUsuarioAsciiAux.substring(ind1, ind1 + 3).toString());
//            senhaShort =
//                    new Short(senhaAsciiAux.substring(ind1, ind1 + 3).toString());
//            somaSenha =
//                    (short) (codUsuarioShort.shortValue()
//                            + senhaShort.shortValue());
//            shortAux = new Short(somaSenha);
//            arrumaResult = Short.toString(shortAux.shortValue());
//            if ((arrumaResult != null) && (arrumaResult.length() == 2)) {
//                arrumaResult = "0" + arrumaResult;
//            }
//            valorAscii.replace(ind1, ind1 + 2, arrumaResult);
//            // System.out.println("1 : "+ shortAux.shortValue());//teste2
//            // System.out.println("2 : " + valorAscii.toString()); //teste3
//        }
//
//        valorAscii.setLength(24);
//        //Truncando p/ os 24 caracteres da criptografia do Logix
//
//        senhaResult = (String) valorAscii.toString();
//        this.senha = senhaResult;
//        // Obtendo senha criptografada
//        return senhaResult;
//    }
//
//    public String decriptografarSenha(String usuario, String senhaCripto) throws java.lang.NumberFormatException {
//        String senhaResult;
//        String arrumaResult = new String("");
//
//        StringBuffer codUsuarioAsciiAux;
//        String usuarioAscii;
//        StringBuffer senhaAsciiAux;
//        StringBuffer senhaResultAux;
//        StringBuffer valorAscii;
//
//        Short codUsuarioShort;
//        Short shortAux;
//        Short senhaCriptoShort;
//
//        short diminuiSenha = 0;
//
//        codUsuarioAsciiAux = new StringBuffer("000000000000000000000000");
//        senhaAsciiAux = new StringBuffer("000000000000000000000000");
//        senhaResultAux = new StringBuffer("000000000000000000000000");
//        valorAscii = new StringBuffer(senhaCripto);
//
//        int ind2 = 1;
//        for (int ind1 = usuario.length(); ind1 >= 1; ind1--) {
//            codUsuarioAsciiAux.replace(
//                    ind2 - 1,
//                    ind2 + 1,
//                    obtemValorAscii(usuario.substring(ind1 - 1)));
//            ind2 = ind2 + 3;
//
//        }
//
//        codUsuarioAsciiAux.setLength(24);
//        // Truncando p/ os 24 caracteres da criptografia
//        usuarioAscii = (String) codUsuarioAsciiAux.toString();
//        // Obtendo usuario criptografado
//
//        for (int ind1 = 0; ind1 < 22; ind1 += 3) {
//            codUsuarioShort = new Short(codUsuarioAsciiAux.substring(ind1, ind1 + 3).toString());
//            senhaCriptoShort = new Short(valorAscii.substring(ind1,ind1+3).toString());
//            diminuiSenha =  (short) (senhaCriptoShort.shortValue() - codUsuarioShort.shortValue());
//            shortAux = new Short(diminuiSenha);
//            arrumaResult = Short.toString(shortAux.shortValue());
//            if ((arrumaResult != null) && (arrumaResult.length() == 2)) {
//                arrumaResult = "0" + arrumaResult;
//            }
//            senhaResultAux.replace(ind1, ind1 + 2, arrumaResult);
//            // System.out.println("1 : "+ shortAux.shortValue());//teste2
//            // System.out.println("2 : " + valorAscii.toString()); //teste3
//        }
//
//        senhaResultAux.setLength(24);
//        //Truncando p/ os 24 caracteres da criptografia
//        senhaResult="";
//        try {
//
//            for(int ind1=0;ind1<22;ind1 +=3){
//                String aux = String.valueOf((char) new Short(senhaResultAux.substring(ind1, ind1 + 3).toString()).intValue());
//                if((Character.isLetterOrDigit(aux.charAt(0)))){
//                    senhaResult += aux;
//                }
//            }
//        } catch(Exception ex) {
//            return "000000000";
//        }
//
//        return senhaResult;
//    }
//
//    public String obtemValorAscii(String caracterString) {
//        Character caracter;
//        Character caracter2;
//        Character caracter3;
//        Character caracter4;
//
//        String caracterAux;
//        String resultCharacter;
//
//        StringBuffer valorAscii;
//
//        int compara = 0;
//
//        short posIni = 0;
//        short posFim = 0;
//        short comparador = 0;
//
//        byte valorAsciiAux;
//
//        posIni = (short) 32;
//        posFim = (short) 126;
//        caracter = new Character(caracterString.charAt(0));
//        caracter2 = new Character('0');
//        caracter3 = new Character('A');
//        caracter4 = new Character('a');
//
//        valorAscii = new StringBuffer();
//
//        compara = caracter.compareTo(caracter2);
//        // Retorna 0 (zero) se for igual numericamente
//        // Retorna um numero menor que zero se for menor numericamente
//        // Retorna um numero maior que zero se for maior numericamente
//
//        if (compara == 0 || compara > 0) {
//            posIni = (short) 48;
//            compara = caracter.compareTo(caracter3);
//            if (compara == 0 || compara > 0) {
//                posIni = (short) 65;
//                compara = caracter.compareTo(caracter4);
//                if (compara == 0 || compara > 0) {
//                    posIni = (short) 97;
//                } else {
//                    posFim = (short) 90;
//                }
//            } else {
//                posFim = (short) 90;
//            }
//        } else {
//            posFim = (short) 47;
//        }
//
//        for (int i = posIni; i <= posFim; i++) {
//            caracterAux = caracter.toString();
//            valorAsciiAux = (byte) caracterAux.charAt(0);
//            comparador = valorAsciiAux;
//            if (comparador == i) {
//                if (comparador < 100) {
//                    valorAscii.append("0").append(i);
//                    // apenas para fazer igual ao 4GL USING &&&
//                } else {
//                    valorAscii.append(i);
//                }
//            }
//        }
//        resultCharacter = new String(valorAscii);
//        return resultCharacter;
//    }
//
//    public String descriptografaUsuario(String usuarioCriptografado) {
//
//        Byte byteAux = null;
//        StringBuffer retornoAux = null;
//        String stringAux;
//        String retorno = "";
//
//        char[] charAux = new char[1];
//
//        retornoAux = new StringBuffer();
//
//        for (int i = 0; i < usuarioCriptografado.length(); i = i + 3) {
//            stringAux = usuarioCriptografado.substring(i, i + 3);
//            byteAux = new Byte(stringAux);
//            charAux[0] = (char) byteAux.byteValue();
//            stringAux = new String(charAux);
//            retornoAux.append(stringAux);
//        }
//
//        retornoAux.reverse();
//        retorno = new String(retornoAux.toString());
//
//        return retorno;
//    }
//
//    public String getUsuarioCriptografado() {
//        return this.usuario.toString();
//    }
//
//    public String getSenhaCriptografada() {
//        return this.senha.toString();
//    }
//
//    public static String criptografaSenha(String senha) {
//        MessageDigest md = null;
//        String password = new String(senha);
//        try {
//            md = MessageDigest.getInstance("SHA-1");
//        } catch (NoSuchAlgorithmException _exNSAE) {
//            System.out.println(
//                    "Algoritmo de criptografia não encontrado. \n Execussão abortada.");
//            System.exit(0);
//        }
//        md.reset();
//        md.update(password.getBytes());
//        byte[] digest1 = md.digest();
//
//        StringBuffer encryptedPassword = new StringBuffer();
//        for (int i = 0; i < digest1.length; i++) {
//            encryptedPassword.append(Integer.toHexString(0xFF & digest1[i]));
//        }
//        return encryptedPassword.toString();
//    }
//
//
//}
    private char[] alfa = new char[52];
    private char[] nume = new char[10];

    private String letras = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    private String numeros = "0123456789";

    private HashMap<Character, String> valores = new HashMap<Character, String>();
    private String senha = "senha";

    private static CriptografiaSenha criptografia = new CriptografiaSenha();

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

    private void setSenhaOriginal(String senha) {
        this.senha = senha;

        for (int i = 0; i < 52; i++) {
            if ((i % 2) == 0) { // minúsculas
                valores.put(alfa[i], String.format("%02X", logica((senha.length() * i) % 2014)));
            }
            else if ((i % 2) != 0) { // maiúsculas
                valores.put(alfa[i], String.format("%02x", logica((senha.length() * i) % 2013)));
            }
        }
        for (int i = 0; i < 10; i++) {
            if ((i % 2) == 0) { // pares
                valores.put(nume[i], String.format("%02X", logica((senha.length() * i) % 2012)));
            }
            else if ((i % 2) != 0) { // ímpares
                valores.put(nume[i], String.format("%02x", logica((senha.length() * i) % 2011)));
            }
        }
    }

    // Método com a lógica pra gerar os valores
    private long logica(long n) {
        long cubo = n * n * n;

        long x = cubo + 157;
        long y = (cubo * n) * (21 * 2007);

        return 2 + x + y;
    }

    private CriptografiaSenha() {
        for (int i = 0; i < 52; i++) {
            alfa[i] = letras.charAt(i);
        }
        for (int i = 0; i < 10; i ++) {
            nume[i] = numeros.charAt(i);
        }
    }

}


