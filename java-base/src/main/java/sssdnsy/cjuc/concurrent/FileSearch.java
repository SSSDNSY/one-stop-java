package sssdnsy.cjuc.concurrent;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/10$ 10:22$
 */
public class FileSearch extends Thread{
    private File file;
    private String[] tarStr;
    public FileSearch(File file,String[] tarStr){
        this.file = file;
        this.tarStr = tarStr;
    }

    @Override
    public void run() {
        String fileContent = readFileContent(file);
        for (int i =0;i<tarStr.length;i++){
            if( StringUtils.contains(fileContent.toLowerCase(),tarStr[i].toLowerCase()) ){
                System.out.println("已在 "+file.getName()+"找到 "+tarStr);
            }
        }

    }

    static String readFileContent(File file){
        FileReader fileReader = new FileReader(file);
        return fileReader.readString();
    }

    static String replacePackageTag(String fullClassName){
        if(StringUtils.isBlank(fullClassName)){
            return "";
        }else {
            return StringUtils.replace(fullClassName,".","\\")+".java";
        }
    }

    static String replace2PackageTag(String fullClassName){
        if(StringUtils.isBlank(fullClassName)){
            return "";
        }else {
            return StringUtils.replace(StringUtils.replace(fullClassName,"\\",".")
                    ,".java","");
        }
    }

    static List fileList(String preFix,String  txtfileName){
        List newList = new LinkedList();
        List rawList =  cn.hutool.core.io.file.FileReader.create(new File(txtfileName)).readLines();
        for (int i = 0; i <rawList.size() ; i++) {
            newList.add(preFix+"\\"+replacePackageTag(rawList.get(i).toString()));
        }
        return newList;
    }
    static void findTargetFile(List list,String[] tarStrArr){
        Set tarGetList  = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            final File file = new File(list.get(i).toString());
            String fileContent = readFileContent(file);
            for (int j =0;j<tarStrArr.length;j++){
                if(fileContent.contains(tarStrArr[j])){
                    tarGetList.add(replace2PackageTag(file.getAbsolutePath()));
                    System.out.println("已在 "+file.getName()+"找到 "+tarStrArr[j]);
                }
            }
        }
        FileWriter fileReader = new FileWriter("E:\\desktop\\targetAction.txt");
        fileReader.appendLines(tarGetList);
    }

    public static void main(String[] args) {
//        final long t1 = System.currentTimeMillis();
//        searchTargetFile( new File("W:\\code\\algorithm\\Javascript"),"var");
//        System.out.println("多线程耗时="+(System.currentTimeMillis()-t1));
//
//        final long t2 = System.currentTimeMillis();
//        final ThreadPoolExecutor pools = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
//        searchTargetFileByJUC( new File("W:\\code\\algorithm\\Javascript"),"var",pools);
//        pools.shutdown();
//        System.out.println("线程池耗时="+(System.currentTimeMillis()-t2));

       String soaFolders = "W:\\workspaces\\tjin\\dev\\order\\src\\soa";
       String yourActionFiles = "E:\\desktop\\家庭融和\\action.txt";

        List list = fileList(soaFolders, yourActionFiles);
//        String [] tarStrArr = new String[]{"RelationTradeData","ShareRelaTradeData"};
//        String [] tarStrArr = new String[]{"RelationTradeData"};
//        String [] tarStrArr = new String[]{"ShareRelaTradeData"};
//        String [] tarStrArr = new String[]{"share"};
//        String [] tarStrArr = new String[]{"ShareInfoQry"};
        String [] tarStrArr = new String[]{"RelaUU"};
        findTargetFile(list,tarStrArr);
        System.out.println(list);
    }

    static void searchTargetFile(File f,String[] tar){
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
            pools.execute(new FileSearch(f,new String[]{tar}));
        }else{
            for(File _f :f.listFiles()){
                searchTargetFile(_f,new String[]{tar});
            }
        }
    }
}
