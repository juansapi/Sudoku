
package uniandes.cupi2.sudoku.Mundo;


public class Casilla
{

    private int vactual;
    private int valorIngresado;
    private boolean inicial;
    private boolean marcado;

    
    // ----------------------------
    // Constructor
   
    public Casilla( int value )
    {
    	vactual = value;
    	valorIngresado = 0;
        inicial = false;
        marcado = false;
    }

    // ----------------------------
    // Metodos
    
    
    public boolean darInicial( )
    {
        return inicial;
    }

   public int getvalor( )
    {
        return vactual;
    }

    public boolean darMarcado( )
    {
        return marcado;
    }

    public int getValorIngresado( )
    {
        return valorIngresado;
    }

    public void setInicial( )
    {
        inicial = true;
    }

    public void marcado( )
    {
        marcado= true;
    }

    public void setValorIngresado( int nValorIngresado )
    {
    	valorIngresado = nValorIngresado;
    }

   
    public void darMarcad( )
    {
        marcado = false;
    }
}
