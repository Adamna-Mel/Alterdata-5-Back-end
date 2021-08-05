package br.com.alterdata.pack.model.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.alterdata.pack.exception.BadGatewayException;

@Component
public class Mailler {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(MensagemEmail mensagem) {
        
		try {
			MimeMessage email = javaMailSender.createMimeMessage();	
			MimeMessageHelper helper = new MimeMessageHelper(email, true);
			    
			helper.setSubject(mensagem.getAssunto());
		    helper.setFrom(mensagem.getRemetente());
		    helper.setText(mensagem.getConteudo(), true);
		         		
		    helper.setTo(mensagem.getDestinatarios()
						.toArray(new String[mensagem.getDestinatarios().size()]));
				
			javaMailSender.send(email);
				
		} catch (MessagingException e) {
			throw new BadGatewayException("Email não pôde ser enviado!");
		}
	}
}
