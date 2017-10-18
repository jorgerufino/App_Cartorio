//Limita a quantidade de caracteres 
//Para usá-lo basta chama-lo no metodo setDocument de um JTextField
//Por exemplo Ex. jTextField.setDocument(new LimitaCaracteres(14)); (o parametro passado é quantidade maxima de caracteres aceitos)
package Classes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitaCaracteres extends PlainDocument {

    private int maxLength;

    public LimitaCaracteres(int maxlen) {
        super();

        if (maxlen <= 0) {
            throw new IllegalArgumentException("You must specify a maximum length!");
        }

        maxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null || getLength() == maxLength) {
            return;
        }

        int totalLen = (getLength() + str.length());
        if (totalLen <= maxLength) {
            super.insertString(offset, str, attr);
            return;
        }

        String newStr = str.substring(0, (maxLength - getLength()));
        super.insertString(offset, newStr, attr);
    }
}
