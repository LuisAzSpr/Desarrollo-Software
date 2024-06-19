# ACTIVIDAD : Stubs y Mocks

### Sistema de notificaciones por email

- Paso 1: Definir una interfaz MailServer que abstraiga el envío de correos electrónicos.

```java
public interface MailServer {
  public boolean sendEmail(String cabecera,String cuerpo) throws MessagingException;
}
```


- Paso 2: Crear una clase UserNotifications que dependa de la interfaz MailServer para enviar
  correos.

```java
public class UserNotifications {
  private MailServer mailServer;
  public UserNotifications(MailServer mailServer){
      this.mailServer = mailServer;
  }
  public void sendEmail(String recipient) throws MessagingException {
      mailServer.sendEmail(email,password,recipient);
  }
}
```


- Paso 3: Implementar un mock de MailServer que registre las llamadas a su método sendEmail y
  capture los valores de los parámetros enviados.

```java
@ExtendWith(MockitoExtension.class)
class UserNotificationTest {

    @Mock
    private MailServer mailServer;
    
    @InjectMocks
    private UserNotifications userNotifications;
    
    List<String> arrayOfUsers = Arrays.asList("luis.azana.v@uni.pe", "pldelacruzv@gmail.com");
}

```



- Paso 4: Escribir pruebas unitarias para UserNotifications utilizando el mock para verificar que los
  correos se envíen correctamente.

```java
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

```


- Paso 5: Implementar una clase RealMailServer que use SMTP para enviar correos en un entorno
  de producción.

```java
public class RealMailServer implements MailServer{
    
  private Session session;
  private String username;

  public RealMailServer(String username,String password){
    this.username = username;
    if(username==null || password==null){
      throw new NullPointerException("Ningun campo puede ser nulo");
    }
    Properties properties = stablishProperties();
    session = createSession(properties,username,password);
  }

  private Properties stablishProperties(){
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true");
    return prop;
  }
  private Session createSession(Properties properties,String username,String password){
    return Session.getInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });
  }
  @Override
  public boolean sendEmail(String cabecera, String cuerpo,String recipient) throws MessagingException{
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(recipient)
    );

    message.setSubject(cabecera);
    message.setText(cuerpo);

    Transport.send(message);

    System.out.println("Correo enviado correctamente");
    return true;
  }
}
```



- Paso 6: Integrar UserNotifications en una aplicación de producción, inyectando la
  implementación real de MailServer.


```java
public class UserNotifications {
    
  private MailServer mailServer;
  private String[] users;

  public UserNotifications(String[]users){
    String email = System.getenv("EMAIL_USERNAME");
    String password = System.getenv("EMAIL_PASSWORD");
    this.mailServer = new RealMailServer(email,password);
    this.users = users;
  }

  public void sendEmail(String cabecera,String cuerpo) throws MessagingException {
    String recipient = generarCadena(users);
    mailServer.sendEmail(cabecera,cuerpo,recipient);
  }

  private String generarCadena(String []users){
    return String.join(",", users);
  }

}

```

```java
public class Main {
  public static void main(String[] args) throws MessagingException {
    String[] users = {"luis.azana.v@uni.pe","pldelacruzv@gmail.com"};
    UserNotifications userNotifications = new UserNotifications(users);
    userNotifications.sendEmail("Cabecera del correo", "Cuerpo del correo");
  }
}
```
