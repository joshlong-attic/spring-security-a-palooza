# WebAuthN Demo

## Warning 
This code is almost entirely based on the good work of Rob Winch and Daniel Garnier Moiroux. I am not a security expert, I just play one on TV. Do not take security advice from me, but _do_ take secure code from them. Or don't. 

## Setup

You need to install Rob Winch's branch. I've copied a known-to-work version of the code into this repository, under the `spring-security-webauthn` folder.

```
./mvnw -DskipTests clean install
```

Then in your demo application (I've got one under the `passkeys` folder), add that new dependency:

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-webauthn</artifactId>
    <version>0.0.1-rwinch</version>
</dependency>
```

then in your java code, do someting like in `passkeys/passkeys/src/main/java/bootiful/passkeys/PasskeysApplication.java`.

