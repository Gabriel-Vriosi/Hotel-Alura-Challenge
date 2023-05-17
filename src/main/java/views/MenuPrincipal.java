package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame implements MouseListener, MouseMotionListener{

	//panels
	private Panel panel;
	private JPanel contentPane;
	
	//header
	private JPanel header;
	
	//labels
	private JLabel lblTitulo;
	private JLabel labelExit;
	
	//buttons
	private JPanel btnexit;
	private JPanel btnLogin;
	
	//mouse position
	int xMouse, yMouse;


	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		panel = new Panel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 0, 910, 537);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(-50, 0, 732, 501);
		imagenFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/menu-img.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/aH-150px.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 500, 910, 37);
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setLayout(null);
		
		JLabel lblCopyR = new JLabel("Desarrollado por Gabriel Vriosi © 2023");
		lblCopyR.setBounds(315, 11, 284, 19);
		lblCopyR.setForeground(new Color(240, 248, 255));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		
		header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.addMouseListener(this);
		header.addMouseMotionListener(this);

		btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.addMouseListener(this);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnLogin = new JPanel(); 
		btnLogin.setBounds(754, 300, 83, 70);
		btnLogin.addMouseListener(this);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(SystemColor.window);

		JLabel imagenLogin = new JLabel("");
		imagenLogin.setBounds(0, 0, 80, 70);
		imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/login.png")));
		
		lblTitulo = new JLabel("LOGIN");
		lblTitulo.setBounds(754, 265, 83, 24);
		lblTitulo.setBackground(SystemColor.window);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(SystemColor.textHighlight);
		lblTitulo.setFont(new Font("Roboto Light", Font.PLAIN, 20));	
		
		/*
		 * adding components (al parecer lo hice 2 veces, me sorprendo)
		 * */
		setContentPane(contentPane);
		contentPane.add(panel);
		panel.add(imagenFondo);
		panel.add(logo);
		panel.add(panel_1);
		panel_1.add(lblCopyR);
		panel.add(lblTitulo);
		panel.add(btnLogin);
		panel.add(header);	
		btnLogin.add(imagenLogin);
		header.add(btnexit);
		btnexit.add(labelExit);
	}
	

    /*********************************************************************************
     * MouseListener
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (btnLogin == e.getSource()) {
			Login login = new Login();
			login.setVisible(true);
			dispose();
		}
		if (btnexit == e.getSource()) {
			CloseConfirm closeConfirm = new CloseConfirm();
			closeConfirm.setVisible(true);
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
			labelExit.setForeground(Color.white);			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (btnexit == e.getSource()) {
			 btnexit.setBackground(Color.white);
		     labelExit.setForeground(Color.black);	
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
