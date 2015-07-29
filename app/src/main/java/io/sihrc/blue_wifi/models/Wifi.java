package io.sihrc.blue_wifi.models;

/**
 * Created by Chris on 7/29/15.
 */
public class Wifi {
    public int id;
    public String name, desc, password;

    public Wifi(String name, String desc, String password) {
        this.name = name;
        this.desc = desc;
        this.password = password;
    }

    public Wifi(int id, String name, String desc, String password) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.password = password;
    }
}
