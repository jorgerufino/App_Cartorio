//Aceita somente números
//Como extende a Classe LimitaCaracteres, se passa a quantidade maxima de caracteres que serão aceitos
//Exemplo. jTextField.setDocument(new SomenteNumeros(14)); (aceita somente números e a quantidade maxima de 14 caracteres)
package Classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class SomenteNumeros extends LimitaCaracteres {
public SomenteNumeros(int maxlen) {
super(maxlen);
}

@Override
public void insertString(int offset, String str, AttributeSet attr)
throws BadLocationException {
    
if (str == null)
return;

try {
    str = str.replaceAll("-", "");
    //somente no caso do ponto é obrigatorio usar os colchetes
    str = str.replaceAll("[.]", "");
    str = str.replaceAll("/", "");
    Long.parseLong(str);
    //Integer.parseInt(str);
} catch (Exception e) {
return;
}

super.insertString(offset, str, attr);
}
}
