package aawork;

import java.io.*;
import java.util.*;

public class test {

    public static final String encoding = "gbk";

    public static void main(String[] args) throws IOException {
        File dir = new File("C:/Users/temp/Documents/test");
        ArrayList<File> allTxtFiles = new ArrayList<File>();
        getAlltxtFiles(dir, allTxtFiles);

        File destFile = new File("C:/Users/temp/Documents/all.txt");
        int num=0;

        for (File srcFile : allTxtFiles) {
            num= num+1;
            ArrayList<String> lines = new ArrayList<String>();
            readTxt(srcFile, lines);
            save(destFile, lines,num,srcFile.getPath());
        }
    }

    public static void readTxt(File file, ArrayList<String> lines) {
        FileInputStream fis = null;
        InputStreamReader sr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            sr = new InputStreamReader(fis, encoding);
            br = new BufferedReader(sr);
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line + System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sr != null) {
                try {
                    sr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }// finally
    }

    /**
     * @param line
     */
    public static void save(File file, ArrayList<String> lines, int index,String path) {
        FileOutputStream fos = null;
        OutputStreamWriter sw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(file, true);
            sw = new OutputStreamWriter(fos, encoding);
            bw = new BufferedWriter(sw);
            bw.write("ananpath"+path);
            bw.write(System.getProperty("line.separator"));
            for (String line : lines) {
                bw.write(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }// finally
    }

    public static ArrayList<File> getAlltxtFiles(File dir, ArrayList<File> txtFiles) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getAlltxtFiles(file, txtFiles);
            } else if (file.getName().endsWith(".py")  || file.getName().endsWith(".sh") ) {
                txtFiles.add(file);
                System.out.println(file.getName());
            }
        }
        return txtFiles;
    }
}
