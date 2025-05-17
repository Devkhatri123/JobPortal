/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.ArrayList;
import model.category;
import DaoImpl.categoryDaoImpl;
/**
 *
 * @author Dev khatri
 */
public class categoryService {
    private categoryDaoImpl categoryDaoImpl;
    public categoryService(){
        this.categoryDaoImpl = new categoryDaoImpl();
    }
    public ArrayList<category> getCategories(){
       return categoryDaoImpl.getCategories();
    }
}
