package com.mercapp.usuario.gui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public abstract class Mascara {

    public enum MaskType {
        CNPJ("##.###.###/####-##"), CPF("###.###.###-##"), CEP("#####-###"), TEL("(##) ####-####"),
        CELL("(##) #####-####");

        private String mask;

        MaskType(String s) {
            mask = s;
        }

        public void setMask(String masks) {
            this.mask = masks;
        }
        public String getMask() {
            return mask;
        }


    }


    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[ ]", "").replaceAll("[)]", "");
    }

//    public static String mask(MaskType type, String s) {
//        //String result = s;
//        StringBuilder result = new StringBuilder();
//        if (!s.contains(".")) {
//
//            result.append("");
//            String str = Mascara.unmask(s.toString());
//            result.append("");
//
//            int i = 0;
//            concatenacao(str, result, i, type);
//        }
//        return result.toString();
//    }


    public static TextWatcher insert(final MaskType type, final EditText ediTxt) {
        return new TextWatcher() {

            private String old = "";
            private boolean isUpdating;
            public boolean isUpdating() {
                return isUpdating;
            }

            public void setUpdating(boolean updating) {
                isUpdating = updating;
            }
            public void setOld(String old) {
                this.old = old;
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (isUpdating()){
                    setUpdating(false);
                    setOld(s.toString());
                    return;
                }

                buscaConcatena(s);
            }

            private void buscaConcatena(CharSequence s) {
                if (!s.toString().isEmpty() && (s.toString().length() > old.length())) {
                    String str = Mascara.unmask(s.toString());
//                    String mask = "";
                    StringBuilder mask = new StringBuilder();
                    int i = 0;
                    concatenacao(str, mask, i, type);
                    setUpdating(true);
                    ediTxt.setText(mask);
                    ediTxt.setSelection(mask.length());
                }else{
                    setOld(s.toString());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static void concatenacao(String str, StringBuilder mask, int incremanenta, MaskType type) {
        for (char iteradorConcatenacaoString : type.getMask().toCharArray()) {
            if ( iteradorConcatenacaoString != '#') {
                mask.append(iteradorConcatenacaoString);
                continue;
            }
            try {
                mask.append(str.charAt(incremanenta));
            } catch (Exception e) {
                break;
            }
            incremanenta++;
        }
    }

}
