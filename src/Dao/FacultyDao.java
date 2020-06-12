/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.awt.Image;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author sid
 */
public class FacultyDao {
    
    
      public static void insert(JTextField Name,JTextField Father_Name,JTextField Course_Name,JComboBox<String> Branch,JTextField Mobile_No,JTextField Father_Mobile_No,JTextField Dor,JTextField Fin,JTextField Add,JTextField success1,JRadioButton Male,JRadioButton Female,String Imgpath){
        
        
        
        
         if(!"".equals(Name.getText()))
        {
        try
		{
                    
			 Connection  con=Dao.getMyConnection();
                    
                      
                        
                    String sql="insert into faculty_dashboard values(?,?,?,?,?,?,?,?,?,?,?)";	
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
                        pst.setInt(9,Integer.parseInt(Fin.getText()));
                        pst.setString(10,Add.getText());
                        
                        
             
                        FileInputStream fn= new  FileInputStream(Imgpath);
                        pst.setBinaryStream(11, fn,fn.available());
                       
			pst.executeUpdate();
                        success1.setText("Successfully Registered !");
                                           
                }
catch(Exception e)
		{
			System.out.println(e);
                        success1.setText("Registration Failed !");
		}
    
               Name.setText("");
               Father_Name.setText("");
               Course_Name.setText("");
               Branch.setSelectedIndex(0);
               Mobile_No.setText("");
               Father_Mobile_No.setText("");
               
               Dor.setText("");
               Fin.setText("");
               Add.setText("");
             
        }
        else{
            success1.setText("Registration Failed !");
        }
    }
    
    public static void show(JPanel pnlFaculty,JPanel pnlTeacherDetails,JPanel pnlHeader,JPanel pnlD,JTextField tchName,JTextField tchId,JTextField tchMno,JTextField  tchTextName,JTextField tchTextFatherName,JTextField tchTextCourse,JTextField tchTextMobileNo,JTextField successDetails1, JLabel lblTch){
         
        
        pnlFaculty.removeAll();
        pnlFaculty.repaint();
        pnlFaculty.revalidate();
        pnlFaculty.add(pnlTeacherDetails);
        pnlFaculty.repaint();
        pnlFaculty.revalidate();

        pnlHeader.removeAll();
        pnlHeader.repaint();
        pnlHeader.revalidate();
        pnlHeader.add(pnlD);
        pnlHeader.repaint();
        pnlHeader.revalidate();
          
         
      int mob=0,id=0,x=0;
        String name = null;
       if(!"".equals(tchName.getText())){
           name=tchName.getText();
          
       }
       if(!"".equals(tchId.getText())){
             id=Integer.parseInt(tchId.getText());  
             
       }
        if(!"".equals(tchMno.getText())){
            mob=Integer.parseInt(tchMno.getText());
           
       }
       
       
         try {
        
              Connection  con=Dao.getMyConnection();
          
               
               String sql="select * from faculty_dashboard where NAME='"+name+"' OR MOBILE_NO='"+mob+"' OR FACULTY_ID_NO='"+id+"'" ;
            PreparedStatement pst=con.prepareStatement(sql);
                 ResultSet rs=pst.executeQuery();
            if(rs.next()) {
                tchTextName.setText(rs.getString(1));
                tchTextFatherName.setText(rs.getString(2));
                tchTextCourse.setText(rs.getString(3));
                x=rs.getInt(5);
                tchTextMobileNo.setText(Integer.toString(x));
                Blob b=rs.getBlob(11);
                byte barr[]=b.getBytes(1, (int)b.length());
                ImageIcon icon=new ImageIcon(barr);

                Image img=icon.getImage();
                Image newImg=img.getScaledInstance(lblTch.getWidth(), lblTch.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                lblTch.setIcon(image);
                successDetails1.setText("");
            }
            else{
                successDetails1.setText("Not Found !");
            }
       }catch (Exception e) {
          System.out.println(e);
        
            successDetails1.setText("Not Found !");
       }
    }
             
    
}
