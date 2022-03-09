package app.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import shared.domain.bus.Bus;
import shared.domain.bus.Handler;

@Component
public class BusBeanPostProcessor implements BeanPostProcessor {

    private final Bus bus;
    SpelExpressionParser expressionParser = new SpelExpressionParser();

    @Autowired
    public BusBeanPostProcessor(Bus bus) {
        this.bus = bus;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Subscriber annotation = AnnotationUtils.getAnnotation(bean.getClass(), Subscriber.class);
        if(annotation == null){
            return bean;
        }
        if (!(bean instanceof Handler)) {
            //subscribier deberían ser todos handlers??? (petada)
            return bean;
        }

        this.bus.subscribe((Handler) bean);
        return bean;
    }
}
