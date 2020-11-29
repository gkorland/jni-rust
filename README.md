[![Java CI with Maven](https://github.com/gkorland/jni-rust/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/gkorland/jni-rust/actions)

# jni-rust

Java JNI example using Rust. (Based on https://github.com/jni-rs/jni-rs)

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
(The jar is set as executable jar with main class just for tests & demo reasons)


## Maven pom.xml

The maven pom.xml defines three steps:
1. `exec:exec` (part of the `compile` phase) that compiles the Rust code.
2. `resources:copy-resources` (part of the `validate` phase) that copies the .so file to targer folder.
3. `package` bundle the jar and set `jnirs.HelloWorld` as the main class in the manifest.
