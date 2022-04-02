package com.company;


import java.math.BigInteger;
import java.lang.Math;
import java.util.Random;
import java.io.*;

public class Elgamal {

    public static BigInteger getPrime(int bitLenth) {
        BigInteger p = BigInteger.probablePrime(bitLenth, new Random());
        while(!p.isProbablePrime(6)) {
            p.nextProbablePrime();
        }
        return p;
    }

    //b ≡ y^k M ( mod p )
    public static BigInteger encrypt (BigInteger m, BigInteger y, BigInteger k, BigInteger p) {
        BigInteger b = null;
        //BigInteger mi = new BigInteger(m.getBytes());
        b = m.multiply(y.modPow(k,p)).mod(p);
        return b;
    }

    //M ≡ b / a^x ( mod p )
    public static BigInteger decrypt (BigInteger b, BigInteger a, BigInteger x, BigInteger p) {
        BigInteger m = b.multiply(a.modPow(x.negate(),p)).mod(p);
        return m;
    }

    public static String serializeMsg (String str) {
        byte[] b = null;
        StringBuffer sb  = new StringBuffer();
        b = str.getBytes();
        for(byte bb : b){
            sb.append(((int)bb + 256));
        }
        return sb.toString();
    }

    public static String deSerializeMsg (String num) {
        String str = null;
        byte [] b = new byte[num.length()];
        for (int i = 0, j = 0; i <= num.length() - 3; i+=3,j++) {
            b[j] = (byte)(Integer.parseInt(num.substring(i,i+3)) - 256);
        }
        str = new String(b);
        return str;
    }


}

