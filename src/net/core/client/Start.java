package net.core.client;

import net.core.manager.HospitalManager;



public class Start {

    public static void main(String[] args) {
        HospitalManager hm = new HospitalManager();
       
//       hm.getComunidades().forEach(System.out::println);
        int hospitalesTotal = hm.hospitalesTotales();
        System.out.println(hospitalesTotal);
    }

}
