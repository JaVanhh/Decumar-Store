package datn.shopmypham.ultil;

import datn.shopmypham.entity.Logged;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CacheUtil {
    public static final Map<String, Logged> LOGGED_MAP = new HashMap<>();

    public static boolean isValidTokenAndUser(String token, String username) throws Exception {

        Logged logged = LOGGED_MAP.get(username);
        if (logged != null) {
            if (token.equals(logged.getSession())) {
                return logged.getLastAccessTime().isAfter(LocalDateTime.now());
            }
        }
        return false;
    }

}
