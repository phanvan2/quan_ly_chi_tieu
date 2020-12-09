	package baithicuoiki;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.sun.rowset.internal.Row;

import javafx.scene.layout.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
public class QuanLiCT extends JFrame implements ActionListener{
	Statement 	stm;
	Connection 	con;
	int selectedrow1 = 0;
	int selectedrow2 = 0;
	Vector vTitle1 = new Vector();	Vector vData1 = new Vector();
	Vector vTitle2 = new Vector();	Vector vData2 = new Vector(); 
	Vector vTitle4 = new Vector();	Vector vData4 = new Vector(); 
	Vector vTitle5 = new Vector();	Vector vData5 = new Vector();
	ScrollPane 	tablereult;
	DefaultTableModel 	model1,	model2,	model4,	model5;
	JTable 		tb1,tb2,tb4,tb5;
	JButton 	them,xoa,sua;
	JTextField 	tientf,	ngaytf,	ghichutf;
	JComboBox 	danhmuctf;
	String 	[]cb1= {"Ăn uống","Áo quần","Giáo dục","Mỹ phẩm","Chi phí Khác"} ;
	JLabel 		tcl,ttl,sdl;
	JTextField	tc,	 tt, sd;
	String 		tc3,tt3,sd3;
	public QuanLiCT(String s)  {
		super(s);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {	
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyCT","sa","1234");
		stm =con.createStatement();
		
		JPanel 	pnMain 	= new JPanel();
		pnMain.setBackground(Color.cyan);
		pnMain.setLayout(new BorderLayout());
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.cyan);
		inputPanel.setLayout(new GridLayout(4,2));
		JLabel danhmuclb=new JLabel("Danh mục : ");
		inputPanel.add(danhmuclb);
		danhmuctf = new JComboBox(cb1);
		inputPanel.add(danhmuctf);
		
		JLabel tienlb = new JLabel("Tiền (nghìn đồng) :");
		inputPanel.add(tienlb);
		tientf = new JTextField(10);
		tientf.setText("");
		inputPanel.add(tientf);
		
		JLabel 	ngaylb 	= new JLabel("Ngày :");
		inputPanel.add(ngaylb);
		ngaytf= new JTextField(10);
		inputPanel.add(ngaytf);
		
		JLabel ghichulb	= new JLabel("Ghi chú : ");
		inputPanel.add(ghichulb);
		ghichutf = new JTextField(10);
		inputPanel.add(ghichutf);
		
		JPanel 	pn 		= new JPanel();
		pn.setLayout(new GridLayout(3,2));
		JLabel tclb = new JLabel("Tổng tiền chi (nghìn đồng) : ");
		pn.add(tclb);
		tc = new JTextField();
		pn.add(tc);
		JLabel ttlb = new JLabel("Tổng tiền thu (nghìn đồng :");
		pn.add(ttlb);
		tt= new JTextField();
		pn.add(tt);
		JLabel sdlb = new JLabel("Số dư : ");
		pn.add(sdlb);
		sd = new JTextField();
		pn.add(sd);
		
		JPanel controlpn = new JPanel();
		controlpn.setBackground(Color.cyan);
		them=new JButton("Nhập khoản chi");
		them.addActionListener(this);
		sua	=new JButton("Sửa khoản chi");
		sua.addActionListener(this);
		xoa = new JButton("Xóa khoản chi");
		xoa.addActionListener(this);
		
		controlpn.add(them);
		controlpn.add(sua);
		controlpn.add(xoa);
	
		reload1();
		model1 = new DefaultTableModel(vData1,vTitle1);
		tb1 =new JTable(model1);
		JScrollPane sc = new JScrollPane(tb1);
		tb1.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			int row = tb1.getSelectedRow();
			selectedrow1 =tb1.getSelectedRow();
			String d=(String)tb1.getValueAt(row,1);
			String t=(String)tb1.getValueAt(row,2);
			String n=(String)tb1.getValueAt(row,3);
			String g=(String)tb1.getValueAt(row,4);
			 danhmuctf.setSelectedItem(d);
			 tientf.setText(t);
			 ngaytf.setText(n);
			 ghichutf.setText(g);
			
			}
		});
		
		reload2();
		model2 	= new DefaultTableModel(vData2,vTitle2);
		tb2		= new JTable(model2);
		JScrollPane sc2 = new JScrollPane(tb2);
		
		tb2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tb2.getSelectedRow();
				selectedrow2 =tb2.getSelectedRow();
				String d = (String)tb2.getValueAt(row, 1);
				String t = (String)tb2.getValueAt(row, 2);
				String n = (String)tb2.getValueAt(row, 3);
				String g = (String)tb2.getValueAt(row, 4);
				
				danhmuctf.setSelectedItem(d);
				tientf.setText(t);
				ngaytf.setText(n);
				ghichutf.setText(g);
				
			}	
		});
	
		JPanel pnc = new JPanel();
		pnc.setLayout(new GridLayout(1,2));
		JPanel pn4 = new JPanel();
		pn4.setLayout(new BorderLayout());
		JPanel pn5 = new JPanel();
		pn5.setLayout(new BorderLayout());
		JLabel bangc = new JLabel("Bảng thống kê chi");
		pn4.add(bangc,"North");
		JLabel bangt = new JLabel("Bảng thống kê thu");
		pn5.add(bangt,"North");
		
		reload4();
		model4 = new DefaultTableModel(vData4,vTitle4) ;
		tb4 = new JTable(model4);
		JScrollPane sc4 = new JScrollPane(tb4);
		pn4.add(sc4,"Center");
		pn4.setBackground(Color.green);
		model5 = new DefaultTableModel(vData5,vTitle5);
		tb5 = new JTable(model5);
		JScrollPane sc5 = new JScrollPane(tb5);
		pn5.add(sc5,"Center");
		pn5.setBackground(Color.green);
		pnc.add(pn4);
		pnc.add(pn5);
		Container con = getContentPane();	
		pnMain.add(inputPanel,BorderLayout.NORTH);
		pnMain.add(controlpn,BorderLayout.SOUTH);
		
		JTabbedPane myTable = new JTabbedPane();
		JPanel pnTab1 = new JPanel();
		pnTab1.setLayout(new BorderLayout());
		pnTab1.add(sc,"Center");
		myTable.add(pnTab1,"Bang chi");
	
		JPanel pnTab2 = new JPanel();
		pnTab2.setLayout(new BorderLayout());
		pnTab2.add(sc2,"Center");
		myTable.add(pnTab2,"Bang thu");
		
		JPanel pnTab3 = new JPanel();
		pnTab3.setBackground(Color.ORANGE);
		pnTab3.setLayout(new BorderLayout());
	
		pnTab3.add(pn,"South");
		pnTab3.add(pnc,"Center");
		myTable.add(pnTab3,"Thong ke");
		
		
		pnMain.add(myTable,BorderLayout.CENTER);
		con.add(pnMain);
		
		myTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if( myTable.getSelectedIndex() == 1){
					danhmuctf.removeAllItems();
					danhmuctf.addItem("Trợ cấp");	danhmuctf.addItem("Tiền thưởng");
					danhmuctf.addItem("Lương");		danhmuctf.addItem("Đầu tư");
					danhmuctf.addItem("Chi phí khác");
					xoa.setText("Xóa khoản thu");
					xoa.setEnabled(true);
					them.setText("Nhập khoản thu");
					them.setEnabled(true);
					sua.setText("Sửa khoản thu");
					sua.setEnabled(true);
				}
				if( myTable.getSelectedIndex() == 0) {
					danhmuctf.removeAllItems();
					danhmuctf.addItem("Ăn uống");	danhmuctf.addItem("Áo Quần");
					danhmuctf.addItem("Giáo dục");	danhmuctf.addItem("Mỹ phẩm");
					danhmuctf.addItem("Đi lại");	danhmuctf.addItem("Tiền nhà");
					danhmuctf.addItem("Điện nước");	danhmuctf.addItem("Chi phí khác");
					xoa.setText("Xóa khoản chi");
					xoa.setEnabled(true);
					them.setText("Nhập khoản chi");
					them.setEnabled(true);
					sua.setText("Sửa khoản chi");
					sua.setEnabled(true);
				}
				if( myTable.getSelectedIndex() == 2) {
					xoa.setEnabled(false);
					them.setEnabled(false);
					sua.setEnabled(false);
					danhmuctf.removeAllItems();
					reload3();
					tc.setText(tc3);
					tt.setText(tt3);
					sd.setText(sd3);
					reload4();
					model4.fireTableDataChanged();
					model5.fireTableDataChanged();
					tientf.setText("");
					ngaytf.setText("");
					ghichutf.setText("");
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void reload1() {
		try {
			vTitle1.clear();
			vData1.clear();
			ResultSet re = stm.executeQuery("select*from TIENCHI order by Thoi_gian desc");
			ResultSetMetaData rsmt=re.getMetaData();
			int num = rsmt.getColumnCount();
			TableColumn col=null;
			for(int i = 1 ; i <= num ; i++) 
				vTitle1.add(rsmt.getColumnLabel(i));
			while(re.next()) {
				Vector row = new Vector(num);
				for(int i = 1 ; i <= num ; i++) {
					row.add(re.getString(i));
				}
			vData1.add(row);
			}
			re.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void reload2() {
		try {
			vTitle2.clear();
			vData2.clear();
			ResultSet re2 = stm.executeQuery("select * from TIENTHU order by Thoi_gian desc");
			ResultSetMetaData rsmt2 = re2.getMetaData();
			int num = rsmt2.getColumnCount();
			TableColumn col =null;
			for(int i = 1 ; i <= num ; i++)
				vTitle2.add(rsmt2.getColumnLabel(i));
			while(re2.next()) {
				Vector row = new Vector(num);
				for(int i = 1 ; i <= num ; i++)
					row.add(re2.getString(i));
				vData2.add(row);
				}
			re2.close();
			}catch(Exception e) {
		System.out.println(e.getMessage());
		}
		
	}
	public void reload3() {
		try {
			ResultSet re3 = stm.executeQuery("Select sum(Tien_chi) from TIENCHI"); 
			re3.next();
			tc3 = re3.getString(1);
			int a = re3.getInt(1);
			re3 = stm.executeQuery("Select sum(Tien_thu) from TIENTHU");
			re3.next();
			tt3 = re3.getString(1);
			int b = re3.getInt(1);
			sd3 = String.valueOf(b-a);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void reload4() {
		try {
			vTitle4.clear();
			vData4.clear();
			 ResultSet re4 = stm.executeQuery("Select Danh_muc, sum(Tien_chi) as Tong_tien_chi from TIENCHI group by Danh_muc");
			ResultSetMetaData rstm = re4.getMetaData();
			int num = rstm.getColumnCount();
			for(int i = 1 ; i <= num ; i++)
				vTitle4.add(rstm.getColumnLabel(i));
			while(re4.next()) {
				Vector row = new Vector(num);
				for(int i = 1 ; i <= num ; i++) 
					row.add(re4.getString(i));
				vData4.add(row);
			}
				vTitle5.clear();
				vData5.clear();
				ResultSet re5 = stm.executeQuery("Select Danh_muc, sum(Tien_thu) as Tong_tien_thu from TIENTHU group by Danh_muc");
				ResultSetMetaData rstm1 = re5.getMetaData();
				int num1 = rstm1.getColumnCount();
				for(int i = 1 ; i <= num1 ; i++)
					vTitle5.add(rstm1.getColumnLabel(i));
				while(re5.next()) {
					Vector row1 = new Vector(num1);
					for(int i = 1 ; i<=num1 ; i++) 
						row1.add(re5.getString(i));
					vData5.add(row1);
			}
			re4.close();
			re5.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		QuanLiCT a = new QuanLiCT("Quản li chi tiêu");
		a.setLocation(550, 200);
		a.setSize(800,600);
		a.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//----------------Bảng chi-------------------
		if(e.getActionCommand().equals("Xóa khoản chi"))
			if(tientf.getText().equals("") == true||ngaytf.getText().equals("") == true) 
					JOptionPane.showMessageDialog(null, "Bạn cần chọn dữ liệu cần xóa");
				else {
					delete1();
			}
		if(e.getActionCommand().equals("Nhập khoản chi"))
			if(tientf.getText().equals("") == true||ngaytf.getText().equals("") == true)
					JOptionPane.showMessageDialog(null,"Ban can nhap du lieu can chen ");
				else 
					Insert1();
		if(e.getActionCommand().equals("Sửa khoản chi"))
			if(tientf.getText().equals("")==true || ngaytf.getText().equals("")==true)
					JOptionPane.showMessageDialog(null,"Bạn cần nhâp dữ liệu !");
				else
					Edit1();
	
		//-----------------Bang thu-----------------
		if(e.getActionCommand().equals("Xóa khoản thu")) {
			if(tientf.getText().equals("") == true||ngaytf.getText().equals("") == true)
					JOptionPane.showMessageDialog(null,"Bạn cần chọn dữ liệu cần xóa");
				else 
					delete2();
		}
		if(e.getActionCommand().equals("Nhập khoản thu")) {
			if(tientf.getText().equals("") == true || ngaytf.getText().equals("") == true )
					JOptionPane.showMessageDialog(null,"Bạn cần nhập dữ liệu cần thêm");
				else 
					Insert2();
		}
		if(e.getActionCommand().equals("Sửa khoản thu")) {
			if(tientf.getText().equals("") == true || ngaytf.getText().equals("") == true)
					JOptionPane.showMessageDialog(null,"Bạn cần nhập dữ liệu !");
				else
					Edit2();
		}
	}
	//---------bang chi----------------
	public void delete1()
	{
		
		try {
			Vector st=(Vector)vData1.elementAt(selectedrow1);
			String sql="Delete from TIENCHI where Ma_chi= "+st.elementAt(0);
			stm.executeUpdate(sql);
			vData1.remove(selectedrow1);
			reload1();
			model1.fireTableDataChanged();
			danhmuctf.setSelectedIndex(1);
			tientf.setText("");
			ngaytf.setText("");
			ghichutf.setText("");
			JOptionPane.showMessageDialog(null, "Xóa thành công");	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Xóa thất bại ");
		}
		
	}
	public void Insert1() {
		try {
			String d=(String)danhmuctf.getSelectedItem();
			String t=tientf.getText();
			String n=ngaytf.getText();
			String g=ghichutf.getText();
			String sql="insert into TIENCHI values (N'"+d+"',"+t+",'"+n+"','"+g+"')";
			stm.executeUpdate("set dateformat dmy");
			stm.executeUpdate(sql);
			reload1();
			model1.fireTableDataChanged();
			danhmuctf.setSelectedIndex(1);
			tientf.setText("");
			ngaytf.setText("");
			ghichutf.setText("");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	public void Edit1() {
		try {
			Vector 	st = (Vector)vData1.elementAt(selectedrow1);
			String 	d  = (String)danhmuctf.getSelectedItem();
			String 	t  = tientf.getText();
			String 	n  = ngaytf.getText();
			String 	g  =ghichutf.getText();
			String 	sql="Update TIENCHI set Danh_muc = N'"+d+"',Tien_chi ="+t+",Thoi_gian ='"+n+"',Ghi_chu =N'"+g+"' where Ma_chi = "+st.elementAt(0);
			stm.executeUpdate("set dateformat dmy");
			stm.executeUpdate(sql);
			reload1();
			model1.fireTableDataChanged();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Sủa dữ liệu thất bại");
		}
	}
	//----------------bang thu------------------
	public void delete2() {
		try {
		Vector st=(Vector)vData2.elementAt(selectedrow2);
		String sql="delete from TIENTHU where Ma_thu="+st.elementAt(0);
		stm.executeUpdate(sql);
		vData2.remove(selectedrow2);
		reload2();
		model2.fireTableDataChanged();
		danhmuctf.setSelectedIndex(1);
		tientf.setText("");
		ngaytf.setText("");
		ghichutf.setText("");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void Insert2() {
		try {
			String d = (String)danhmuctf.getSelectedItem();
			String t = tientf.getText();
			String n = ngaytf.getText();
			String g = ghichutf.getText();
			String sql = "insert into TIENTHU values (N'"+d+"',"+t+",'"+n+"',N'"+g+"')";
			stm.executeUpdate("set dateformat dmy");
			stm.executeUpdate(sql);
			reload2();
			model2.fireTableDataChanged();
			danhmuctf.setSelectedIndex(1);
			tientf.setText("");
			ngaytf.setText("");
			ghichutf.setText("");
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
	public void Edit2() {
		try {
			Vector st =(Vector)vData2.elementAt(selectedrow2);
			String d  = (String)danhmuctf.getSelectedItem();
			String t  = tientf.getText();
			String n  = ngaytf.getText();
			String g  = ghichutf.getText();
			String sql = "Update TIENTHU set Danh_muc = N'"+d+"',Tien_thu ="+t+",Thoi_gian ='"+n+"',Ghi_chu =N'"+g+"' where Ma_thu = "+st.elementAt(0);
			stm.executeUpdate("set Dateformat dmy");
			stm.executeUpdate(sql);
			reload2();
			model2.fireTableDataChanged();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

