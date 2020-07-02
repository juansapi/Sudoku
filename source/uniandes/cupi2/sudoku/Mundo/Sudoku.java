

package uniandes.cupi2.sudoku.Mundo;

import java.util.Properties;




public class Sudoku
{
    // --------------------------
    // Constantes
	
    private static final String PROPERTIES = "sudoku.su";
    public static final int filas = 9;//ROW_NUMBER
    public static final int numero_columna = 9;//COLUMN_NUMBER
    public static final int numero_area = 9;//NUMBER_AREAS 
    public static final int inicializac = 3;//INITIAL_CELL_NUMBER

    private boolean jugar;
    private Casilla[][] tabla;

    // -------------------
    // Constructor
   
    public Sudoku( )
    {
    	tabla = new Casilla[filas][numero_columna];
    }

    // -------------------
    // Metodos
    

   
    public void loadtabla( Properties properties ) throws Exception
    {
        for( int i = 0; i < numero_columna; i++ )
        {
            String su = ( String )properties.get(  PROPERTIES + ( i + 1 ) );
            if( su != null && su.length( ) == filas )
                loadFila( su, i );
            else
                throw new Exception( "el formato del archivo no es valido" );
        }
    }
    /*
    public void loadBoard( Properties properties ) throws Exception
    {
        for( int i = 0; i < COLUMN_NUMBER; i++ )
        {
            String row = ( String )properties.get( KEY_PROPERTIES + ( i + 1 ) );
            if( row != null && row.length( ) == ROW_NUMBER )
                loadRow( row, i );
            else
                throw new Exception( "The file does not have the expected format" );
        }
    }*/
    
    
    /*private void loadRow( String row, int numRow ) throws Exception
    {

        for( int i = 0; i < row.length( ); i++ )
        {
            int value;
            try
            {
                value = Integer.parseInt( row.substring( i, i + 1 ) );
            }
            catch( NumberFormatException e )
            {
                throw new Exception( "The properties file does not have the expected format" );
            }
            Cell cell = new Cell( value );
            board[ numRow ][ i ] = cell;
        }
    }*/
    
    private void loadFila( String fila, int numFila) throws Exception
    {

        for( int i = 0; i < fila.length( ); i++ )
        {
            int v;
            try
            {
                v= Integer.parseInt( fila.substring( i, i + 1 ) );
            }
            catch( NumberFormatException e )
            {
                throw new Exception( "The properties file does not have the expected format545454" );
            }
            Casilla cell = new Casilla( v );
            tabla[ numFila ][ i ] = cell;
        }
    }
    
    
    
    

   
    public void juego( )
    {
    	jugar = true;
    	inicializaMarcado( );

    }

    
    public boolean validar_tabla( int[][] ltabla )
    {
        valores( ltabla);
        boolean areas = validarAreas( );
        boolean filas = validarFilas( );
        boolean columnas = validarColumnas( );
        boolean zeros = validarinterior( );
        return areas && filas && columnas && zeros;

    }

   
    private void valores( int[][] latabla)
    {
        for( int i = 0; i < filas; i++ )
        {
            for( int j = 0; j < numero_columna; j++ )
            {
            	tabla[ i ][ j ].setValorIngresado( latabla[ i ][ j ] );
            }
        }
    }

   
    public void limpiar( )
    {
    	tabla = new Casilla[filas][numero_columna];
    }

   
    public void fin_juego( )
    {
    	jugar= false;
    }

   
    public boolean activar_juego( )
    {
        return jugar;
    }

   


   
    private void inicializaMarcado( )
    {
        for( int i = 0; i < numero_area; i++ )
        {
            Casilla[] cells = getCellsArea( i + 1 );
            int counter = 0;
            while( counter < inicializac)
            {
                int value = generateRandomNumberWithinRange( cells.length );
                Casilla cell = cells[ value ];
                if( !cell.darInicial( ) )
                {
                    counter++;
                    cell.setInicial( );
                }
            }
        }
    }

   
    private int generateRandomNumberWithinRange( int rangeSize )
    {
        return ( int ) ( Math.random( ) * rangeSize );
    }

   
    public Casilla[] getCellsArea( int area )
    {
        Casilla[] cells = new Casilla[filas];

        int rowBeginning = ( ( area - 1 ) / 3 ) * 3;
        int columnBeginning = ( ( area - 1 ) % 3 ) * 3;
        int counter = 0;

        for( int i = rowBeginning; i < rowBeginning + 3; i++ )
        {
            for( int j = columnBeginning; j < columnBeginning + 3; j++ )
            {
                cells[ counter ] = tabla[ i ][ j ];
                counter++;
            }
        }

        return cells;
    }

    
    private boolean validarFilas( )
    {
        boolean answer = true;
        for( int i = 0; i < numero_columna; i++ )
        {
            Casilla[] cells = getRow( i );
            if( thereAreRepeated( cells ) )
            {
                mark( cells );
                answer = false;
            }

        }
        return answer;
    }

   
    private boolean validarColumnas( )
    {
        boolean answer = true;
        for( int i = 0; i < filas; i++ )
        {
            Casilla[] cells = tabla[ i ];
            if( thereAreRepeated( cells ) )
            {
                mark( cells );
                answer = false;
            }
        }
        return answer;
    }

    private boolean validarAreas( )
    {
        boolean answer = true;
        for( int i = 0; i < numero_area; i++ )
        {
            Casilla[] cells = getCellsArea( i + 1 );
            if( thereAreRepeated( cells ) )
            {
                mark( cells );
                answer = false;
            }
        }
        return answer;
    }


    private void mark( Casilla[] cells )
    {
        for( int i = 0; i < cells.length; i++ )
        {
            cells[ i ].marcado( );
        }
    }

   
    private Casilla[] getRow( int numRow )
    {
        Casilla[] cells = new Casilla[numero_columna];
        for( int i = 0; i < filas; i++ )
        {
            cells[ i ] = tabla[ i ][ numRow ];
        }
        return cells;
    }

  
    private boolean thereAreRepeated( Casilla[] cells )
    {
        for( int i = 0; i < cells.length; i++ )
        {
            Casilla cell = cells[ i ];
            for( int j = 0; cell != null && j < cells.length; j++ )
            {
                Casilla cell2 = cells[ j ];
                if( cell.getValorIngresado( ) == cell2.getValorIngresado( ) && i != j && cell.getValorIngresado( ) != 0 )
                    return true;

            }
        }
        return false;
    }

    
    public void unmarkCells( )
    {
        for( int i = 0; i < filas; i++ )
        {
            for( int j = 0; j < numero_columna; j++ )
            {
            	tabla[ i ][ j ].darMarcad( );
            }
        }
    }

   
    private boolean validarinterior( )
    {
        for( int i = 0; i < filas; i++ )
        {
            for( int j = 0; j < numero_columna; j++ )
            {
                Casilla cell = tabla[ i ][ j ];
                if( cell == null || cell.getValorIngresado( ) == 0 )
                    return false;
            }
        }
        return true;
    }

   
    public Casilla[][] getBoard( )
    {
        return tabla;
    }

    

    
    public String method1( )
    {
        return "Answer 1";
    }

    
    public String method2( )
    {
        return "Answer 2";
    }

}