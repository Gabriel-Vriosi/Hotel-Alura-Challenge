package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MenuUsuario extends JFrame implements MouseListener, MouseMotionListener{

	//panels
	private JPanel contentPane;
	private JPanel header;
	
	//labels and "buttons"
	private JLabel labelExit;
	private JPanel btnexit;
	private JLabel labelRegistro;
	private JPanel btnRegistro;
	private JLabel lblBusqueda;
	private JPanel btnBusqueda;
	private JLabel lblCrearUsuario;
	private JPanel btnCrearUsuario;

	//mouse position
	int xMouse, yMouse;
	

	/**
	 * Create the frame.
	 */
	public MenuUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		header = new JPanel();
		header.addMouseListener(this);
		header.addMouseMotionListener(this);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(50, 58, 150, 150);
		panelMenu.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		
		btnRegistro = new JPanel();
		btnRegistro.setBounds(0, 255, 257, 56);
		btnRegistro.setBackground(new Color(12, 138, 199));
		btnRegistro.setLayout(null);
		btnRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistro.addMouseListener(this);
		panelMenu.add(btnRegistro);
		
		labelRegistro = new JLabel("Registro de reservas");
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setBounds(25, 11, 205, 34);
		labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistro.add(labelRegistro);
		
		btnBusqueda = new JPanel();
		btnBusqueda.setBounds(0, 312, 257, 56);
		btnBusqueda.setBackground(new Color(12, 138, 199));
		btnBusqueda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBusqueda.setLayout(null);
		btnBusqueda.addMouseListener(this);
		panelMenu.add(btnBusqueda);
		
		lblBusqueda = new JLabel("Búsqueda");
		lblBusqueda.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
		lblBusqueda.setBounds(27, 11, 200, 34);
		lblBusqueda.setHorizontalAlignment(SwingConstants.LEFT);
		lblBusqueda.setForeground(Color.WHITE);
		lblBusqueda.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnBusqueda.add(lblBusqueda);
		
		btnCrearUsuario = new JPanel();
		btnCrearUsuario.setLayout(null);
		btnCrearUsuario.setBackground(new Color(12, 138, 199));
		btnCrearUsuario.setBounds(0, 370, 257, 56);
		btnCrearUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCrearUsuario.addMouseListener(this);
		panelMenu.add(btnCrearUsuario);
		
		lblCrearUsuario = new JLabel("Crear Usuario");
		lblCrearUsuario.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/icon_user2.png")));
		lblCrearUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblCrearUsuario.setForeground(Color.WHITE);
		lblCrearUsuario.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblCrearUsuario.setBounds(27, 11, 200, 34);
		btnCrearUsuario.add(lblCrearUsuario);
		
		header.setLayout(null);
		header.setBackground(new Color(118, 187, 223));
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);
		
		btnexit = new JPanel();		
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(118, 187, 223));
		btnexit.setBounds(891, 0, 53, 36);
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnexit.addMouseListener(this);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setForeground(Color.WHITE);
		btnexit.add(labelExit);
		
	    JPanel panelFecha = new JPanel();
	    panelFecha.setBackground(new Color(118, 187, 223));
	    panelFecha.setBounds(256, 36, 688, 121);
	    contentPane.add(panelFecha);
	    panelFecha.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("Sistema de reservas Hotel Alura");
	    lblNewLabel_1.setBounds(180, 11, 356, 42);
	    panelFecha.add(lblNewLabel_1);
	    lblNewLabel_1.setForeground(Color.WHITE);
	    lblNewLabel_1.setFont(new Font("Roboto", Font.PLAIN, 24));
	    
	    JLabel labelFecha = new JLabel("New label");
	    labelFecha.setBounds(35, 64, 294, 36);
	    panelFecha.add(labelFecha);
	    labelFecha.setForeground(Color.WHITE);
	    labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
	    Date fechaActual = new Date(); 
	    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual); 
	    labelFecha.setText("Hoy es " + fecha); 
	    
	    JLabel lblNewLabel = new JLabel("Bienvenido");
	    lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 24));
	    lblNewLabel.setBounds(302, 234, 147, 46);
	    contentPane.add(lblNewLabel);
	    
	    String textoDescripcion = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
	    JLabel labelDescripcion = new JLabel(textoDescripcion);
	    labelDescripcion.setFont(new Font("Roboto", Font.PLAIN, 17));
	   
	    labelDescripcion.setBounds(312, 291, 598, 66);
	    contentPane.add(labelDescripcion);
	    
	    String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
	    JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
	    labelDescripcion_1.setFont(new Font("Roboto", Font.PLAIN, 17));
	    labelDescripcion_1.setBounds(311, 345, 569, 88);
	    contentPane.add(labelDescripcion_1);
	    
	    JLabel lblNewLabel_3 = new JLabel("- Registro de Reservas y Huéspedes");
	    lblNewLabel_3.setFont(new Font("Roboto", Font.PLAIN, 17));
	    lblNewLabel_3.setBounds(312, 444, 295, 27);
	    contentPane.add(lblNewLabel_3);
	    
	    JLabel lblNewLabel_3_1 = new JLabel("- Edición de Reservas y Huéspedes existentes");
	    lblNewLabel_3_1.setFont(new Font("Roboto", Font.PLAIN, 17));
	    lblNewLabel_3_1.setBounds(312, 482, 355, 27);
	    contentPane.add(lblNewLabel_3_1);
	    
	    JLabel lblNewLabel_3_2 = new JLabel("- Eliminar todo tipo de registros");
	    lblNewLabel_3_2.setFont(new Font("Roboto", Font.PLAIN, 17));
	    lblNewLabel_3_2.setBounds(312, 520, 295, 27);
	    contentPane.add(lblNewLabel_3_2);
	}
    
	/*********************************************************************************
	 * MouseListener
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (btnexit == e.getSource()) {
			CloseConfirm closeConfirm = new CloseConfirm();
			closeConfirm.setVisible(true);
		}
		if (btnRegistro == e.getSource()) {
			ReservasView reservas = new ReservasView();
			reservas.setVisible(true);
			dispose();
		}
		if (btnBusqueda == e.getSource()) {
			Busqueda busqueda = new Busqueda();
			busqueda.setVisible(true);
			dispose();
		}
		if (btnCrearUsuario == e.getSource()) {
			RegistroHuesped registroHuesped = new RegistroHuesped();
			registroHuesped.setVisible(true);
			dispose();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
        if (header == e.getSource()) {
        	xMouse = e.getX();
        	yMouse = e.getY();				
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {			
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (btnexit == e.getSource()) {
			btnexit.setBackground(Color.red);		
		}
		if (btnRegistro == e.getSource()) {
			btnRegistro.setBackground(new Color(118, 187, 223));
		}
		if (btnBusqueda == e.getSource()) {
			btnBusqueda.setBackground(new Color(118, 187, 223));
		}
		if (btnCrearUsuario == e.getSource()) {
			btnCrearUsuario.setBackground(new Color(118, 187, 223));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (btnexit == e.getSource()) {
			btnexit.setBackground(new Color(118, 187, 223));			
		}
		if (btnRegistro == e.getSource()) {
			btnRegistro.setBackground(new Color(12, 138, 199));	
		}
		if (btnBusqueda == e.getSource()) {
			btnBusqueda.setBackground(new Color(12, 138, 199));	
		}
		if (btnCrearUsuario == e.getSource()) {
			btnCrearUsuario.setBackground(new Color(12, 138, 199));	
		}
	}
	
    /*********************************************************************************
     * MouseMotionListener
     */
	@Override
	public void mouseDragged(MouseEvent e) {
        if (header == e.getSource()) {
	        int x = e.getXOnScreen();
	        int y = e.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
		}	
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
}             
