package org.example.entities;

public class CurrentUser {
    private static Accounts currentUser;

    public static Accounts getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Accounts currentUser) {
        CurrentUser.currentUser = currentUser;
    }
}
