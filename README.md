# Terra Java Client Library

---

The official [Terra](https://tryterra.co) Java client library.

## Installation

---

### Requirements

- Java 11 or later

### Gradle users

Add this dependency to your project's build file:

```groovy
implementation "co.tryterra:terra-client:0.0.1"
```

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>co.tryterra</groupId>
    <artifactId>terra-client</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Usage

---

TerraExample.java

```java
import co.tryterra.terraclient.TerraClientFactory;
import co.tryterra.terraclient.api.TerraApiResponse;
import co.tryterra.terraclient.api.TerraClientV2;
import co.tryterra.terraclient.api.User;
import co.tryterra.terraclient.exceptions.TerraRuntimeException;
import co.tryterra.terraclient.models.Athlete;

public class TerraExample {
    public static void main(String[] args) {
        TerraClientV2 client = TerraClientFactory.getClientV2("YOUR_API_KEY", "YOUR_DEV_ID");

        try {
            User user = client.getUser("YOUR_USER_ID");
            TerraApiResponse<Athlete> response = client.getActivityForUser(user);
            System.out.println(response.getParsedData());
        } catch (TerraRuntimeException ex) {
            ex.printStackTrace();
        }
    }
}
```
