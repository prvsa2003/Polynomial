package pol;

import java.util.ArrayList;

public class Polynomial {

    public ArrayList<Poly> a = null;

    public Polynomial() {
    }

    public Polynomial(ArrayList<Poly> a) {
        this.a = a;
    }

    public String split(String s) {
        s = s.replace(" ", "");
        if (s.charAt(0) != '+' && s.charAt(0) != '-') {
            s = "+" + s;
        }
        s = s.replace("X", "x");
        s = s.replace("-x", "-1x");
        s = s.replace("+x", "+1x");
        s = s.replace("-", " -");
        s = s.replace("+", " +");

        return s;
    }

    public void eachPoly(String[] A) {
        this.a = new ArrayList<>();
        for (String s : A) {
            Poly n = new Poly(s);
            a.add(n);
        }

        //return a;
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < a.size(); i++) {

            if (a.get(i).power == -1) {
                return "it's not polynomial!";
            }

            if (a.get(i).coeff == 0) {
                continue;
            }

            if (a.get(i).power == 0) {
                if (a.get(i).coeff < 0) {
                    s = s + a.get(i).coeff;
                } else if (a.get(i).coeff > 0) {
                    s = s + "+" + a.get(i).coeff;
                }
            }
            if (a.get(i).power == 1) {
                if (a.get(i).coeff < 0) {
                    s = s + a.get(i).coeff + "x";
                } else if (a.get(i).coeff > 0) {
                    s = s + "+" + a.get(i).coeff + "x";
                }
            }

            if (a.get(i).coeff > 0 && a.get(i).power > 1) {
                s = s + "+" + a.get(i).coeff + "x^" + a.get(i).power;
            } else if (a.get(i).coeff < 0 && a.get(i).power > 1) {
                s = s + a.get(i).coeff + "x^" + a.get(i).power;
            }

        }
        return s;
    }

    public double evaluate(double x) {
        double z = 0;
        for (int i = 0; i < a.size(); i++) {
            z += a.get(i).coeff * (Math.pow(x, a.get(i).power));
        }
        return z;
    }

    public Polynomial sum(Polynomial another) {
        ArrayList<Poly> n = new ArrayList();
        int coeff, power;
        ArrayList<Poly> B = another.a;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                if (a.get(i).power == B.get(j).power) {
                    coeff = a.get(i).coeff + B.get(j).coeff;
                    power = a.get(i).power;
                    Poly k = new Poly(coeff, power);
                    n.add(k);
                    a.get(i).coeff = 0;
                    B.get(j).coeff = 0;
                }
            }
        }

        for (Poly B1 : B) {
            n.add(B1);
        }
        for (Poly A1 : a) {
            n.add(A1);
        }

        return new Polynomial(n);
    }

    public ArrayList<Poly> subtract(ArrayList<Poly> A, ArrayList<Poly> B) {
        ArrayList<Poly> n = new ArrayList();
        int coeff, power;

        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                if (A.get(i).power == B.get(j).power) {
                    coeff = A.get(i).coeff - B.get(j).coeff;
                    power = A.get(i).power;
                    Poly k = new Poly(coeff, power);
                    n.add(k);
                    A.get(i).coeff = 0;
                    B.get(j).coeff = 0;
                }
            }
        }

        for (Poly B1 : B) {
            B1.coeff = -B1.coeff;
            n.add(B1);
        }
        for (Poly A1 : A) {
            n.add(A1);
        }

        return n;
    }

    public ArrayList<Poly> multiply(ArrayList<Poly> A, ArrayList<Poly> B) {
        ArrayList<Poly> n = new ArrayList();
        int coeff, power;

        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                coeff = A.get(i).coeff * B.get(j).coeff;
                power = A.get(i).power + B.get(j).power;
                Poly k = new Poly(coeff, power);
                n.add(k);
            }
        }

        return n;
    }

    public ArrayList<Poly> divide(ArrayList<Poly> A, ArrayList<Poly> B) {
        ArrayList<Poly> t = new ArrayList();
        ArrayList<Poly> h = new ArrayList();
        int coeff, power;

        sortByDeg(B);
        sortByDeg(A);

        if (maxDeg(B) >= maxDeg(A)) {
            for (int i = B.get(0).power - A.get(0).power; i >= 0; --i) {
                ArrayList<Poly> m = new ArrayList();
                t = new ArrayList();
                coeff = B.get(0).coeff / A.get(0).coeff;
                power = B.get(0).power - A.get(0).power;
                Poly k = new Poly(coeff, power);
                t.add(k);
                h.add(k);
                m = multiply(t, A);
                B = subtract(B, m);
                for (int j = 0; j < B.size(); j++) {
                    if (B.get(j).coeff == 0) {
                        B.remove(j);
                        --j;
                    }
                }
                sortByDeg(B);
                sortByDeg(A);
            }
        } else {

            for (int i = A.get(0).power - B.get(0).power; i >= 0; --i) {
                ArrayList<Poly> m = new ArrayList();
                t = new ArrayList();
                coeff = A.get(0).coeff / B.get(0).coeff;
                power = A.get(0).power - B.get(0).power;
                Poly k = new Poly(coeff, power);
                t.add(k);
                h.add(k);
                m = multiply(t, B);
                A = subtract(A, m);
                for (int J = 0; J < A.size(); J++) {
                    if (A.get(J).coeff == 0) {
                        A.remove(J);
                        --J;
                    }
                }
                sortByDeg(B);
                sortByDeg(A);
            }
        }
        return h;
    }

    public int maxDeg(ArrayList<Poly> B) {
        int maxDeg = B.get(0).power;
        for (int x = 0; x < B.size(); ++x) {
            if (B.get(x).power >= maxDeg) {
                maxDeg = B.get(x).power;
            }
        }
        return maxDeg;
    }

    public ArrayList<Poly> sortByDeg(ArrayList<Poly> B) {
        int t, d;
        for (int x = 0; x < B.size(); ++x) {
            for (int y = x + 1; y < B.size(); ++y) {
                if (B.get(x).power < B.get(y).power) {
                    t = B.get(y).power;
                    d = B.get(y).coeff;
                    B.get(y).power = B.get(x).power;
                    B.get(y).coeff = B.get(x).coeff;
                    B.get(x).power = t;
                    B.get(x).coeff = d;
                }
            }
        }
        return B;
    }
}
