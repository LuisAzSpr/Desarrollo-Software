package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.MessagingException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserNotificationTest {

    @Mock
    private MailServer mailServer;
    @InjectMocks
    private UserNotifications userNotifications;
    List<String> arrayOfUsers = Arrays.asList("luis.azana.v@uni.pe","pldelacruzv@gmail.com");

    @Test
    void verificateEmailSendCorrectly() throws Exception {
        when(mailServer.sendEmail("Cabecera","cuerpo",arrayOfUsers))
                .thenReturn(true);
        userNotifications = new UserNotifications(mailServer,arrayOfUsers);
        assertThat(userNotifications.sendEmailToUsers("Cabecera","cuerpo"))
                .isEqualTo(true);
    }

    @Test
    void verificateEmailSendIncorrectlyForNoUsers() throws Exception{
        when(mailServer.sendEmail("Cabecera","cuerpo",Arrays.asList("")))
                .thenThrow(MessagingException.class);
        userNotifications = new UserNotifications(mailServer,Arrays.asList(""));
        assertThrows(MessagingException.class, () -> {
            userNotifications.sendEmailToUsers("Cabecera","cuerpo");
        });
    }

    @Test
    void verificateEmailSendIncorrectlyForNoHeader() throws Exception{
        when(mailServer.sendEmail("","cuerpo",arrayOfUsers))
                .thenThrow(MessagingException.class);
        userNotifications = new UserNotifications(mailServer,arrayOfUsers);
        assertThrows(MessagingException.class,()->{
            userNotifications.sendEmailToUsers("","cuerpo");
        });
    }

}
