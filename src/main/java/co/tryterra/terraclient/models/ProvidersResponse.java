package co.tryterra.terraclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Data
@NoArgsConstructor
@Setter(AccessLevel.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersResponse {
    @JsonProperty("providers")
    private List<String> providers;
    @JsonProperty("sdk_providers")
    private List<String> sdkProviders;
    private String status;
}
