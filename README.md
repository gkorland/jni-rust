# jni-rust

Java JNI example using Rust.

This is an example project for developing a library with JNI extension based on Rust code.


## Build

```
mvn package
```

## Run executable Jar

```
java -jar ./target/jnirs-0.0.1-SNAPSHOT.jar 
```

## Project structure

The structure is following the Maven structure.
Each language under `src->main` has its own folder (Java/Rust).

And the artifacts are copied under `target` and packaged in a single jar.
(The jar is set as executable jar with main class just for tests & demo reseasons)
 
