import javax.swing.*;
import java.awt.Image;
import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest;

public class Client2 {
    JFrame frame = new JFrame();
    JLabel icon;

    public Client2() {

        //first (time)
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/time")).GET().build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String apiResponse = response.body();
        System.out.println(apiResponse);

        //second (json)
        HttpRequest requetip = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/json")).GET().build();
        HttpResponse<String> ip = null;
        try {
            ip = httpClient.send(requetip, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String apipp = ip.body();
        System.out.println(apipp);


        String path = "http://localhost:8080/api/image";
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<byte[]> reque = null;
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(path))
                    .header("Content-Type", "application/json")
                    .GET().build();
            reque = client.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            System.out.println(reque.body().length);
        } catch (Exception e) {
            System.out.println(e);
        }


        //third (image)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);


        ImageIcon img;
        img = new ImageIcon(reque.body());
        icon = new JLabel(new ImageIcon(img.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH)));
        frame.add(icon);
    }

}
