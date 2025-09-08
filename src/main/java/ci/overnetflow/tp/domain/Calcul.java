package ci.overnetflow.tp.domain;

public class Calcul {
    private Float a;
    private Float b;

    public Calcul(Float a, Float b) {
        this.a = a;
        this.b = b;
    }

    public Float additionner(float a, float b) {
        return a + b;
    }

    public Float soustraire(float a, float b) {
        return a - b;
    }

    public Float multiplier(float a, float b) {
        return a * b;
    }

    public Float diviser(float a, float b) throws Exception{
        if (b != 0) {
            return a / b;
        }
        throw new Exception();
    }

    public Float identiteRemarquable(Float a, Float b) {
        Float a2 = multiplier(a, a);
        Float b2 = multiplier(b, b);
        Float aX2 = multiplier(2, a);
        return additionner(additionner(a2, multiplier(aX2, b)), b2);
    }

}
