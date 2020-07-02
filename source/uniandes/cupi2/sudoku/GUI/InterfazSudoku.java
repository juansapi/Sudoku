
package uniandes.cupi2.sudoku.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.sudoku.Mundo.Casilla;
import uniandes.cupi2.sudoku.Mundo.Sudoku;

/**
 * This is the main application window
 */
public class InterfazSudoku extends JFrame
{

   
    private Sudoku sudoku;

   
    private PanelOpciones extentionPane;

    
    private PanelEncabezado imagePane;

    
    private PanelSudoku boardPane;

    // ---------------------
    // Constructor
    
    public InterfazSudoku( )
    {
        
        sudoku = new Sudoku( );

        
        setLayout( new BorderLayout( ) );
        setSize( 500, 500 );
        setResizable( false );
        setTitle( "Sudoku" );
        getContentPane( ).setBackground( Color.BLACK );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        
        imagePane = new PanelEncabezado( );
        add( imagePane, BorderLayout.NORTH );

        extentionPane = new PanelOpciones( this );
        add( extentionPane, BorderLayout.SOUTH );

        boardPane = new PanelSudoku( this );
        add( boardPane, BorderLayout.CENTER );

        
        JPanel auxiliaryPane1 = new JPanel( );
        auxiliaryPane1.setPreferredSize( new Dimension( 110, 0 ) );
        auxiliaryPane1.setBackground( Color.BLACK );
        add( auxiliaryPane1, BorderLayout.WEST );
        JPanel auxiliaryPane2 = new JPanel( );
        auxiliaryPane2.setPreferredSize( new Dimension( 110, 0 ) );
        auxiliaryPane2.setBackground( Color.BLACK );
        add( auxiliaryPane2, BorderLayout.EAST );
    }

    // -------------------
    // Methods
   
    public void loadGame( )
    {
        
        JFileChooser chooser = new JFileChooser( );
        chooser.setCurrentDirectory( new java.io.File( "./data" ) );
        chooser.setDialogTitle( "Select file" );
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        chooser.setVisible( true );

        if( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            try
            {
               
                Properties propiedades = new Properties( );
                propiedades.load( new FileInputStream( chooser.getSelectedFile( ) ) );
               
                sudoku.loadtabla( propiedades );

                
                sudoku.juego( );
                
                update( );
            }
            catch( Exception e )
            {                
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    
    private void update( )
    {
        boardPane.updateBoard( );
        extentionPane.updateButtons( );
    }

    
    public void validateGame( )
    {
        int[][] theBoard;
        try
        {
            theBoard = boardPane.getMatrix( );
            sudoku.unmarkCells( );
            if( sudoku.validar_tabla( theBoard ) )
            {
                sudoku.fin_juego( );
                drawCompletedBoard( );
                JOptionPane.showMessageDialog( this, "Game successfully completed", "Game Over", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                update( );
            }
        }
        catch( Exception e )
        {            
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        
    }

   
    private void drawCompletedBoard( )
    {
        boardPane.drawCompletedBoard( );
        extentionPane.updateButtons( );
    }

    
    public void displaySolution( )
    {
        sudoku.fin_juego( );
        boardPane.showSolution( );
        extentionPane.updateButtons( );
        sudoku.limpiar( );
    }

    
    public boolean activeGame( )
    {
        return sudoku.activar_juego( );
    }

    
    public Casilla[][] getBoardCells( )
    {
        return sudoku.getBoard( );
    }

    
    public void reqFuncOption1( )
    {
        String result = sudoku.method1( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    
    public void reqFuncOption2( )
    {
        String result = sudoku.method2( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    
    public static void main( String[] args )
    {

        InterfazSudoku gui = new InterfazSudoku( );
        gui.setVisible( true );
    }
}