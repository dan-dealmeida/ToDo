/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelCategory;
import view.ViewCategory;
import view.ViewTask;

/**
 *
 * @author zelen
 */
public class ControllerCategory {
    public static boolean addCategory(String name, javax.swing.JTable tableCategory){
        ModelCategory c = new ModelCategory();
        c.setName(name);
        
        if (name.isEmpty()){
           JOptionPane.showMessageDialog(null, "Empty forms", "Please fill out all forms", JOptionPane.ERROR_MESSAGE);
      
        }else{
            DefaultTableModel model = (DefaultTableModel) tableCategory.getModel();
            model.addRow(new Object[]{name});
            return true;
            
        }
        return false;
    }
    public static void deleteCategory(javax.swing.JTable tableCategory){
        int row = tableCategory.getSelectedRow();
        
        if (row<0){
            JOptionPane.showMessageDialog(null, "No row selected", "Select row", JOptionPane.ERROR_MESSAGE);
        } else{
            DefaultTableModel model = (DefaultTableModel) tableCategory.getModel();
            model.removeRow(row);
        }
        
    }
    public static String open(javax.swing.JTable tableCategory){
        int row = tableCategory.getSelectedRow();
        String name;
        if (row<0){
            JOptionPane.showMessageDialog(null, "No row selected", "Select row", JOptionPane.ERROR_MESSAGE);
            name = "";
        } else{
            int column = 0;
            name = tableCategory.getModel().getValueAt(row, column).toString();
            
            
        }
        
        return name+".txt";
        
    }
    
    public static void clearText(javax.swing.JTextField tfName ){
        tfName.setText("");
    }
    
    public static void load(javax.swing.JTable tableCategory, String fileName){
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            //Metodo para deserialização de objeto
            Vector<Vector> tableData = (Vector<Vector>)input.readObject();
            
            input.close();
            file.close();
            
            
            DefaultTableModel model = (DefaultTableModel) tableCategory.getModel();
            for (int i = 0; i < tableData.size(); i++){
                Vector row = tableData.get(i);
                model.addRow(new Object[]{row.get(0)});
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void save(javax.swing.JTable tableCategory, String fileName){
        
        DefaultTableModel model = (DefaultTableModel) tableCategory.getModel();
        Vector<Vector> tableData = model.getDataVector();
        
        //Salvando objeto em File
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            
            //Metodo para serialização de objeto
            output.writeObject(tableData);
            
            output.close();
            file.close();
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
