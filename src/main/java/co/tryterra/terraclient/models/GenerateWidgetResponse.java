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
public class GenerateWidgetResponse {
    @JsonProperty("expires_in")
    private int expires_in;
    @JsonProperty("session_id")
    private String session_id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("url")
    private String url; 
}
