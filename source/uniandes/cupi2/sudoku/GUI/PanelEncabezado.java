
package uniandes.cupi2.sudoku.GUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Class for the header image
 */
public class PanelEncabezado extends JPanel
{

    // --------------------
    // Constructors
   
    public PanelEncabezado( )
    {
        JLabel image = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/title.png" );
        
        image = new JLabel( "" );
        image.setIcon( icono );
        add( image );

        setBackground( Color.BLACK );
        setBorder( new LineBorder( Color.BLACK ) );
    }

}
