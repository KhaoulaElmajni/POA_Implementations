package me.elmajni.service;

public class SecurityContext {
    private static String username="";
    private static String password="";
    private static String[] roles={};


    public static void authenticate(String u, String p, String[] r){
        if (u.equals("root") && p.equals("123")){
            username=u;
            password=p;
            roles=r;
        }else {
            throw new RuntimeException("Access denied");
        }
    }

    public static boolean hasRole(String r){
        for (String role: roles){
            if (role.equals(r))
                return true;
        }
        return false;
    }
}
