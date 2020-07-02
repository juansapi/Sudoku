

package uniandes.cupi2.sudoku.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import uniandes.cupi2.sudoku.Mundo.Casilla;
import uniandes.cupi2.sudoku.Mundo.Sudoku;


public class PanelSudoku extends JPanel
{

    // ------------------
    // Constants
   
public static final int NUM_CELLS_AREA = 9;
    private InterfazSudoku principal;
    private Color solvedColor;
    private Color errorColor;
    private Color emptyColor;
    private JTextField[][] cells;

    // -----------------
    // Constructor
     
    public PanelSudoku( InterfazSudoku mainWindow )
    {
   
        principal = mainWindow;
        setLayout( new GridLayout( Sudoku.filas/ 3, Sudoku.numero_columna  / 3 ) );
        setBackground( Color.BLACK );

       
        solvedColor = new Color( 87, 150, 38 );
        errorColor = new Color( 200, 1, 1 );
        emptyColor = new Color( 229, 132, 15 );

        cells = new JTextField[Sudoku.filas][Sudoku.numero_columna ];

       
        JPanel[][] areaPanes = new JPanel[Sudoku.numero_area / 3][Sudoku.numero_area / 3];
        Border border = new BevelBorder( BevelBorder.RAISED );

        for( int i = 0; i < Sudoku.numero_area / 3; i++ )
        {
            for( int j = 0; j < Sudoku.numero_area / 3; j++ )
            {
                areaPanes[ i ][ j ] = new JPanel( );
                areaPanes[ i ][ j ].setLayout( new GridLayout( NUM_CELLS_AREA / 3, NUM_CELLS_AREA / 3 ) );
                areaPanes[ i ][ j ].setBorder( border );
                add( areaPanes[ i ][ j ] );
            }
        }


        for( int i = 0; i < Sudoku.filas; i++ )
        {
            for( int j = 0; j < Sudoku.numero_columna ; j++ )
            {
                cells[ i ][ j ] = new JTextField( "" );
                cells[ i ][ j ].setHorizontalAlignment( JTextField.CENTER );                
                Font fontType = cells[ i ][ j ].getFont( );
                Font newFontType = new Font( fontType.getName( ), Font.PLAIN, fontType.getSize( ) + 3 );
                cells[ i ][ j ].setFont( newFontType );
                cells[ i ][ j ].setPreferredSize( new Dimension( 50, 50 ) );
                cells[ i ][ j ].setEditable( false );
                areaPanes[ i / 3 ][ j / 3 ].add( cells[ i ][ j ] );
            }
        }
    }

    // ---------------
    // Metodos
   
    public void updateBoard( )
    {
        Casilla[][] boardCells = principal.getBoardCells( );

        for( int i = 0; i < Sudoku.filas; i++ )
        {
            for( int j = 0; j < Sudoku.numero_columna ; j++ )
            {
                
                cells[ i ][ j ].setText( "" );
                cells[ i ][ j ].setEditable( true );
                cells[ i ][ j ].setBackground( Color.WHITE );
                Font fontType = cells[ i ][ j ].getFont( );
                Font newFontType = new Font( fontType.getName( ), Font.PLAIN, fontType.getSize( ) );
                cells[ i ][ j ].setFont( newFontType );

             
                if( boardCells[ i ][ j ].darInicial( ) && boardCells[ i ][ j ].darMarcado( ) )
                {
                    cells[ i ][ j ].setBackground( errorColor );
                    cells[ i ][ j ].setEditable( false );
                    cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getvalor( ) );
                    Font font = cells[ i ][ j ].getFont( );
                    Font newFont = new Font( font.getName( ), Font.BOLD, font.getSize( ) );
                    cells[ i ][ j ].setFont( newFont );

                }
                else if( boardCells[ i ][ j ].darInicial( ) )
                {
                    cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getvalor( ) );
                    Font font = cells[ i ][ j ].getFont( );
                    Font newFont = new Font( font.getName( ), Font.BOLD, font.getSize( ) );
                    cells[ i ][ j ].setFont( newFont );
                    cells[ i ][ j ].setEditable( false );
                    cells[ i ][ j ].setBackground( Color.WHITE );
                }
                else if( boardCells[ i ][ j ].darMarcado( ) )
                {
                    cells[ i ][ j ].setBackground( errorColor );
                    if( boardCells[ i ][ j ]. getValorIngresado( ) != 0 )
                    {
                        cells[ i ][ j ].setText( "" + boardCells[ i ][ j ]. getValorIngresado( ) );
                    }
                    else
                    {
                        cells[ i ][ j ].setText( "" );
                    }
                }
                else if( boardCells[ i ][ j ]. getValorIngresado( ) == 0 )
                {
                    cells[ i ][ j ].setBackground( emptyColor );
                }
                else if( boardCells[ i ][ j ]. getValorIngresado( ) != 0 )
                {
                    cells[ i ][ j ].setBackground( Color.WHITE );
                    cells[ i ][ j ].setText( "" + boardCells[ i ][ j ]. getValorIngresado( ) );
                }
            }

        }
    }

   
    public int[][] getMatrix( ) throws Exception
    {
        // Matrix inicializacion
        int[][] board = new int[Sudoku.filas][Sudoku.numero_columna ];

        for( int i = 0; i < Sudoku.filas; i++ )
        {
            for( int j = 0; j < Sudoku.numero_columna ; j++ )
            {
                int value = 0;
                try
                {
                    String content= ( String )cells[ i ][ j ].getText( ); 
                    
                    if(content!=null && !content.equals(""))
                    {
                        value = Integer.parseInt( ( String )cells[ i ][ j ].getText( ) );
                        if(value<1 || value>9)
                        {                        
                            throw new Exception("The content entered in each cell must be a number between 1 and 9");
                        }
                    }
                    
                    board[ i ][ j ] = value;                                                            
                }
                catch( NumberFormatException e )
                {
                    throw new Exception("The content entered in each cell must be a number between 1 and 9");
                }
                
            }
        }

        return board;
    }

    
    public void drawCompletedBoard( )
    {
        for( int i = 0; i < Sudoku.numero_area; i++ )
        {
            for( int j = 0; j < Sudoku.numero_columna ; j++ )
            {
                cells[ i ][ j ].setBackground( solvedColor );
                ;
            }

        }
    }

   
    public void showSolution( )
    {
        Casilla[][] boardCells = principal.getBoardCells( );
        for( int i = 0; i < Sudoku.numero_area; i++ )
        {
            for( int j = 0; j < Sudoku.numero_columna ; j++ )
            {
                cells[ i ][ j ].setBackground( solvedColor );
                cells[ i ][ j ].setText( "" + boardCells[ i ][ j ].getvalor( ) );
                cells[ i ][ j ].setEditable( false );
            }

        }
    }
}
