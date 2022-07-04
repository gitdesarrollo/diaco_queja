package gt.gob.mineco.diaco.service;

import javax.ejb.Stateless;
import org.apache.http.NameValuePair;
import java.util.List;
import java.util.ArrayList;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.UnsupportedEncodingException;

@Stateless
public class NotificacionServiceImp implements NotificacionService{

    public static final String URL_CONSUMER = "http://128.5.4.59/api/diaco/sendNotificationDiacoConsumer";
    public static final String URL_PERMANENT = "http://128.5.4.59/api/diaco/sendNotificationPermanent";

    @Override
    public Boolean consumerNotification(String to, String link, Boolean flag){

        boolean resp = false;

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("to", to));
        urlParameters.add(new BasicNameValuePair("flag", flag.toString()));
        urlParameters.add(new BasicNameValuePair("link", link));
        try {
            HttpPost post = new HttpPost(URL_CONSUMER);
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            try (CloseableHttpClient httpClient = HttpClients.createDefault(); 
                CloseableHttpResponse response = httpClient.execute(post)){
                    System.out.println("Enviado " + EntityUtils.toString(response.getEntity()));
                    resp = true;
            } catch (Exception e) {
                System.out.println("error en el envio " + e);
            }
        } catch (UnsupportedEncodingException e1) {
            System.out.println("Error dos en el envio " + e1);
        }

        return resp;

    }

    @Override
    public Boolean  permanentCommunitacion(String to, String descripcion, String observacion, Boolean flag){
        boolean resp = false;

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("to", to));
        urlParameters.add(new BasicNameValuePair("descripcion", descripcion));
        urlParameters.add(new BasicNameValuePair("observacion", observacion));
        urlParameters.add(new BasicNameValuePair("flag", flag.toString()));
        try {
            HttpPost post = new HttpPost(URL_PERMANENT);
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            try (CloseableHttpClient httpClient = HttpClients.createDefault(); 
                CloseableHttpResponse response = httpClient.execute(post)){
                    System.out.println("Enviado " + EntityUtils.toString(response.getEntity()));
                    resp = true;
            } catch (Exception e) {
                System.out.println("error en el envio " + e);
            }
        } catch (UnsupportedEncodingException e1) {
            System.out.println("Error dos en el envio " + e1);
        }

        return resp;
    }
    
}
