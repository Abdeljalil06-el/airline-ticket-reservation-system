/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airline.ticket.reservation.system;

public class SessionManager {
    private static String username;

    public static void setUsername(String user) {
        username = user;
    }

    public static String getUsername() {
        return username;
    }
}
