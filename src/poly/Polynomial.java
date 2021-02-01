package poly;

import java.util.*;
/**
 * Class creates a GSDLL type list with polynomials that have monomials
 * @author taliayahav
 */

public class Polynomial
{
    private GSDLL<Monomial> poly;

    public Polynomial()
    {
        poly = new GSDLL<Monomial>();
    }

    /**
     * addTerm term to the polynomial poly e.g. if poly is 1 + 2x then
     * addTerm(3x) should result in poly being 1 + 5x
     *
     * @param term Monomial to be added
     */
    public void addTerm(Monomial term)
    {
        //check if poly is null
        //if it is null, create new GSDLL with only term inside and set poly to it
        if (!poly.locate(term)) //this degree doesn't exist yet in poly
        {
            poly.insert(term);
        }
        else //there's already a monomial with this degree in poly
        {
            Iterator<Monomial> iter = poly.iterator();
            while (iter.hasNext())
            {
                Monomial curr = iter.next();
                if (term.equals(curr))
                {
                    double newCoeff = term.getCoeff() + curr.getCoeff();
                    //now create a new monomial with this coeff and the degree of term
                    Monomial toBeInserted = new Monomial(term.getDegree(), newCoeff);
                    //now add this new monomial to the list

                    //and remove the old one
                    //and get out of the while loop cuz we're done

                    poly.insert(toBeInserted);
                    poly.remove(curr);
                    break;
                }
                //term.compareTo(iter.next());

            }
        }
    }

    /**
     * addPoly: add another polynomial to this one e.g. if our poly is 1 + 2x
     * and the other is 3 + x^2 then addPoly should transform our poly to 4 + 3x
     * + x^2
     *
     * @param other Polynomial to add to this one
     */
    public void addPoly(Polynomial other)
    {
        Iterator<Monomial> iter = other.poly.iterator();
        while (iter.hasNext())
        {
            addTerm(iter.next());
        }
    }

    /**
     * multiply this polynomial by other
     * @param other Polynomial to multiply this one by
     */
    public void multiplyBy(Polynomial other)
    {
        GSDLL<Monomial> pntr2 = new GSDLL<>();
        Iterator<Monomial> iter = poly.iterator();
        Iterator<Monomial> iter2 = other.poly.iterator();
        while (iter.hasNext())
        {
            Monomial mono1 = iter.next();
            while(iter2.hasNext())
            {
                Monomial mono2 = iter2.next();
                int degree = mono1.getDegree() + mono2.getDegree();
                double coeff = mono1.getCoeff() * mono2.getCoeff();
                pntr2.insert(new Monomial(degree,coeff));
                //multiply coeff of both poly
                //add degree of both polys
                //create new node (monomial?)that is now the new poly

            }
        }
        poly=pntr2;
    }

    public String toString()
    {
        String strRet = "";
        if (poly != null)
        {
            Iterator<Monomial> it = poly.iterator();
            while (it.hasNext())
            {
                Monomial term = it.next();
                strRet += term.toString();
                strRet += " + ";
            }
            strRet = strRet.substring(0, strRet.length() - 3); // eat last " + "
        }
        return strRet;
    }

}