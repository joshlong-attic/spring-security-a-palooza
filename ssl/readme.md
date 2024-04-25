# SSL 

## getting the right version of `curl`

You're going to need the right version of `curl` for this demo to work. 

If you're on a Mac, like I am, then you're going to have to install the `curl` variant with OpenSSL support:

```
brew install curl-openssl 
```

Then you're going to need to make sure that MacOS is picking up the new Homebrew build of `curl` _before_ the system-installed version! 

```
export PATH="/opt/homebrew/opt/curl/bin:$PATH"
```

then, and only then, will the test in `test.sh` work as expected.

## demo flow

* run `cert.sh`. NB; it'll create a new directory at `$HOME`/Desktop/certs and put new SSL self-signed keys in that
  directory. it'll also create a file called `key_counter` if it doesn't exist. It'll put `1` if the file doesn't exist.
  If it does exist, it'll increment the number. The script will generate a new certificate with a `CN` value
  of `bootiful-1`, or `bootiful-2`, etc., based on the value in `key_counter`. This is important.
* then run the Spring Boot application: `./mvnw spring-boot:run`
* then run `test.sh`. It'll enable verbose logging so you can see the `CN` value. It should say `bootiful-1` after the first run. 
* run `certs.sh` again and then run `test.sh` again and you'll see the `bootiful-2`. 
