package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

public class Main {
    public static void main (String [] args) {
        BigInteger p = null; //素数P
        BigInteger g = null;
        BigInteger x = null;

        BigInteger y = null;

        String m0 = "";
        System.out.println("Vui Lòng Nhập Tin Nhắn M:");
        InputStream clav= System.in;
        BufferedReader rd = new BufferedReader(new InputStreamReader(clav));
        try {m0 = rd.readLine();}
        catch(IOException e) {System.out.println(e.getMessage());}

        String m = Elgamal.serializeMsg(m0);
        System.out.println("m = " + m);
        int len = m.length() * 8;

        p = Elgamal.getPrime(len);
        System.out.println("p = "+p.toString());
        g = new BigInteger(len,new Random());
        System.out.println("g = "+g.toString());
        x = new BigInteger(len,new Random());
        System.out.println("x = "+x.toString());

        y = g.modPow(x,p);
        System.out.println("y = "+y.toString());
        // public key: y,g,p
        // private key: x,g,p

        BigInteger k = Elgamal.getPrime(len+1);
        System.out.println("k = "+k.toString());
        //a ≡ g^k ( mod p )
        BigInteger a = g.modPow(k,p);
        System.out.println("a = "+a.toString());

        //b ≡ y^k M ( mod p )
        BigInteger mb = new BigInteger(m);
        System.out.println("mb = "+mb.toString());
        BigInteger b = Elgamal.encrypt(mb,y,k,p);
        System.out.println("b = "+b.toString());

        BigInteger md = Elgamal.decrypt(b,a,x,p);
        System.out.println("md = "+md.toString());

        String ms = Elgamal.deSerializeMsg(md.toString());
        System.out.println("ms = " + ms);
    }
}
