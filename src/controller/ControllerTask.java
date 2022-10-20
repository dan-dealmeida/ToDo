/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelTask;
/**
 *
 * @author zelen
 */
public class ControllerTask {
    public static boolean addTask(String name, String date, String description,
                                    javax.swing.JTable tableTasks){
        ModelTask t = new ModelTask();
        t.setName(name);
        t.setDate(date);
        t.setDescription(description);
        
        if (name.isEmpty() || date.isEmpty() || description.isEmpty()){
           JOptionPane.showMessageDialog(null, "Empty forms", "Please fill out all forms", JOptionPane.ERROR_MESSAGE);
      
        }else{
            DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
            model.addRow(new Object[]{name, date, description});
            return true;
            
        }
        return false;
    }
    public static void deleteTask(javax.swing.JTable tableTasks){
        int row = tableTasks.getSelectedRow();
        
        if (row<0){
            JOptionPane.showMessageDialog(null, "No row selected", "Select row", JOptionPane.ERROR_MESSAGE);
        } else{
            DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
            model.removeRow(row);
        }
        
    }
    
    public static void editTask(javax.swing.JTable tableTasks, javax.swing.JTextField tfName , 
                                     javax.swing.JTextField tfDate,
                                     javax.swing.JTextField tfDescription){
        int row = tableTasks.getSelectedRow();
        
        if (row<0){
            JOptionPane.showMessageDialog(null, "No row selected", "Select row", JOptionPane.ERROR_MESSAGE);
        } else{
            DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
            String name =model.getValueAt(row, 0).toString();
            String date =model.getValueAt(row, 1).toString();
            String description =model.getValueAt(row, 2).toString();
            
            tfName.setText(name);
            tfDate.setText(date);
            tfDescription.setText(description);
            
            model.removeRow(row);
        }
        
    }
    
    public static void clearText(javax.swing.JTextField tfName , 
                                     javax.swing.JTextField tfDate,
                                     javax.swing.JTextField tfDescription ){
        tfName.setText("");
        tfDate.setText("");
        tfDescription.setText("");
    }
    
    public static void load(javax.swing.JTable tableTasks, String fileName){
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            //Metodo para deserialização de objeto
            Vector<Vector> tableData = (Vector<Vector>)input.readObject();
            
            input.close();
            file.close();
            
            
            DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
            for (int i = 0; i < tableData.size(); i++){
                Vector row = tableData.get(i);
                model.addRow(new Object[]{row.get(0), row.get(1), row.get(2)});
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void save(javax.swing.JTable tableTasks, String fileName){
        
        DefaultTableModel model = (DefaultTableModel) tableTasks.getModel();
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
