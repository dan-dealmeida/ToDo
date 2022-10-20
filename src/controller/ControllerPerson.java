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
import model.ModelPerson;
import view.ViewCategory;
import view.ViewTask;

/**
 *
 * @author zelen
 */
public class ControllerPerson {
    public static boolean addPerson(String name,String id, javax.swing.JTable tablePerson){
        ModelPerson p = new ModelPerson();
        p.setName(name);
        p.setId(Integer.parseInt(id));
        
        if (name.isEmpty() || id.isEmpty()){
           JOptionPane.showMessageDialog(null, "Empty forms", "Please fill out all forms", JOptionPane.ERROR_MESSAGE);
      
        }else{
            DefaultTableModel model = (DefaultTableModel) tablePerson.getModel();
            model.addRow(new Object[]{name, id});
            return true;
            
        }
        return false;
    }
    public static void deletePerson(javax.swing.JTable table){
        int row = table.getSelectedRow();
        
        if (row<0){
            JOptionPane.showMessageDialog(null, "No row selected", "Select row", JOptionPane.ERROR_MESSAGE);
        } else{
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(row);
        }
        
    }
    public static String open(javax.swing.JTable table){
        int row = table.getSelectedRow();
        String name;
        if (row<0){
            JOptionPane.showMessageDialog(null, "No row selected", "Select row", JOptionPane.ERROR_MESSAGE);
            name = "";
        } else{
            int column = 0;
            name = table.getModel().getValueAt(row, column).toString();
            
            
        }
        
        return name;
        
    }
    
    public static void clearText(javax.swing.JTextField tfName , 
                                     javax.swing.JTextField tfId){
        tfName.setText("");
        tfId.setText("");
    }
    
    public static void load(javax.swing.JTable table, String fileName){
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(file);
            //Metodo para deserialização de objeto
            Vector<Vector> tableData = (Vector<Vector>)input.readObject();
            
            input.close();
            file.close();
            
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < tableData.size(); i++){
                Vector row = tableData.get(i);
                model.addRow(new Object[]{row.get(0), row.get(1)});
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void save(javax.swing.JTable table, String fileName){
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
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
