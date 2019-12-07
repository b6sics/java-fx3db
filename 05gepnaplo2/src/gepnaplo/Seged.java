/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepnaplo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author kjg
 */
public class Seged {

    public static String datumido() {
        LocalDate d = LocalDate.now();
        LocalTime t = LocalTime.now();
        return d.toString() + " " + t.toString().substring(0, 8);
    }

    public static String gepnev() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(datumido());
        System.out.println(gepnev());
    }
}
