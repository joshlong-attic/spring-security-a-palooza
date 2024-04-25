Here is the cleaned and grammar-corrected version of your text:

# Basics

## Spring Security has...

- An `AuthenticationManager` which answers the question: "Who is making the request?" (Is it Josh, Cora, or Tasha?)
  - It works by taking an `Authentication` attempt (using a username/password, token, etc.) and returning an `Authentication` with the `authenticated` bit set to `true`. If it's not authenticated, then the contract is to throw an `Exception`.
  - The `AuthenticationManager` does not know or care in which domain you present an `Authentication`. It could handle OAuth, LDAP, Mutual TLS, Passkeys, etc. However, a very common scheme is username/password authentication, typically via an HTML form or an HTTP BASIC request.
  - Spring Security includes a specialized abstraction for this called a `UserDetailsManager`.

- An `AuthorizationManager` which answers the question: "What may the authenticated user do?" (What roles/privileges/scopes/etc. do they have? Can they read or delete things? Do they have `openid`? Are they read-only? etc.)

- Ultimately, a successful `Authentication` in Spring Security will install the `Authentication` in the `SecurityContextHolder`, which has a static `ThreadLocal` that you can obtain via `SecurityContextHolder.getSecurityContextHolderStrategy().getContext().getPrincipal().getName()`. All discussions about authorization assume that Spring Security can access this `SecurityContextHolder`, a well-known place to find the authentication, and then consult the `authorities` collection on the `Authentication`.

- But who will put this `Authentication` in the security context in the first place?

- These concepts are all high level and enable you to approach the rest of the stack with an awareness of what is doing what and why, but the specifics are vast.

- Spring Security can operate within an HTTP container, in a back-office messaging flow (think Spring Integration `MessageChannels` and Kafka), in websockets, in RSocket, or even just in a context where an object invokes methods on other objects. All of these integrations stem from the core abstractions mentioned above.

- The most common use by far is HTTP. Spring Security has a filter that can intercept all incoming requests and reject them if they do not present a credential of some sort. The question is, how do we configure the granularity of this filter? How do we reject unauthenticated requests intended for some paths in your HTTP application and not for others? Easily configure a `SecurityFilterChain` bean, like this:

```java
@Bean 
SecurityFilterChain mySecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
   return httpSecurity
        .formLogin(Customizer.withDefaults())
        .authorizeHttpRequests(http -> http
            .requestMatchers("/login/**").permitAll()
            .anyRequest().authenticated()
        )
        .csrf(Customizer.withDefaults())
        .cors(Customizer.withDefaults())
        .build();
}
```