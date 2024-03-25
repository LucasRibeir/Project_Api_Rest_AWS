package com.lucasribeiro.anotaai.config.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSnsConfig {

    @Value("${aws.region}")
    private String region;
    @Value("${aws.accessKeyId}")
    private String accesKeyId;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.sns.topic.catalog.arn}")
    private String catalogTopicArn;

    @Bean
    public AmazonSNS amazonSNSBuilder() {
        var baseCredentials = new BasicAWSCredentials(accesKeyId, secretKey);
        return AmazonSNSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(baseCredentials)).withRegion(region).build();
    }

    @Bean(name = "catalogEventsTopic")
    public Topic snsCatalogTopic() {
        return new Topic().withTopicArn(catalogTopicArn);
    }
}