[![GitHub license](https://img.shields.io/github/license/tryterra/terra-client-java)](https://github.com/tryterra/terra-client-java/blob/master/LICENSE)
[![test](https://github.com/tryterra/terra-client-java/actions/workflows/test.yml/badge.svg)](https://github.com/tryterra/terra-client-java/actions/workflows/test.yml)
[![docs](https://github.com/tryterra/terra-client-java/actions/workflows/docs.yml/badge.svg)](https://github.com/tryterra/terra-client-java/actions/workflows/docs.yml)
![Maven Central](https://img.shields.io/maven-central/v/co.tryterra/terra-client)

# Terra Java Client Library

The official [Terra](https://tryterra.co) Java client library.

## Installation

- Check the latest version available on https://mvnrepository.com/artifact/co.tryterra/terra-client

### Requirements

- Java 11 or later

### Gradle users

Add this dependency to your project's build file:

```groovy
implementation "co.tryterra:terra-client:<version>"
```

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>co.tryterra</groupId>
    <artifactId>terra-client</artifactId>
    <version>${terra-client.version}</version>
</dependency>
```

## Usage

`TerraExample.java`

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
