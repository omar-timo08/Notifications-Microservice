package com.example.testkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@EnableFeignClients
@Slf4j
@SpringBootApplication
public class NotificationsApplication {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserProxy userProxy;

    public static void main(String[] args) {
        SpringApplication.run(NotificationsApplication.class, args);
    }
    @KafkaListener(id = "statutId",topics = "Statut")
    public void notif(Transfusion transfusion) throws MalformedURLException {
        List<Users> users=userProxy.findByBloodType(transfusion.getStatutStock());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Appel aux dons");
        for (int i=0; i<users.size();i++){
            message.setTo(users.get(i).getEmail());
            URL url = new URL("http:localhost:3003?name="+users.get(i).getName());
            String emailBody= "Monsieur/Madame "+users.get(i).getName()+". Vu la situation critique de notre stock de sang, et vu que votre groupe sanguin correspond " +
                    "à celui dont on a besoin ("+transfusion.getStatutStock()+")," +
                    " nous vous " +
                    "vous appelons pour effectuer un don de sang dans l'un de nos centres. Veuillez spécifier si vous êtes eligibles au don ou pas en suivant la démarche spécifiée" +
                    "dans lien ci-dessous.\n\n"+url+"\n\n"+
                    "Cordialement  ";
            message.setText(emailBody);
            javaMailSender.send(message);
        }
        log.info("Une transfusion a eu lieu. Statut du stock du sang : {}",transfusion.getStatutStock());
    }
}
