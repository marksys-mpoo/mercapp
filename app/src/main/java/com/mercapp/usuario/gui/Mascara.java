package com.mercapp.usuario.gui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public abstract class Mascara {

    public enum MaskType {
        CNPJ("##.###.###/####-##"), CPF("###.###.###-##"), CEP("#####-###"), TEL("(##) ####-####"),
        CELL("(##) #####-####");

        private String mask;
        public void setMask(String mask) {
            this.mask = mask;
        }
        public String getMask() {
            return mask;
        }

        MaskType(String s) {
            mask = s;
        }


    }


    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[ ]", "").replaceAll("[)]", "");
    }

    public static String mask(MaskType type, String s) {
        String result = s;

        if (!s.contains(".")) {
            String str = Mascara.unmask(s.toString());
            result = "";

            int i = 0;
            for (char m : type.getMask().toCharArray()) {
                if (m != '#') {
                    result += m;
                    continue;
                }
                try {
                    result += str.charAt(i);
                } catch (Exception e) {
                    break;
                }
                i++;
            }
        }

        return result;
    }


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
            public String getOld() {
                return old;
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

                if (!s.toString().isEmpty() && (s.toString().length() > old.length())) {
                    String str = Mascara.unmask(s.toString());
                    String mask = "";

                    int i = 0;
                    for (char m : type.getMask().toCharArray()) {
                        if (m != '#') {
                            mask += m;
                            continue;
                        }
                        try {
                            mask += str.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }
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

}
