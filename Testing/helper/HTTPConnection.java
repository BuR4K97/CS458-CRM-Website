package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTTPConnection 
{
    private HttpURLConnection connection;

    public HTTPConnection(String url) throws MalformedURLException, IOException 
    {
        this.connection = (HttpURLConnection) new URL(url).openConnection();
    }

    public String sendRequest(String body) 
    {
        try 
        {
            this.connection.setRequestMethod("POST");
            this.connection.setRequestProperty("Content-Type", "application/json");
            this.connection.setRequestProperty("Accept", "application/json");
            this.connection.setDoOutput(true);

            try(OutputStream out = this.connection.getOutputStream()) 
            {
                byte[] input = body.getBytes("utf-8");
                out.write(input, 0, input.length);			
            } 
            catch (IOException exception)
            {
                exception.printStackTrace();
            }

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream(), "utf-8"))) 
            {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = reader.readLine()) != null)  response.append(responseLine.trim());
                return response.toString();
            } 
            catch (IOException exception) 
            {
                exception.printStackTrace();
            }
        } 
        catch (ProtocolException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }

}