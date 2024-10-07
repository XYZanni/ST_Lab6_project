import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public boolean loadUsers() {
        // In real application, load from a database or file.
        users.put("admin", new User("admin", "adminpass", UserRole.ADMIN));
        users.put("cashier", new User("cashier", "cashierpass", UserRole.CASHIER));
        return true;
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
