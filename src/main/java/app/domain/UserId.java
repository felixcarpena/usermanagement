package app.domain;

import shared.domain.AggregateId;

import java.util.UUID;

final public class UserId implements AggregateId {
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
