package dev.carpena.usermanagement.domain;

import java.util.UUID;

final public class UserId {
    final private UUID id;

    public UserId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        return obj == this || this.id == ((UserId) obj).id;
    }
}
