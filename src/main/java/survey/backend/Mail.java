package survey.backend;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

public class Mail {

    public static void main(String[] args) throws MailjetException {

        MailjetRequest request;
        MailjetResponse response;

        String mailApi = "731b0489b8e1c5f8b27f6e774930efc0";
        String mailSecret="b3ae7418edb711ca539432223eb14c7b";





        ClientOptions options = ClientOptions.builder()
                .apiKey(mailApi)
                .apiSecretKey(mailSecret)
                .build();

        MailjetClient client = new MailjetClient(options);

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "${SENDER}")
                                        .put("Name", "Geoffrey from the back"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "{RECEIVER}")
                                                .put("Name", "You")))
                                .put(Emailv31.Message.SUBJECT, "Suivi post-stagiaire")
                                .put(Emailv31.Message.TEXTPART, "Greetings from Mailjet!")
                                .put(Emailv31.Message.HTMLPART,
                                        "<img src=\"https://media.licdn.com/dms/image/C4D0BAQGj8GUDOoaB0g/company-logo_200_200/0/1587987303565?e=2147483647&v=beta&t=i-CCnedSuyst6egrg_8fJrGYe2YmlAGfR2VUECVk7iw\">"+
                                        "<h3>Cher stagiaire, merci de bien vouloir vous inscrire au nouveau site de suivi post-stagiaire <a href=\"https://localhost:4200/\">Suivi post-stagiaire Aelion</a>!</body><br />En attente de vous retrouver !")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
