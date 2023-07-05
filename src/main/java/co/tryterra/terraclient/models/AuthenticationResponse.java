package co.tryterra.terraclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("auth_url")
    private String auth_url; 
    @JsonProperty("user_id")
    private String user_id; 
}
