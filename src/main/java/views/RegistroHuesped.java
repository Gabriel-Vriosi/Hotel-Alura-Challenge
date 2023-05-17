package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import dao.HuespedDAO;
import models.DatosExtraHuesped;
import models.Huesped;
import utils.JPAUtils;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame implements MouseListener, MouseMotionListener{

	//panels
	private JPanel contentPane;
	private JPanel header;
	
	//textfields
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField textDNI;
	
	//datechooser
	private JDateChooser txtFechaN;
	
	//combobox
	private JComboBox<Object> txtNacionalidad;
	
	//mouse position
	int xMouse, yMouse;
	
	//labels and "buttons"
	private JLabel labelExit;
	private JLabel labelAtras;
	private JPanel btnGuardar;
	
	//buttons
	private JPanel btnexit;
	private JPanel btnAtras;
	

	/**
	 * Create the frame.
	 */
	public RegistroHuesped() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.addMouseListener(this);
		header.addMouseMotionListener(this);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(560, 88, 285, 33);
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		txtApellido = new JTextField();
		txtApellido.setBounds(560, 162, 285, 33);
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(new LineBorder(Color.LIGHT_GRAY));

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 300, 285, 33);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		
		/*
		 * demasiadas nacionalidades...
		 * */
		String[] nacionalidades = {"afgana", "alemana", "árabe", "argentina", "australiana", "belga", "boliviana", "brasileña", "camboyana", "canadiense", "chilena", "china", "colombiana", "coreana", "costarricense", "cubana", "danesa", "ecuatoriana", "egipcia", "salvadoreña", "escocesa", "española", "estadounidense", "estonia", "etiope", "filipina", "finlandesa", "francesa", "galesa", "griega", "guatemalteca", "haitiana", "holandesa", "hondureña", "indonesa", "inglesa", "iraquí", "iraní", "irlandesa", "israelí", "italiana", "japonesa", "jordana", "laosiana", "letona", "letonesa", "malaya", "marroquí", "mexicana", "nicaragüense", "noruega", "neozelandesa", "panameña", "paraguaya", "peruana", "polaca", "portuguesa", "puertorriqueño", "dominicana", "rumana", "rusa", "sueca", "suiza", "tailandesa", "taiwanesa", "turca", "ucraniana", "uruguaya", "venezolana", "vietnamita"};
		
		txtNacionalidad = new JComboBox<Object>(nacionalidades);
		txtNacionalidad.setBounds(560, 369, 285, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
	
		JLabel lblNombre = new JLabel("NOMBRE/S");
		lblNombre.setBounds(560, 63, 285, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblApellido = new JLabel("APELLIDO/S");
		lblApellido.setBounds(560, 132, 285, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 275, 285, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 344, 285, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(560, 416, 285, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(560, 441, 285, 33);
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JLabel lblEmail = new JLabel("E-MAIL DE CONTACTO");
		lblEmail.setBounds(560, 485, 285, 20);
		lblEmail.setForeground(SystemColor.textInactiveText);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(560, 516, 285, 33);
		txtEmail.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		btnGuardar = new JPanel();
		btnGuardar.setBounds(723, 560, 122, 35);
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(new Color(12, 138, 199));
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnGuardar.addMouseListener(this);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 910, 634);
		panel.setBackground(new Color(255, 255, 255));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		
		btnexit = new JPanel();
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		btnexit.addMouseListener(this);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnAtras = new JPanel();
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setLayout(null);
		btnAtras.setForeground(Color.BLACK);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.addMouseListener(this);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.BLACK);
		labelAtras.setFont(new Font("Dialog", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setBounds(0, 0, 122, 35);
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JLabel lblTitulo = new JLabel("Create account");
		lblTitulo.setBounds(372, -3, 165, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		
		textDNI = new JTextField();
		textDNI.setFont(new Font("Dialog", Font.PLAIN, 16));
		textDNI.setColumns(10);
		textDNI.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textDNI.setBackground(Color.WHITE);
		textDNI.setBounds(560, 231, 285, 33);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setForeground(SystemColor.textInactiveText);
		lblDNI.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDNI.setBounds(560, 206, 285, 14);
		
		/*
		 * adding components (nunca mas lo hice con los otros frames, se desacomodan
		 * 				      a cada rato)
		 * */
		contentPane.add(panel);
		header.add(btnexit);
		btnexit.add(labelExit);
		header.add(btnAtras);
		btnAtras.add(labelAtras);
		panel.setLayout(null);
		panel.add(header);
		header.add(lblTitulo);
		panel.add(imagenFondo);
		panel.add(logo);
		panel.add(txtNombre);
		panel.add(lblApellido);
		panel.add(txtApellido);
		panel.add(lblFechaN);
		panel.add(txtFechaN);
		panel.add(lblNacionalidad);
		panel.add(txtNacionalidad);
		panel.add(lblTelefono);
		panel.add(txtTelefono);
		panel.add(lblEmail);
		panel.add(txtEmail);
		panel.add(btnGuardar);
		btnGuardar.add(labelGuardar);	
		panel.add(lblNombre);
		panel.add(textDNI);
		panel.add(lblDNI);
		
		
	}
	
	/*
	 * Se sobreentiende lo que hace por el nombre no?
	 * */
	public boolean checkNotNullorEmpty() {
		try {
			if (txtNombre.getText().isBlank()
					|| txtApellido.getText().isBlank()
					|| textDNI.getText().isBlank()
					|| txtFechaN.getDate().toString().isBlank()
					|| txtNacionalidad.getSelectedItem().toString().isBlank()
					|| txtTelefono.getText().isBlank()
					|| txtEmail.getText().isBlank())
			{
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	
	/*********************************************************************************
	 * MouseListener
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		 * -----------------------------------------------------------
		 * */
		if (btnexit == e.getSource()) {
			CloseConfirm closeConfirm = new CloseConfirm();
			closeConfirm.setVisible(true);
		}
		/*
		 * -----------------------------------------------------------
		 * */
		if (btnAtras == e.getSource()) {
			MenuUsuario menuUsuario = new MenuUsuario();
			menuUsuario.setVisible(true);
			dispose();				
		}
		/*
		 * -----------------------------------------------------------
		 * */
		if (btnGuardar == e.getSource()){

			if (!checkNotNullorEmpty()) {
				EntityManager em = JPAUtils.getEntityManager();	
				/*
				 * Creo el huesped por primera vez :)
				 * */
				try {
					String nombres = txtNombre.getText();
					String apellidos = txtApellido.getText();
					String dni = textDNI.getText();
					LocalDate date_nac = txtFechaN.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					String nacionalidad = txtNacionalidad.getSelectedItem().toString();
					String telefono = txtTelefono.getText();
					String email = txtEmail.getText();
					
					Huesped huesped = new Huesped(
							nombres,
							apellidos,
							date_nac,
							nacionalidad,
							telefono);
					
					DatosExtraHuesped dx = new DatosExtraHuesped(dni, email);
					huesped.setDatosExtraHuesped(dx);
					HuespedDAO huespedDao = new HuespedDAO(em);
					
					em.getTransaction().begin();
					huespedDao.save(huesped);
					em.getTransaction().commit();
					Exito exito = new Exito();
					exito.setVisible(true);
					
				} catch (Exception exep) {
					JOptionPane.showMessageDialog(this, "Algo salio mal");
					exep.printStackTrace();
				}finally {
					em.close();
				}
				
			} else {
				JOptionPane.showMessageDialog(this, "Rellene correctamente todos los campos");
			}
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
			labelAtras.setForeground(Color.WHITE);				
		}
		if (btnexit == e.getSource()) {
			btnexit.setBackground(Color.red);
			labelExit.setForeground(Color.white);				
		}
		if (btnGuardar == e.getSource()) {
			btnGuardar.setBackground(new Color(0, 156, 223));			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (btnAtras == e.getSource()) {
			btnAtras.setBackground(Color.WHITE);
			labelAtras.setForeground(Color.BLACK);				
		}
		if (btnexit == e.getSource()) {
			btnexit.setBackground(Color.white);
			labelExit.setForeground(Color.black);				
		}
		if (btnGuardar == e.getSource()) {
			btnGuardar.setBackground(SystemColor.textHighlight);	
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
