package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.HuespedDAO;
import dao.ReservaDAO;
import models.Huesped;
import models.Reserva;
import utils.JPAUtils;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Cursor;


@SuppressWarnings("serial")
public class ReservasView extends JFrame implements MouseListener, MouseMotionListener, PropertyChangeListener{

	//panels
	private JPanel contentPane;
	private JPanel header;
	
	//textfields
	public static JTextField txtValor;
	private JTextField txtId;
	
	//datechoosers
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	
	//combobox
	public static JComboBox<Object> txtFormaPago;
	
	//labels and "buttons"
	private JPanel btnexit;
	private JLabel labelExit;
	private JPanel btnAtras;
	private JLabel labelAtras;
	private JPanel btnGuardar;
	
	//mouseposition
	int xMouse, yMouse;
	

	/**
	 * Create the frame.
	 */
	public ReservasView() {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		panel.setLayout(null);
		contentPane.add(panel);
		
		header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseListener(this);
		header.addMouseMotionListener(this);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));
		
		btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnexit.addMouseListener(this);
		panel_1.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);
		
		btnAtras = new JPanel();
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.addMouseListener(this);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		btnAtras.add(labelAtras);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);
		
		JLabel lblCheckIn = new JLabel("INGRESO");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 134, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("SALIDA");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(223, 136, 134, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(90, 47, 248, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 285, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);
		
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 134, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtFechaEntrada.addPropertyChangeListener(this);
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(223, 161, 134, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		txtFechaSalida.addPropertyChangeListener(this);
		panel.add(txtFechaSalida);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(68, 328, 289, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);

		String[] metodosPago = {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"};
		
		txtFormaPago = new JComboBox<Object>(metodosPago);
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.add(txtFormaPago);

		btnGuardar = new JPanel();
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setBounds(238, 493, 122, 35);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnGuardar.addMouseListener(this);
		panel.add(btnGuardar);
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setBounds(0, 0, 122, 35);
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnGuardar.add(labelGuardar);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtId.setBorder(null);
		txtId.setBounds(68, 247, 289, 33);
		txtId.setColumns(10);
		panel.add(txtId);
		
		JLabel lblID = new JLabel("ID DEL HUÉSPED");
		lblID.setForeground(SystemColor.textInactiveText);
		lblID.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblID.setBounds(72, 212, 285, 24);
		panel.add(lblID);
	}
	
	/*
	 * Se sobreentiende lo que hace por el nombre no?
	 * */
	public boolean checkNotNullorEmpty() {
		try {
			if (txtFechaEntrada.getDate().toString().isBlank()
					|| txtFechaSalida.getDate().toString().isBlank()
					|| txtFormaPago.getSelectedItem().toString().isBlank()
					|| txtValor.getText().isBlank()
					|| txtId.getText().isBlank())
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
			MenuUsuario usuario = new MenuUsuario();
			usuario.setVisible(true);
			dispose();				
		}
		/*
		 * -----------------------------------------------------------
		 * */
		if (btnGuardar == e.getSource()) {
			if (checkNotNullorEmpty() == false) {		
				EntityManager em = JPAUtils.getEntityManager();
				/*
				 * busco el huesped por el id
				 * creo la reserva
				 * y la agrego a la lista de reservas del huesped
				 * */
				try {
					ReservaDAO reservaDAO = new ReservaDAO(em);
					HuespedDAO huespedDAO = new HuespedDAO(em);
					
					Huesped huesped = em.find(Huesped.class, Integer.parseInt(txtId.getText()));
					LocalDate fechaEntrada = txtFechaEntrada.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate fechaSalida = txtFechaSalida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					Reserva reserva = new Reserva(
							fechaEntrada,
							fechaSalida,
							txtFormaPago.getSelectedItem().toString(),
							txtValor.getText(),
							huesped);
					
					List<Reserva> reservasLista = huesped.getReservas();
					reservasLista.add(reserva);
					huesped.setReservas(reservasLista);
					
					em.getTransaction().begin();
					huespedDAO.save(huesped);
					reservaDAO.save(reserva);
					em.getTransaction().commit();
					Exito exito = new Exito();
					exito.setVisible(true);
					
				} catch (Exception exep) {
					JOptionPane.showMessageDialog(this,"Algo salio mal");
					exep.printStackTrace();
				}finally {
					em.close();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Rellene correctamente todos los campos");
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
		if (btnexit == e.getSource()) {
			btnexit.setBackground(Color.red);		
		}
		if (btnAtras == e.getSource()) {
			btnAtras.setBackground(new Color(12, 138, 199));
			labelAtras.setForeground(Color.white);
		}
		if (btnGuardar == e.getSource()) {
			btnGuardar.setBackground(new Color(0, 156, 223));			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (btnexit == e.getSource()) {
			btnexit.setBackground(new Color(12, 138, 199));			
		}
		if (btnAtras == e.getSource()) {
			 btnAtras.setBackground(Color.white);
		     labelAtras.setForeground(Color.black);	
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
	
    /*********************************************************************************
     * propertyChangeListener
     */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			txtValor.setText(null);				
			if (txtFechaEntrada.getDate().compareTo(txtFechaSalida.getDate()) < 0) {
				LocalDate fechaEntrada = txtFechaEntrada.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fechaSalida = txtFechaSalida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();				
				long diferenciaEnDias = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
				txtValor.setText(Double.toString(diferenciaEnDias * Reserva.VALOR_RESERVA));
			} 
			if (txtValor.getText().equals("0.0")) {
				txtValor.setText(null);		
			}
		} catch (Exception e) {
			/*
			 * No hace nada, es para que no me tire error nomas al comparar fechas
			 * */
		}
	}
}
