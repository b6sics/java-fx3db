/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workers;

import java.util.Scanner;

/**
 *
 * @author g
 */
public class Workers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB ab = new DB();
        Scanner bill = new Scanner(System.in, "cp1250");
        boolean tovabb = true;
        String nev, szulido, fnev;
        int fizetes;

        while (tovabb) {
            System.out.println("1-listázás 2-új 3-importálás 4-exportálás "
                    + "5-emelés 6-törlés 0-kilépés");
            System.out.print("Válasz: ");
            String v = bill.nextLine();
            switch (v.charAt(0)) {
                case '1':
                    ab.lista();
                    break;
                case '2':
                    System.out.print("Név: ");
                    nev = bill.nextLine();
                    System.out.print("Születési idő: ");
                    szulido = bill.nextLine();
                    System.out.print("Fizetés: ");
                    fizetes = bill.nextInt();
                    bill.nextLine();
                    ab.uj(nev, szulido, fizetes);
                    break;
                case '3':
                    System.out.print("Fájlnév: ");
                    fnev = bill.nextLine();
                    ab.beolvas(fnev);
                    break;
                case '4':
                    System.out.print("Fájlnév: ");
                    fnev = bill.nextLine();
                    ab.kiir(fnev);
                    break;
                case '5':
                    System.out.print("Százalék: ");
                    int sz = bill.nextInt();
                    bill.nextLine();
                    ab.emel(sz);
                    break;
                case '6':
                    System.out.print("Törölni szeretnél minden dolgozót?(i/n): ");
                    char igen = bill.nextLine().charAt(0);
                    if (igen == 'i') {
                        ab.torol();
                    }
                    break;
                default:
                    tovabb = false;
            }
        }
    }
}
