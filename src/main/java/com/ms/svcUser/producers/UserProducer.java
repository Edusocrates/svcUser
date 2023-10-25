package com.ms.svcUser.producers;

import com.ms.svcUser.models.DTO.EmailDTO;
import com.ms.svcUser.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;


    public UserProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }


    @Value("${broker.queue.email.name}")//exchange do tipo default, routing key é o mesmo nome da fila
    private String routingKey;


    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDTO();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!!!");
        emailDto.setText(userModel.getName() + "Seja bem vindo! \n agradecemos o seu cadastro!");

        //converte e envia mensagem
        rabbitTemplate.convertAndSend("",routingKey,emailDto);
    }


}
