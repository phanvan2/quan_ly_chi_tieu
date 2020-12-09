package baithicuoiki;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Password extends JFrame implements ActionListener{
	JPasswordField tf;
	JLabel er,error;
	public Password(String s) {
		super(s);
		this.setLayout(new BorderLayout());
		JPanel a = new JPanel();
		a.setLayout(new GridLayout(2,2));
		JLabel p = new JLabel("Nhập mk");
		a.add(p);
		tf= new JPasswordField();
		tf.setEchoChar('*');
		a.add(tf);
		 er=new JLabel("error");
		er.setForeground(Color.red);
		er.setVisible(false);
		a.add(er);
		 error= new JLabel("Nhâp mật khẩu sai!");
		error.setForeground(Color.red);
		error.setVisible(false);
		a.add(error);
		this.add(a,"Center");
		JButton b= new JButton("OK");
		b.addActionListener(this);
		this.add(b,"South");
		JLabel mk = new JLabel("Mời bạn nhâp mật khẩu:");
		this.add(mk,"North");
		a.setBackground(Color.CYAN);
		this.setLocation(550,200);
		this.setSize(450, 200);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e)  {
		if (e.getActionCommand().equals("OK"))
			if(tf.getText().equals("1234")) {
				this.dispose();
				QuanLiCT a = new QuanLiCT("Quản lý thu chi cá nhân");
				a.setLocation(550, 200);
				a.setSize(800,600);
				a.setVisible(true);
				}
			else {
				er.setVisible(true);
				error.setVisible(true);	
		}
	}
}
