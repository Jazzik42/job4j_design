package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Ivan ivanov";
        int age = 33;
        short height = 170;
        char nick = 'I';
        long ipAddress = 4151514L;
        double weight = 88.8D;
        boolean marriage = true;
        float foot = 42F;
        LOG.debug("User info name : {}, age : {}, height : {},"
                        + "nick : {}, ipAddress : {}, weight : {}, "
                        + "marriage : {}, foot : {} ", name, age, height, nick, ipAddress,
                weight, marriage, foot);
    }
}

