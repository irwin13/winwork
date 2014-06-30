package com.irwin13.winwork.basic.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 30/06/2014 19:30
 */
public class D3Encryption {

    private static final Logger LOGGER = LoggerFactory.getLogger(D3Encryption.class);

    public static String encrypt(String message) {
        LOGGER.debug("string to be encrypted = {}", message);

        int evenAdd = randomInt();
        int oddAdd = randomInt();

        char[] messageArray = message.toCharArray();
        char[] cipher = new char[messageArray.length + 4];

        for (int index = 0; index < messageArray.length; index++) {
            char singleChar = messageArray[index];
            if (index % 2 == 0) {
                singleChar += evenAdd;
            } else {
                singleChar += oddAdd;
            }
            cipher[index] = singleChar;
        }

        cipher[cipher.length - 2] = (switchIntToChar(cipher, evenAdd));
        cipher[cipher.length - 1] = (switchIntToChar(cipher, oddAdd));

        return String.valueOf(cipher);

    }

    public static String decrypt(String message) {
        LOGGER.debug("string to be decrypted = {}", message);
        char[] messageArray = message.toCharArray();

        int evenAdd = switchCharToInt(messageArray, messageArray[messageArray.length - 2]);
        int oddAdd = switchCharToInt(messageArray, messageArray[messageArray.length - 1]);

        char[] realMessage = new char[messageArray.length - 4];

        for (int i = 0; i < realMessage.length; i++) {
            char singleChar = messageArray[i];
            if (i % 2 == 0) {
                singleChar -= evenAdd;
            } else {
                singleChar -= oddAdd;
            }

            realMessage[i] = singleChar;
        }

        return String.valueOf(realMessage);
    }

    private static int randomInt() {
        return (int) (Math.random() * 10);
    }

    private static char switchIntToChar(char[] chars, int number) {
        char c = 'i';
        if (chars.length % 2 == 0) {
            if (number == 1) {
                c =  'a';
            }
            if (number == 1) {
                c =  '@';
            }
            if (number == 2) {
                c =  'g';
            }
            if (number == 3) {
                c =  ':';
            }
            if (number == 4) {
                c =  'P';
            }
            if (number == 5) {
                c =  ',';
            }
            if (number == 6) {
                c =  'v';
            }
            if (number == 7) {
                c =  '}';
            }
            if (number == 8) {
                c =  'Q';
            }
            if (number == 9) {
                c =  'x';
            }
        } else {
            if (number == 0) {
                c =  'M';
            }
            if (number == 1) {
                c =  '%';
            }
            if (number == 2) {
                c =  'k';
            }
            if (number == 3) {
                c =  'R';
            }
            if (number == 4) {
                c =  '?';
            }
            if (number == 5) {
                c =  'd';
            }
            if (number == 6) {
                c =  'Y';
            }
            if (number == 7) {
                c =  '$';
            }
            if (number == 8) {
                c =  '(';
            }
            if (number == 9) {
                c =  'N';
            }
        }
        return c;
    }

    private static int switchCharToInt(char[] chars, char number) {
        char c = 0;
        if (chars.length % 2 == 0) {
            if (number == 'a') {
                c = 0;
            }
            if (number == '@') {
                c = 1;
            }
            if (number == 'g') {
                c = 2;
            }
            if (number == ':') {
                c = 3;
            }
            if (number == 'P') {
                c = 4;
            }
            if (number == ',') {
                c = 5;
            }
            if (number == 'v') {
                c = 6;
            }
            if (number == '}') {
                c = 7;
            }
            if (number == 'Q') {
                c = 8;
            }
            if (number == 'x') {
                c = 9;
            }
        } else {
            if (number == 'M') {
                c = 0;
            }
            if (number == '%') {
                c = 1;
            }
            if (number == 'k') {
                c = 2;
            }
            if (number == 'R') {
                c = 3;
            }
            if (number == '?') {
                c = 4;
            }
            if (number == 'd') {
                c = 5;
            }
            if (number == 'Y') {
                c = 6;
            }
            if (number == '$') {
                c = 7;
            }
            if (number ==  '(') {
                c = 8;
            }
            if (number == 'N') {
                c = 9;
            }
        }
        return c;
    }
}
