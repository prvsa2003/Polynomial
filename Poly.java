package pol;

public class Poly {

    int power, coeff;

    public Poly(String s) {
        s = s.replace(" ", "");      // space 
        s = s.replace("x^", "x^ ");
        s = s.replace("x", " x");
        String[] z = s.split(" "); // splite with space 

        for (int j = 0; j < z.length; j++) {
            if (j == 0) {
                coeff = Integer.parseInt(z[0]);
            }
            if ("x".equals(z[j])) {
                power = 1;
            }
            if (z[j].equals("x^")) {
                power = Integer.parseInt(z[j + 1]);
            }
        }

    }

    public Poly(int coeff, int power) {
        this.power = power;
        this.coeff = coeff;
    }

}
