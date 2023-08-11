package co.tryterra.terraclient.impl.v2;
import co.tryterra.terraclient.TerraClientFactory;
import co.tryterra.terraclient.api.TerraApiResponse;
import co.tryterra.terraclient.api.TerraClientV2;
import co.tryterra.terraclient.exceptions.TerraRuntimeException;
import co.tryterra.terraclient.models.AuthenticationResponse;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.List;

public class TerraExample {
    public static void main(String[] args) {
        TerraClientV2 client = TerraClientFactory.getClientV2("VjUc0jvOJO6JwQuGT8vfW6QMcQasAp9za2MFgSq5", "ouilive-yOPMDcEnTC");

        try {
            Future<TerraApiResponse<AuthenticationResponse>> response = client.generateAuthenticationURL("GOOGLE"); 
            TerraApiResponse<AuthenticationResponse> apiResponse = response.get();
            // Check if the response is successful
            if (apiResponse.isSuccessful()) {
                // Get the parsed data from the response
                List<AuthenticationResponse> data = apiResponse.getParsedData();

                // Process the data
                for (AuthenticationResponse authResponse : data) {
                    // Access the fields of each AuthenticationResponse object
                    String authUrl = authResponse.getAuth_url();

                    // Print the auth URL
                    System.out.println(authUrl);
                }
            } else {
                // Handle unsuccessful response
                System.out.println("Request was not successful. Response code: " + apiResponse.getResponseCode());
                System.out.println("Error message: " + apiResponse.getMessage());
            }
        } catch (InterruptedException e) {
            // Handle InterruptedException
            e.printStackTrace();
        } catch (ExecutionException e) {
            // Handle ExecutionException
            e.printStackTrace();
        } catch (TerraRuntimeException e) {
            // Handle TerraRuntimeException
            e.printStackTrace();
        }
    }
}