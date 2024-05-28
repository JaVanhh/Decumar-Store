package datn.shopmypham.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Logged {
    private boolean locked;
    private int loginAttempts = 0;
    private String username;
    private String session;
    private String role;
    private LocalDateTime lockTime;
    private LocalDateTime lastAccessTime;

    public boolean isLock() {
        if (!locked) {
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(lockTime)) {
            locked = false;
            return false;
        }

        return  true;
    }

}
