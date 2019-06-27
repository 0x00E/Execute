package com.github.qianniancc.execute.dialog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class Open extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser jfc;
	
	public Open() {
		
		setResizable(false);
		setModal(true);
		setTitle("打开");
		setBounds(100, 100, 681, 545);
		jfc=new JFileChooser();
		jfc=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setAcceptAllFileFilterUsed(true);
		jfc.addActionListener(this);
		jfc.setApproveButtonText("确认");
		jfc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "程序 (*.exe;*.pif;*.com;*.bat;*.cmd)";
			}
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()){
					return true;
				}
				if(endsWith(f.getName(),".exe",".pif",".com",".bat",".cmd")){
					return true;
				}
				return false;
			}
		});
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(jfc);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setVisible(true);
	}
	
	public boolean endsWith(String str,String... ends){
		for(String end:ends){
			if(str.endsWith(end))
				return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac=e.getActionCommand();
		if(ac.equals(JFileChooser.APPROVE_SELECTION)){
			File srcFile=jfc.getSelectedFile();
			if(!srcFile.exists()){
				JOptionPane.showMessageDialog(this,srcFile.getName()+"\n找不到文件。"
						+ "\n请检查文件名是否正确，然后重试。","浏览", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Main.getTextField().setText(srcFile.getAbsolutePath());
		}
		dispose();
		
	}

}
