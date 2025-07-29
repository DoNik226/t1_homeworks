package ru.donik.t1.audit;

import org.aspectj.lang.JoinPoint;
import ru.donik.t1.annotation.WeylandWatchingYou;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditAspect.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AuditAspect(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @AfterReturning("@annotation(annotation)")
    public void afterReturning(JoinPoint joinPoint, WeylandWatchingYou annotation) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String topic = annotation.topic();

        String logMessage = String.format("Метод '%s', Аргументы: %s", methodName, Arrays.toString(args));

        if (topic.isEmpty()) {
            LOGGER.info(logMessage);
        } else {
            kafkaTemplate.send(topic, logMessage);
        }
    }
}