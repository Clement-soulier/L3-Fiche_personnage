package fr.clement_tristan_olivier.liste_personnage.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class passwordUtils {
   public static String hashPassword(String password){
    Argon2 argon2 = Argon2Factory.create();
    return argon2.hash(10, 65536, 1, password);
   } 

    public static boolean verifyPassword(String hash, String password){
     Argon2 argon2 = Argon2Factory.create();
     return argon2.verify(hash, password);
    }
}
