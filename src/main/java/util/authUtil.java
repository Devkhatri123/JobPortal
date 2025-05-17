/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Dev khatri
 */
public class authUtil {
    public static String hashPassword(String plainPassword){
      return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public static boolean checkhashedPw(String plainPw,String hashedPw){
        return BCrypt.checkpw(plainPw, hashedPw);
    }
}
