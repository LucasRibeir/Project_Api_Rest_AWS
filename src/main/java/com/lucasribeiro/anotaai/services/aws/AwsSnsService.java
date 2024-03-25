package com.lucasribeiro.anotaai.services.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    AmazonSNS snsCliente;
    Topic catalogTopic;

    public AwsSnsService(AmazonSNS snsCliente, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
        this.snsCliente = snsCliente;
        this.catalogTopic = catalogTopic;
    }

    public void pusblishMessage(MessageDto message) {
        this.snsCliente.publish(catalogTopic.getTopicArn(), message.toString());
    }
}
