package app.config;

import app.domain.UserWasCreated;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import shared.domain.EventResolver;

@Configuration
public class EventResolverFactoryBean extends AbstractFactoryBean<Object> {

    @Override
    protected Object createInstance() throws Exception {
        EventResolver eventResolver = new EventResolver();
        eventResolver.register("user.email.was_updated", UserWasCreated.class);
        eventResolver.register("user.email.was_updated", UserWasCreated.class);

        return eventResolver;
    }

    @Override
    public Class<EventResolver> getObjectType() {
        return EventResolver.class;
    }
}
