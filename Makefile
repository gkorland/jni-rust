java_run: lib
	mvn package
	javac src/main/java/jnirs/HelloWorld.java && java -Djava.library.path=src/main/rust/target/debug/ -cp ./src/main/java jnirs.HelloWorld

.PHONY: lib

lib:
	cd src/main/rust && cargo build --release
	mkdir -p target/NATIVE/amd64/Linux
	cp src/main/rust/target/release/libhelloworld.so target/NATIVE/amd64/Linux