package days.day02;

public class Password {
    private int a;
    private int b;
    private char letter;
    private String password;

    public Password() {}

    public Password(String st) {
        String[] policy = st.split("-|:\\s|\\s");
        a = Integer.parseInt(policy[0]);
        b = Integer.parseInt(policy[1]);
        letter = policy[2].charAt(0);
        password = policy[3];
    }

    public boolean part01(int low, int high, char letter, String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter) {
                count++;
            }
        }
        if (count >= low && count <= high) {
            return true;
        }

        return false;
    }

    public boolean part02(int posOne, int posTwo, char letter, String password) {
        if (posOne <= password.length() && posTwo <= password.length()) {
            if ((password.charAt(posOne - 1) == letter) ^ (password.charAt(posTwo - 1) == letter)) {
                return true;
            }
        }

        return false;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public char getLetter() {
        return letter;
    }

    public String getPassword() {
        return password;
    }
}
