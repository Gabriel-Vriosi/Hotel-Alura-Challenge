package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.border.LineBorder;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Login extends JFrame implements MouseListener, MouseMotionListener, FocusListener{

	private static final long serialVersionUID = 1L;
	
	//panels
	private JPanel contentPane;
	private JPanel header;
	
	//labels and "buttons"
	private JLabel labelAtras;
	private JPanel btnAtras;
	private JPanel btnexit;
	private JLabel labelExit;
	private JLabel labelGuardar;
	private JPanel btnLogin;
	
	//texfields
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	
	//mouse position
	int xMouse, yMouse;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 788, 527);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		header = new JPanel();
		header.setBounds(0, 0, 788, 36);
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.addMouseListener(this);
		header.addMouseMotionListener(this);
		panel.add(header);
		
		btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(735, 0, 53, 36);
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnexit.addMouseListener(this);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.white);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnexit.add(labelExit);
		
		btnAtras = new JPanel();
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setLayout(null);
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.addMouseListener(this);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.BLACK);
		labelAtras.setFont(new Font("Dialog", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtUsuario.setText("Ingrese su nombre de usuario");
		txtUsuario.setBorder(new LineBorder(SystemColor.inactiveCaption));
		txtUsuario.setForeground(Color.gray);
		txtUsuario.setBounds(65, 256, 324, 32);
		txtUsuario.setColumns(10);
		txtUsuario.addMouseListener(this);
		txtUsuario.addFocusListener(this);
		panel.add(txtUsuario);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setText("********");
		txtContrasena.setForeground(Color.gray);
		txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtContrasena.setBorder(new LineBorder(SystemColor.inactiveCaption));
		txtContrasena.setBounds(65, 353, 324, 32);
		txtContrasena.addMouseListener(this);
		txtContrasena.addFocusListener(this);
		panel.add(txtContrasena);
		
		JLabel labelTitulo = new JLabel("INICIAR SESIÓN");
		labelTitulo.setForeground(SystemColor.textHighlight);
		labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		labelTitulo.setBounds(65, 149, 202, 26);
		panel.add(labelTitulo);
		
		JLabel LabelUsuario = new JLabel("USUARIO");
		LabelUsuario.setForeground(SystemColor.textInactiveText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(65, 219, 107, 26);
		panel.add(LabelUsuario);
		
		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(SystemColor.textInactiveText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblContrasea.setBounds(65, 316, 140, 26);
		panel.add(lblContrasea);
		
		btnLogin = new JPanel();
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setBounds(65, 431, 122, 44);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.addMouseListener(this);
		panel.add(btnLogin);
		
		labelGuardar = new JLabel("ENTRAR");
		labelGuardar.setBounds(0, 0, 122, 44);
		btnLogin.add(labelGuardar);
		labelGuardar.setForeground(SystemColor.controlLtHighlight);
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/lOGO-50PX.png")));
		lblNewLabel_1.setBounds(65, 65, 48, 59);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setBounds(484, 0, 304, 527);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel imgHotel = new JLabel("");
		imgHotel.setBounds(0, 0, 304, 538);
		panel_1.add(imgHotel);
		imgHotel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/img-hotel-login-.png")));
		
	}
	/*
	 * Usuario y contraseña por defecto de la app
	 * */
	private void Verificacion() {
		
		String Usuario= "admin";
		String Contraseña="admin";

        String contraseña= new String (txtContrasena.getPassword());

        if (txtUsuario.getText().equals(Usuario) && contraseña.equals(Contraseña)){
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            dispose();	 
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o Contraseña no válidos");
        }
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
		if (btnAtras == e.getSource()) {
			MenuPrincipal principal = new MenuPrincipal();
			principal.setVisible(true);
			dispose();				
		}
		if (btnLogin == e.getSource()) {
			Verificacion();				
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
		if (btnAtras == e.getSource()) {
			btnAtras.setBackground(new Color(12, 138, 199));
			labelAtras.setForeground(Color.black);				
		}
		if (btnexit == e.getSource()) {
			btnexit.setBackground(Color.red);
			labelExit.setForeground(Color.white);				
		}
		if (btnLogin == e.getSource()) {
			btnLogin.setBackground(new Color(0, 156, 223));			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (btnAtras == e.getSource()) {
			btnAtras.setBackground(Color.WHITE);
			labelAtras.setForeground(Color.BLACK);				
		}
		if (btnexit == e.getSource()) {
			btnexit.setBackground(new Color(12, 138, 199));
			labelExit.setForeground(Color.WHITE);				
		}
		if (btnLogin == e.getSource()) {
			btnLogin.setBackground(SystemColor.textHighlight);	
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

    /*********************************************************************************
     * FocusListener
     */
	@SuppressWarnings("deprecation")
	@Override
	public void focusGained(FocusEvent e) {
		if (txtContrasena == e.getSource() 
			&& txtContrasena.getText().equals("********")
			&& txtContrasena.getForeground().equals(Color.gray)) {

    		txtContrasena.setText("");;
			txtContrasena.setForeground(Color.black);
		}
		if (txtUsuario == e.getSource() && txtUsuario.getText().equals("Ingrese su nombre de usuario")) {
			txtUsuario.setText("");
			txtUsuario.setForeground(Color.black);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void focusLost(FocusEvent e) {
		if (txtContrasena == e.getSource() && txtContrasena.getText().isBlank()) {
			txtContrasena.setText("********");
    		txtContrasena.setForeground(Color.gray);

		}
		if (txtUsuario == e.getSource() && txtUsuario.getText().equals("")) {
			txtUsuario.setText("Ingrese su nombre de usuario");
			txtUsuario.setForeground(Color.gray);
		}
	}
}
