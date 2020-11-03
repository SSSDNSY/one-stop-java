package lang.cjuc.concurrent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/10$ 10:22$
 */
public class FileSearch extends Thread{
    private File file;
    private String tarStr;
    public FileSearch(File file,String tarStr){
        this.file = file;
        this.tarStr = tarStr;
    }

    @Override
    public void run() {
        String fileContent = readFileContent(file);
        if(fileContent.contains(tarStr)){
            System.out.println("已在 "+file.getName()+"找到 "+tarStr);
        }
    }
    String readFileContent(File file){
        String str = null;
        try(FileReader fr= new FileReader(file)){
            char[] all  = new char[(int)file.length()];
            fr.read(all);
            str = new String( all);
        }catch (IOException ioe){
            ioe.getStackTrace();
        }
        return  str;
    }

    public static void main(String[] args) {
        final long t1 = System.currentTimeMillis();
        searchTargetFile( new File("W:\\code\\algorithm\\Javascript"),"var");
        System.out.println("多线程耗时="+(System.currentTimeMillis()-t1));

        final long t2 = System.currentTimeMillis();
        final ThreadPoolExecutor pools = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
        searchTargetFileByJUC( new File("W:\\code\\algorithm\\Javascript"),"var",pools);
        pools.shutdown();
        System.out.println("线程池耗时="+(System.currentTimeMillis()-t2));

    }
    static void searchTargetFile(File f,String tar){
        if(f.isFile()){
            new FileSearch(f,tar).start();
        }else{
            for(File _f :f.listFiles()){
                searchTargetFile(_f,tar);
            }
        }
    }

    static void searchTargetFileByJUC(File f,String tar,ThreadPoolExecutor pools){
        if(f.isFile()){
            pools.execute(new FileSearch(f,tar));
        }else{
            for(File _f :f.listFiles()){
                searchTargetFile(_f,tar);
            }
        }
    }
}
