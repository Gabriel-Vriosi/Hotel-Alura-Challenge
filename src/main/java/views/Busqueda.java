package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import dao.HuespedDAO;
import dao.ReservaDAO;
import models.Huesped;
import models.Reserva;
import utils.JPAUtils;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame implements MouseListener, MouseMotionListener{
	
	//panels
	private JPanel contentPane;
	private JPanel header;
	private JTabbedPane panel;
	
	//textfields
	private JTextField txtBuscar;
	
	//tables and models
	private JTable tbHuespedes;
	private JTable tbReservas;
	private JTable tbHuespedesTodos;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloHuespedesTodos;
	private JScrollPane scroll_table_reservas;
	private JScrollPane scroll_tableHuespedes;
	private JScrollPane scroll_tableHuespedesTodos;
	
	//lables and "buttons"
	private JLabel labelAtras;
	private JPanel btnAtras;
	private JLabel labelExit;
	private JPanel btnexit;
	private JPanel btnbuscar;
	private JPanel btnEliminar;
	private JLabel lblEditar;
	private JPanel btnEditar;
	private JLabel lblHintBuscar;
	
	//mouse position
	int xMouse, yMouse;

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setEnabled(true);
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 13));
		txtBuscar.setBounds(549, 112, 193, 23);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(295, 38, 298, 42);
		contentPane.add(lblNewLabel_4);
		
		lblHintBuscar = new JLabel("Nº de reserva:");
		lblHintBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHintBuscar.setEnabled(false);
		lblHintBuscar.setFont(new Font("Arial", Font.PLAIN, 13));
		lblHintBuscar.setBounds(426, 112, 113, 23);
		contentPane.add(lblHintBuscar);
		setResizable(false);
		
		btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(this);
		contentPane.add(btnEditar);
		
		lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		
		/*
		 *Cambia elementos segun la tabla que se esta usando 
		 */
		panel.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        if (panel.getSelectedIndex() == 0) {
		          lblHintBuscar.setText("N° de reserva:");
		          txtBuscar.setEnabled(true);
		          lblEditar.setEnabled(true);
		          btnEditar.setEnabled(true);
		          
		        }else if (panel.getSelectedIndex() == 2) {
		        	lblHintBuscar.setText("Apellido:");
		        	txtBuscar.setEnabled(true);
			          lblEditar.setEnabled(true);
			          btnEditar.setEnabled(true);
			          
				}else {
					lblHintBuscar.setText("");
					txtBuscar.setEnabled(false);
			        lblEditar.setEnabled(false);
			        btnEditar.setEnabled(false);
				}
			}
		});
		contentPane.add(panel);
		
		/*
		 * Para que no se pueda editar las celdas de la tabla
		 * */
		tbReservas = new JTable() {			
			@Override
		    public boolean isCellEditable(int row, int column) {                
		       return false;               
		    };
		};
		tbReservas.setRowSelectionAllowed(false);
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 16));
		tbReservas.setRowHeight(22);
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbReservas.getTableHeader().setReorderingAllowed(false);
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Número de Reserva");
		modeloReservas.addColumn("Fecha Check In");
		modeloReservas.addColumn("Fecha Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pago");
		scroll_table_reservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table_reservas, null);
		scroll_table_reservas.setVisible(true);		
		
		tbHuespedes = new JTable();
		tbHuespedes.setEnabled(false);
		tbHuespedes.setRowSelectionAllowed(false);
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 16));
		tbHuespedes.setRowHeight(22);
		tbHuespedes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbHuespedes.getTableHeader().setReorderingAllowed(false);
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huésped");
		modeloHuesped.addColumn("Nombre/s");
		modeloHuesped.addColumn("Apellido/s");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Teléfono");
		modeloHuesped.addColumn("Número de Reserva");
		scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes con reserva", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);	
		/*
		 * Para que no se pueda editar las celdas de la tabla
		 * */
		tbHuespedesTodos = new JTable() {
			@Override
		    public boolean isCellEditable(int row, int column) {                
		       return false;               
		    };
		};
		tbHuespedesTodos.setRowSelectionAllowed(false);
		tbHuespedesTodos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedesTodos.setFont(new Font("Arial", Font.PLAIN, 16));
		tbHuespedesTodos.setRowHeight(22);
		tbHuespedesTodos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbHuespedesTodos.getTableHeader().setReorderingAllowed(false);
		modeloHuespedesTodos = (DefaultTableModel) tbHuespedesTodos.getModel();
		modeloHuespedesTodos.addColumn("Número de Huésped");
		modeloHuespedesTodos.addColumn("Nombre/s");
		modeloHuespedesTodos.addColumn("Apellido/s");
		modeloHuespedesTodos.addColumn("Fecha de Nacimiento");
		modeloHuespedesTodos.addColumn("Nacionalidad");
		modeloHuespedesTodos.addColumn("Teléfono");
		modeloHuespedesTodos.addColumn("DNI");
		modeloHuespedesTodos.addColumn("E-Mail");
		scroll_tableHuespedesTodos = new JScrollPane(tbHuespedesTodos);
		panel.addTab("Todos los Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedesTodos, null);
		scroll_tableHuespedesTodos.setVisible(true);	

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		header = new JPanel();
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		header.addMouseListener(this);
		header.addMouseMotionListener(this);
		contentPane.add(header);
		
		btnAtras = new JPanel();
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addMouseListener(this);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnexit.addMouseListener(this);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(549, 140, 193, 2);
		contentPane.add(separator_1_2);
		
		btnbuscar = new JPanel();
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(750, 104, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnbuscar.addMouseListener(this);
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnbuscar.add(lblBuscar);
		
		btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(this);
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		fillTables();
	}
	
	public void fillTable_1(List<Huesped> huespedesConReserva) throws SQLException{
		/*
		 * tabla huespedes con reserva
		 */
		while (modeloHuesped.getRowCount() > 0) {
			modeloHuesped.removeRow(0);
		}
		for (Huesped h : huespedesConReserva) {
			for (Reserva r : h.getReservas())
				modeloHuesped.addRow(new Object[] {
						h.getId(),
						h.getNombre(),
						h.getApellido(),
						h.getFechaNacimiento(),
						h.getNacionalidad(),
						h.getTelefono(),
						r.getId()});
		}		
	}
	
	public void fillTables_2(List<Reserva> reservas) throws SQLException{
		/*
		 * tabla reservas
		 */
		while (modeloReservas.getRowCount() > 0) {
			modeloReservas.removeRow(0);
		}
		for (Reserva r : reservas) {
			modeloReservas.addRow(new Object[] {
					r.getId(), 
					r.getFechaEntrada(),
					r.getFechaSalida(),
					r.getValor(),
					r.getFormaDePago()});
		}			
	}
	
	
	public void fillTables_3(List<Huesped> huespedesTodos) throws SQLException{
		/*
		 * tabla todos los huespedes
		 */
		while (modeloHuespedesTodos.getRowCount() > 0) {
			modeloHuespedesTodos.removeRow(0);
		}	
	
		for (Huesped h2 : huespedesTodos) {
			modeloHuespedesTodos.addRow(new Object[] {
					h2.getId(),
					h2.getNombre(),
					h2.getApellido(),
					h2.getFechaNacimiento(),
					h2.getNacionalidad(),
					h2.getTelefono(),
					h2.getDatosExtraHuesped().getDni(),
					h2.getDatosExtraHuesped().getEmail()});
		}		
	}
	
	public void fillTables() {

		try {
			EntityManager em = JPAUtils.getEntityManager();
			HuespedDAO huespedDAO = new HuespedDAO(em);
			ReservaDAO reservaDAO = new ReservaDAO(em);

			fillTable_1(huespedDAO.getAll());
			fillTables_2(reservaDAO.getAll());
			fillTables_3(huespedDAO.getAll());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Algo salió mal");
		}
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
		if (btnbuscar == e.getSource()) {
			EntityManager em = JPAUtils.getEntityManager();
			try {
				HuespedDAO huespedDAO = new HuespedDAO(em);
				ReservaDAO reservaDAO = new ReservaDAO(em);
				String value = txtBuscar.getText();
				
				if (panel.getSelectedIndex() == 0) {
					if (!value.isBlank()) {
						List<Reserva> reservas = new ArrayList<>();
						reservas.add(reservaDAO.getByID(Integer.parseInt(value)));
						fillTables_2(reservas);					
					} else {
						fillTables_2(reservaDAO.getAll());
					}
				}
				if (panel.getSelectedIndex() == 2) {
					if (!value.isBlank()) {
						fillTables_3(huespedDAO.getByApellido(value));					
					} else {
						fillTables_3(huespedDAO.getAll());
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally {
				em.close();
			}
		}
		/*
		 * -----------------------------------------------------------
		 * */
		if (btnEliminar == e.getSource()) {
			EntityManager em = JPAUtils.getEntityManager();
			try {
				HuespedDAO huespedDAO = new HuespedDAO(em);
				ReservaDAO reservaDAO = new ReservaDAO(em);

				/*
				 * tabla reservas
				 */
				if (panel.getSelectedIndex() == 0) {
					int id =
							Integer.parseInt(
									tbReservas.getValueAt(
											tbReservas.getSelectedRow(), 0)
									.toString());
					int respuesta = JOptionPane.showConfirmDialog(
							null, 
							"<html> ¿Está seguro de que desea eliminar la reserva? <br>" +
							"Esta acción no se puede deshacer </html>",
							"Confirmación", 
							JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						em.getTransaction().begin();
						reservaDAO.deleteById(id);
						em.getTransaction().commit();
						em.close();
						JOptionPane.showMessageDialog(null, "Datos borrados exitosamente");
						fillTables();						
					}
				}
				/*
				 * tabla todos los huespedes
				 */
				if (panel.getSelectedIndex() == 2) {
					int id =
							Integer.parseInt(
									tbHuespedesTodos.getValueAt(
											tbHuespedesTodos.getSelectedRow(), 0)
									.toString());
					
					int respuesta = JOptionPane.showConfirmDialog(
							null, 
							"<html> ¿Está seguro de que desea eliminar el huesped? <br>" +
							"Se eliminarán todas las reservas asociadas a el <br>" + 
							"Esta acción no se puede deshacer </html>",
							"Confirmación", 
							JOptionPane.YES_NO_OPTION);
					
					if (respuesta == JOptionPane.YES_OPTION) {
						em.getTransaction().begin();
						huespedDAO.deleteById(id);
						em.getTransaction().commit();
						em.close();
						JOptionPane.showMessageDialog(null, "Datos borrados exitosamente");
						fillTables();						
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Algo salió mal");
				e2.printStackTrace();
			} 
		}
		/*
		 * -----------------------------------------------------------
		 * */
		if (btnEditar == e.getSource()) {
			EntityManager em = JPAUtils.getEntityManager();
			try {
				ReservaDAO reservaDAO = new ReservaDAO(em);
				HuespedDAO huespedDAO = new HuespedDAO(em);
				/*
				 * tabla reservas
				 */

				if (panel.getSelectedIndex() == 0) {
					int id =
							Integer.parseInt(
									tbReservas.getValueAt(
									tbReservas.getSelectedRow(), 0)
									.toString());
					int columnIndex = tbReservas.getSelectedColumn();
					String columnName = tbReservas.getColumnName(columnIndex);
					
					/*
					 * para que no se tome en cuenta la columna de los numeros de reserva y huesped
					 * */
					if (columnIndex > 0) {
						String Inputedit = "";
						Inputedit = JOptionPane.showInputDialog(columnName + ":");
						Reserva reserva = reservaDAO.getByID(id);
						
						switch (columnIndex) {
						case 1:
							reserva.setFechaEntrada(LocalDate.parse(Inputedit));
							break;
						case 2:
							reserva.setFechaSalida(LocalDate.parse(Inputedit));
							break;
						case 3:
							reserva.setValor(Inputedit);
							break;
						case 4:
							reserva.setFormaDePago(Inputedit);
							break;
						default :
							break;
						}
						if (!Inputedit.isBlank()) {
							int respuesta = JOptionPane.showConfirmDialog(
									null, 
									"<html> ¿Guardar cambios? <br>" + columnName + " = " + Inputedit + "</html>",
											"Confirmación",
											JOptionPane.YES_NO_OPTION);
							if (respuesta == JOptionPane.YES_OPTION) {
								em.getTransaction().begin();
								em.merge(reserva);
								em.getTransaction().commit();
								em.close();
								JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
								fillTables();					
							}
						}else {
							JOptionPane.showMessageDialog(null, "No ingreso ningún dato");
						}
					}						
				}
				/*
				 * tabla todos los huespedes
				 */
				if (panel.getSelectedIndex() == 2) {
					int id =
							Integer.parseInt(
									tbHuespedesTodos.getValueAt(
											tbHuespedesTodos.getSelectedRow(), 0)
									.toString());
					int columnIndex = tbHuespedesTodos.getSelectedColumn();
					String columnName = tbHuespedesTodos.getColumnName(columnIndex);
					
					/*
					 * para que no se tome en cuenta la columna de los numeros de reserva y huesped
					 * */
					if (columnIndex > 0) {
						String Inputedit = "";
						Inputedit = JOptionPane.showInputDialog(columnName + ":");
						Huesped huesped = huespedDAO.getByID(id);
						
						switch (columnIndex) {
						case 1:
							huesped.setNombre(Inputedit);
							break;
						case 2:
							huesped.setApellido(Inputedit);
							break;
						case 3:
							huesped.setFechaNacimiento(LocalDate.parse(Inputedit));
							break;
						case 4:
							huesped.setNacionalidad(columnName);
							break;
						case 5:
							huesped.setTelefono(columnName);
							break;
						case 6:
							huesped.getDatosExtraHuesped().setDni(Inputedit);;
							break;
						case 7:
							huesped.getDatosExtraHuesped().setEmail(Inputedit);
							break;
						default :
							break;
						}
						if (!Inputedit.isBlank()) {
							int respuesta = JOptionPane.showConfirmDialog(
									null, 
									"<html> ¿Guardar cambios? <br>" +
											columnName + " = " + Inputedit + "</html>",
											"Confirmación",
											JOptionPane.YES_NO_OPTION);
							if (respuesta == JOptionPane.YES_OPTION) {
								em.getTransaction().begin();
								em.merge(huesped);
								em.getTransaction().commit();
								em.close();
								JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
								fillTables();					
							}
						}else {
							JOptionPane.showMessageDialog(null, "No ingreso ningún dato");
						}
					}
				}
				
			}catch (Exception e2) {
				e2.printStackTrace();
			}finally {
				em.close();
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
			btnexit.setBackground(Color.RED);	
			labelExit.setForeground(Color.WHITE);
		}
		if (btnAtras == e.getSource()) {
			btnAtras.setBackground(new Color(12, 138, 199));
			labelAtras.setForeground(Color.WHITE);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if (btnexit == e.getSource()) {
			btnexit.setBackground(Color.WHITE);		
			labelExit.setForeground(Color.BLACK);
		}
		if (btnAtras == e.getSource()) {
			 btnAtras.setBackground(Color.WHITE);
		     labelAtras.setForeground(Color.BLACK);
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
