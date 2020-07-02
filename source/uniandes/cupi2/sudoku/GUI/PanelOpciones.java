

package uniandes.cupi2.sudoku.GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Extension management class
 */
public class PanelOpciones extends JPanel implements ActionListener
{

    // -----------------
    // Constants


    
    private static final String OPTION_1 = "OPTION_1";

   
    private static final String OPTION_2 = "OPTION_2";

    
    private static final String OPEN_FILE = "OPEN_FILE";

    
    private static final String VALIDATE = "VALIDATE";

   
    private static final String LOAD_GAME = "LOAD_GAME";

    
    
    private InterfazSudoku principal;

   
    private JButton btnOption1;

    private JButton btnOption2;

    
    private JButton btnFile;

   
    private JButton btnValidate;

   
    private JButton btnDisplaySolution;

    // ----------------------
    // Constructor


    
    public PanelOpciones( InterfazSudoku window )
    {
        principal = window;

        TitledBorder border = new TitledBorder( "Options" );
        border.setTitleColor( Color.WHITE );
        setBorder( border );
        setLayout( new GridLayout( 1, 2 ) );
        setBackground( Color.BLACK );

       
        btnFile = new JButton( "Load" );
        btnFile.setActionCommand( OPEN_FILE );
        btnFile.addActionListener( this );
        add( btnFile );

        // Button Validate
        btnValidate = new JButton( "Validate" );
        btnValidate.setActionCommand( VALIDATE );
        btnValidate.addActionListener( this );
        btnValidate.setEnabled( false );
        add( btnValidate );

        // Button display solution
        btnDisplaySolution = new JButton( "Solution" );
        btnDisplaySolution.setActionCommand( LOAD_GAME );
        btnDisplaySolution.addActionListener( this );
        btnDisplaySolution.setEnabled( false );
        add( btnDisplaySolution );

        // Botón option 1
        btnOption1 = new JButton( "Option 1" );
        btnOption1.setActionCommand( OPTION_1 );
        btnOption1.addActionListener( this );
        add( btnOption1 );

        // Botón opción 2
        btnOption2 = new JButton( "Option 2" );
        btnOption2.setActionCommand( OPTION_2 );
        btnOption2.addActionListener( this );
        add( btnOption2 );

    }

    // --------------
    // Methodos
   

   
    public void actionPerformed( ActionEvent event )
    {
        if( OPTION_1.equals( event.getActionCommand( ) ) )
        {
            principal.reqFuncOption1( );
        }
        else if( OPTION_2.equals( event.getActionCommand( ) ) )
        {
            principal.reqFuncOption2( );
        }
        else if( OPEN_FILE.equals( event.getActionCommand( ) ) )
        {
            principal.loadGame( );
        }
        else if( VALIDATE.equals( event.getActionCommand( ) ) )
        {
            principal.validateGame( );
        }
        else if( LOAD_GAME.equals( event.getActionCommand( ) ) )
        {
            principal.displaySolution( );
        }
    }

   
    public void updateButtons( )
    {
        if( principal.activeGame( ) )
        {
            btnValidate.setEnabled( true );
            btnDisplaySolution.setEnabled( true );
        }
        else
        {
            btnValidate.setEnabled( false );
            btnDisplaySolution.setEnabled( false );
        }
    }

}
