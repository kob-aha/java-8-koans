## Java 8 Koans Project ##

The Java 8 Koans project is a collection of small exercises in the form of unit tests, designed to get Java
developers up to speed on Java 8 features.

## Getting Started ##

1.  Make sure you have [JDK 1.8+][jdk] installed
2.  Download and unzip the [Project][zip] (or clone the GitHub repository with `$ git clone https://github.com/kob-aha/java-8-koans.git`)
3.  Remove the solutions from the Koans using `$ ./gradlew removeSolutions`
4.  Execute Koan01 with `$ ./gradlew koan01` and fail (or any other Koan using `$ ./gradlew koan##`)
5.  Fix code, and execute again
6.  Keep going until you'll finish all Koans :)

## Important ##

Although the solutions are already committed, for try to first solve them yourself and than compare solutions.
This is why it is important to run step 3 above before trying to solve the failing Koans.

## Troubleshooting ##

#### How to work behind a proxy? ####

The `gradlew` script downloads Gradle for you, so you don't have to set up anything by yourself.
To allow gradlew to work through your proxy, simply add the proxy parameters to gradle.properties file located in the project's root.
The file should look as follows:

    ...
    systemProp.http.proxyHost=[proxy host]
    systemProp.http.proxyPort=[proxy port]
    systemProp.http.proxyUser=[proxy user (leave empty if not necessary)]
    systemProp.http.proxyPassword=[proxy user password (leave empty if not necessary)]
    ...

For more information see "Accessing the web via a proxy" [here][proxy].

## Credits and License ##

Many thanks goes to [nadavc] for letting me use his awesome [groovykoans] project as a base to this project.
See [groovykoans] for any additional credits.

The Java 8 Koans project is licensed under the [Apache 2 License][apache2].


Please feel free to leave comments and pull requests :)

Enjoy!

[jdk]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[zip]: https://github.com/kob-aha/java-8-koans/archive/master.zip
[proxy]: https://gradle.org/docs/current/userguide/build_environment.html
[nadavc]: http://github.com/nadavc
[groovykoans]: https://github.com/nadavc/groovykoans
[apache2]: http://www.apache.org/licenses/LICENSE-2.0.html
