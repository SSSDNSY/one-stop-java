package dio.nio;

import java.nio.IntBuffer;

public class TestBuffer {
    public static void main(String[] args) {

        //静态方法初始化
        IntBuffer intBuffer = IntBuffer.allocate(Integer.MAX_VALUE);
        intBuffer.put( 1);
        intBuffer.put(13);
        intBuffer.put( 5);
        intBuffer.flip();
        System.out.println("intBuffer=" + intBuffer);
//        intBuffer.get();
//        intBuffer.get();
//        intBuffer.put(1,123);
        for (int i = 0; i < intBuffer.limit(); i++) {
            //调用get(index)方法的 position 是不会变化的， 调用get()的时候position才会变化
            System.out.print(intBuffer.get() + " \t");
        }
        System.out.println("intBuffer=" + intBuffer);
    }
}
