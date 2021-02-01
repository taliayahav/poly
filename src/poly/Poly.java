package poly;

/**
 *
 * @author taliayahav
 */
public class Poly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Polynomial poly = new Polynomial();
        Monomial mono1 = new Monomial(3, 4); // 4x^3
        poly.addTerm(mono1);
        Monomial mono2 = new Monomial(3, 5); //5x^3
        poly.addTerm(mono2);
        System.out.println("Poly = " + poly.toString());
        Polynomial poly2 = new Polynomial();
        Monomial mono3 = new Monomial(2, 4); // 4x^2
        poly2.addTerm(mono3);
        Monomial mono4 = new Monomial(3, 6); //6x^3
        poly2.addTerm(mono4);
        System.out.println("Poly2 = " + poly2.toString());
        poly.addPoly(poly2);
        System.out.println("Added together: " + poly);
        System.out.print("Multiplied together: " + poly);
        poly.multiplyBy(poly2);
    }

}
