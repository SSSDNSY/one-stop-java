package lang.cjuc.concurrent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        searchTargetFile( new File("W:\\code\\sql"),"4 、内联视图子查询");
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
}
