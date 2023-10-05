package Task4;

import java.util.*;

public class Password {
    private final static char[] SPECIALSYMBOLS = {
            '#', '@', '$', '%', '^', '&', '*', '<', '>'
    };
    private final static char[] PUNCTUATIONMARKS = {
            ',', '.', '?', '!'
    };

    private final static int GOODMINLENGTH = 8;
    private final static int GOODMAXLENGTH = 12;
    
    private String password;

    private boolean isGood;
    private ArrayList<PswdCriteria> badQualities;

    public List<PswdCriteria> getBadQualities() {
        return Collections.unmodifiableList(badQualities);
    }

    public boolean isGood() {
        return isGood;
    }

    public Password(String password) throws Exception {
        if (password.isBlank() || password.split(" ").length > 1)
            throw new Exception("Invalid password!");
        this.password = password;
        badQualities = new ArrayList<>(PswdCriteria.values().length);
        isGood = true;
        checkIsGood();
    }

    private void checkIsGood() {
        if (password.length() < GOODMINLENGTH || password.length() > GOODMAXLENGTH)
            badQualities.add(PswdCriteria.hasGoodLength);
        boolean hasLatinLowerCase = false;
        boolean hasLatinUpperCase = false;
        boolean hasSpecialSymbols = false;
        boolean hasPunctuationMarks = false;

        outer:
        for (Character c : password.toCharArray()) {
            if (!hasSpecialSymbols) {
                for (Character s : SPECIALSYMBOLS)
                    if (c.equals(s)) {
                        hasSpecialSymbols = true;
                        continue outer;
                    }
            }
            if (!hasPunctuationMarks)
                for (Character s : PUNCTUATIONMARKS)
                    if (c.equals(s)){
                        hasPunctuationMarks = true;
                        continue outer;
                    }
            if (!hasLatinUpperCase && c >= 'A' && c <= 'Z'){
                hasLatinUpperCase = true;
            }
            if (!hasLatinLowerCase && c >= 'a' && c <= 'z'){
                hasLatinLowerCase = true;
            }
        }

        if (!hasSpecialSymbols)
            badQualities.add(PswdCriteria.hasSpecialSymbols);
        if (!hasPunctuationMarks)
            badQualities.add(PswdCriteria.hasPunctuationMarks);
        if (!hasLatinUpperCase)
            badQualities.add(PswdCriteria.hasLatinUpperCase);
        if (!hasLatinLowerCase)
            badQualities.add(PswdCriteria.hasLatinLowerCase);

        if (!badQualities.isEmpty())
            isGood = false;
    }
}
