package com.mercapp.usuario.negocio;


import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.mercapp.R;
import com.mercapp.usuario.gui.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jrvansuita on 17/11/15.
 */

public class Validacao {

    //Validações da tela de cadastro de Pessoa

    public static final int INVALID = -1;
    public static final int VISA = 0;
    public static final int MASTERCARD = 1;
    public static final int AMERICAN_EXPRESS = 2;
    public static final int EN_ROUTE = 3;
    public static final int DINERS_CLUB = 4;

    private static final String stringInicial = "0.0";

    private static final int[] weightCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] weightCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final String[] cardNames = {"Visa", "Mastercard",
            "American Express", "En Route", "Diner's CLub/Carte Blanche",};

    private static int computeDigit(String str, int[] weight) {
        int sum = 0;
        for (int index = str.length() - 1, digit; index >= 0; index--) {
            digit = Integer.parseInt(str.substring(index, index + 1));
            sum += digit * weight[weight.length - str.length() + index];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isValidCPF(String cpf) {
        cpf = onlyNumbers((CharSequence) cpf);
        if ((cpf == null) || (cpf.length() != 11)) return false;

        Integer digitA = computeDigit(cpf.substring(0, 9), weightCPF);
        Integer digitB = computeDigit(cpf.substring(0, 9) + digitA, weightCPF);
        return cpf.equals(cpf.substring(0, 9) + digitA.toString() + digitB.toString());
    }

    public static boolean isValidCNPJ(String cnpj) {
        cnpj = onlyNumbers((CharSequence) cnpj);
        if ((cnpj == null) || (cnpj.length() != 14)) return false;

        Integer digitA = computeDigit(cnpj.substring(0, 12), weightCNPJ);
        Integer digitB = computeDigit(cnpj.substring(0, 12) + digitA, weightCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digitA.toString() + digitB.toString());
    }

    private static String onlyNumbers(CharSequence s) {
        return s.toString().replaceAll("\\D+", "");
    }


    public static boolean validCC(String number) throws Exception {
        int CardID;
        if ((CardID = getCardID(number)) != -1)
            return validCCNumber(number);
        return false;
    }

    /**
     * Get the Card type returns the credit card type INVALID = -1; VISA = 0;
     * MASTERCARD = 1; AMERICAN_EXPRESS = 2; EN_ROUTE = 3; DINERS_CLUB = 4;
     */
    public static int getCardID(String number) {
        int valid = INVALID;

        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);
        String digit3 = number.substring(0, 3);
        String digit4 = number.substring(0, 4);

        if (isNumber(number)) {
            /*
             * ----* VISA prefix=4* ---- length=13 or 16 (can be 15 too!?!
             * maybe)
             */
            if (digit1.equals("4")) {
                if (number.length() == 13 || number.length() == 16)
                    valid = VISA;
            }
            /*
             * ----------* MASTERCARD prefix= 51 ... 55* ---------- length= 16
             */
            else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
                if (number.length() == 16)
                    valid = MASTERCARD;
            }
            /*
             * ----* AMEX prefix=34 or 37* ---- length=15
             */
            else if (digit2.equals("34") || digit2.equals("37")) {
                if (number.length() == 15)
                    valid = AMERICAN_EXPRESS;
            }
            /*
             * -----* ENROU prefix=2014 or 2149* ----- length=15
             */
            else if (digit4.equals("2014") || digit4.equals("2149")) {
                if (number.length() == 15)
                    valid = EN_ROUTE;
            }
            /*
             * -----* DCLUB prefix=300 ... 305 or 36 or 38* ----- length=14
             */
            else if (digit2.equals("36")
                    || digit2.equals("38")
                    || (digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0)) {
                if (number.length() == 14)
                    valid = DINERS_CLUB;
            }
        }
        return valid;

        /*
         * ----* DISCOVER card prefix = 60* -------- lenght = 16* left as an
         * exercise ...
         */

    }

    public static boolean isNumber(String n) {
        try {
            double d = Double.valueOf(n).doubleValue();
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCardName(int id) {
        return (id > -1 && id < cardNames.length ? cardNames[id] : "");
    }

    public static boolean validCCNumber(String n) {
        try {
            /*
             * * known as the LUHN Formula (mod10)
             */
            int j = n.length();

            String[] s1 = new String[j];
            for (int i = 0; i < n.length(); i++)
                s1[i] = "" + n.charAt(i);

            int checksum = 0;

            for (int i = s1.length - 1; i >= 0; i -= 2) {
                int k = 0;

                if (i > 0) {
                    k = Integer.valueOf(s1[i - 1]).intValue() * 2;
                    if (k > 9) {
                        String s = "" + k;
                        k = Integer.valueOf(s.substring(0, 1)).intValue()
                                + Integer.valueOf(s.substring(1)).intValue();
                    }
                    checksum += Integer.valueOf(s1[i]).intValue() + k;
                } else
                    checksum += Integer.valueOf(s1[0]).intValue();
            }
            return ((checksum % 10) == 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //Validações da tela de Login e Cadastro de Usuario
    public static boolean verificaVazios(String email, String senha, Context context, EditText etEmail, EditText etSenha) {

        boolean result;

        if (TextUtils.isEmpty(email)) {
            etEmail.requestFocus();
            etEmail.setError(context.getString(R.string.email_vazio));
            result = true;
        } else if (TextUtils.isEmpty(senha)) {
            etSenha.requestFocus();
            etSenha.setError(context.getString(R.string.senha_vazio));
            result = true;
        }
        result = false;

        return result;
    }

    public static boolean validarEmail(String email, Context context, EditText etEmail) {
        boolean result;
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            result = true;
        } else {
            etEmail.requestFocus();
            etEmail.setError(context.getString(R.string.email_invalido));
            result = false;
        }
        return result;
    }

    public static boolean semEspaco(String email, Context context, EditText etEmail) {
        boolean result;
        int idx = email.indexOf(" ");

        if (idx != -1) {
            etEmail.requestFocus();
            etEmail.setError(context.getString(R.string.email_senha_invalido));
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    public static boolean tamanhoInválido(String email, String senha, Context context, EditText etEmail, EditText etSenha) {
        boolean result;

        if (!(email.length() > 3)) {
            etEmail.requestFocus();
            etEmail.setError(context.getString(R.string.login_tamanho_invalido));
            result = false;
        } else if (!(senha.length() > 2)) {
            etSenha.requestFocus();
            etSenha.setError(context.getString(R.string.login_senha_tamanho_invalido));
            result = false;
        }
        result = true;

        return result;
    }

    public static boolean confirmarSenha(String senha, String confirmarSenha, Context context, EditText etConfirmar) {

        boolean result;

        if (senha.equals(confirmarSenha)) {
            result = true;
        } else {
            etConfirmar.requestFocus();
            etConfirmar.setError(context.getString(R.string.senha_diferentes));
            result = false;
        }
        return result;
    }

    // Tela Cadastro de Supermercado
    public static boolean verificaVaziosSupermercado(String nomeSupermercado,
                                                     String telefonesupermercado, Context context, EditText etSupermercadoNome, EditText etSupermercadoTelefone, EditText etLatitude, EditText etLogintude) {
        boolean result;
        if (TextUtils.isEmpty(nomeSupermercado)) {
            etSupermercadoNome.requestFocus();
            etSupermercadoNome.setError(context.getString(R.string.nome_vazio_tela_cadastro_supermrecados));
            result = false;
        } else if (TextUtils.isEmpty(telefonesupermercado)) {
            etSupermercadoTelefone.requestFocus();
            etSupermercadoTelefone.setError(context.getString(R.string.telefone_vazio_tela_cadastro_supermrecados));
            result = false;
        } else if (etLatitude.getText().toString().equals(stringInicial)) {
            etLatitude.requestFocus();
            etLatitude.setError(context.getString(R.string.latitude_vazio_tela_cadastro_produtos));
            result = false;
        } else if (etLogintude.getText().toString().equals(stringInicial)) {
            etLogintude.requestFocus();
            etLogintude.setError(context.getString(R.string.longitude_vazio_tela_cadastro_produtos));
            result = false;
        } else {
            result = true;
        }
        return result;

    }

    // Tela Cadastro de Produto
    public static boolean verificaVaziosProduto(String nome, String descricao, Context context,
                                         EditText setnome, EditText setdescricao) {
        boolean result;
        if (TextUtils.isEmpty(nome)) {
            setnome.requestFocus();
            setnome.setError(context.getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        } else if (TextUtils.isEmpty(descricao)) {
            setdescricao.requestFocus();
            setdescricao.setError(context.getString(R.string.campo_vazio_tela_cadastro_produtos));
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    

}

