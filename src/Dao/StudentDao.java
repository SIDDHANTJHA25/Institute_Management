/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.FileInputStream;
import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author sid
 */
public class StudentDao {
    
    
    
    public static void insert(JTextField Name,JTextField Father_Name,JTextField Course_Name,JComboBox<String> Branch,JTextField Mobile_No,JTextField Father_Mobile_No,JTextField Dor,JTextField Sin,JTextField Add,JTextField success,JRadioButton Male,JRadioButton Female,String Imgpath){
        
        
        
        
        
         if(!"".equals(Name.getText()))
        {
        try
		{
                    
			 Connection  con=Dao.getMyConnection();
                    
                      
                        
                    String sql="insert into Student_dashboard values(?,?,?,?,?,?,?,?,?,?,?)";	
			PreparedStatement pst=con.prepareStatement(sql);
                        
                        pst.setString(1,Name.getText());
			pst.setString(2,Father_Name.getText());
			pst.setString(3,Course_Name.getText());
                        
                         String Br;
                        
                        Br=Branch.getSelectedItem().toString();
                        
			pst.setString(4, Br);
                        pst.setInt(5,Integer.parseInt(Mobile_No.getText()));
                        pst.setInt(6,Integer.parseInt(Father_Mobile_No.getText()));
                        
                        String Gr;
                        
                        if(Male.isSelected())
                        {
                            Gr=Male.getText();
                        }
                        else
                        {
                            Gr=Female.getText();
                        }
                        pst.setString(7,Gr);
                        pst.setString(8,Dor.getText());
                        pst.setInt(9,Integer.parseInt(Sin.getText()));
                        pst.setString(10,Add.getText());
                        
                        
             
                        FileInputStream fn= new  FileInputStream(Imgpath);
                        pst.setBinaryStream(11, fn,fn.available());
                       
			pst.executeUpdate();
                         success.setText("Successfully Registered !");
                       
                                           
                }
catch(Exception e)
		{
			System.out.println(e);
                        success.setText("Registration Failed !");
		}
    
               Name.setText("");
               Father_Name.setText("");
               Course_Name.setText("");
               Branch.setSelectedIndex(0);
               Mobile_No.setText("");
               Father_Mobile_No.setText("");
               
               Dor.setText("");
               Sin.setText("");
               Add.setText("");
             
        }
        else{
         success.setText("Registration Failed !");
        
        }
    }
    
    public static void show(JPanel pnlStudent,JPanel pnlStudentDetails,JPanel pnlHeader,JPanel pnlD,JTextField stdName,JTextField stdId,JTextField stdMno,JTextField  stdTextName,JTextField stdTextFatherName,JTextField stdTextCourse,JTextField stdTextMobileNo,JTextField successDetails, JLabel lblStd){
         
        
          pnlStudent.removeAll();
          pnlStudent.repaint();
          pnlStudent.revalidate();
          pnlStudent.add(pnlStudentDetails);
          pnlStudent.repaint();
          pnlStudent.revalidate();
          
          pnlHeader.removeAll();
          pnlHeader.repaint();
          pnlHeader.revalidate();
          pnlHeader.add(pnlD);
          pnlHeader.repaint();
          pnlHeader.revalidate();
          
         
      int mob=0,id=0,x=0;
        String name = null;
       if(!"".equals(stdName.getText())){
           name=stdName.getText();
          
       }
       if(!"".equals(stdId.getText())){
             id=Integer.parseInt(stdId.getText());  
             
       }
        if(!"".equals(stdMno.getText())){
            mob=Integer.parseInt(stdMno.getText());
           
       }
       
       
         try {
        
              Connection  con=Dao.getMyConnection();
          
               
                String sql="select * from Student_dashboard where NAME='"+name+"' OR MOBILE_NO='"+mob+"' OR STUDENT_ID_NO='"+id+"'" ;	
		PreparedStatement pst=con.prepareStatement(sql);  
                 ResultSet rs=pst.executeQuery();
                       if(rs.next()) {
                            stdTextName.setText(rs.getString(1));
                            stdTextFatherName.setText(rs.getString(2));
                            stdTextCourse.setText(rs.getString(3));
                            x=rs.getInt(5);
                            stdTextMobileNo.setText(Integer.toString(x)); 
                           Blob b=rs.getBlob(11);
                           byte barr[]=b.getBytes(1, (int)b.length());
                                                  ImageIcon icon=new ImageIcon(barr);
                                                  
                                                   Image img=icon.getImage();
                          Image newImg=img.getScaledInstance(lblStd.getWidth(), lblStd.getHeight(), Image.SCALE_SMOOTH);
                                     ImageIcon image = new ImageIcon(newImg);
                                                  lblStd.setIcon(image);

               successDetails.setText("");
           
           
       }
                       else{
                            successDetails.setText("Not Found !");
                         
                       }
       }catch (Exception e) {
          System.out.println(e);
         successDetails.setText("Not Found !");

       }
             
  
    }
    
}
