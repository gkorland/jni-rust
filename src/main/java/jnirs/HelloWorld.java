package jnirs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

class HelloWorld {
  private static native String hello(String input);
  private static native byte[] helloByte(byte[] input);
  private static native void factAndCallMeBack(int n, HelloWorld callback);

  private static native long counterNew(HelloWorld callback);
  private static native void counterIncrement(long counter_ptr);
  private static native void counterDestroy(long counter_ptr);

  private static native void asyncComputation(HelloWorld callback);

  static {
    try {
      InputStream is = HelloWorld.class.getResourceAsStream("/resources/libhelloworld.so");
      File file = File.createTempFile("lib", ".so");
      OutputStream os = new FileOutputStream(file);
      byte[] buffer = new byte[1024];
      int length;
      while ((length = is.read(buffer)) != -1) {
        os.write(buffer, 0, length);
      }
      is.close();
      os.close();

      System.load(file.getAbsolutePath());
      file.deleteOnExit();
    } catch (Exception e){
      e.printStackTrace();
      System.loadLibrary("helloworld");
    }  
  }

  public static void main(String[] args) {

    String output = HelloWorld.hello("josh");
    System.out.println(output);
    byte[] outputByte = HelloWorld.helloByte("byte".getBytes());
    System.out.println(outputByte);


    HelloWorld.factAndCallMeBack(6, new HelloWorld());

    long counter_ptr = counterNew(new HelloWorld());

    for (int i = 0; i < 5; i++) {
      counterIncrement(counter_ptr);
    }

    counterDestroy(counter_ptr);

    System.out.println("Invoking asyncComputation (thread id = " + Thread.currentThread().getId() + ")");
    asyncComputation(new HelloWorld());
  }

  public void factCallback(int res) {
    System.out.println("factCallback: res = " + res);
  }

  public void counterCallback(int count) {
    System.out.println("counterCallback: count = " + count);
  }

  public void asyncCallback(int progress) {
    System.out.println("asyncCallback: thread id = " + Thread.currentThread().getId() + ", progress = " + progress + "%");
  }
}
