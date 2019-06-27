package com.github.qianniancc.execute.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField textField;
	private static JButton okButton;
	private static Main instance;
	
	public static Main getInstance() {
		if(instance==null)instance=new Main();
		return instance;
	}
	
	public static JTextField getTextField() {
		return textField;
	}
	
	public static void remerber(){
		if(textField.getText().equals(""))okButton.setEnabled(false);
		else okButton.setEnabled(true);
	}

	public Main() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		setTitle("运行");
		setBounds(100, 100, 491, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Windows 将根据您所输入的名称，为您打开相应的程序、<br>文件夹、文档或 Internet 资源。</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(22, 22, 402, 55);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("打开(O):");
		lblNewLabel_1.setBounds(26, 84, 54, 15);
		contentPanel.add(lblNewLabel_1);
		
		{
			textField = new JTextField();
			textField.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					changedUpdate(e);
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					changedUpdate(e);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					remerber();
				}
			});
			textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textField.setBounds(78, 80, 300, 24);
			textField.setColumns(10);
		}
		contentPanel.add(textField);
		{
			JLabel lblNewLabel_2 = new JLabel("使用管理权限创建此任务。");
			lblNewLabel_2.setBounds(26, 173, 220, 15);
			contentPanel.add(lblNewLabel_2);
		}
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					

					public void actionPerformed(ActionEvent e) {
						try {
							if(textField.getText().equals("cmd"))
								Runtime.getRuntime().exec("cmd /c start cmd");
							else
								try {
									Runtime.getRuntime().exec(textField.getText());
								} catch (Exception e1) {
									if(e1.getMessage().contains("系统找不到指定的文件")){
										JOptionPane.showMessageDialog(instance, "Windows 找不到文件 '"+textField.getText()+"'。请确定文件名是否正确后，再试一次。", textField.getText(), JOptionPane.ERROR_MESSAGE);
										return;
									}	
								}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						System.exit(0);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton btnNewButton = new JButton("浏览(B)...");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Open();
					}
				});
				buttonPane.add(btnNewButton);
			}
		}
		setVisible(true);
		remerber();
	}
}
