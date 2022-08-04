package tests.gson;

import com.google.gson.Gson;
import test_data.models.LoginCred;

public class TestGSON {
    public static void main(String[] args) {
        LoginCred loginCred = new LoginCred("teo@smt.com","12345678");

        // object to gson
        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCred));

        // gson to object
        String loginCredJSONData = "{\"email\":\"teo@sth.com\",\"password\":\"12345678\"}";
        LoginCred convertedFromJSON = gson.fromJson(loginCredJSONData, LoginCred.class);
        System.out.println(convertedFromJSON);

    }

}
